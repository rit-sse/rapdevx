package edu.rit.se.sse.rapdevx.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * It's an enum! Pick a song from the options available! Pass this bad boy into
 * AudioManager and you're all good.
 */
public enum Sound implements ILoadableSound {

	BattleStart("battlestart.wav", 3500);

	private String soundFile = null;
	private long length;

	Sound(String soundFile, long length) {
		this.soundFile = soundFile;
		this.length = length;
	}

	public long getSongLength() {
		return length;
	}

	public AudioDataStream getStream(boolean continuous) {
		try {
			InputStream is = new FileInputStream("assets" + File.separator
					+ "sounds" + File.separator + soundFile);

			AudioStream stream = new AudioStream(is);

			AudioData aData = stream.getData();
			if (!continuous) {
				return new AudioDataStream(aData);
			} else {
				return new ContinuousAudioDataStream(aData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
