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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import model.MainFactory;
import model.PopUpMSG;
import model.threadview.BuilderTextThreadManagement;
import model.threadview.TextThreadViewColor;

/**
 * FXML Controller class
 *
 * I've tried to clean all the code from this class which was not from
 * controller. This makes me create as many classes as functions I see to manage
 * this. The reason why I do this is because this controller was with >800 lines
 * of code and it smells like an endless spaghetti here...
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
     * and consumed) to set its color only
     */
    TextThreadViewColor textThreadView;

    /**
     * It actually manage the starting threads to control the colors from
     * factories
     */
    BuilderTextThreadManagement builderTextThreadManagement;

    //Per mostrar errors o altres
    private PopUpMSG popUpMsg;

    //Prioroties from thread
    private final int MIN_PRIOROTY_THREAD = 1;
    private final int MAX_PRIOROTY_THREAD = 10;

    @FXML
    private Spinner<Integer> engineSpinnerId;
    @FXML
    private Spinner<Integer> batterySpinngerId;
    @FXML
    private Spinner<Integer> wheelSpinngerId;
    @FXML
    private Spinner<Integer> seatSpinngerId;
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
    private Spinner<Integer> carSpinngerId;
    @FXML
    private TextField carText;
    @FXML
    private Button stopBtnCars;
    @FXML
    private Spinner<Integer> stampingSpinngerId;

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
        textThreadView = new TextThreadViewColor(mainFacotry);
        builderTextThreadManagement = new BuilderTextThreadManagement(textThreadView);

        builderTextThreadManagement.windowComponentsInit(carText, engineText, stampingText, batteryText, wheelText, seatText);
        initSpinners();

    }

    private void initSpinners() {

        engineSpinnerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        engineSpinnerId.increment(4);//Started value : from 5 as each thread has per default
        carSpinngerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        carSpinngerId.increment(4);
        batterySpinngerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        batterySpinngerId.increment(4);
        stampingSpinngerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        stampingSpinngerId.increment(4);
        seatSpinngerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        seatSpinngerId.increment(4);
        wheelSpinngerId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_PRIOROTY_THREAD, MAX_PRIOROTY_THREAD));
        wheelSpinngerId.increment(4);
    }

    @FXML
    private void onStopBatteriesClick(MouseEvent event) {
        synchronized (stopBtnBatteries) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void onIncreaseEnginePriority(SwipeEvent event) {
        synchronized (mainFacotry.getEngineBuilderThread()) {
            mainFacotry.getEngineBuilderThread().setPriority(engineSpinnerId.getValue());
            System.out.println(mainFacotry.getEngineBuilderThread().getPriority());
        }
    }

    @FXML
    private void onStopEnginesClick(MouseEvent event) {
        synchronized (stopBtnEngines) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!mainFacotry.getEngineBuilder().isStop()) {
                mainFacotry.getEngineBuilder().setStop(true);
                stopBtnEngines.setText("Restart factory");
            } else {
                mainFacotry.getEngineBuilder().setStop(false);
                stopBtnEngines.setText("Stop factory");

            }
        }
    }

    @FXML
    private void onStopStampingsClick(MouseEvent event) {
        synchronized (stopBtnStampings) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!mainFacotry.getStampingBuilder().isStop()) {
                mainFacotry.getStampingBuilder().setStop(true);
                stopBtnStampings.setText("Restart factory");
            } else {
                mainFacotry.getStampingBuilder().setStop(false);
                stopBtnStampings.setText("Stop factory");

            }
        }
    }

    @FXML
    private void onStopWheelsClick(MouseEvent event) {
        synchronized (stopBtnWheels) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!mainFacotry.getWheelBuilder().isStop()) {
                mainFacotry.getWheelBuilder().setStop(true);
                stopBtnWheels.setText("Restart factory");
            } else {
                mainFacotry.getWheelBuilder().setStop(false);
                stopBtnWheels.setText("Stop factory");

            }
        }
    }

    @FXML
    private void onStopSeatsClick(MouseEvent event) {
        synchronized (stopBtnSeats) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!mainFacotry.getSeatBuilder().isStop()) {
                mainFacotry.getSeatBuilder().setStop(true);
                stopBtnSeats.setText("Restart factory");
            } else {
                mainFacotry.getSeatBuilder().setStop(false);
                stopBtnSeats.setText("Stop factory");

            }
        }
    }

    @FXML
    private void onStopCarsClick(MouseEvent event) {
        synchronized (stopBtnCars) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarFactoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!mainFacotry.getCarBuilder().isStop()) {
                mainFacotry.getCarBuilder().setStop(true);
                stopBtnCars.setText("Restart factory");
            } else {
                mainFacotry.getCarBuilder().setStop(false);
                stopBtnCars.setText("Stop factory");

            }
        }
    }

    @FXML
    private void onEnginePriority(MouseEvent event) {
        synchronized (mainFacotry.getEngineBuilderThread()) {
            mainFacotry.getEngineBuilderThread().setPriority(engineSpinnerId.getValue());
            System.out.println("NEW ENGINE THREAD PRIORITY :: " + mainFacotry.getEngineBuilderThread().getPriority() + "\n");
        }
    }

    @FXML
    private void onStampingPriority(MouseEvent event) {
        synchronized (mainFacotry.getStampingBuilderThread()) {
            mainFacotry.getStampingBuilderThread().setPriority(stampingSpinngerId.getValue());
            System.out.println("NEW  STAMPING THREAD PRIORITY :: " + mainFacotry.getStampingBuilderThread().getPriority() + "\n");
        }
    }

    @FXML
    private void onBatteriesPriority(MouseEvent event) {
        synchronized (mainFacotry.getBatteryBuilderThread()) {
            mainFacotry.getBatteryBuilderThread().setPriority(batterySpinngerId.getValue());
            System.out.println("NEW BATTERY THREAD PRIORITY :: " + mainFacotry.getBatteryBuilderThread().getPriority() + "\n");
        }
    }

    @FXML
    private void onWheelsPriority(MouseEvent event) {
        synchronized (mainFacotry.getWheelBuilderThread()) {
            mainFacotry.getWheelBuilderThread().setPriority(wheelSpinngerId.getValue());
            System.out.println("NEW WHEELS THREAD PRIORITY :: " + mainFacotry.getWheelBuilderThread().getPriority() + "\n");
        }
    }

    @FXML
    private void onSeatsPriority(MouseEvent event) {
        synchronized (mainFacotry.getSeatBuilderThread()) {
            mainFacotry.getSeatBuilderThread().setPriority(seatSpinngerId.getValue());
            System.out.println("NEW SEATS THREAD PRIORITY :: " + mainFacotry.getSeatBuilderThread().getPriority() + "\n");
        }
    }

    @FXML
    private void onCarsPriority(MouseEvent event) {
        synchronized (mainFacotry.getCarBuilderThread()) {
            mainFacotry.getCarBuilderThread().setPriority(carSpinngerId.getValue());
            System.out.println("NEW CARS THREAD PRIORITY :: " + mainFacotry.getCarBuilderThread().getPriority() + "\n");
        }
    }

}
