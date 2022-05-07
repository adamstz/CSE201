import javax.swing.JPanel;
import javax.swing.JTextField;

//Creates search bar UI
public class SearchBar {

	static JTextField searchBar;

	SearchBar(JPanel panel) {
		searchBar = new JTextField(50);
		panel.add(searchBar);

	}

	/**
	 * Retrieves Text from search bar
	 * 
	 * @return
	 */
	public static String getText() {
		return searchBar.getText();
	}
}
