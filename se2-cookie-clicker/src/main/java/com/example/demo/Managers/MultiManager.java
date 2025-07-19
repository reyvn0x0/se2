package com.example.demo.Managers;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Factory.IObtainble;
public class MultiManager implements IMultipliable {
    private static final Logger logger= Logger.getLogger(MultiManager.class.getName());
    @Override
    public int multiply(List<IObtainble> upgrades) {
        int multiplikator = 0;
        for (IObtainble upgrade : upgrades) {
            if (upgrade != null && upgrade.getStatus()) {
                multiplikator += upgrade.getValue();
            }
        }
        logger.log(Level.INFO,"Multiplikator wird zurückgegeben! Der Wert beträgt: " + multiplikator);
        return multiplikator;
    }
}
