/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factoryImp;

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

    }

    public CarBuilder(BatteryBuilder batteryBuilder, EngineBuilder engineBuilder) {
        this.engineBuilder = engineBuilder;
        this.batteryBuilder = batteryBuilder;

    }

    public CarBuilder(BatteryBuilder batteryBuilder, EngineBuilder engineBuilder, SeatBuilder seatBuilder) {
        this.engineBuilder = engineBuilder;
        this.batteryBuilder = batteryBuilder;
        this.seatBuilder = seatBuilder;

    }

    public CarBuilder(BatteryBuilder batteryBuilder, EngineBuilder engineBuilder, SeatBuilder seatBuilder, StampingBuilder stampingBuilder) {
        this.engineBuilder = engineBuilder;
        this.batteryBuilder = batteryBuilder;
        this.seatBuilder = seatBuilder;
        this.stampingBuilder = stampingBuilder;

    }

    public CarBuilder(BatteryBuilder batteryBuilder, EngineBuilder engineBuilder, SeatBuilder seatBuilder, StampingBuilder stampingBuilder, WheelBuilder wheelBuilder) {
        this.engineBuilder = engineBuilder;
        this.batteryBuilder = batteryBuilder;
        this.seatBuilder = seatBuilder;
        this.stampingBuilder = stampingBuilder;
        this.wheelBuilder = wheelBuilder;
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
        if (canConsume()) {
            // int consumes = 100;
            System.out.println("GO CONSUMER.... (" + count + ")");
            /* Integer batPieces = batteryBuilder.getPieces();
            batPieces -= CARS_PER_HOUR;
            batteryBuilder.setPieces(batPieces);
             */
            consumePieces();
            cars += CARS_PER_HOUR;

            System.out.println("CONSUMED: " + CARS_PER_HOUR);
        } else {
            System.out.println("CONSUMER STOP (max stock reached)");
        }

    }

    /**
     * Whether the CarFactory can make a car ( Whether we can produce 100 cars
     * per hour(
     *
     * @return If all the factories were made enough pieces to build a car (100
     * p hour)
     */
    public boolean canConsume() {
        return canConsumeBatteries() && canConsumeEngines() && canConsumeSeats() && canConsumeStampings() && canConsumeWheel();
    }

    /**
     * Report the consumer whether batteries can be consumed.
     *
     * @return Can we get batteries?
     */
    public boolean canConsumeBatteries() {
        return batteryBuilder.getPieces() > batteryBuilder.getMIN_STOCK() || !batteryBuilder.canProduce();
    }

    /**
     * Report the consumer whether engines can be consumed.
     *
     * @return Can we get engines?
     */
    public boolean canConsumeEngines() {
        return engineBuilder.getPieces() > engineBuilder.getMIN_STOCK() || !engineBuilder.canProduce();
    }

    /**
     * Report the consumer whether seats can be consumed.
     *
     * @return Can we get seats?
     */
    public boolean canConsumeSeats() {
        return seatBuilder.getPieces() > seatBuilder.getMIN_STOCK() || !seatBuilder.canProduce();
    }

    /**
     * Report the consumer whether stampings can be consumed.
     *
     * @return Can we get stampings?
     */
    public boolean canConsumeStampings() {
        return stampingBuilder.getPieces() > stampingBuilder.getMIN_STOCK() || !stampingBuilder.canProduce();
    }

    /**
     * Report the consumer whether wheels can be consumed.
     *
     * @return Can we get wheels?
     */
    public boolean canConsumeWheel() {
        return wheelBuilder.getPieces() > wheelBuilder.getMIN_STOCK() || !wheelBuilder.canProduce();
    }

    /**
     * It takes the amount of pieces which 100 cars need per hour (100 pieces
     * from each builder/factory)
     *
     */
    private void consumePieces() {
        //Consuming batteries
        batteryBuilder.setPieces(batteryBuilder.getPieces() - CARS_PER_HOUR);
        //Consuming engines
        engineBuilder.setPieces(engineBuilder.getPieces() - CARS_PER_HOUR);
        //Consuming seats
        seatBuilder.setPieces(seatBuilder.getPieces() - CARS_PER_HOUR);
        //Consuming stampings
        stampingBuilder.setPieces(stampingBuilder.getPieces() - CARS_PER_HOUR);
        //Consuming wheel
        wheelBuilder.setPieces(wheelBuilder.getPieces() - CARS_PER_HOUR);
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

    public synchronized Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

}
