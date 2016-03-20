package com.coaledu.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coaledu.org.iservice.IPositionService;
import com.coaledu.org.mapper.PositionMapper;
import com.coaledu.org.model.Position;

@Service("positionService")
public class PositionService implements IPositionService {
	
	private PositionMapper positionMapper;

	public PositionMapper getPositionMapper() {
		return positionMapper;
	}
	
	@Autowired
	public void setPositionMapper(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}

	@Override
	public void addPosition(Position position) {
		positionMapper.addPosition(position);		
	}

	@Override
	public void deletePositionById(Integer id) {
		positionMapper.deletePositionById(id);
	}

	@Override
	public void updatePosition(Position position) {
		positionMapper.updatePosition(position);
	}

	@Override
	public Position getPositionById(Integer id) {
		return positionMapper.getPositionById(id);
	}

	@Override
	public List<Position> getAllPosition() {
		return positionMapper.getAllPosition();
	}

}
