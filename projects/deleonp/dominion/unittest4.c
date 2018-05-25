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
	struct gameState state;
	int r,i;
	int k[10] = {adventurer, council_room, feast, gardens,
		mine,remodel, smithy, village, baron, great_hall};
	int c[10] = {minion, steward, tribute, ambassador, cutpurse,
	embargo, outpost, salvager,sea_hag, treasure_map};
	
	state.numPlayers = 1;
	
	//initialize supplyCount
	r = -5;
	state.supplyCount[curse] = 1;
    state.supplyCount[estate] = 1;
    state.supplyCount[duchy] = 1;
    state.supplyCount[province] = 1;
    state.supplyCount[copper] = 1;
	state.supplyCount[silver] = 1;
	state.supplyCount[gold] = 1;
	
	for (i=0;k[i]!= 0;i++){
		state.supplyCount[k[i]] = 1;
	}
	
	for (i=0;c[i]!= 0;i++){
		state.supplyCount[c[i]] = 1;
	}
		
	//check game with all piles set	
	r = isGameOver(&state);
	assert( r == 0);
	
	//check game with one supply empty
	r = -5;
	state.supplyCount[estate] = 0;
	r = isGameOver(&state);
	assert(r == 0);
	
	//check game with two supplies empty
	r = -5;
	state.supplyCount[feast] = 0;
	r = isGameOver(&state);
	assert(r == 0);
	
	//check game with three supplies empty
	r = -5;
	state.supplyCount[tribute] = 0;
	r = isGameOver(&state);
	assert(r == 1);
	
	//check game with two supplies empty
	r = -5;
	state.supplyCount[feast] = 1;
	r = isGameOver(&state);
	assert(r == 0);
	
	//check game with one supply empty
	r = -5;
	state.supplyCount[tribute] = 1;
	r = isGameOver(&state);
	assert(r == 0);
	
	//check game with one supply and province empty
	r = -5;
	state.supplyCount[province] = 0;
	r = isGameOver(&state);
	assert(r == 1);
	
	//check game with only province empty
	r = -5;
	state.supplyCount[estate] = 1;
	r = isGameOver(&state);
	assert(r == 1);
	
	//check game with all piles set
	r = -5;
	state.supplyCount[province] = 1;
	r = isGameOver(&state);
	assert(r == 0);
	
	return 0;
}