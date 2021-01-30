/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factoryImp;

import java.util.concurrent.atomic.AtomicBoolean;
import model.factory.CarPieceFactory;

/**
 *
 * @author Luis ML
 */
public class SeatBuilder implements CarPieceFactory {

    /**
     * Max amount to produce - it will be stop, it will be stop when it reaches
     */
    private final int MAX_STOCK = 700;
    /**
     * Min amount to start building
     */
    private final int MIN_STOCK = 450;
    /**
     * Production per hour
     */
    private final int PROD_PER_HOUR = 80;

    // boolean keepProducing;
    /**
     * To stop this factory
     */
    boolean stop;
    /**
     * Factory name
     */
    private String name;

    /**
     * Start amount when factory is started
     */
    private Integer pieces = MIN_STOCK;

    /**
     * Thread control - run or not to run - thread-safe
     */
    private final AtomicBoolean running = new AtomicBoolean(false);

    public SeatBuilder() {

    }

    public SeatBuilder(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        running.set(true);
        int count = 0;

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
            pieces += PROD_PER_HOUR;
            System.out.println("PRODUCIENDO ASIENTOS\n");
        } else {
            System.out.println("STOP PRODUCIENDO ASIENTOS\n");
        }

        System.out.println("PIECES: " + pieces);
    }

    @Override
    public boolean canProduce() {
        return !stop && pieces <= MIN_STOCK || pieces <= MAX_STOCK;
    }

    @Override
    public void stop() {
       this.stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
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

    public int getMAX_STOCK() {
        return MAX_STOCK;
    }

    public int getMIN_STOCK() {
        return MIN_STOCK;
    }

    public int getPROD_PER_HOUR() {
        return PROD_PER_HOUR;
    }

    public AtomicBoolean getRunning() {
        return running;
    }

     public void closeThread() {
        this.running.set(false);
    }
}
