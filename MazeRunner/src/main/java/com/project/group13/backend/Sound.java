package com.project.group13.backend;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * The `Sound` class represents a sound effect that can be played in the game. It extends the `Thread` class to
 * allow asynchronous sound playback. This class handles loading and playing sound files in different formats.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Sound extends Thread {
    // The path to the sound file.
    private final String filepath;
    // Flag indicating whether the sound should loop.
    private final boolean loop;
    // Wrapper for audio system functions.
    private final IAudioSystemWrapper audioSystemWrapper;

    /**
     * Constructs a new `Sound` object.
     *
     * @param filepath           The path to the sound file.
     * @param loop               True if the sound should loop, false otherwise.
     * @param audioSystemWrapper The audio system wrapper to use for audio operations.
     */
    public Sound(String filepath, boolean loop, IAudioSystemWrapper audioSystemWrapper) {
        this.filepath = filepath;
        this.loop = loop;
        this.audioSystemWrapper = audioSystemWrapper;
    }

    /**
     * Runs the sound playback logic in a separate thread.
     */
    @Override
    public void run() {
        try {
            InputStream inputStream = Sound.class.getResourceAsStream(filepath);
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = audioSystemWrapper.getAudioInputStream(bufferedInputStream);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = audioSystemWrapper.getClip(info);
            clip.open(audioInputStream);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
            Thread.sleep(100);
            while (clip.isRunning() || loop) {
                Thread.sleep(100);
            }
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
