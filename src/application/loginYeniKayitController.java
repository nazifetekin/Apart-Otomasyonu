package application;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginYeniKayitController {
	
	public loginYeniKayitController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnKayit;

    @FXML
    private TextField logAd_text;

    @FXML
    private PasswordField logSifre_text;
    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;


    @FXML
    void btnKayit_Click(ActionEvent event) {
    	sql= "insert into logindb(kullanici_adi, sifre) values(?,?)";
    	
    	try {
    		 String kullaniciAdi = logAd_text.getText().trim();
             String sifre = logSifre_text.getText().trim();
             String sifreMd5 = MD5sifre.hash(sifre);  // Şifreyi MD5'e göre hashle
             
             //yeni kayıt bilgilerini veritabanına ekler. 
             //sifre md5 şifreleme yöntemi ile veritabanına eklenir.
             sorguİfadesi = baglanti.prepareStatement(sql);
             sorguİfadesi.setString(1, kullaniciAdi);
             sorguİfadesi.setString(2, sifreMd5);
             sorguİfadesi.executeUpdate();
    		
             
             //kayıt başarılı bir şekilde eklenirse ekrana bilgi mesajı gösterir
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText(null);
            alert.setContentText("Yeni Kayıt Başarıyla Eklendi!");
            alert.showAndWait();
            
            Stage stage = (Stage) btnKayit.getScene().getWindow();
            stage.close();
            
            
    	 } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();	
		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    @FXML
    void initialize() {
       
    }

}
