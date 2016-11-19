package practitioner_desktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;


public class InitialLayoutController implements Initializable {

    @FXML
    private Button patientInboxButton;
    @FXML
    private Button patientReceiverButton;
    @FXML
    private Button patientSentButton;
    @FXML
    private Button patientNewAccountButton;

    @FXML
    private ListView<Patient> listView;
    private ObservableList<Patient> patientListViewData = FXCollections.observableArrayList();
    private ObservableList<Patient> patientReceiverListViewData = FXCollections.observableArrayList();
    private ObservableList<Patient> patientSentListViewData = FXCollections.observableArrayList();

    @FXML
    private ImageView patitentImage;

    @FXML
    Label patientSubjectLabel;
    @FXML
    Label patientSeverityLabel;

    @FXML
    private TextArea patientTextArea;
    @FXML
    private TextArea providerTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        listView.setItems(getPatientListViewData());

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            patientSubjectLabel.setText(observable.getValue().getMessageSubject());
            patientSeverityLabel.setText(Integer.toString(observable.getValue().getMessageSeverity()));
            patientTextArea.setText(observable.getValue().getMessageContent());
        });

        patientInboxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearPatientTextArea();
                listView.getItems().clear();
                listView.setItems(getPatientListViewData());
            }
        });

        patientReceiverButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearPatientTextArea();
                listView.getItems().clear();
                listView.setItems(getPatientReceiverListViewData());
            }
        });

        patientSentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearPatientTextArea();
                listView.getItems().clear();
                listView.setItems(getPatientSentListViewData());
            }
        });
    }

    private void clearPatientTextArea(){
        patientSubjectLabel.setText("");
        patientSeverityLabel.setText("");
        patientTextArea.setText("");
    }

    private ObservableList<Patient> getPatientListViewData(){
        patientListViewData.add(new Patient("Patient #2", 1));
        patientListViewData.add(new Patient("Patient #3", 2));

        return patientListViewData;
    }

    private ObservableList<Patient> getPatientReceiverListViewData(){
        patientReceiverListViewData.add(new Patient("Patient #1", 0));
        patientReceiverListViewData.add(new Patient("Patient #2", 1));
        patientReceiverListViewData.add(new Patient("Patient #3", 2));

        return patientReceiverListViewData;
    }

    private ObservableList<Patient> getPatientSentListViewData(){
        patientSentListViewData.add(new Patient("Patient #1", 0));
        return patientSentListViewData;
    }
}
