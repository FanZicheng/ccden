package com.coaledu.org.iservice;

import java.util.List;

import com.coaledu.basic.dto.OrgTypeDto;
import com.coaledu.basic.dto.OrgTypeRuleDto;
import com.coaledu.org.model.OrgType;

public interface IOrgTypeService {

	public void addOrgType(OrgType orgType);
	
	public void deleteOrgTypeById(Integer id);
	
	public void updateOrgType(OrgType orgType);
	
	public OrgType getOrgTypeById(Integer id);
	
	public List<OrgType> getAllOrgType();
	
	public List<OrgTypeDto> getChildOrgTypeByTypeId(Integer tid);
	
	public List<OrgTypeRuleDto> getOrgTypeRuleTreeByOrgTypeId(int id);

}
