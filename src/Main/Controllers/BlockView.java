package Main.Controllers;

import Main.Model.Block;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Manutea on 07/04/2017.
 */
public class BlockView extends AnchorPane{
    @FXML
    private TextField previousHashField;
    @FXML
    private TextField nonceField;
    @FXML
    private TextField currentHashField;
    @FXML
    private Button mineButton;
    @FXML
    private TableView<String> dataTable;
    @FXML
    private TableColumn<String,Integer> numberColumn;
    @FXML
    private TableColumn<String,String> dataColumn;

    private ObjectProperty<Block> _block;

    public TextField getPreviousHashField() {
        return previousHashField;
    }

    public void setPreviousHashField(TextField previousHashField) {
        this.previousHashField = previousHashField;
    }

    public TextField getNonceField() {
        return nonceField;
    }

    public void setNonceField(TextField nonceField) {
        this.nonceField = nonceField;
    }

    public TextField getCurrentHashField() {
        return currentHashField;
    }

    public void setCurrentHashField(TextField currentHashField) {
        this.currentHashField = currentHashField;
    }

    public Button getMineButton() {
        return mineButton;
    }

    public void setMineButton(Button mineButton) {
        this.mineButton = mineButton;
    }

    public TableView<String> getDataTable() {
        return dataTable;
    }

    public void setDataTable(TableView<String> dataTable) {
        this.dataTable = dataTable;
    }

    public TableColumn<String, Integer> getNumberColumn() {
        return numberColumn;
    }

    public void setNumberColumn(TableColumn<String, Integer> numberColumn) {
        this.numberColumn = numberColumn;
    }

    public TableColumn<String, String> getDataColumn() {
        return dataColumn;
    }

    public void setDataColumn(TableColumn<String, String> dataColumn) {
        this.dataColumn = dataColumn;
    }

    public BlockView(ObjectProperty<Block> block){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/blockView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            _block = block;
            init();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void init(){
        System.out.println("AAAAAAAAAAAAAAAAA"+_block);
        previousHashField.textProperty().bind(_block.getValue().prevHashProperty());

        nonceField.textProperty().bind(_block.getValue().nonceProperty().asString());

        currentHashField.textProperty().bind(_block.getValue().currHashProperty());

        mineButton.setOnAction(this::calculateHash);

        dataTable.setItems(_block.getValue().getDatas());

        numberColumn.setCellValueFactory(data -> new SimpleObjectProperty<Integer>(dataTable.getItems().indexOf(data)));

        dataColumn.setCellValueFactory(data -> new SimpleObjectProperty<String>(data.getValue()));
    }

    public void calculateHash(Event event){
        try {
            _block.getValue().calculateNonceAndHash();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
