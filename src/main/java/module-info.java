module com.example.vyskomapa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vyskomapa to javafx.fxml;
    exports com.example.vyskomapa;
}