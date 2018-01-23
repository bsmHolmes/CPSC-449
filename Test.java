package cpsc449;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String base = new File("").getAbsolutePath();
		
		String filePath = base + "\\src\\cpsc449\\inputs\\myinput0.txt";
		System.out.println(filePath);
        FileHandler f = new FileHandler();
        f.loadData(filePath);
        f.printAll();
        
	}

}
