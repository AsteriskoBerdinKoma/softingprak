package praktika.zerbitzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import praktika.partekatuak.Agentea;
import praktika.partekatuak.Erreserba;
import praktika.partekatuak.Irteera;
import praktika.partekatuak.Turista;

/**
 * Arkitekturaren hirugarren mailaren klase nagusia Singleton diseinu-patroia
 * erabiltzen da instantzia bakarra izateko.
 */

class AplikazioDatuBase {

	private static final String URL = "jdbc:odbc:bidaiak";
	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String USER = "";
	private static String PASS = "";

	private static AplikazioDatuBase instantzia;

	private Connection konexioa;
	private boolean connectedToDatabase;

	/**
	 * Datu basea hasieratzen
	 */
	private AplikazioDatuBase() {
		try {
			Class.forName(JDBC_DRIVER);
			konexioa = DriverManager.getConnection(URL, USER, PASS);
			connectedToDatabase = true;
			System.out.println("Datu basearekin konexia ezarrita.");
		} catch (SQLException anException) {
			while (anException != null) {
				System.out.println("SQL Exception:  "
						+ anException.getMessage());
				anException = anException.getNextException();
			}
			connectedToDatabase = false;
		} catch (java.lang.Exception anException) {
			anException.printStackTrace();
			connectedToDatabase = false;
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
	 * Datu base itxi
	 */
	public void finalize() {
		try {
			konexioa.close();
			connectedToDatabase = false;
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
	 * 
	 * @throws SQLException
	 */

	public int getAzkenErreserbarenZenbakia() throws SQLException {
		// Erazagupenak
		int erreserbarenZenbakia = 0;
		PreparedStatement selectSententzia;
		ResultSet resultSet;
		// Sarrera
		// Select sententzia sortu
		selectSententzia = konexioa
				.prepareStatement("SELECT MAX (Erreserba_Zenbakia) FROM Erreserba");
		// Select sententzia exekutatu
		resultSet = selectSententzia.executeQuery();

		// ResulSet osagaia aztertzen
		if (resultSet.next()) {
			erreserbarenZenbakia = resultSet.getInt(1);
		}
		resultSet.close();
		selectSententzia.close();
		return erreserbarenZenbakia;
	}

	/**
	 * Erreserba txertatzen datu basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 * @throws SQLException
	 */
	public int sartuErreserba(Erreserba erreserba) throws SQLException {
		// Erazagupena
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
		// Insert sententzia sortu
		insertSententzia = konexioa
				.prepareStatement("INSERT INTO Erreserba VALUES (?, ?, ?, ?, ?)");
		// Insert sententzia hasieratu
		insertSententzia.setInt(1, erreserba.getErreserbaZenbakia());
		insertSententzia.setTimestamp(2, new java.sql.Timestamp(erreserba
				.getData().getTime().getTime()));
		insertSententzia.setInt(3, erreserba.getPertsonaKopurua());
		insertSententzia.setInt(4, erreserba.getBaieztapenZenbakia());
		insertSententzia.setInt(5, erreserba.getIrteeraKodea());
		// Insert sententzia exekutatu
		count = insertSententzia.executeUpdate();
		// Insert sententzia bukatu
		System.out.println("Insert Erreserba count : " + count);
		insertSententzia.close();
		return count;
	}

	/**
	 * Erreserbaren ezugarriak txertatzen datu-basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 * @throws SQLException
	 */
	public int sartutErreserbaIten(int erreserbarenZenbakia,
			int baieztapenZenbakia, double prezioa, String irteerarenKodea,
			java.util.Date data) throws SQLException {
		// Erazagupenak
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
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
		System.out.println("Insert Iten count : " + count);
		insertSententzia.close();
		return count;
	}

	/**
	 * Turistaren datuak txertatzen datu-basean
	 * 
	 * @return count int
	 * @param data
	 *            java.util.Date
	 * @param amount
	 *            double
	 * @throws SQLException
	 */
	public int sartuTurista(Turista t) throws SQLException {
		// Erazagupenak
		int count = 0;
		PreparedStatement insertSententzia = null;
		// Sarrera
		// Insert sententzia sortu
		insertSententzia = konexioa
				.prepareStatement("INSERT INTO Turista VALUES (?, ?, ?, ?)");
		// Hasieratu Insert statement
		insertSententzia.setString(1, t.getIzena());
		insertSententzia.setString(2, t.getHelbidea());
		insertSententzia.setString(3, t.getTelefonoa());
		insertSententzia.setInt(4, t.getErreserbaZenb());
		// Insert sententzia exekutatu
		count = insertSententzia.executeUpdate();
		// Insert sententzia amaitu
		System.out.println("Insert Turista count : " + count);
		insertSententzia.close();
		return count;
	}

	/**
	 * Datuak eskuratzen datu-basetik
	 * 
	 * @throws SQLException
	 */
	public Agentea irakurriErreserbarenAgentea(int erreserbaZenb)
			throws SQLException {
		String query = "SELECT * FROM (Agentea A INNER JOIN Irteera I ON A.Agente_Kodea = I.Agente_Kodea) INNER JOIN Erreserba E ON I.Irteera_Kodea = E.Irteera_Kodea WHERE E.Erreserba_Zenbakia = ?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setInt(1, erreserbaZenb);
		ResultSet rs = ps.executeQuery();
		Agentea a = new Agentea();
		int aKod;
		String izena;
		if (rs.next()) {
			aKod = rs.getInt("A.Agente_Kodea");
			izena = rs.getString("A.Izena");
			a = new Agentea(aKod, izena);
		}
		rs.close();
		ps.close();
		return a;
	}

	public Vector<Irteera> irakurriIrteerak(String agenteIzena)
			throws SQLException {
		Vector<Irteera> vIrteerak = new Vector<Irteera>();
		String query = "SELECT DISTINCT * FROM Irteera I INNER JOIN Agentea A ON I.Agente_Kodea = A.Agente_Kodea WHERE A.Izena = ?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setString(1, agenteIzena);
		ResultSet rs = ps.executeQuery();
		int irtKod;
		String ezaug;
		int pertsonaKopMax;
		int agenteKod;
		double prezioa;
		Calendar data;
		while (rs.next()) {
			irtKod = rs.getInt("Irteera_Kodea");
			ezaug = rs.getString("Ezaugarriak");
			pertsonaKopMax = rs.getInt("Pertsona_Kop_Max");
			agenteKod = rs.getInt("Agente_Kodea");
			prezioa = rs.getDouble("Prezioa");
			data = new GregorianCalendar();
			data.setTime(new Date(rs.getTimestamp("Data").getTime()));
			vIrteerak.addElement(new Irteera(irtKod, ezaug, pertsonaKopMax,
					agenteKod, prezioa, data));
		}
		rs.close();
		ps.close();
		return vIrteerak;
	}

	/**
	 * Datu basean dauden agente guztien izenak itzultzen ditu.
	 * 
	 * @return Agente guztien izenak bektore baten
	 * @throws SQLException
	 */
	public Vector<Agentea> getAgenteak() throws SQLException {
		Vector<Agentea> vAgenteak = new Vector<Agentea>();
		String query = "SELECT Agente_Kodea, Izena FROM Agentea";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			vAgenteak.addElement(new Agentea(rs.getInt("Agente_Kodea"), rs
					.getString("Izena")));
		rs.close();
		ps.close();
		return vAgenteak;
	}

	public boolean isConnectedToDatabase() {
		return connectedToDatabase;
	}

	public int getPertsonaMax(int irteeraKodea) throws SQLException {
		String query = "SELECT Pertsona_Kop_Max FROM Irteera WHERE Irteera_Kodea = ?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setInt(1, irteeraKodea);
		ResultSet rs = ps.executeQuery();
		int kopMax = -1;
		if (rs.next())
			kopMax = rs.getInt("Pertsona_Kop_Max");
		rs.close();
		ps.close();
		return kopMax;
	}

	public int getPertsonaKop(int erreserbaZenb) throws SQLException {
		String query = "SELECT Pertsona_Kopurua FROM Erreserba WHERE Erreserba_Zenbakia = ?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setInt(1, erreserbaZenb);
		ResultSet rs = ps.executeQuery();
		int kop = 0;
		if (rs.next())
			kop = rs.getInt("Pertsona_Kopurua");
		rs.close();
		ps.close();
		return kop;
	}

	public int getErreserbatutakoPertsonaKop(int irteeraKodea)
			throws SQLException {
		String query = "SELECT SUM(Pertsona_Kopurua) AS KopGuztira FROM Erreserba WHERE Irteera_Kodea = ?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setInt(1, irteeraKodea);
		ResultSet rs = ps.executeQuery();
		int kop = 0;
		if (rs.next())
			kop = rs.getInt("KopGuztira");
		rs.close();
		ps.close();
		return kop;
	}

	public int getTuristaKop(int erreserbaZenb) throws SQLException {
		String query = "SELECT COUNT(Izena) AS TuristaKop FROM Turista WHERE Erreserba_Zenb=?";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ps.setInt(1, erreserbaZenb);
		ResultSet rs = ps.executeQuery();
		int kop = 0;
		if (rs.next())
			kop = rs.getInt("TuristaKop");
		rs.close();
		ps.close();
		return kop;
	}

	public int getLastBaieztapenZenb() throws SQLException {
		String query = "SELECT MAX(Baieztapen_Zenbakia) AS AzkenZenb FROM Erreserba";
		PreparedStatement ps = konexioa.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int baiZenb = 0;
		if (rs.next())
			baiZenb = rs.getInt("AzkenZenb");
		rs.close();
		ps.close();
		return baiZenb;
	}

	public int deleteErreserba(int erreserbaZenbakia) throws SQLException {
		String update = "DELETE FROM Erreserba WHERE Erreserba_Zenbakia=?";
		PreparedStatement ps = konexioa.prepareStatement(update);
		ps.setInt(1, erreserbaZenbakia);
		int kont = ps.executeUpdate();
		ps.close();
		return kont;
	}
}
