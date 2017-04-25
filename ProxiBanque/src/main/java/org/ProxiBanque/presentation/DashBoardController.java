package org.ProxiBanque.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
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
	
	private List<String> listModel = new ArrayList<>();
	private PieChartModel pieModelAdvisor, pieModelDirector;
	private long id;

	
	
	public List<String> getListModel() {
		return listModel;
	}

	
	
	
	public void loadAllTransaction() {
		listModel.clear();
		
		try {
			listModel = serviceDashboard.getAllTransactions();
		
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	
	
	@PostConstruct
    public void init() {
		   
		listModel = serviceDashboard.getAllTransactions();
		
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("advisor")!=null){
		id = ((Advisor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("advisor")).getId();
		System.out.println("------------------------------------------------ "+ id);
        pieModelAdvisor = new PieChartModel();
        pieModelAdvisor.setData(serviceDashboard.getPieMapAdvisor(id));
        pieModelAdvisor.setTitle("Statistiques Conseiller n°" + id);
        pieModelAdvisor.setShowDataLabels(true);
        pieModelAdvisor.setLegendPosition("w");
		}else{
        pieModelDirector = new PieChartModel();
        pieModelDirector.setData(serviceDashboard.getPieMapDirector());
        pieModelDirector.setTitle("Statistiques Directeur");
        pieModelDirector.setShowDataLabels(true);
        pieModelDirector.setLegendPosition("w");
		}
    }

	public PieChartModel getPieModelAdvisor() {
		
		pieModelAdvisor.setData(serviceDashboard.getPieMapAdvisor(id));
		return pieModelAdvisor;
	}

	public void setPieModelAdvisor(PieChartModel pieModelAdvisor) {
		this.pieModelAdvisor = pieModelAdvisor;
	}

	public PieChartModel getPieModelDirector() {
		
		pieModelDirector.setData(serviceDashboard.getPieMapDirector());
		return pieModelDirector;
	}

	public void setPieModelDirector(PieChartModel pieModelDirector) {
		this.pieModelDirector = pieModelDirector;
	}
	

	
	
	
	
	
}
