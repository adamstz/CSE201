import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FilterDropDown {
	public static ArrayList<VideoGame> filteredGames = new ArrayList<>();
	public static ArrayList<VideoGame> games = new ArrayList<>();
	public static ArrayList<TableColumn> deletedColumns = new ArrayList<>();
	public static ArrayList<VideoGame> temp;
	public static void createFilterButton(JFrame jf, JPanel panel) {
		JButton filterButton = new JButton("Filter");
		filterButton.setSize(20, 20);
		panel.add(filterButton);
		jf.getContentPane().add(panel, BorderLayout.NORTH);
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterMenu(jf);

			}
		});
	}

	public static void filterMenu(JFrame jf) {
		String[] options = { "Developer", "Platform", "Price Range", "Unfilter" };
		JFrame jFrame = new JFrame("Filter by:");

		JComboBox<String> jComboBox = new JComboBox<>(options);
		jComboBox.setBounds(80, 50, 140, 20);

		JButton jButton = new JButton("Done");
		jButton.setBounds(100, 100, 90, 20);

		JLabel jLabel = new JLabel();
		jLabel.setBounds(90, 100, 400, 100);

		jFrame.add(jButton);
		jFrame.add(jComboBox);
		jFrame.add(jLabel);

		jFrame.setLayout(null);
		jFrame.setSize(350, 250);
		jFrame.setVisible(true);

		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String filterOptionChosen = jComboBox.getItemAt(jComboBox.getSelectedIndex());

				switch (filterOptionChosen) {
				case "Developer":
					byDeveloper(jf);
					break;

				case "Platform":
					byPlatform(jf);
					break;

				case "Price Range":
					byPriceRange(jf);
					break;

				case "Unfilter":
					unfilterTable(jf);
				}

			}
		});
	}

	public static void byDeveloper(JFrame jf) {
		JFrame questionBox = new JFrame("Enter developer name to filter by:");

		questionBox.setSize(500, 100);
		questionBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		questionBox.setLocationRelativeTo(null);
		questionBox.setResizable(false);
		JPanel panel = new JPanel();
		questionBox.add(panel);
		JLabel include = new JLabel("Developer to Include:");
		include.setBounds(10, 20, 80, 25);
		panel.add(include);
		JTextField includeText = new JTextField(20);
		includeText.setBounds(100, 20, 165, 25);
		panel.add(includeText);
		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(10, 80, 80, 25);
		panel.add(enterButton);
		questionBox.setVisible(true);

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.parseVideoGames(games);
				filteredGames = Filter.filterByDev(includeText.getText(), games);			
				filterTable(filteredGames, jf);

			}
		});
	}

	public static void byPlatform(JFrame jf) {
		JFrame questionBox = new JFrame("Enter platform name to filter by:");

		questionBox.setSize(500, 100);
		questionBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		questionBox.setLocationRelativeTo(null);
		questionBox.setResizable(false);
		JPanel panel = new JPanel();
		questionBox.add(panel);
		JLabel include = new JLabel("Platform to Include:");
		include.setBounds(10, 20, 80, 25);
		panel.add(include);
		JTextField includeText = new JTextField(20);
		includeText.setBounds(100, 20, 165, 25);
		panel.add(includeText);
		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(10, 80, 80, 25);
		panel.add(enterButton);
		questionBox.setVisible(true);

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<VideoGame> games = new ArrayList<>();

				Main.parseVideoGames(games);
				filteredGames = Filter.filterByPlatform(includeText.getText(), games);
				
				filterTable(filteredGames, jf);

			}
		});
	}

	public static void byPriceRange(JFrame jf) {
		JFrame questionBox = new JFrame("Enter price range:");

		questionBox.setSize(500, 150);
		questionBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		questionBox.setLocationRelativeTo(null);
		questionBox.setResizable(false);
		JPanel panel = new JPanel();
		questionBox.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lowLabel = new JLabel("Price Low:");
		lowLabel.setBounds(10, 20, 80, 25);

		JPanel panel2 = new JPanel();
		questionBox.getContentPane().add(panel2, BorderLayout.CENTER);
		JLabel highLabel = new JLabel("Price High:");
		highLabel.setBounds(10, 20, 80, 25);

		panel.add(lowLabel);
		panel2.add(highLabel);

		JTextField lowText = new JTextField(20);
		lowText.setBounds(100, 20, 165, 25);
		panel.add(lowText);

		JTextField highText = new JTextField(20);
		lowText.setBounds(100, 20, 165, 25);
		panel2.add(highText);
		JPanel panel3 = new JPanel();

		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(10, 80, 80, 25);
		panel3.add(enterButton);
		questionBox.getContentPane().add(panel3, BorderLayout.SOUTH);
		questionBox.setVisible(true);

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<VideoGame> games = new ArrayList<>();

				Main.parseVideoGames(games);
				filteredGames = Filter.filterByPrice(Integer.parseInt(lowText.getText()),
				Integer.parseInt(highText.getText()), games);
			
				filterTable(filteredGames, jf);

			}
		});
	}

	public static void unfilterTable(JFrame jf) {
		if(Filter.filtered == false) {
			return;
		}
		
		Main.parseVideoGames(games);
		for (int i = 0, k=1 ;i < ViewGames.model.getColumnCount() - 1; i++,k++) {
			ViewGames.table.getColumnModel().getColumn(k).setHeaderValue(games.get(i).getName());
			ViewGames.model.setValueAt(games.get(i).getDescription(), 0, k);
			ViewGames.model.setValueAt(games.get(i).getPlatformString(), 1, k);
			ViewGames.model.setValueAt(games.get(i).getDeveloper(), 2, k);
			ViewGames.model.setValueAt(games.get(i).getPrice(), 3, k);
		}
		
		for (int i = ViewGames.model.getColumnCount()-1, k = ViewGames.model.getColumnCount(); i < games.size(); i++, k++) {
			
			
			ViewGames.model.addColumn(games.get(i).getName());
			ViewGames.model.setValueAt(games.get(i).getDescription(), 0, k);
			ViewGames.model.setValueAt(games.get(i).getPlatformString(), 1, k);
			ViewGames.model.setValueAt(games.get(i).getDeveloper(), 2, k);
			ViewGames.model.setValueAt(games.get(i).getPrice(), 3, k);
			ViewGames.model.setValueAt(Boolean.FALSE, 4, k);
		}
		
		ViewGames.table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF);
		ViewGames.table.setFillsViewportHeight(true);	
		TableColumn column = null;
		for (int i = 0; i < games.size()+1; i++) {
		    column = ViewGames.table.getColumnModel().getColumn(i);
		    column.setMinWidth(400); 
		}
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment( JLabel.CENTER );
		TableColumn column1 = ViewGames.table.getColumnModel().getColumn(0);
		column1.setCellRenderer(center);
		ViewGames.model.fireTableDataChanged();
		ViewGames.table.getTableHeader().repaint();
		Filter.filtered = false;
		jf.repaint();
	}

	public static void filterTable(ArrayList<VideoGame> games, JFrame jf) {
		temp = new ArrayList<>();
		for (VideoGame game : games) {
			temp.add(game);
		}
		unfilterTable(jf);
		Filter.filtered = true;
		for (int i = 0, k = 1; i < temp.size(); i++, k++) {
			ViewGames.table.getColumnModel().getColumn(k).setHeaderValue(temp.get(i).getName());
			
		}
		for (int i = 0, k = 1; i < temp.size(); i++, k++) {
			ViewGames.model.setValueAt(temp.get(i).getDescription(), 0, k);
			ViewGames.model.setValueAt(temp.get(i).getPlatformString(), 1, k);
			ViewGames.model.setValueAt(temp.get(i).getDeveloper(), 2, k);
			ViewGames.model.setValueAt(temp.get(i).getPrice(), 3, k);

		}
		
		ViewGames.model.setColumnCount(temp.size()+1);
		ViewGames.model.fireTableStructureChanged();
		ViewGames.table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF);
		ViewGames.table.setFillsViewportHeight(true);	
		TableColumn column = null;
		for (int i = 0; i < temp.size()+1; i++) {
		    column = ViewGames.table.getColumnModel().getColumn(i);
		    column.setMinWidth(400); 
		}
		ViewGames.table.getTableHeader().repaint();
		ViewGames.model.fireTableDataChanged();
		
	}

}
