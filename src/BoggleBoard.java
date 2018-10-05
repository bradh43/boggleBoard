
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Name: Brad Hodkinson
 * Assignment: 8
 * Title: Boggle Board
 * Course: CSCI 270
 * Lab Section: 1 (9:55-11:40)
 * Semester: Fall, 2016
 * Instructor: Dr. Blaha
 * Date: 11/7/16
 * Sources consulted: Data Structures 2E, Dr. Blaha, Java Doc, Course web page 
 * Program description: uses a binary search to check if word is valid on BoggleBoard
 * Known Bugs: N/A
 * Creativity: 	added support for Qu,
 * 				also added random board to simulate dice,
 * 				also gives list of possible words if "cheatcode is entered in" ,
 * 				also made a GUI,
 * 				also made it so different board sizes can be chosen,
 * 				also added different game options   
 */
public class BoggleBoard extends JFrame{
	
	//private variables for boggle board
	private static String[][] board;
	private boolean[][] used;
	public static ArrayList<String> usedWords;
	private static int size;
	private static int score;
	private static String boardFile;
	private static String temp;
	private static Dictionary dictionary;
	private static BoggleBoard boggleBoard;
	private static boolean gui = true;
	
	//private variables for GUI componets 
	private JButton newGameButton;
	private JTextField responseField;
	private JLabel enterLabel;
	private JLabel settingsLabel;
	private JLabel sizeSettingLabel;
	private JLabel scoreLabel;
	private JLabel timeLabel;
	private JLabel gameTypeLabel;
	private JTextArea messageLabel;
	private Choice sizeChoice;
	private ButtonGroup group;
	private JRadioButton game1, game2, game3;
	private JButton resetButton;
	private GridLayout gameBoard;
	private JScrollPane scrollPane;
	private JLabel spaceLabel;
	
	//private variables for GUI panels
	private JPanel upperPanel;
	private JPanel lowerPanel;
	private JPanel upPanel;
	private JPanel lowPanel;
	private JPanel boardPanel;
	private JPanel settingPanel;
	private JPanel wordPanel;
	private JPanel messagePanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel gameOptionPanel;
	
	//private variables for GUI
	private int timeLimit;
	private long time;
	private double guessTime;
	private boolean timeBoolean;
	private String gameChosen;
	private static boolean gameFlag;
	
