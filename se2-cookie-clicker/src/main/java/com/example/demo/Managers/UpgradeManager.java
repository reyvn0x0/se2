package com.example.demo.Managers;
import com.example.demo.Factory.ObtainbleFactory;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Factory.Typ;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public  class UpgradeManager implements IUListManager{
    private static final Logger logger= Logger.getLogger(UpgradeManager.class.getName());
    private static UpgradeManager instance;
    public List<IObtainble> upgradeliste;
    private final MultiManager mmanager = new MultiManager();
    public static UpgradeManager getInstance() {
        if (instance == null){
            instance = new UpgradeManager();
            logger.log(Level.INFO,"Manager wurde erstellt!");

        }
        logger.log(Level.INFO,"Manager wurde zurückgegeben!");
        return instance;
    }

public UpgradeManager(){
    upgradeliste=new ArrayList<>();
    upgradeliste.add(ObtainbleFactory.create(2, false, "Double your clicking power", "Double Click", 25, Typ.Upgrade));
    upgradeliste.add(ObtainbleFactory.create(3, false, "Triple your clicking power", "Triple Click", 100, Typ.Upgrade));
    upgradeliste.add(ObtainbleFactory.create(4, false, "Quadruple your clicking power", "Quad Click", 400, Typ.Upgrade));
    upgradeliste.add(ObtainbleFactory.create(5, false, "Ultimate clicking power", "Super Click", 1000, Typ.Upgrade));
}

    public void activatevictory(){
        upgradeliste.parallelStream()
                .forEach(x->{
                    x.setName("VICTORY");
                    x.setDescription(("VICTORY"));
                });
        logger.log(Level.INFO,"Name und Description wurde auf VICTORY gesetzt!");
    }
    public boolean allactivated(){
        int count = 0;
        for(IObtainble x:upgradeliste){
            if(x.getStatus()){
                count++;
            }
            if(count==upgradeliste.size()){
                logger.log(Level.INFO,"Alle sind aktiviert!");
                return true;
            }
        }
        logger.log(Level.INFO,"Nicht alle sind aktiviert!");
        return false;

    }
    public int domultiply(){
        return mmanager.multiply(upgradeliste);
    }
        public void setlist(List<IObtainble> list) {
        this.upgradeliste = list;
        logger.log(Level.INFO,"Upgrade List wurde gesetzt!");    
    }
    public List<IObtainble> getlist() {
        logger.log(Level.INFO,"Kopie von Upgrade List wurde zurückgegeben!");
        return new ArrayList<IObtainble>(upgradeliste);
    }
}

