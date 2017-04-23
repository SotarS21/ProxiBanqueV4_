package org.ProxiBanque.wsrest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.service.IServiceDirector;
import org.ProxiBanque.wsrest.adapter.WSRestAdapter;
import org.ProxiBanque.wsrest.bean.Director;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceDirectorWSRest implements IServiceDirectorWSRest {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceAdvisorWSRest.class);

	@Autowired
	IServiceDirector serviceDirector;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	public Director findOne(String id) {
		// TODO Auto-generated method stub
		logger.debug("entered findOne()");
		return WSRestAdapter.marshallDirector(serviceDirector.findOne(Long.valueOf(id)));
	}

	@Override
	public List<Director> findAll() {
		// TODO Auto-generated method stub
		List<org.ProxiBanque.model.Director> list = serviceDirector.findAll();
		List<Director> marshallList = new ArrayList<Director>();
		for (org.ProxiBanque.model.Director director : list) {

			marshallList.add(WSRestAdapter.marshallDirector(director));
		}

		return marshallList;
	}

	@Override
	public Response save(Director director) {
		// TODO Auto-generated method stub
		if (director != null) {

			serviceDirector.save(WSRestAdapter.unmarshallDirector(director));
			return Response.ok().build();
		} else {

			return Response.notModified("director is null").build();
		}
	}

	@Override
	public Response update(Director director) {
		// TODO Auto-generated method stub
		if (director != null) {

			serviceDirector.save(WSRestAdapter.unmarshallDirector(director));
			return Response.ok().build();
		} else {

			return Response.notModified("director is null").build();
		}
	}

	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		serviceDirector.delete(Long.valueOf(id));
		if(serviceDirector.findOne(Long.valueOf(id)) == null) {
			
			return Response.ok().build();
		} else {
			
			return Response.notModified("object don't exist or delete operation failed").build();
		}
	}

}