	/**
	 * Default constructor for BoggleBoard with no file passed
	 * Creates a BoggleBoard GUI
	 */
	public BoggleBoard() {
		//GUI Title and setup
	    super(" Boggle Board ");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	 
	    // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //create buttons, panels, and labels
        newGameButton = new JButton(" New Game ");
        resetButton = new JButton(" Reset ");
    	responseField = new JTextField(16);
    	new JTextArea();
    	enterLabel = new JLabel(" Enter a word:");
    	settingsLabel = new JLabel(" ------SETTINGS------ ");
    	settingsLabel.setOpaque(true);
    	settingsLabel.setBackground(Color.lightGray);
    	new JLabel(" Game Type:  ");
    	sizeSettingLabel = new JLabel(" Select size of board ");
    	scoreLabel = new JLabel(" Score: 0 points ");
    	timeLabel = new JLabel(" Time: ");
    	gameTypeLabel = new JLabel(" Select game type ");
    	messageLabel = new JTextArea(" Choose a game option then click New Game to begin. ");
    	messageLabel.setOpaque(true);
    	messageLabel.setBackground(Color.yellow);
    	sizeChoice = new Choice();
    	scrollPane = new JScrollPane(messageLabel);
    	scrollPane.setPreferredSize(new Dimension(500, 40));
    	spaceLabel = new JLabel("");
    	
    	
    	//instantiate panels and set layouts
    	upperPanel = new JPanel();
    	upperPanel.setLayout(new BorderLayout());
    	lowerPanel = new JPanel();
    	lowerPanel.setLayout(new BorderLayout());
    	upPanel = new JPanel();
    	upPanel.setLayout(new BorderLayout());
    	lowPanel = new JPanel();
    	lowPanel.setLayout(new BorderLayout());
    	boardPanel = new JPanel();
    	boardPanel.setLayout(new BorderLayout());
    	boardPanel.setPreferredSize(new Dimension(400, 400));
    	settingPanel = new JPanel();
    	settingPanel.setLayout(new BorderLayout());
    	wordPanel = new JPanel();
    	wordPanel.setLayout(new BorderLayout());
    	messagePanel = new JPanel();
    	messagePanel.setLayout(new BorderLayout());
    	panel1 = new JPanel();
    	panel1.setLayout(new BorderLayout());
    	panel2 = new JPanel();
    	panel2.setLayout(new BorderLayout());
    	panel3 = new JPanel();
    	panel3.setLayout(new BorderLayout());
    	panel4 = new JPanel();
    	panel4.setLayout(new BorderLayout());
    	gameOptionPanel = new JPanel();
    	gameOptionPanel.setLayout(new BorderLayout());
    	
    	//add option for game bored size
    	sizeChoice.add(" 4 x 4 ");
    	sizeChoice.add(" 5 x 5 ");
    	sizeChoice.add(" 6 x 6 ");
    	sizeChoice.add(" 7 x 7 ");
    	sizeChoice.add(" 8 x 8 ");
    	sizeChoice.add(" 9 x 9 ");
       
    	//JRadio for game options
        game1 = new JRadioButton();
        game1.setText(" 1 min. Challenge ");
        game2 = new JRadioButton();
        game2.setText(" 3 min. Challenge ");
        game3 = new JRadioButton();
        game3.setText(" Unlimited Time ");
        
        //instantiate button group
        group = new ButtonGroup();
        group.add(game1);
        group.add(game2);
        group.add(game3);
        gameOptionPanel.add(game1, BorderLayout.NORTH);
        gameOptionPanel.add(game2, BorderLayout.CENTER);
        gameOptionPanel.add(game3, BorderLayout.SOUTH);
        
        //add componets to JPanel
        panel1.add(settingsLabel, BorderLayout.NORTH);
        panel1.add(sizeSettingLabel, BorderLayout.SOUTH);
        panel2.add(sizeChoice, BorderLayout.NORTH);
        panel2.add(gameTypeLabel, BorderLayout.SOUTH);
        panel3.add(gameOptionPanel, BorderLayout.NORTH);
        panel3.add(timeLabel, BorderLayout.SOUTH);
        panel4.add(scoreLabel, BorderLayout.NORTH);
        panel4.add(newGameButton, BorderLayout.CENTER);
        panel4.add(resetButton, BorderLayout.SOUTH);
        upPanel.add(panel1, BorderLayout.NORTH);
        upPanel.add(panel2, BorderLayout.SOUTH);
        lowPanel.add(panel3, BorderLayout.NORTH);
        lowPanel.add(panel4, BorderLayout.SOUTH);
        settingPanel.add(lowPanel, BorderLayout.SOUTH);
        settingPanel.add(upPanel, BorderLayout.NORTH);
        upperPanel.add(settingPanel, BorderLayout.EAST);
        upperPanel.add(boardPanel, BorderLayout.WEST);
        messagePanel.add(scrollPane, BorderLayout.CENTER);
        wordPanel.add(enterLabel, BorderLayout.WEST);
        wordPanel.add(responseField);
        lowerPanel.add(wordPanel, BorderLayout.NORTH);
        lowerPanel.add(messagePanel, BorderLayout.CENTER);
        lowerPanel.add(spaceLabel, BorderLayout.SOUTH);
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        
        //add action listeners 
  	  	newGameButton.addActionListener(new newGameButtonListener());
  	  	resetButton.addActionListener(new resetButtonListener());
  	  	game1.addActionListener(new game1ButtonListener());
  	  	game2.addActionListener(new game2ButtonListener());
  	  	game3.addActionListener(new game3ButtonListener());
  	  	responseField.addActionListener(new responseFieldListener());
  	  	
  	  	//set button and field disabled
  	  	newGameButton.setEnabled(false);
  	  	responseField.setEditable(false);
  	  	responseField.setEnabled(false);
        
    	// Add the panel to this JFrame
        this.add(mainPanel);

        // Size this JFrame 
        this.setSize(564,488);
       
        // Make this JFrame visible
        this.setVisible(true);
        
	}
	
