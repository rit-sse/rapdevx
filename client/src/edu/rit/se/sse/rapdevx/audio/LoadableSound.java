package edu.rit.se.sse.rapdevx.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * Use this class if you want to load some audio file that I haven't
 *  already planned for, in Sound.java.
 */
public class LoadableSound implements ILoadableSound {

	private String filename = null;
	private int length = 2000;

	public LoadableSound(String filename) {
		this.filename = filename;

		// TODO should we really do such a default?
		this.length = 2000;
	}

	public LoadableSound(String filename, int length) {
		this.filename = filename;
		this.length = length;
	}

	public long getSongLength() {
		return length;
	}

	public AudioDataStream getStream(boolean continuous) {
		try {
			InputStream is = new FileInputStream("rsc"+File.separator+"sounds"+File.separator+filename);

			AudioStream stream = new AudioStream(is);
			AudioData aData = stream.getData();
			if (!continuous) {
				return new AudioDataStream(aData);
			}
			else {
				return new ContinuousAudioDataStream(aData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
