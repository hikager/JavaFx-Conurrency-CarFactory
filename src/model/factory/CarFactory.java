/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

/**
 * A car builder needs to satisfy all these parts from the contract.
 *
 * @author Luis ML 
 * See also {@link dam.projecte.concurrencia.fabrica.model.factoryImp.CarBuilder#this}
 */
public interface CarFactory extends Runnable {

    /**
     * When a carBuilder starts to consume car pieces
     *
     * @param count A counting integer to know the times this method is called.
     */
    void consume(int count);

    /**
     * Whether we need to stop the consume part (All of it) - this will stop the
     * whole factory (no cars will be "created" then)
     */
    void stop();
}
