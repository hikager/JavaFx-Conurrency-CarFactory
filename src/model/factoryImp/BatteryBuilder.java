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

    /**
     * Max amount to produce - it will be stop
     */
    private final int MAX_STOCK = 750;
    /**
     * Min amount to start building
     */
    private final int MIN_STOCK = 550;
    /**
     * Production per hour
     */
    private final int PROD_PER_HOUR = 90;

  // boolean keepProducing;

    /**
     * To stop this factory
     */
    boolean stop;
    /**
     * Factory name
     */
    private String name;

    private Integer pieces = MIN_STOCK;

    //Thread control - run or not to run
    private final AtomicBoolean running = new AtomicBoolean(false);

    public BatteryBuilder(String name, List<Integer> piecesList) {
        this.name = name;

        if (piecesList == null) {
            piecesList = new ArrayList<>();
        }
    }

    public BatteryBuilder() {
    }

    @Override
    public void run() {
        //  keepProducingUnderButton = true;
        running.set(true);
        int count = 0;
        // keepProducing = true;
        stop = false;
        while (running.get()) {

            try {
                //stops for external request (eg: button)
                if (!stop) {
                    produce(++count);

                }
                System.out.println("\npieces:: " + pieces);
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

        }

    }

    @Override
    public void produce(int count) throws InterruptedException {
        if (canProduce()) {
            pieces += PROD_PER_HOUR+1000;
            System.out.println("PRODUCIENDO BATERIAS\n");
        } else {
            System.out.println("STOP PRODUCIENDO BATERIAS\n");
        }

        System.out.println("PIECES: " + pieces);
    }

    @Override
    public synchronized boolean canProduce() {
        return !stop && pieces <= MIN_STOCK || pieces <= MAX_STOCK;
    }

    @Override
    public void stop() {
        this.stop = true;
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

  
    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

}
