package com.coaledu.org.mapper;

import java.util.HashMap;

import com.coaledu.org.model.OrgTypeRule;

public interface OrgTypeRuleMapper {    

    int addOrgTypeRule(OrgTypeRule orgTypeRule);
    
    int deleteOrgTypeRuleById(HashMap<String, Integer> map);    

    int updateOrgTypeRule(OrgTypeRule orgTypeRule);

    OrgTypeRule getOrgTypeRuleById(Integer id);
    
    int getOrgTypeRuleNum(HashMap<String, Integer> map);    
    
}