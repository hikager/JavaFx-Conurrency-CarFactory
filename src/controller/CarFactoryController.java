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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;

import model.PopUpMSG;

import model.factoryImp.BatteryBuilder;
import model.factoryImp.CarBuilder;
import model.factoryImp.EngineBuilder;

/**
 * FXML Controller class for M15-JAVAFX-DAVID.2020-2021.DURINGCOVID
 *
 * @author LuisDAM
 */
public class CarFactoryController implements Initializable {

    private final String styleWhenIsWorking = " -fx-background-color:  #9cff33;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsNotWorking = " -fx-background-color:  #e10303;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsBeingStop = " -fx-background-color:  #f19518;  -fx-text-fill: #000000 ;-fx-alignment:center;";

    //Runnables
    private CarBuilder carBuilder;
    private BatteryBuilder batteryBuilder;
    private EngineBuilder engineBuilder;

    //Threads
    private Thread carBuilderThread;
    private Thread batteryBuilderThread;
    private Thread engineBuilderThread;

    //Windows threads
    private Thread carBuilderTextThread;
    private Thread batteryBuilderTextThread;
    private Thread engineBuilderTextThread;
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
        engineBuilder = new EngineBuilder();
        carBuilder = new CarBuilder(batteryBuilder, engineBuilder);

        buildersInit();
        windowComponentsInit();
        //    stopFactory();
    }

    private void buildersInit() {
        this.carBuilderThread = new Thread(this.carBuilder, "car-builder-thread");
        this.batteryBuilderThread = new Thread(this.batteryBuilder, "battery-builder-thread");
        engineBuilderThread = new Thread(this.engineBuilder, "engine-builder-thread");
        carBuilderThread.start();
        batteryBuilderThread.start();
        engineBuilderThread.start();

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
//engineBuilderTextThread

        this.engineBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("battery text");
                        Thread.sleep(1000);
                        engineTextSync();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }, "battery-text-builder-thread");
        carBuilderTextThread.start();

        batteryBuilderTextThread.start();

        engineBuilderTextThread.start();
    }

    private void carTextSync() throws InterruptedException {
        synchronized (carBuilder.getCars()) {
            carText.setText("" + carBuilder.getCars());

            if (carBuilder.canConsume()) {
                carText.setStyle(styleWhenIsWorking);
            } else {
                carText.setStyle(styleWhenIsNotWorking);
            }
            System.out.println(carText.getText());
            // this.carBuilder.getPiecesList().notify();
        }

    }

    private void batteryTextSync() throws InterruptedException {
        synchronized (batteryBuilder.getPieces()) {
            batteryText.setText("" + batteryBuilder.getPieces());

            if (!batteryBuilder.isStop()) {
                if (batteryBuilder.canProduce()) {
                    batteryText.setStyle(styleWhenIsWorking);
                } else {
                    batteryText.setStyle(styleWhenIsNotWorking);
                }
            } else {
                batteryText.setStyle(styleWhenIsBeingStop);
            }

            System.out.println(batteryText.getText());
            // batteryBuilder.getPiecesList().notify();
        }
    }

    private void engineTextSync() throws InterruptedException {
        synchronized (engineBuilder.getPieces()) {
            engineText.setText("" + engineBuilder.getPieces());

            if (!engineBuilder.isStop()) {
                if (engineBuilder.canProduce()) {
                    engineText.setStyle(styleWhenIsWorking);
                } else {
                    engineText.setStyle(styleWhenIsNotWorking);
                }
            } else {
                engineText.setStyle(styleWhenIsBeingStop);
            }

            System.out.println(engineText.getText());
            // batteryBuilder.getPiecesList().notify();
        }
    }

    @FXML
    private void onStopBatteriesClick(MouseEvent event) throws InterruptedException {
        synchronized (stopBtnBatteries) {
            Thread.sleep(200);
            if (!batteryBuilder.isStop()) {
                batteryBuilder.setStop(true);
                stopBtnBatteries.setText("Restart factory");
                // stopBtnBatteries.notify();
            } else {
                batteryBuilder.setStop(false);
                stopBtnBatteries.setText("Stop factory");
                //  stopBtnBatteries.notify();
            }
        }

    }

}
