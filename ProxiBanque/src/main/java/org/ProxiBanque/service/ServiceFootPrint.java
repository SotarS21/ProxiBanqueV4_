package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.dao.ICRUDFootPrint;
import org.ProxiBanque.model.Footprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFootPrint implements IServiceFootPrint {

	@Autowired
	ICRUDFootPrint dao;
	
	@Override
	public Footprint findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Footprint> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(Footprint footPrint) {
		dao.save(footPrint);

	}

	@Override
	public void update(Footprint footPrint) {
		this.save(footPrint);

	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

}
