package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class rezervasyonKayitController {
	
	
	public rezervasyonKayitController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adText;

    @FXML
    private TextField bolumText;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_temizle;
    
    @FXML
    private RadioButton krediRadio;

    @FXML
    private RadioButton nakitRadio;

    @FXML
    private DatePicker cikisDate;

    @FXML
    private DatePicker girisDate;
    
    @FXML
    private ComboBox<String> daireCombo;


    @FXML
    private TextField tcText;

    @FXML
    private TextField telefonText;

    @FXML
    private TextField tutarText;

    @FXML
    private TextField uniText;
    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;


    @FXML
    void adText(ActionEvent event) {

    }

    @FXML
    void btn_ekle_Click(ActionEvent event) {
    	
    	sql="insert into ogrenciKayitdb(adSoyad, tcNo, telNo, unvAd, bolumAd, odalar, girisT, cikisT, Tutar, nakit, kredi) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	String adSoyad = adText.getText().trim();
        String tcKimlikNo = tcText.getText().trim();
        String telNo = telefonText.getText().trim();
        String unvAd = uniText.getText().trim();
        String bolumAd = bolumText.getText().trim();
        String daireNo = daireCombo.getValue();
        LocalDate girisT = girisDate.getValue(); 
        LocalDate cikisT = cikisDate.getValue();
        String tutar = tutarText.getText().trim();
        String nakit = nakitRadio.isSelected() ? "Evet" : "Hayır";
        String krediK = krediRadio.isSelected() ? "Evet" : "Hayır";
        
        
        //Tc kimlik no boş geçilmeyecek ve 11 haneli olacak  
        //eğer bunlardan birini içerirse ekrana uyarı mesajı verecek ve kayıt oluşmayacak
        
        if (tcKimlikNo.isEmpty() || tcKimlikNo.length() != 11 ) {
			
			//Tc kimlik numarası girilmediği, eksik olduğu takdirde uyarı ekranı çıkacak
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Geçersiz TC Kimlik No!");
            alert.showAndWait();
            return; // TC numarası formatı yanlışsa işleme devam etme
			
		} 
        //tc kimlik no sadece rakamdan oluşmazsa kayıt olmayacak ve ekrana uyarı mesajı verecek
        
        else if (!tcKimlikNo.matches("^[0-9]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Geçersiz TC Kimlik No! Sadece rakam içermelidir.");
            alert.showAndWait();
            return; // TC sadece rakam içermiyorsa işleme devam etme
        }
     // Telefon numarası 11 haneli ve sadece rakamlardan oluşacak
        
        if (telNo.length() != 11 || !telNo.matches("^[0-9]+$")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Uyarı");
                    alert.setHeaderText(null);
                    alert.setContentText("Geçersiz Telefon Numarası! 11 haneli ve sadece rakam içermelidir.");
                    alert.showAndWait();
                    return; // Telefon numarası formatı yanlışsa işleme devam etme
                }
        
        
    	try {
    		
    		//kayıt bilgileri veritabanına aktarılacak
    		sorguİfadesi=baglanti.prepareStatement(sql);
    		sorguİfadesi.setString(1, adSoyad);
    		sorguİfadesi.setString(2, tcKimlikNo);
    		sorguİfadesi.setString(3, telNo);
    		sorguİfadesi.setString(4, unvAd);
    		sorguİfadesi.setString(5, bolumAd);
    		sorguİfadesi.setString(6, daireNo);
    		sorguİfadesi.setObject(7, girisT);
    		sorguİfadesi.setObject(8, cikisT);
    		sorguİfadesi.setString(9, tutar);
    		sorguİfadesi.setString(10, nakit);
    		sorguİfadesi.setString(11, krediK);
    		sorguİfadesi.executeUpdate();
    
			
    			//kayıtlar eklendi bilgisi ekrana çıkacak
    			 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Bilgi");
                 alert.setHeaderText(null);
                 alert.setContentText("Kayit eklendi.");
                 alert.showAndWait();
    		
    		
			
		} catch (Exception e) {
			
			//veritabanı bağlantısında hata çıkarsa hata mesajı verecek
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Veritabanına bağlanırken bir hata oluştu!");
            alert.showAndWait();
            e.printStackTrace();
		}
        }
    	

    

    @FXML
    void btn_temizle_Click(ActionEvent event) {
    	    adText.clear();
    	    tcText.clear();
    	    telefonText.clear();
    	    uniText.clear();
    	    bolumText.clear();
    	    daireCombo.getSelectionModel().clearSelection();
    	    girisDate.getEditor().clear();
    	    cikisDate.getEditor().clear();
    	    tutarText.clear();
    	    krediRadio.setSelected(false);
    	    nakitRadio.setSelected(false);
    	}
    
    
    @FXML
    void daireCombo_Action(ActionEvent event) {
    	
    	

    }

    @FXML
    void initialize() {
    	
    	
    	daireCombo.setPromptText("Oda seçiniz");
    	String[] oda= {"Daire 1", "Daire 2", "Daire 3", "Daire 4", "Daire 5", "Daire 6", "Daire 7", "Daire 8", "Daire 9", "Daire 10", "Daire 11", "Daire 12", "Daire 13", "Daire 14", "Daire 15", "Daire 16", "Daire 17", "Daire 18"};
    	daireCombo.getItems().addAll(oda);
    }
    
}


