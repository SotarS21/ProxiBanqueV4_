package org.ProxiBanque.dao;

import java.util.List;

import org.ProxiBanque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface permettant la communication avec la base de donnée
 * Permet la sauvegarde des utilisateurs de l'application
 * 
 * @author Kévin, Jonas, Andy, Mathieu
 * @version 1.0
 */
public interface ICRUDUser extends JpaRepository<User, Long> {
	
	/**
	 * @param login
	 * @param password
	 * @return utilisateur
	 */
	public User findFirstByLoginAndPasswordAllIgnoreCase(String login, String password);
	
	/**
	 * @param login
	 * @return liste utilisateurs
	 */
	public List<User> findAllByLoginAllIgnoreCase(String login);
	
	/**
	 * @param Password
	 * @return liste utilisateurs
	 */
	public List<User> findAllByPasswordAllIgnoreCase(String Password);
}
