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
import model.threadview.TextThreadView;

/**
 * FXML Controller class
 *
 * @author LuisDAM
 */
public class CarFactoryController implements Initializable {

    /**
     * Main factory which manage piece production and car building
     */
    MainFactory mainFacotry;

    /**
     * Manage text threads from view (those which show up the pieces produced
     * and consumed)
     */
    TextThreadView textThreadView;

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
        textThreadView = new TextThreadView(mainFacotry);

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

        this.carBuilderTextThread = new Thread(() -> {
            Runnable carText1 = () -> {
                System.out.println("CAR TEXT");
                //carTextSync(); //no synchronize because it wont work
                textThreadView.carTextSync(carText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(carText1);
            }
        });

        this.WheelBuilderTextThread = new Thread(() -> {
            Runnable wheelText1 = () -> {
                System.out.println("WHEEL TEXT");
                // wheelTextSync(); //no synchronize because it wont work
                textThreadView.wheelTextSync(wheelText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(wheelText1);
            }
        });

        this.batteryBuilderTextThread = new Thread(() -> {
            Runnable batteryText1 = () -> {
                System.out.println("BATTERY TEXT");
                // batteryTextSync(); //no synchronize because it wont work
                textThreadView.batteryTextSync(batteryText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(batteryText1);
            }
        });

        this.seatBuilderTextThread = new Thread(() -> {
            Runnable seatText1 = () -> {
                System.out.println("BATTERY TEXT");
                //seatTextSync(); //no synchronize because it wont work
                textThreadView.seatTextSync(seatText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(seatText1);
            }
        });

        this.engineBuilderTextThread = new Thread(() -> {
            Runnable engineText1 = () -> {
                System.out.println("ENGINE TEXT");
                // engineTextSync(); //no synchronize because it wont work
                textThreadView.engineTextSync(engineText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(engineText1);
            }
        });

        this.stampingBuilderTextThread = new Thread(() -> {
            Runnable stampingText1 = () -> {
                System.out.println("ENGINE TEXT");
                // stampingTextSync(); //no synchronize because it wont work
                textThreadView.stampingTextSync(stampingText);
            };
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(stampingText1);
            }
        });

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
