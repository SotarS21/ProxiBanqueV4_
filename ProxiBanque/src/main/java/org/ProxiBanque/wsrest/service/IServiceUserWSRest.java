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

import org.ProxiBanque.model.User;

/**
 * Interface de service concernant le user de la présentation, est exposé au web en tant que web service REST, pour
 * une fourniture de service à des applications externes ( non nécessairement JAVA) 
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
@Produces({"application/xml", "application/json"})
public interface IServiceUserWSRest {

	@POST
	@Path("/users/{user}")
	public Response save(@PathParam("user") User user);
	
	@PUT
	@Path("/users/{user}")
	public Response update(@PathParam("user") User user);
	
	@GET
	@Path("/users/{id}")
	public User findOne(@PathParam("id") String id);
	
	@GET
	@Path("/users/")
	public List<User> findAll();
	
	@DELETE
	@Path("/users/{id}")
	public Response delete(@PathParam("id") String id);
	
	@GET
	@Path("/users/{login}&{password}")
	public User findFirstByLoginAndPasswordAllIgnoreCase(@PathParam("login") String login, @PathParam("password") String password);
	
	@GET
	@Path("/users/{login}")
	public List<User> findAllByLoginAllIgnoreCase(@PathParam("login") String login);
	
	@GET
	@Path("/users/{password}")
	public List<User> findAllByPasswordAllIgnoreCase(@PathParam("password") String Password);
}
