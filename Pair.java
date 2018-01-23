package cpsc449;

//a tuple class used for storing the machine/task pairs as well as too near task pairs

public class Pair{
	//Strings are used here so that the x value can be either an int or a char
    private String x;
    private String y;
    
    public Pair(String x, String y)
    {
    	this.x = x;
    	this.y = y;
    }
    
    //print the tuple
    public String toString()
    {
    	return("(" + x + ", " + y + ")");
    }
    //only works if first in tuple is int
    public int getInt()
    {
    	return(Integer.parseInt(x));
    }
    
    //print the second symbol in the tuple
    public char getSecondChar()
    {
    	return(y.charAt(0));
    }
    
    //only useful if the first of the tuple is a char
    public char getFirstChar()
    {
    	return(x.charAt(0));
    }
    
    
    
}
