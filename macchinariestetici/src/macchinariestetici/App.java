package macchinariestetici;
import java.sql.*;
import java.util.*;

public class App {

	public static void main(String[] args) {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/progetto"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root"; 
			String pwd = "30061984";
			con = DriverManager.getConnection(url,username,pwd);
			
			System.out.println("Connessione riuscita");
			
			Scanner in = new Scanner(System.in);
			
			int querySelector = 0;
			
			switch (querySelector) {
			case 0:
				try {
					Statement query = con.createStatement();
					ResultSet result = query.executeQuery("SELECT cf FROM cliente");
					
					while(result.next()) {
						String cf = result.getString("cf");
						System.out.println(cf);
					}
				} catch (Exception e) {
					System.out.println("Errore nella Query #0");
				}
				
				break;
			//Query #1
			case 1:
				try {
					System.out.println("Inserire nome cliente: ");
					String nome = in.nextLine();
					
					System.out.println("Inserire cognome cliente: ");
					String cognome = in.nextLine();
					
					System.out.println("Inserire codice fiscale cliente: ");
					String cf = in.nextLine();
					
					System.out.println("Inserire un recapito telefonico: ");
					String tel = in.nextLine();
					
					System.out.println("Inserire indirizzo email: ");
					String email = in.nextLine();
					
					System.out.println("Inserire numero acquisti effettuati: ");
					int numAcquisti = in.nextInt();
					
					Statement query1 = con.createStatement();
					query1.execute("INSERT INTO cliente(nome,cognome,cf,numAcquisti,telefono,email) VALUES('"+nome+"','"+cognome+"','"+cf+"','"+numAcquisti+"','"+tel+"','"+email+"')");
					
					System.out.println("Query #1 eseguita con successo");
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #1!");
				}
				
				break;
				
			//Query #2
			case 2:
				try {
					
					System.out.println("Query #2 eseguita con successo");
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #2!");
				}
				
				break;
				
			default:
				break;
			}
			
			
			
			
			
		}catch(Exception e){System.out.println("Connessione fallita!!!");}
		
		
		
	}

}
