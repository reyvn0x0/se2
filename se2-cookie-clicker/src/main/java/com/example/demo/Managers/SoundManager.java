package com.example.demo.Managers;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.SoundManagerException;

import javafx.scene.media.AudioClip;

public class SoundManager implements ISoundManager {
    private static final Logger logger= Logger.getLogger(SoundManager.class.getName());
    private static SoundManager instance;
    public static SoundManager getInstance() {
        if(instance==null){
            instance=new SoundManager();
            logger.log(Level.INFO,"Manager wurde erstellt!");

        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }
    private final AudioClip COOKIE_CLIP;
    private final AudioClip ACHIEVEMENT_CLIP;
    private final AudioClip SWITCH_CLIP;
    private final AudioClip UPGRADE_CLIP;
    private double volume = 0.5;
    public SoundManager(){
        COOKIE_CLIP=new AudioClip(new File("src/main/resources/sounds/Cookie.mp3").toURI().toString());
        ACHIEVEMENT_CLIP=new AudioClip(new File("src/main/resources/sounds/Achievement.mp3").toURI().toString());
        SWITCH_CLIP=new AudioClip(new File("src/main/resources/sounds/SceneSwitch.mp3").toURI().toString());
        UPGRADE_CLIP=new AudioClip(new File("src/main/resources/sounds/Click.mp3").toURI().toString());
        COOKIE_CLIP.setVolume(volume);
        ACHIEVEMENT_CLIP.setVolume(volume);
        SWITCH_CLIP.setVolume(volume);
        UPGRADE_CLIP.setVolume(volume);
    }
    @Override
    public void playSound(char index){
    logger.log(Level.INFO, "Sound mit " + index + " wird abgespielt!");
    try{
    switch (index) {
        case 'c': COOKIE_CLIP.play(); break;
        case 'a': ACHIEVEMENT_CLIP.play(); break;
        case 's': SWITCH_CLIP.play(); break;
        case 'u': UPGRADE_CLIP.play(); break;
        default:
            SoundManagerException e = new SoundManagerException("Index " + index + " wurde nicht gefunden!");
            throw e;
    }
    }catch (SoundManagerException e) {
        logger.log(Level.WARNING, "Fehler bei Methodenaufruf", e);
    }
}

@Override
public AudioClip getMusicClip(char index){
    logger.log(Level.INFO, "Audioclip mit " + index + " wird zurückgegeben!");
    try{
    switch (index) {
        case 'c': return COOKIE_CLIP;
        case 'a': return ACHIEVEMENT_CLIP;
        case 's': return SWITCH_CLIP;
        case 'u': return UPGRADE_CLIP;
        default:
            SoundManagerException e = new SoundManagerException("Index " + index + " wurde nicht gefunden!");
            throw e;
    }
    } catch (SoundManagerException e) {
        logger.log(Level.WARNING, "Fehler bei Methodenaufruf", e);
    }
    return null;
    }
    @Override
    public void setVolume(double v) {
        volume = v;
        COOKIE_CLIP.setVolume(v);
        ACHIEVEMENT_CLIP.setVolume(v);
        SWITCH_CLIP.setVolume(v);
        UPGRADE_CLIP.setVolume(v);
        logger.log(Level.INFO,"Volume wurde auf " + volume + "gesetzt!");
    }
    @Override
    public double getVolume() {
        logger.log(Level.INFO,"Volume mit Wert " + volume + "wurde zurückgegeben!");
        return volume;
    }
}
