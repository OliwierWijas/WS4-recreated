module dk.via.ws4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.via.ws4 to javafx.fxml;
    exports dk.via.ws4;
    exports dk.via.ws4.sockets;
    opens dk.via.ws4.sockets to javafx.fxml;
    exports dk.via.ws4.sockets.ex1;
    opens dk.via.ws4.sockets.ex1 to javafx.fxml;
}