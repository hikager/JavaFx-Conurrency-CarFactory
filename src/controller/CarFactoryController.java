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

    /**
     * Initializes the controller class components I will be using along the
     * execution.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
