package com.example.demo.Factory;

import java.util.logging.Logger;

public class ObtainbleFactory {
    private static final Logger logger= Logger.getLogger(ObtainbleFactory.class.getName());
    public static IObtainble create(int value, boolean status, String description, String name, int cost, Typ Type){
        if(Type== Typ.Upgrade){
            logger.info("Upgrade mit folgenden Werten wurde erstellet value:" +value+", status:"+status+", Description:"+description+", Name:"+name+", Cost:"+cost+"!");
            return new Upgrade(value, status, description, name, cost);
        }
        if(Type== Typ.Achievement){
            logger.info("Achievement mit folgenden Werten wurde erstellet value:" +value+", status:"+status+", Description:"+description+", Name:"+name+"!");
            return new Achievement(value, description, name, status);
        }
        return null;
    }
}
