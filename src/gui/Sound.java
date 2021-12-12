package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    File[] soundURL = new File[30];

    public Sound() {
        soundURL[0] = new File("res\\sound\\theme.wav");
        soundURL[1] = new File("res\\sound\\drop_bomb.wav");
        soundURL[2] = new File("res\\sound\\explosion.wav");
        soundURL[3] = new File("res\\sound\\life_lost.wav");
        soundURL[4] = new File("res\\sound\\get_item.wav");
        soundURL[5] = new File("res\\sound\\move_down_up.wav");
        soundURL[6] = new File("res\\sound\\move_right_left.wav");
        soundURL[7] = new File("res\\sound\\next_level.wav");
        soundURL[8] = new File("res\\sound\\bomber_die_end_game.wav");
    }

    public void setFile(int idx) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[idx]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            System.out.println("hihi");
        }
    }

    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
}
