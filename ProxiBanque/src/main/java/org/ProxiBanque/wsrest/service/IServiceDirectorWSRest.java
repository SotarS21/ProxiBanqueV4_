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

import org.ProxiBanque.wsrest.bean.Director;

/**
 * Interface de service concernant le directeur, est expos� au web en tant que web service REST, pour
 * une fourniture de service � des applications externes ( non n�cessairement JAVA) 
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
@Produces({"application/xml", "application/json"})
public interface IServiceDirectorWSRest {
	
	@GET
	@Path("/directors/{id}")
	public Director findOne(@PathParam("id") String id);

	@GET
	@Path("/directors/")
	public List<Director> findAll();

	@POST
	@Path("/directors/{dir}")
	public Response save(@PathParam("dir") Director dir);
	
	@PUT
	@Path("/directors/{dir}")
	public Response update(@PathParam("dir") Director dir);

	@DELETE
	@Path("/directors/{id}")
	public Response delete(@PathParam("id") String id);
}
