package broker;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import domen.Artikal;
import domen.OrganizacionaJedinica;
import domen.Preduzece;
import domen.Radnik;
import domen.Rukovodilac;
import domen.TipArtikla;
import domen.TipRandnogMesta;
import domen.Zaposleni;

public class Broker {
	
	public static Broker broker;
	private java.sql.Connection con = null; // class za vezu sa bazom
	
	public Broker() { // konstruktor default

		ucitajDrajver();

	}
	public void ucitajDrajver() { // metoda

		try {
			Class.forName("com.mysql.jdbc.Driver"); // classa koja u sebi ima
													// metodu u njoj se nalazi
													// jdbc drivera
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void otvoriKonekciju() {

		try {/// konectioni string
			con = DriverManager.getConnection("jdbc:mysql://localhost/fabrika", "root", "");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static Broker getBroker(){
		
		if(broker==null){
			
			broker =new Broker();
		}
		return broker;
		
		
	}
	public void zatvoriKonekciju() {

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void upisiArtikal(String ime, String barkod, String sifra, int tip) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO artikl (naziv,barkod,id_tip_artikla,sifra ) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ime);
			ps.setString(2, barkod);
			ps.setInt(3, tip);
			ps.setString(4, sifra);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<TipArtikla> vratiTip() {
		
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<TipArtikla> al = new ArrayList<>();
		String upit = "SELECT  idTipArtikla,naziv from tipartikla";

		try {
			st = con.createStatement();

			rs = st.executeQuery(upit);

			while (rs.next()) {

				TipArtikla tp = new TipArtikla();
				tp.setId(rs.getInt("idTipArtikla"));
				tp.setNazivTipaArtikla(rs.getString("naziv"));
				
				al.add(tp);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}
	public ArrayList<domen.Artikal> vratiArtikal() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<domen.Artikal> al = new ArrayList<>();
		String upit = "SELECT  idArtikla,naziv,barkod,id_tip_artikla, 	sifra  from artikl";

		try {
			st = con.createStatement();

			rs = st.executeQuery(upit);

			while (rs.next()) {

				Artikal tp = new Artikal();
				tp.setId(rs.getInt("idArtikla"));
				tp.setNaziv(rs.getString("naziv"));
				tp.setBarkod(rs.getString("barkod"));
				tp.setIdtip(rs.getInt("id_tip_artikla"));
				tp.setSifra(rs.getString("sifra"));
				
				
				al.add(tp);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}
	public void promeniPodatke(String ime, String barkod, String sifra, int id, String idArtikla) {
		// TODO Auto-generated method stub
		int idART=Integer.valueOf(idArtikla);
		String izmena = "UPDATE Artikl SET naziv='" + ime + "' where IDArtikla='" + idART+
				"'";

		try {

			PreparedStatement ps = con.prepareStatement(izmena);
			System.out.print(izmena);
			ps.execute();
			// JOptionPane.showMessageDialog(null, "Obrisana stavka");

			// Update_table();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska nije obrisana stavka");
		}

		
	}
	
	public ArrayList<domen.Zaposleni> vratiZaposlene() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<domen.Zaposleni> al = new ArrayList<>();
		domen.Zaposleni za = null;
		String upit = "SELECT  id_org_jed,ime,prezime,jmbg, id,pozicija from zaposleni";

		try {
			st = con.createStatement();

			rs = st.executeQuery(upit);

			while (rs.next()) {

				
				if(rs.getString("pozicija").equalsIgnoreCase("rukovodilac")){
					 za = new Rukovodilac();
					za.setId_org_jed(rs.getInt("id_org_jed"));
					za.setIme(rs.getString("ime"));
					za.setPrezime(rs.getString("prezime"));
					za.setJmbg(rs.getString("jmbg"));
					za.setId(rs.getInt("id"));
					za.setPozicija(rs.getString("pozicija"));
				
				} if(rs.getString("pozicija").equalsIgnoreCase("zaposleni")){
				 za = new Radnik();
				za.setId_org_jed(rs.getInt("id_org_jed"));
				za.setIme(rs.getString("ime"));
				za.setPrezime(rs.getString("prezime"));
				za.setJmbg(rs.getString("jmbg"));
				za.setId(rs.getInt("id"));
				za.setPozicija(rs.getString("pozicija"));
		
				}
				
				
				al.add(za);
				//System.out.println(al);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}
	public void upisiUcinak(int idArtikla, int idZaposlenog, int kol, String datumOd, String datumDo) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO ucinak (idZaposlenog,idArtikla,kolicina,datumOd, datumDo) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idZaposlenog);
			ps.setInt(2, idArtikla);
			ps.setInt(3, kol);
			ps.setString(4, datumOd);
			ps.setString(5,  datumDo);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public ArrayList<domen.Preduzece>vratiPreduzeca(){
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<domen.Preduzece> al = new ArrayList<>();
		String upit = "SELECT  idPreduzeca,pib,naziv,adresa  from preduzece ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(upit);

			while (rs.next()) {

				Preduzece p=new Preduzece();
				p.setIdPreduzeca(rs.getInt("idPreduzeca"));
				p.setPib(rs.getString("pib"));
				p.setNaziv(rs.getString("naziv"));
				p.setAdresa(rs.getString("adresa"));
				
				al.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
	public void upisiOrganizacionuJedinicu(String nazivOrgjed, String adresa, int idPreduzeca) {
		String sql="INSERT INTO organizacionajedinica(naziv,adresa,idPreduzeca)VALUES(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nazivOrgjed);
			ps.setString(2, adresa);
			ps.setInt(3, idPreduzeca);
			
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<domen.OrganizacionaJedinica>vratiOrganizacioneJedinice(){
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<domen.OrganizacionaJedinica> al = new ArrayList<>();
		String upit = "SELECT  idOrgJed,naziv,adresa,idpreduzeca  from organizacionajedinica ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(upit);

			while (rs.next()) {

				OrganizacionaJedinica oj=new OrganizacionaJedinica();
				oj.setIdOrganizacioneJedinice(rs.getInt("idOrgJed"));
				oj.setNaziv(rs.getString("naziv"));
				oj.setAdresa(rs.getString("adresa"));
				oj.setIdPreduzeca(rs.getInt("idpreduzeca"));
				al.add(oj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;	
}
	public void promeniOrganizacionuJedinicu(String naziv, String adresa, int idPreduzeca, int id) {
		// TODO Auto-generated method stub
		String update="UPDATE organizacionajedinica SET naziv=?,adresa=?,idpreduzeca=? WHERE idOrgJed=?";
		try {
			PreparedStatement ps=con.prepareStatement(update);
			ps.setString(1, naziv);
			ps.setString(2, adresa);
			ps.setInt(3, idPreduzeca);
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<domen.TipRandnogMesta>vratitipRadnogMesta(){
		ResultSet rs = null;
		java.sql.Statement st = null;
		ArrayList<domen.TipRandnogMesta> al = new ArrayList<>();
		String upit = "SELECT  idRadnogMesta,Naziv  from tipradnogmesta ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(upit);

			while (rs.next()) {

				TipRandnogMesta trm=new TipRandnogMesta();
				trm.setIdRadnogMesta(rs.getInt("idRadnogMesta"));
				trm.setNaziv(rs.getString("Naziv"));
				al.add(trm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;	
}
	public void upisiVezuRMiOJ(int idRM, int idOJ) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO radnomesto_orgjed(id_tip,id_org)VALUES(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idRM);
			ps.setInt(2, idOJ);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void proslediZaposlenog(String naziv, String ime, String prezime, String jmbg, int id) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO zaposleni (jmbg,ime,prezime,id_org_jed, pozicija) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,jmbg);
			ps.setString(2, ime);
			ps.setString(3, prezime);
			ps.setInt(4, id);
			ps.setString(5,  naziv);
			ps.execute();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void promeniPodatkeOZaposlenom(String ime, String prezime, String jmbg, int id, String pozicija,int idZaposlenog) {
		String update="UPDATE zaposleni SET jmbg=?,ime=?,prezime=?,id_org_jed=?,pozicija=? WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(update);
			ps.setString(1, jmbg);
			ps.setString(2, ime);
			ps.setString(3, prezime);
			ps.setInt(4, id);
			ps.setString(5, pozicija);
			ps.setInt(6, idZaposlenog);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void obrisiZaposlenog(int idZaposlenog){
		String del="DELETE FROM zaposleni WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(del);
			ps.setInt(1, idZaposlenog);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void upisiNazivTipaArtikla(String naziv) {
		String unos="INSERT INTO tipartikla (naziv) VALUES (?)";
		try {
			PreparedStatement st =con.prepareStatement(unos);
			st.setString(1, naziv);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	}
