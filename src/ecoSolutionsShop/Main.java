package ecoSolutionsShop;

import ecoSolutionsShop.UI.UIControl.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    //  window id and the fxml file
    public static String windowId1 = "login";
    public static String window1File = "login.fxml";
    public static String windowId2 = "Create New Order";
    public static String window2File = "newOrder.fxml";
    public static String windowId3 = "Manage client";
    public static String window3File = "manageClient.fxml";
    public static String windowId4 = "Check order";
    public static String window4File = "checkOrder.fxml";



    /**
     *
     * @param primaryStage
     */

    @Override
    public void start(Stage primaryStage){
        System.out.println("as"); // visual feed back


        Controller mainContainer = new Controller();
        mainContainer.loadWindow(Main.windowId1, Main.window1File);
        mainContainer.loadWindow(Main.windowId2, Main.window2File);
        mainContainer.loadWindow(Main.windowId3, Main.window3File);
        mainContainer.loadWindow(Main.windowId4, Main.window4File);


        // the default screen
        mainContainer.setWindow(Main.windowId1);


        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
