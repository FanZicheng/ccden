package com.coaledu.auth.mapper;

import com.coaledu.auth.model.ControllerRes;

public interface ControllerResMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ControllerRes record);

    int insertSelective(ControllerRes record);

    ControllerRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ControllerRes record);

    int updateByPrimaryKey(ControllerRes record);
}