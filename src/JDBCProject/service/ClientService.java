package JDBCProject.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCProject.beans.Client;
import JDBCProject.connexion.Connexion;
import JDBCProject.dao.IDao;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {
		String sql = "INSERT INTO client(nom, prenom) VALUES (?, ?)";

		try {
		    PreparedStatement pstmt = Connexion.getConn().prepareStatement(sql);
		    
		    // Set the values for the placeholders
		    pstmt.setString(1, o.getNom());
		    pstmt.setString(2, o.getPrenom());
		    
		    // Execute the SQL statement
		    int rowsAffected = pstmt.executeUpdate();
		    
		    // Check if the insertion was successful
		    if (rowsAffected > 0) {
		    	pstmt.close();
		        return true;
		    } else {
		        return false;
		    }
		    
		    // Close the PreparedStatement
		    
		} catch (SQLException e) {
		    // Handle any SQL exceptions
		    e.printStackTrace();
		    return false;
		}
	}

	@Override
	public List<Client> findAll() {
		String req="SELECT * FROM client";
		List<Client> l=new ArrayList<Client>();
		try {
			Statement stmt=Connexion.getConn().createStatement();
			ResultSet rs=stmt.executeQuery(req);
			while(rs.next()) {
				Client client = new Client();
	            // Assuming Client has setters for id, nom, prenom, etc.
	            client.setId(rs.getInt("id"));
	            client.setNom(rs.getString("nom"));
	            client.setPrenom(rs.getString("prenom"));
	            // Set other attributes similarly if available
	            
	            // Add the Client object to the list
	            l.add(client);
				
			}
			rs.close();
	        stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Client findById(int id) {
		String q="Select id,nom,prenom from client where id=?";
		Client c=new Client();
		try {
			PreparedStatement stmt=Connexion.getConn().prepareStatement(q);
			 stmt.setInt(1, id);
			 ResultSet rs=stmt.executeQuery();
			 
			 while(rs.next()) {
				
				 c.setId(rs.getInt("id"));
				 c.setNom(rs.getString("nom"));
				 c.setPrenom(rs.getString("prenom"));
			 }
			 
			 stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}

	@Override
	public boolean delete(Client o) {
	    String q = "DELETE FROM client WHERE id=?";
	    
	    try {
	        // Prepare the statement with the query
	        PreparedStatement stmt = Connexion.getConn().prepareStatement(q);
	        
	        stmt.setInt(1, o.getId());
	        
	        
	        int res = stmt.executeUpdate();
	        
	        stmt.close();
	       
	        return res > 0;
	    } catch (SQLException e) {
	        // Handle any SQL exceptions
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public boolean update(Client o) {
		String q="UPDATE client SET nom=?,prenom=? where id=?";
		try {
			PreparedStatement stmt=Connexion.getConn().prepareStatement(q);
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			stmt.setInt(3, o.getId());
			int res=stmt.executeUpdate();
			stmt.close();
			return res>0;
		}
		catch (SQLException e) {
			 e.printStackTrace();
		        return false;
			}

		
	}

}
