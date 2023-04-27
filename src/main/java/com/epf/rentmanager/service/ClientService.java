package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
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




    public long create(Client client) throws ServiceException {
		// TODO: créer un client

		try{
			return this.clientDao.create(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public long delete(int Id_client) throws ServiceException {
		// TODO: supprimer un client

		try{
			return this.clientDao.delete(Id_client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

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

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try{
			return this.clientDao.findAll();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public int count() throws ServiceException{
		try {
			return this.clientDao.findAll().size();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public long edit(Client client) throws ServiceException {
		try {
			return clientDao.edit(client);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	
}
