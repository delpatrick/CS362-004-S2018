/*
 * Include the following lines in your makefile:
 *
 * unittest4: unittest4.c dominion.o rngs.o
 *      gcc -o GameOverTest -g  unittest4.c dominion.o rngs.o $(CFLAGS)
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
	struct gameState *state;

	
	int r,i,n;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, province, great_hall};
	
	state->numPlayers = 1;
		
	if (state->deckCount[0] > 0)
	{
		state->deck[1][i] = k[i];
		state->deckCount[1]++;
	}
	
	r = isGameOver(&state);
	assert( r == 0);
	
	discardCard(8,1,&state,1);
	r = isGameOver(&state);
	assert( r == 1);
}