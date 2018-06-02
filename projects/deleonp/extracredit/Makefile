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
	
testAdventurer: randomtestadventurer.c dominion.o rngs.o  
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o $(CFLAGS)	
	
testSmithy: randomtestcard1.c dominion.o rngs.o  
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)

testVillage: randomtestcard2.c dominion.o rngs.o  
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)	

cardtest1: cardtest1.c dominion.o rngs.o
	gcc -o SmithyTest -g  cardtest1.c dominion.o rngs.o $(CFLAGS)
	
cardtest2: cardtest2.c dominion.o rngs.o
	gcc -o AdventurerTest -g  cardtest2.c dominion.o rngs.o $(CFLAGS)	
	
cardtest3: cardtest3.c dominion.o rngs.o
	gcc -o VillageTest -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
	
cardtest4: cardtest4.c dominion.o rngs.o
	gcc -o RemodelTest -g  cardtest4.c dominion.o rngs.o $(CFLAGS)	
	
unittest1: unittest1.c dominion.o rngs.o
	gcc -o FullDeckCntTest -g  unittest1.c dominion.o rngs.o $(CFLAGS)
	
unittest2: unittest2.c dominion.o rngs.o
	gcc -o HandCardTest -g  unittest2.c dominion.o rngs.o $(CFLAGS)

unittest3: unittest3.c dominion.o rngs.o
	gcc -o ShuffleTest -g  unittest3.c dominion.o rngs.o $(CFLAGS)	
	
unittest4: unittest4.c dominion.o rngs.o
	gcc -o GameOverTest -g  unittest4.c dominion.o rngs.o $(CFLAGS)
	
testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

#Run test programs
runDrawtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

runRandAdventurertests: randomtestadventurer 
	randomtestadventurer &> randomtestadventurer.out
	gcov randomtestadventurer.c -b >> randomtestadventurer.out
	cat randomtestadventurer.c.gcov >> randomtestadventurer.out
	gcov dominion.c >> randomtestadventurer.out
	cat dominion.c.gcov >> randomtestadventurer.out
	
runRandCard1tests: randomtestcard1 
	./randomtestcard1 &> randomtestcard1.out
	gcov randomtestcard1.c -b >> randomtestcard1.out
	cat randomtestcard1.c.gcov >> randomtestcard1.out
	gcov dominion.c >> randomtestcard1.out
	cat dominion.c.gcov >> randomtestcard1.out
	
runRandCard2tests: randomtestcard2 
	./randomtestcard2 &> randomtestcard2.out
	gcov randomtestcard2.c -b >> randomtestcard2.out
	cat randomtestcard2.c.gcov >> randomtestcard2.out	
	gcov dominion.c >> randomtestcard2.out
	cat dominion.c.gcov >> randomtestcard2.out	

runCardTest1: cardtest1
	./SmithyTest &> cardtest1.out
	gcov cardtest1.c -b >> cardtest1.out
	cat cardtest1.c.gcov >> cardtest1.out
	
runCardTest2: cardtest2
	./AdventurerTest &> cardtest2.out
	gcov cardtest2.c -b >> cardtest2.out
	cat cardtest2.c.gcov >> cardtest2.out
	
runCardTest3: cardtest3
	./VillageTest &> cardtest3.out
	gcov cardtest3.c -b >> cardtest3.out
	cat cardtest3.c.gcov >> cardtest3.out
	
runCardTest4: cardtest4
	./RemodelTest &> cardtest4.out
	gcov cardtest4.c -b >> cardtest4.out
	cat cardtest4.c.gcov >> cardtest4.out
	
runUnittest1: unittest1
	./FullDeckCntTest &> unittest1.out
	gcov unittest1.c -b >> unittest1.out
	cat unittest1.c.gcov >> unittest1.out
	
runUnittest2: unittest2
	./HandCardTest &> unittest2.out
	gcov unittest2.c -b >> unittest2.out
	cat unittest2.c.gcov >> unittest2.out
	
runUnittest3: unittest3
	./ShuffleTest &> unittest3.out
	gcov unittest3.c -b >> unittest3.out
	cat unittest3.c.gcov >> unittest3.out
	
runUnittest4: unittest4
	./GameOverTest &> unittest4.out
	gcov unittest4.c -b >> unittest4.out
	cat unittest4.c.gcov >> unittest4.out
	
player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player 

tests: testAdventurer testSmithy testVillage cardtest1 cardtest2 cardtest3 cardtest4 unittest1 unittest2 unittest3 unittest4

test: all tests runRandAdventurertests runCardTest2 

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out 
	rm -f testDrawCard testDrawCard.exe 
	rm -f randomtestadventurer randomtestadventurer.exe randomtestcard1 randomtestcard1.exe randomtestcard2 randomtestcard2.exe 
	rm -f SmithyTest SmithyTest.exe AdventurerTest AdventurerTest.exe VillageTest VillageTest.exe RemodelTest RemodelTest.exe
	rm -f FullDeckCntTest FullDeckCntTest.exe HandCardTest HandCardTest.exe ShuffleTest ShuffleTest.exe GameOverTest GameOverTest.exe
