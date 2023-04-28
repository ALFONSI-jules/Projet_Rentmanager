package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}



	/**
	 * Créé un client
	 * @param client
	 * @return un appel de la fonction create de clientdao sur client
	 * @throws ServiceException en cas d'erreur
	 */
    public long create(Client client) throws ServiceException {
		try{
			return this.clientDao.create(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Supprime un client
	 * @param Id_client
	 * @return un appel de la fonction delete de clientdao sur Id_client
	 * @throws ServiceException en cas d'erreur
	 */
	public long delete(int Id_client) throws ServiceException {
		try{
			return this.clientDao.delete(Id_client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	/**
	 * Trouve un client par son Id
	 * @param id
	 * @return un appel de la fonction findById de clientdao sur id
	 * @throws ServiceException en cas d'id négatif ou d'erreur
	 */
	public Client findById(long id) throws ServiceException {
		if (id<0){
			throw new ServiceException("L'id est inferieur à 0");
		}
		try{
			return this.clientDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Trouve tous les clients
	 * @return un appel de la fonction findAll de clientdao
	 * @throws ServiceException en cas d'erreur
	 */
	public List<Client> findAll() throws ServiceException {
		try{
			return this.clientDao.findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Compte le nombre de clients
	 * @return un appel de la fonction findAll de clientdao auquel on applique size
	 * @throws ServiceException en cas d'erreur
	 */
	public int count() throws ServiceException{
		try {
			return this.clientDao.findAll().size();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Modifie un client
	 * @param client
	 * @return un appel de la fonction edit de clientdao sur client
	 * @throws ServiceException en cas d'erreur
	 */
	public long edit(Client client) throws ServiceException {
		try {
			return clientDao.edit(client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	
}
