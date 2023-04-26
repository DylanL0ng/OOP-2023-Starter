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
				if (findWord(line[j]) == null)
				{
					Word x = new Word(line[j]);
					wordList.add(x);
				}
			}
		}
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
        return null;
    }

	public String findWord(String word)
	{
		for (Word w:wordList)
		{
			if (word.equals(w.getWord()))
				return w.getWord();
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
