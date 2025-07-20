package com.example.demo.Managers;
import com.example.demo.Factory.ObtainbleFactory;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Factory.Typ;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
public class AchievementManager implements IAListManager{
    private static final Logger logger= Logger.getLogger(AchievementManager.class.getName());
    private static AchievementManager instance;
    public List<IObtainble> achievementList;
    public static AchievementManager getInstance() {
        if (instance == null){
            instance = new AchievementManager();
            logger.info("Manager wurde erstellt!");
        }
        logger.info("Manager wurde zurückgegeben!");
        return instance;
    }
public AchievementManager(){
    achievementList = new ArrayList<>();
    achievementList.add(ObtainbleFactory.create(10, false, "First taste of sweetness", "Cookie Beginner", 0, Typ.Achievement));
    achievementList.add(ObtainbleFactory.create(50, false, "Getting the hang of it", "Cookie Enthusiast", 0, Typ.Achievement));
    achievementList.add(ObtainbleFactory.create(200, false, "Serious cookie business", "Cookie Expert", 0, Typ.Achievement));
    achievementList.add(ObtainbleFactory.create(1000, false, "Master of cookies", "Cookie Master", 0, Typ.Achievement));
    logger.log(Level.INFO,"Arraylist wurde mit Achievements befüllt!");
}
    public boolean allactivated(){
        int count = 0;
        for(IObtainble x:achievementList){
            if(x.getStatus()){
                count++;
            }
            if(count==achievementList.size()){
                logger.info("Alle sind aktiviert!");
                return true;
            }
        }
        logger.log(Level.INFO,"Nicht alle sind aktiviert!");
        return false;
    }
    public void activatevictory(){
        achievementList.parallelStream()
                .forEach(x->{
                    x.setName("VICTORY");
                    x.setDescription("VICTORY");
                });
        logger.log(Level.INFO,"Name und Description wurde auf VICTORY gesetzt!");
    }
    public void setlist(List<IObtainble> list) {
        this.achievementList = list;
        logger.log(Level.INFO,"Achievement List wurde gesetzt!");    
    }
    public List<IObtainble> getlist() {
        logger.log(Level.INFO,"Kopie von Achievement List wurde zurückgegeben!");
        return new ArrayList<IObtainble>(achievementList);
    }
}
