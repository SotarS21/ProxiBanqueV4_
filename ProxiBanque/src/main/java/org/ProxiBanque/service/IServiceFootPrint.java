package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Footprint;

public interface IServiceFootPrint {
	
	public Footprint findOne(Long id);
	public List<Footprint> findAll();
	public void save(Footprint footPrint);
	public void update(Footprint footPrint);
	public void delete(Long id);
}
