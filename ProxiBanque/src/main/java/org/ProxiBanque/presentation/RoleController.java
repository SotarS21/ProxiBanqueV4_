package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.model.Director;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe de contr�ler permettant d'effectuer des actions sur le client dans
 * nos pages xhtml
 * 
 * @author kevin jonas
 *
 */

@Controller
@SessionScoped
public class RoleController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	FacesContext ctx = FacesContext.getCurrentInstance();
	ExternalContext extCtx = ctx.getExternalContext();
	Map<String, Object> sessionMap = extCtx.getSessionMap();
	@Autowired
	private UserController userController;

	public String listAdvisor() {

//		Advisor advisor = (Advisor) sessionMap.get("advisor");
		Director director = (Director) sessionMap.get("director");
		LOGGER.debug("listAdvisor");
		if (director != null)
			return "listAdvisor?faces-redirect=true";
		else
			return null;
	}

	public String listClient() {

		
		Advisor advisor = (Advisor) sessionMap.get("advisor");
//		Director director = (Director) sessionMap.get("director");
		LOGGER.debug("listClient");
		if (advisor != null)
			return "listClient?faces-redirect=true";
		else
			return null;
	}

	public String ajoutClient() {

		Advisor advisor = (Advisor) sessionMap.get("advisor");
//		Director director = (Director) sessionMap.get("director");
		LOGGER.debug("ajoutClient");
		if (advisor != null)
			return "ajoutClient?faces-redirect=true";
		else
			return null;
	}

}
