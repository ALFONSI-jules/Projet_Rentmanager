package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	

	private VehicleDao() {}

	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,nb_places FROM Vehicle;";

	/**
	 * Créé un véhicule
	 * @param vehicle
	 * @return l'id du véhicule
	 * @throws DaoException en cas d'erreur
	 */
	public long create(Vehicle vehicle) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			int id=0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;
		}
		catch(SQLException e){
			throw 	new DaoException();
		}
	}
	/**
	 * Supprime un véhicule
	 * @param id_Vehicle
	 * @return 1 ou 0 en fonction de si la supression a lieu
	 * @throws DaoException en cas d'erreur
	 */

	public long delete(int id_Vehicle) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setInt(1, id_Vehicle);
			if(ps.executeUpdate()!=0){
				return 1;
			}
			else{
				return 0;
			}
		}
		catch(SQLException e){
			throw new DaoException();
		}
	}

	/**
	 * Modifie un véhicule
	 * @param vehicle
	 * @return
	 * @throws DaoException en cas d'erreur
	 */

	public long edit(Vehicle vehicle) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement ps =connection.prepareStatement(EDIT_VEHICLE_QUERY);
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
			ps.setInt(3, vehicle.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}

	}
	/**
	 * Trouve un véhicule par son Id
	 * @param id
	 * @return le véhicule
	 * @throws DaoException en cas d'erreur
	 */
	public Vehicle findById(long id) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			String constructeur = rs.getString("constructeur");
			int nbPlaces = rs.getInt("nb_places");
			return new Vehicle((int) id,constructeur,nbPlaces);
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}
	/**
	 * Trouve tous les véhicules
	 * @return la liste de véhicule
	 * @throws DaoException en cas d'erreur
	 */
	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
			while(rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");
				vehicles.add(new Vehicle(id,constructeur,nbPlaces));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return vehicles;
	}
}
