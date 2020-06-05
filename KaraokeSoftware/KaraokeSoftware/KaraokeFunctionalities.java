import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class KaraokeFunctionalities {
	// Song Library
	static HashMap<String, Song> songLibrary = new HashMap<>();

	// Playlist
	static LinkedList<Song> songPlaylist = new LinkedList<>();

	static Song currentSong;

	static void addSong(String songName, String artistName, int songDuration, String fileName) {
		Song newSong = new Song(songName, artistName, songDuration, fileName);
		if (songLibrary.containsKey(newSong.getTitle())) {
			AlertBox.displayPopUp("Song already exists", "Song already exists in library");
		} else {
			songLibrary.put(newSong.getTitle(), newSong);
			AlertBox.displayPopUp("Add song to library", "Song successfully added to library.");
			System.out.println("New song added to library: \n" + newSong);
		}
	}// end of addSong

	static Song searchSongLibrary(String songSearched) {
		if (songLibrary.containsKey(songSearched)) {
			KaraokeInterface.searchSongOutput.setText(songLibrary.get(songSearched).getTitle());
			KaraokeInterface.searchArtistOutput.setText(songLibrary.get(songSearched).getArtist());
			KaraokeInterface.searchDurationOutput
					.setText(String.valueOf(songLibrary.get(songSearched).getRunningTime()));
		} else {
			AlertBox.displayPopUp("Cannot find song", "No such song found in the library.");
		}
		return songLibrary.get(songSearched);
	} // end of searchSongLibrary

	static void addSongToPlaylist(String songName) {
		Song sameSong = null;
		if (songLibrary.containsKey(songName)) {
			sameSong = songLibrary.get(songName);
			if (songPlaylist.contains(sameSong)) {
				AlertBox.displayPopUp("Song already exists", "This song already exists in the playlist");
			} else {
				songPlaylist.add(sameSong);
				displayPlaylist();
				AlertBox.displayPopUp("Song added to playlist", "Song has been added to playlist.");
			}
		} else {
			AlertBox.displayPopUp("Song cannot be added to playlist", "This song does not exist in the library");
		}
	}// end of addSongToPlaylist

	static void displayPlaylist() {
		String outputSongNumber = "";
		int i = 0;
		for (Song song : songPlaylist) {
			outputSongNumber = outputSongNumber + i + " - " + song.getTitle() + " - " + song.getArtist() + " - "
					+ song.getRunningTime() + "\n";
			i++;
		}
		if (outputSongNumber.length() == 0) {
			AlertBox.displayPopUp("Playlist empty", "There are currently no songs in the playlist");
		}
		KaraokeInterface.loadPlaylist.setText(outputSongNumber);
	}// end of displayPlaylist

	static void deleteSongFromPlaylist(int index) {
		try {
			songPlaylist.remove(index);
			displayPlaylist();
			AlertBox.displayPopUp("Song deleted", "Song has been successfully deleted.");
		} catch (IndexOutOfBoundsException ex) {
			AlertBox.displayPopUp("Song cannot be deleted", "Song does not exist.");
		}
	}// end of deleteSongFromPlaylist

	// Load video file
	static MediaPlayer playVideo() {
		// get file path
		String path = currentSong.getFileName();
		Media media = new Media((new File(path)).toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		// play next video
		player.setOnEndOfMedia(() -> playNewSong());
		return player;
	}// end of playVideo

	// Play new song
	static void playNewSong() {
		if (songPlaylist.size() > 0) {
			// Get first song
			currentSong = songPlaylist.pollFirst();
			displayPlaylist();
			if (KaraokeInterface.currentPlayer != null) {
				KaraokeInterface.currentPlayer.stop();
			}
			if (KaraokeInterface.mediaView.isVisible() == false) {
				KaraokeInterface.mediaView.setVisible(true);
			}
			System.out.println("Playing song: " + currentSong.getTitle());
			// Retrieve filename of current song
			KaraokeInterface.currentPlayer = playVideo();
			// Display title of song being played
			KaraokeInterface.songTitleLabel.setText(currentSong.getTitle());
			// Display video
			KaraokeInterface.mediaView.setMediaPlayer(KaraokeInterface.currentPlayer);
			// Play media file
			KaraokeInterface.currentPlayer.play();
			// Display time taken
			KaraokeInterface.displayTimeSong.setText(String.valueOf(currentSong.getRunningTime()));
		} else {
			AlertBox.displayPopUp("Empty playlist", "Playlist is empty.");
		}
	}// end of playNewSong

	// Play song from playlist
	static void playSong() {
		if (currentSong == null) {
			playNewSong();
		} else {
			// play paused song again
			if (KaraokeInterface.currentPlayer.getStatus() == Status.PAUSED) {
				KaraokeInterface.currentPlayer.play();
			} else {
				AlertBox.displayPopUp("Video Playing", "The video is already playing.");
			}
		}
	}// end of playSong

	static void pauseSong() {
		if (KaraokeInterface.currentPlayer != null) {
			if (KaraokeInterface.currentPlayer.getStatus() == Status.PLAYING) {
				// pause media player
				KaraokeInterface.currentPlayer.pause();
			} else {
				AlertBox.displayPopUp("Video already paused", "Video is already paused. Press play to resume.");
			}
		} else {
			AlertBox.displayPopUp("No media file", "No media file found.");
		}

	}// end of pauseSong

}
