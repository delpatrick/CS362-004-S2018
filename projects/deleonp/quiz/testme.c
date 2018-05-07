#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

char inputChar()
{
    char c 	  		= (char)0;
	int  minAscii	= 0;
	int  maxAscii 	= 256;
	int	 rNum 		= 0;
	struct timeval  ti;
	
	//Added because the seed for the random generator was updating really slow
	//gettimeofday(&ti,NULL);
	//srand(ti.tv_usec);
	
	rNum = rand() % maxAscii + minAscii;
	c = (char)rNum;
	
	return c;
}

char *inputString()
{
	char str[6];
	char c;
	int	 pos 		= 0;
	int  maxStrLen	= 5;
	int  minAscii	= 97; 	//"a"
	int  maxAscii 	= 122;	//"z"
	int	 rNum 		= 0;
	
	while (pos < maxStrLen)
	{
		rNum = rand() % (maxAscii-minAscii) + minAscii;
		
		str[pos] = (char)rNum;;
		pos++;
	}
	
	str[pos] = (char)0;
	
	return &str;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);
	
    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error \n\n\n");
      exit(200);
    }
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}
