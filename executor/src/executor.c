#include <stdio.h>
#include <stdlib.h>

int main(int argc,char* argv[]){
	if (argc == 1) {
		printf ("Please provide the args to execute\n");
		return -1;
	}
	int length = 0;
	for (int i=1; i < argc; i++) {
		length += strlen(argv[i]) + 1;
	}
	char* command;
	command = malloc(sizeof(char)*length);
	command[0] = '\0';
	for (int i=1; i < argc; i++) {
		strcat(command, argv[i]);
		if (i < argc -1)
		 strcat(command, " ");
		
	}
	printf ("Executing command: %s\n", command);
	int status = system(command);
	printf("Executed command and status is:%d\n", status);
	free(command);
	return status;
	
}