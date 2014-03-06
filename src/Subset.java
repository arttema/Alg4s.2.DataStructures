/**
 * Class created by:
 * Author: artyom
 * Date: 16.02.14
 * Time: 22:14
 * Copyright 2014
 * Write a client program Subset.java that takes a command-line integer k; reads in a sequence of N strings
 * from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once. You may assume that k ≥ 0 and no greater
 * than the number of string N on standard input.

 % echo A B C D E F G H I | java Subset 3       % echo AA BB BB BB BB BB CC CC | java Subset 8
 C                                              BB
 G                                              AA
 A                                              BB
 CC
 % echo A B C D E F G H I | java Subset 3       BB
 E                                              BB
 F                                              CC
 G                                              BB
 The running time of Subset must be linear in the size of the input. You may use only a constant amount of
 memory plus either one Deque or RandomizedQueue object of maximum size at most N, where N is the number of
 strings on standard input. (For an extra challenge, use only one Deque or RandomizedQueue object of maximum size
 at most k.) It should have the following API.
 public class Subset {
 public static void main(String[] args)
 }
 */
public class Subset {
    public static void main(String[] args) {
        int outputSize = Integer.parseInt(args[0]);
        String[] input = new String[100];

        int inputSize = 0;
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            input[inputSize++] = s;
        }
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for(int i=0; i<inputSize; i++){
            queue.enqueue(input[i]);
        }
//        String[] output = new String[inputSize];
//        System.arraycopy(input, 0, output, 0, inputSize);

//        StdRandom.shuffle(output);
        for(int i=0; i<outputSize; i++){
            System.out.println(queue.dequeue());
        }
    }
}
// cd "C:\Users\artemstolpovski\Google Drive\Education\Algorithms I (1)\programming assignments\Week 2\Alg4s.2.DataStructures\out\production\Alg4s.2.DataStructures"
// java Subset 3 <input.txt
