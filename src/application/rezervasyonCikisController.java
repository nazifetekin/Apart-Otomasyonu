package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class rezervasyonCikisController {
	
	public rezervasyonCikisController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String > col_ad;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_bolum;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, Date> col_cikis;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, Date> col_giris;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, Integer> col_id;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_krediK;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String > col_nakit;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_oda;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_tc;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_tel;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_tutar;

    @FXML
    private TableColumn<rezervasyon_cikisTablo, String> col_unv;

    @FXML
    private Label labelİd;

    @FXML
    private Button silbtn;

    @FXML
    private TableView<rezervasyon_cikisTablo> tableview_rezervasyonCikis;
    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;

    @FXML
    void silbtn_Click(ActionEvent event) {
    	sql = "delete from ogrenciKayitdb where id = ?";

    	try {
    		sorguİfadesi=baglanti.prepareStatement(sql);
    		sorguİfadesi.setInt(1, Integer.parseInt(labelİd.getText()));
    		
            sorguİfadesi.executeUpdate();

            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText(null);
            alert.setContentText("Silme işlemi başarılı");
            alert.showAndWait();
            
         // Silme işlemi başarılıysa tabloyu güncelleyin
            DegerleriGetir(tableview_rezervasyonCikis);
			
		} catch (Exception e) {
			// TODO: handle exception
			//silme işlemi hata mesajı
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText(null);
            alert.setContentText("Silme işlemi başarısız oldu");
		}

    }
    
public void DegerleriGetir(TableView tablo) {
    	
    	sql="select * from ogrenciKayitdb";
    	
    	//veritabanı bilgilerini dinamik olarak tableView'e bağlıyor

    	ObservableList<rezervasyon_cikisTablo> rezervasyonliste=FXCollections.observableArrayList();
    	
    	try {
    		//sql sorgusu çalıştırılıyor  ve bilgiler getirilen 'e atanıyor
			sorguİfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguİfadesi.executeQuery();
			
			//veritabanındaki her sütün ismi getirilen'den alınıp
			//rezervasyon_cikisTablo classındaki ilgili alanlarda set ediliyor
			
			while(getirilen.next()) {
				rezervasyonliste.add(new rezervasyon_cikisTablo(getirilen.getInt("id"), getirilen.getString("adSoyad"), getirilen.getString("tcNo"), getirilen.getString("telNo"), getirilen.getString("unvAd"), getirilen.getString("bolumad"), getirilen.getString("odalar"), getirilen.getDate("girisT"), getirilen.getDate("cikisT"), getirilen.getString("Tutar"), getirilen.getString("nakit"), getirilen.getString("kredi")));
				
			}
			
			//tableView'deki her sütün rezervasyon_cikisTablo'daki değerleri alıyor.
			
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
			tableview_rezervasyonCikis.setItems(rezervasyonliste);

			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

    @FXML
    void tableview_MouseClick(MouseEvent event) {
    	
  	 
    	rezervasyon_cikisTablo kayit= new rezervasyon_cikisTablo();
    	kayit=(rezervasyon_cikisTablo) tableview_rezervasyonCikis.getItems().get(tableview_rezervasyonCikis.getSelectionModel().getSelectedIndex());
    	labelİd.setText(String.valueOf(kayit.getId()));

    }

    @FXML
    void initialize() {
    	// Tabloyu doldurmak için
    	DegerleriGetir(tableview_rezervasyonCikis);
    }

}
