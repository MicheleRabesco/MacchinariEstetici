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
			
			int querySelector = 4;
			
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
				
			//Query #1 : Registrazione di un cliente
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
					
					Statement query = con.createStatement();
					query.execute("INSERT INTO cliente(nome,cognome,cf,numAcquisti,telefono,email) VALUES('"+nome+"','"+cognome+"','"+cf+"','"+numAcquisti+"','"+tel+"','"+email+"')");
					
					System.out.println("Query #1 eseguita con successo");
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #1!");
				}
				
				break;
				
			//Query #2 : Acquisto di un macchinario
			case 2:
				try {
					System.out.println("Inserire codice fiscale acquirente: ");
					String cf = in.nextLine();
					
					System.out.println("Inserire numero seriale del macchinario acquistato: ");
					int serialNum = in.nextInt();
					
					Statement query = con.createStatement();
					
					  query.executeUpdate("UPDATE macchinario SET cf_cliente = '"+cf+"' "
					  				+ "WHERE (seriale = '"+serialNum+"' AND "
					  				+ "(SELECT seriale_macc_prog FROM progetto WHERE seriale_macc_prog = '"+serialNum+"' AND tipo = 'commercializzato'))");
					  
					  query.executeUpdate("UPDATE cliente SET numAcquisti = numAcquisti+1 WHERE cf = '"+cf+"'");
					  
					  System.out.println("Query #2 eseguita con successo");
					 
					
					ResultSet result = query.executeQuery("SELECT * FROM macchinario WHERE seriale = '"+serialNum+"' ");
						while(result.next()) {
							
							int seriale = result.getInt("seriale");
							float prezzo = result.getFloat("prezzo");
							int numAccessori = result.getInt("numAccessori");
							int lotto = result.getInt("lotto");
							int valutazione = result.getInt("valutazione");
							String problematiche = result.getString("problematiche");
							String descrizione = result.getString("descrizione");
							String cf_cliente = result.getString("cf_cliente");
							String isBase = result.getString("isBase");
							String isAccessoria = result.getString("isAccessoria");
							
							String nomeCategoria = isBase != null ? isBase : isAccessoria;
							
							System.out.println("\nCodice Seriale: "+seriale+"\nNome: "+nomeCategoria+"\nDescrizione: "+descrizione+"\nPrezzo: €"+prezzo+"\nNumero Accessori: "+numAccessori+"\nN.Lotto: "+lotto+"\nValutazione: "+valutazione+"/100\nProblematiche riscontrate: "+problematiche+"\nAcquirente: "+cf_cliente+"\n\n");
						}
					
					result = query.executeQuery("SELECT * FROM cliente WHERE cf = '"+cf+"' ");
						while(result.next()) {
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String CF = result.getString("cf");
							int numAcquisti = result.getInt("numAcquisti");
							String telefono = result.getString("telefono");
							String email = result.getString("email");
							
							System.out.println("\nNome: "+nome+"\nCognome: "+cognome+"\nCodice Fiscale: "+CF+"\nN.Acquisti: "+numAcquisti+"\nEmail: "+email+"\nN.Telefono: "+telefono+"\n\n");
						}
					
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #2!");
				}
				
				break;
				
			//Query #3 : Consegna di un macchinario ad un corriere
			case 3:
				try {
					System.out.println("Inserire codice fiscale del corriere designato: ");
					String CF = in.nextLine();
					
					System.out.println("Inserire numero seriale del macchinario da consegnare: ");
					int serialNum = in.nextInt();
					
					
					Statement query = con.createStatement();
					
					  query.execute("INSERT INTO affido (cf_corr,seriale_macch_corr) " +
					  "SELECT cf, seriale FROM macchinario LEFT JOIN corriere ON corriere.cf = '"
					  +CF+"' " + "WHERE cf_cliente IS NOT NULL AND seriale = '"+serialNum+"' ");
					  
					  System.out.println("Query #3 eseguita con successo");
					 
					
					ResultSet result = query.executeQuery("SELECT * FROM affido WHERE seriale_macch_corr = '"+serialNum+"' AND cf_corr = '"+CF+"' ");
															
						while(result.next()) {
							int seriale_macch_corr = result.getInt("seriale_macch_corr");
							String cf_corr = result.getString("cf_corr");
							
							System.out.println("\nN.Seriale Macchinario: "+seriale_macch_corr+"\nCodice Fiscale corriere: "+cf_corr+"\n\n");
						}
					
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #3!");
				}
				
				break;
			
			//Query #4 : Acquisto di un macchinario di tipo accessorio
			case 4:
				try {
					System.out.println("Inserire codice fiscale acquirente: ");
					String cf = in.nextLine();
					
					System.out.println("Inserire numero seriale del macchinario accessorio acquistato: ");
					int serialNum = in.nextInt();
					
					Statement query = con.createStatement();
					 query.executeUpdate("UPDATE macchinario SET cf_cliente = '"+cf+"' "
				  				+ "WHERE (seriale = '"+serialNum+"' AND "
				  				+ "isAccessoria IS NOT NULL AND isBase IS NULL AND "
				  				+ "(SELECT seriale_macc_prog FROM progetto WHERE seriale_macc_prog = '"+serialNum+"' AND tipo = 'commercializzato'))");
					query.executeUpdate("UPDATE cliente SET numAcquisti = numAcquisti+1 WHERE cf = '"+cf+"'");
					
					System.out.println("Query #4 eseguita con successo");
					
					ResultSet result = query.executeQuery("SELECT * FROM macchinario WHERE seriale = '"+serialNum+"' ");
					while(result.next()) {
						
						int seriale = result.getInt("seriale");
						float prezzo = result.getFloat("prezzo");
						int numAccessori = result.getInt("numAccessori");
						int lotto = result.getInt("lotto");
						int valutazione = result.getInt("valutazione");
						String problematiche = result.getString("problematiche");
						String descrizione = result.getString("descrizione");
						String cf_cliente = result.getString("cf_cliente");
						String isBase = result.getString("isBase");
						String isAccessoria = result.getString("isAccessoria");
						
						System.out.println("\nCodice Seriale: "+seriale+"\nNome: "+isAccessoria+"\nDescrizione: "+descrizione+"\nPrezzo: €"+prezzo+"\nN.Lotto: "+lotto+"\nValutazione: "+valutazione+"/100\nProblematiche riscontrate: "+problematiche+"\nAcquirente: "+cf_cliente+"\n\n");
					}
				
				result = query.executeQuery("SELECT * FROM cliente WHERE cf = '"+cf+"' ");
					while(result.next()) {
						String nome = result.getString("nome");
						String cognome = result.getString("cognome");
						String CF = result.getString("cf");
						int numAcquisti = result.getInt("numAcquisti");
						String telefono = result.getString("telefono");
						String email = result.getString("email");
						
						System.out.println("\nNome: "+nome+"\nCognome: "+cognome+"\nCodice Fiscale: "+CF+"\nN.Acquisti: "+numAcquisti+"\nEmail: "+email+"\nN.Telefono: "+telefono+"\n\n");
					}
					
				} catch (Exception e) {
					System.out.println("Errore nell'interrogazione della Query #4!");
				}
				
				break;
				
			default:
				break;
			}
			
			
			
			
			
		}catch(Exception e){System.out.println("Connessione fallita!!!");}
		
		
		
	}

}
