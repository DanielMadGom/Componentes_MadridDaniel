package componente_madridDaniel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Daniel Madrid
 */
public class CampoTextoBoton extends HBox implements Initializable {
    @FXML private TextField textField;
    @FXML
    private Button botonGrabar;
    
       private ObjectProperty<EventHandler<ActionEvent>> OnAction =
            new SimpleObjectProperty<EventHandler<ActionEvent>>();

    public CampoTextoBoton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CampoTextoBoton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        botonGrabar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                onActionProperty().get().handle(event);
            }
        });
    }
    
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return OnAction;
    }
    
    public final void setOnAction(EventHandler<ActionEvent> handler) {
        OnAction.set(handler);
    }
    
    public final EventHandler<ActionEvent> getOnAction() {
        return OnAction.get();
    }
    
    public String getText() {
        return textProperty().get();
    }
    
    public void setText(String value) {
        textProperty().set(value);
    }
    
    public StringProperty textProperty() {
        return textField.textProperty();                
    }      
}