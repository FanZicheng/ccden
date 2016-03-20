package com.coaledu.auth.mapper;

import com.coaledu.auth.model.ControllerOper;

public interface ControllerOperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ControllerOper record);

    int insertSelective(ControllerOper record);

    ControllerOper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ControllerOper record);

    int updateByPrimaryKey(ControllerOper record);
}