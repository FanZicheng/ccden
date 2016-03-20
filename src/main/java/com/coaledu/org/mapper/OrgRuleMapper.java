package com.coaledu.org.mapper;

import com.coaledu.org.model.OrgRule;

public interface OrgRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgRule record);

    int insertSelective(OrgRule record);

    OrgRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgRule record);

    int updateByPrimaryKey(OrgRule record);
    
    
    /**
	 * 添加某个组织机构可以管理的子组织的id，这个子组织的id其实就是可以管理的所有子节点id
	 * @param orgId
	 * @param id
	 */
    public void addRule(int orgId,int cid);
	/**
	 * 添加一组子组织机构
	 * @param orgId
	 * @param cids
	 */
	public void addRule(int orgId,Integer[] cids);
	/**
	 * 删除子组织
	 * @param orgId
	 * @param cid
	 */
	public void deleteRule(int orgId,int cid);
}