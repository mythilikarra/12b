//Mythili Karra
//mmkarra
//12B
//14 March 2019
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
	char* v;
	char* k;
	int i;
	printf("%d\n", size(A));
	printf(lookup(A,"hi"));
	printf("%s\n", (isEmpty(A)?"true":"false")); 
	char* word1[] = {"one","two","three","four","five","six","seven"};
	char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};

	for(i=0; i<7; i++){
		insert(A, word1[i], word2[i]);
	}

	for(i=0; i<7; i++){
		k = word1[i];
		v = lookup(A, k);
		printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
	}
