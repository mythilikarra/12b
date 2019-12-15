//#Mythili Karra
//#mmkarra
//#12M
//#2/8/2019
//This is charType.c that will go through every line in a file and will seperate the letters, numbers, whitespaces, and punctuation marks.
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>
#define MAX_STRING_LENGTH 100
int main(int argc, char* argv[]){
	FILE* in;        // handle for input file                  
	FILE* out;       // handle for output file                 
	char* line;      // string holding input line              
	char* alpha; // string holding all alpha chars
	char* num;
	char* punct;
	char* white;
	void extract_chars(char* s, char* a, char* d, char* p, char* w); 
	// check command line for correct number of arguments 
	if( argc != 3 ){
		printf("Usage: %s input-file output-file\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	// open input file for reading 
	if( (in=fopen(argv[1], "r"))==NULL ){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	// open output file for writing 
	if( (out=fopen(argv[2], "w"))==NULL ){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}
	// allocate strings line and alpha_num on the heap 
	line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
	alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
	num  = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
	punct  = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
	white  = calloc(MAX_STRING_LENGTH+1, sizeof(char) );

	assert( line!=NULL && alpha!=NULL && num != NULL && punct != NULL && white != NULL );
	// read each line in input file, extract alpha-numeric characters 
	int x = 1;
	while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
		extract_chars(line, alpha, num, punct, white);
		fprintf(out, "line %i contains:\n", x);
		if(strlen(alpha) == 1) {
		fprintf(out, "%i alphabetic character: %s\n",(int)strlen(alpha), alpha);
		}
		else {
		fprintf(out, "%i alphabetic characters: %s\n",(int)strlen(alpha), alpha);
		}
		if(strlen(num) == 1) {
		fprintf(out, "%i numeric character: %s\n",(int)strlen(num), num);		
		}
		else {
		fprintf(out, "%i numeric characters: %s\n",(int)strlen(num), num);
		}
		if(strlen(punct) == 1) {
		fprintf(out, "%i punctuation character: %s\n",(int)strlen(punct), punct);
		}
		else {
		fprintf(out, "%i punctuation characters: %s\n",(int)strlen(punct), punct);
		}
		if(strlen(white) == 1){
		fprintf(out, "%i whitespace character: %s\n",(int)strlen(white), white);
		}
		else {
		fprintf(out, "%i whitespace characters: %s\n",(int)strlen(white), white);
		}
		x++;
}


	// free heap memory 
	free(line);
	free(alpha);
	free(num);
	free(punct);
	free(white);
	// close input and output files 
	fclose(in);
	fclose(out);

	return EXIT_SUCCESS;
}
// function definition
void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int i=0, j=0, k = 0, l = 0, m = 0;
	while(s[i]!='\0' && i<MAX_STRING_LENGTH){
		if( isalpha( (int) s[i]) ) {
			a[j++] = s[i];
			i++;
		}
		else if( ispunct( (int) s[i]) ) {
			p[k++] = s[i];
			i++;
		}
		else if( isdigit( (int) s[i]) ) {
			d[l++] = s[i];
			i++;
		}
		else {
			w[m++] = s[i];
			i++;
		}
		a[j] = '\0';
		p[k] = '\0';
		d[l] = '\0';
		w[m] = '\0';
	}
}
