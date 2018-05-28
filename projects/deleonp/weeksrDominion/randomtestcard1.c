#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

//The basic functionality of the Smithy card is to allow the player to draw 3 cards 
//from the deck and discard the smithy card. The hand is populated with at least 1 
//Smithy card at the hand position passed in. Test will only test the smithy card at
//at that position.
int checkplaySmithy(int p, int handPos, struct gameState *post, int n) {
  int r;
  struct gameState pre;
  
  memcpy(&pre, post, sizeof(struct gameState));
    
  r = playSmithy(p, post, handPos,0);

  assert (r == 0);
  
  //Pre count + 3 cards - 1 discard
  //assert (pre.handCount[p] == (post->handCount[p] - 2));
  if (pre.handCount[p] == (post->handCount[p] - 2)){
	  printf("Failed HandCount (%d): pre - %d, post - %d \n",n,pre.handCount[p],post->handCount[p]);
  }
  //Smithy removed from hand at handpos, as long as the card after was not also a smithy
  if (handPos != pre.handCount && pre.hand[p][handPos+1] != smithy){
	  assert (post->hand[p][handPos] != smithy);
  }
  //Was Smithy card added to the discard? correct = no
  assert(pre.discardCount[p] == post->discardCount[p]);
  //Did a card get added to the played card count? 
  assert(pre.playedCardCount == (post->playedCardCount - 1));
  
  return 0;
}

int main () {

  int i, n, r;
  int p 			= 0;
  int handPos		= 0;
  int deckCount 	= 0;
  int discardCount	= 0;
  int handCount		= 0;

  struct gameState G;

  SelectStream(2);
  PutSeed(3);

  for (n = 0; n < 100; n++) {
    for (i = 0; i < sizeof(struct gameState); i++) {
      ((char*)&G)[i] = floor(Random() * 256);
    }
	//which of two players is current, player zero is allowed
	p = floor(Random() * 2);
	
	//determine the size of the initial handCount
	handCount = floor(Random() * MAX_HAND);
	//detrmine the initial position of the smithy card
	handPos	  = ceil(Random() * handCount);
	
	//determine deck and  discard count 
	deckCount = floor(Random() * MAX_DECK);
	discardCount = floor(Random() * MAX_DECK);
	
	
	//Fill counts 
	G.whoseTurn = p;
	G.deckCount[p] = deckCount;
	G.discardCount[p] = discardCount;
	G.handCount[p] = handCount;
	G.playedCardCount = 0;
	
	//Fill decks
	for (i=0; i < handCount; i++){
		if (i == handPos){
			G.hand[p][i] = smithy;
		}else{
			//Randomly fill the rest of the hand
			G.hand[p][i] = floor(Random() * 26); 
		}
	}		
	
	//add end marker, for visual debugging
	G.hand[p][i]=-1;
     
    
    r = checkplaySmithy(p,handPos,&G,n);
	
	assert (r == 0);
  }

  return 0;
}
