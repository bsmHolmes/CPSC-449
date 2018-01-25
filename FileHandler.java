package cpsc449;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


// used to extract all data from a specified file in a specified path
public class FileHandler {
	int debug =0;
	int feedback = 1;
    //array for machine penalties
	private int[][] mp;
	
	//name
	private String name;
	
	
	//to 8x8
	//forced PA: array of pairs
	private Triple[] fpa;
	//to 8x8
	// forbidden machine: array of pairs
	private Pair[] fm;
	
	//too-near tasks
	private Triple[] tnt;
	
	// too near penalties: array of triples
	private Triple[] tnp;
	
	
	//this will be the triple that contains both TNT & TNP, to be passed to the tree
	private Triple[] trip;
	
	
	
	//loads all data into the various data types that 
	//it goes through the file searching for keywords
	//after finding a keyword the function will process each line of data until blank space or null is found
	//assumptions: there is a blank space after each section of data, the keywords in the file are spelled properly
	//assumptions: the tuples are in the correct formats (can be checked later in another function)
	//
   public void loadData(String filePath)
   {
	   fpa = new Triple[0];
	   fm = new Pair[0];
	   tnt = new Triple[0];
	   tnp = new Triple[0];
	   mp = new int[8][8];
	   
	   
       try
	   {
	       BufferedReader in = new BufferedReader(new FileReader(filePath));
	       String x = "";
	       
	       while (!(x==null))
	       {
	    	   x = in.readLine();
	    	   
	    	   
	    	   if (!(x==null))
	    	   {
	    		   
		    	   if ((x.toLowerCase()).equals(("name:")))
		    	   {
		    		   x = in.readLine();
		    		   name = x;
		    		   if (debug==1)
		    		   {
		    			   System.out.println("name is: " + x);
		    		   }
		    		   
		    		   
		    		   
		    	   }
		    	   else if ((x.toLowerCase()).equals("forced partial assignment:"))
		    	   {
		    		   int i = 0;
		    		   if (debug==1)
		    		   {
		    			   System.out.println(x);
		    		   }
		    		   
		    		   while(!(x.equals(""))&& !(x==null))
		    		   {
			    		   x = in.readLine();
			    		   if (!(x.equals(""))&& !(x==null))
			    		   {
			    			   if (debug==1)
				    		   {
			    				   System.out.println(i + ": " + x);
				    		   }
			    			   fpa = addTriple2(fpa, x);;
				    		   i++;
			    		   }

		    		   }
		    	   }
		    	   else if ((x.toLowerCase()).equals("forbidden machine:"))
		    	   {
		    		   int i = 0;
		    		   if (debug==1)
		    		   {
		    			   System.out.println(x);
		    		   }
		    		   while(!(x.equals(""))&& !(x==null))
		    		   {
			    		   x = in.readLine();
			    		   if (!(x.equals(""))&& !(x==null))
			    		   {
			    			   if (debug==1)
				    		   {
			    				   System.out.println(i + ": " + x);
				    		   }
			    			   fm = addPair(fm, x);
				    		   i++;
			    		   }

		    		   }
		    	   }
		    	   else if ((x.toLowerCase()).equals("too-near tasks:"))
		    	   {
		    		   int i = 0;
		    		   if (debug==1)
		    		   {
		    			   System.out.println(x);
		    		   }
		    		   while(!(x.equals(""))&& !(x==null))
		    		   {
			    		   x = in.readLine();
			    		   if (!(x.equals(""))&& !(x==null))
			    		   {
			    			   if (debug==1)
				    		   {
			    				   System.out.println(i + ": " + x);
				    		   }
			    			   tnt = addTriple2(tnt, x);
				    		   i++;
			    		   }

		    		   }
		    	   }
		    	   else if ((x.toLowerCase()).equals("machine penalties:"))
		    	   {
		    		   int i = 0;
		    		   if (debug==1)
		    		   {
		    			   System.out.println(x);
		    		   }
		    		   while(!(x.equals(""))&& !(x==null))
		    		   {
			    		   x = in.readLine();
			    		   if (!(x.equals(""))&& !(x==null))
			    		   {
			    			   if (debug==1)
				    		   {
			    				   System.out.println(i + ": " + x);
				    		   }
			    			   mp[i] = readInts(x);
				    		   i++;
			    		   }

		    		   }
		    	   }
		    	   else if ((x.toLowerCase()).equals("too-near penalties:"))
		    	   {
		    		   int i = 0;
		    		   if (debug==1)
		    		   {
		    			   System.out.println(x);
		    		   }
		    		   while(!(x.equals("")) && !(x==null))
		    		   {
			    		   x = in.readLine();
			    		   if (!(x.equals(""))&& !(x==null))
			    		   {
			    			   if (debug==1)
				    		   {
			    				   System.out.println(i + ": " + x);
				    		   }
			    			   tnp = addTriple(tnp,x);
				    		   i++;
			    		   }
			    		  

		    		   }

		    	   }
		    	   
	    	   
	    	   
	    	   }
	    	  
	    	   
	       }
	       
	       

	       in.close();
	       if (feedback == 1)
	       {
	    	   System.out.println("done!");
	       }
	       
	       
	   }
       catch(NullPointerException e)
       {
    	   if (feedback == 1)
    	   {
    		   System.out.println("End of file reached");
    	   }
    	   
       }
	   catch (FileNotFoundException ex)
	   {
	       System.out.println("File does not exist!");
	   } 
       catch (IOException e)
       {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }   
	   if (feedback == 1)
	   {
		   System.out.println("File read successfully! \n\n");
	   }
       
	   trip = concat(tnt, tnp);
	   prepareArray();
	   
       if (debug == 2)
       {
    	   System.out.println("****************************************Data read from file**********************************************");
    	   
    	   printFPA();
    	   printFM();
    	   printTNT();
    	   printMP();
    	   printTNP();
	       
	       printTrip();
	       
	       
	       printMP();
    	   
    	   
    	   
    	   
    	   
       }
       
       

	
   }
   //parse a string for the ints inside it
   // we assume there are 8 integers for this assignment
   public int[] readInts(String toRead)
   {
	   
	   String[] tokens = toRead.split(" ");
	   int len = tokens.length;
	   int[] toRet = new int[len];
	   
	   for( int i =0; i<len; i++)
	   {
		   toRet[i] = Integer.parseInt(tokens[i]);
	   }
	   return(toRet);
   }
   //makes a tuple
   //must duplicate the array each time
   public Pair[] addPair(Pair[] current, String pair)
   {
	   //extract data
	   String c1 = "";
	   String c2 = "";
	   c1 = c1 + pair.charAt(1);
	   c2 = c2 + pair.charAt(4);
	   
	   //resize array and add new pair
	   int currentSize = current.length;
	   int newSize = currentSize + 1;
	   Pair[] tempPair = new Pair[newSize];
	   
	   for (int i = 0; i < currentSize; i++)
	   {
		   tempPair[i] = current[i];
	   }
	   tempPair[newSize - 1] = new Pair(c1, c2);
	  
	   return(tempPair);
	   
   }
   //makes a triple for too-near task cases
   public Triple[] addTriple(Triple[] current, String triple)
   {
	   char c1 = ' ';
	   char c2 = ' ';
	   int c3 = 0;
	   //grab tasks
	   c1 = triple.charAt(1);
	   c2 = triple.charAt(4);
	   //remove all non digits
	   triple = triple.replaceAll("\\D+","");
	   c3 = Integer.parseInt(triple);
	   
	   int currentSize = current.length;
	   
	   int newSize = currentSize + 1;
	   Triple[] tempTrip = new Triple[newSize];
	   
	   for (int i = 0; i < currentSize; i++)
	   {
		   tempTrip[i] = current[i];
	   }
	   tempTrip[newSize - 1] = new Triple(c1, c2, c3);
	  
	   return(tempTrip);
	   
   }
   //for fpa and tnt
   public Triple[] addTriple2(Triple[] current, String triple)
   {
	   char c1 = ' ';
	   char c2 = ' ';
	   int c3 = 0;
	   //grab tasks
	   c1 = triple.charAt(1);
	   c2 = triple.charAt(4);
	   //remove all non digits
	   
	   int currentSize = current.length;
	   
	   int newSize = currentSize + 1;
	   Triple[] tempTrip = new Triple[newSize];
	   
	   for (int i = 0; i < currentSize; i++)
	   {
		   tempTrip[i] = current[i];
	   }
	   tempTrip[newSize - 1] = new Triple(c1, c2, -1);
	  
	   return(tempTrip);
	   
   }
   
