//Mythili Karra
//mmkarra
//12M
//01/22/2019
//This FileReverse java file will take in a string and reverse the order of the string.

import java.io.*;
import java.util.Scanner;
class FileReverse{
	public static String stringReverse(String s, int n){ 
		if(n > 0) {
			return s.charAt(n-1) + stringReverse(s,n-1);
		}
		else {
			return "";
		}
	}
	public static void main(String[] args) throws IOException {

		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i, n, lineNumber = 0;	
		//		check number of command line arguments is at least 2
		try {
			if(args.length != 2){
				System.out.println("Usage: FileReverse <input file> <output file>");
				System.exit(1);
			}
			// open files
			in = new Scanner(new File(args[0]));
			out = new PrintWriter(new FileWriter(args[1]));
			//	read lines from in, extract and print tokens from each line
			while( in.hasNextLine() ){
				lineNumber++;
				// trim leading and trailing spaces, then add one trailing space so
				// split works on blank lines
				line = in.nextLine().trim() + " ";
				// split line around white space
				token = line.split("\\s+");
				// print out tokens
				n = token.length;

				for(i=0; i<n; i++){
					char[] ch = token[i].toCharArray();	
					out.println(stringReverse(token[i],ch.length));
				}
			}

			// close files
			in.close();
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(args[0] + " (No such file or directory)");
			System.out.println("Usage: FileReverse file target1 [target2 ..]");
		}

	}
}


