package github.josedoce;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import github.josedoce.App.ActionApplication;
import github.josedoce.tarefas.FiboCalcTarefa;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {
    private static int contador = 0;
    private static ScheduledExecutorService executor = null;
    public static boolean isTarefaFinalizada = false;
    public static AtomicReference<String> reference = new AtomicReference<>("");
    @FXML
    Label status;

    @FXML
    Label painel;

    @FXML Button fiboButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void iniciarFibonacci() {
         
        
        try{
            int valor = 10;
            
            executor = Executors.newScheduledThreadPool(2);
            FiboCalcTarefa tarefa = new FiboCalcTarefa(valor);
            //quando a tarefa finalizar, o valor será emitidos na UI
            status.textProperty().bind(tarefa.messageProperty());
    
            //quando a tarefa começar
            tarefa.setOnRunning((successEvent)->{
                fiboButton.setDisable(true);
                painel.setText("");
            });
    
            //quando a tarefa terminar
            tarefa.setOnSucceeded((successedEvent)->{
                isTarefaFinalizada = true;
                painel.setText(tarefa.getValue().toString());
                fiboButton.setDisable(false);
            });
            
            executor.execute(tarefa);
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        //https://genuinecoder.com/javafx-observables-and-bindings/
                        status.textProperty().bind(new SimpleStringProperty("Tempo em execução-"+contador));
                    });
                    contador++;
                }

                
            }, 0, 2, TimeUnit.SECONDS);
            
            
        }catch(Exception e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
            painel.setText("Operação falhou.");
        }finally{
            if(executor != null){
                App.on(new ActionApplication() {
                    @Override
                    public void onCloseApplication() {
                        executor.shutdown();
                        
                    }
                });
            }
        }
    }
}
