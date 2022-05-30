package emre.Stock;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public ListView<Item> itemList;


    @FXML
    public TextField fuckSpinner;
    @FXML
    public Label buildLabel;
    @FXML
    public Button buildProduct;


    @FXML
    private TextArea itemDetail;
    @FXML
    private MenuItem deleteMenu;

    @FXML
    private Label numberOfPeice;

    @FXML
    public ListView<Item> productList;

    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemDetail.setEditable(false);
        //List of items.
        itemList.setItems(DataStock.getInstance().getItemList());
        itemList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observableValue, Item item, Item t1) {
                itemDetail.setText(itemList.getSelectionModel().getSelectedItem().getDetails());
                numberOfPeice.setText(itemList.getSelectionModel().getSelectedItem().getPieceString());
            }
        });

        // Value factory.

        //

        deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Item currentItem = itemList.getSelectionModel().getSelectedItem();
                selectedDelete(currentItem);
            }
        });

        itemList.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
            @Override
            public ListCell<Item> call(ListView<Item> itemListView) {
                ListCell<Item> newItemList = new ListCell<>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        //her satıra özel özellik atamak için
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);

                        } else if (item.getPieceint() < 20) {
                            setText(item.getName());
                            setTextFill(Color.RED);
                        } else {
                            setText(item.getName());
                            setTextFill(Color.BLACK);
                        }
                    }
                };
                return newItemList;
            }
        });
        buildProduct.setDisable(true);
        progressBar.setVisible(false);
    }


    @FXML
    private BorderPane mainPage;

    @FXML
    public void newPiece(ActionEvent actionEvent) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("peiceAddDialog.fxml"));

        dialog.setTitle("Yeni Parça Ekle");
        dialog.getDialogPane().setContent(fxmlLoader.load());


        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == ButtonType.APPLY) {
            NewPeiceController dialogController = fxmlLoader.getController();
            Item addedItem = dialogController.addMessage();
            itemList.getSelectionModel().select(addedItem);
        }

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
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

    public void newProduct(ActionEvent actionEvent) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("prodAddDialog.fxml"));

        dialog.setTitle("Yeni Ürün Ekle");
        dialog.getDialogPane().setContent(fxmlLoader.load());


        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == ButtonType.APPLY) {
            try {
                //DataProduct.getInstance().saveItems();
                NewProdController dialogController = fxmlLoader.getController();
                productList = dialogController.productList;
                buildProduct.setDisable(false);
                buildLabel.setText("Ürün üretilmeye hazır");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ürün eklenemedi");
            }
        }

    }

    public void printPDF(ActionEvent actionEvent) {
        String str = DataStock.getInstance().getItemListString();
        Text text = new Text();
        text.setText(str);
        text.setWrappingWidth(58000);
        text.setX(15.0);
        text.setY(15.0);

        Group root = new Group(text);
        Scene scene = new Scene(root);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.showPrintDialog(scene.getWindow());
            job.printPage(text);
            job.endJob();

        }

    }


    public void increasePiece(ActionEvent actionEvent) {
        String text = fuckSpinner.getText();
        System.out.println(isValid(text));
        int current = isValid(text);
        itemList.getSelectionModel().getSelectedItem().addPiece(current);
        numberOfPeice.setText(itemList.getSelectionModel().getSelectedItem().getPieceString());
    }

    public void decreasePiece(ActionEvent actionEvent) {
        String text = fuckSpinner.getText();
        System.out.println(isValid(text));
        int current = isValid(text);
        itemList.getSelectionModel().getSelectedItem().removePiece(current);
        Item currentItem = itemList.getSelectionModel().getSelectedItem();
        if (0 >= itemList.getSelectionModel().getSelectedItem().getPieceint()) {
            selectedDelete(currentItem);
        } else {
            numberOfPeice.setText(itemList.getSelectionModel().getSelectedItem().getPieceString());
        }
    }

    private void selectedDelete(Item currentItem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Emin Misiniz?");
        alert.setHeaderText("Silinecek Parça: " + currentItem.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            DataStock.getInstance().deleteItem(currentItem);
        }
    }

    private void wrongEntry() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Emin Misiniz?");
        alert.setHeaderText("Lütfen sadece sayı giriniz.");
        Optional<ButtonType> result = alert.showAndWait();

    }

    public void buildProductAction(ActionEvent actionEvent) {
        progressBar.setVisible(true);
        Iterator<Item> iteratorPruduct = productList.getItems().listIterator();

        while (iteratorPruduct.hasNext()) {
            Item current = iteratorPruduct.next();

            for (Item check : itemList.getItems()) {
                //Item check=iteratorItems.next();
                if (check.getName() == current.getName()) {
                    check.removePiece(current.getPieceint());
                    if (0 >= check.getPieceint()) {
                        selectedDelete(check);
                    }
                }
            }
        }
        progressBar.setVisible(false);
        buildProduct.setDisable(true);
        itemList.setItems(DataStock.getInstance().getItemList());
        numberOfPeice.setText(itemList.getSelectionModel().getSelectedItem().getPieceString());
    }
}