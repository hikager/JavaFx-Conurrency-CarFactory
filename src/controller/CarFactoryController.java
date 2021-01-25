/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Person;
import model.PopUpMSG;
import model.factory.CarFactory;
import model.factoryImp.BatteryBuilder;
import model.factoryImp.CarBuilder;

/**
 * FXML Controller class for M15-JAVAFX-DAVID.2020-2021.DURINGCOVID
 *
 * @author LuisDAM
 */
public class CarFactoryController implements Initializable {

    //Runnables
    private CarBuilder carBuilder;
    private BatteryBuilder batteryBuilder;

    //Threads
    private Thread carBuilderThread;
    private Thread batteryBuilderThread;

    //Windows threads
    private Thread carBuilderTextThread;
    private Thread batteryBuilderTextThread;
    private Thread batteryBuilderStopButtonThread;

    //Per mostrar errors o altres
    private PopUpMSG popUpMsg;
    
    @FXML
    private Spinner<?> engineSpinnerId;
    @FXML
    private Spinner<?> stappingSpinngerId;
    @FXML
    private Spinner<?> batterySpinngerId;
    @FXML
    private Spinner<?> wheelSpinngerId;
    @FXML
    private Spinner<?> seatSpinngerId;
    @FXML
    private TextField engineText;
    @FXML
    private TextField stampingText;
    @FXML
    private TextField batteryText;
    @FXML
    private TextField wheelText;
    @FXML
    private TextField seatText;
    @FXML
    private Button stopBtnEngines;
    @FXML
    private Button stopBtnStampings;
    @FXML
    private Button stopBtnBatteries;
    @FXML
    private Button stopBtnWheels;
    @FXML
    private Button stopBtnSeats;
    @FXML
    private Spinner<?> carSpinngerId;
    @FXML
    private TextField carText;
    @FXML
    private Button stopBtnCars;

    /**
     * Initializes the controller class components I will be using along the
     * execution.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        batteryBuilder = new BatteryBuilder("batteryBuilder", new ArrayList<Integer>());
        carBuilder = new CarBuilder(batteryBuilder);
        buildersInit();
        windowComponentsInit();
    }
    
    private void buildersInit() {
        this.carBuilderThread = new Thread(this.carBuilder, "car-builder-thread");
        this.batteryBuilderThread = new Thread(this.batteryBuilder, "battery-builder-thread");
        carBuilderThread.start();
        batteryBuilderThread.start();
        
    }
    
    private void windowComponentsInit() {
        
        this.carBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    while (true) {
                        System.out.println("CAR TEXT");
                        Thread.sleep(500);
                        carTextSync(); //no synchronize because it wont work
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        },
                "car-text-builder-thread");
        
        this.batteryBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("battery text");
                        Thread.sleep(1000);
                        batteryTextSync();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }, "battery-text-builder-thread");
        
        carBuilderTextThread.start();
        
        batteryBuilderTextThread.start();
    }
    
    private void carTextSync() throws InterruptedException {
        synchronized (carBuilder) {
            carText.setText("" + carBuilder.getPiecesList().size());
            System.out.println(carText.getText());
            // this.carBuilder.getPiecesList().notify();
        }
        
    }
    
    private void batteryTextSync() throws InterruptedException {
        synchronized (batteryBuilder) {
            batteryText.setText("" + batteryBuilder.getPiecesList().size());
            System.out.println(batteryText.getText());
            // batteryBuilder.getPiecesList().notify();
        }
    }
    
    @FXML
    private void onStopBatteriesClick(MouseEvent event) {
        
        System.out.println("BUTTTTONNNNNNNNNNN\n\n\n");
        
        if (batteryBuilder.isKeepProducingUnderButton()) {
            batteryBuilder.setKeepProducingUnderButton(false);
            stopBtnBatteries.setText("Restart factory");
        }else{
               batteryBuilder.setKeepProducingUnderButton(true);
               stopBtnBatteries.setText("Stop factory");
        }
    }
    
}
