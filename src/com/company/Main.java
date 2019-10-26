package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Q q = new Q();
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q)).start();
    }
}
