/*introcs.cs.princeton.edu. (2017). Stopwatch.java. [online] 
Available at: https://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html [Accessed 22 Apr. 2020].
*/

public class Stopwatch {

	private static long start;

	public static void startStopwatch() {
		start = System.currentTimeMillis();
	}// end of stopwatch

	// Invoke Stopwatch.elapsedTime() when measuring runtime of function
	public static void elapsedTime() {
		long now = System.currentTimeMillis();
		double timeTaken = (double) (now - start) / 1000.0;
		System.out.format("%.10f \n", timeTaken);
	}// end of elapstedTime

}// end of stopwatch
