package cpsc449;
// a Triple class used for storing too near 

public class Triple {
    private char x;
    private char y;
    private int z;
    
    public Triple(char x, char y, int z)
    {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }
    
    public char getFirstChar()
    {
    	return(x);
    }
    
    public char getSecondChar()
    {
    	return(y);
    }
    
    public int getInt()
    {
    	return(z);
    }
    
    //print the triple
    public String toString()
    {
    	return("(" + x + ", " + y + ", " + z + ")");
    }
    
    
}
