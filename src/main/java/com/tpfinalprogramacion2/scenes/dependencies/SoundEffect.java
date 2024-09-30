package com.tpfinalprogramacion2.scenes.dependencies;

import com.tpfinalprogramacion2.services.Resource;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class SoundEffect {
    private static MediaPlayer backgroundMusic;
    private static final AudioClip buttonSound = new AudioClip(Objects.requireNonNull(SoundEffect.class.getResource(Resource.MAIN_MENU_BUTTON_CLICK_SOUND_PATH)).toExternalForm());
    private static boolean isMute = false;

    public static void setBackgroundMusic(String path, double volume) {
        backgroundMusic = new MediaPlayer(new Media(Objects.requireNonNull(SoundEffect.class.getResource(path)).toExternalForm()));
        backgroundMusic.setVolume(volume);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
        backgroundMusic.setMute(isMute);

    }

    public static MediaPlayer getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void toggleMusic() {
        backgroundMusic.setMute(!backgroundMusic.isMute());
        isMute = backgroundMusic.isMute();
    }

    public static void updateMusicIcon(ImageView musicIcon) {
        musicIcon.setImage(new Image(backgroundMusic.isMute() ? Resource.MAIN_MENU_MUSIC_ON_ICON_PATH : Resource.MAIN_MENU_MUSIC_OFF_ICON_PATH));
    }

    public static void playButtonSound(double volume) {
        buttonSound.setVolume(volume);
        buttonSound.play();
    }
}
