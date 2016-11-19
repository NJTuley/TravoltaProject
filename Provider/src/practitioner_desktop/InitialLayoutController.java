package practitioner_desktop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


public class InitialLayoutController implements Initializable {

    @FXML
    private ListView<String> mainContentListView;

    @FXML
    private ImageView patitentImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainContentListView.setItems(FXCollections.observableArrayList(
                "Patient #1 : [Subject] - Severity",
                "Patient #2 : [Subject] - Severity",
                "Patient #3 : [Subject] - Severity",
                "Patient #4 : [Subject] - Severity",
                "Patient #5 : [Subject] - Severity",
                "Patient #6 : [Subject] - Severity",
                "Patient #7 : [Subject] - Severity",
                "Patient #8 : [Subject] - Severity",
                "Patient #9 : [Subject] - Severity",
                "Patient #10 : [Subject] - Severity",
                "Patient #11 : [Subject] - Severity",
                "Patient #12 : [Subject] - Severity",
                "Patient #13 : [Subject] - Severity",
                "Patient #14 : [Subject] - Severity",
                "Patient #15 : [Subject] - Severity",
                "Patient #16 : [Subject] - Severity",
                "Patient #17 : [Subject] - Severity",
                "Patient #17 : [Subject] - Severity",
                "Patient #18 : [Subject] - Severity",
                "Patient #19 : [Subject] - Severity",
                "Patient #20 : [Subject] - Severity",
                "Patient #21 : [Subject] - Severity",
                "Patient #22 : [Subject] - Severity",
                "Patient #23 : [Subject] - Severity"
        ));


        mainContentListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

            }
        });
    }
}
