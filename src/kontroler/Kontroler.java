package kontroler;

import java.util.ArrayList;
import java.util.Date;

import broker.Broker;
import domen.Artikal;
import domen.TipArtikla;

public class Kontroler {
	
	public static Kontroler instanca=null;
	
	public static  Kontroler getInstanca(){
		
		if(instanca==null){
			
			instanca=new Kontroler();
		}
		
		return instanca;
		
		
	}

	public void upisiArtikal(String ime, String barkod, String sifra, int tip) {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().upisiArtikal(ime, barkod,sifra, tip);
		Broker.getBroker().zatvoriKonekciju();
		
	}

	public ArrayList<TipArtikla> vratitipArtikla() {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		ArrayList<TipArtikla>al=Broker.getBroker().vratiTip();
		Broker.getBroker().zatvoriKonekciju();
		return al;
		
	}

	public ArrayList<domen.Artikal> vratiArtikal() {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		ArrayList<domen.Artikal>al=Broker.getBroker().vratiArtikal();
		Broker.getBroker().zatvoriKonekciju();
		return al;
	}

	public void promeniPodatke(String ime, String barkod, String sifra, int id, String idArtikla) {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().promeniPodatke(ime, barkod,sifra, id,idArtikla);
		Broker.getBroker().zatvoriKonekciju();
		
	}

	public ArrayList<domen.Zaposleni> vratiZaposlene() {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		ArrayList<domen.Zaposleni>al=Broker.getBroker().vratiZaposlene();
		Broker.getBroker().zatvoriKonekciju();
		return al;
	}

	public void upisiUcinak(int idArtikla, int idZaposlenog, int kol, String datumOd, String datumDo) {
		// TODO Auto-generated method stub
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().upisiUcinak( idArtikla,  idZaposlenog,  kol,  datumOd,  datumDo);
		Broker.getBroker().zatvoriKonekciju();
	}
	public ArrayList<domen.Preduzece>vratiPreduzeca(){
		Broker.getBroker().otvoriKonekciju();
		ArrayList<domen.Preduzece>al=Broker.getBroker().vratiPreduzeca();
		Broker.getBroker().zatvoriKonekciju();
		return al;
	}

	public void upisiOrganizacionuJedinicu(String nazivOrgjed, String adresa, int idPreduzeca) {
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().upisiOrganizacionuJedinicu(nazivOrgjed, adresa,  idPreduzeca);
		Broker.getBroker().zatvoriKonekciju();
		
	}

	public ArrayList<domen.OrganizacionaJedinica>vratiOrganizacioneJedinice(){
		Broker.getBroker().otvoriKonekciju();
		ArrayList<domen.OrganizacionaJedinica>al=Broker.getBroker().vratiOrganizacioneJedinice();
		Broker.getBroker().zatvoriKonekciju();
		return al;
	}

	public void promeniOrganizacionuJedinicu(String naziv, String adresa, int idPreduzeca,int id) {
		
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().promeniOrganizacionuJedinicu( naziv, adresa, idPreduzeca,id) ;
		Broker.getBroker().zatvoriKonekciju();
	}
	public ArrayList<domen.TipRandnogMesta>vratitipRadnogMesta(){
		Broker.getBroker().otvoriKonekciju();
		ArrayList<domen.TipRandnogMesta>al=Broker.getBroker().vratitipRadnogMesta();
		Broker.getBroker().zatvoriKonekciju();
		return al;
	}

	public void upisiVezuRMiOJ(int idRM, int idOJ) {
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().upisiVezuRMiOJ( idRM,  idOJ);
		Broker.getBroker().zatvoriKonekciju();
		
	}
	public void proslediZaposlenog(String naziv, String ime, String prezime, String jmbg, int id) {
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().proslediZaposlenog( naziv, ime, prezime,  jmbg, id);
		Broker.getBroker().zatvoriKonekciju();
	}

	public void promeniPodatkeOZaposlenom(String ime, String prezime, String jmbg, int id, String pozicija, int idZaposlenog) {
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().promeniPodatkeOZaposlenom( ime,  prezime,  jmbg,  id,  pozicija, idZaposlenog) ;
		Broker.getBroker().zatvoriKonekciju();
	}
	public void obrisiZaposlenog(int idZaposlenog){
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().obrisiZaposlenog(idZaposlenog);
		Broker.getBroker().zatvoriKonekciju();
	}

	public void upisiNazivTipaArtikla(String naziv) {
		Broker.getBroker().otvoriKonekciju();
		Broker.getBroker().upisiNazivTipaArtikla( naziv);
		Broker.getBroker().zatvoriKonekciju();
		
	}
}
