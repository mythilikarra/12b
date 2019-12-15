// FileTokens.java
// // Illustrates file IO and tokenization of strings.
import java.io.*;
import java.util.Scanner;
class FileTokens{
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
	//	String[] array = new String[0];
		String[] finalarray = new String[10];
		int i, n, lineNumber = 0;
		//	check number of command line arguments is at least 2
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
		//	out.println("Line " + lineNumber + " contains " + n + " tokens:");
	//out.println(array.length);	
		for(i=0; i<n; i++){
		//		out.println(" "+array[i]);
				String[] array1 = new String[array.length + n];
				String[] array = null;
				if(array != null) {
				for(int j = 0; j < array.length;j++) {
				array1[j] = array[j];
				}
				for(int k = array.length; k < array1.length; k++) {
				array1[k] = token[k-array.length];
				}
array = new String[token.length];
for(int x = 0; x < token.length; x++) {
array[x] = token[x];
}
for(int y = 0; y < array1.length;y++) {
finalarray[y] = array1[y]; 
//out.println(" "+ array1[y]);
}
				}	
				
		if(array == null) {
		String[] array = new String[token.length];
		for(int p = 0; p < token.length; p++) {
		array1[p] = token[p];
		array[p] = token[p];
		}
		for(i=0; i<array.length; i++){
                                out.println("HI");
		}
		}	
		}
}
		for(int y = 0; y < finalarray.length;y++) {
out.println(" "+finalarray[y]);
}
		// close files
		in.close();
		out.close();
	}
} 
