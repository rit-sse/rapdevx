package edu.rit.se.sse.rapdevx.audio;

import sun.audio.AudioDataStream;

/**
 * Has required information in order to play audio, both looping or not.
 *  If you're confused, refer to the enum Sound in this package.
 * This gets passed into AudioManager.
 * 
 */
public interface ILoadableSound {
	/**
	 * Required to be able to have a post loop sound.  Two seconds is default
	 * 
	 * @return ms of time to wait before we loop
	 */
	public long getSongLength();
	
	/**
	 * Gets the audio stream information we need to play the song.
	 *  Needs to handle whether or not the song will be played continuously (looped).
	 */
	public AudioDataStream getStream(boolean continuous);
}