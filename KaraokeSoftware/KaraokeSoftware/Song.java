public class Song implements Comparable<Song> {

	// Initialise variables
	private String title;
	private String artist;
	private int runningTime;
	private String fileName;

	// Default constructor
	Song() {
		title = " ";
		artist = " ";
		runningTime = 0;
		fileName = " ";
	}

	// User defined constructor
	Song(String title, String artist, int runningTime, String fileName) {
		this.title = title;
		this.artist = artist;
		this.runningTime = runningTime;
		this.fileName = fileName;
	}

	// Setters and getters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// Convert object to string
	@Override
	public String toString() {
		return "artist=" + artist + ", fileName=" + fileName + ", runningTime=" + runningTime + ", title=" + title
				+ "\n";
	}

	// Compare songs
	@Override
	public int compareTo(Song otherSong) {
		return getTitle().compareTo(otherSong.getTitle());
	}

}// end of class song
