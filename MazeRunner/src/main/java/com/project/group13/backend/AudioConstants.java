package com.project.group13.backend;

/**
 * Contains constants representing the file paths of various audio resources used in the game.
 * This class centralizes the management of audio resource paths, making it easier to update
 * and maintain the paths to background music and sound effects used throughout the game.
 * Each constant in this class is a static final String representing a path to a specific audio file.
 * Usage of these constants instead of hardcoded strings throughout the codebase helps in
 * maintaining a cleaner and more manageable code structure, especially when dealing with
 * multiple sound resources.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class AudioConstants {
    public static final String BGM_PATH = "/audio/bgm.mid";
    public static final String GAME_LOST_SOUND_PATH = "/audio/game_lost.wav";
    public static final String COLLECT_SOUND_PATH = "/audio/collect.wav";
    public static final String HURT_SOUND_PATH = "/audio/hurt.wav";
    public static final String GAME_WIN_SOUND_PATH = "/audio/game_win.wav";
    public static final String CLICK_TREBLE_SOUND_PATH = "/audio/click_treble.wav";
}

