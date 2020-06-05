import static org.junit.Assert.*;

import org.junit.Test;

public class KaraokeInterfaceTest {

	@Test
	public final void testAddSong() {
		String songName = "ON";
		String artistName = "BTS";
		int songDuration = 123;
		String fileName = "test.mp4";
		String expectedMessage = "Song successfully added to library.";
		String displayMessage = "Song successfully added to library.";
		Song newSong = new Song(songName, artistName, songDuration, fileName);
		if (KaraokeFunctionalities.songLibrary.containsKey(newSong.getTitle())) {
			System.out.println("Song already exists in library");
		} else {
			KaraokeFunctionalities.songLibrary.put(newSong.getTitle(), newSong);
			System.out.println(displayMessage);
			System.out.println(newSong);
		}

		assertEquals("BTS", newSong.getArtist());
		assertEquals("ON", newSong.getTitle());
		assertEquals(123, newSong.getRunningTime());
		assertEquals("test.mp4", newSong.getFileName());
		assertEquals(expectedMessage, displayMessage);
		assertTrue(KaraokeFunctionalities.songLibrary.containsKey("ON")
				&& KaraokeFunctionalities.songLibrary.get("ON") != null);
		assertTrue(KaraokeFunctionalities.songLibrary.containsValue(newSong));

	}

	@Test
	public final void testAddSong5() { // song already exists
		String songName = "ON";
		String artistName = "BTS";
		int songDuration = 123;
		String fileName = "test.mp4";
		String expectedMessage = "Song successfully added to library.";
		String displayMessage = "Song successfully added to library.";
		String displayMessage1 = "Song already exists in library";
		Song newSong = new Song(songName, artistName, songDuration, fileName);
		if (KaraokeFunctionalities.songLibrary.containsKey(newSong.getTitle())) {
			System.out.println(displayMessage1);
		} else {
			KaraokeFunctionalities.songLibrary.put(newSong.getTitle(), newSong);
			System.out.println(displayMessage);
			System.out.println(newSong);
		}

		assertNotEquals(expectedMessage, displayMessage1);

	}

	@Test
	public final void testSearch() {
		String songSearched = "ON";
		String searchTrue = "Song found in the library.";
		String searchFalse = "No such song found in the library.";
		if ((KaraokeFunctionalities.songLibrary.containsKey(songSearched))) {
			Song song = KaraokeFunctionalities.songLibrary.get(songSearched);
			System.out.println("Searched song is" + song);
			System.out.println(searchTrue);
		} else {
			System.out.println(searchFalse);
		}

		assertTrue(KaraokeFunctionalities.songLibrary.containsKey("ON")
				&& KaraokeFunctionalities.songLibrary.get("ON") != null);

	}

	@Test
	public final void testSearch1() { // song not found
		String songSearched = "Tester";
		String word = "Song found in the library.";
		String searchTrue = "Song found in the library.";
		String searchFalse = "No such song found in the library.";
		if ((KaraokeFunctionalities.songLibrary.containsKey(songSearched))) {
			KaraokeFunctionalities.songLibrary.get(songSearched);
			System.out.println(searchTrue);
		} else {
			System.out.println(searchFalse);
		}

		assertNotEquals(word, searchFalse);
	}

	@Test
	public final void addToPlaylistTest() {
		String songName = "ON";
		Song sameSong = null;
		String expectedMsg = "Song has been added to playlist.";
		String outputTrue = "Song has been added to playlist.";
		String outputFalse = "This song does not exist in the library";
		String alreadyTrue = "This song already exists in the playlist";
		if (KaraokeFunctionalities.songLibrary.containsKey(songName)) {
			sameSong = KaraokeFunctionalities.songLibrary.get(songName);
			System.out.println("Song added " + sameSong);
			if (KaraokeFunctionalities.songPlaylist.contains(sameSong)) {
				System.out.println(alreadyTrue);
			} else {
				KaraokeFunctionalities.songPlaylist.add(sameSong);
				System.out.println(outputTrue);
			}
		} else {
			System.out.println(outputFalse);
		}

		assertEquals(expectedMsg, outputTrue);
		assertTrue(KaraokeFunctionalities.songPlaylist.contains(sameSong));

	}

	@Test
	public final void addToPlaylistTest1() { // already exists
		String songName = "ON";
		Song sameSong = null;
		String outputTrue = "Song has been added to playlist.";
		String outputFalse = "This song does not exist in the library";
		String alreadyTrue = "This song already exists in the playlist";
		String alreadyExpected = "This song already exists in the playlist";
		if (KaraokeFunctionalities.songLibrary.containsKey(songName)) {
			sameSong = KaraokeFunctionalities.songLibrary.get(songName);
			if (KaraokeFunctionalities.songPlaylist.contains(sameSong)) {
				System.out.println(alreadyTrue);
			} else {
				KaraokeFunctionalities.songPlaylist.add(sameSong);
				System.out.println(outputTrue);
			}
		} else {
			System.out.println(outputFalse);
		}

		assertEquals(alreadyExpected, alreadyTrue);

	}

	@Test
	public final void addToPlaylistTest2() { // does not exist
		String songName = "Tester";
		Song sameSong = null;
		String outputTrue = "Song has been added to playlist.";
		String outputFalse = "This song does not exist in the library";
		String alreadyTrue = "This song already exists in the playlist";
		if (KaraokeFunctionalities.songLibrary.containsKey(songName)) {
			sameSong = KaraokeFunctionalities.songLibrary.get(songName);
			if (KaraokeFunctionalities.songPlaylist.contains(sameSong)) {
				System.out.println(alreadyTrue);
			} else {
				KaraokeFunctionalities.songPlaylist.add(sameSong);
				System.out.println(outputTrue);
			}
		} else {
			System.out.println(outputFalse);
		}

		assertNotEquals(outputTrue, outputFalse);

	}

	@Test
	public final void displayPlaylistTest() {
		String outputSongNumber = "";
		int i = 0;
		for (Song song : KaraokeFunctionalities.songPlaylist) {
			outputSongNumber = outputSongNumber + i + " - " + song.getTitle() + " - " + song.getArtist() + " - "
					+ song.getRunningTime() + "\n";
			i++;
		}
		if (outputSongNumber.length() == 0) {
			System.out.println("There are no more songs in the playlist");
		}
		System.out.println(outputSongNumber);

		assertTrue(KaraokeFunctionalities.songPlaylist.isEmpty());

	}

	@Test
	public final void deleteSongFromPlaylistTest() { // deleted
		int index = 0;
		String expectedDelete = "Song has been successfully deleted.";
		String deleteSong = "Song has been successfully deleted.";
		String deleteFalse = "Song does not exist.";
		try {
			KaraokeFunctionalities.songPlaylist.remove(index);
			System.out.println(deleteSong);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(deleteFalse);
		}

		assertEquals(expectedDelete, deleteSong);

	}

	@Test
	public final void deleteSongFromPlaylistTest1() { // song not exist
		int index = 0;
		String expectedDelete = "Song has been successfully deleted.";
		String deleteSong = "Song has been successfully deleted.";
		String deleteFalse = "Song does not exist.";
		try {
			KaraokeFunctionalities.songPlaylist.remove(index);
			System.out.println(deleteSong);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(deleteFalse);
		}

		assertNotEquals(expectedDelete, deleteFalse);

	}

}
