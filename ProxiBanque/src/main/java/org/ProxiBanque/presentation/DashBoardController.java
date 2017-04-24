package org.ProxiBanque.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.ProxiBanque.model.Advisor;
import org.ProxiBanque.service.IServiceDashboard;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class DashBoardController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -411390593715013672L;

	@Autowired
	private IServiceDashboard serviceDashboard;
	
	private PieChartModel pieModelAdvisor, pieModelDirector;

	@PostConstruct
    public void init() {
		   
		
		long id = ((Advisor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("advisor")).getId();
		
        pieModelAdvisor = new PieChartModel();
        pieModelAdvisor.setData(serviceDashboard.getPieMapAdvisor(id));
        pieModelAdvisor.setTitle("Statistiques Conseiller n°" + id);
        pieModelAdvisor.setShowDataLabels(true);
        pieModelAdvisor.setLegendPosition("w");
        
        pieModelDirector = new PieChartModel();
        pieModelDirector.setData(serviceDashboard.getPieMapDirector());
        pieModelDirector.setTitle("Statistiques Directeur");
        pieModelDirector.setShowDataLabels(true);
        pieModelDirector.setLegendPosition("w");
    }

	public PieChartModel getPieModelAdvisor() {
		return pieModelAdvisor;
	}

	public void setPieModelAdvisor(PieChartModel pieModelAdvisor) {
		this.pieModelAdvisor = pieModelAdvisor;
	}

	public PieChartModel getPieModelDirector() {
		return pieModelDirector;
	}

	public void setPieModelDirector(PieChartModel pieModelDirector) {
		this.pieModelDirector = pieModelDirector;
	}
	

	
	
	
	
	
}
