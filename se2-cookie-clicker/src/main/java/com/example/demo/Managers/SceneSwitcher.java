package com.example.demo.Managers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Controller.ControllerSceneAchievement;
import com.example.demo.Controller.ControllerSceneUpgrade;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher implements ISceneSwitcher{
    private static final Logger logger= Logger.getLogger(SceneSwitcher.class.getName());
    private static SceneSwitcher instance;
    private Stage primaryStage;
    
    // FIXED: Correct paths to FXML files in the fxml-files folder
    public static final String HOME_SCENE = "/fxml-files/HomeScene.fxml";
    public static final String UPGRADE_SCENE = "/fxml-files/UpgradeScene.fxml";
    public static final String ACHIEVEMENT_SCENE = "/fxml-files/AchievementScene.fxml";
    public static final String SETTING_SCENE = "/fxml-files/SettingScene.fxml";
    
    private SceneSwitcher(){}
    SoundManager soundManager=SoundManager.getInstance();
    
    public static SceneSwitcher getInstance() {
        if (instance == null){
            instance = new SceneSwitcher();
            logger.info("Manager wurde erstellt!");
            }
            logger.info("Manager wurde zurückgegeben!");
            return instance;
    }
    
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage=primaryStage;
    }
    
    @Override
    public  void switchScene(String filename)throws SoundManagerException{
            try {
                FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource(filename));
                Parent root = fxmlLoader.load();
                if(filename.equals(UPGRADE_SCENE)){
                    ControllerSceneUpgrade Controller=fxmlLoader.getController();
                    Controller.Upgradeadd();
                }
                if(filename.equals(ACHIEVEMENT_SCENE)){
                    ControllerSceneAchievement Controller=fxmlLoader.getController();
                    Controller.Achievementadd();
                }

                primaryStage.setScene(new Scene(root,850,600));
                soundManager.playSound('s');
                logger.log(Level.INFO,"Die Szene wurde zu " + filename +" gewechselt!");
            } catch (IOException e) {
                logger.log(Level.SEVERE,"Fehler mit dem FXMLLoader beim Laden der Szene: " + filename, e);
            }
    }
}