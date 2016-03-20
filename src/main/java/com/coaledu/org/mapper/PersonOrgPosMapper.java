package com.coaledu.org.mapper;

import com.coaledu.org.model.PersonOrgPos;

public interface PersonOrgPosMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonOrgPos record);

    int insertSelective(PersonOrgPos record);

    PersonOrgPos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonOrgPos record);

    int updateByPrimaryKey(PersonOrgPos record);
}