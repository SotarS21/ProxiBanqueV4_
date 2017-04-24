package org.ProxiBanque.service;

import java.util.List;

import org.ProxiBanque.model.User;
/**
 * Interface de service concernant l'utilisateur, elle est appeler dans les
 * controllers addapter ou dans un webService .
 * 
 * 
 * @author kevin, jonas, andy, mathieu
 * @version 1.0
 */
public interface IServiceUser {

	/**
	 * 
	 * @param user l'utilisateur à persiter en base de données
	 */
	public void save(User user);
	/**
	 * 
	 * @param user l'utilisateur à modifier en base de données
	 */
	public void update(User user);
	/**
	 * 
	 * @param id identifaction de l'utilisateur à trouver
	 * @return l'utilisateur
	 */
	public User findOne(Long id);
	/**
	 * 
	 * @return liste de tous les utilisateurs
	 */
	public List<User> findAll();
	/**
	 * 
	 * @param id identification de l'utilisateru à spprimer
	 */
	public void delete(Long id);
	/**
	 * 
	 * @param login identifiant de l'utilisateur
	 * @param password mot de passe de l'utilisateur
	 * @return l'utilisateur par retrouver en base de données grâce à son login et password
	 */
	public User findFirstByLoginAndPasswordAllIgnoreCase(String login, String password);
	/**
	 * 
	 * @param login identifiant de l'utilisateur
	 * @return la liste de tous les utilisateurs ayant pour identifiant : login
	 */
	public List<User> findAllByLoginAllIgnoreCase(String login);
	/**
	 * 
	 * @param Password mot de passe de l'utilisateur
	 * @return la liste de tous les utilisateurs ayant pour mot de passe : password
	 */
	public List<User> findAllByPasswordAllIgnoreCase(String Password);
}
