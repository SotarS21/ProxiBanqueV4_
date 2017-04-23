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

import org.ProxiBanque.exception.VirementException;
import org.ProxiBanque.wsrest.bean.BankAccount;
import org.ProxiBanque.wsrest.bean.Client;

@Produces({"application/xml", "application/json"})
public interface IServiceAccountWSRest {

	@GET
	@Path("/accounts/{id}")
	public BankAccount getAccount (@PathParam("id") String id);
	
	@POST
	@Path("/accounts/{account}&{client}")
	public Response addAccount (@PathParam("account") BankAccount account, @PathParam("client") Client client);
	
	@GET
	@Path("/accounts/")
	public List<BankAccount> listAccounts();
	
	@DELETE
	@Path("/accounts/{id}")
	public Response deleteAccount (@PathParam("id") String id);
	
	@PUT
	@Path("/accounts/{account}")
	public Response editAccount (@PathParam("account") BankAccount account);
	
	@GET
	@Path("/accounts/{idclient}")
	public List<BankAccount> getAccountsByClientId(@PathParam("idclient")String idClient);
	
	@POST
	@Path("/accounts/{debiteur}&{crediteur}&{montant}")
	public Response doVirement(@PathParam("debiteur") BankAccount debiteur, @PathParam("crediteur") BankAccount crediteur, @PathParam("montant") double montant) throws VirementException;
}
