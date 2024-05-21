package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {
	
	public loginController() {
		baglanti=VeritabaniUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Button btnEkle;

    @FXML
    private URL location;

    @FXML
    private Button btnGiris;


    @FXML
    private TextField logAd_text;

    @FXML
    private PasswordField logSifre_text;
    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;

    @FXML
    void btnGiris_Click(ActionEvent event) {
    	sql="select * from logindb where kullanici_adi=? and sifre=?";
    	
    	try {
    		String kullaniciAdi = logAd_text.getText().trim();
            String sifre = logSifre_text.getText().trim();
            String sifreMd5 = MD5sifre.hash(sifre);
 
    		//veritabanı ile bağlantı kurar ve bilgileri doğrular
			sorguİfadesi=baglanti.prepareStatement(sql);
			sorguİfadesi.setString(1, kullaniciAdi);
			sorguİfadesi.setString(2, sifreMd5);
			
			ResultSet getirilen=sorguİfadesi.executeQuery();
			
			if(!getirilen.next()) {
				
				//şifre veya kullanıcı adı yanlış girdi mi ekrana uyarı mesajı gelecek
				Alert alert= new Alert(AlertType.ERROR);
				alert.setTitle("Sisteme Giriş Yapılamadı!");
				alert.setHeaderText(null);
				alert.setContentText("Kullanıcı Adı veya Şifre hatalıdır!");
				alert.showAndWait();
			}
			
			else {
				//giriş başarılı olduğunda anasayfa paneline geçme  kodu
				Stage stage = new Stage();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("anaSayfa.fxml"));
	    		AnchorPane root=(AnchorPane) loader.load();
	    		Scene scene=new Scene(root, 600, 450);
	            stage.setScene(scene);
	            stage.show();
	            
	            //login panelini kapatma kodu
	            Stage loginStage = (Stage) btnGiris.getScene().getWindow();
                loginStage.close();
				
				
				
				//veritabanı bilgilerini consola yazdırır
				getirilen.getString(1); //1.sütündaki değeri getirir
				System.out.println("id:" + getirilen.getString("id"));
				System.out.println("kullanıcı:" + getirilen.getString("kullanci_adi"));
				System.out.println("şifre:" + getirilen.getString(""));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}

    }
    
    
    @FXML
    void btnEkle_Click(ActionEvent event) {
    	
    	try {
            // yeni admin Kayit ekleme paneline geçiş
    		Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginYeniKayit.fxml"));
    		AnchorPane root=(AnchorPane) loader.load();
    		Scene scene=new Scene(root, 350, 350);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
       

    }

}
