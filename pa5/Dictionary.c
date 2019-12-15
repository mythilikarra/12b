//Mythili Karra
////mmkarra
////12B
////14 March 2019

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"
const int arraylength = 101;
typedef struct NodeObj {
	char* key;
	char* value;
	struct NodeObj* next;
}NodeObj;
typedef NodeObj* Node;
Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	N->key = k;
	N->value = v;
	N->next = NULL;
	return N;
}
void freeNode(Node* pN) {
	if(pN != NULL && *pN != NULL) { //pN is pointer, *pN refers to pointer(node) of pointer
	//pN is a pointer to a node, which is a pointer to a NodeObj
		free(*pN);//frees the Node pointer
		*pN = NULL;
	}
}
typedef struct DictionaryObj {
	Node* array;
	int numItems;
}DictionaryObj;

typedef DictionaryObj* Dictionary;
Dictionary newDictionary() {
	Dictionary D = malloc(sizeof(DictionaryObj));
	D->array = malloc(arraylength*sizeof(Node));
	for(int i = 0; i < arraylength; i++) {
		D->array[i] = NULL;
	}
	D-> numItems = 0;
	assert(D != NULL);
	assert(D->array != NULL);
	return D;
}
void freeDictionary(Dictionary* pD) {
	if(pD != NULL && *pD != NULL) {
		makeEmpty(*pD);
		free((*pD)->array);
		free(*pD);
		*pD = NULL;
	}
}
//rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%arraylength;
}

int isEmpty(Dictionary D) {
	if(D == NULL) {
		fprintf(stderr, "Dictionary Error: isEmpty() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D -> numItems == 0);
}
int size(Dictionary D) {
	if(D == NULL) {
		fprintf(stderr, "Dictionary Error: size() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return(D -> numItems);
}
char* lookup(Dictionary D, char* k) {
	int index = hash(k);
	if(D == NULL) {
		fprintf(stderr, "Must call lookup() on valid Dictionary!\n");
		exit(EXIT_FAILURE);
	} 
	if(D->array[index]!= NULL) {
		Node node = D->array[index];
		while(node != NULL) {
			if(strcmp(node->key,k) == 0) {
				return node-> value;
			}
			else {
				node = node->next;
			}
		}
	}
	return NULL;
}
void insert(Dictionary D, char* k, char* v) {
	int index = hash(k);
	Node node = newNode(k,v);
	if(D == NULL) {
		fprintf(stderr, "Dictionary Error: size() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if(lookup(D,k) == NULL) {
		if(D->array[index] == NULL) {
			D->array[index] = node;
			D-> numItems++;
		}	
		else {
			node->next = D->array[index];
			D->array[index] = node;
			D->numItems++;
		}
	}
}

void makeEmpty(Dictionary D) {
	for(int i = 0; i < arraylength; i++) {
		while(D->array[i] != NULL) {
			if(D->array[i]->next != NULL) {
				Node N = D->array[i]->next;
					freeNode(&(D->array[i]));
				D->array[i] = N;
			}
			if(D->array[i]->next== NULL) {
				freeNode(&(D->array[i]));
				D->array[i] = NULL;
			}
		}
	}
	D->numItems = 0;
}
void delete(Dictionary D, char* k) {
	int index = hash(k);
	Node N = D->array[index];
	if(lookup(D,k) == NULL) {
		fprintf(stderr,"Cannot use delete()  on a non-existent key\n");
	}
	if(strcmp(N->key,k) == 0) {
		N = N->next;
			freeNode(&(D->array[index]));
		D->numItems--;
		D->array[index] = N;
	}
	else {
		while(N !=NULL) {
			if(N->next->key == k) {
				Node x = N->next->next;
				freeNode(&(N->next));
				N->next = x;
				D->numItems--;	
				break;	
			}
			else {
				N = N->next;
			}

		}
	}
}	
void printDictionary(FILE* out, Dictionary D){
	if( D==NULL ){
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	Node node;
	for(int i = 0; i < arraylength; i++) {
		node = D->array[i];
		while(node != NULL) {
			fprintf(out, "%s %s\n" , node->key, node->value);
			node = node->next;
		}
	}
}

