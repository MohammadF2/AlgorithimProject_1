module com.example.algorithimproject_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algorithimproject_1 to javafx.fxml;
    exports com.example.algorithimproject_1;
}