CFLAGS = -Wall -fpic -coverage -lm

runtests: testme 
	./testme &> randomtestresult.out
	gcov testme.c >> unittestresult.out
	cat testme.c.gcov >> unittestresult.out


testme: testme.c
	gcc -o testme testme.c -g $(CFLAGS)

all: testme

clean:
	rm -f *.o testme.exe *.gcov *.gcda *.gcno *.so *.out 
