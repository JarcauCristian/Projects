module com.example.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;


    opens com.example.sudokusolver to javafx.fxml;
    exports com.example.sudokusolver;
}