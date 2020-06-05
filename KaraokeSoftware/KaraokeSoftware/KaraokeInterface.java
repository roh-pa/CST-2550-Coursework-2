/*References:
Roberts, B. (2015a). JavaFX Java GUI Tutorial - 5 - Creating Alert Boxes. YouTube. 
Available at: https://www.youtube.com/watch?v=SpL3EToqaXA [Accessed 20 Apr. 2020].
Roberts, B. (2015b). JavaFX Java GUI Tutorial - 9 - GridPane. YouTube. 
Available at: https://www.youtube.com/watch?v=YtKF1JKtRWM [Accessed 20 Apr. 2020].
Stack Overflow. (2015). Java - I want to read a file name from command line then use a bufferedreader to read each line. [online] 
Available at: https://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to [Accessed 21 Apr. 2020].
Stack Overflow. (2016). java - JavaFX Media - pause(); method makes MediaPlayer fast-forward? [online] 
Available at: https://stackoverflow.com/questions/38819690/javafx-media-pause-method-makes-mediaplayer-fast-forward [Accessed 20 Apr. 2020].
www.javatpoint.com. (2018). JavaFX Playing Video - javatpoint. [online] 
Available at: https://www.javatpoint.com/javafx-playing-video [Accessed 21 Apr. 2020].
*/

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class KaraokeInterface extends Application {
	static Label searchSongOutput = new Label();
	static Label searchArtistOutput = new Label();
	static Label searchDurationOutput = new Label();
	static Label loadPlaylist = new Label();
	static Label songTitleLabel = new Label("Song Title");
	static Label displayTimeSong = new Label();
	static File defaultFile = new File("test.mp4");
	static String defaultSource = defaultFile.toURI().toString();
	static Media defaultVideo = new Media(defaultSource);
	static MediaPlayer defaultPlayer = new MediaPlayer(defaultVideo);
	static MediaView mediaView = new MediaView(defaultPlayer);
	static MediaPlayer currentPlayer;

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("Karaoke Application");

			GridPane grid = new GridPane();
			GridPane libraryPane = new GridPane();
			GridPane videoPane = new GridPane();
			GridPane playlistPane = new GridPane();
			GridPane addSongPane = new GridPane();
			GridPane searchLibraryPane = new GridPane();
			GridPane displaySearchPane = new GridPane();
			GridPane displayListPane = new GridPane();
			GridPane videoWindow = new GridPane();
			// Pane to add, pause and skip song
			GridPane songControlPane = new GridPane();
			// Pane for playlist functionalities
			GridPane playlistControlPane = new GridPane();

			GridPane.setMargin(libraryPane, new Insets(10));
			GridPane.setMargin(videoPane, new Insets(10));
			GridPane.setMargin(playlistPane, new Insets(10));
			GridPane.setMargin(addSongPane, new Insets(8));
			GridPane.setMargin(searchLibraryPane, new Insets(8));
			GridPane.setMargin(songControlPane, new Insets(8));
			GridPane.setMargin(playlistControlPane, new Insets(8));
			GridPane.setMargin(displaySearchPane, new Insets(8, 8, 8, 0));
			GridPane.setMargin(displayListPane, new Insets(0, 8, 8, 8));

			grid.add(libraryPane, 0, 0);
			grid.add(videoPane, 1, 0);
			grid.add(playlistPane, 2, 0);

			libraryPane.add(addSongPane, 0, 1);
			libraryPane.add(searchLibraryPane, 0, 2);

			videoPane.add(videoWindow, 0, 1);
			videoPane.add(songControlPane, 0, 2);

			playlistPane.add(playlistControlPane, 0, 2);

			// ---------------------------Library Pane----------------------
			Label libraryLabel = new Label("Song Library");
			libraryPane.add(libraryLabel, 0, 0);
			GridPane.setHalignment(libraryLabel, HPos.CENTER);

			// -----------------------------addSong pane---------------------
			Label addSongLabel = new Label("Add New Song");
			GridPane.setHalignment(addSongLabel, HPos.CENTER);

			Label songNameLabel = new Label("Song name");
			Label artistNameLabel = new Label("Artist name");
			Label songDurationLabel = new Label("Song duration");
			Label fileNameLabel = new Label("Video name");

			TextField inputSongTitle = new TextField();
			inputSongTitle.setPromptText("Song Title");

			TextField inputArtistName = new TextField();
			inputArtistName.setPromptText("Artist Name");

			TextField inputDuration = new TextField();
			inputDuration.setPromptText("Song duration(seconds)");

			TextField inputVideoFile = new TextField();
			inputVideoFile.setPromptText("Video filename");

			Button addSongButton = new Button("Add Song");

			addSongPane.add(addSongLabel, 1, 0);
			addSongPane.add(songNameLabel, 1, 1);
			addSongPane.add(inputSongTitle, 2, 1);
			addSongPane.add(artistNameLabel, 1, 2);
			addSongPane.add(inputArtistName, 2, 2);
			addSongPane.add(songDurationLabel, 1, 3);
			addSongPane.add(inputDuration, 2, 3);
			addSongPane.add(fileNameLabel, 1, 4);
			addSongPane.add(inputVideoFile, 2, 4);
			addSongPane.add(addSongButton, 1, 5);
			GridPane.setColumnSpan(addSongLabel, 2);
			GridPane.setColumnSpan(addSongButton, 2);
			GridPane.setHalignment(addSongButton, HPos.CENTER);

			// Invoke addSong method to store song to library
			addSongButton.setOnAction((ActionEvent e) -> {
				String videoFile = "test.mp4";
				if (inputSongTitle.getText().isEmpty() || inputArtistName.getText().isEmpty()
						|| inputDuration.getText().isEmpty() || inputVideoFile.getText().isEmpty()) {
					AlertBox.displayPopUp("Add song to library", "Please fill in all fields.");
				} else if (!inputDuration.getText().matches("[0-9]+")) {
					AlertBox.displayPopUp("Song duration", "Song duration is in seconds. Only whole numbers allowed.");
				} else if (!(inputVideoFile.getText().equals(videoFile))) {
					AlertBox.displayPopUp("Video file format", "Only test.mp4 accepted");
				} else {
					KaraokeFunctionalities.addSong(inputSongTitle.getText(), inputArtistName.getText(),
							Integer.parseInt(inputDuration.getText()), inputVideoFile.getText());
				}
			});// end of addSong

			addSongButton.setTooltip(new Tooltip("Add a song to the library"));

			// ----------------------------------Search library pane-------------
			Label labelSearch = new Label("Search Library");
			GridPane.setHalignment(labelSearch, HPos.CENTER);
			Button searchLibraryButton = new Button();
			searchLibraryButton.getStyleClass().add("search-button");
			TextField searchSongInput = new TextField();
			searchSongInput.setPromptText("Enter song title");

			Label displaySongTitle = new Label("Song Title ");
			Label displayArtistName = new Label("Artist Name ");
			Label displaySongDuration = new Label("Song duration(s) ");
			displaySongTitle.getStyleClass().add("search-output");
			displaySongDuration.getStyleClass().add("search-output");
			displayArtistName.getStyleClass().add("search-output");

			// Search result window
			displaySearchPane.setPrefHeight(250);
			displaySearchPane.setPrefWidth(300);
			GridPane.setColumnSpan(displaySearchPane, 2);
			displaySearchPane.getStyleClass().add("border");

			searchLibraryPane.add(labelSearch, 0, 0);
			searchLibraryPane.add(searchSongInput, 0, 1);
			searchLibraryPane.add(searchLibraryButton, 1, 1);
			searchLibraryPane.add(displaySearchPane, 0, 2);
			displaySearchPane.add(displaySongTitle, 0, 0);
			displaySearchPane.add(displayArtistName, 0, 1);
			displaySearchPane.add(displaySongDuration, 0, 2);
			displaySearchPane.add(searchSongOutput, 1, 0);
			displaySearchPane.add(searchArtistOutput, 1, 1);
			displaySearchPane.add(searchDurationOutput, 1, 2);

			// Invoke searchSongLibrary method
			searchLibraryButton.setOnAction((ActionEvent e) -> {
				if (searchSongInput.getText().isEmpty()) {
					AlertBox.displayPopUp("Search song", "Enter the song title you want to search.");
				} else {
					KaraokeFunctionalities.searchSongLibrary(searchSongInput.getText());
				}
			});// end of search

			searchLibraryButton.setTooltip(new Tooltip("Search for a song"));

			// -----------------------------------------Video Pane--------------
			GridPane.setHalignment(songTitleLabel, HPos.CENTER);

			Button playVideo = new Button();
			playVideo.getStyleClass().add("play-button");
			Button pauseVideo = new Button();
			pauseVideo.getStyleClass().add("pause-button");
			Button skipVideo = new Button();
			skipVideo.getStyleClass().add("skip-button");

			Label timeSong = new Label("Duration(s):");

			// set height and width of video interface
			videoWindow.setPrefWidth(640);
			videoWindow.setPrefHeight(480);
			videoWindow.getStyleClass().add("border");
			videoPane.add(songTitleLabel, 0, 0);

			songControlPane.add(playVideo, 0, 0);
			songControlPane.add(pauseVideo, 1, 0);
			songControlPane.add(skipVideo, 2, 0);

			// Invoke song control methods
			playVideo.setOnAction((ActionEvent e) -> {
				KaraokeFunctionalities.playSong();
			});

			pauseVideo.setOnAction((ActionEvent e) -> {
				KaraokeFunctionalities.pauseSong();
			});
			skipVideo.setOnAction((ActionEvent e) -> {
				KaraokeFunctionalities.playNewSong();
			});

			playVideo.setTooltip(new Tooltip("Play song"));

			pauseVideo.setTooltip(new Tooltip("Pause song"));

			skipVideo.setTooltip(new Tooltip("Skip song"));

			// Add media interface to video interface
			mediaView.setVisible(false);
			videoWindow.add(mediaView, 0, 0);
			videoWindow.add(timeSong, 0, 1);
			videoWindow.add(displayTimeSong, 0, 2);

			ColumnConstraints play = new ColumnConstraints();
			play.setPercentWidth(30);
			ColumnConstraints pause = new ColumnConstraints();
			pause.setPercentWidth(40);
			ColumnConstraints skip = new ColumnConstraints();
			skip.setPercentWidth(30);

			songControlPane.getColumnConstraints().addAll(play, pause, skip);

			GridPane.setHalignment(playVideo, HPos.CENTER);
			GridPane.setHalignment(pauseVideo, HPos.CENTER);
			GridPane.setHalignment(skipVideo, HPos.CENTER);

			// ----------------------------------Playlist Pane---------------------
			Label playlistLabel = new Label("Song Playlist");
			playlistPane.add(playlistLabel, 0, 0);
			GridPane.setHalignment(playlistLabel, HPos.CENTER);

			Button addSongToPlaylistButton = new Button("Add Song  ");
			TextField inputSongToPlaylist = new TextField();
			inputSongToPlaylist.setPromptText("Add song");

			Button deleteSongFromPlaylistButton = new Button("Delete Song");
			TextField deleteSongFromPlaylistInput = new TextField();
			deleteSongFromPlaylistInput.setPromptText("Enter song number");

			Button refreshPlaylist = new Button("Display Playlist");

			refreshPlaylist.setTooltip(new Tooltip("Show list of songs in playlist"));

			// Display playlist interface
			displayListPane.setPrefWidth(300);
			displayListPane.setPrefHeight(300);
			GridPane.setColumnSpan(displayListPane, 4);
			displayListPane.getStyleClass().add("border");

			playlistPane.add(displayListPane, 0, 1);
			displayListPane.add(loadPlaylist, 0, 1);

			playlistControlPane.add(addSongToPlaylistButton, 1, 0);
			playlistControlPane.add(inputSongToPlaylist, 0, 0);
			playlistControlPane.add(deleteSongFromPlaylistButton, 1, 1);
			playlistControlPane.add(deleteSongFromPlaylistInput, 0, 1);
			playlistControlPane.add(refreshPlaylist, 0, 2);
			GridPane.setColumnSpan(refreshPlaylist, 2);
			GridPane.setHalignment(refreshPlaylist, HPos.CENTER);

			addSongToPlaylistButton.setOnAction((ActionEvent e) -> {
				if (inputSongToPlaylist.getText().isEmpty()) {
					AlertBox.displayPopUp("Add song to playlist",
							"Enter the song title you want to add to the playlist.");
				} else {
					KaraokeFunctionalities.addSongToPlaylist(inputSongToPlaylist.getText());
				}
			});// end of addSongToPlaylist

			addSongToPlaylistButton.setTooltip(new Tooltip("Insert the song title to add a song to the playlist"));

			deleteSongFromPlaylistButton.setOnAction((ActionEvent e) -> {
				if (deleteSongFromPlaylistInput.getText().isEmpty()) {
					AlertBox.displayPopUp("Delete song from playlist", "Enter the song number you want to delete.");
				} else if (!deleteSongFromPlaylistInput.getText().matches("[0-9]+")) {
					AlertBox.displayPopUp("Delete song from playlist", "Please enter the song number only");
				} else {
					KaraokeFunctionalities
							.deleteSongFromPlaylist(Integer.parseInt(deleteSongFromPlaylistInput.getText()));
				}
			});// end of deleteFromPlaylist

			deleteSongFromPlaylistButton.setTooltip(new Tooltip("Enter the song number only to delete a song"));

			refreshPlaylist.setOnAction((ActionEvent e) -> {
				KaraokeFunctionalities.displayPlaylist();
			});// end of refreshPlaylist

			Scene scene = new Scene(grid, 1330, 500);
			primaryStage.setScene(scene);
			scene.getStylesheets().add("stylesheet.css");
			primaryStage.show();

		} catch (Exception e) {
			System.out.println("Could not load GUI window" + e.getMessage());
		}
	}// end of start

	public static void main(String[] args) {
		// Check if argument empty
		if (args.length == 0) {
			System.out.println("Error, please enter the filename");
		}
		// check if more than one argument
		else if (args.length > 1) {
			System.out.println("Please enter the filename only.");
		} else {
			SongFile.addSongFile(args[0]);
		}

		launch(args);
	}// end of main

}// end of class
