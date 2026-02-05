package a7.model.exceptions;

public class IndexOutOfRangeException extends RuntimeException 
{
    private final int selected,
                      lowest,
                      highest;

    public IndexOutOfRangeException(int selected, int lowest, int highest)
    {
        super("Index was out of range.");

        this.selected = selected;
        this.lowest = lowest;
        this.highest = highest;
    }

    public int GetSelected() { return selected; }

    public int GetLowBound() { return lowest; }

    public int GetHighBound() { return highest; }
}
