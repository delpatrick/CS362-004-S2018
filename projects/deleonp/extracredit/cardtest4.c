/*
 * Include the following lines in your makefile:
 *
 * unittest2: cardtest4.c dominion.o rngs.o
 *      gcc -o RemodelTest -g  cardtest4.c dominion.o rngs.o $(CFLAGS)
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
	
	int r = -5;
	int i;
	int n = 0;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
	
	for (i = 0; i < sizeof(struct gameState); i++) {
      ((char*)&state)[i] = floor(Random() * 256);
    }
	
	r = initializeGame(2, k, 1, &state);
	
	memcpy(&pre, &state, sizeof(struct gameState));

	printf ("initializeGame(4, k, 1, &G) = %d\n", r);
	assert(r == 0);
	
	state.hand[0][state.handCount[0]] = remodel;//Add card to hand
    state.handCount[0]++;//Increment hand count
	

	r = cardEffect(remodel,estate,feast,gold,&state,state.handCount[0],&n);
	
	//Pre count,swap card - discard
	assert(pre.handCount[0] == (state.handCount[0] + 1 ));
	assert(pre.playedCardCount == (state.playedCardCount - 2));
	
	return 0;
}