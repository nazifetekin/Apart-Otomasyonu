package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class odalarController {

	public odalarController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private Button d10btn;

    @FXML
    private Button d11btn;

    @FXML
    private Button d12btn;

    @FXML
    private Button d13btn;

    @FXML
    private Button d14btn;

    @FXML
    private Button d15btn;

    @FXML
    private Button d16btn;

    @FXML
    private Button d17btn;

    @FXML
    private Button d18btn;

    @FXML
    private Button d1btn;

    @FXML
    private Button d2btn;

    @FXML
    private Button d3btn;

    @FXML
    private Button d4btn;

    @FXML
    private Button d5btn;

    @FXML
    private Button d6btn;

    @FXML
    private Button d7btn;

    @FXML
    private Button d8btn;

    @FXML
    private Button d9btn;

    
    Connection baglanti=null;
    PreparedStatement sorguİfadesi=null;
    ResultSet getirilen=null;
    String sql;


    @FXML
    void d10btn_Click(ActionEvent event) {

    }

    @FXML
    void d11btn_Click(ActionEvent event) {

    }

    @FXML
    void d12btn_Click(ActionEvent event) {

    }

    @FXML
    void d13btn_Click(ActionEvent event) {

    }

    @FXML
    void d14btn_Click(ActionEvent event) {

    }

    @FXML
    void d15btn_Click(ActionEvent event) {

    }

    @FXML
    void d16btn_Click(ActionEvent event) {

    }

    @FXML
    void d17btn_Click(ActionEvent event) {

    }

    @FXML
    void d18btn_Click(ActionEvent event) {

    }

    @FXML
    void d1btn_Click(ActionEvent event) {

    }

    @FXML
    void d2btn_Click(ActionEvent event) {

    }

    @FXML
    void d3btn_Click(ActionEvent event) {

    }

    @FXML
    void d4btn_Click(ActionEvent event) {

    }

    @FXML
    void d5btn_Click(ActionEvent event) {

    }

    @FXML
    void d6btn_Click(ActionEvent event) {

    }

    @FXML
    void d7btn_Click(ActionEvent event) {

    }

    @FXML
    void d8btn_Click(ActionEvent event) {

    }

    @FXML
    void d9btn_Click(ActionEvent event) {

    }
    

    @FXML
    void initialize() {
        
    	try {
            // Odaları veritabanından alır
            sql = "SELECT odalar FROM ogrenciKayitdb";
            sorguİfadesi = baglanti.prepareStatement(sql);
            getirilen = sorguİfadesi.executeQuery();
            
            
            // Her bir düğmeyi kırmızı ve tıklanamaz yaparak veritabanından gelen odalarla eşleştir
            while (getirilen.next()) {
                String odalar = getirilen.getString("odalar");
                Button button = getButtonOdaNo(odalar);
                if (button != null) {
                    button.setDisable(true);
                    button.setStyle("-fx-background-color: red");
                    
                }
                
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Kaynakları temizler
            try {
                if (getirilen != null) getirilen.close();
                if (sorguİfadesi != null) sorguİfadesi.close();
                if (baglanti != null) baglanti.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private Button getButtonOdaNo(String odalar) {
    	
    	//oda isimlerine ilgili butonlara aktarıldı
        switch (odalar) {
            case "Daire 1":
                return d1btn;
            case "Daire 2":
                return d2btn;
            case "Daire 3":
                return d3btn;
            case "Daire 4":
                return d4btn;
            case "Daire 5":
                return d5btn;
            case "Daire 6":
                return d6btn;
            case "Daire 7":
                return d7btn;
            case "Daire 8":
                return d8btn;
            case "Daire 9":
                return d9btn;
            case "Daire 10":
                return d10btn;
            case "Daire 11":
                return d11btn;
            case "Daire 12":
                return d12btn;
            case "Daire 13":
                return d13btn;
            case "Daire 14":
                return d14btn;
            case "Daire 15":
                return d15btn;
            case "Daire 16":
                return d16btn;
            case "Daire 17":
                return d17btn;
            case "Daire 18":
                return d18btn;
            default:
                return null;
        }
    }

    

}
