package com.example.demo.Managers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterManager implements ICountable {
    private static final Logger logger= Logger.getLogger(CounterManager.class.getName());
    private long counter = 0;
    private static CounterManager instance;
    public static CounterManager getInstance() {
        if (instance == null){
            instance = new CounterManager();
            logger.log(Level.INFO,"Manager wurde erstellt!");
        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }
    public void setCounter(long counter) {
        logger.log(Level.INFO,"Counter wurde auf " + counter + " gesetzt!");
        this.counter = counter;
    }
    public long getCounter() {
        logger.log(Level.INFO,"Counter mit Wert: " + counter + " wurde zurückgegeben!");
        return counter;
    }
}
