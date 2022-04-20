package com.oracle.ssm.vo;

import java.io.Serializable;
import java.util.Date;

public class GoodsVo implements Serializable{
	private int id;
	private String name;
	private Date start;
	private Date end;
	private String price;
	private String url;
	private int initCount;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getInitCount() {
		return initCount;
	}
	public void setInitCount(int initCount) {
		this.initCount = initCount;
	}
	
	
	
	

}
