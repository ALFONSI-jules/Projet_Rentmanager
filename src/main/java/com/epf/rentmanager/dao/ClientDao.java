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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {
	

	private ClientDao() {
	}

	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	/**
	 * Créé un client
	 * @param client
	 * @return l'id du client
	 * @throws DaoException en cas d'erreur
	 */
	public long create(Client client) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
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
	 * Supprime un client
	 * @param Id_client
	 * @return 0 ou 1 en fonction de si la suppression a eu lieu
	 * @throws DaoException en cas d'erreur
	 */
	public long delete(int Id_client) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setInt(1, Id_client);
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
	 * Modifie un client
	 * @param client
	 * @return
	 * @throws DaoException en cas d'erreur
	 */
	public long edit(Client client) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement ps =connection.prepareStatement(EDIT_CLIENT_QUERY);
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.setInt(5,client.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	/**
	 * Trouve un client par son id
	 * @param id
	 * @return le client
	 * @throws DaoException en cas d'erreur
	 */
	public Client findById(long id) throws DaoException {
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate date = rs.getDate("naissance").toLocalDate();
			return new Client((int) id,nom,prenom,email,date);
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
	}

	/**
	 * Trouve tous les clients
	 * @return la liste de clients
	 * @throws DaoException en cas d'erreur
	 */
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
			while(rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate date = rs.getDate("naissance").toLocalDate();
				clients.add(new Client(id,nom,prenom,email,date));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return clients;
	}

}
