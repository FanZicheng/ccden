package com.coaledu.org.iservice;

import java.util.HashMap;
import com.coaledu.org.model.OrgTypeRule;

public interface IOrgTypeRuleService {
	
	public void addOrgTypeRule(OrgTypeRule orgTypeRule);
	
	public void deleteOrgTypeRuleById(HashMap<String, Integer> map);
	
	public void updateOrgTypeRule(OrgTypeRule orgTypeRule);
	
	public OrgTypeRule getOrgTypeRuleById(Integer id);

}
