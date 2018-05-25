/*
 * Include the following lines in your makefile:
 *
 * unittest3: unittest3.c dominion.o rngs.o
 *      gcc -o ShuffleTest -g  unittest3.c dominion.o rngs.o $(CFLAGS)
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
	
	int r = -5;
	int i;
	int n;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
		
	state.numPlayers = 1;
	
	for (i =0; i < MAX_CARDS; i++)
	{
		state.deck[1][i] = k[i];
		state.deckCount[1]++;
	}
	
	r = shuffle(1,&state);
	 
	assert(r == 0);
	
	//Check that the cards are not in the same place
	//Select three cards at random
	for (i = 0; i < 3; i++)
	{
		n = floor(Random() * MAX_CARDS);
		assert(state.deck[1][n] != k[n]); 
	}
	
	printf("Test passed...");
	
	return 0;
}