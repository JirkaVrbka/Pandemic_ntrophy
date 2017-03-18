/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.FX;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IForcedDestination;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.handlers.IRoundHandler;
import pandemic.Interfaces.handlers.ISpeedHandler;
import pandemic.enums.EDisease;
import pandemic.globals.Global;
import pandemic.globals.Initiator;
import pandemic.impl.background.ArrayBackground;
import pandemic.impl.handlers.RoundHandler;
import pandemic.impl.handlers.SpeedHandler;
import pandemic.impl.worldComponents.MapPR;
import pandemic.impl.Painter;
import pandemic.impl.worldComponents.SingleDestination;
import pandemic.impl.World;

/**
 *
 * @author Jirka
 */
public class FXMLDocumentController implements Initializable {
    
    ////local variables////
    private Timeline timeline;
    
    private Painter painter;
    
    //private static int round = 0;
    
    private World world;
    
    /**
     * Handles speed
     */
    private ISpeedHandler handlerSpeed;
    
    /**
     * Handles round, days
     */
    private IRoundHandler handleRound;
    
    private boolean isRunning = false;
    
    //////////////////////
    
    @FXML
    private Button buttonStop;
    @FXML
    private Slider sliderSpeed;
    @FXML
    private Slider sliderMortality;
    @FXML
    private Button buttonReset;
    @FXML
    private Slider sliderInfectivity;
    @FXML
    private Label labelInfoSick;
    @FXML
    private Label labelInfoHealthy;
    @FXML
    private Label labelInfoAll;
    @FXML
    private Label labelInfoDead;
    @FXML
    private Label labelInfoStatus;
    @FXML
    private Label labelDebugInfectivityValue;
    @FXML
    private Label labelDebugSpeedValue;
    @FXML
    private Label labelDebugMortalityValue;
    @FXML
    private Label labelDebugRound;
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonOneStep;
    @FXML
    private Tab tabDiseaseFlu;
    @FXML
    private Tab tabDiseasePatogen;
    @FXML
    private Tab tabDiseaseDiabetes;
    @FXML
    private Tab tabDiseaseRadiation;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label labelDebugDiseaseType;
    @FXML
    private Label labelDebugDiseaseInfectivity;
    @FXML
    private Label labelDebugDiseaseMortality;
    @FXML
    private Pane paneDebug;
    @FXML
    private Tab tabDiseaseRage;
    @FXML
    private Tab tabDiseaseZombie;
    @FXML
    private Tab tabDiseaseLover;
    @FXML
    private Label labelDebugCureValue;
    @FXML
    private Label labelDebugCureProgress;
    @FXML
    private Slider sliderCure;
    @FXML
    private Label labelDebugCureSpeed;
    @FXML
    private Label labelInfoDay;
    @FXML
    private ProgressBar progressBarCureProgress;
    @FXML
    private Label labelInfoSliderCure;
    @FXML
    private Label labelInfoSliderSpeed;
    @FXML
    private Label labelInfoSliderMortality;
    @FXML
    private Label labelInfoSliderInfectivity;
    @FXML
    private Button buttonCureDiffDecrease;
    @FXML
    private Button buttonInfectivityDecrease;
    @FXML
    private Button buttonSpeedDecrease;
    @FXML
    private Button buttonMortalityDecrease;
    @FXML
    private Button buttonCureDiffIncrease;
    @FXML
    private Button buttonInfectivityIncrease;
    @FXML
    private Button buttonSpeedIncrease;
    @FXML
    private Button buttonMortalityIncrease;
    @FXML
    private Pane paneNotepad;
    @FXML
    private Label labelGameInfo;
    @FXML
    private AnchorPane anchorPaneBackground;
    @FXML
    private Pane panePlayer;
    @FXML
    private Button buttonDestinationIncrease1;
    @FXML
    private Button buttonDestinationDecrease;
    @FXML
    private Label labelInfoSliderDestination;
    @FXML
    private Slider sliderDestination;
    @FXML
    private Canvas canvasPlayground;
    @FXML
    private Label labelInfoCurePercent;
    @FXML
    private Hyperlink hyperlinkInfoRules;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDebugVisibility(false);
        
        handlerSpeed = new SpeedHandler(0);
        handleRound = new RoundHandler(handlerSpeed);
        
        setListenersButtons();
        setListenersSliders();
        setListenersTabs();
        
        
        
        
        int selected = tabPane.getSelectionModel().getSelectedIndex();
        initGame(EDisease.byInteger(selected));
        
