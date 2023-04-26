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
		printModel();
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
        return null;
    }

	public void printModel()
	{
		for (Word w: wordList)
		{
			System.out.print(w.getWord() + ": ");
			for (Follow f: w.getFollowList())
			{
				System.out.printf("%s(%d) ", f.getWord(), f.getCount());
			}
			System.out.println();
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
        
	}
}
