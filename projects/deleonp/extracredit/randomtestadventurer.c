#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 1

//The basic functionality of the Adventurer card is to allow the player to draw cards
//from the deck until two treasury cards were drawn. The drawn cards, that were not treasure cards,
//were placed in the discard pile.
int checkplayAdventurer(int p,int handCnt, int tresMulti, struct gameState *post, int n) {
  int r;
  struct gameState pre;
  //Added for weeksr testing
  int drawnTreasure  = 0;
  int cardDrawn = 0;
  int temphand[MAX_HAND];
  int z = 0; 
  int ret = 0;
  
  memcpy(&pre, post, sizeof(struct gameState));
    
  r = playAdventurer(drawnTreasure,p,cardDrawn,temphand,z,post,0);

  assert (r == 0);
  //replace assert for easier output
  if (pre.handCount[p] != (post->handCount[p] - 1))//Pre count + 2 Treasury cards - discard
  {
	  printf("Failed HandCount (%d): pre - %d, post - %d \n",n,pre.handCount[p],post->handCount[p]);
	  ret++;
  }
  
  if ((pre.discardCount[p]+pre.deckCount[p]) != (post->discardCount[p] + (post->deckCount[p] + 2)))
  {
	  printf("Failed discard/deck count (%d): pre - %d, post - %d \n",n,
			(pre.discardCount[p]+pre.deckCount[p]), 
			(post->discardCount[p] + (post->deckCount[p] + 2)));
			
	  ret++;
  }
  
  return ret;
}

int main () {

  int i, n, r,f;
  int p 			= 0;
  int t 			= 0;
  int remainTrs		= 2;
  int deckCount 	= 0;
  int discardCount	= 0;
  int handCount		= 0;

  struct gameState G;

  SelectStream(2);
  PutSeed(3);

  for (n = 0; n < 10000; n++) {
    for (i = 0; i < sizeof(struct gameState); i++) {
      ((char*)&G)[i] = floor(Random() * 256);
    }
	remainTrs = 2;  //Set number of treasury cards 
	 
    //which of two players is current, player zero is allowed
	p = floor(Random() * 2);
	
	//fill deck with a treasure card every t cards, 
	//zero not allowed due to Div by zero issue
	t = ceil(Random() * 5);// + 1;
	
	//determine the size of the initial handCount
	handCount = floor(Random() * MAX_HAND);
	
	//figure out deck and  discard count and ensure that combined
	//they will allow for two treasury cards
	do{
		if (n > 0 && (n % 100) == 0){
			deckCount = 0;
			discardCount = t*3;
			
		}else{
			deckCount = floor(Random() * MAX_DECK);
			discardCount = floor(Random() * MAX_DECK);
		}
	}while ((deckCount + discardCount) < (t*2));
	
	//Fill counts 
	G.whoseTurn = p;
	G.deckCount[p] = deckCount;
	G.discardCount[p] = discardCount;
	G.handCount[p] = handCount;
	
	//added for weeksr testing
	G.hand[p][0] = adventurer;
	G.playedCardCount = 0;
	
	//Fill decks
	if (deckCount >= (t*2)){
		//populate the deck with random cards with each 't' card as a treasury card
		for (i=0; i <= deckCount; i++){
			if ((i % t) != 0){
				G.deck[p][i] = estate;
			}else{
				G.deck[p][i] = copper;
			}
		}		
	}else if (deckCount > 0 && ((discardCount + deckCount) >= (t*2))){
		//populate the deck with one treasury card and the discard with one
		//printf("Both used\n");
		for (i=0; i <= deckCount; i++){
			if ((i % t) != 0){
				G.deck[p][i] = estate;
			}else{
				G.deck[p][i] = copper;
				remainTrs--;
			}
		}
		
		for (i=0; i <= discardCount; i++){
			if ((i % t) != 0){
				G.discard[p][i] = estate;
			}else{
				G.discard[p][i] = copper;
				remainTrs--;
				
				if (remainTrs == 0){
					break;
				}
			}
		}
		
	}else if (discardCount >= (t*2)){
		//populate the discard with random cards with each 't' card as a treasury card
		//printf("\tDiscard used\n");
		for (i=0; i <= discardCount; i++){
			if ((i % t) != 0){
				G.discard[p][i] = estate;
			}else{
				G.discard[p][i] = copper;
			}
		}
	}
     
    
    r = checkplayAdventurer(p,handCount,t,&G, n);
	//assert (r == 0);
	
	if (r != 0){
		printf("Player: %d, Interval: %d, Deck: %d, Discarc: %d, Hand: %d \n", p, t, deckCount, discardCount, handCount);
		f++;
	}
  }

  printf("Number of tests: %d\n",n);
  printf("Number of fails: %d\n",f);
  
  return 0;
}
