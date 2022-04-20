package com.oracle.ssm.mapper;

import com.oracle.ssm.model.UserDetails;

public interface UserDetailsMapper {
    int insert(UserDetails record);

    int insertSelective(UserDetails record);
}