	/**
	 * default constructor for BoggleBoard being passed a file name
	 * @param fileName to read in for BoggleBoard data
	 */
	public BoggleBoard(String fileName) {
		
		super();
		
		//reset score to 0
		score = 0;
		
		//read in file to scanner
		Scanner infile = null;
		try {
			infile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File " + fileName + " not found");
			System.exit(0);
		}
		
		//check if file fixed or random
		boolean random;
		int startInt = 0;
		String firstLine = infile.nextLine();
		if(Character.isDigit(firstLine.charAt(0))) {
			size = Integer.parseInt(firstLine);
			temp = infile.nextLine();
			random = false;
		}
		else {
			//check to make sure size of board is legal
			if(size >= 10 ||size <= 3) {
				size = 4;
			}
			//build a string representation of file contents
			StringBuilder s = new StringBuilder();
			s.append(firstLine.charAt(0));
			s.append(" ");
			while(infile.hasNext()) {
				s.append(infile.next());
				s.append(" ");
			}
			temp = s.toString();
			//simulate dice being rolled
			Random rand = new Random();
			startInt = rand.nextInt(91 - (size * size));
			random = true;
		}
		
		//Instantiate board data and used data
		board = new String[size][size];
		used = new boolean[size][size];
		
		//set up scanner to read in string of data
		Scanner tempScan = new Scanner(temp);
		tempScan.useDelimiter(" ");
		
		//start at random spot
		if(random) {
			for(int i = 0; i < startInt; i++) {
				tempScan.next();
			}
		}
		
		//read in file to board
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				board[r][c] = tempScan.next();
			}
		}
		tempScan.close();
	}
	
	/**
	 * @return size of board length
	 */
	public int getBoardSize() {
		return size;
	}
	
	/**
	 * @return player score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param addPoints points to add to score
	 */
	public void updateScore(int addPoints) {
		score += addPoints;
	}
	
	/**
	 * @param word to calculate points 
	 * @return calculated points for given word
	 */
	public int calculatePoints(String word) {
		int points;
		word = word.trim();
		
		//check to see how much points the word is
		if(word.length() >= 8) {
			points = 11;
		}
		else if(word.length() >= 7) {
			points = 5;
		}
		else if(word.length() >= 6) {
			points = 3;
		}
		else if(word.length() >= 5) {
			points = 2;
		}
		else if(word.length() >= 3) {
			points = 1;
		}
		else {
			points = 0;
		}
		return points;
	}
	
	/**
	 * draws board
	 */
	public void drawBoard() {
		//draw the corner of the board
		System.out.print("+");
		//draw border lines
		for(int i = 0; i < size; i++) {
			System.out.print("--");
		}
		//draw corner
		System.out.println("-+");
		//draw boarder and board letters
		for(int r = 0; r < size; r++) {
			System.out.print("| ");
			for(int c = 0; c < size; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println("|");
		}
		//draw corner
		System.out.print("+");
		//draw boarder lines
		for(int i = 0; i < size; i++) {
			System.out.print("--");
		}
		//draw corner
		System.out.println("-+");
		resetUsed();
	}
	
	/**
	 * resets spot to unused 
	 */
	public void resetUsed() {
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				used[r][c] = false;
			}
		}
	}
	
	
	/**
	 * @param target word to check if valid
	 * @param r row to check
	 * @param c col to check
	 * @return true if valid move else false
	 */
	private boolean validMove(String target, int r, int c) {
		//if all of word is on board return true
		if(target.length() == 0) {
			return true;
		}
		//check to make sure row and col are in bounds
		if(r >= size || c >= size || r < 0 || c < 0) {
			return false;
		}
		//read in first element
		String firstElement = target.charAt(0) + "";
		//check if Q and check if it has U
		if(firstElement.equals("Q")) {
			if(target.charAt(1) != 'U') {
				return false;
			}
			else {
				firstElement = "QU";
			}
		}
		//read in location on board to see if it contains the element 
		String temp = board[r][c];
		temp = temp.toUpperCase();
		//check to see if location has been used and element valid in location
		if(!used[r][c] && firstElement.equals(temp)) {
			//set location used to true
			used[r][c] = true;
			//remove the element from the target word and search using the shorten word
			String newTarget = target.substring(firstElement.length(), target.length());
			//check all possible positions of the new target on the board
			if(validMove(newTarget, r + 1, c + 1)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r + 1, c)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r + 1, c - 1)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r, c + 1)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r, c - 1)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r - 1, c + 1)) {
				resetUsed();
				return true;
			}
			if(validMove(newTarget, r - 1, c)) {
				resetUsed();
				return true;	
			}
			if(validMove(newTarget, r - 1, c - 1)) {
				resetUsed();
				return true;	
			}
		} //if
		return false;
		
	} //validMove
	
	/**
	 * 
	 * @param word to see if on board
	 * @return if on board true else false
	 */
	public boolean validWord(String word) {
		word = word.trim();
		word = word.toUpperCase();
		//check to see if the word is valid at a given start position
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				if(validMove(word, r, c)) {
					return true;
				}
				resetUsed();
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param target word to see if used
	 * @return if word has been used return true else false
	 */
	public boolean checkUsed(String target) {
		//check to see if word has been used in the used word list
		for(String word : usedWords) {
			if(word.equalsIgnoreCase(target)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * gets answers to current boggle board
	 * @return string representation of all the answers
	 */
	public String getAnswer() {
		//create a string representation of answers
		StringBuilder answers = new StringBuilder();
		//check all the words to see if they are on the board
		for(int i = 0; i < dictionary.getSize() - 1; i++) {
			String word = dictionary.getWord(i);
			//check if the word is valid length and valid move on board
			if(word.length() >= 3 && boggleBoard.validWord(word)) {
				answers.append(word + ", ");
			}
		}
		//replace the last comma and space with a period
		answers.replace(answers.length() - 2, answers.length(), ".");
		return answers.toString();
	}
	
	/**
     * This is a private inner class that is responsible for handling events
     * for the new game button in this GUI.
     */
    private class newGameButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	//disable setting and prepare response field
        	newGameButton.setEnabled(false);
        	game1.setEnabled(false);
        	game2.setEnabled(false);
        	game3.setEnabled(false);
        	sizeChoice.setEnabled(false);
        	responseField.setEditable(true);
        	responseField.setEnabled(true);
        	responseField.selectAll();
        	responseField.cut();
        	responseField.requestFocus();
        	
        	//read in the size of board from the size drop down box
            String sizeString = sizeChoice.getItem(sizeChoice.getSelectedIndex());
            sizeString = sizeString.trim();
            String numberString;
            numberString = sizeString.charAt(0) + "";
            size = Integer.parseInt(numberString);
            
            //prompt user to enter a word on the board
            messageLabel.setText(" Enter a word found on the " + sizeString + " board. ");
            
            //set the game board layout to a square grid
            gameBoard = new GridLayout(size, size);
            boardPanel.setLayout(gameBoard);
            
            //create a new boggle board game
            boggleBoard = new BoggleBoard("dice.txt");
            
            //create a button to hold elements of the game board data
            JButton button;
            //create the necessary amount of buttons and make them display data
            for(int r = 0; r < size; r++) {
            	for(int c = 0; c < size; c++) {
            		button = new JButton(board[r][c] + "");
            		boardPanel.add(button);
            		button.setFont(new Font("Arial", Font.PLAIN, 16));
            	}
            }
            //make sure all the space on the game board is used efficiently
            pack();
         
            //reset score
            scoreLabel.setText(" Score: 0 points ");
            
            //load Dictionary
			String wordFile = "words.txt";
			dictionary = new Dictionary(wordFile);
			
			//reset used words, start a new game, and read in current time
			usedWords = new ArrayList<String>();
			gameFlag = true;
			time = System.currentTimeMillis();
        }
    }
    
    /**
     * This is a private inner class that is responsible for handling events
     * for the reset button in this GUI.
     */
    private class resetButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	//set default size to 4
        	size = 4;
        	//reset all buttons and enable the settings
        	gameFlag = false;
        	newGameButton.setEnabled(true);
        	game1.setEnabled(true);
        	game2.setEnabled(true);
        	game3.setEnabled(true);
        	sizeChoice.setEnabled(true);
        	boardPanel.removeAll();
        	responseField.setEditable(false);
        	responseField.setEnabled(false);
        	responseField.selectAll();
        	responseField.cut();
        	responseField.requestFocus();
        	//prompt user how to start game and reset time
        	messageLabel.setBackground(Color.yellow);
        	messageLabel.setText(" Choose a game option then click New Game to begin. ");
        	timeLabel.setText(" Time: " + gameChosen);
        }
    }
    
    /**
     * This is a private inner class that is responsible for handling events
     * for the response field in this GUI.
     */
    private class responseFieldListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	//get text from response field
        	String response = responseField.getText();
        	response = response.trim();
        	response = response.toLowerCase();
        	responseField.selectAll();
        	responseField.requestFocus();
				
			//check if space is entered and end program
			if(response.length() == 0) {
				//reset program
				size = 4;
	        	newGameButton.setEnabled(true);
	        	game1.setEnabled(true);
	        	game2.setEnabled(true);
	        	game3.setEnabled(true);
	        	sizeChoice.setEnabled(true);
	        	boardPanel.removeAll();
	        	responseField.setEditable(false);
	        	responseField.setEnabled(false);
	        	responseField.selectAll();
	        	responseField.cut();
	        	responseField.requestFocus();
	        	messageLabel.setBackground(Color.yellow);
	        	messageLabel.setText(" Choose a game option then click New Game to begin. ");
			}
			//check if legal word was played
			else {
				//check if word is long enough
			    if(response.length() < 3) {
					messageLabel.setText(" The word " + response + " is too short!");
				}
				//cheat code to get all answers
				else if(response.equalsIgnoreCase("cheatcode")) {
					messageLabel.setBackground(Color.cyan);
					messageLabel.setText("unlocks all the answers: " + boggleBoard.getAnswer());
				}
				//check if word is in dictionary
				else if(!dictionary.isWord(response)) {
					messageLabel.setBackground(Color.red);
					messageLabel.setText(" The word " + response + " is not a valid word.");
				}
				//check if word is on board
				else if(!boggleBoard.validWord(response)) {
					messageLabel.setBackground(Color.red);
					messageLabel.setText(" The word " + response + " is a valid word, but is not on board.");
				}
				//check if word has been used already
				else if(boggleBoard.checkUsed(response)) {
					messageLabel.setBackground(Color.red);
					messageLabel.setText(" The word " + response + " has already been used.");
				}
				//display points received for valid word and update score
				else {
					int points = boggleBoard.calculatePoints(response);
					String p;
					if(points == 1) {
						p = points + " point.";
					}
					else {
						p = points + " points.";
					}
					messageLabel.setBackground(Color.green);
					messageLabel.setText(" The word " + response + " is good! You score " + p);
					boggleBoard.updateScore(points);
					usedWords.add(response);
					
					//update score
					String s;
					if(score == 1) {
						s = score + " point";
					}
					else {
						s = score + " points";
					}
					scoreLabel.setText(" Score: " + s);
				} //else valid
			} //else word not ""
			
			//check time limit
			if(timeBoolean) {
				guessTime = (System.currentTimeMillis() - time) / 1000.0;
				if(guessTime < 60) {
					timeLabel.setText(" Time: " + String.format("%.2f", guessTime));
				}
				else {
					int min = (int) (guessTime / 60);
					int sec = (int) (guessTime % 60);
					String second = "";
					if(sec <= 10) {
						second = "0" + sec;
					} else {
						second = sec + "";
					}
					timeLabel.setText(" Time: " + min + ":" + second);
				}
				//check if time limit up
				if(timeLimit < guessTime) {
					messageLabel.setBackground(Color.red);
					messageLabel.setText(" Game Over. Time is up! ");
				}
			}
			
        }
    }
    
    /**
     * This is a private inner class that is responsible for handling events
     * for the game 1 button in this GUI.
     */
    private class game1ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	newGameButton.setEnabled(true);
        	gameChosen = " 1 min Challenge ";
        	timeLabel.setText(" Time: 1 min. ");
        	timeLimit = 60;
        	timeBoolean = true;
        	
        }
    }
    
    /**
     * This is a private inner class that is responsible for handling events
     * for the game 2 button in this GUI.
     */
    private class game2ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	newGameButton.setEnabled(true);
        	gameChosen = " 3 min. Challenge ";
        	timeLimit = 180;
        	timeLabel.setText(" Time: 3 min. ");
        	timeBoolean = true;
        	
        }
    }
    
    /**
     * This is a private inner class that is responsible for handling events
     * for the game 3 button in this GUI.
     */
    private class game3ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	newGameButton.setEnabled(true);
        	gameChosen = " Unlimited Time";
        	timeLabel.setText(" Time: Unlimited ");
        	timeBoolean = false;
        	
        }
    }

	public static void main(String[] args) {
		if(gui) {
			BoggleBoard frame = new BoggleBoard();
		}
		else {
			//load Boggle Board
			//boardFile = "board.txt";
			size = 4;
			boardFile = "dice.txt";
			boggleBoard = new BoggleBoard(boardFile);
			
			//load Dictionary
			String wordFile = "words.txt";
			dictionary = new Dictionary(wordFile);
			
			//start a new game
			usedWords = new ArrayList<String>();
			gameFlag = true;
			while(gameFlag) {
				
				boggleBoard.drawBoard();
				System.out.print("Enter a word:  ");
				Scanner keyboard = new Scanner(System.in);
				String response = keyboard.nextLine();
				response = response.trim();
				response = response.toLowerCase();
				keyboard.close();
				
				
				//check if space is entered and end program
				if(response.length() == 0) {
					System.out.print("Total Score: " + boggleBoard.getScore());
					if(boggleBoard.getScore() == 1) {
						System.out.print(" point");
					}
					else {
						System.out.print(" points");
					}
					gameFlag = false;
				}
				//check if legal word was played
				else {
					System.out.print("The word " + response + " ");
					//check if word is long enough
					if(response.length() < 3) {
						System.out.print("is too short!");
					}
					//cheat code to get all answers
					else if(response.equalsIgnoreCase("cheatcode")) {
						System.out.println("unlocks all the answers:");
						System.out.print("" + boggleBoard.getAnswer());
					}
					//check if word is in dictionary
					else if(!dictionary.isWord(response)) {
						System.out.print("is not a valid word.");
					}
					//check if word is on board
					else if(!boggleBoard.validWord(response)) {
						System.out.print("is a valid word, but is not on board.");
					}
					//check if word has been used already
					else if(boggleBoard.checkUsed(response)) {
						System.out.print("has already been used.");
					}
					//display points received for valid word and update score
					else {
						int points = boggleBoard.calculatePoints(response);
						System.out.print("is good! You score " + points);
						if(points == 1) {
							System.out.print(" point.");
						}
						else {
							System.out.print(" points.");
						}
						boggleBoard.updateScore(points);
						usedWords.add(response);
					}
				}
				System.out.println();
			}
			
		}
	}//main
}// class
