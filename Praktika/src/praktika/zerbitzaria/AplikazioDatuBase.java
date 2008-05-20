package praktika.zerbitzaria;

import java.sql.*;
import java.util.HashMap;

/**
 * Arkitekturaren hirugarren mailaren klase nagusia Singleton diseinu-patroia
 * erabiltzen da instantzia bakarra izateko.
 */

public class AplikazioDatuBase {

	private static final String URL = "jdbc:odbc:bidaiak";
	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String USER = "";
	private static String PASS = "";

	private static AplikazioDatuBase instantzia;

	private Connection konexioa;

	/**
	 * Datu basea hasieratzen
	 */
	public AplikazioDatuBase() {
		try {
			Class.forName(JDBC_DRIVER);
			konexioa = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println("SQL Exception:  "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (java.lang.Exception anException) {
			anException.printStackTrace();
		}
	}

	/**
	 * Datu base itxi
	 */
	public void finalize() {
		try {
			konexioa.close();
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println("SQL Exception:  "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (Exception anException) {
			anException.printStackTrace();
		}
	}

	/**
	 * Eskuratu azken erreserbaren zenbakia eragiketak egiteko.
	 */

	public int getAzkenErreserbarenZenbakia() {
		// Erazagupenak
		int erreserbarenZenbakia = 0;
		PreparedStatement selectSententzia;
		ResultSet resultSet;
		// Sarrera
		try {
			// Select sententzia sortu
			selectSententzia = konexioa
					.prepareStatement("SELECT MAX (Erreserba_Zenbakia) FROM Erreserba");
			// Select sententzia exekutatu
			resultSet = selectSententzia.executeQuery();

			// ResulSet osagaia aztertzen
			if (resultSet.next()) {
				erreserbarenZenbakia = resultSet.getInt(1);
			}
			System.out.println("Select Max erreserbarenZenbakia = "
					+ erreserbarenZenbakia);
			resultSet.close();
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println("SQL Exception:  "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (java.lang.Exception anException) {
			System.out.println("Exception:  " + anException.getMessage());
		}
		// Irteera
		finally {
			return erreserbarenZenbakia;
		}
	}

	/**
	 * Erreserba txertatzen datu basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 */
	public int sartuErreserba(int erreserbarenZenbakia, java.util.Date data,
			int pertsonaKopurua, String agentearenKodea) {
		// Erazagupena
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
		try {
			// Insert sententzia sortu
			insertSententzia = konexioa
					.prepareStatement("INSERT INTO Erreserba VALUES (?, ?, ?, ?)");
			// Insert sententzia hasieratu
			insertSententzia.setInt(1, erreserbarenZenbakia);
			insertSententzia.setTimestamp(2, new java.sql.Timestamp(data
					.getTime()));
			insertSententzia.setInt(3, pertsonaKopurua);
			insertSententzia.setString(4, agentearenKodea);
			// Insert sententzia exekutatu
			count = insertSententzia.executeUpdate();
			// Insert sententzia bukatu
			insertSententzia.close();
			System.out.println("Insert Erreserba count : " + count);
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println(" SQL Exception : "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (java.lang.Exception anException) {
			anException.printStackTrace();
		}
		// Irteera
		finally {
			return count;
		}
	}

	/**
	 * Erreserbaren ezugarriak txertatzen datu-basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 */
	public int sartutErreserbaIten(int erreserbarenZenbakia,
			int baieztapenZenbakia, double prezioa, String irteerarenKodea,
			java.util.Date data) {
		// Erazagupenak
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
		try {
			// Insert sententzia sortu
			insertSententzia = konexioa
					.prepareStatement("INSERT INTO Erreserba_Iten VALUES (?, ?, ?, ?, ?)");
			// Insert sententzia hasieratu
			insertSententzia.setInt(1, erreserbarenZenbakia);
			insertSententzia.setInt(2, baieztapenZenbakia);
			insertSententzia.setDouble(3, prezioa);
			insertSententzia.setString(4, irteerarenKodea);
			insertSententzia.setDate(5, new java.sql.Date(data.getTime()));
			// Insert sententzia exekutatu
			count = insertSententzia.executeUpdate();
			// Insert sententzia amaitu
			insertSententzia.close();
			System.out.println("Insert Iten count : " + count);
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println(" SQL Exception : "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (java.lang.Exception anException) {
			anException.printStackTrace();
		}
		// Irteera
		finally {
			return count;
		}
	}

	/**
	 * Turistaren datuak txertatzen datu-basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 */
	public int sartuTurista(String izena, String helbidea, String telefonoa,
			int erreserbarenZenbakia) {
		// Erazagupenak
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
		try {
			// Insert sententzia sortu
			insertSententzia = konexioa
					.prepareStatement("INSERT INTO Turista VALUES (?, ?, ?, ?)");
			// Hasieratu Insert statement
			insertSententzia.setString(1, izena);
			insertSententzia.setString(2, helbidea);
			insertSententzia.setString(3, telefonoa);
			insertSententzia.setInt(4, erreserbarenZenbakia);
			// Insert sententzia exekutatu
			count = insertSententzia.executeUpdate();
			// Insert sententzia amaitu
			insertSententzia.close();
			System.out.println("Insert Turista count : " + count);
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println(" SQL Exception : "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
		} catch (java.lang.Exception anException) {
			anException.printStackTrace();
		}
		// Irteera
		finally {
			return count;
		}
	}

	/**
	 * Klasearen instantzia eskuratzeko metodoa
	 * 
	 * @return instance AplikazioDatuBase
	 */
	public static AplikazioDatuBase instance() {
		if (instantzia == null)
			instantzia = new AplikazioDatuBase();
		return instantzia;
	}

	/**
	 * Datuak eskuratzen datu-basetik
	 */

	public HashMap irakurriErreserbarenAgentea() {
		return null;
	}

	public HashMap irakurriIrteerak() {
		return null;
	}
}
