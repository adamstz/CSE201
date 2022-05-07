import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class Sort {

	public static JTable newTable;
	public static ArrayList<VideoGame> sortedGamesPriceDe = new ArrayList<>();
	public static ArrayList<VideoGame> sortedGamesPriceAs = new ArrayList<>();
	public static ArrayList<VideoGame> sortedGamesAlpha = new ArrayList<>();
	public static ArrayList<VideoGame> sortedGamesDev = new ArrayList<>();
	public static Object[][] sortedData;
	public static ArrayList<VideoGame> games = new ArrayList<>();
	
	/**
	 * Creates sort menu UI
	 * @param jf
	 * @param panel
	 */
	public static void createSortMenu(JFrame jf, JPanel panel) {
		String[] options = {"Unsorted", "Alphabetically", "Developer", "Price Descending", "Price Ascending"};

		JComboBox<String> jComboBox = new JComboBox<>(options);

		JButton jButton = new JButton("Sort");
		JLabel jLabel = new JLabel("Sort By:");

		panel.add(jLabel);
		jf.getContentPane().add(panel, BorderLayout.NORTH);
		panel.add(jComboBox);
		jf.getContentPane().add(panel, BorderLayout.NORTH);
		panel.add(jButton);
		jf.getContentPane().add(panel, BorderLayout.NORTH);
		
		Main.parseVideoGames(games);
		
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				String selected = (String) jComboBox.getSelectedItem();
				if(selected == "Price Descending") {
					sortPriceDescend();
				} 
				else if (selected == "Price Ascending") {
					sortPriceAscend();
				}
				else if (selected == "Alphabetically") {
					sortAlphabet();
				}
				else if (selected == "Developer") {
					sortDeveloper();
				} else {
					
					sortTable(FilterDropDown.filteredGames,UI.jf);
					
				}
			}
		});
	}
	/**
	 * Functionality for sorting by price ascending
	 */
	public static void sortPriceAscend() {
		Main.parseVideoGames(sortedGamesPriceAs);
		if(Filter.filtered == true) {
			FilterDropDown.filteredGames.sort((o1, o2)
	                -> o1.compareTo(o2));
			sortTable(FilterDropDown.filteredGames,UI.jf);
		} else {
			sortedGamesPriceAs.sort((o1, o2)
					-> o1.compareTo(o2));
			sortTable(sortedGamesPriceAs,UI.jf);
		}
	}
	/**
	 * Functionality for sorting by price descending
	 */
	public static void sortPriceDescend() {
		Main.parseVideoGames(sortedGamesPriceDe);
		if(Filter.filtered == true) {
			FilterDropDown.filteredGames.sort((o1, o2)
	                -> o1.compareTo(o2));
			sortTable(FilterDropDown.filteredGames,UI.jf);
		} else {
			sortedGamesPriceDe.sort((o1, o2)
					-> o2.compareTo(o1));
			sortTable(sortedGamesPriceDe,UI.jf);
		}
		
	}
	/**
	 * Functionality for sorting alphabetically
	 */
	public static void sortAlphabet() {
		Main.parseVideoGames(sortedGamesAlpha);
		if(Filter.filtered == true) {
			FilterDropDown.filteredGames.sort((o1, o2)
	                -> o1.getName().compareTo(
	                        o2.getName()));
			sortTable(FilterDropDown.filteredGames,UI.jf);
		} else {
		sortedGamesAlpha.sort((o1, o2)
                -> o1.getName().compareTo(
                    o2.getName()));
		sortTable(sortedGamesAlpha,UI.jf);
		}
	}
	/**
	 * Functionality for sorting by developer
	 */
	public static void sortDeveloper() {
		Main.parseVideoGames(sortedGamesDev);
		if(Filter.filtered == true) {
			FilterDropDown.filteredGames.sort((o1, o2)
	                -> o1.getDeveloper().compareTo(
							o2.getDeveloper()));
			sortTable(FilterDropDown.filteredGames,UI.jf);
		} else {
		sortedGamesDev.sort((o1, o2)
				-> o1.getDeveloper().compareTo(
						o2.getDeveloper()));
		sortTable(sortedGamesDev,UI.jf);
		}
	}
	/**
	 * Functionality for sorting the table
	 * @param games
	 * @param jf
	 */
	public static void sortTable(ArrayList<VideoGame> games, JFrame jf) {
		

		for (int i = 0, k = 1; i < games.size(); i++, k++) {
			ViewGames.table.getColumnModel().getColumn(k).setHeaderValue(games.get(i).getName());

		}
		
		for (int i = 0, k = 1; i < games.size(); i++, k++) {
			ViewGames.model.setValueAt(games.get(i).getDescription(), 0, k);
			ViewGames.model.setValueAt(games.get(i).getPlatformString(), 1, k);
			ViewGames.model.setValueAt(games.get(i).getDeveloper(), 2, k);
			ViewGames.model.setValueAt(games.get(i).getPrice(), 3, k);
			
		}
		ViewGames.model.fireTableDataChanged();
		ViewGames.table.getTableHeader().repaint();
		jf.repaint();
	}
}