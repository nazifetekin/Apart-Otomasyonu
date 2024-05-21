package application;

import java.sql.Date;

public class rezervasyon_cikisTablo {
	private int id;
	private String adSoyad;
	private String tcNumara;
	private String telNumara;
	private String unvAdi;
	private String bolumAdi;
	private String odaNumara;
	private Date girisTarihi;
	private Date cikisTarihi;
	private String tutar;
	private String nakit;
	private String krediK;
	
	
	rezervasyon_cikisTablo() {
		// TODO Auto-generated constructor stub
	}
	rezervasyon_cikisTablo(int id, String adSoyad, String tcNumara, String telNumara, String unvAdi, String bolumAdi, String odaNumarasi, Date girisTarihi, Date cikisTarihi, String tutar, String nakit, String krediK ) {
		this.id=id;
		this.adSoyad=adSoyad;
		this.tcNumara=tcNumara;
		this.telNumara=telNumara;
		this.unvAdi=unvAdi;
		this.bolumAdi=bolumAdi;
		this.odaNumara=odaNumarasi;
		this.girisTarihi=girisTarihi;
		this.cikisTarihi=cikisTarihi;
		this.tutar=tutar;
		this.nakit=nakit;
		this.krediK=krediK;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdSoyad() {
		return adSoyad;
	}
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	public String getTcNumara() {
		return tcNumara;
	}
	public void setTcNumara(String tcNumara) {
		this.tcNumara = tcNumara;
	}
	public String getTelNumara() {
		return telNumara;
	}
	public void setTelNumara(String telNumara) {
		this.telNumara = telNumara;
	}
	public String getUnvAdi() {
		return unvAdi;
	}
	public void setUnvAdi(String unvAdi) {
		this.unvAdi = unvAdi;
	}
	public String getBolumAdi() {
		return bolumAdi;
	}
	public void setBolumAdi(String bolumAdi) {
		this.bolumAdi = bolumAdi;
	}
	public String getOdaNumara() {
		return odaNumara;
	}
	public void setOdaNumara(String odaNumara) {
		this.odaNumara = odaNumara;
	}
	public Date getGirisTarihi() {
		return girisTarihi;
	}
	public void setGirisTarihi(Date girisTarihi) {
		this.girisTarihi = girisTarihi;
	}
	public Date getCikisTarihi() {
		return cikisTarihi;
	}
	public void setCikisTarihi(Date cikisTarihi) {
		this.cikisTarihi = cikisTarihi;
	}
	public String getTutar() {
		return tutar;
	}
	public void setTutar(String tutar) {
		this.tutar = tutar;
	}
	public String getNakit() {
		return nakit;
	}
	public void setNakit(String nakit) {
		this.nakit = nakit;
	}
	public String getKrediK() {
		return krediK;
	}
	public void setKrediK(String krediK) {
		this.krediK = krediK;
	}
	
	

}
