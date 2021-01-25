/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import model.factory.CarPieceFactory;

/**
 *
 * @author Luis ML
 */
public class BatteryBuilder implements CarPieceFactory {

    //Max amount to produce - it will be stop
    private final int MAX_STOCK = 750;
    //Min amount to start building
    private final int MIN_STOCK = 550;
    //Production per hour
    private final int PROD_PER_HOUR = 90;

    private String name;

    //List which is shared among carbuilder and its  piece builders
    private List<Integer> piecesList;

    //Thread control - run or not to run
    private final AtomicBoolean running = new AtomicBoolean(false);

    public BatteryBuilder(String name, List<Integer> piecesList) {
        this.name = name;
        this.piecesList = piecesList;

        if (piecesList == null) {
            piecesList = new ArrayList<>();
        }
    }

    public BatteryBuilder() {
        if (piecesList == null) {
            piecesList = new ArrayList<>();
        }
    }

    @Override
    public void run() {
        running.set(true);
        int count = 0;

        while (running.get()) { // gets the value from the memory, so that changes made by other threads are visible; equivalent to reading a volatile variable
            System.out.println("Battery builder");
            try {
                System.out.println("\n\t\\/" + this.name + "\n");
                produce(++count);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

    @Override
    public void produce(int count) throws InterruptedException {
        //Making synchronization of objects - we avoid Race Conditions
        synchronized (piecesList) {
            System.out.println("STOCK SIZE PRODUCER " + name + ": " + piecesList.size());
            while (piecesList.size() == MAX_STOCK) {
                System.out.println("\nProducer [" + name + "] is full !!!\n");
                piecesList.wait();
                System.out.println("\n==================\n");
            }

            //If the consumer is not empty then we got to simulate the "consumition"
            Thread.sleep(1000);//for human being to watch how jvm behaves

            for (int i = 0; i < PROD_PER_HOUR; ++i) {
                piecesList.add(count);
            }

            System.out.println("\nProduced " + name + ":: " + count);
            ++count;
            piecesList.notify();//Notify all threads which use the object that this one is no "free"

        }
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMAX_STOCK() {
        return MAX_STOCK;
    }

    public int getMIN_STOCK() {
        return MIN_STOCK;
    }

    public int getPROD_PER_HOUR() {
        return PROD_PER_HOUR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPiecesList() {
        return piecesList;
    }

    public void setPiecesList(List<Integer> piecesList) {
        this.piecesList = piecesList;
    }

}
