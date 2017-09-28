package domen;

public class Artikal {
	private int id,idtip;
	public int getId() {
		return id;
	}
	public Artikal(){
		
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdtip() {
		return idtip;
	}
	public void setIdtip(int idtip) {
		this.idtip = idtip;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getBarkod() {
		return barkod;
	}
	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}
	private String naziv,sifra, barkod;

}
