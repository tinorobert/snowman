
import acm.program.*;
import acm.util.*;
import java.io.*;    // for File
import java.util.*;  // for Scanner


public class dummy extends ConsoleProgram{
	
	public void run() {
		
		try {
			String n, m="practical";
			int sum =0, counter=0;

				Scanner input = new Scanner(new File("res/display1.txt"));

				while(input.hasNextLine()) {
					n = input.nextLine();
					println(n);
				}
				
				input.close();

		} catch (FileNotFoundException e){
			println("File Not Found.");
			e.printStackTrace();
		}
		
	}
}
