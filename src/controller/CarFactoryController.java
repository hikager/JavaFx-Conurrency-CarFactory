/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import model.MainFactory;

import model.PopUpMSG;

/**
 * FXML Controller class for M15-JAVAFX-DAVID.2020-2021.DURINGCOVID
 *
 * @author LuisDAM
 */
public class CarFactoryController implements Initializable {

    /**
     * Main factory which manage piece production and car building
     */
    MainFactory mainFacotry;

    //Styles for each factory 
    private final String styleWhenIsWorking = " -fx-background-color:  #9cff33;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsNotWorking = " -fx-background-color:  #e10303;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsBeingStop = " -fx-background-color:  #f19518;  -fx-text-fill: #000000 ;-fx-alignment:center;";

    //Windows threads
    private Thread carBuilderTextThread;
    private Thread batteryBuilderTextThread;
    private Thread engineBuilderTextThread;
    private Thread seatBuilderTextThread;
    private Thread stampingBuilderTextThread;
    private Thread WheelBuilderTextThread;
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
        mainFacotry = new MainFactory();

        windowComponentsInit();
        //    stopFactory();
    }

    /**
     * <h1>Adding Jordi-s Examaples for: Threads independents en la vista.</h1>
     *
     * This solve my problem with:
     *
     * "JavaFX Application Thread" ArrayIndexOutOfBoundsException
     *
     * This errors happens sometimes when our UI (javafx engine) ends up without
     * threads.
     *
     * Also a good source I found (complementing Jordi good solution) :
     * <p>
     * Answer from Guillaume Poussel only</p>
     * <a href="https://stackoverflow.com/questions/19755031/how-javafx-application-thread-works">
     * Link-StackOverFlow</a>
     */
    private void windowComponentsInit() {

        this.carBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable carText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("CAR TEXT");
                        carTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(carText);
                }
            }
        }
        );

        this.WheelBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable wheelText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("WHEEL TEXT");
                        wheelTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(wheelText);
                }
            }
        }
        );

        this.batteryBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable batteryText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("BATTERY TEXT");
                        batteryTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(batteryText);
                }
            }
        }
        );

        this.seatBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable seatText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("BATTERY TEXT");
                        seatTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(seatText);
                }
            }
        }
        );

        this.engineBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable engineText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ENGINE TEXT");
                        engineTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(engineText);
                }
            }
        }
        );

        this.stampingBuilderTextThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable stampingText = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ENGINE TEXT");
                        stampingTextSync(); //no synchronize because it wont work
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(stampingText);
                }
            }
        }
        );

        //Why setting deamons these threads?
        //
        carBuilderTextThread.setDaemon(true);
        carBuilderTextThread.start();

        batteryBuilderTextThread.setDaemon(true);
        batteryBuilderTextThread.start();

        seatBuilderTextThread.setDaemon(true);
        seatBuilderTextThread.start();

        engineBuilderTextThread.setDaemon(true);
        engineBuilderTextThread.start();

        stampingBuilderTextThread.setDaemon(true);
        stampingBuilderTextThread.start();

        WheelBuilderTextThread.setDaemon(true);
        WheelBuilderTextThread.start();

    }

    private void carTextSync() {

        synchronized (mainFacotry.getCarBuilder().getCars()) {
            carText.setText("" + mainFacotry.getCarBuilder().getCars());

            if (mainFacotry.getCarBuilder().canConsume()) {
                carText.setStyle(styleWhenIsWorking);
            } else {
                carText.setStyle(styleWhenIsNotWorking);
            }
            System.out.println(carText.getText());
            // this.carBuilder.getPiecesList().notify();
        }

    }

    private void wheelTextSync() {

        synchronized (mainFacotry.getWheelBuilder().getPieces()) {
            wheelText.setText("" + mainFacotry.getWheelBuilder().getPieces());
        }

        synchronized (mainFacotry.getWheelBuilder().getPieces()) {
            if (!mainFacotry.getWheelBuilder().isStop()) {
                if (mainFacotry.getWheelBuilder().canProduce()) {
                    wheelText.setStyle(styleWhenIsWorking);
                } else {
                    wheelText.setStyle(styleWhenIsNotWorking);
                }
            } else {
                wheelText.setStyle(styleWhenIsBeingStop);
            }
        }
        System.out.println(wheelText.getText());
        // batteryBuilder.getPiecesList().notify();

    }

    private void batteryTextSync() {

        synchronized (mainFacotry.getBatteryBuilder().getPieces()) {
            batteryText.setText("" + mainFacotry.getBatteryBuilder().getPieces());
        }

        synchronized (mainFacotry.getBatteryBuilder().getPieces()) {
            if (!mainFacotry.getBatteryBuilder().isStop()) {
                if (mainFacotry.getBatteryBuilder().canProduce()) {
                    batteryText.setStyle(styleWhenIsWorking);
                } else {
                    batteryText.setStyle(styleWhenIsNotWorking);
                }
            } else {
                batteryText.setStyle(styleWhenIsBeingStop);
            }
        }
        System.out.println(batteryText.getText());
        // batteryBuilder.getPiecesList().notify();

    }

    private void engineTextSync() {

        synchronized (mainFacotry.getEngineBuilder().getPieces()) {
            engineText.setText("" + mainFacotry.getEngineBuilder().getPieces());

            if (!mainFacotry.getEngineBuilder().isStop()) {
                if (mainFacotry.getEngineBuilder().canProduce()) {
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

    private void seatTextSync() {

        synchronized (mainFacotry.getSeatBuilder().getPieces()) {
            seatText.setText("" + mainFacotry.getSeatBuilder().getPieces());

            if (!mainFacotry.getSeatBuilder().isStop()) {
                if (mainFacotry.getSeatBuilder().canProduce()) {
                    seatText.setStyle(styleWhenIsWorking);
                } else {
                    seatText.setStyle(styleWhenIsNotWorking);
                }
            } else {
                seatText.setStyle(styleWhenIsBeingStop);
            }

            System.out.println(seatText.getText());
            // batteryBuilder.getPiecesList().notify();
        }
    }

    private void stampingTextSync() {

        synchronized (mainFacotry.getStampingBuilder().getPieces()) {
            stampingText.setText("" + mainFacotry.getStampingBuilder().getPieces());
        }
        synchronized (mainFacotry.getStampingBuilder().getPieces()) {
            //1st test if can produce
            if (mainFacotry.getStampingBuilder().canProduce()) {
                //2n test if it's not stopped
                if (!mainFacotry.getStampingBuilder().isStop()) {
                    stampingText.setStyle(styleWhenIsWorking);
                } else {
                    stampingText.setStyle(styleWhenIsBeingStop);
                }
            } else {
                stampingText.setStyle(styleWhenIsNotWorking);
            }
            System.out.println(stampingText.getText());
            // batteryBuilder.getPiecesList().notify();
        }
    }

    @FXML
    private void onStopBatteriesClick(MouseEvent event) throws InterruptedException {
        synchronized (stopBtnBatteries) {
            Thread.sleep(200);
            if (!mainFacotry.getBatteryBuilder().isStop()) {
                mainFacotry.getBatteryBuilder().setStop(true);
                stopBtnBatteries.setText("Restart factory");
                // stopBtnBatteries.notify();
            } else {
                mainFacotry.getBatteryBuilder().setStop(false);
                stopBtnBatteries.setText("Stop factory");
                //  stopBtnBatteries.notify();
            }
        }

    }

}
