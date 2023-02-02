package View;

import Controller.GrandController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Main{
//    @Override
//    public void start(Stage stage) throws Exception {
//        URL url = new URL(Main.class.getClassLoader().getResource("View/LoginPage.fxml").toExternalForm());
//        BorderPane root = FXMLLoader.load(url);
////        Image icon = new Image(Main.class.getResource("View/digikala.png").toExternalForm());
////        stage.getIcons().add(icon);
//        stage.setTitle("Digi kala");
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

    public static void main(String[] args) {
//        launch();
        GrandController grandController = GrandController.getInstance();
        grandController.start();
//        try{
//            String databaseDriver = "net.sourceforge.jtds.jdbc.Driver";
//            Class.forName(databaseDriver);
//            Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://DESKTOP-JLBIAKI:1433;instance=MSSQLSERVER");
//            Statement statement = connection.createStatement();
//            ResultSet rs= statement.executeQuery("select * from person");
//            while (rs.next()){
//                String name = rs.getString("firstname");
//                System.out.println(name);
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }


    }
}


