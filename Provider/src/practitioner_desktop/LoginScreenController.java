package practitioner_desktop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginScreenController implements Initializable {

    @FXML
    public void loginSubmitButton(ActionEvent event) {
        if(authorizeLogin()){
            try {
                Main.primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("initialLayout.fxml"))));
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private boolean authorizeLogin(){
        return true;
    }
}
