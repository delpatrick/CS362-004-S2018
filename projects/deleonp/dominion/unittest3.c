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
	struct gameState *statePre;
	struct gameState *statePost;
	
	int r,i,n;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
	
	statePre->numPlayers = 1;
		
	if (statePre->deckCount[0] > 0)
	{
		statePre->deck[1][i] = k[i];
		statePre->deckCount[1]++;
	}
	memcpy(&statePost,&statePre, sizeof(struct gameState));
	r = shuffle(1,statePost);
	assert(memcmp(&statePre,&statePost, sizeof(struct gameState)) == 0);
}