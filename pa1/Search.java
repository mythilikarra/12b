//Mythili Karra
//mmkarra
//12M
//01/22/2019
//This Search java file will use merge, mergesort, and binary search functions to help a user search for a particular string in a file.

import java.io.*;
import java.io.File;
import java.util.*;
import java.util.Scanner;
public class Search {
	public static void mergeSort(String[] A, int[] lineNumber, int p, int r){
		int q;
		if(p < r) {
			q = (p+r)/2;

			mergeSort(A, lineNumber, p, q);
			mergeSort(A, lineNumber, q+1, r);
			merge(A, lineNumber, p, q, r);
		}
	} 

	public static void merge(String[] A, int[] lineNumber, int p, int q, int r){
		int[] ln = lineNumber;
		int n1 = q-p+1;
		int n2 = r-q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		int[] Left = new int[n1];
		int[] Right = new int[n2];
		String[] original = new String[A.length];
		int i, j, k;

		for(i=0; i<n1; i++){
			L[i] = A[p+i];
			Left[i] = lineNumber[p+i];

		}
		for(j=0; j<n2; j++){ 
			R[j] = A[q+j+1];
			Right[j] = lineNumber[q+j+1];

		}

		i = 0; j = 0;
		for(k=p; k<=r; k++){
			if( i<n1 && j<n2 ){
				if( L[i].compareTo(R[j])<0 ){
					A[k] = L[i];
					lineNumber[k] = Left[i];
					i++;
				}else{
					A[k] = R[j];
					lineNumber[k] = Right[j];
					j++;
				}
			}else if( i<n1 ){
				A[k] = L[i];
				lineNumber[k] = Left[i];
				i++;
			}else{ 
				A[k] = R[j];
				lineNumber[k] = Right[j];
				j++;
			}

		}


	}
	static int binarySearch(String[] A, int p, int r, String t) {
		int q;
		if(p > r) {
			return -1;
		}
		else{
			q = (p+r)/2;
			if(t.equals(A[q])){
				return q;         
			}
			else if(!(A[q] == null) && !(t == null)) {
				if(t.compareTo(A[q])<0){
					return binarySearch(A, p, q-1, t);
				}else{ 
					return binarySearch(A, q+1, r, t);
				}
			}
		}
		return q;
	}
	public static void main(String[] args) {

		String[] B = {"entire", "beginning", "possibly", "specified", "key","value", "initial", "before", 
			"dictionary","however"};
		String[] original = {"entire", "beginning", "possibly", "specified", "key","value", "initial", "before", 
			"dictionary","however"};
		try {  
			if(args.length <= 1){
				System.err.println("Usage: Search file target1 [target2 ..]");
				System.exit(1);
			}

			Scanner in = new Scanner(new File(args[0]));
			int lineCount = 0;
			int m = 0;
			while( in.hasNextLine() ){
				in.nextLine();
				lineCount++;
			}
			in.close();
			String[] input = new String[lineCount];
			Scanner sc = new Scanner(new File(args[0]));
			while(sc.hasNextLine() ) {
				input[m] = sc.nextLine();
				m++;
			}
			int[] linenumber = new int[lineCount];
			for(int u = 0; u < lineCount; u++) {
				linenumber[u] = u + 1;
			}
			mergeSort(input,linenumber, 0, input.length-1);
			for(int p = 0; p < lineCount; p++) {
			}

			for(int i=0; i<args.length-1; i++) {
				int bin = binarySearch(input, 0, input.length-1, args[i+1]);
				if(bin == -1) {
					System.out.println(args[i+1] + " not found");
				}
				else {
					System.out.println(args[i+1]+ " found on line " + linenumber[binarySearch(input, 0, input.length-1, args[i+1])]+ " ");
				}
			}
	}
	catch (FileNotFoundException e) {
		System.out.println(args[0] + " (No such file or directory)");
		System.out.println("Usage: Search file target1 [target2 ..]");
	}
}

}

