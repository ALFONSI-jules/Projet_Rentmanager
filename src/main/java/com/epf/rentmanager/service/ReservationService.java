package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao=reservationDao;
    }


    /**
     * Créé une réservation
     * @param reservation
     * @return un appel de la fonction create de reservationDao sur reservation
     * @throws ServiceException en cas d'erreur
     */
    public long create(Reservation reservation) throws ServiceException {
        try{
            return this.reservationDao.create(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Supprime une réservation
     * @param Id_client
     * @return un appel de la fonction delete de reservationDao sur Id_client
     * @throws ServiceException en cas d'erreur
     */
    public long delete(int Id_client) throws ServiceException {
        try{
            return this.reservationDao.delete(Id_client);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Trouve toutes les réservations correspondant à un client
     * @param id
     * @return un appel de la fonction findByClientId de reservationDao sur id
     * @throws ServiceException en cas d'erreur
     */
    public List<Reservation> findByClientId(long id) throws ServiceException {
        if (id<0){
            throw new ServiceException("L'id est inferieur à 0");
        }
        try{
            return this.reservationDao.findResaByClientId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    /**
     * Trouve toutes les réservations correspondant à un véhicule
     * @param id
     * @return un appel de la fonction findByVehicleId de reservationDao sur id
     * @throws ServiceException en cas d'erreur
     */
    public List<Reservation> findByVehicleId(long id) throws ServiceException {
        if (id<0){
            throw new ServiceException("L'id est inferieur à 0");
        }
        try{
            return this.reservationDao.findResaByVehicleId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    /**
     * Trouve toute les réservations
     * @return un appel de la fonction findAll de reservationDao
     * @throws ServiceException en cas d'erreur
     */
    public List<Reservation> findAll() throws ServiceException {
        try{
            return this.reservationDao.findAll();
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Trouve une réservation par son ID
     * @param id
     * @return un appel de la fonction findById de reservationDao sur id
     * @throws ServiceException en cas d'erreur
     */
    public Reservation findById(long id) throws ServiceException {
        if (id<0){
            throw new ServiceException("L'id est inferieur à 0");
        }
        try{
            return this.reservationDao.findById(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Compte le nombre de réservation
     * @return un appel de la fonction findAll de reservationDao auquel on applique size
     * @throws ServiceException en cas d'erreur
     */
    public int count() throws ServiceException{
        try {
            return this.reservationDao.findAll().size();
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Modifie une réservation
     * @param reservation
     * @return un appel de la fonction edit de reservationDao sur reservation
     * @throws ServiceException en cas d'erreur
     */
    public long edit(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.edit(reservation);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
}
