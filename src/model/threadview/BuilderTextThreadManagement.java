/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threadview;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 *
 * @author Luis ML
 */
public final class BuilderTextThreadManagement {

    //Windows text threads
    private Thread carBuilderTextThread;
    private Thread batteryBuilderTextThread;
    private Thread engineBuilderTextThread;
    private Thread seatBuilderTextThread;
    private Thread stampingBuilderTextThread;
    private Thread WheelBuilderTextThread;

    private boolean factoryWorking = true;
    /**
     * Class which manage all the colors in the GUI. It's actually a complement
     * for this one.
     */
    TextThreadViewColor textThreadView;

    public BuilderTextThreadManagement(TextThreadViewColor textThreadView) {
        this.textThreadView = textThreadView;
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
    public void windowComponentsInit(TextField carText, TextField engineText, TextField stampingText, TextField batteryText, TextField wheelText, TextField seatText) {

        this.carBuilderTextThread = new Thread(() -> {
            Runnable carText1 = () -> {
                System.out.println("CAR TEXT");
                //carTextSync(); //no synchronize because it wont work
                textThreadView.carTextSync(carText);
            };
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            while (factoryWorking) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(BuilderTextThreadManagement.class.getName()).log(Level.SEVERE, null, ex);
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

}
