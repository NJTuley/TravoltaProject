package practitioner_desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        //title of application
        primaryStage.setTitle("Practitioner Desktop Application");

        //layouts
        Parent initial = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        //scenes
        Scene practitionerContent = new Scene(initial);

        //set initial scene & display
        primaryStage.setScene(practitionerContent);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
