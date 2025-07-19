package com.example.demo.Managers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TimerManager implements ITimerManager{
    private static final Logger logger= Logger.getLogger(TimerManager.class.getName());
    private static TimerManager instance;
    private final List<Timer> timerListe;
    private TimerManager(List<Timer> initialTimers){
        this.timerListe = new ArrayList<>(initialTimers);
    }

    public static TimerManager getInstance() {
        if (instance == null){
            List<Timer> initialTimers = List.of(new Timer("cookietimer"), new Timer("timeouttimer"));
            instance = new TimerManager(initialTimers);
            logger.log(Level.INFO,"Manager wurde erstellt!");

        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }
    @Override
    public void addTimer(Timer timer) {
        timerListe.add(timer);
        logger.log(Level.INFO,"Neuer Timer wurde hinzugefügt!");
    }
    @Override
    public void setTimer(int index, Timer timer){
        if(index>=timerListe.size()){
            throw new IndexOutOfBoundsException();
        }
        timerListe.set(index, timer);
        logger.log(Level.INFO,"Neuer Timer wurde an dem Index " + index +" hinzugefügt!");
    }
    @Override
    public Timer getTimer(int index){
        if(index>=timerListe.size()){
            throw new IndexOutOfBoundsException();
        }
        logger.log(Level.INFO,"Timer am Index " + index + " wurde zurückgegeben!");
        return timerListe.get(index);
    }
}
