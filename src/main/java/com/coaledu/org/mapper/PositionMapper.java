package com.coaledu.org.mapper;

import java.util.List;

import com.coaledu.org.model.Position;

public interface PositionMapper {
	
	int addPosition(Position position);
	
	int deletePositionById(Integer id);
	
	int updatePosition(Position position);
	
	Position getPositionById(Integer id);
	
	/**
     * 获得所有岗位信息
     * @return
     */
	List<Position> getAllPosition();
    
    /**
     * 根据岗位序号获得岗位信息
     * @param sn
     * @return
     */    
	public Position getPositionBySn(String sn);
	
	/**
	 * 获取某个组织中存在的所有岗位列表
	 * @param orgId
	 * @return
	 */
	public List<Position> getPositionByOrgId(int id);
}