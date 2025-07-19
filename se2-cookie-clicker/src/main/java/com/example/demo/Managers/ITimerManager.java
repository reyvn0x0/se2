package com.example.demo.Managers;

public interface ITimerManager{
    void addTimer(Timer timer);
    void setTimer(int index, Timer timer);
    Timer getTimer(int index);
}
