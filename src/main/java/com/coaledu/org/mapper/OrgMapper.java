package com.coaledu.org.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coaledu.basic.dto.TreeDto;
import com.coaledu.org.model.Org;

public interface OrgMapper {

	/**
     * 添加组织
     * @param org
     * @return
     */
    int addOrg(Org org);
    
	/**
	 * 根据组织ID删除组织
	 * @param id
	 * @return
	 */
    int deleteOrgById(Integer id);

    /**
     * 更改组织信息
     * @param org
     * @return
     */
    int updateOrg(Org org);
    
    /**
	 * 根据组织id获得组织
	 * @param id
	 * @return
	 */
	Org getOrgById(Integer id);
	
	/**
     * 获得所有的组织
     * @return Org
     */
    List<Org> getAllOrg();
    
	/**
	 * 根据组织类型来生成这颗树，如果tid为null，就获取所有的组织
	 * @param tid
	 * @return
	 */
	List<TreeDto> getOrgTree();
	
	/**
	 * 根据pid和typeId获得所有组织，用于分页显示
	 * @param pid
	 * @param typeId
	 * @return
	 */
	List<Org> getOrgByPidAndTypeId(Map<String,Integer> map);    
    
    /**
     * 获得所有的组织ID
     * @return Integer
     */
    List<Integer> listAllOrgId();
    
    /**
	 * 根据组织机构类型id获取当前的组织数
	 * @param typeId
	 * @return
	 */
	int getOrgNumsByType(int typeId);

	/**
	 * 根据组织类型，获取该父节点下究竟有多少个子组织，在添加组织的时候可以确定是否可以添加
	 * @param pid
	 * @param type
	 * @return
	 */
	int getChildNumByPidAndTid(HashMap<String, Integer> map);
	
	/**
	 * 获取所有子组织的最大排序号
	 * @param Pid
	 * @return
	 */
	int getOrgMaxOrderByPid(Integer Pid);
	
	
	/**
	 * 获取某个组织下面的所在子组织id
	 * 需要进行判断，如果组织类型是NO_TYPE-->return null
	 * 如果组织类型是DEFAULT_TYPE-->返回该组织的所有子节点
	 * 如果组织类型是ALL_TYPE-->返回所有组织
	 * 如果组织类型是DEF_TYPE-->获取所有子组织下的子节点
	 * @param id
	 * @return
	 */
	List<Integer> listAllChildIdsByOrg(int id);
	
	/**
	 * 获取某个组织下面的所有直线管理的子组织
	 * @param id
	 * @return
	 */
	List<Integer> listChildIdsByOrg(int id);
	
	List<Org> listAllChildByOrg(int id);
	
	List<Org> listChildByOrg(int id);
	/**
	 * 获取某个子组织下的节点树
	 * @param id
	 * @return
	 */
	List<TreeDto> listChildTreeByOrg(int id);
	/**
	 * 获取某个子组织下的所有的组织，也需要根据类型来进行获取
	 * @param id
	 * @return
	 */
	List<TreeDto> listAllChildTreeByOrg(int id);
	/**
	 * 根据一组ids获取树对象
	 * @param ids
	 * @return
	 */
	List<TreeDto> listChildTreeByOrgs(List<Integer> ids);
	/**
	 * 根据一个组织机构类型的编号获取父类的所有的组织机构节点，并且生成一棵树
	 * @param sn
	 * @return
	 */
	List<TreeDto> listParentTreeByOrgType(String sn);
	
	int getMaxOrder(Integer pid);
	
	List<Integer> listManagerRuleIds(int orgId);
	/**
	 * 获取某个人所在的所有组织
	 * @param pid
	 * @return
	 */
	List<Org> listPersonOrg(int pid);
}