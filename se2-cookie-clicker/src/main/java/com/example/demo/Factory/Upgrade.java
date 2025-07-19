package com.example.demo.Factory;
import java.util.logging.Logger;
class Upgrade extends UpgradeAbstract {
    private static final Logger logger= Logger.getLogger(Upgrade.class.getName());
    public Upgrade(int value, boolean status, String Description, String Name, int Cost) {
        super(value, status, Description, Name, Cost);
        logger.info("Upgrade mit folgenden Werten wurde erstellet value:" +value+", status:"+status+", Description:"+Description+", Name:"+Name+", Cost:"+Cost+"!");
    }
}
