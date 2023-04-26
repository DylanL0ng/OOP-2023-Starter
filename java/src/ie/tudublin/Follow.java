package ie.tudublin;

public class Follow {
    private String word;

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    private int count;


    Follow(String word)
    {
        this.word = word;
        this.count = 1;
    }

    public String toString()
    {
        return "";
    }

    
}
