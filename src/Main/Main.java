package Main;

import Main.Model.Block;
import Main.Model.Blockchain;
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

        /*Blockchain blockchain = new Blockchain();
        Block block = new Block(blockchain.getLastBlock().getCurrHash());
        block.calculateNonceAndHash();
        blockchain.addBlock(block);
        System.out.println(blockchain);*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
