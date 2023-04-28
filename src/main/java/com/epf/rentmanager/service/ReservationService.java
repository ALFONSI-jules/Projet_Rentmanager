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



    public long create(Reservation reservation) throws ServiceException {
        try{
            return this.reservationDao.create(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    public long delete(int Id_client) throws ServiceException {
        try{
            return this.reservationDao.delete(Id_client);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
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
    public List<Reservation> findAll() throws ServiceException {
        try{
            return this.reservationDao.findAll();
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
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
    public int count() throws ServiceException{
        try {
            return this.reservationDao.findAll().size();
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    public long edit(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.edit(reservation);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
}
