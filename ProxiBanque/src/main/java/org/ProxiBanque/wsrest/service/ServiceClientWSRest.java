package org.ProxiBanque.wsrest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.service.IServiceClient;
import org.ProxiBanque.wsrest.adapter.WSRestAdapter;
import org.ProxiBanque.wsrest.bean.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceClientWSRest implements IServiceClientWSRest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAdvisorWSRest.class);

	@Autowired
	IServiceClient serviceClient;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public Client findOne(String id) {
		// TODO Auto-generated method stub
		logger.debug("entered findOne()");
		return WSRestAdapter.marshallClient(serviceClient.findOne(Long.valueOf(id)));
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		List<org.ProxiBanque.model.Client> list = serviceClient.findAll();
		List<Client> marshallList = new ArrayList<Client>();
		for (org.ProxiBanque.model.Client client : list) {

			marshallList.add(WSRestAdapter.marshallClient(client));
		}

		return marshallList;
	}

	@Override
	public List<Client> findByConseiller_Id(String idConseiller) {
		// TODO Auto-generated method stub
		List<org.ProxiBanque.model.Client> list = serviceClient.findByConseiller_Id(Long.valueOf(idConseiller));
		List<Client> marshallList = new ArrayList<Client>();
		for (org.ProxiBanque.model.Client client : list) {

			marshallList.add(WSRestAdapter.marshallClient(client));
		}

		return marshallList;
	}

	@Override
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {
		// TODO Auto-generated method stub
		List<org.ProxiBanque.model.Client> list = serviceClient.findAllByLastNameAndFirstNameAllIgnoreCase(lastName,
				firstName);
		List<Client> marshallList = new ArrayList<Client>();
		for (org.ProxiBanque.model.Client client : list) {

			marshallList.add(WSRestAdapter.marshallClient(client));
		}

		return marshallList;
	}

	@Override
	public Response save(Client client) {
		// TODO Auto-generated method stub
		if (client != null) {

			serviceClient.save(WSRestAdapter.unmarshallClient(client));
			return Response.ok().build();
		} else {

			return Response.notModified("client is null").build();
		}
	}

	@Override
	public Response update(Client client) {
		// TODO Auto-generated method stub
		if (client != null) {

			serviceClient.save(WSRestAdapter.unmarshallClient(client));
			return Response.ok().build();
		} else {

			return Response.notModified("client is null").build();
		}
	}

	@Override
	public Response delete(String id) {
		// TODO Auto-generated method stub
		serviceClient.delete(Long.valueOf(id));
		if(serviceClient.findOne(Long.valueOf(id)) == null) {
			
			return Response.ok().build();
		} else {
			
			return Response.notModified("object don't exist or delete operation failed").build();
		}
	}

}
