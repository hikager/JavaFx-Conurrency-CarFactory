/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import model.factoryImp.BatteryBuilder;
import model.factoryImp.CarBuilder;
import model.factoryImp.EngineBuilder;
import model.factoryImp.SeatBuilder;
import model.factoryImp.StampingBuilder;
import model.factoryImp.WheelBuilder;

/**
 *
 * To reduce spaggetti code from Controller I need to create a class to manage
 * the factory itself (who makes cars).
 *
 * So this class performs the main activity to make pieces and consume them to
 * build cars.
 *
 * This class makes Builders like it does not exists in controller. So we made
 * some clean from the controller avoiding repetitive instructions.
 *
 * @author Luis ML
 */
public class MainFactory {

    //Runnables
    private CarBuilder carBuilder;
    private BatteryBuilder batteryBuilder;
    private EngineBuilder engineBuilder;
    private SeatBuilder seatBuilder;
    private StampingBuilder stampingBuilder;
    private WheelBuilder wheelBuilder;

    //Threads
    private Thread carBuilderThread;
    private Thread batteryBuilderThread;
    private Thread engineBuilderThread;
    private Thread seatBuilderThread;
    private Thread stampingBuilderThread;
    private Thread wheelBuilderThread;

    //Map to manage all threads
    private Map<String, Thread> factories;

    public MainFactory() {
        start();
    }

    /**
     * Start all the factories and its threads and container
     */
    private void start() {
        initFactories();
        initAllThreadFactories();
        initFacotryThreadsContainer();
        startAllThreadFactories();
    }

    /**
     * Initialize all the builders we need to use in threads
     */
    private void initFactories() {
        wheelBuilder = new WheelBuilder();
        stampingBuilder = new StampingBuilder();
        batteryBuilder = new BatteryBuilder("batteryBuilder");
        engineBuilder = new EngineBuilder();
        seatBuilder = new SeatBuilder();
        carBuilder = new CarBuilder(batteryBuilder, engineBuilder, seatBuilder, stampingBuilder, wheelBuilder);
    }

    /**
     * Initialize our thread-factory container
     */
    private void initFacotryThreadsContainer() {
        this.factories = new HashMap<String, Thread>() {
            {
                put("CarBuilderThread", carBuilderThread);
                put("BatteryBuilderThread", batteryBuilderThread);
                put("EngineBuilderThread", engineBuilderThread);
                put("SeatBuilderThread", seatBuilderThread);
                put("StampingBuilderThread", stampingBuilderThread);
                put("WheelBuilderThread", wheelBuilderThread);
            }
        };
    }

    /**
     * Initialize each builder thread (with its name and it's builder)
     */
    private void initAllThreadFactories() {
        this.carBuilderThread = new Thread(this.carBuilder, "car-builder-thread");
        this.batteryBuilderThread = new Thread(this.batteryBuilder, "battery-builder-thread");
        this.engineBuilderThread = new Thread(this.engineBuilder, "engine-builder-thread");
        this.seatBuilderThread = new Thread(this.seatBuilder, "seat-builder-thread");
        this.stampingBuilderThread = new Thread(this.stampingBuilder, "stamping-builder-thread");
        this.wheelBuilderThread = new Thread(this.wheelBuilder, "wheel-builder-thread");
    }

    /**
     * Starts all factory threads.
     */
    private void startAllThreadFactories() {
        carBuilderThread.start();
        batteryBuilderThread.start();
        engineBuilderThread.start();
        seatBuilderThread.start();
        stampingBuilderThread.start();
        wheelBuilderThread.start();
    }

    /**
     * Close all threads created to build pieces and cars
     */
    public void closeAllFactories() {
        carBuilder.closeThread();
        batteryBuilder.closeThread();
        engineBuilder.closeThread();
        seatBuilder.closeThread();
        stampingBuilder.closeThread();
        wheelBuilder.closeThread();
    }

    public CarBuilder getCarBuilder() {
        return carBuilder;
    }

    public void setCarBuilder(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public BatteryBuilder getBatteryBuilder() {
        return batteryBuilder;
    }

    public void setBatteryBuilder(BatteryBuilder batteryBuilder) {
        this.batteryBuilder = batteryBuilder;
    }

    public EngineBuilder getEngineBuilder() {
        return engineBuilder;
    }

    public void setEngineBuilder(EngineBuilder engineBuilder) {
        this.engineBuilder = engineBuilder;
    }

    public SeatBuilder getSeatBuilder() {
        return seatBuilder;
    }

    public void setSeatBuilder(SeatBuilder seatBuilder) {
        this.seatBuilder = seatBuilder;
    }

    public StampingBuilder getStampingBuilder() {
        return stampingBuilder;
    }

    public void setStampingBuilder(StampingBuilder stampingBuilder) {
        this.stampingBuilder = stampingBuilder;
    }

    public WheelBuilder getWheelBuilder() {
        return wheelBuilder;
    }

    public void setWheelBuilder(WheelBuilder wheelBuilder) {
        this.wheelBuilder = wheelBuilder;
    }

    public Thread getCarBuilderThread() {
        return carBuilderThread;
    }

    public void setCarBuilderThread(Thread carBuilderThread) {
        this.carBuilderThread = carBuilderThread;
    }

    public Thread getBatteryBuilderThread() {
        return batteryBuilderThread;
    }

    public void setBatteryBuilderThread(Thread batteryBuilderThread) {
        this.batteryBuilderThread = batteryBuilderThread;
    }

    public Thread getEngineBuilderThread() {
        return engineBuilderThread;
    }

    public void setEngineBuilderThread(Thread engineBuilderThread) {
        this.engineBuilderThread = engineBuilderThread;
    }

    public Thread getSeatBuilderThread() {
        return seatBuilderThread;
    }

    public void setSeatBuilderThread(Thread seatBuilderThread) {
        this.seatBuilderThread = seatBuilderThread;
    }

    public Thread getStampingBuilderThread() {
        return stampingBuilderThread;
    }

    public void setStampingBuilderThread(Thread stampingBuilderThread) {
        this.stampingBuilderThread = stampingBuilderThread;
    }

    public Thread getWheelBuilderThread() {
        return wheelBuilderThread;
    }

    public void setWheelBuilderThread(Thread wheelBuilderThread) {
        this.wheelBuilderThread = wheelBuilderThread;
    }

    public Map<String, Thread> getFactories() {
        return factories;
    }

    public void setFactories(Map<String, Thread> factories) {
        this.factories = factories;
    }

}
