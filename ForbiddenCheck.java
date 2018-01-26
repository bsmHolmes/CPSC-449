//package cpsc449;
// Class that compares all pairs in fm to mp and replaces the value in mp to -1 if true
import java.io.File;
public class ForbiddenCheck{
	public static void main(String[] args){
		FileHandler getFiles = new FileHandler();
		Pair getPairs;

		// load the file to read from
        String base = new File("").getAbsolutePath();
        String filePath = base + "/inputs/myinput0.txt";
        getFiles.loadData(filePath);
		//int machine = getPairs.getInt();
		//char task = getPairs.getSecondChar();
        
        
		int[][] machinePenalties = getFiles.getMP();
		Pair[] forbiddenMachines = getFiles.getFM();
		int machine = forbiddenMachines.getInt();

	
		/*for (int i = 0; i < forbiddenMachines.length; i++){
			int machine = forbiddenMachines.getInt();
			char task = forbiddenMachines.getSecondChar();

			// comvert task from alphabetical to numerical
			//String str = task;
			//char[] ch  = str.toCharArray();
			/*for(char c : task){
				int temp = (int)c;
				int temp_integer = 96; 
			}
			if(temp<=122 & temp>=97){
				int tasknum = (temp-temp_integer);
			}*/
			//for (int j = 0; j<getFM.len)
		System.out.println("Machine Penalties\n" + machinePenalties + forbiddenMachines);
		//System.out.println(java.util.Arrays.toString(machinePenalties));
		for(int[] i : machinePenalties){
			System.out.println(i);
		}

	}
}