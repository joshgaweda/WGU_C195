package Main;

import DBUtils.DBConnection;
import DBUtils.DBQuery;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * WGU C195
 * @author Josh Gaweda
 */
public class Main extends Application {

    /**
     * Loads database and displays the login window
     * @param primaryStage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        DBQuery data = new DBQuery();
        data.readData();
        ResourceBundle rb = ResourceBundle.getBundle("Properties/rb", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controllers/Login.fxml"));
        View_Controllers.LoginController controller = new View_Controllers.LoginController(data);
        loader.setController(controller);
        loader.setResources(rb);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Opens and closes the database connection
     * @param args
     */
    public static void main(String[] args) {
        DBConnection.connect();
        launch(args);
        DBConnection.disconnect();
    }
}
