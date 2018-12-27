/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Dhanush
 */
public class FXMLDocumentController implements Initializable {
    
    public boolean maximized = false;
    final Image playImage  = new Image(this.getClass().getResource("play.png").toString());
    final Image pauseImage  = new Image(this.getClass().getResource("pause.png").toString());
    final Image prevImage  = new Image(this.getClass().getResource("backward.png").toString());
    final Image nextImage  = new Image(this.getClass().getResource("forward.png").toString());
    final Image stopImage  = new Image(this.getClass().getResource("stop.png").toString());
    final Image expImage  = new Image(this.getClass().getResource("explorer.png").toString());
    final Image expandImage  = new Image(this.getClass().getResource("expand.png").toString());
    final Image compressImage = new Image(this.getClass().getResource("compress.png").toString());
    private ImageView playPauseImageView;
    private ImageView expandImageView;
    private JPlay player;
    @FXML
    private Slider seeker;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button expandBtn;
    @FXML
    private Button expBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button prevBtn;
    @FXML
    private Button playPauseBtn;
    @FXML
    private MediaView mediaView;
    @FXML
    private void playPauseButtonAction(ActionEvent event) {
        try{
            if(!this.player.getMediaStatus().equals(MediaPlayer.Status.UNKNOWN)){
                if(this.player.getMediaStatus().equals(MediaPlayer.Status.PLAYING)){
                    this.player.pause();
                    this.playPauseImageView.setImage(this.playImage);
                }else {
                    this.player.play();
                    this.playPauseImageView.setImage(this.pauseImage);
                }
            }
        }catch(Exception e){
            try{
                this.playMedia();
                this.playPauseImageView.setImage(this.pauseImage);
            }catch(Exception e1){
                System.out.println(e1.getMessage());
            }
        }
    }
    
    @FXML
    private void prevAction(ActionEvent event){
        try{
            this.player.playPrevious();
        }catch(Exception e){
            
        }
    }
    
    @FXML
    private void nextAction(ActionEvent event){
        try{
            this.player.playNext();
        }catch(Exception e){
            
        }
    }
    
    @FXML
    private void stopButtonAction(ActionEvent event) {
        System.out.println("You stopped!");
        try{
            this.player.stop();
            this.playPauseImageView.setImage(this.playImage);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void maximizedAction(ActionEvent event){
        Button btn = (Button)event.getTarget();
        if(this.maximized){
            ((Stage)btn.getScene().getWindow()).setFullScreen(false);
            this.expandImageView.setImage(this.expandImage);
            this.maximized = false;
        }else{
            ((Stage)btn.getScene().getWindow()).setFullScreen(true);
            this.expandImageView.setImage(this.compressImage);
            this.maximized = true;
        }
    }
    
    
    @FXML
    private void fileExplorerAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select Media files","*.mp4");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog(null);
        if(file != null){
            try{
                this.player.stop();
            }catch(Exception e){
                
            }finally{
                this.player.addToQueue(file.toURI().toString());
                this.player.setCurrentIndex(this.player.getMediaQueue().size()-1);
                this.playMedia();
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.player = new JPlay();
        this.playPauseImageView = new ImageView();
        this.playPauseBtn.setGraphic(this.playPauseImageView);
        this.playPauseImageView.setFitWidth(40);
        this.playPauseImageView.setFitHeight(40);
        this.playPauseBtn.setPrefSize(45, 45);
        this.playPauseImageView.setImage(this.playImage);
        
        ImageView prevImageView = new ImageView();
        this.prevBtn.setGraphic(prevImageView);
        prevImageView.setFitHeight(20);
        prevImageView.setFitWidth(20);
        this.prevBtn.setPrefSize(25, 25);
        prevImageView.setImage(this.prevImage);
        
        
        ImageView nextImageView = new ImageView();
        this.nextBtn.setGraphic(nextImageView);
        nextImageView.setFitHeight(20);
        nextImageView.setFitWidth(20);
        this.nextBtn.setPrefSize(25, 25);
        nextImageView.setImage(this.nextImage);
        
        ImageView stopImageView = new ImageView();
        this.stopBtn.setGraphic(stopImageView);
        stopImageView.setFitHeight(20);
        stopImageView.setFitWidth(20);
        this.stopBtn.setPrefSize(25, 25);
        stopImageView.setImage(this.stopImage);
        
        ImageView expImageView = new ImageView();
        this.expBtn.setGraphic(expImageView);
        expImageView.setFitHeight(20);
        expImageView.setFitWidth(20);
        this.expBtn.setPrefSize(25, 25);
        expImageView.setImage(this.expImage);
        
        this.expandImageView = new ImageView();
        this.expandBtn.setGraphic(this.expandImageView);
        this.expandImageView.setFitHeight(40);
        this.expandImageView.setFitWidth(40);
        this.expandBtn.setPrefSize(40, 40);
        this.expandImageView.setImage(this.expandImage);
        try{
            this.volumeSlider.setValue(this.player.getMediaPlayer().getVolume());
        }catch(Exception e){
        
        }
        this.volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                try{
                player.getMediaPlayer().setVolume(volumeSlider.getValue()/100);
                }catch(Exception e){
                    
                }
            }
        });
    } 
    
    private void playMedia(){
        try{
            try{
                this.volumeSlider.setValue(this.player.getMediaPlayer().getVolume()*100);
            }catch(Exception e){
                this.volumeSlider.setValue(100);
            }
            this.player.setMediaPlayer(this.player.getFileURI());
            this.player.play();
            this.mediaView.setMediaPlayer(this.player.getMediaPlayer());
            this.mediaView.fitWidthProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            this.mediaView.fitHeightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            this.playPauseImageView.setImage(this.pauseImage);
            this.seeker.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    try {
                        player.getMediaPlayer().seek(Duration.seconds(seeker.getValue()*player.getMediaPlayer().getTotalDuration().toSeconds()/100));
                    } catch (Exception ex) {
                    }
                }
            });
            StackPane trackPane = (StackPane) this.seeker.lookup(".track");
            this.seeker.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    //int val = (int)newValue;
                    String style = String.format("-fx-background-color: linear-gradient(to right, #FFFFFF %d%%, #606060 %d%%);",
                        newValue.intValue(), newValue.intValue());
                    trackPane.setStyle(style);
                }
            });
            this.player.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    try {
                        seeker.setValue((newValue.toSeconds()/player.getMediaPlayer().getTotalDuration().toSeconds())*100);
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public ImageView getExpandImageView(){
        return expandImageView;
    }
    
    
    
}
