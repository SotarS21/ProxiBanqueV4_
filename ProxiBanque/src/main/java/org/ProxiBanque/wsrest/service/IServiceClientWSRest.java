package org.ProxiBanque.wsrest.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.ProxiBanque.wsrest.bean.Client;

@Produces({"application/xml", "application/json"})
public interface IServiceClientWSRest {
	
	@GET
	@Path("/clients/{id}")
	public Client findOne(@PathParam("id") String id);
	
	@GET
	@Path("/clients/")
	public List<Client> findAll();
	
	@GET
	@Path("/clients/{idConseiller}")
	public List<Client> findByConseiller_Id(@PathParam("idConseiller") String idConseiller);
	
	@GET
	@Path("/clients/{lastName}&{firstName}")
	public List<Client> findAllByLastNameAndFirstNameAllIgnoreCase(@PathParam("lastName") String lastName, @PathParam("firstName") String firstName);
	
	@POST
	@Path("/clients/{client}")
	public Response save(@PathParam("client") Client client);
	
	@PUT
	@Path("/clients/{client}")
	public Response update(@PathParam("client") Client client);
	
	@DELETE
	@Path("/clients/{id}")
	public Response delete(@PathParam("id") String id);
}
