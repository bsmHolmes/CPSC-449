package cpsc449;

import java.io.File; 

public class Driver {

	private FileHandler inputFile = new FileHandler;
	private Tree tree = new Tree;										//Naming of "tree" class should be capitalized
	private byte[] bestTree;
	
   public static void main(String[] args) {
	   
	   if (args.length != 2) {
		   System.out.println("Usage: Driver filename");				//check for proper calling format, 2 arguments
		   System.exit(0);
	   }
	   
	   inputFile.loadData(args[1]); 									//run file handler on input file, "inputFile" holds most data
	   
	   	//																//should probably run forbidden check here, or in the file handler
	   
	   tree.get_initial_tree(inputFile.getMP(), inputFile.getTNP()); 	//create initial tree, note TNP has a size mismatch between tree and file handler
	   
	   bestTree = tree.get_best_tree_slow; 								//Temporary best tree finding algorithm
	   
	   for (int i = 0; i < 8; i++)
		   System.out.println("Machine %d - Task %d", i, bestTree[i]);	//print best machine task pairings from "bestTree"
	   
   }
 }