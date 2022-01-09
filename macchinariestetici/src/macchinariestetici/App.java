package macchinariestetici;
import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
			int querySelector;
			
			while(true) {
				System.out.println("\n\nAttendi...");
				TimeUnit.SECONDS.sleep(3);
				//MENU
				System.out.println("\n\n\nApplicazione per la gestione dello sviluppo e manutenzione di macchinari estetici a livello industriale\n");
				System.out.println("\t\t\t\t\t--MENU DI SELEZIONE QUERY--\n\n");
				System.out.println("1 : Registrazione di un cliente;\n");
				System.out.println("2 : Acquisto di un macchinario;\n");
				System.out.println("3 : Consegna di un macchinario ad un corriere;\n");
				System.out.println("4 : Acquisto di un macchinario di tipo accessorio;\n");
				System.out.println("5 : Avvio di un intervento di manutenzione di un macchinario;\n");
				System.out.println("6 : Verifica della possibilità di assegnare un operario ad un intervento di manutenzione; \n");
				System.out.println("7 : Coinvolgimento di un dipendente in un intervento;\n");
				System.out.println("8 : Visualizzazione per ogni dipendente del numero di macchinari che ha riparato;\n");
				System.out.println("9 : Visualizzazione dei codici dei macchinari affidati ad un corriere o per i quali è in atto un\n"
									+ "    intervento di manutenzione con stato 'in lavorazione';\n");
				System.out.println("10 : Visualizzazione dei macchinari per i quali non è stato mai richiesto un intervento di\n"
									+ "    manutenzione;\n");
				System.out.println("11 : Stampa dei dati degli ingegneri, compreso il numero di richieste di sostituzione che ha definito;\n");
				System.out.println("12 : Stampa dei dati degli ingegneri che non hanno mai progettato macchinari di tipo accessorio;\n");
				System.out.println("13 : Stampa di un report che mostri i dati delle categorie di tipo accessorio, inclusa la quantità totale\n"
									+ "    di macchinari di tipo \"base\" a cui sono stati associati;\n");
				System.out.println("14 : Stampa di un report che mostri i dati degli operari compreso il numero totale di ore dedicate\n"
									+ "    agli interventi di manutenzione;\n");
				System.out.println("15 : Stampa di un report che mostri i dati dei clienti, incluso il numero totale di prodotti acquistati;\n");
				System.out.println("\n\nDIGITARE INTERO CORRISPONDENTE ALLA QUERY CHE SI VUOLE ESEGUIRE:");
				System.out.println("[0 PER USCIRE]\n");
				querySelector = in.nextInt();
				in.nextLine();
				
				
				switch (querySelector) {

				case 0:
					System.out.println("\t\t\t\t\t--TERMINATO--");
					System.exit(0);

					//Query #1 : Registrazione di un cliente
				case 1:
					try {
						System.out.println("Inserire nome cliente: ");
						String Nome = in.nextLine();

						System.out.println("Inserire cognome cliente: ");
						String Cognome = in.nextLine();

						System.out.println("Inserire codice fiscale cliente: ");
						String CF = in.nextLine();

						System.out.println("Inserire un recapito telefonico: ");
						String tel = in.nextLine();

						System.out.println("Inserire indirizzo email: ");
						String Email = in.nextLine();

						System.out.println("Inserire numero acquisti effettuati: ");
						int NumAcquisti = in.nextInt();

						Statement query = con.createStatement();
						query.execute("INSERT INTO cliente(nome,cognome,cf,numAcquisti,telefono,email) VALUES('"+Nome+"','"+Cognome+"','"+CF+"','"+NumAcquisti+"','"+tel+"','"+Email+"')");

						System.out.println("Query #1 eseguita con successo");

						ResultSet result = query.executeQuery("SELECT * FROM cliente WHERE cf = '"+CF+"'");

						System.out.println("\nNome\tCognome\tCodice Fiscale\t    N.Acquisti\tN.Telefono\tEmail");
						while(result.next()) {
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String cf = result.getString("cf");
							int numAcquisti = result.getInt("numAcquisti");
							String telefono = result.getString("telefono");
							String email = result.getString("email");

							char[] trimmed = Arrays.copyOf(nome.toCharArray(), 7);
							String nomeTrimmed = new String(trimmed);
							char[] trimmed2 = Arrays.copyOf(cognome.toCharArray(), 7);
							String cognomeTrimmed = new String(trimmed2);

							System.out.println("\n"+nomeTrimmed+"\t"+cognomeTrimmed+"\t"+cf+"\t"+numAcquisti+"\t"+telefono+"\t"+email);
						}
					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #1!");
					}

					break;

					//Query #2 : Acquisto di un macchinario
				case 2:
					try {
						System.out.println("Inserire codice fiscale acquirente: ");
						String CF = in.nextLine();

						System.out.println("Inserire numero seriale del macchinario acquistato: ");
						int serialNum = in.nextInt();

						Statement query = con.createStatement();

						query.executeUpdate("UPDATE macchinario SET cf_cliente = '"+CF+"' "
								+ "WHERE (seriale = '"+serialNum+"' AND "
								+ "(SELECT seriale_macc_prog FROM progetto WHERE seriale_macc_prog = '"+serialNum+"' AND tipo = 'commercializzato'))");

						query.executeUpdate("UPDATE cliente SET numAcquisti = numAcquisti+1 WHERE cf = '"+CF+"'");

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

						result = query.executeQuery("SELECT * FROM cliente WHERE cf = '"+CF+"' ");
						while(result.next()) {
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String cf = result.getString("cf");
							int numAcquisti = result.getInt("numAcquisti");
							String telefono = result.getString("telefono");
							String email = result.getString("email");

							System.out.println("\nNome: "+nome+"\nCognome: "+cognome+"\nCodice Fiscale: "+cf+"\nN.Acquisti: "+numAcquisti+"\nEmail: "+email+"\nN.Telefono: "+telefono+"\n\n");
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
						String CF = in.nextLine();

						System.out.println("Inserire numero seriale del macchinario accessorio acquistato: ");
						int serialNum = in.nextInt();

						Statement query = con.createStatement();
						query.executeUpdate("UPDATE macchinario SET cf_cliente = '"+CF+"' "
								+ "WHERE (seriale = '"+serialNum+"' AND "
								+ "isAccessoria IS NOT NULL AND isBase IS NULL AND "
								+ "(SELECT seriale_macc_prog FROM progetto WHERE seriale_macc_prog = '"+serialNum+"' AND tipo = 'commercializzato'))");
						query.executeUpdate("UPDATE cliente SET numAcquisti = numAcquisti+1 WHERE cf = '"+CF+"'");

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

						result = query.executeQuery("SELECT * FROM cliente WHERE cf = '"+CF+"' ");
						while(result.next()) {
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String cf = result.getString("cf");
							int numAcquisti = result.getInt("numAcquisti");
							String telefono = result.getString("telefono");
							String email = result.getString("email");

							System.out.println("\nNome: "+nome+"\nCognome: "+cognome+"\nCodice Fiscale: "+cf+"\nN.Acquisti: "+numAcquisti+"\nEmail: "+email+"\nN.Telefono: "+telefono+"\n\n");
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #4!");
					}

					break;

					//Query #5 : Avvio di un intervento di manutenzione di un macchinario;
				case 5:
					try {

						System.out.println("Inserire numero seriale del macchinario acquistato: ");
						int serialNum = in.nextInt();

						in.nextLine();

						System.out.println("Inserire stato dell'intervento (Richiesto/Valutato/In lavorazione/Verificato/Completato)"
								); String stato = in.nextLine();

								System.out.println("Inserire data dell'inizio dell'intervento (YYYY-MM-DD)");
								String data = in.nextLine();


								Statement query = con.createStatement();

								query.execute("INSERT INTO intervento (seriale_macchinario, sostituzione, stato, dataArrivo) "
										+ "VALUES ((SELECT seriale "
										+ "FROM macchinario LEFT JOIN affido ON affido.seriale_macch_corr = macchinario.seriale "
										+ "WHERE macchinario.cf_cliente IS NOT NULL AND affido.cf_corr = 'non affidato' AND macchinario.seriale = '"+serialNum+"'), "
										+ "'0', '"+stato+"', '"+data+"')");

								System.out.println("Query #5 eseguita con successo");

								ResultSet result = query.executeQuery("SELECT intervento.progressivo, intervento.seriale_macchinario, intervento.stato, intervento.dataArrivo "
										+ "FROM intervento JOIN (SELECT MAX(progressivo) AS numProgressivo, "
										+ "seriale_macchinario FROM intervento WHERE seriale_macchinario = '"+serialNum+"') AS intervento2 "
										+ "ON intervento.seriale_macchinario = intervento2.seriale_macchinario "
										+ "AND intervento.progressivo = intervento2.numProgressivo");
								while(result.next()) {
									int progressivo = result.getInt("progressivo");
									String seriale_macchinario = result.getString("seriale_macchinario");
									String stato_intervento = result.getString("stato");
									String dataArrivo = result.getString("dataArrivo");

									System.out.println("\nNumero Progressivo: "+progressivo+"\nN.Seriale macchinario: "+seriale_macchinario+"\nStato corrente dell'intervento: "+stato_intervento+"\nData presa in carico: "+dataArrivo+"\n\n");
								}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #5!");
					}

					break;	

					//Query #6 : Verifica della possibilità di assegnare un operario ad un intervento di manutenzione
				case 6:
					try {
						System.out.println("Inserire matricola del dipendende di cui si vuole verificare la disponibilità: ");
						String badgeNum = in.nextLine();

						Statement query = con.createStatement();


						ResultSet result = query.executeQuery("SELECT matricola FROM dipendente "
								+ "WHERE matricola IN (SELECT matricola_dip_coinv FROM coinvolgimento WHERE (matricola_dip_coinv = '"+badgeNum+"' "
								+ "AND dataFine IS NOT NULL) "
								+ "GROUP BY matricola_dip_coinv HAVING COUNT(matricola_dip_coinv)<=3) "
								+ "GROUP BY matricola");

						System.out.println("Query #6 eseguita con successo");

						if(result.next()) {
							System.out.println("\nIl dipendente con matricola: "+badgeNum+", può essere coinvolto in altri interventi\n");
						}else
							System.out.println("\nIl dipendente con matricola: "+badgeNum+", NON può essere coinvolto in altri interventi\n");


					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #6!");
					}

					break;

					//Query #7 : Coinvolgimento di un dipendente in un intervento
				case 7:
					try {

						System.out.println("Inserire matricola del dipendente da coinvolgere: ");
						String badgeNum = in.nextLine();

						System.out.println("Inserire numero seriale del macchinario su cui opera l'intervento: ");
						int serialNum = in.nextInt();

						in.nextLine();

						Statement query = con.createStatement();

						int progressiveNum = 0;
						String data = null;
						boolean valid = false;

						while(!valid) {

							System.out.println("Inserire numero progressivo dell'intervento: ");
							progressiveNum = in.nextInt();

							in.nextLine();

							ResultSet result = query.executeQuery("SELECT * FROM intervento "
									+ "WHERE progressivo = '"+progressiveNum+"' AND dataFine IS NULL AND seriale_macchinario = '"+serialNum+"'");

							if(!result.next()) {
								System.out.println("\nIl numero progressivo inserito non e' valido per via del verificarsi di una di queste possibilità:\n"
										+ "\t>Non esiste un intervento identificato dal numero progressivo inserito;\n"
										+ "\t>Il numero progressivo inserito identifica un intervento già concluso;\n"
										+ "\t>L'intervento identificato dal seguente numero progressivo non si riferisce al numero seriale del macchinario inserito.");
								System.out.println("\nInserisci un numero progressivo valido.\n");
								continue;
							}else valid = true;

							System.out.println("Inserire data dell'assegnazione del dipendente all'intervento: (YYYY-MM-DD)");
							data = in.nextLine();

							result = query.executeQuery("SELECT * FROM intervento "
									+ "WHERE progressivo = '"+progressiveNum+"' AND dataArrivo <= '"+data+"'");

							if(!result.next()) {
								System.out.println("\nL'inizio del coivolgimento di un dipendente non può essere precedente alla data di inizio di un intervento!");
								System.out.println("\nInserisci una data valida.\n");
								valid = false;
							}else valid = true;
						}

						query.execute("INSERT INTO coinvolgimento(matricola_dip_coinv, num_intervento, seriale_macc_coinv, dataInizio) "
								+ "VALUES((SELECT * FROM (SELECT matricola FROM dipendente WHERE matricola IN "
								+ "(SELECT matricola_dip_coinv FROM coinvolgimento WHERE (matricola_dip_coinv = '"+badgeNum+"' AND dataFine IS NOT NULL) "
								+ "GROUP BY matricola_dip_coinv HAVING COUNT(matricola_dip_coinv)<=3) GROUP BY matricola) AS badgeNum), '"+progressiveNum+"', '"+serialNum+"', '"+data+"')");

						System.out.println("Query #7 eseguita con successo");

						ResultSet result = query.executeQuery("SELECT * FROM coinvolgimento WHERE num_intervento = '"+progressiveNum+"' AND seriale_macc_coinv = '"+serialNum+"'");
						while(result.next()) {
							String matricola_dip_coinv = result.getString("matricola_dip_coinv");
							int num_intervento = result.getInt("num_intervento");
							int seriale_macc_coinv = result.getInt("seriale_macc_coinv");
							String dataInizio = result.getDate("dataInizio").toString();

							System.out.println("\nMatricola Dipendente coinvolto: "+matricola_dip_coinv+"\nNumero progressivo intervento: "+num_intervento+"\nN.Seriale macchinario: "+seriale_macc_coinv+"\nData d'inizio coinvolgimento: "+dataInizio+"\n\n");
						}


					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #7!");
					}

					break;

					//Query #8 : Visualizzazione per ogni dipendente del numero di macchinari che ha riparato
				case 8:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT matricola_dip_coinv, COUNT(matricola_dip_coinv) "
								+ "FROM coinvolgimento JOIN (SELECT progressivo, seriale_macchinario FROM intervento "
								+ "WHERE dataFine IS NOT NULL) AS riparazioni "
								+ "ON riparazioni.progressivo = coinvolgimento.num_intervento AND riparazioni.seriale_macchinario = coinvolgimento.seriale_macc_coinv "
								+ "GROUP BY matricola_dip_coinv");

						System.out.println("Query #8 eseguita con successo");										

						System.out.println("Matricola dipendente\tRiparazioni effettuate");
						while(result.next()) {
							String matricola_dip_coinv = result.getString("matricola_dip_coinv");
							int numRiparazioni = result.getInt("COUNT(matricola_dip_coinv)");

							System.out.println("\n\t"+matricola_dip_coinv+"\t\t\t  "+numRiparazioni);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #8!");
					}

					break;	

					/*Query #9 : 
				Visualizzazione dei codici dei macchinari affidati ad un corriere o per i quali è in atto un
				intervento di manutenzione con stato "in lavorazione" */
				case 9:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT seriale_macch_corr FROM affido WHERE cf_corr != 'non affidato' "
								+ "UNION SELECT seriale_macchinario FROM intervento "
								+ "WHERE stato = 'In lavorazione' ORDER BY seriale_macch_corr ASC");

						System.out.println("Query #9 eseguita con successo");										

						System.out.println("N.Seriale Macchinario:");
						while(result.next()) {
							int seriale_macch_corr = result.getInt("seriale_macch_corr");

							System.out.println("\n"+seriale_macch_corr);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #9!");
					}

					break;	

					/*Query #10 : 
				Visualizzazione dei macchinari per i quali non è stato mai richiesto un intervento di
				manutenzione */

				case 10:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT * FROM macchinario WHERE problematiche = 'nessuna'");

						System.out.println("Query #10 eseguita con successo");										
						System.out.println("N.Seriale\tNome\t\tPrezzo\tN.Accessori\tLotto\tValutazione\tAcquirente");

						while(result.next()) {
							int seriale = result.getInt("seriale");
							float prezzo = result.getFloat("prezzo");
							int numAccessori = result.getInt("numAccessori");
							int lotto = result.getInt("lotto");
							int valutazione = result.getInt("valutazione");
							String cf_cliente = result.getString("cf_cliente");
							String isBase = result.getString("isBase");
							String isAccessoria = result.getString("isAccessoria");

							String nomeCategoria = isBase != null ? isBase : isAccessoria;
							char[] trimmed = Arrays.copyOf(nomeCategoria.toCharArray(), 11);
							String nomeCategoriaTrimmed = new String(trimmed);

							System.out.println("\n"+seriale+"\t\t"+nomeCategoriaTrimmed+"\t€"+prezzo+"\t    "+numAccessori+"\t\t"+lotto+"\t  "+valutazione+"/100\t"+cf_cliente);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #10!");
					}

					break;

					//Query #11 : Stampa dei dati degli ingegneri, compreso il numero di richieste di sostituzione che ha definito
				case 11:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT matricola, tipoContratto, alboProfessionale, specializzazione, cf, nome, cognome, email, telefono, COUNT(matricola_dip_coinv) "
								+ "FROM dipendente, schedadipendente, "
								+ "(SELECT matricola_dip_coinv FROM intervento JOIN coinvolgimento ON progressivo = num_intervento WHERE sostituzione = '1') AS sostituizioni "
								+ "WHERE matricola = matricola_dip AND matricola like '1%' AND matricola = sostituizioni.matricola_dip_coinv");

						System.out.println("Query #11 eseguita con successo");										

						System.out.println("\nMatricola\tNome\tCognome\tCodice Fiscale\t   Tipo di Contratto\tAlbo Professionale\t\t\tSpecializzazione\tN.Telefono\tEmail\t\t\tSostituzioni definite");
						while(result.next()) {
							String matricola = result.getString("matricola");
							String tipoContratto = result.getString("tipoContratto");
							String alboProfessionale = result.getString("alboProfessionale");
							String specializzazione = result.getString("specializzazione");
							String cf = result.getString("cf");
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String email = result.getString("email");
							String telefono = result.getString("telefono");
							String sostituzioni = result.getString("COUNT(matricola_dip_coinv)");

							char[] trimmed = Arrays.copyOf(nome.toCharArray(), 7);
							String nomeTrimmed = new String(trimmed);
							char[] trimmed2 = Arrays.copyOf(cognome.toCharArray(), 7);
							String cognomeTrimmed = new String(trimmed2);
							char[] trimmed3 = Arrays.copyOf(tipoContratto.toCharArray(), 9);
							String contractTrimmed = new String(trimmed3);

							System.out.println("\n"+matricola+"\t\t"+nomeTrimmed+"\t"+cognomeTrimmed+"\t"+cf+"   "+contractTrimmed+"\t\t"+alboProfessionale+"\t"+specializzazione+"\t\t"+telefono+"\t"+email+"\t\t  "+sostituzioni);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #11!");
					}

					break;

					//Query #12 : Stampa dei dati degli ingegneri che non hanno mai progettato macchinari di tipo accessorio
				case 12:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT matricola, tipoContratto, alboProfessionale, specializzazione, cf, nome, cognome, email, telefono "
								+ "FROM dipendente, schedadipendente WHERE matricola = matricola_dip AND matricola like '1%' AND matricola NOT IN "
								+ "(SELECT matricola_dip_part FROM partecipazione JOIN progetto ON id_prog = ID  "
								+ "JOIN macchinario ON seriale_macc_prog = seriale WHERE isAccessoria IS NOT NULL)");

						System.out.println("Query #12 eseguita con successo");										

						System.out.println("\nMatricola\tNome\tCognome\tCodice Fiscale\t   Tipo di Contratto\tAlbo Professionale\t\t\tSpecializzazione\tN.Telefono\tEmail");
						while(result.next()) {
							String matricola = result.getString("matricola");
							String tipoContratto = result.getString("tipoContratto");
							String alboProfessionale = result.getString("alboProfessionale");
							String specializzazione = result.getString("specializzazione");
							String cf = result.getString("cf");
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String email = result.getString("email");
							String telefono = result.getString("telefono");

							char[] trimmed = Arrays.copyOf(nome.toCharArray(), 7);
							String nomeTrimmed = new String(trimmed);
							char[] trimmed2 = Arrays.copyOf(cognome.toCharArray(), 7);
							String cognomeTrimmed = new String(trimmed2);
							char[] trimmed3 = Arrays.copyOf(tipoContratto.toCharArray(), 9);
							String contractTrimmed = new String(trimmed3);

							System.out.println("\n"+matricola+"\t\t"+nomeTrimmed+"\t"+cognomeTrimmed+"\t"+cf+"   "+contractTrimmed+"\t\t"+alboProfessionale+"\t"+specializzazione+"\t\t"+telefono+"\t"+email);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #12!");
					}

					break;

					/*Query #13 : 
				Stampa di un report che mostri i dati delle categorie di tipo accessorio, inclusa la quantità totale
				di macchinari di tipo "base" a cui sono stati associati */
				case 13:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT nomeCat, destinazioneUso, applicabilitàMul, COUNT(nomeCat) "
								+ "FROM accessoria JOIN associazione ON accessoria.nomeCat = associazione.nomeCat_acc GROUP BY nomeCat");

						System.out.println("Query #13 eseguita con successo");										

						System.out.println("Nome Accessorio\tDestinazioneD'Uso  Applicabilità Multipla  N.Macchinari base a cui sono associati");
						while(result.next()) {
							String nomeCat = result.getString("nomeCat");
							String destinazioneUso = result.getString("destinazioneUso");
							int applicabilitàMul = result.getInt("applicabilitàMul");
							int attachedTo = result.getInt("COUNT(nomeCat)");

							char[] trimmed = Arrays.copyOf(nomeCat.toCharArray(), 11);
							String nomeTrimmed = new String(trimmed);

							System.out.println("\n"+nomeTrimmed+"\t "+destinazioneUso+"\t\t    "+applicabilitàMul+"\t\t\t    "+attachedTo);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #13!");
					}

					break;

					/*Query #14 : 
				Stampa di un report che mostri i dati degli operari compreso il numero totale di ore dedicate
				agli interventi di manutenzione */
				case 14:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT matricola, tipoContratto, oreManutenzione, cf, nome, cognome, email, telefono FROM dipendente, schedadipendente "
								+ "WHERE matricola = matricola_dip AND matricola like '0%'");

						System.out.println("Query #14 eseguita con successo");										

						System.out.println("\nMatricola\tNome\tCognome\tCodice Fiscale\t   Tipo di Contratto\tOre Manutenzione\tN.Telefono\tEmail");
						while(result.next()) {
							String matricola = result.getString("matricola");
							String tipoContratto = result.getString("tipoContratto");
							int oreManutenzione = result.getInt("oreManutenzione");
							String cf = result.getString("cf");
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String email = result.getString("email");
							String telefono = result.getString("telefono");

							char[] trimmed = Arrays.copyOf(nome.toCharArray(), 7);
							String nomeTrimmed = new String(trimmed);
							char[] trimmed2 = Arrays.copyOf(cognome.toCharArray(), 7);
							String cognomeTrimmed = new String(trimmed2);
							char[] trimmed3 = Arrays.copyOf(tipoContratto.toCharArray(), 9);
							String contractTrimmed = new String(trimmed3);

							System.out.println("\n"+matricola+"\t\t"+nomeTrimmed+"\t"+cognomeTrimmed+"\t"+cf+"   "+contractTrimmed+"\t\t\t"+oreManutenzione+"\t\t"+telefono+"\t"+email);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #14!");
					}

					break;

					//Query #15 : Stampa di un report che mostri i dati dei clienti, incluso il numero totale di prodotti acquistati
				case 15:
					try {

						Statement query = con.createStatement();

						ResultSet result = query.executeQuery("SELECT * FROM cliente");

						System.out.println("Query #15 eseguita con successo");										

						System.out.println("\nNome\tCognome\tCodice Fiscale\t    N.Acquisti\tN.Telefono\tEmail");
						while(result.next()) {
							String nome = result.getString("nome");
							String cognome = result.getString("cognome");
							String cf = result.getString("cf");
							int numAcquisti = result.getInt("numAcquisti");
							String telefono = result.getString("telefono");
							String email = result.getString("email");

							char[] trimmed = Arrays.copyOf(nome.toCharArray(), 7);
							String nomeTrimmed = new String(trimmed);
							char[] trimmed2 = Arrays.copyOf(cognome.toCharArray(), 7);
							String cognomeTrimmed = new String(trimmed2);

							System.out.println("\n"+nomeTrimmed+"\t"+cognomeTrimmed+"\t"+cf+"\t"+numAcquisti+"\t"+telefono+"\t"+email);
						}

					} catch (Exception e) {
						System.out.println("Errore nell'interrogazione della Query #15!");
					}

					break;		

				default:
					System.out.println("\n\nNessuna Query corrispondente, si prega di scegliere tra quelle mostrate a schermo\n\n");
					break;
				}
		}
			
		}catch(Exception e){System.out.println("Connessione fallita!!!");}
		
	}

}
