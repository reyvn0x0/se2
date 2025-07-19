package com.example.demo.Managers;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class MusicManager implements IMusicManager {
    private static final Logger logger= Logger.getLogger(MusicManager.class.getName());
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    public static MusicManager getInstance() {
        if (instance == null){
            instance = new MusicManager();
            logger.log(Level.INFO,"Manager wurde erstellt!");
        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }
    @Override
    public void backgroundMusic() {
        String resourcePath = Objects.requireNonNull(getClass().getResource("/sounds/Fein.mp3")).toExternalForm();
        Media h = new Media(resourcePath);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        logger.log(Level.INFO,"Musik wird abgespielt!");
    }
    @Override
    public void stop() {
            logger.info("Musikplayer wurde gestopt!");
            mediaPlayer.stop();
    }
    @Override
    public void setVolume(double volume) {
            mediaPlayer.setVolume(volume);
            logger.log(Level.INFO,"Lautstärke wurde auf " + volume + " gesetzt!");
    }
    @Override
    public double getVolume() {
            double volume = mediaPlayer.getVolume();
            logger.log(Level.INFO,"Aktuelle Lautstärke ist " + volume);
            return volume;
    }
}
