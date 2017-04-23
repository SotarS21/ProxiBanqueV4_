package org.ProxiBanque.wsrest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.ProxiBanque.service.IServiceAdvisor;
import org.ProxiBanque.service.ServiceAdvisor;
import org.ProxiBanque.wsrest.bean.Address;
import org.ProxiBanque.wsrest.bean.Advisor;
import org.ProxiBanque.wsrest.bean.Client;
import org.ProxiBanque.wsrest.bean.CurrentAccount;
import org.ProxiBanque.wsrest.bean.SavingAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ServiceAdvisorWSRest implements IServiceAdvisorWSRest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceAdvisor.class);

	@Autowired
	IServiceAdvisor serviceAdvisor;

	@PostConstruct
	public void init() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public Advisor findOne(String id) {

		logger.debug("Call advisor findOne: id " + id);
		org.ProxiBanque.model.Advisor advisorTemp = serviceAdvisor.findOne(Long.valueOf(id));
		Address address = new Address();
		address.setNumber(advisorTemp.getAddress().getNumber());
		address.setTown(advisorTemp.getAddress().getTown());
		address.setZipCode(advisorTemp.getAddress().getZipCode());
		Advisor advisor = new Advisor(advisorTemp.getFirstName(), advisorTemp.getLastName(), address);

		for (org.ProxiBanque.model.Client client : advisorTemp.getClients()) {

			address.setNumber(client.getAddress().getNumber());
			address.setTown(client.getAddress().getTown());
			address.setZipCode(client.getAddress().getZipCode());

			CurrentAccount current = new CurrentAccount(client.getCurrentAccount().getDecouvert(),
					client.getCurrentAccount().getSold());
			current.setType(client.getCurrentAccount().getType());

			SavingAccount saving = new SavingAccount(client.getSafeAccount().getSold(),
					client.getSafeAccount().getTauxDeRemuneration(), client.getSafeAccount().getDecouvert());
			saving.setType(client.getSafeAccount().getType());

			Client client2 = new Client(client.getFirstName(), client.getLastName(), address, client.getType(),
					client.isRitch());
			client2.setCurrentAccount(current);
			client2.setSafeAccount(saving);
			advisor.addClient(client2);

		}

		return advisor;
	}

	@Override
	public List<Advisor> findAll() {

		logger.debug("Call advisor findAll()");

		List<org.ProxiBanque.model.Advisor> list = serviceAdvisor.findAll();
		List<Advisor> listAdvisor = new ArrayList<Advisor>();
		
		for (org.ProxiBanque.model.Advisor advisorTemp : list) {
			
			Address address = new Address();
			address.setNumber(advisorTemp.getAddress().getNumber());
			address.setTown(advisorTemp.getAddress().getTown());
			address.setZipCode(advisorTemp.getAddress().getZipCode());
			Advisor advisor = new Advisor(advisorTemp.getFirstName(), advisorTemp.getLastName(), address);

			for (org.ProxiBanque.model.Client client : advisorTemp.getClients()) {

				address.setNumber(client.getAddress().getNumber());
				address.setTown(client.getAddress().getTown());
				address.setZipCode(client.getAddress().getZipCode());

				CurrentAccount current = new CurrentAccount(client.getCurrentAccount().getDecouvert(),
						client.getCurrentAccount().getSold());
				current.setType(client.getCurrentAccount().getType());

				SavingAccount saving = new SavingAccount(client.getSafeAccount().getSold(),
						client.getSafeAccount().getTauxDeRemuneration(), client.getSafeAccount().getDecouvert());
				saving.setType(client.getSafeAccount().getType());

				Client client2 = new Client(client.getFirstName(), client.getLastName(), address, client.getType(),
						client.isRitch());
				client2.setCurrentAccount(current);
				client2.setSafeAccount(saving);
				advisor.addClient(client2);

				listAdvisor.add(advisor);
			}
		}

		return listAdvisor;
	}

	@Override
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName) {

		logger.debug("Call advisor  findByLastNameAndFirstNameAllIgnoreCase() last name : " + lastName
				+ "| first name :" + firstName);
		List<org.ProxiBanque.model.Advisor> list = serviceAdvisor.findByLastNameAndFirstNameAllIgnoreCase(firstName, firstName);
		List<Advisor> listAdvisor = new ArrayList<Advisor>();
		
		for (org.ProxiBanque.model.Advisor advisorTemp : list) {
			
			Address address = new Address();
			address.setNumber(advisorTemp.getAddress().getNumber());
			address.setTown(advisorTemp.getAddress().getTown());
			address.setZipCode(advisorTemp.getAddress().getZipCode());
			Advisor advisor = new Advisor(advisorTemp.getFirstName(), advisorTemp.getLastName(), address);

			for (org.ProxiBanque.model.Client client : advisorTemp.getClients()) {

				address.setNumber(client.getAddress().getNumber());
				address.setTown(client.getAddress().getTown());
				address.setZipCode(client.getAddress().getZipCode());

				CurrentAccount current = new CurrentAccount(client.getCurrentAccount().getDecouvert(),
						client.getCurrentAccount().getSold());
				current.setType(client.getCurrentAccount().getType());

				SavingAccount saving = new SavingAccount(client.getSafeAccount().getSold(),
						client.getSafeAccount().getTauxDeRemuneration(), client.getSafeAccount().getDecouvert());
				saving.setType(client.getSafeAccount().getType());

				Client client2 = new Client(client.getFirstName(), client.getLastName(), address, client.getType(),
						client.isRitch());
				client2.setCurrentAccount(current);
				client2.setSafeAccount(saving);
				advisor.addClient(client2);

				listAdvisor.add(advisor);
			}
		}

		return listAdvisor;
	}

	@Override
	public Response save(Advisor advisor) {

		if (advisor != null) {

			logger.debug("Call advisor save () : client = " + advisor);
			serviceAdvisor.save(advisor);
			return Response.ok().build();
		}
		return Response.notModified("null object").build();
	}

	@Override
	public Response update(Advisor advisor) {

		if (advisor != null) {

			logger.debug("Call advisor save () : client = " + advisor);
			serviceAdvisor.save(advisor);
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
