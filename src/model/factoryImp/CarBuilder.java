/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import model.factory.CarFactory;

/**
 *
 * <h2>Carbuilder class</h2>
 * <p>
 * It consumes from its piece factories and then it produce cars with them.</p>
 *
 * It inherited from its CarFactory the CARS_PER_HOUR
 *
 * @author Luis ML
 */
public class CarBuilder implements CarFactory {

    //Per hour -> 100 cars aprox => 5 pieces, then 100/5  makes how many pieces  needs per hour
    private int CONSUMING_PIECES_PER_HOUR = 20;
    private boolean stop;
    private boolean stopByStock;
    private String name;

    //Thread control - run or not to run
    private final AtomicBoolean running = new AtomicBoolean(false);

    //List which is shared among carbuilder and its  piece builders
    private List<Integer> piecesList;
    private Integer cars = 0;

    //Builders - For Pieces 
    private BatteryBuilder batteryBuilder;
    private EngineBuilder engineBuilder;
    private SeatBuilder seatBuilder;
    private StampingBuilder stampingBuilder;
    private WheelBuilder wheelBuilder;

    public CarBuilder() {

    }

    //Just for test view is working fine (synchronized)
    public CarBuilder(BatteryBuilder batteryBuilder) {
        this.batteryBuilder = batteryBuilder;
        if (piecesList == null) {
            piecesList = new ArrayList<>();
        }
    }

    @Override
    public void run() {
        // writes the value to memory, so that the change is visible to other threads; equivalent to writing a volatile variable

        running.set(true);
        int count = 0;
        stop = false;
        stopByStock = false;

        while (running.get()) { // gets the value from the memory, so that changes made by other threads are visible; equivalent to reading a volatile variable
            try {
                consume(++count);

                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void consume(int count) throws InterruptedException {

        //  consume(count);
        //!stop &&
        if ( canConsume()) {
           // int consumes = 100;
            System.out.println("GO CONSUMER.... (" + count + ")");
            Integer batPieces = batteryBuilder.getPieces();
            batPieces -= CARS_PER_HOUR;
            batteryBuilder.setPieces(batPieces);

            cars += CARS_PER_HOUR;

            System.out.println("CONSUMED: " + CARS_PER_HOUR);

        } else {
            System.out.println("CONSUMER STOP (max stock reached)");
        }

    }

    public boolean canConsume() {
        return batteryBuilder.getPieces() > batteryBuilder.getMIN_STOCK() || !batteryBuilder.canProduce();
    }

    /* public int amountToConsume() {
        return batteryBuilder.canProduce() ? 1 : 100;
    }
     */
    public boolean isStop() {
        return stop;
    }

    public synchronized void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public synchronized Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

}
