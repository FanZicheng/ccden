package com.coaledu.org.iservice;

import java.util.List;
import java.util.Map;

import com.coaledu.basic.dto.TreeDto;
import com.coaledu.org.model.Org;

public interface IOrgService {
	
	public void addOrg(Org org, Integer pid);
    
    public void deleteOrg(Integer id);
    
    public void updateOrg(Org org);
    
    public int getOrgMaxOrderByPid(Integer Pid);
    
    /**
     * 根据组织的pid和typeId获得所有组织信息，进行分页
     * @param pid
     * @param typeId
     * @return
     */
    public List<Org> getOrgByPidAndTypeId(Map<String,Integer> map);
	
	/**
     * 获取组织树
     * @return
     */
    public List<TreeDto> getOrgTree();
    
    /**
     * 根据组织ID获得组织信息
     * @param id
     * @return
     */
    public Org getOrgById(int id);
}
