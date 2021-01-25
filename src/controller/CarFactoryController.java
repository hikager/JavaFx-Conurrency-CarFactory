/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class for M15-JAVAFX-DAVID.2020-2021.DURINGCOVID
 *
 * @author LuisDAM
 */
public class CarFactoryController implements Initializable {

    //Definim un atribut que serà una especie Array pròpia de JavaFX que es diu ObservableList.
    private ObservableList<Person> personas;
    //Per mostrar errors o altres
    private PopUpMSG popUpMsg;

    private TextField txtName;
    private TextField txtFamilyName;
    private TextField txtAge;
    private TableColumn colName;
    private TableColumn colFamilyName;
    private TableColumn colAge;
    private TableView<Person> tblPersona;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
