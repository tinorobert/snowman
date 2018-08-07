import java.io.*;
import java.util.*;
import acm.program.*;


public class negativeSum extends ConsoleProgram {
	
	public void run () {
		try {
			int n;
			int sum =0, counter=0;
				Scanner input = new Scanner(new File("negsum.txt"));
				//xxxx
				while(input.hasNextInt()) {

				n = input.nextInt();
				counter++;
				sum += n;
				println("n= " + n + "sum= " + sum);
				if(sum<0) {
					println(sum + " after " + counter + " steps");
					break;
				}
				
				if(sum>=0)
					println("no negative sum");
				
				int[] a = {1, 2, 3, 4, 5, 6};
					for(int b : a)
						println(b);
			}
			input.close();
		} catch (FileNotFoundException e){
			println("File Not Found.");
		}
	}

	
	
	
}
