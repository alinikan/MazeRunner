package com.project.group13;

import com.project.group13.backend.IAudioSystemWrapper;
import com.project.group13.backend.Sound;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundTest {

    @Test
    void testSoundPlaying() throws Exception {
        IAudioSystemWrapper mockAudioSystemWrapper = mock(IAudioSystemWrapper.class);
        String testPath = "/audio/test.wav";
        Sound sound = new Sound(testPath, false, mockAudioSystemWrapper);
        Clip mockClip = mock(Clip.class);
        AudioInputStream mockStream = mock(AudioInputStream.class);
        AudioFormat mockFormat = new AudioFormat(8000.0f, 16, 1, true, false);

        when(mockStream.getFormat()).thenReturn(mockFormat);
        when(mockAudioSystemWrapper.getAudioInputStream(any(InputStream.class))).thenReturn(mockStream);
        when(mockAudioSystemWrapper.getClip(any(DataLine.Info.class))).thenReturn(mockClip);

        sound.run();

        verify(mockClip, times(1)).open(any(AudioInputStream.class));
        verify(mockClip, times(1)).start();
        verify(mockClip, atLeastOnce()).isRunning();
    }
}
