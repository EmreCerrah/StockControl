package emre.Stock;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewProdController implements Initializable {

@FXML
    public ListView <Item> itemList;
    @FXML
    public ListView <Item> productList;
    @FXML
    public Label productPeiceNumber;
    @FXML
    public Label stockPieceNumber;
    @FXML
    public TextField addedNumber;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemList.setItems(DataStock.getInstance().getItemList());

        itemList.getSelectionModel().selectedItemProperty().addListener((observableValue, item, t1) ->
                stockPieceNumber.setText(itemList.getSelectionModel().getSelectedItem().getPieceString()));
        productList.getSelectionModel().selectedItemProperty().addListener((observableValue, item, t1) ->
                productPeiceNumber.setText(productList.getSelectionModel().getSelectedItem().getPieceString()));

}
    @FXML
    public void addToProductList(ActionEvent actionEvent) {

        String name = itemList.getSelectionModel().getSelectedItem().getName();
        String strNumber = addedNumber.getText();
        int number = isValid(strNumber);
        if (number>itemList.getSelectionModel().getSelectedItem().getPieceint()) {
        wrongEntry();
        return;
        }else if(number != 0) {
            productList.getItems().add(new Item(name, null, number));
        }
    }
    @FXML
    public void removeToProductList(ActionEvent actionEvent) {
        Item currentItem= productList.getSelectionModel().getSelectedItem();
        productList.getItems().remove(currentItem);
       // productList.getItems().remove(itemList.getSelectionModel().getSelectedItem());

    }
    private void wrongEntry() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Emin Misiniz?");
        alert.setHeaderText("Lütfen sadece sayı giriniz.");
        Optional<ButtonType> result = alert.showAndWait();

    }
    public int isValid(String text) {
        try {
            int i = Integer.parseInt(text);
            return i;
        } catch (NumberFormatException e) {
            wrongEntry();
            return 0;
        }
    }

}
