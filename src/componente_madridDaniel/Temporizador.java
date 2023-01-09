/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componente_madridDaniel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author Daniel Madrid
 */
public class Temporizador extends HBox implements Initializable {
    private IntegerProperty tiempoRestante;
    private Timeline temporizador = new Timeline(); 
    
    @FXML private Label tiempo;
    
    private ObjectProperty<EventHandler<ActionEvent>> OnAction = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    private EventHandler onFinished = (EventHandler<ActionEvent>) (ActionEvent event) -> {
        System.out.println("EL TEMPORIZADOR HA LLEGADO A 0");
    } ;
    
    public Temporizador() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    private void establecerTiempo() {

        tiempo.textProperty().set(tiempoRestante.toString());
        tiempo.textProperty().bind(tiempoRestanteProperty().asString());
        temporizador.setCycleCount(1);
        temporizador.setAutoReverse(false);
        final KeyValue kv = new KeyValue(tiempoRestante, 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(tiempoRestante.getValue()*1000), onFinished, kv);
        //tiempo.setText(tiempoRestante.getValue());
        
        /*KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                tiempoRestante--;
                tiempo.setText(tiempoRestante.toString());
                if(tiempoRestante<=0){
                    temporizador.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("EL TEMPORIZADOR HA LLEGADO A 0");
                    alert.show();
                }
            }
        });
        */
        temporizador.getKeyFrames().add(kf);
        //temporizador.play();
    }

    public IntegerProperty tiempoRestanteProperty() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiemp) {
        this.tiempoRestante.set(tiemp);
    }

    public ObjectProperty<EventHandler<ActionEvent>> getOnAction() {
        return OnAction;
    }

    public void setOnAction(ObjectProperty<EventHandler<ActionEvent>> OnAction) {
        this.OnAction = OnAction;
    }

    public EventHandler getOnFinished() {
        return onFinished;
    }

    public void setOnFinished(EventHandler onFinished) {
        this.onFinished = onFinished;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tiempoRestante = new SimpleIntegerProperty(25);
        
    }    
    public void iniciarTemporizador(){
        establecerTiempo();
        
        temporizador.play();
        //tiempo.setText(tiempoRestanteProperty().getValue().toString());
    }
}