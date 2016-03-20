package com.coaledu.org.iservice;

import java.util.List;

import com.coaledu.org.model.Position;

public interface IPositionService {
	
	public void addPosition(Position position);
	
	public void deletePositionById(Integer id);
	
	public void updatePosition(Position position);
	
	public Position getPositionById(Integer id);
	
	public List<Position> getAllPosition();

}
