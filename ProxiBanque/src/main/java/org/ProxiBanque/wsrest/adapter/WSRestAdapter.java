package org.ProxiBanque.wsrest.adapter;

import org.ProxiBanque.wsrest.bean.Address;
import org.ProxiBanque.wsrest.bean.Advisor;
import org.ProxiBanque.wsrest.bean.Agence;
import org.ProxiBanque.wsrest.bean.Client;
import org.ProxiBanque.wsrest.bean.CurrentAccount;
import org.ProxiBanque.wsrest.bean.Director;
import org.ProxiBanque.wsrest.bean.SavingAccount;

/**
 * Cette Classe abstraite expose des méthode Construite sur le principe du pattern Adapter,
 * permettant de convertir des objets du model en objets compatibles avec un service WS REST,
 *  et inversement.
 * 
* @author kevin, jonas, andy, mathieu
* @version 1.0
*/
public abstract class WSRestAdapter {

	public static CurrentAccount marshallCurrentAccount(org.ProxiBanque.model.CurrentAccount current) {

		CurrentAccount marshallCurrent = null;
		
		if (current != null) {

			marshallCurrent = new CurrentAccount(current.getDecouvert(), current.getSold());
			marshallCurrent.setType(current.getType());
			marshallCurrent.setAccountNumber(current.getAccountNumber());

		}
		return marshallCurrent;
	}

	public static org.ProxiBanque.model.CurrentAccount unmarshallCurrentAccount(CurrentAccount current) {

		org.ProxiBanque.model.CurrentAccount unmarshallCurrent = null;

		if (current != null) {
		
			unmarshallCurrent = new org.ProxiBanque.model.CurrentAccount(
					current.getDecouvert(), current.getSold());
			unmarshallCurrent.setType(current.getType());
			unmarshallCurrent.setAccountNumber(current.getAccountNumber());
		}
		return unmarshallCurrent;
	}

	public static SavingAccount marshallSavingAccount(org.ProxiBanque.model.SavingAccount saving) {

		SavingAccount marshallSaving = null;
		
		if(saving != null) {
			
			marshallSaving = new SavingAccount(saving.getSold(), saving.getTauxDeRemuneration(),
					saving.getDecouvert());
			marshallSaving.setType(saving.getType());
			marshallSaving.setAccountNumber(saving.getAccountNumber());
		}
		return marshallSaving;
	}

	public static org.ProxiBanque.model.SavingAccount unmarshallSavingAccount(SavingAccount saving) {

		org.ProxiBanque.model.SavingAccount unmarshallSaving = null;
		
		if(saving != null) {
			
			unmarshallSaving = new org.ProxiBanque.model.SavingAccount(saving.getSold(),
					saving.getTauxDeRemuneration(), saving.getDecouvert());
			unmarshallSaving.setType(saving.getType());
			unmarshallSaving.setAccountNumber(saving.getAccountNumber());
		}
		return unmarshallSaving;
	}

	public static Address marshallAddress(org.ProxiBanque.model.Address address) {

		Address marshallAddress = null;
		
		if(address != null) {
			
			marshallAddress = new Address();
			marshallAddress.setNumber(address.getNumber());
			marshallAddress.setTown(address.getTown());
			marshallAddress.setZipCode(address.getZipCode());
		}
		return marshallAddress;
	}

	public static org.ProxiBanque.model.Address unmarshallAddress(Address address) {

		org.ProxiBanque.model.Address unmarshallAddress = null;
		
		if(address != null) {
			
			unmarshallAddress = new org.ProxiBanque.model.Address();
			unmarshallAddress.setNumber(address.getNumber());
			unmarshallAddress.setTown(address.getTown());
			unmarshallAddress.setZipCode(address.getZipCode());
		}
		return unmarshallAddress;
	}

	public static Client marshallClient(org.ProxiBanque.model.Client client) {

		Client marhallClient = null;
		
		if(client !=null) {
			
			marhallClient = new Client(client.getFirstName(), client.getLastName(),
					marshallAddress(client.getAddress()), client.getType(), client.isRitch());
			marhallClient.setCellphone(client.getCellphone());
			marhallClient.setEmail(client.getEmail());
			marhallClient.setCurrentAccount(marshallCurrentAccount(client.getCurrentAccount()));
			marhallClient.setSafeAccount(marshallSavingAccount(client.getSafeAccount()));
			marhallClient.setId(client.getId());
		}
		return marhallClient;
	}

