/*
 * Include the following lines in your makefile:
 *
 * unittest2: cardtest2.c dominion.o rngs.o
 *      gcc -o AdventurerTest -g  cardtest2.c dominion.o rngs.o $(CFLAGS)
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
	struct gameState state,pre;
	
	int i;
	int r = -5;
	int n = 0;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
	
	for (i = 0; i < sizeof(struct gameState); i++) {
      ((char*)&state)[i] = floor(Random() * 256);
    }
	
	r = initializeGame(2, k, 1, &state);
	
	printf ("initializeGame(4, k, 1, &G) = %d\n", r);
	assert(r == 0);
	
	state.hand[0][state.handCount[0]] = adventurer;//Add card to hand
    state.handCount[0]++;//Increment hand count
	
	memcpy(&pre, &state, sizeof(struct gameState));
	//Test Adventurer card
	r = cardEffect(adventurer, 1,2,3,&state,state.handCount[0],&n);
	
	//Pre count + 2 Treasury cards
	assert(pre.handCount[0] == (state.handCount[0] - 2));
	assert(pre.playedCardCount == state.playedCardCount);
	assert(state.discardCount[0] == 0);
	assert((pre.discardCount[0]+pre.deckCount[0]) == (state.discardCount[0] + (state.deckCount[0] + 2)));
	
	return 0;
}