CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/

#Compile tests programs
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)
	
testAdventurer: dominion.o rngs.o randomtestadventurer.c 
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o $(CFLAGS)	
	
testSmithy: dominion.o rngs.o randomtestcard1.c 
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)

testVillage: dominion.o rngs.o randomtestcard2.c 
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)	

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

#Run test programs
runDrawtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

runAdventurertests: randomtestadventurer 
	./randomtestadventurer &> randomtestadventurer.out
	gcov randomtestadventurer.c -b >> randomtestadventurer.out
	cat randomtestadventurer.c.gcov >> randomtestadventurer.out
	gcov dominion.c >> randomtestadventurer.out
	cat dominion.c.gcov >> randomtestadventurer.out
	
runCard1tests: randomtestcard1 
	./randomtestcard1 &> randomtestcard1.out
	gcov randomtestcard1.c -b >> randomtestcard1.out
	cat randomtestcard1.c.gcov >> randomtestcard1.out
	gcov dominion.c >> randomtestcard1.out
	cat dominion.c.gcov >> randomtestcard1.out
	
runCard2tests: randomtestcard2 
	./randomtestcard2 &> randomtestcard2.out
	gcov randomtestcard2.c -b >> randomtestcard2.out
	cat randomtestcard2.c.gcov >> randomtestcard2.out	
	gcov dominion.c >> randomtestcard2.out
	cat dominion.c.gcov >> randomtestcard2.out	
	
player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player 

tests: testAdventurer testSmithy testVillage

test: all tests runAdventurertests runCard1tests runCard2tests

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe randomtestadventurer randomtestadventurer.exe randomtestcard1 randomtestcard1.exe randomtestcard2 randomtestcard2.exe
