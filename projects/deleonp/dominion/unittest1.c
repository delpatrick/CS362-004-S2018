/*
 * Include the following lines in your makefile:
 *
 * unittest1: unittest1.c dominion.o rngs.o
 *      gcc -o FullDeckCntTest -g  unittest1.c dominion.o rngs.o $(CFLAGS)
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
	
	int r,i,n, pc;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
	int c[MAX_CARDS];
	
	state.numPlayers = 1;
	
	for (i =0; i < MAX_CARDS; i++)
	{
		pc = rand() % MAX_CARDS;
		state.hand[1][i] = pc;
		c[pc]++;
		state.handCount[1]++;
	}
	
	for (i=0; k[i]!=0; i++)
	{
		r = fullDeckCount(1,i,&state);
		assert((r==c[i])==0);
	}
	
}