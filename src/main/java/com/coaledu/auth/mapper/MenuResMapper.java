package com.coaledu.auth.mapper;

import com.coaledu.auth.model.MenuRes;

public interface MenuResMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRes record);

    int insertSelective(MenuRes record);

    MenuRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRes record);

    int updateByPrimaryKey(MenuRes record);
}