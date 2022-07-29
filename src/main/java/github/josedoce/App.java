package github.josedoce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App 
 * guids https://www.educba.com/javafx-timer/
 */
public class App extends Application {
    private static ActionApplication actionApplication = null;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle("Matematica");
        stage.setScene(scene);
        stage.setOnCloseRequest((event)->{
            if(actionApplication != null){
                actionApplication.onCloseApplication();
            }
        });
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void on(ActionApplication action) {
        actionApplication = action;
    }

    public interface ActionApplication {
        void onCloseApplication();
    }   
}