        // need to be done after initialization of game (uses its instance)
        sliderMortality.setValue(500);
        sliderInfectivity.setValue(10);
    } 
    
    /**
     * Sets visibility of debug console by parameter
     * @param isDebugOn if true, debug console will be shown
     */
    private void setDebugVisibility(boolean isDebugOn){
        paneDebug.setVisible(isDebugOn);
        paneNotepad.setVisible(!isDebugOn);
        
    }
    
    
    /**
     * One step of life on pandemic
     */
        private void oneStep(){
        int inRound = handlerSpeed.getSpeed();
        
        do{
            //round++;
            world.doRound();
            painter.drawPoints(world.getMovedFrom());
            inRound--;
        }while(inRound > 0);
        
        handleRound.addRound();
        painter.drawPeople(world.getPR().getAsMap());
        setInfomations();
        setDebugInfo();
        
        
    }
    
    
    
    /**
     * Write debug informations into GUI
     * Only for developing
     */
    private void setDebugInfo(){
        labelDebugRound.setText(String.valueOf(handleRound.getRound()));
        
        labelDebugInfectivityValue.setText(String.valueOf(sliderInfectivity.getValue()));
        labelDebugMortalityValue.setText(String.valueOf(sliderMortality.getValue()));
        labelDebugSpeedValue.setText(String.valueOf(handlerSpeed.getSpeed()));
        labelDebugCureValue.setText(String.valueOf(world.getDisease().getCureDifficuty()));
        
        labelDebugDiseaseType.setText(world.getDisease().getClass().getSimpleName());
        labelDebugDiseaseInfectivity.setText(String.valueOf(world.getDisease().getInfectivity()));
        labelDebugDiseaseMortality.setText(String.valueOf(world.getDisease().getMortality()));
        labelDebugCureProgress.setText(String.valueOf(world.getCure().getCureProgress()));
        labelDebugCureSpeed.setText(String.valueOf(world.getCure().getCurrentCureDevelopSpeed()));
        
        
        
    }
    
    /**
     * Write informations into GUI
     * Informations are for players
     */
    private void setInfomations(){
        
        World.Statistics stats = world.getStatistics();
        int ill, healthy, dead, all;
        ill = stats.getIll();
        healthy = stats.getHealthy();
        dead = stats.getDead();
        all = world.getCountPeople();
        
        //informations
        labelInfoAll.setText(String.valueOf(all)); 
        labelInfoHealthy.setText(String.valueOf(healthy));
        labelInfoSick.setText(String.valueOf(ill));
        labelInfoDead.setText(String.valueOf(dead));
        labelInfoDay.setText(String.valueOf(handleRound.getRound()));
        
        //progress bar
        double cureProgress = world.getCure().getCureProgress();
        progressBarCureProgress.setProgress(cureProgress/100);
        labelInfoCurePercent.setText(String.valueOf(cureProgress >= 100 ? 100 : (int)cureProgress) + "%");
        
        //slider values
        labelInfoSliderCure.setText(String.valueOf((int)sliderCure.getValue()));
        labelInfoSliderMortality.setText(String.valueOf((int)sliderMortality.getValue()));
        labelInfoSliderInfectivity.setText(String.valueOf((int)sliderInfectivity.getValue()));
        labelInfoSliderSpeed.setText(handlerSpeed.getAsString());
        labelInfoSliderDestination.setText(String.valueOf((int)sliderDestination.getValue()));
        
        if(dead == all){
            labelInfoStatus.setText("Vsichni jsou mrtvi Dejve");
            timeline.stop();
        }else if(ill == 0){
            labelInfoStatus.setText("Nekdo prezil");
            //timeline.stop();
        }else{
            labelInfoStatus.setText("Pracuji na tom");
        }
    }
    
    
       
    
    private void initGame(EDisease disease){
        int zoom = 5;
        IBackground background = new ArrayBackground((int)(canvasPlayground.getWidth()/zoom),(int)(canvasPlayground.getHeight()/zoom));
        
        IPR pr = new MapPR(background, zoom);
        
        IDisease dis = Initiator.initDisease(disease, pr);
        
        IForcedDestination dest = new SingleDestination(new Point(50,60), 0);
        
        world = new World(pr, background, dis, dest);
        world.generatePeopleToWorldRandom(116, 4);
        
        painter = new Painter(canvasPlayground, background, zoom);
        
        painter.drawBackground();
        painter.drawPeople(world.getPR().getAsMap());
        
        world.getDisease().setInfectivity((int)(sliderInfectivity.getValue()));
        world.getDisease().setMortality((int)(sliderMortality.getValue()));
        world.getDisease().setCureDifficuty((int)(sliderCure.getValue()));
        world.getForcedDestination().setProbability((int)(sliderDestination.getValue()));
        
        changeTimeline();
        handleRound.resetToZero();
        
        setInfomations();
        setDebugInfo();
        
    }
    
    private void changeTimeline(){
        int newTiming;
        
        if(handlerSpeed.getSpeed() <= 0){
            newTiming = Global.Variables.TIMELINE_SPEED_DEFAULT + (Math.abs(handlerSpeed.getSpeed())*20);
        }else{
            newTiming = Global.Variables.TIMELINE_SPEED_DEFAULT;
        }
        
         
        
        if(timeline != null && isRunning == true){
            timeline.stop();
        }
        
        timeline = new Timeline(new KeyFrame((Duration.millis(newTiming)), ae->oneStep()));
        timeline.setCycleCount(Animation.INDEFINITE);
        
        
    }

    
    
    
    
    
    
    
    /**
     * Sets sliders their listeners to save their values into code
     */
    private void setListenersSliders(){
        sliderInfectivity.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                world.getDisease().setInfectivity(newValue.intValue());
                sliderInfectivity.setValue(newValue.intValue());
                setDebugInfo();
                setInfomations();
            }
        });
        
        sliderMortality.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                world.getDisease().setMortality(2*newValue.intValue());//world.getDisease().setMortality(newValue.intValue());
                sliderMortality.setValue((newValue.intValue()));
                setDebugInfo();
                setInfomations();
            }
        });
        
        sliderDestination.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                world.getForcedDestination().setProbability(newValue.intValue());//world.getDisease().setMortality(newValue.intValue());
                sliderDestination.setValue((newValue.intValue()));
                setDebugInfo();
                setInfomations();
            }
        });
        
        sliderSpeed.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sliderSpeed.setValue(newValue.intValue());
                handlerSpeed.setSpeed(newValue.intValue());
                changeTimeline();
                if(isRunning == true){
                    timeline.play();
                }
                setDebugInfo();
                setInfomations();
            }
        });
        
        sliderCure.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                world.getDisease().setCureDifficuty(newValue.intValue());
                sliderCure.setValue(newValue.intValue());
                setDebugInfo();
                setInfomations();
            }
        });
        
    }
    
    public void setListenersHyperlink(EventHandler<ActionEvent> handler){
        hyperlinkInfoRules.setOnAction(handler);
    }
    
    
    /**
     * Sets listeners to buttons to be able start, stop, reset and go by step in game
     */
    private void setListenersButtons(){
        buttonStop.setOnAction((ActionEvent event) -> {
            timeline.stop();
            isRunning = false;
        });
        
        buttonPlay.setOnAction((ActionEvent event) -> {
            timeline.play();
            isRunning = true;
        });
        
        buttonReset.setOnAction((ActionEvent event) -> {
            //call handler to cal initGame with correct 
            tabPane.getSelectionModel().getSelectedItem().getOnSelectionChanged().handle(new ActionEvent());
            isRunning = false;
            
        });
        
        buttonOneStep.setOnAction((ActionEvent event) -> {
            timeline.pause();
            isRunning = false;
            int saveSpeed = handlerSpeed.getSpeed();
            handlerSpeed.setSpeed(0);
            oneStep();
            handlerSpeed.setSpeed(saveSpeed);
            setDebugInfo();
            setInfomations();
        });
        
        buttonCureDiffDecrease.setOnAction((ActionEvent event) -> {
            sliderCure.setValue(sliderCure.getValue()-1);
        });
        
        buttonCureDiffIncrease.setOnAction((ActionEvent event) -> {
            sliderCure.setValue(sliderCure.getValue()+1);
        });
        
        buttonInfectivityDecrease.setOnAction((ActionEvent event) -> {
            sliderInfectivity.setValue(sliderInfectivity.getValue()-1);
        });
        
        buttonInfectivityIncrease.setOnAction((ActionEvent event) -> {
            sliderInfectivity.setValue(sliderInfectivity.getValue()+1);
        });
        
        buttonMortalityDecrease.setOnAction((ActionEvent event) -> {
            sliderMortality.setValue(sliderMortality.getValue()-1);
        });
        
        buttonMortalityIncrease.setOnAction((ActionEvent event) -> {
            sliderMortality.setValue(sliderMortality.getValue()+1);
        });
        
        buttonSpeedDecrease.setOnAction((ActionEvent event) -> {
            sliderSpeed.setValue(sliderSpeed.getValue()-1);
        });
        
        buttonSpeedIncrease.setOnAction((ActionEvent event) -> {
            sliderSpeed.setValue(sliderSpeed.getValue()+1);
        });
    }
    
    /**
     * Set listeners to tabs to init right disease when selected.
     */
    private void setListenersTabs(){
        tabDiseaseFlu.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.FLU);
            }
        });
        
        tabDiseaseDiabetes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.DIABETES);
            }
        });
        
        tabDiseasePatogen.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.PATOGEN);
            }
        });
        
        tabDiseaseRadiation.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.RADIATION);
            }
        });
        
        tabDiseaseRage.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.RAGE);
            }
        });
        
        tabDiseaseZombie.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.ZOMBIE);
            }
        });
        
        tabDiseaseLover.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                initGame(EDisease.LOVERS);
            }
        });
        
    }
}
