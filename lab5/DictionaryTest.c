#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
        Dictionary A = newDictionary();
        printf("%d",isEmpty(A));
        insert(A, "one","two");
        printf("%s",lookup(A, "one"));
        printDictionary(stdout, A);
        printf("%d",isEmpty(A));
        printf("%d",size(A));
        makeEmpty(A);
        printf("%d",size(A));

}
