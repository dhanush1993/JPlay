/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.ArrayList;
import java.util.Queue;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Dhanush
 */
public interface jplayInterface {
    
    public void play()throws Exception;
    
    public void pause()throws Exception;
    
    public void stop()throws Exception;
    
    public MediaPlayer getMediaPlayer()throws Exception;
    
    public void setMediaPlayer(MediaPlayer player)throws Exception;
    
    public void setMediaPlayer(String path)throws Exception;
    
    public void playNext() throws Exception;
    
    public void playPrevious() throws Exception;
    
    public String getFileURI();
    
    public void setFileURI(String path);
    
    public void setMediaQueue(ArrayList<String> queue);
    
    public ArrayList<String> getMediaQueue();
    
    public void addToQueue(String path);
    
    public void deleteFromQueue(String path);
    
    public void deleteFromQueue(int index);
    
    public String getFromQueue(int index);
    
    public MediaPlayer.Status getMediaStatus();
    
    public void setCurrentIndex(int index);
    
    public int getCurrentIndex();
    
    public void clearQueue();
    
}
