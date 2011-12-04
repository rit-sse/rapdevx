package edu.rit.se.sse.rapdevx.audio;

import java.util.ArrayList;
import java.util.List;

import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

public class AudioManager {

	private AudioManager() {}
	
	private static AudioManager INSTANCE = null;
	public static AudioManager get() {
		if (INSTANCE == null) {
			INSTANCE = new AudioManager();
		}
		
		return INSTANCE;
	}
	
	private List<AudioDataStream> playingStreams = new ArrayList<AudioDataStream>();
	private List<ThreadedRunner> runningThreads = new ArrayList<ThreadedRunner>();
	
	public void stopAll() {
		// TODO investigate any more stupid weird shit issues with where/when audio
		//  plays.  AKA, don't double play stuff anymore.
		
		for (AudioDataStream stream : playingStreams) {
			AudioPlayer.player.stop(stream);
		}
		
		for (ThreadedRunner thread : runningThreads) {
			thread.setStop(true);
		}
		
		runningThreads.clear();
		playingStreams.clear();
	}
	
	public void play(ILoadableSound song) {

		AudioDataStream songStream = song.getStream(false);
		if (songStream != null) {
			AudioPlayer.player.start(songStream);
			playingStreams.add(songStream);
		}
		
	}
	
	public void play(ILoadableSound start, ILoadableSound toLoop) {
		
		ThreadedRunner tr = new ThreadedRunner(start, toLoop);
		tr.setDaemon(true);
		runningThreads.add(tr);
		tr.start();
		
	}
	
	public class ThreadedRunner extends Thread {
		
		private ILoadableSound str1;
		private ILoadableSound str2;
		private boolean stopASAP;
		
		public ThreadedRunner(ILoadableSound str1, ILoadableSound str2) {
			this.str1 = str1;
			this.str2 = str2;
			this.stopASAP = false;
		}
		
		public void setStop(boolean toStop) {
			stopASAP = toStop;
		}
		
		public void run() {

			AudioDataStream startStream = str1.getStream(false);
			AudioDataStream loopStream = str2.getStream(true);
			
			if (startStream != null && !stopASAP) {
				AudioPlayer.player.start(startStream);
				playingStreams.add(startStream);
			}
			
			try {
				Thread.sleep(str1.getSongLength());
				playingStreams.remove(startStream);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Oh god, threading problem for audio...");
			}
			
			if (loopStream != null && !stopASAP) {
				AudioPlayer.player.start(loopStream);
				playingStreams.add(loopStream);
			}
			
		}
		
	}
}
