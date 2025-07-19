package com.example.demo.Managers;



import javafx.scene.media.AudioClip;

public interface ISoundManager {
    void playSound(char index);
    AudioClip getMusicClip(char index);
    void setVolume(double volume);
    double getVolume();
}
