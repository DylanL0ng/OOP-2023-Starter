package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> followList;

    Word(String word)
    {
        this.word = word;
        this.followList = new ArrayList<Follow>();
    }

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

    public Follow findFollow(String word)
    {
        for (Follow f: followList)
        {
            if (f.getWord().equals(word))
                return f;
        }

        return null;
    }

    public String toString()
    {
        String x = this.word + ": ";
        for (Follow f: this.followList)
            x = x + f.toString();
        
        return x;
    }
}
