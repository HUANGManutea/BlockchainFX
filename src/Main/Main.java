package Main;

import Main.Model.Block;
import Main.Model.Blockchain;
import Main.Model.Miner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        /*
        Blockchain blockchain = new Blockchain();
        Miner miner = new Miner(blockchain);
        miner.createNewBlock();
        miner.mine();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
