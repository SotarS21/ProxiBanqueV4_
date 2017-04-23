package org.ProxiBanque.wsrest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.service.IServiceAdvisor;
import org.ProxiBanque.wsrest.adapter.WSRestAdapter;
import org.ProxiBanque.wsrest.bean.Advisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceAdvisorWSRest implements IServiceAdvisorWSRest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAdvisorWSRest.class);

	@Autowired
	IServiceAdvisor serviceAdvisor;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public Advisor findOne(String id) {

		logger.debug("Call advisor findOne: id " + id);
		
		return WSRestAdapter.marshallAdvisor(serviceAdvisor.findOne(Long.valueOf(id)));
	}

	@Override
	public List<Advisor> findAll() {

		logger.debug("Call advisor findAll()");

		List<Advisor> marshallList = new ArrayList<Advisor>();
		List<org.ProxiBanque.model.Advisor> list = serviceAdvisor.findAll();
		for (org.ProxiBanque.model.Advisor advisor : list) {
			
			marshallList.add(WSRestAdapter.marshallAdvisor(advisor));
			
		}
		
		return marshallList;
	}

	@Override
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {

		logger.debug("Call advisor  findByLastNameAndFirstNameAllIgnoreCase() last name : " + lastName
				+ "| first name :" + firstName);
		List<Advisor> marshallList = new ArrayList<Advisor>();
		List<org.ProxiBanque.model.Advisor> list = serviceAdvisor.findByLastNameAndFirstNameAllIgnoreCase(firstName, firstName);
		for (org.ProxiBanque.model.Advisor advisor : list) {
			
			marshallList.add(WSRestAdapter.marshallAdvisor(advisor));
			
		}
		
		return marshallList;
	}

	@Override
	public Response save(Advisor advisor) {

		if (advisor != null) {

			logger.debug("Call advisor save () : client = " + advisor);
			org.ProxiBanque.model.Advisor unmarhallAdvisor = WSRestAdapter.unmarshallAdvisor(advisor);
			serviceAdvisor.save(unmarhallAdvisor);
			return Response.ok().build();
		}
		return Response.notModified("null object").build();
	}

	@Override
	public Response update(Advisor advisor) {

		if (advisor != null) {

			logger.debug("Call advisor save () : client = " + advisor);
			org.ProxiBanque.model.Advisor unmarhallAdvisor = WSRestAdapter.unmarshallAdvisor(advisor);
			serviceAdvisor.save(unmarhallAdvisor);
			return Response.ok().build();
		}
		return Response.notModified("null object").build();

	}

	@Override
	public Response delete(String id) {

		logger.debug("Call advisor service update() : id " + id);
		serviceAdvisor.delete(Long.valueOf(id));
		if (serviceAdvisor.findOne(Long.valueOf(id)) == null) {

			return Response.ok().build();
		} else {

			return Response.notModified("not existing or failed to delete").build();
		}

	}

}
