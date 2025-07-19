package com.example.demo.Managers;

import java.util.List;

import com.example.demo.Factory.IObtainble;

public interface IUListManager {
    boolean allactivated();
    void activatevictory();
    void setlist(List<IObtainble> list);
    List<IObtainble> getlist();
    int domultiply();
}
