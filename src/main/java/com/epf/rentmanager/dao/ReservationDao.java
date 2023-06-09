package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {


	private ReservationDao() {}

	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_BY_ID_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";

	/**
	 * Créé un une réservation
	 * @param reservation
	 * @return l'id de la réservation
	 * @throws DaoException en cas d'erreur
	 */
	public long create(Reservation reservation) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,reservation.getClient_id());
			ps.setInt(2,reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			int id=0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;
		}
		catch(SQLException e){
			throw new DaoException();
		}
	}
	/**
	 * Supprime un une réservation
	 * @param idClient
	 * @return 0 ou 1 en fonction de si la suppression a eu lieu
	 * @throws DaoException en cas d'erreur
	 */
	public long delete(int idClient) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			ps.setInt(1, idClient);
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
	 * Trouve toutes les réservations associées à un client
	 * @param clientId
	 * @return la liste de réservations
	 * @throws DaoException en cas d'erreur
	 */
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstatement.setLong(1,clientId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id,(int) clientId,vehicle_id,debut,fin));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}

		return reservations;
	}
	/**
	 * Trouve toutes les réservations associées à un véhicule
	 * @param vehicleId
	 * @return la liste de réservations
	 * @throws DaoException en cas d'erreur
	 */
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstatement.setLong(1,vehicleId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id, client_id,(int) vehicleId,debut,fin));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return reservations;
	}
	/**
	 * Trouve une réservation par son id
	 * @param id
	 * @return la réservation
	 * @throws DaoException en cas d'erreur
	 */
	public Reservation findById(long id) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_ID_QUERY);
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			int client_id = rs.getInt("client_id");
			int vehicle_id = rs.getInt("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			return new Reservation((int) id,client_id,vehicle_id,debut,fin);
		}
		catch(SQLException e){
			throw new DaoException();
		}
	}
	/**
	 * Modifie une réservation
	 * @param reservation
	 * @return
	 * @throws DaoException en cas d'erreur
	 */
	public long edit(Reservation reservation) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement ps =connection.prepareStatement(EDIT_RESERVATION_QUERY);
			ps.setInt(1, reservation.getClient_id());
			ps.setInt(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.setInt(5,reservation.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	/**
	 * Trouve toutes les réservations
	 * @return la liste de réservations
	 * @throws DaoException en cas d'erreur
	 */
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try(Connection connection = ConnectionManager.getConnection()){
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while(rs.next()){
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id,client_id,vehicle_id,debut,fin));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return reservations;
	}
}
