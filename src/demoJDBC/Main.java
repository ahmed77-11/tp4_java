package demoJDBC;
import java.sql.SQLException;
import java.util.List;

import JDBCProject.beans.Client;
import JDBCProject.service.ClientService;

import static JDBCProject.connexion.Connexion.getConn;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
		/*
		 * ClientService cs=new ClientService(); Client c=new Client("dd","dd"); boolean
		 * res=cs.create(c); if(res) { System.out.println("added"); }else {
		 * System.out.println("err"); }
		 */
    	ClientService cs=new ClientService();
		/*
		 * List<Client> l=cs.findAll(); for(Client c:l) { System.out.println(c); }
		 */
    	Client c=cs.findById(3);
    	c.setNom("ssss");
    	System.out.println(c);
		/*
		 * boolean b=cs.delete(c);
		 */    
    	boolean f=cs.update(c);
    	if(f) {
    		System.out.println("updated");
    	}
    	
    }
}

