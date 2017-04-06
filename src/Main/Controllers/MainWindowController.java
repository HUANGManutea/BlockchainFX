package Main.Controllers;

import Main.Model.Block;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable{
    @FXML
    private VBox mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Block block = new Block("0");
        mainContainer.getChildren().add(new BlockView(new SimpleObjectProperty<Block>(block)));
    }
}
