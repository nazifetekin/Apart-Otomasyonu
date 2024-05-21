package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;

public class musteriListesiController {
	
	public musteriListesiController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker cikisTarihi;
    
    @FXML
    private Button guncellebtn;
    @FXML
    private Label idLabel;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_ad;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_bolum;
    
    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_oda;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, Date> col_cikis;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, Date> col_giris;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, Integer> col_id;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_tc;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_tel;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_tutar;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_unv;
    
    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_krediK;

    @FXML
    private TableColumn<rezervasyon_kayitTablo, String> col_nakit;

    @FXML
    private TableView<rezervasyon_kayitTablo> tableview_musteriListe;

    @FXML
    private TextField tutarText;
    
  
    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    void guncellebtn_Click(ActionEvent event) {
    	
    	/* Hem tabloda güncelleme yapacak ve güncellemeden sonra 
    	güncellenen değeri tabloya aktaracakve tabloyu yenileyecek) */
    	
    	sql="update ogrenciKayitdb set cikisT=?, Tutar=? where id=?";
    	try {
			sorguİfadesi=baglanti.prepareStatement(sql);
			sorguİfadesi.setDate(1, java.sql.Date.valueOf(cikisTarihi.getValue()));
	        sorguİfadesi.setInt(2, Integer.parseInt(tutarText.getText().trim()));
			sorguİfadesi.setInt(3, Integer.parseInt(idLabel.getText()));
			
			sorguİfadesi.executeUpdate();
			
			//güncelleme başarılı bilgi mesajını ekrana verecek
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText(null);
            alert.setContentText("Güncelleme işlemi başarılı");
            alert.showAndWait();
            
            //tabloyu yenileyecek
            DegerleriGetir(tableview_musteriListe);

		} catch (Exception e) {
			// TODO: handle exception
			
			//güncelleme işlemi olmazsa ekrana hata mesajı verecek
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText(null);
            alert.setContentText("Güncelleme işlemi başarısız oldu");
		}

    }
    
   
    public void DegerleriGetir(TableView tablo) {
    	
    	sql="select * from ogrenciKayitdb";
    	
    	//veritabanı bilgilerini dinamik olarak tableView'e bağlıyor
    	ObservableList<rezervasyon_kayitTablo> rezervasyonliste=FXCollections.observableArrayList();
    	
    	try {
    		
    		//sql sorgusu çalıştırılıyor  ve bilgiler getirilen 'e atanıyor
			sorguİfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguİfadesi.executeQuery();
			
			
			//veritabanındaki her sütün ismi getirilen'den alınıp
			//rezervasyon_kayitTablo classındaki ilgili alanlarda set ediliyor
			
			while(getirilen.next()) {
				
				
				rezervasyonliste.add(new rezervasyon_kayitTablo(getirilen.getInt("id"), getirilen.getString("adSoyad"), getirilen.getString("tcNo"), getirilen.getString("telNo"), getirilen.getString("unvAd"), getirilen.getString("bolumad"), getirilen.getString("odalar"), getirilen.getDate("girisT"), getirilen.getDate("cikisT"), getirilen.getString("Tutar"), getirilen.getString("nakit"), getirilen.getString("kredi")));
				
			}
			//tableView'deki her sütün rezervasyon_kayitTablo'daki değerleri alıyor.
			
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			col_ad.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
			col_tc.setCellValueFactory(new PropertyValueFactory<>("tcNumara"));
			col_tel.setCellValueFactory(new PropertyValueFactory<>("telNumara"));
			col_unv.setCellValueFactory(new PropertyValueFactory<>("unvAdi"));
			col_bolum.setCellValueFactory(new PropertyValueFactory<>("bolumAdi"));
			col_oda.setCellValueFactory(new PropertyValueFactory<>("odaNumara"));
			col_giris.setCellValueFactory(new PropertyValueFactory<>("girisTarihi"));
			col_cikis.setCellValueFactory(new PropertyValueFactory<>("cikisTarihi"));
			col_tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
			col_nakit.setCellValueFactory(new PropertyValueFactory<>("nakit"));
			col_krediK.setCellValueFactory(new PropertyValueFactory<>("krediK"));
			
			//tableView değerleri set ediyor ve veriler bu listede görüntülenir
			tableview_musteriListe.setItems(rezervasyonliste);

			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

    @FXML
    void tableview_MouseClick(MouseEvent event) {
    	
    	/*herhangi bir kişiye tıkladığımızda  o kişinin çıkış tarihi ve ücret bilgisini 
    	  text fieldlara yazacak ve admin o bilgileri güncelleye bilecek */
    	 
    	rezervasyon_kayitTablo kayit= new rezervasyon_kayitTablo();
    	kayit=(rezervasyon_kayitTablo) tableview_musteriListe.getItems().get(tableview_musteriListe.getSelectionModel().getSelectedIndex());
    	idLabel.setText(String.valueOf(kayit.getId()));
        tutarText.setText(String.valueOf(kayit.getTutar()));
        cikisTarihi.setValue(kayit.getCikisTarihi().toLocalDate());
    }

    @FXML
    void initialize() {
    	DegerleriGetir(tableview_musteriListe);

    	
        
    }

}
