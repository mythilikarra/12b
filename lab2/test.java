
// Illustrates file IO and tokenization of strings.
import java.io.*;
import java.util.Scanner;
class test{
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] a;
		String[] token = null;
		int i, n,length, lineNumber = 0;
		// check number of command line arguments is at least 2
		if(args.length < 2){
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}
		// open files
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		// read lines from in, extract and print tokens from each line
		while( in.hasNextLine() ){
			lineNumber++;
			// trim leading and trailing spaces, then add one trailing space so
			// split works on blank lines
			line = in.nextLine().trim() + " ";
			// split line around white space
			token = line.split("\\s+");
			// print out tokens
			n = token.length;
			out.println("Line " + lineNumber + " contains " + n + " tokens:");
			for(i=0; i<n; i++){
				out.println(" "+token[i]);
			}
			String[] array = new String[length+token.length];
			for(int j = 0; j < token.length; j++) {
			array[j+length] = token[j];
			out.println(array[j+length]);
			}
			length =array.length;
		if(lineNumber == 1) {
			 a = new String[token.length];
                          for(int x = 0; x < token.length; x++) {
                          a[x] = token[x]; 
                          }
		}
		if(lineNumber > 1) {
			for(int s = 0; s < a.length; s++) {
                            array[s] = a[s]; 
                          }
			 a = new String[array.length]; 
			  for(int u = 0; u < array.length ;u++) {
                          a[u] = array[u];
                         }
		
		}	
}
		// close files
		in.close();
		out.close();
	}
}
