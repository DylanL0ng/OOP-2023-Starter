package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	private ArrayList<Word> wordList = new ArrayList<Word>();

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

	public void loadFile() {
		String[] lines = loadStrings("small.txt"); // Load a text file into a String array

		for (int i=0; i < lines.length; i++)
		{
			lines[i] = lines[i].replaceAll("[^\\w\\s]","");
			lines[i] = lines[i].toLowerCase();
			String[] line = split(lines[i], ' ');

			for (int j=0; j < line.length; j++)
			{
				Word foundWord = findWord(line[j]);
				if (foundWord == null)
				{
					Word x = new Word(line[j]);
					foundWord = x;
					wordList.add(x);
				}

				ArrayList<Follow> wordFollowList = foundWord.getFollowList();
				try {
					Follow foundFollow = foundWord.findFollow(line[j + 1]);
					if (foundFollow == null)
					{
						Follow f = new Follow(line[j + 1]);
						wordFollowList.add(f);
					}
					else
						foundFollow.setCount(foundFollow.getCount() + 1);	
				} catch (Exception e) {
				}
			}
		}
		// printModel();

		writeSonnet();
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
		sonnet = new String[14];
		for (int i = 0; i < 14; i++)
		{
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
		float offsetY = 80.0f;

		for (int i = 0; i < sonnet.length; i++)
		{
			text(sonnet[i], (width / 2.0f), offsetY);
			offsetY+= 30.0f;
		}
	}

	public void printSonnet()
	{
		for (int i = 0; i < sonnet.length; i++)
		{
			System.out.println(sonnet[i]);
		}
	}

	public String generateSentence(Word word)
	{
		Word target = word;
		String sentence = word.getWord() + " ";

		for (int i = 0; i < 8; i++)
		{
			ArrayList<Follow> followList = target.getFollowList();
			int follow_size = followList.size();
			
			if (follow_size == 0)
			{
				return sentence;
			}
	
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
		{
			System.out.println(w.toString());
		}
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
       
	}

	public void keyPressed() {

	}

	float off = 0;

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
