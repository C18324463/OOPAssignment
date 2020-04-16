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
import javax.swing.JOptionPane;
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
	JTextArea results;
	JLabel programTitle;
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
		results.setEditable(false);
		searchField = new JTextField();
		searchField.setText("Enter Word...");
		
		searchButton.setPreferredSize(new Dimension(100, 40));
		searchField.setPreferredSize(new Dimension(130, 30));
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
	public void actionPerformed(ActionEvent buttonEvent) 
	{
		//change the path to the path that your files are in
		String path = "C:\\Users\\Gabriel Hynes\\eclipse-workspace\\C18324463 Assignment\\";
		
		if (buttonEvent.getSource() == searchButton) 
		{
			int num = Integer.parseInt(JOptionPane.showInputDialog(this, "How many files are you searching?" + " Please Enter a number"));
			results.setText(null);
			String[] holder = new String[num];
			int j = 0;
			try {
			 File folder = new File(path);
				 File[] listOfFiles = folder.listFiles();
				 //test if there is files in list
				 if (listOfFiles.length > 0) 
				 {
					//loop over length of list 
				   for (int i = 0; i < listOfFiles.length; i++) 
				   {
					  //if document is a file then 
				      if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt")) 
				      {
				    	 //read the file
				         scan = new Scanner(new BufferedReader(new FileReader(path + listOfFiles[i].getName())));
				         //while the file has a next line
				         while (scan.hasNext())
				         {	
				        	 //Check how many words have been entered
				        	 String text = searchField.getText();
				        	 String [] splitter = text.trim().split("\\p{javaSpaceChar}{1,}");
				        	 int numOfWords = splitter.length;
				        	 if (numOfWords == 1) 
				        	 {
				        		 String word = scan.next();
				        		 //equalsIgnoreCase(word); //didn't work
					        	 if (searchField.getText().endsWith("*"))
					        	 {
					        		 searchField.setText("" + searchField.getText().substring(0, searchField.getText().length() - 1));
					        		 if (word.equals(searchField.getText()))
					        		 {
					        			 results.append(listOfFiles[i].getName() + "\n");
					        			 searchField.setText(searchField.getText() + "*");
					        		 }
					        		 else if (word.startsWith(searchField.getText())) 
					        		 {
				        				 holder[j] = listOfFiles[i].getName();
				        				 j++;
					        			 
					        			 searchField.setText(searchField.getText() + "*");
					        		 } else 
					        		 {
					        			 searchField.setText(searchField.getText() + "*");
					        		 }
					        		 //JOptionPane.showMessageDialog(this, word);
					        	 }
					        	 
					        	 //else if word is equal to what you typed then set result field to name of file you are in
					        	 if(word.equals(searchField.getText()))
					        	 {
					        		 results.append(listOfFiles[i].getName() + "\n");
					        	 }
				        	 } 
				        	 if (numOfWords > 1)
				        	 {
				        		//using a word variable to check each word
				        		 String word = scan.nextLine();//ONLY SCANS WORDS IF ON THE SAME LINE, FIX SO SCANS WORDS ON ANY LINE
					        	 //else if word is equal to what you typed then set result field to name of file you are in
					        	 if(word.contains(searchField.getText()))
					        	 {
					        		 results.append(listOfFiles[i].getName() + "\n");
					        	 }
				        	 }
				          }//end while
				      }//end if
				   	}//end for
				   for (int k = 0; k < holder.length; k++)
				   {
					   if (holder[k] != null)
					   {
						   results.append(holder[k] + "\n");
					   }
				   }
				}//end 1st if
				            
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		scan.close();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		searchField.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
