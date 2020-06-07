
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * Copyright 2020
 * 3 Musketeers Tech (Michael Arcangel, Caleb Cheshire, Christian Cheshire)
 * AcePW - ICS 427 Project
 */

@SuppressWarnings("serial")
public class AppGUI extends JFrame {
	
	private JScrollPane tableScroller;
	private DefaultTableModel modelTable;
	private JTable table;

	private JMenuBar AcePWMenuBar;
	private JMenu fileMenu, editMenu, viewMenu, helpMenu;
	private JMenuItem fNew, fOpen, fSave, fLoad, fExit;
	private JMenuItem eCut, eCopy, ePaste, eSelectAll;
	private JMenuItem vAll, vHidePasswords;
	private JMenuItem hAcePWHelp, hContactUs;

	@SuppressWarnings("unused")
	private NewEntryGUI infogui;
	@SuppressWarnings("unused")
	private UserHelpGUI help;

	public AppGUI() {
		setTitle("AcePW - Ace Password");
		getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 400, 600, 600);

		setJMenuBar(new AcePasswordManagerMenuBar());
		add(new AcePasswordManagerTable());

		setVisible(true);
	}

	public class AcePasswordManagerMenuBar extends JMenuBar {
		public AcePasswordManagerMenuBar() {

			fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);// key board navigation by ALT
			fNew = new JMenuItem("New");
			fOpen = new JMenuItem("Open");
			fSave = new JMenuItem("Save");
			fLoad = new JMenuItem("Load");
			fExit = new JMenuItem("Exit");
			fileMenu.add(fNew);
			fileMenu.add(fOpen);
			fileMenu.add(fSave);
			fileMenu.add(fLoad);
			fileMenu.addSeparator();
			fileMenu.add(fExit);

			editMenu = new JMenu("Edit");
			editMenu.setMnemonic(KeyEvent.VK_E);
			eCut = new JMenuItem("Cut");
			eCopy = new JMenuItem("Copy");
			ePaste = new JMenuItem("Paste");
			eSelectAll = new JMenuItem("SelectAll");
			editMenu.add(eCut);
			editMenu.add(eCopy);
			editMenu.add(ePaste);
			editMenu.addSeparator();
			editMenu.add(eSelectAll);

			viewMenu = new JMenu("View");
			viewMenu.setMnemonic(KeyEvent.VK_V);
			vAll = new JMenuItem("All");
			vHidePasswords = new JMenuItem("Hide Passwords");
			viewMenu.add(vAll);
			viewMenu.add(vHidePasswords);

			helpMenu = new JMenu("Help");
			helpMenu.setMnemonic(KeyEvent.VK_H);
			hAcePWHelp = new JMenuItem("AcePW Help");
			hContactUs = new JMenuItem("Contact Us");
			helpMenu.add(hAcePWHelp);
			helpMenu.add(hContactUs);

			AcePWMenuBar = new JMenuBar();
			AcePWMenuBar.add(fileMenu);
			AcePWMenuBar.add(editMenu);
			AcePWMenuBar.add(viewMenu);
			AcePWMenuBar.add(helpMenu);

			add(AcePWMenuBar);

			fNew.addActionListener(new openNew());
			hAcePWHelp.addActionListener(new openHelpMenu());
			fLoad.addActionListener(new loadFile());
		}
	}

	public class AcePasswordManagerTable extends JPanel {
		public AcePasswordManagerTable() {
			setLayout(null);
			modelTable = new DefaultTableModel() {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};

			table = new JTable(modelTable);
			tableScroller = new JScrollPane(table);

			add(tableScroller);
			tableScroller.setBounds(1, 1, 600, 553);

			setUpTable();
			setBounds(0, 0, 600, 600);
		}

		public void setUpTable() {
			String[] colTitles = { "Websites", "Usernames", "Passwords" };
			for (int i = 0; i < 3; i++) {
				modelTable.addColumn(colTitles[i]);
			}

			table.getTableHeader().setResizingAllowed(false);
			table.getTableHeader().setReorderingAllowed(false);
			table.setColumnSelectionAllowed(true);
			table.setRowSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setRowHeight(30);

			modelTable.setColumnCount(3);
			modelTable.setRowCount(25);

			populateTable();
		}
	}

	public void populateTable() {
		int row = 0;
		int col = 0;
		String nameOfUser = System.getProperty("user.name");
		System.out.println("The name of the user is " + nameOfUser);
		String entry;

		File filePath = new File("/Users/" + nameOfUser + "/Documents/AcePW Info.txt");
		System.out.println("The path that the file will be in is " + filePath);

		try {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(filePath);

			while (input.hasNextLine()) {
				entry = input.nextLine();
				System.out.println(entry);
				if (col >= 3) {
					col = 0;
				}
				modelTable.setValueAt(entry, row, col);

				if (col == 2) {
					row++;
				}
				col++;
				
			}
			
		} catch (Exception e) {
			System.err.format("File not Found");
		}
		
	}

	/*
	 * Instance of New Entry
	 */
	public class openNew implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			infogui = new NewEntryGUI();
		}
	}
	
	/*
	 * Instance of Help Menu
	 */
	public class openHelpMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			help = new UserHelpGUI();
		}
	}
	
	/*
	 * Instance of Data Table
	 */
	public class loadFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			populateTable();
		}
	}
	
}
