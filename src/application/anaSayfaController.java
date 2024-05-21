package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class anaSayfaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anaSayfaa;

    @FXML
    private Button btn_Rcikis;

    @FXML
    private Button btn_Rkayit;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_musteri;

    @FXML
    private Button btn_odalar;
    
   

    @FXML
    void btn_Rcikis_Click(ActionEvent event) {
    	try {
            // rezervasyon çikis paneline geçiş
    		Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rezervasyonCikis.fxml"));
    		AnchorPane root=(AnchorPane) loader.load();
    		Scene scene=new Scene(root, 1000, 500);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btn_Rkayit_Click(ActionEvent event) {
    	try {
            // rezervasyon Kayıt paneline geçiş
    		Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rezervasyonKayit.fxml"));
    		AnchorPane root=(AnchorPane) loader.load();
    		Scene scene=new Scene(root, 600, 450);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btn_cikis_Click(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void btn_musteri_Click(ActionEvent event) {
    	try {
            // müşteri listesi paneline geçiş
    		Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("musteriListesi.fxml"));
    		AnchorPane root=(AnchorPane) loader.load();
    		Scene scene=new Scene(root, 1000, 500);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_odalar_Click(ActionEvent event) {
    	try {
            // odalar paneline geçiş
    		Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("odalar.fxml"));
    		AnchorPane root=(AnchorPane) loader.load();
    		Scene scene=new Scene(root, 600, 450);
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
