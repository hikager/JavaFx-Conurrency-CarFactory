/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factoryImp;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import model.factory.CarFactory;

/**
 *
 * @author Luis ML
 */
public class CarBuilder implements CarFactory {

    private String name;

    //Thread control - run or not to run
    private final AtomicBoolean running = new AtomicBoolean(false);

    //List which is shared among carbuilder and its  piece builders
    private List<Integer> piecesList;

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

    @Override
    public void run() {
        // writes the value to memory, so that the change is visible to other threads; equivalent to writing a volatile variable
        running.set(true);
        int count = 0;
        while (running.get()) { // gets the value from the memory, so that changes made by other threads are visible; equivalent to reading a volatile variable
            try {
                System.out.println("GO CONSUMER....");
                consume(count);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void consume(int count) throws InterruptedException {
        int consumed = 0;
        synchronized (batteryBuilder) {

            while (batteryBuilder.getPiecesList().size() < batteryBuilder.getMIN_STOCK()) {
                System.out.println("\nProducer [" + batteryBuilder.getName() + "] is full !!!\n");
                batteryBuilder.wait();
                System.out.println("\n==================\n");
            }

            Thread.sleep(1000);//for human being to watch how jvm behaves
            consumed = batteryBuilder.getPiecesList().remove(0);
            System.out.println("Consumed: " + consumed);
            ++count;
            batteryBuilder.notify();
        }

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

}