	public static org.ProxiBanque.model.Client unmarshallClient(Client client) {

		org.ProxiBanque.model.Client unmarhallClient = null;
		
		if(client != null) {
			
			unmarhallClient = new org.ProxiBanque.model.Client(client.getFirstName(),
					client.getLastName(), unmarshallAddress(client.getAddress()));
			unmarhallClient.setCellphone(client.getCellphone());
			unmarhallClient.setEmail(client.getEmail());
			unmarhallClient.setRitch(client.isRitch());
			unmarhallClient.setType(client.getType());
			unmarhallClient.setCurrentAccount(unmarshallCurrentAccount(client.getCurrentAccount()));
			unmarhallClient.setSafeAccount(unmarshallSavingAccount(client.getSafeAccount()));
			unmarhallClient.setId(client.getId());
		}
		return unmarhallClient;
	}

	public static Director marshallDirector(org.ProxiBanque.model.Director director) {
		
		
		Director marhallDirector = null;
		
		if(director != null) {
			
			marhallDirector = new Director(director.getFirstName(), director.getLastName(),
					marshallAddress(director.getAddress()));
			marhallDirector.setCellphone(director.getCellphone());
			marhallDirector.setEmail(director.getEmail());
			marhallDirector.setId(director.getId());
		}
		return marhallDirector;
	}

	public static org.ProxiBanque.model.Director unmarshallDirector(Director director) {

		org.ProxiBanque.model.Director unmarhallDirector = null;
		
		if(director != null) {
			
			unmarhallDirector = new org.ProxiBanque.model.Director(director.getFirstName(),
					director.getLastName(), unmarshallAddress(director.getAddress()));
			unmarhallDirector.setCellphone(director.getCellphone());
			unmarhallDirector.setEmail(director.getEmail());
			unmarhallDirector.setId(director.getId());
		}
		return unmarhallDirector;
	}

	public static Advisor marshallAdvisor(org.ProxiBanque.model.Advisor advisor) {

		Advisor marshallAdvisor = null;
		
		if(advisor != null) {
			
			marshallAdvisor = new Advisor(advisor.getFirstName(), advisor.getLastName(),
					marshallAddress(advisor.getAddress()));
			marshallAdvisor.setCellphone(advisor.getCellphone());
			marshallAdvisor.setEmail(advisor.getEmail());
			marshallAdvisor.setId(advisor.getId());
			
			for (org.ProxiBanque.model.Client client : advisor.getClients()) {
				
				if (client != null) {
					
					marshallAdvisor.addClient(marshallClient(client));
				}
			}
		}
		return marshallAdvisor;
	}

	public static org.ProxiBanque.model.Advisor unmarshallAdvisor(Advisor advisor) {

		org.ProxiBanque.model.Advisor unmarshallAdvisor = null;
		
		if(advisor != null) {
			
			unmarshallAdvisor = new org.ProxiBanque.model.Advisor(advisor.getFirstName(),
					advisor.getLastName(), unmarshallAddress(advisor.getAddress()));
			unmarshallAdvisor.setCellphone(advisor.getCellphone());
			unmarshallAdvisor.setEmail(advisor.getEmail());
			unmarshallAdvisor.setId(advisor.getId());

			for (Client client : advisor.getClients()) {
				
				if (client != null) {
					
					unmarshallAdvisor.addClient(unmarshallClient(client));
				}
			}
		}
		return unmarshallAdvisor;
	}

	public static Agence marshallAgence(org.ProxiBanque.model.Agence agence) {

		Agence marshallAgence = null;
		
		if(agence != null) {
			
			marshallAgence = new Agence(agence.getDateCreation());
			marshallAgence.setId(agence.getId());

			for (org.ProxiBanque.model.Advisor advisor : agence.getAdvisors()) {
				
				if(advisor != null) {
					
					Advisor marshallAdvisor = marshallAdvisor(advisor);
					marshallAgence.addAdvisor(marshallAdvisor);
					
					for (org.ProxiBanque.model.Client client : advisor.getClients()) {
						
						if(client != null) {
							
							marshallAdvisor.addClient(marshallClient(client));
						}
					}
				}
			}
		}

		return marshallAgence;
	}

	public static org.ProxiBanque.model.Agence unmarshallAgence(Agence agence) {

		org.ProxiBanque.model.Agence unmarshallAgence = null;
		
		if(agence != null) {
			
			unmarshallAgence = new org.ProxiBanque.model.Agence(agence.getDateCreation());
			unmarshallAgence.setId(agence.getId());
			
			for (Advisor advisor : agence.getAdvisors()) {
				
				if(advisor != null) {
					
					org.ProxiBanque.model.Advisor unmarshallAdvisor = unmarshallAdvisor(advisor);
					unmarshallAgence.addAdvisor(unmarshallAdvisor);
					
					for (Client client : advisor.getClients()) {
						
						if(client != null) {
							
							unmarshallAdvisor.addClient(unmarshallClient(client));
						}
					}
				}
			}
		}
		return unmarshallAgence;
	}
}
