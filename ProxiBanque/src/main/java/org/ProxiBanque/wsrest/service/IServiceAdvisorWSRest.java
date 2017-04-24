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

import org.ProxiBanque.wsrest.bean.Advisor;

/**
 * Interface de service concernant le conseiller, est exposé au web en tant que web service REST, pour
 * une fourniture de service à des applications externes ( non nécessairement JAVA) 
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
@Produces({"application/xml", "application/json"})
public interface IServiceAdvisorWSRest {

	@GET
	@Path("/advisors/{id}")
	public Advisor findOne(@PathParam("id")String id);
	
	@GET
	@Path("/advisors/")
	public List<Advisor> findAll();
	
	@GET
	@Path("/advisors/{lastName}&{firstName}")
	public List<Advisor> findByLastNameAndFirstNameAllIgnoreCase(@PathParam("lastName")String lastName, @PathParam("firstName")String firstName);
	
	@POST
	@Path("/advisors/{advisor}")
	public Response save(@PathParam("advisor")Advisor advisor);
	
	@PUT
	@Path("/advisors/{advisor}")
	public Response update(@PathParam("advisor")Advisor advisor);
	
	@DELETE
	@Path("/advisors/{id}")
	public Response delete(@PathParam("id")String id);
	
}
