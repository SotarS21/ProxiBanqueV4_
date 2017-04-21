package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Director;

public interface IServiceDirector {
	
	public Director findOne(Long id);

	public List<Director> findAll();

	public void save(Director dir);

	public void update(Director dir);

	public void delete(Long id);
}
