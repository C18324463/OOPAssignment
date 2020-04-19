/* Author: Gabriel Hynes
 * Date: 20/04/20
 * Short Description: Search Engine to search files for words
 */
//Package
package assignment;

//Imports
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

	private static final long serialVersionUID = 1L;
	// Attributes
	JButton searchButton;
	JPanel centrePanel, topPanel, bottomPanel;
	JTextField searchField;
	JTextArea results;
	JLabel programTitle, note;
	BorderLayout layout;
	String search;
	File file;
	Scanner scan;

	// Constructors
	public SearchEngine(String title) {
		super(title);
		setSize(300, 300);

		layout = new BorderLayout();
		setLayout(layout);

		searchButton = new JButton("Search");
		programTitle = new JLabel("Search Engine");
		note = new JLabel("Note: Search is case sensitive");
		results = new JTextArea();
		searchField = new JTextField();
		centrePanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		searchButton.setPreferredSize(new Dimension(100, 40));
		searchField.setPreferredSize(new Dimension(130, 30));
		results.setPreferredSize(new Dimension(150, 150));
		results.setEditable(false);
		searchField.setText("Enter Word...");
		searchField.setEditable(false);

		searchButton.addActionListener(this);
		searchField.addActionListener(this);
		searchField.addMouseListener(this);
		
		topPanel.add(programTitle);
		centrePanel.add(searchField);
		centrePanel.add(note);
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
		//Path that your files are in
		String path = "C:\\Users\\Gabriel Hynes\\eclipse-workspace\\C18324463 Assignment\\";
		
		//When user presses the button
		if (buttonEvent.getSource() == searchButton) 
		{
			//Reading in number of files searched for size of holder array
			int num = Integer.parseInt(JOptionPane.showInputDialog(this, "How many files are you searching?" + " Please Enter a number"));
			String[] holder = new String[num];
			//Emptying results field after each search
			results.setText(null);
			int j = 0;
			//Error check so user enters a number
			if (num > 0)
			{
				//Error check so user has typed a word
				if (! searchField.getText().matches(".*[^a-z].*"))
				{
					try 
					{
						//Putting documents in folder into an array
						File folder = new File(path);
						File[] listOfFiles = folder.listFiles();
						//Test if there is files in list
						if (listOfFiles.length > 0) 
						{
							//Loop over length of list 
							for (int i = 0; i < listOfFiles.length; i++) 
							{
								//If document is a file and ends in .txt then 
								if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt")) 
								{
									//Read the file
									scan = new Scanner(new BufferedReader(new FileReader(path + listOfFiles[i].getName())));
									//While the file has a next line
									while (scan.hasNext())
									{	
										//Check how many words have been entered
										String text = searchField.getText();
										String [] splitter = text.trim().split("\\p{javaSpaceChar}{1,}");
										int numOfWords = splitter.length;
										//If only one word is being searched
										if (numOfWords == 1) 
										{
											//Scan each individual word
											String word = scan.next();
											//Checking if its a text file
											if (searchField.getText().endsWith("*"))
											{
												//Removing the *
												searchField.setText("" + searchField.getText().substring(0, searchField.getText().length() - 1));
												//If word scanned is an exact match to the entered word
												if (word.equals(searchField.getText()))
												{
													//Put file in results field
													results.append(listOfFiles[i].getName() + " 100% match" + "\n");
													//Put * back on the typed word for going through the loop again
													searchField.setText(searchField.getText() + "*");
												}//If word scanned begins with entered word
												else if (word.startsWith(searchField.getText())) 
												{
													//Put in holder array to be printed to results later
													holder[j] = listOfFiles[i].getName();
													j++;
													searchField.setText(searchField.getText() + "*");
												} else 
												{
													searchField.setText(searchField.getText() + "*");
												}//End of if-else
											}//Else if word is equal to what you typed then set result field to name of file you are in
											else if(word.equals(searchField.getText()))
											{
												results.append(listOfFiles[i].getName() + " 100% match" +"\n");
											}//End if-else
										}//else if searching more than one word, for example 2 words or phrase/sentence
										else if (numOfWords > 1)
										{
											//Using a word variable to check each line
											String line = scan.nextLine();
											//If line has the words/phrase entered, put file name into results field
											if(line.contains(searchField.getText()))
											{
												results.append(listOfFiles[i].getName() + " 100% match" + "\n");
											}//End if
										}//End of if
									}//End while
								}//End if
							}//End for
							/* For any file names in the holder array, that weren't an exact match to the typed word,
							put those files in afterwards, as a way to rank the files in order of closest match to 
							entered word */
							for (int k = 0; k < holder.length; k++)
							{
								if (holder[k] != null)
								{
									results.append(holder[k] + " 75% match" + "\n");
								}
							}
								}//End if          
					} catch (FileNotFoundException e)
					{
						e.printStackTrace();
						System.out.println("No Files Found");
					}//End try-catch
				} else 
				{
					JOptionPane.showMessageDialog(this, "Error, please enter a word");
				}//End if-else
			} else
			{
				JOptionPane.showMessageDialog(this, "Error, please enter a number of files to be searched and try again");
			}//End if-else
		}//End if
		//Close the scanner
		scan.close();
	}//End method

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		//When person clicks field to enter text, it clears the field and allows them to edit the field
		searchField.setEditable(true);
		searchField.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
}
