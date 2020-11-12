package Main;

import DBUtils.*;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * WGU C195
 * @author Josh Gaweda
 */
public class Main extends Application 
{
    /**
     * Opens/closes the database connection
     * @param args
     */
    public static void main(String[] args) 
    {
        DBConnection.connect_DB();
        launch(args);
        DBConnection.disconnect_DB();
    }
    
    /**
     * Loads database and displays the login window
     * @param mainStage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage mainStage) throws Exception 
    {
        DBQuery dbq = new DBQuery();
        dbq.read_DB_Data();
        ResourceBundle rb = ResourceBundle.getBundle("Properties/rb", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controllers/Login.fxml"));
        View_Controllers.LoginController controller = new View_Controllers.LoginController(dbq);
        loader.setController(controller);
        loader.setResources(rb);
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }
}
