/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threadview;

import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.MainFactory;

/**
 *
 * @author Luis ML
 */
public class TextThreadViewColor {

    //Styles for each factory 
    private final String styleWhenIsWorking = " -fx-background-color:  #9cff33;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsNotWorking = " -fx-background-color:  #e10303;  -fx-text-fill: #ffffff;-fx-alignment:center;";
    private final String styleWhenIsBeingStop = " -fx-background-color:  #f19518;  -fx-text-fill: #000000 ;-fx-alignment:center;";

    //Map to manage all threads
    private MainFactory mainFacotry;

    public TextThreadViewColor(MainFactory mainFacotry) {
        this.mainFacotry = mainFacotry;
    }

    /**
     * It makes the color change from car-text factory about the stock it has in
     * that moment.
     *
     * @param carText
     */
    public void carTextSync(TextField carText) {

        synchronized (mainFacotry.getCarBuilder().getCars()) {
            carText.setText("" + mainFacotry.getCarBuilder().getCars());

            if (!mainFacotry.getCarBuilder().isStop()) {
                if (mainFacotry.getCarBuilder().canConsume()) {
                    carText.setStyle(styleWhenIsWorking);
                } else {
                    carText.setStyle(styleWhenIsNotWorking);
                }
            }else{
               carText.setStyle(styleWhenIsBeingStop);
            }
            System.out.println(carText.getText());
            // this.carBuilder.getPiecesList().notify();
        }
    }

    /**
     * It makes the color change from wheel-text factory about the stock it has
     * in that moment.
     *
     * @param wheelText
     */
    public void wheelTextSync(TextField wheelText) {

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

    /**
     * It makes the color change from Battery-text factory about the stock it
     * has in that moment.
     *
     * @param batteryText
     */
    public void batteryTextSync(TextField batteryText) {

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

    /**
     * It makes the color change from engine-text factory about the stock it has
     * in that moment.
     *
     * @param engineText
     */
    public void engineTextSync(TextField engineText) {

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

    /**
     * It makes the color change from seat-text factory about the stock it has
     * in that moment.
     *
     * @param seatText
     */
    public void seatTextSync(TextField seatText) {

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

    /**
     * It makes the color change from stamping-text factory about the stock it
     * has in that moment.
     *
     * @param stampingText
     */
    public void stampingTextSync(TextField stampingText) {

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

}