   public Triple[] concat(Triple[] tnt, Triple[] tnp )
   {
	   int len = tnt.length + tnp.length;
	   Triple[] temp = new Triple[len];
	   int i = 0;
	   int j = 0;
	   while(i < tnt.length)
	   {
		   temp[j] = tnt[i];
		   i++;
		   j++;
	   }
	   i = 0;
	   while(i < tnp.length)
	   {
		   temp[j] = tnp[i];
		   i++;
		   j++;
	   }
	   return(temp);
	   
   }
   //this will take the 
   public void prepareArray()
   {
	   //first we will deal will forced partial assignments
	   //we need to make sure no other machine can be assigned to this task and no task to this machine
	   //therefore we will substitute a -1 in the array where this is true
	   int len = fpa.length;
	   for (int l = 0; l < len; l++)
	   {
		   //column
		   int i = 0;
		   //row
		   int j = 0;
		   //-1 because array starts at 0
		   //must cast to int before use
		   int m = (int) fpa[l].getFirstChar() - 49;
		   int t = (int) fpa[l].getSecondChar() - 65;
		   
		   System.out.println(m);
		   System.out.println(m);
		   while(j < 8)
		   {
			   // if the machine task pair is in this row then make entire row -1 except for m/t pair
			   if (j == m)
			   {
				   while(i < 8)
				   {
					   if (i == t)
					   {
						   i++;
					   }
					   else
					   {
						   mp[j][i] = -1;
						   i++;
					   }
					   
				   }
				   i = 0;
				   j++;
			   }
			   else
			   {
				   mp[j][t] = -1;
				   j++;
			   }
		   }
		    
		   
	   }
	   
   }
   

	   
   //*****************************************************accessors and mutators********************************************************
   
  
   
