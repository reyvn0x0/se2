package com.example.demo.Controller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PurchaseException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Managers.CounterManager;
import com.example.demo.Managers.ICountable;
import com.example.demo.Managers.ISoundManager;
import com.example.demo.Managers.IUListManager;
import com.example.demo.Managers.SoundManager;
import com.example.demo.Managers.UpgradeManager;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
public class ControllerUpgradePanel {
    private static final Logger logger= Logger.getLogger(ControllerUpgradePanel.class.getName());
    ICountable counterManager=CounterManager.getInstance();
    IUListManager upgradeManager=UpgradeManager.getInstance();
    ISoundManager soundManager= SoundManager.getInstance();
    @FXML
    GridPane Upgradebox;
    @FXML
    Label Name;
    @FXML
    Label Description;
    @FXML
    Label Cost;
    @FXML
    ImageView Buy;
    @FXML
    Image image = new Image(getClass().getResource("/Images/Checked.png").toExternalForm());
    public void initialize(){
        logger.info("ControllerUpgradePanel wurde initaliziert!");
    }
    public void Statuschange()throws SoundManagerException{
        List<IObtainble> upgradeList = upgradeManager.getlist();
       try{
        for(IObtainble x: upgradeList){
           if(!x.getName().equals("VICTORY")&&x.getName().equals(Name.getText())&&counterManager.getCounter()>=x.getCost()){
               Buy.setImage(image);
               Upgradebox.setBackground(new Background(new BackgroundFill(Color.web("#35E09D"), CornerRadii.EMPTY, Insets.EMPTY)));
               soundManager.playSound('u');
               x.setStatus(true);
               counterManager.setCounter(counterManager.getCounter()-x.getCost());
               logger.log(Level.INFO,"Upgrade wurde gekauft!");
               break;
           }
           else if(x.getName().equals("VICTORY")){
             logger.log(Level.INFO,"Du hast berteits alles gekauft!");
           }
           else if(x.getName().equals(Name.getText())&&x.getStatus()==true){
               logger.log(Level.INFO,"Upgrade wurde bereits gekauft!");
           }
           else{
               throw new PurchaseException("Du hast auf etwas geklickt, was man nicht kaufen kann!");
           }
        }
        }catch(PurchaseException e){
            logger.log(Level.INFO,"Fehler beim Kauf des Upgrades: " + e.getMessage());
        }
       upgradeManager.setlist(upgradeList);
    }
}
