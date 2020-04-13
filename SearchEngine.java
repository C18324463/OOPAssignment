package assignment;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchEngine extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Attributes
	JButton searchButton;
	JPanel centrePanel, topPanel, bottomPanel;
	JTextField searchField;
	JLabel programTitle;
	JTextArea results;
	String search;
	File file;
	Scanner scan;
	

	// Constructors
	public SearchEngine(String title) {
		super(title);
		setSize(300, 300);

		BorderLayout bl1 = new BorderLayout();
		setLayout(bl1);

		searchButton = new JButton("Search");
		programTitle = new JLabel("Search Engine");
		results = new JTextArea();
		searchField = new JTextField();
		searchField.setText("Enter Word...");

		searchButton.setPreferredSize(new Dimension(100, 40));
		searchField.setPreferredSize(new Dimension(100, 30));
		results.setPreferredSize(new Dimension(100, 150));

		searchButton.addActionListener(this);
		searchField.addActionListener(this);
		searchField.addMouseListener(this);

		centrePanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();

		topPanel.add(programTitle);
		centrePanel.add(searchField);
		centrePanel.add(results);
		bottomPanel.add(searchButton);

		add(centrePanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	
	//Methods
	
	@Override
	public void actionPerformed(ActionEvent buttonEvent){
		//change the path to the path that your files are in
		String path = "C:\\Users\\Gabriel Hynes\\eclipse-workspace\\C18324463 Assignment\\";
		
		
		if (buttonEvent.getSource() == searchButton) {
			try {
			 File folder = new File(path);
				 File[] listOfFiles = folder.listFiles();
				 
				 //test if there is files in list
				 if (listOfFiles.length > 0) {
					 
					//loop over length of list 
				   for (int i = 0; i < listOfFiles.length; i++) {
					  //if document is a file then 
				      if (listOfFiles[i].isFile()) {
				    	  
				    	 //read the file
				         scan = new Scanner(new BufferedReader(new FileReader(path + listOfFiles[i].getName())));
				         
				         //while the file has a next line
				         while (scan.hasNext()){
				        	//using a word variable to check each word 
				            String word = scan.next();
				            //if word is equal to what you typed then set result field to name of file you are in
				            if(word.equals(searchField.getText())){
				            	results.setText(listOfFiles[i].getName());
				            }
				          }//end while
				      }//end if
				  }//end for
				}//end 1st if
				            
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		searchField.setText("");

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
