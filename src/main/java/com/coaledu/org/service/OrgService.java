 package com.coaledu.org.service;

import com.coaledu.basic.model.SysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coaledu.basic.dto.TreeDto;
import com.coaledu.org.iservice.IOrgService;
import com.coaledu.org.mapper.OrgMapper;
import com.coaledu.org.mapper.OrgTypeMapper;
import com.coaledu.org.mapper.OrgTypeRuleMapper;
import com.coaledu.org.model.Org;

@Service("orgService")
public class OrgService implements IOrgService {
	
	private OrgMapper orgMapper;
	private OrgTypeMapper orgTypeMapper;
	private OrgTypeRuleMapper orgTypeRuleMapper;

	public OrgMapper getOrgMapper() {
		return orgMapper;
	}
	
	@Autowired
	public void setOrgMapper(OrgMapper orgMapper) {
		this.orgMapper = orgMapper;
	}
	
	public OrgTypeRuleMapper getOrgTypeRuleMapper() {
		return orgTypeRuleMapper;
	}
	
	@Autowired
	public void setOrgTypeRuleMapper(OrgTypeRuleMapper orgTypeRuleMapper) {
		this.orgTypeRuleMapper = orgTypeRuleMapper;
	}

	public OrgTypeMapper getOrgTypeMapper() {
		return orgTypeMapper;
	}
	
	@Autowired
	public void setOrgTypeMapper(OrgTypeMapper orgTypeMapper) {
		this.orgTypeMapper = orgTypeMapper;
	}

	private void checkChildOrgNum(Org cOrg,Org pOrg) {
		if(pOrg==null) return;
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("pid", pOrg.getTid());
		map.put("cid", cOrg.getTid());
		int rnum = orgTypeRuleMapper.getOrgTypeRuleNum(map);
		if(rnum<0) return;
		
		map.clear();
		map.put("pid", pOrg.getId());
		map.put("tid", cOrg.getTid());
		int hnum = orgMapper.getChildNumByPidAndTid(map);

		if(hnum>=rnum)throw new SysException(pOrg.getName()+"下的"+cOrg.getName()+"的数量已经达到最大化");
	}
	
	@Override
	public List<TreeDto> getOrgTree() {
		return orgMapper.getOrgTree();
	}

	@Override
	public Org getOrgById(int id) {
		return orgMapper.getOrgById(id);
	}

	@Override
	public List<Org> getOrgByPidAndTypeId(Map<String,Integer> map) {
		return orgMapper.getOrgByPidAndTypeId(map);
	}

	@Override
	public void addOrg(Org org, Integer pid) {
		if(pid != null)
		{
			Org pOrg = orgMapper.getOrgById(pid);
			if(pOrg==null) throw new SysException("要添加的父亲组织不存在!");
			checkChildOrgNum(org,pOrg);
			org.setPid(pOrg.getId());
		}
		org.setOrderNum(orgMapper.getOrgMaxOrderByPid(pid)+1);
		org.setTname(orgTypeMapper.getOrgTypeById(org.getTid()).getName());
		orgMapper.addOrg(org);		
	}

	@Override
	public int getOrgMaxOrderByPid(Integer Pid) {
		return orgMapper.getOrgMaxOrderByPid(Pid);
	}

	@Override
	public void deleteOrg(Integer id) {
		orgMapper.deleteOrgById(id);		
	}

	@Override
	public void updateOrg(Org org) {
		orgMapper.updateOrg(org);
		
	}
}
