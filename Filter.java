import java.util.ArrayList;
import java.util.Arrays;


// Filters video game list
public class Filter {

	public static boolean filtered = false;

	/**
	 * Filters VideoGame list based on selected criteria
	 * 
	 * @param low
	 * @param high
	 * @param games
	 * @return
	 */
	public static ArrayList<VideoGame> filterByPrice(int low, int high, ArrayList<VideoGame> games) {

		games.removeIf(s -> !(s.getPrice() <= high && s.getPrice() >= low));
		filtered = true;
		return games;

	}

	public static ArrayList<VideoGame> filterByDev(String dev, ArrayList<VideoGame> games) {
		games.removeIf(s -> !(s.getDeveloper().contains(dev)));
		filtered = true;
		return games;
	}

	public static ArrayList<VideoGame> filterByPlatform(String platform, ArrayList<VideoGame> games) {
		games.removeIf(s -> !(Arrays.toString(s.getPlatforms()).contains(platform)));
		filtered = true;
		return games;

	}

}
