import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SongFile {

	static void addSongFile(String path) {
		// get file path
		String filePath = System.getProperty("user.dir") + File.separator + path;
		BufferedReader bReader;
		try {
			InputStream inputStream = new FileInputStream(filePath);
			bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String readLine;
			while ((readLine = bReader.readLine()) != null) {
				String[] array = readLine.split("\t");
				Song newSong = new Song(array[0], array[1], Integer.parseInt(array[2]), array[3]);
				KaraokeFunctionalities.songLibrary.put(newSong.getTitle(), newSong);
			}
			if (!KaraokeFunctionalities.songLibrary.isEmpty()) {
				Iterator<Map.Entry<String, Song>> it = KaraokeFunctionalities.songLibrary.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, Song> obj = (Entry<String, Song>) it.next();
					System.out.println(obj.getValue());
				}
			} // end of if
			System.out.println("Song file successfully added to library");
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Cannot read from file" + e.getMessage());
		}
	}// end of addSongFile

}// end of class SongFile
