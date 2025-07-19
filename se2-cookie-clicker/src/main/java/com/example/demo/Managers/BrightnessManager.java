package com.example.demo.Managers;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BrightnessManager implements IBright {
    private static final Logger logger= Logger.getLogger(BrightnessManager.class.getName());
    private double  brightness=0;
    private static BrightnessManager instance;
    public static BrightnessManager getInstance() {
        if (instance == null){
            instance = new BrightnessManager();
            logger.log(Level.INFO,"Manager wurde erstellt!");
        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }
    @Override
    public void setBrightness(double brightness) {
        logger.log(Level.INFO,"Brightness wurde auf "+brightness+" gesetzt!");
        this.brightness = brightness;
    }
    @Override
    public double getBrightness(){
        logger.log(Level.INFO,"Brightness mit Wert: "+brightness +" wurde zruückgegeben!");
        return brightness;
    }
}
