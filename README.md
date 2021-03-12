# coursera-princeton-algs1-
Coursera Princeton Algorithms 1 course

Week 1:
Percolation specs: https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

Results:
100%. Passed all test plus bonus memory for Permutation.java

Deque.java
Implement a Deque using doubly linked lists

RandomizedQueue.java
- Implement a randomized queue using resizing arrays
- Issue: Used StdRandom.shuffle() to shuffle the array, but when running two concurrent iterators, the second iterator changes the array that is also being used by the first iterator
	- Fix: In ArrayIterator class constructor, create an int[] of size N. StdRandom.shuffle() the int[]. Then use this to index into the original RandomizedQueue in random order.

Permutation.java
- Create a client program that reads from standard input and selects k items out of the n total items to print out
- Issue: Currently, read each string using StdIn.readString() and stored into a RandomizedQueue, which is of size n. Memory bonus given if data structure used is of size k.
	- Fix: Looked into reservoir sampling, with size n unknown.
