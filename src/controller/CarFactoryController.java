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
import model.threadview.BuilderTextThreadManagement;
import model.threadview.TextThreadViewColor;

/**
 * FXML Controller class
 *
 * I've tried to clean all the code from this class which was not from
 * controller. This makes me create as many classes as functions I see to manage this. The reason why
 * I do this is because this controller was with >800 lines of code and it
 * smells like an endless spaghetti here...
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
        textThreadView = new TextThreadViewColor(mainFacotry);
        builderTextThreadManagement = new BuilderTextThreadManagement(textThreadView);

        builderTextThreadManagement.windowComponentsInit(carText, engineText, stampingText, batteryText, wheelText, seatText);
     
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
