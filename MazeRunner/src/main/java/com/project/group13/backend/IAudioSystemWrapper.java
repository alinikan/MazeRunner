package com.project.group13.backend;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface to wrap around the static methods of javax.sound.sampled.AudioSystem.
 * This allows for easier testing and mocking of audio system functionalities.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface IAudioSystemWrapper {
    /**
     * Gets an audio input stream from the provided input stream.
     * This method wraps around AudioSystem.getAudioInputStream, allowing it to be mocked for testing.
     *
     * @param stream The input stream from which the audio input stream is obtained.
     * @return An AudioInputStream object.
     * @throws UnsupportedAudioFileException If the file does not contain valid audio file data recognized by the system.
     * @throws IOException If an I/O exception occurs.
     */
    AudioInputStream getAudioInputStream(InputStream stream) throws UnsupportedAudioFileException, IOException;
    /**
     * Obtains a clip that can be used for playing back an audio file.
     * This method wraps around AudioSystem.getLine, specifically for obtaining a Clip instance.
     *
     * @param info A DataLine.Info object specifying the desired line.
     * @return A Clip object that can be used to play an audio file.
     * @throws LineUnavailableException If a line matching the description in the info object is not available.
     */
    Clip getClip(DataLine.Info info) throws LineUnavailableException;
}
