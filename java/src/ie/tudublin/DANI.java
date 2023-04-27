package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;

public class DANI extends PApplet {

	private ArrayList<Word> wordList = new ArrayList<Word>();
	private String[] sonnet;

	public void settings() {
		size(1000, 1000);
	}

	public void loadFile() {
		String[] lines = loadStrings("shakespere.txt"); // Load a text file into a String array

		for (int i=0; i < lines.length; i++)
		{
			// Remove punctuation, and lower case words
			lines[i] = lines[i].replaceAll("[^\\w\\s]","");
			lines[i] = lines[i].toLowerCase();

			// Split each line up into their individual words
			String[] line = split(lines[i], ' ');

			// Check each word in a line
			for (int j=0; j < line.length; j++)
			{
				Word foundWord = findWord(line[j]);

				// If a word isn't found, init a new word object and
				// add it into the word list.
				if (foundWord == null)
				{
					Word x = new Word(line[j]);
					foundWord = x;
					wordList.add(x);
				}

				ArrayList<Follow> wordFollowList = foundWord.getFollowList();
				
				// Check if it is out of bounds
				if ((j + 1) < line.length)
				{
					Follow foundFollow = foundWord.findFollow(line[j + 1]);
					
					// If there is no follow add a follow
					// if there is a follow increment the count
					if (foundFollow == null)
					{
						Follow f = new Follow(line[j + 1]);
						wordFollowList.add(f);
					}
					else
						foundFollow.setCount(foundFollow.getCount() + 1);	
				}
			}
		}

		printModel();
	}

    public String[] writeSonnet()
    {
		// Reset the sonnet;
		sonnet = new String[14];

		for (int i = 0; i < 14; i++)
		{
			// Generate a random word and use it
			// to generate a new sentence, repeat
			// 14 times to fill up the sonnet.

			int size = wordList.size();
			int index = parseInt(random(size));
			Word randomWord = wordList.get(index);
	
			String sentence = generateSentence(randomWord);
			sonnet[i] = sentence;
		}

		printSonnet();
        return sonnet;
    }

	public void displaySonnet()
	{
		// offset y represents the gap between new lines
		// off represents the y vertical offset between the whole sonnet

		float offsetY = 80.0f;

		for (int i = 0; i < sonnet.length; i++)
		{
			text(sonnet[i], (width / 2.0f), off + offsetY);
			offsetY+= 30.0f;
		}
	}

	public void printSonnet()
	{
		System.out.println("\nNEW SONNET:\n");

		for (int i = 0; i < sonnet.length; i++)
			System.out.println(sonnet[i]);
		System.out.println();
	}

	public String generateSentence(Word word)
	{
		// Generates a sentence using a starting word
		// max length of the sentence is 8 words, if the
		// target word has no follow then it will end the
		// sentence.
		
		Word target = word;
		String sentence = word.getWord() + " ";

		for (int i = 0; i < 8; i++)
		{
			ArrayList<Follow> followList = target.getFollowList();
			int follow_size = followList.size();
			
			// If theres no follows then end sentence
			if (follow_size == 0)
				return sentence;
	
			int follow_idx = parseInt(random(follow_size));
			Follow random_follow = followList.get(follow_idx);

			sentence = sentence + random_follow.getWord() + " ";
			target = findWord(random_follow.getWord());
		}

		return sentence;
	}

	public void printModel()
	{
		for (Word w: wordList)
			System.out.println(w.toString());
	}

	public Word findWord(String word)
	{
		for (Word w:wordList)
		{
			if (word.equals(w.getWord()))
				return w;
		}

		return null;
	}

	public void setup() {
		colorMode(HSB);
		loadFile();
		writeSonnet();
	}

	public void keyPressed() {
		if (keyCode == ' ')
			writeSonnet();
	}

	float off = 120.0f;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
        displaySonnet();
	}
}
