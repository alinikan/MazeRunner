package com.project.group13.backend;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;


/**
 * Manages the playback of sound effects and background music in the game.
 * Provides methods to play, toggle, and stop sounds based on game settings.
 * It uses a wrapper around javax.sound.sampled.AudioSystem to facilitate testing.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SoundManager {

    // Flag to enable or disable sound effects.
    public static boolean SOUND_ON = true;
    // Flag to enable or disable background music.
    public static boolean MUSIC_ON = true;
    // Instance to manage background music.
    public static BGM bgm = null;

    // Wrapper for the audio system.
    private static IAudioSystemWrapper audioSystemWrapper = new AudioSystemWrapperImpl();

    /**
     * Toggles the sound effect on or off.
     */
    public static void toggleSound() {
        SOUND_ON = !SOUND_ON;
    }

    /**
     * Toggles the background music on or off and handles the playback.
     */
    public static void toggleMusic() {
        MUSIC_ON = !MUSIC_ON;
        BGM();
    }

    /**
     * Manages the background music playback based on the MUSIC_ON flag.
     */
    public static void BGM() {
        if (MUSIC_ON) {
            SoundManager.bgm = new BGM(AudioConstants.BGM_PATH);
            SoundManager.bgm.start();
        } else {
            if (SoundManager.bgm != null) {
                SoundManager.bgm.stopBGM();
            }
        }
    }

    /**
     * Plays the game_lost sound effect.
     */
    public static void playDieSound() {
        playSound(AudioConstants.GAME_LOST_SOUND_PATH, false);
    }

    /**
     * Plays the collect sound effect.
     */
    public static void playCollectSound() {
        playSound(AudioConstants.COLLECT_SOUND_PATH, false);
    }

    /**
     * Plays the hurt sound effect.
     */
    public static void playDamageSound() {
        playSound(AudioConstants.HURT_SOUND_PATH, false);
    }

    /**
     * Plays the game_win sound effect.
     */
    public static void playWinSound() {
        playSound(AudioConstants.GAME_WIN_SOUND_PATH, false);
    }

    /**
     * Plays the click_treble sound effect.
     */
    public static void playClickSound() {
        playSound(AudioConstants.CLICK_TREBLE_SOUND_PATH, false);
    }

    /**
     * Private method to play a specified sound effect.
     *
     * @param filePath The path to the sound effect file.
     * @param loop     True if the sound should loop, false otherwise.
     */
    private static void playSound(String filePath, boolean loop) {
        if (SOUND_ON) {
            Sound s = new Sound(filePath, loop, audioSystemWrapper);
            s.start();
        }
    }

    /**
     * Implementation of the IAudioSystemWrapper interface to wrap AudioSystem functions.
     */
    static class AudioSystemWrapperImpl implements IAudioSystemWrapper {
        @Override
        public AudioInputStream getAudioInputStream(InputStream stream) throws UnsupportedAudioFileException, IOException {
            return javax.sound.sampled.AudioSystem.getAudioInputStream(stream);
        }

        @Override
        public Clip getClip(DataLine.Info info) throws LineUnavailableException {
            return (Clip) javax.sound.sampled.AudioSystem.getLine(info);
        }
    }
}
