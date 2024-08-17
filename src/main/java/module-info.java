module ec.edu.espol.proyectoed.p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires java.net.http;
    requires org.json;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;

    opens ec.edu.espol.proyectoed.p.controller to javafx.fxml;
    
    exports ec.edu.espol.proyectoed.p.controller;    
    exports ec.edu.espol.proyectoed.p;
}