   public void printName()
   {
	   System.out.println("Name:");
	   System.out.println(name + "\n");
   }
   public String getName()
   {
	   return(name);
   }
   
   //too-near penalties
   public Triple[] getTNP()
   {
	   return(tnp);
   }
   public void printTNP()
   {
	   System.out.println("too-near penalties:");
	   int len = tnp.length;
	   for(int i = 0; i < len; i++)
	   {
		   System.out.println(tnp[i]);
	   }
	   System.out.println("");
   }
   
   //too-near tasks
   public Triple[] getTNT()
   {
	   return(tnt);
   }
   public void printTNT()
   {
	   System.out.println("too-near tasks:");
	   int len = tnt.length;
	   for(int i = 0; i < len; i++)
	   {
		   System.out.println(tnt[i]);
	   }
	   System.out.println("");
   }
   
   
   
   //forced machines
   public Pair[] getFM()
   {
	   return(fm);
   }
   public void printFM()
   {
	   System.out.println("Forbidden Machines:");
	   int len = fm.length;
	   for(int i = 0; i < len; i++)
	   {
		   System.out.println(fm[i]);
	   }
	   System.out.println("");
   }
   
   //forced partial assignment
   public Triple[] getFPA()
   {
	   return(fpa);
   }
   public void printFPA()
   {
	   System.out.println("Forced Partial Assignments:");
	   int len = fpa.length;
	   for(int i = 0; i < len; i++)
	   {
		   System.out.println(fpa[i]);
	   }
	   System.out.println("");
   }
   //machine penalties
   public int[][] getMP()
   {
	   return(mp);
   }
   public void printMP()
   {
	   System.out.println("machine penalties:");
	   int len = mp.length;
	   for(int i = 0; i < len; i++)
	   {
		   int len2 = mp[i].length;
		   for(int j = 0; j<len2; j++)
		   {
			   if (mp[i][j] == -1)
			   {
				   System.out.print(mp[i][j]);
				   System.out.print("  ");
			   }
			   else if(mp[i][j] >= 10)
			   {
				   System.out.print(" ");
				   System.out.print(mp[i][j]);
				   System.out.print(" ");
			   }
			   else
			   {
				   System.out.print(" ");
				   System.out.print(mp[i][j]);
				   System.out.print("  ");
			   }

		   }
		   System.out.println("");
	   }
	   System.out.println("");
   }
   
   public void printAll()
   {
	   printName();
	   printFPA();
	   printFM();
	   printTNT();
	   printMP();
	   printTNP();
   }
   //This is the triple that will be passed to the tree function
   public Triple[] getTrip()
   {
	   return(trip);
   }
   
   public void printTrip()
   {
	   System.out.println("Triple to be used:");
	   int len = trip.length;
	   for(int i = 0; i < len; i++)
	   {
		   System.out.println(trip[i]);
	   }
	   System.out.println("");
   }
   
   
	
}
