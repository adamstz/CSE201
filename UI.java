import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;

//UI componenets are called here
public class UI {
	static JFrame jf = new JFrame("Video Game Catalog");
	static ArrayList<VideoGame> games = new ArrayList<>();
	static ArrayList<VideoGame> favorites = new ArrayList<>();
	public UI() {
		
		Main.parseVideoGames(games);
		jf.setLayout(new BorderLayout());
		jf.setSize(1800,600);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ViewGames.viewGames(jf, games);
		JPanel northPanel = new JPanel();
		Log_In.CreateLogInButton(jf, northPanel);
		SearchBar bar = new SearchBar(northPanel);
		SearchButton.createSearchButton(jf, northPanel);
		CreateAccount.createAccountButton(jf, northPanel);
		Sort.createSortMenu(jf,northPanel);
		FilterDropDown.createFilterButton(jf, northPanel);
		AddEntry.createButton(jf, northPanel);
		AddToFavorite.FavoriteButton(jf, northPanel);
		
		jf.setVisible(true);
	}
	/**
	 * Display full catalog
	 */
	public static void fullCatalog() {
		Main.parseVideoGames(games);
		ViewGames.viewGames(jf, games);
		
	}
	/**
	 * Display favorites catalog
	 */
	public static void favCatalog() {
		Main.parseFavoriteGames(favorites);
		ViewFavorites.viewFavorites(jf, favorites);
		
		
	}

}
