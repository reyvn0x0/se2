package com.example.demo.Managers;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;

public class Timer implements Runnable {
    private static final Logger logger = Logger.getLogger(Timer.class.getName());

    private int seconds = 0;
    private boolean running = false;
    private boolean on = false;

    private final String name;

    public Timer(String name) {
        this.name = name;
        logger.log(Level.INFO, "Timer mit Name: " + name + " wurde erstellt!" +"Er befindet sich auf Thread: " + Thread.currentThread().getName());
    }

    public synchronized void start() {
        if (on) return;
        running = true;
        on = true;
        Thread thread = new Thread(this, name);
        thread.setDaemon(true);
        thread.start();
    }

    public void run() {
        logger.log(Level.INFO, "Timer " + name + "auf Thread "+Thread.currentThread().getName()+ " wurde gestartet!");

        while (isRunning()) {
            try {
                Thread.sleep(1000);
                incrementSeconds();

                if (getSeconds() == 10 && name.equals("timeouttimer")) {
                    stopTimer(); // synchronisiert
                    logger.log(Level.INFO, "Anwendung wird geschlossen!");
                    Platform.exit();
                }

            } catch (InterruptedException e) {
                stopTimer();
                logger.log(Level.SEVERE, String.format("Problem beim Timer: %s auf Thread: %s", this.name, Thread.currentThread().getName()));              
                break;
            }
        }

        synchronized (this) {
            on = false;
        }
    }

    public synchronized void stopTimer() {
        running = false;
        on = false;
        logger.log(Level.INFO, "Timer" + name + "auf Thread" +Thread.currentThread().getName()+ "wurde gestoppt!");
    }

    public synchronized boolean isOn() {
        logger.log(Level.INFO, "ison Status" +"von Timer "+ this.name+" auf Thread " +Thread.currentThread().getName() +": " + on + " wurde zurückgegeben!");
        return on;
    }

    public synchronized int getSeconds() {
        logger.log(Level.INFO, "seconds" +" von Timer " +this.name+ "auf Thread " +Thread.currentThread().getName()+ "wurde zurückgegeben! Der Wert beträgt " + seconds + " !");
        return seconds;
    }

    private synchronized boolean isRunning() {
        return running;
    }

    private synchronized void incrementSeconds() {
        seconds++;
    }
}
