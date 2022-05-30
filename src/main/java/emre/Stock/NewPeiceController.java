package emre.Stock;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class NewPeiceController {

    @FXML
    private TextArea peiceDetail;

    @FXML
    private TextField peiceName;
    @FXML
    private TextField pieceNumber;



    public Item addMessage() {
        String name= peiceName.getText().trim().toUpperCase();
        String detail=peiceDetail.getText().trim();

        String text = pieceNumber.getText();
        System.out.println(isValid(text));
        int current = isValid(text);


        Item currentItem= new Item(name,detail,current);
        DataStock.getInstance().addNewItem(currentItem);
        return currentItem;
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
    private void wrongEntry() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Emin Misiniz?");
        alert.setHeaderText("Lütfen sadece sayı giriniz.");
        Optional<ButtonType> result = alert.showAndWait();

    }
    }

