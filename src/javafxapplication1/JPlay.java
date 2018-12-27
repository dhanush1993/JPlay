/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Dhanush
 */
public class JPlay implements jplayInterface{
    
    private MediaPlayer mediaPlayer;
    private ArrayList<String> queue;
    private int curIndex;
    
    public JPlay(){
        this.queue = new ArrayList<String>();
    }

    @Override
    public void play() throws Exception {
        try{
            this.mediaPlayer.play();
        }catch(Exception e){
            throw new Exception();
        }
    }

    @Override
    public void pause() throws Exception {
        try{
            this.mediaPlayer.pause();
        }catch(Exception e){
            throw new Exception();
        }
    }

    @Override
    public void stop() throws Exception {
        try{
            this.mediaPlayer.stop();
        }catch(Exception e){
            throw new Exception();
        }
    }

    @Override
    public MediaPlayer getMediaPlayer() throws Exception {
       return this.mediaPlayer;
    }

    @Override
    public void setMediaPlayer(MediaPlayer player) throws Exception {
        try{
            this.mediaPlayer = player;
        }catch(Exception e){
            throw new Exception();
        }
    }

    @Override
    public void playNext() throws Exception {
        this.curIndex = this.curIndex+1;
        this.play();
    }

    @Override
    public void playPrevious() throws Exception {
        this.curIndex = this.curIndex-1;
        this.play();
    }

    @Override
    public String getFileURI() {
       return this.queue.get(this.curIndex);
    }

    @Override
    public void setFileURI(String path) {
        this.queue.add(path);
    }

    @Override
    public void setMediaQueue(ArrayList<String> queue) {
        this.queue = queue;
    }

    @Override
    public ArrayList<String> getMediaQueue() {
        return this.queue;
    }

    @Override
    public void addToQueue(String path) {
        this.queue.add(path);
    }

    @Override
    public void deleteFromQueue(String path) {
        this.queue.remove(path);
    }

    @Override
    public void deleteFromQueue(int index) {
        this.queue.remove(index);
    }

    @Override
    public String getFromQueue(int index) {
        return this.queue.get(index);
    }

    @Override
    public void setMediaPlayer(String path) throws Exception {
        Media media = new Media(path);
        try{
            this.mediaPlayer.dispose();
        }catch(Exception e){
            //throw new Exception();
        }finally{
            this.mediaPlayer = new MediaPlayer(media);
        }
    }

    @Override
    public MediaPlayer.Status getMediaStatus() {
        try{
            return this.mediaPlayer.getStatus();
        }catch(Exception e){
            return MediaPlayer.Status.UNKNOWN;
        }
    }

    @Override
    public void setCurrentIndex(int index) {
        this.curIndex = index;
    }

    @Override
    public int getCurrentIndex() {
        return this.curIndex;
    }

    @Override
    public void clearQueue() {
        this.queue.clear();
    }
    
}
