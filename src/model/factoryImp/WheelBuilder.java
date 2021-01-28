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
public class WheelBuilder implements CarPieceFactory {

    /**
     * Max amount to produce - it will be stop, it will be stop when it reaches
     */
    private final int MAX_STOCK;
    /**
     * Min amount to start building
     */
    private final int MIN_STOCK;
    /**
     * Production per hour
     */
    private final int PROD_PER_HOUR;

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
    private Integer pieces;

    /**
     * Thread control - run or not to run - thread-safe
     */
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * Default values for stamping factory (whether the user does not want to
     * make its custom)
     */
    public WheelBuilder() {
        PROD_PER_HOUR = 210;
        MIN_STOCK = 500;
        MAX_STOCK = 700;
        //Init our factory with
        pieces = MIN_STOCK;
    }

    /**
     *
     * @param MAX_STOCK Max amount of pieces that can produce this factory
     * @param MIN_STOCK Min amount of pieces that can be this factory (cannot be
     * less without start producing)
     * @param PROD_PER_HOUR Constant amount of production
     */
    public WheelBuilder(int MAX_STOCK, int MIN_STOCK, int PROD_PER_HOUR) throws Exception {

        if (MIN_STOCK > MAX_STOCK) {
            throw new Exception("You cannot set Min stock > max stock");
        } else if (PROD_PER_HOUR >= 0) {
            throw new Exception("PROD_PER_HOUR needs to be more than 0");
        } else {
            this.PROD_PER_HOUR = PROD_PER_HOUR;
            this.MAX_STOCK = MAX_STOCK;
            this.MIN_STOCK = MIN_STOCK;
            pieces = MIN_STOCK;
        }
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
    public synchronized boolean canProduce() {
        return !stop && pieces <= MIN_STOCK || pieces <= MAX_STOCK;
    }

    @Override
    public synchronized void produce(int count) throws InterruptedException {
        if (canProduce()) {
            pieces += PROD_PER_HOUR;
            System.out.println("PRODUCIENDO RUEDAS\n");
        } else {
            System.out.println("STOP PRODUCIENDO RUEDAS\n");
        }

        System.out.println("PIECES: " + pieces);
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized boolean isStop() {
        return stop;
    }

    public synchronized void setStop(boolean stop) {
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

}
