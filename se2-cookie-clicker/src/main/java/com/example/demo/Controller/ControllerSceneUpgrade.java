package com.example.demo.Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SceneSwitchException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Main;
import com.example.demo.Managers.BrightnessManager;
import com.example.demo.Managers.IBright;
import com.example.demo.Managers.ISceneSwitcher;
import com.example.demo.Managers.ITimerManager;
import com.example.demo.Managers.IUListManager;
import com.example.demo.Managers.SceneSwitcher;
import com.example.demo.Managers.Timer;
import com.example.demo.Managers.TimerManager;
import com.example.demo.Managers.UpgradeManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
public class ControllerSceneUpgrade{
    private static final Logger logger= Logger.getLogger(ControllerSceneUpgrade.class.getName());
    ISceneSwitcher manager=SceneSwitcher.getInstance();
    IBright bmanager= BrightnessManager.getInstance();
    IUListManager upgradeManager=UpgradeManager.getInstance();
    ITimerManager tmanager= TimerManager.getInstance();
    Image image = new Image(getClass().getResource("/Images/Checked.png").toExternalForm());
    Image starimage = new Image(getClass().getResource("/Images/Star.png").toExternalForm());
    private final ColorAdjust brightnessEffect = new ColorAdjust();
    @FXML
    VBox UpgradeSammlung = new VBox();
    @FXML
    private Pane rootPane;
    public void timeout() throws InterruptedException {
        if(tmanager.getTimer(1).isOn()){
            tmanager.getTimer(1).stopTimer();
            tmanager.setTimer(1, new Timer("timeouttimer"));
        }
        else{
            tmanager.getTimer(1).start();
        }
    }
    public void initialize() {
        brightnessEffect.setBrightness(bmanager.getBrightness());
        rootPane.setEffect(brightnessEffect);
        logger.log(Level.INFO,"ControllerSceneUpgrade wurde initaliziert!");
    }
    public void Upgradeadd(){
        List<IObtainble> upgradeList = upgradeManager.getlist();
        try{
        for (IObtainble y : upgradeList) {
            FXMLLoader fxmlLoaderUpgradepanel = new FXMLLoader(Main.class.getResource("UpgradePanel.fxml"));
            Node Node = fxmlLoaderUpgradepanel.load();
            ControllerUpgradePanel controllerUpgradepanel = fxmlLoaderUpgradepanel.getController();
            controllerUpgradepanel.Cost.setText(String.valueOf(y.getCost()));
            controllerUpgradepanel.Description.setText(y.getDescription());
            controllerUpgradepanel.Name.setText(y.getName());
            if (y.getDescription().equals(controllerUpgradepanel.Name.getText()) &&!y.getName().equals("VICTORY")&&y.getStatus()){
                controllerUpgradepanel.Buy.setImage(image);
                controllerUpgradepanel.Upgradebox.setStyle("-fx-background-color: #35E09D;");
                logger.log(Level.INFO,"Bereits gekaufte Upgrades gesetzt!");
                break;
            }
            else if(y.getDescription().equals("VICTORY")){
                controllerUpgradepanel.Upgradebox.setStyle("-fx-background-color: #D641DE;");
                controllerUpgradepanel.Buy.setImage(starimage);
                logger.log(Level.INFO,"VICTORY MODE aktivert!");
            }
            else{
                throw new PanelLoadException( y.getName());
            }
            UpgradeSammlung.getChildren().add(Node);
        }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler beim Laden des UpgradePanels mit Fxmloader: " + e.getMessage());
        }
        catch (PanelLoadException e) {
            logger.log(Level.WARNING, "Fehler beim Laden des Upgradepanels: " + e.getMessage());
        }
        upgradeManager.setlist(upgradeList);
    }
        public void switchToScene(MouseEvent event) throws SoundManagerException, PanelLoadException {
        try {
            if(365>=event.getSceneX()&&event.getSceneX()>=342)manager.switchScene("HomeScene.fxml");
            else if (398>=event.getSceneX()&&event.getSceneX()>=375)manager.switchScene("UpgradeScene.fxml");
            else if (431>=event.getSceneX()&&event.getSceneX()>=408)manager.switchScene("SettingScene.fxml");
            throw new SceneSwitchException("Ungültige Szene ausgewählt.");
        } catch ( SceneSwitchException e) {
            logger.log(Level.SEVERE,"Fehler beim Wechseln der Szene: " + e.getMessage());
        }
    }

}
