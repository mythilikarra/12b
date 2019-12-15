//Mythili Karra
//mmkarra
//12M
//Feb 23 2019
//This is Dictioanary.c


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

typedef struct NodeObj{
	char* key;
	char* val;
	struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* x, char* y) {
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N -> key = x;
	N -> val = y;
	N-> next = NULL;
	return(N);
}
// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
		*pN = NULL;
	}
}
typedef struct DictionaryObj{
	Node head;
	int numItems;
} DictionaryObj;

Dictionary newDictionary() {
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert( D != NULL);
	D -> head = NULL;
	D -> numItems = 0;
	return D;
}
void freeDictionary(Dictionary* pD) {
	if(pD != NULL && *pD != NULL) {
		free(*pD);
		*pD = NULL;
	}
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
	if(D == NULL) {
		fprintf(stderr, "Dictionary Error: lookup() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	Node A = D -> head;
	if(A != NULL) {
		if(D -> numItems == 1) {
			if(strcmp((A -> key),k) == 0){ 	
				return A -> val;
			}

		}
		else {
			for(int i=0; i<(D -> numItems); i++){
				if(strcmp(A->key, k) == 0) {
					return A -> val;
				}
				else { 
					A = A -> next;
				}
			}
		}
	}
	return NULL;
}

void makeEmpty (Dictionary D) {
	if(D == NULL) {
                fprintf(stderr, "Dictionary Error: makeEmpty() called on NULL Dictionary reference\n");
                exit(EXIT_FAILURE);
        }

	Node x = D-> head;
	while(x != NULL) {
		Node temp = x;
		x = x->next;
		freeNode(&temp);
	}
	D -> head = NULL;
	D -> numItems = 0;

}    

void insert(Dictionary D, char* ke, char* va) {	
	if(lookup(D,ke) == NULL) {
		if(D->head == NULL) {
			Node N = newNode(ke,va);
			D->head = N;
			D->numItems++;
		}
		else {
			Node N =D-> head;
			for(int i = 1; i < D-> numItems; i++) {
				if(N->next == NULL) {
					break;
				}
				else {
					N = N->next;
				}
			}
			Node x = newNode(ke, va);
			N->next = x;
			D->numItems++;
		}
	}
	else if(lookup(D,ke) != NULL) {      
		fprintf(stderr, "Dictionary Error: calling insert() on duplicate key\n");
		exit(EXIT_FAILURE);
	}
	else if(D==NULL ){
		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
}

void printDictionary(FILE* out, Dictionary D){
	Node H = D-> head;
	if(H == NULL) {
		fprintf(stderr, "Dictionary Error: printDictionary called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	while( H != NULL) {
		fprintf(out,"%s %s \n",H->key,H->val);
		H = H-> next;
	}
}

void delete(Dictionary D, char* k){
	if( D==NULL ){
		fprintf(stderr, 
				"Dictionary Error: calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
		//freeNode(&N);
	}

	if (lookup(D,k) != NULL) {
		Node N = D->head;
		if(D->numItems == 1) {
			if(strcmp((N->key),k) == 0) {
				Node temp = D -> head;   
				D->head = NULL;
				freeNode(&temp);   
				D->numItems--;
			}
		}
		else {
			if(strcmp((N->key),k) == 0) {
				Node temp = D -> head;     
				D-> head = N->next;
				freeNode(&temp);//here   
				D->numItems--;
			}
			else {
				for(int i=0; i < D->numItems; i++){
					if(strcmp((N->next->key),k) == 0) {
						Node temp = N -> next;
						N -> next = N->next->next;
						temp -> next = NULL;
						freeNode(&temp);
						temp = NULL;
						D->numItems--;
						break;
					}
					else {
						N = N->next;
					}
				}

			}

		}
	}
}

