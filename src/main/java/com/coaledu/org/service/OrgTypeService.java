package com.coaledu.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coaledu.basic.dto.OrgTypeDto;
import com.coaledu.basic.dto.OrgTypeRuleDto;
import com.coaledu.org.iservice.IOrgTypeService;
import com.coaledu.org.mapper.OrgTypeMapper;
import com.coaledu.org.model.OrgType;

@Service("orgTypeService")
public class OrgTypeService implements IOrgTypeService {

	private OrgTypeMapper orgTypeMapper;
	
	public OrgTypeMapper getOrgTypeMapper() {
		return orgTypeMapper;
	}
	@Autowired
	public void setOrgTypeMapper(OrgTypeMapper orgTypeMapper) {
		this.orgTypeMapper = orgTypeMapper;
	}

	@Override
	public List<OrgTypeDto> getChildOrgTypeByTypeId(Integer tid) {
		return orgTypeMapper.getChildOrgTypeByTypeId(tid);
	}
	
	@Override
	public List<OrgType> getAllOrgType() {
		return orgTypeMapper.getAllOrgType();
	}
	
	@Override
	public void addOrgType(OrgType orgType) {
		orgTypeMapper.addOrgType(orgType);		
	}
	
	@Override
	public void deleteOrgTypeById(Integer id) {
		orgTypeMapper.deleteOrgTypeById(id);		
	}
	
	@Override
	public void updateOrgType(OrgType orgType) {
		orgTypeMapper.updateOrgType(orgType);
	}
	@Override
	public OrgType getOrgTypeById(Integer id) {
		return orgTypeMapper.getOrgTypeById(id);
	}
	@Override
	public List<OrgTypeRuleDto> getOrgTypeRuleTreeByOrgTypeId(int id) {
		List<OrgTypeDto> otds = this.getChildOrgTypeByTypeId(id);
		List<OrgTypeRuleDto> ortds = new ArrayList<OrgTypeRuleDto>();
		List<Integer> aids = new ArrayList<Integer>();
		OrgTypeRuleDto otrd;
		//把存在的添加进行
		for(OrgTypeDto otd:otds) {
			otrd = new OrgTypeRuleDto();
			otrd.setCid(otd.getCid());
			otrd.setCname(otd.getCname());
			otrd.setExists(true);
			otrd.setNum(otd.getNum());
			ortds.add(otrd);
			aids.add(otd.getCid());
		}
		List<OrgType> ots = this.getAllOrgType();
		for(OrgType ot:ots) {
			if(ot.getId()==id) continue;
			if(aids.contains(ot.getId())) continue;
			otrd = new OrgTypeRuleDto();
			otrd.setCid(ot.getId());
			otrd.setCname(ot.getName());
			otrd.setExists(false);
			otrd.setNum(0);
			ortds.add(otrd);
		}
		return ortds;
	}
}
