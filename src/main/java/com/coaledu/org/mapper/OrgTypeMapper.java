package com.coaledu.org.mapper;

import java.util.List;

import com.coaledu.basic.dto.OrgTypeDto;
import com.coaledu.org.model.OrgType;
public interface OrgTypeMapper {
	
    int addOrgType(OrgType orgType);

    int deleteOrgTypeById(Integer id);

    int updateOrgType(OrgType orgType);
    
    OrgType getOrgTypeById(Integer id);
    
    /**
	 * 根据父节点获取子结点，子节点是个DTO对象，里面存储了父节点的名称、子节点的名称和数量
	 * @param pid
	 * @return
	 */
	List<OrgTypeDto> getChildOrgTypeByTypeId(Integer tid);
	
	List<OrgType> getAllOrgType();
}