/*
 * Include the following lines in your makefile:
 *
 * unittest2: unittest2.c dominion.o rngs.o
 *      gcc -o HandCardTest -g  unittest2.c dominion.o rngs.o $(CFLAGS)
 *
*/
#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <math.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1
#define MAX_CARDS 10


int main(){
	struct gameState state;
	
	int r,i,pc[MAX_CARDS];
	
	state.numPlayers = 1;
	
	for (i =0; i < MAX_CARDS; i++)
	{
		pc[i] = floor(Random() * 26);
		state.hand[0][i] = pc[i];
		state.handCount[0]++;
	}
	
	for (i=0; i < MAX_CARDS; i++)
	{
		r = handCard(i,&state);
		assert(r == pc[i]);
	}
	
	printf("Test passed...");
	
	return 0;
}