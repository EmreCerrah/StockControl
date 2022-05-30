module StockControl {
    requires javafx.controls;
    requires javafx.fxml;


    opens emre.Stock to javafx.fxml;
    exports emre.Stock;
}