package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.Advisor;

public interface IServiceAdvisor {

	public Advisor findOne(Long id);
	public List<Advisor> findAll();
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
	public void save(Advisor client);
	public void update(Advisor client);
	public void delete(Long id);
	
}
