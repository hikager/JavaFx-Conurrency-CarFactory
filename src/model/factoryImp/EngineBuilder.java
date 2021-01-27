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
public class EngineBuilder implements CarPieceFactory {

    /**
     * Max amount to produce - it will be stop when it reaches
     */
    private final int MAX_STOCK = 800;
    /**
     * Min amount to start building
     */
    private final int MIN_STOCK = 600;
    /**
     * Production per hour
     */
    private final int PROD_PER_HOUR = 110;

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

    @Override
    public void run() {
        int count = 0;
        running.set(true);
        stop = false;
        while (running.get()) {
            try {

                if (!stop) {
                    produce(++count);

                }
                System.out.println(EngineBuilder.class + ":: " + pieces);
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    @Override
    public boolean canProduce() {
        return !stop && pieces <= MIN_STOCK || pieces <= MAX_STOCK;
    }

    @Override
    public void produce(int count) throws InterruptedException {
        if (canProduce()) {
            pieces += PROD_PER_HOUR;
            System.out.println("PRODUCIENDO BATERIAS\n");
        } else {
            System.out.println("STOP PRODUCIENDO BATERIAS\n");
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

    public synchronized Integer getPieces() {
        return pieces;
    }

    public synchronized void setPieces(Integer pieces) {
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
