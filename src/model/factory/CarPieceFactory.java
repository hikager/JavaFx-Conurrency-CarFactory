/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

/**
 * The contract which all piece builders need to ensure to be doing.
 *
 * @author Luis ML
 */
public interface CarPieceFactory extends Runnable {

    /**
     * When a a piece builder starts to produces its pieces.
     *
     * @param count 
     *              A counting integer to know the times this method is called.
     */
    void produce(int count) throws InterruptedException;

    /**
     * Whether we need to stop the consume part (All of it) - this will stop the
     * whole factory (only the piece factory which is called)
     */
    void stop();
}
