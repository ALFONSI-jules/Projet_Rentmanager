package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;

	public VehicleService (VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}
	/**
	 * Créé un véhicule
	 * @param vehicle
	 * @return un appel de la fonction delete de vehicleDao sur id_Vehicle
	 * @throws ServiceException en cas d'erreur
	 */
	public long create(Vehicle vehicle) throws ServiceException {
		try{
			return this.vehicleDao.create(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

		
	}
	/**
	 * Trouve un véhicule par son id
	 * @param id
	 * @return un appel de la fonction findById de vehicleDao sur id
	 * @throws ServiceException en cas d'erreur ou d'id négatif
	 */
	public Vehicle findById(long id) throws ServiceException {
		if (id<0){
			throw new ServiceException("L'id est inferieur à 0");
		}
		try{
			return this.vehicleDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Trouve tous les véhicules
	 * @return un appel de la fonction findAll de vehicleDao
	 * @throws ServiceException en cas d'erreur
	 */
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		}
		catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Compte le nombre de véhicules
	 * @return un appel de la fonction findAll de vehicleDao auquel on applique size
	 * @throws ServiceException en cas d'erreur
	 */
	public int count() throws ServiceException{
		try {
			return this.vehicleDao.findAll().size();
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Supprime un véhicule
	 * @param id_Vehicle
	 * @return un appel de la fonction delete de vehicleDao sur id_Vehicle
	 * @throws ServiceException en cas d'erreur
	 */
	public long delete(int id_Vehicle) throws ServiceException {
		try{
			return this.vehicleDao.delete(id_Vehicle);
		}catch(DaoException e){
			throw new ServiceException();
		}
	}
	/**
	 * Modifie un véhicule
	 * @param vehicle
	 * @return un appel de la fonction edit de vehicleDao sur vehicle
	 * @throws ServiceException en cas d'erreur
	 */
	public long edit(Vehicle vehicle) throws ServiceException {
		try {
			return vehicleDao.edit(vehicle);
		} catch (DaoException e) {
			throw new ServiceException();
		}

	}
}
