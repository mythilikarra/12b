//Mythili Karra
////mmkarra
////12B
////02/5/2019
//This is the Queens java file that will check for the number of solutions for a specific amount of queens
import java.io.*;
import java.io.File;
import java.util.Scanner;
public class Queens {
	static int sum = 0;
	static int x;
	static void placeQueen(int[][] B, int i, int j){
		int a = j;
		int b = i;

		B[i][j] = 1;
		B[i][0] = j;

		for(int x = i; (x+1) < B.length; x++) { //right
			if((a+1) < B.length) {
				if(B[x+1][a+1] == 1) {

				}
				else {
					B[x+1][a+1] = B[x+1][a+1]-1;
				}
			}
			a++;
		}
		for(int x = j; (x-1) > 0; x--) { //left
			if((b+1) < B.length) {
				if(B[b+1][x-1] == 1) {
				}
				else {
					B[b+1][x-1] = B[b+1][x-1]-1;
				}
				b++;
			}
		}
		for(int z = i; (z+1) < B.length; z++) { //under
			if(B[z+1][j] == 1) {
			}
			else {
				B[z+1][j] = B[z+1][j]-1;
			}
		}
		for(int c = j; (c+1) < B.length; c++) { //right horiz
			if(B[i][c+1] == 1) {
			}
			else {
				B[i][c+1] = B[i][c+1]-1;
			}
		}
		for(int d = j; (d-1) > 0; d--) { //right horiz
			if(B[i][d-1] == 1) {
			}
			else {
				B[i][d-1] = B[i][d-1]-1;
			}
		}
	}

	static void removeQueen(int[][] B, int i, int j){
		int a = j;
		int b = i;
		B[i][j] = 0;
		for(int x = i; (x+1) < B.length; x++) { //right
			if((a+1) < B.length) {
				B[x+1][a+1] = B[x+1][a+1]+1;
			}
			a++;
		}
		for(int x = j; (x-1) > 0; x--) { //left
			if((b+1) < B.length) {
				B[b+1][x-1] = B[b+1][x-1]+1;
			}
			b++;
		}

		for(int z = i; (z+1) < B.length; z++) { //under
			B[z+1][j] = B[z+1][j]+1;
		}
		for(int c = j; (c+1) < B.length; c++) { //right horiz
			B[i][c+1] = B[i][c+1]+1;
		}
		for(int d = j; (d-1) > 0; d--) { //right horiz
			B[i][d-1] = B[i][d-1]+1;
		}
	}

	static void printBoard(int[][] B) {
		System.out.print("(");
		for(int i = 1; i < B.length; i++) {
			if(i != B.length - 1) 
			System.out.print(B[i][0] + ",");
			else {
			System.out.print(B[i][0]);
			}
		}
		System.out.print(")");
		System.out.println("");
	}


	static int findSolutions(int[][] B, int i, String mode) {
		int n = B.length-1;
		for(int a = 1; a < B.length; a++) {
			if(i > n) {
				if(mode == "verbose") {
					printBoard(B);
				}
				sum++;
				x = x + 1;
				return 1;
			}
			if(B[i][a] == 0) {
				placeQueen(B, i, a);
				B[0][a] = 1;
				x = findSolutions(B, 1+i, mode);
				removeQueen(B, i, a);	
			}
		}		
		return sum;

	}

	public static void main(String[] args) {
	//	findSolutions(B, 1, "verbose");
	//	System.out.println(sum);
		try {
		if(args.length == 2) {
			int arg = Integer.parseInt(args[1]);
			int[][]B = new int[arg+1][arg+1];
			if(args[0].equals( "-v")) {
			findSolutions(B, 1, "verbose");
                	System.out.println(arg + "-Queens has " + sum + " solutions");
			}
		}
		if(args.length == 1) {
		int arg = Integer.parseInt(args[0]);
		}
		}
		catch (NumberFormatException e) {
		System.out.println("Usage: Queens [-v] number");
		System.out.println("Option: -v verbose output, print all solutions");
		System.exit(0);
		}
		if(args.length == 1) {
		int arg = Integer.parseInt(args[0]);
		int[][]B = new int[arg+1][arg+1];
		findSolutions(B, 1, "");
                System.out.println(arg + "-Queens has " + sum + " solutions");	
		}
		if(args.length == 0) {
		System.out.println("Usage: Queens [-v] number");
		System.out.println("Option: -v verbose output, print all solutions");
		}
		}
	
}

