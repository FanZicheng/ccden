package com.coaledu.org.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coaledu.org.iservice.IOrgTypeRuleService;
import com.coaledu.org.mapper.OrgTypeRuleMapper;
import com.coaledu.org.model.OrgTypeRule;

@Service("orgTypeRuleService")
public class OrgTypeRuleService implements IOrgTypeRuleService {

	private OrgTypeRuleMapper orgTypeRuleMapper;
	
	public OrgTypeRuleMapper getOrgTypeRuleMapper() {
		return orgTypeRuleMapper;
	}
	
	@Autowired
	public void setOrgTypeRuleMapper(OrgTypeRuleMapper orgTypeRuleMapper) {
		this.orgTypeRuleMapper = orgTypeRuleMapper;
	}

	@Override
	public void addOrgTypeRule(OrgTypeRule orgTypeRule) {
		orgTypeRuleMapper.addOrgTypeRule(orgTypeRule);		
	}

	@Override
	public void deleteOrgTypeRuleById(HashMap<String, Integer> map) {
		orgTypeRuleMapper.deleteOrgTypeRuleById(map);		
	}

	@Override
	public void updateOrgTypeRule(OrgTypeRule orgTypeRule) {
		orgTypeRuleMapper.updateOrgTypeRule(orgTypeRule);
	}

	@Override
	public OrgTypeRule getOrgTypeRuleById(Integer id) {
		return orgTypeRuleMapper.getOrgTypeRuleById(id);
	}

}
