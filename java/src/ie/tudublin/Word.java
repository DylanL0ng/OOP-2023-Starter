package ie.tudublin;

import java.util.ArrayList;

import processing.data.FloatList;

public class Word {
    private String word;
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }



    public ArrayList<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(ArrayList<Follow> followList) {
        this.followList = followList;
    }

    private ArrayList<Follow> followList;

    public String findFollow(String word)
    {
        for (Follow f: followList)
        {
            if (f.getWord().equals(word))
            {
                return f.getWord();
            }
        }
        
        return null;
    }

    Word(String word)
    {
        this.word = word;
    }

    public String toString()
    {
        return "";
    }
}
