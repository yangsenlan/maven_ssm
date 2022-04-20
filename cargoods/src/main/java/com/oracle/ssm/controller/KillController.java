package com.oracle.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.ssm.model.User;
import com.oracle.ssm.vo.GoodsVo;

@Controller
@RequestMapping("/kill")
public class KillController {
	@Autowired
	private RedisTemplate jedis;

	/**
	 * ȥ��������ɱҳ��
	 * @return
	 */
	@RequestMapping("/killList")
	public String killList(HttpServletRequest request) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		String goods_key_prefix="kill:goods:"+date+":id:*";
		Set<String> keySets=jedis.keys(goods_key_prefix);
		//�Ե��ղ���������ɱ����Ʒ��key ��������
		List<String> keyList=new ArrayList(keySets);
		Collections.sort(keyList);
		
		List<GoodsVo> list=new ArrayList();
		for(String key:keyList) {
			//ȡ��value��value�ǲ���goodVo��Ķ���
			GoodsVo vo=(GoodsVo)jedis.getHashValueSerializer().deserialize((byte[])(jedis.boundValueOps(key).get()));
			//ÿһ����Ʒ�Ŀ��������Ҫȥͳ�Ƴ��ÿ����г���
			vo.setInitCount((jedis.boundListOps("kill:goods:"+date+":list:"+vo.getId()).size()).intValue());
			list.add(vo);
		}
		request.setAttribute("goodsList", list);
		return "kill/index";
	}
	
	/**
	 * ������ɱ��ʵ�ֹ���
	 * @throws Exception 
	 */
	@RequestMapping("/killGoods")
	public void kill(String id,HttpServletResponse response,HttpServletRequest request) throws Exception {
		//��Ʒid   ��Ҫ���� 2 3��key value
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		//ƴkey   key1�ǿ����� key2�������ɹ��û��б�
		String key1="kill:goods:"+date+":list:"+id;
		String key2="kill:users:"+date+":set:"+id;
			
		String userKey="session:"+request.getRemoteAddr();
		//�ж��û��ǲ����Ѿ���¼����
		if(jedis.hasKey(userKey)) {
			//�ж���û�е�����ʱ��
			String goods_key="kill:goods:"+date+":id:"+id;
			GoodsVo vo=(GoodsVo)jedis.getHashValueSerializer().deserialize((byte[])(jedis.boundValueOps(goods_key).get()));
			if(vo.getStart().before(new Date())) {
			//�жϿ�滹��ô
				if(jedis.boundListOps(key1).size()>0) {
					//�ж��û��ǲ����Ѿ���������Ʒ��
					User user=(User)jedis.getHashValueSerializer().deserialize((byte[])(jedis.boundValueOps(userKey).get()));
					String userId=user.getId()+"";
					if(jedis.boundSetOps(key2).isMember(userId)) {
						//�Ѿ������ˣ���Ҫ������
						response.getWriter().write("3");
						
					}else {
						//�û������������߼�
						String xuhao=null;
						//�ұߵ���һ����ţ����������Null,���������ɹ���
						if((xuhao=(String) jedis.boundListOps(key1).rightPop())!=null) {
							//�ɹ�֮����Ҫ���û���id ��¼����
							jedis.boundSetOps(key2).add(userId);
						}
						//�����ɹ���
						response.getWriter().write("4");
						
						
					}
				}else {
					//�Ѿ���������
					response.getWriter().write("2");
				}
			}
			else {
				//��û������ʱ��
				response.getWriter().write("5");
			}
			}
			else {
				//û��½�� 
				response.getWriter().write("1");
			}
			
			
			
			
		}
	

}
