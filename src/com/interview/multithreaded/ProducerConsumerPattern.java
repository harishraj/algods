package com.interview.multithreaded;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerPattern {

	public static void main(String args[]) {

		// Creating shared object
		BlockingQueue sharedQueue = new LinkedBlockingQueue();

		// Creating Producer and Consumer Thread
		Thread prodThread = new Thread(new Producer2(sharedQueue));
		Thread consThread = new Thread(new Consumer2(sharedQueue));

		// Starting producer and Consumer thread
		prodThread.start();
		consThread.start();
	}

}

// Producer Class in java
class Producer2 implements Runnable {

	private final BlockingQueue sharedQueue;

	public Producer2(BlockingQueue sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Produced: " + i);
				sharedQueue.put(i);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

}

// Consumer Class in Java
class Consumer2 implements Runnable {

	private final BlockingQueue sharedQueue;

	public Consumer2(BlockingQueue sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + sharedQueue.take());
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

}

/**
 * BlockingQueue amazingly simplifies implementation of Producer-Consumer design
 * pattern by providing out of the box support of blocking on put() and take().
 * Developer doesn't need to write confusing and critical piece of wait-notify
 * code to implement communication. BlockingQuue is an interface and Java 5
 * provides different implementation like ArrayBlockingQueue and
 * LinkedBlockingQueue , both implement FIFO order or elements, while
 * ArrayLinkedQueue is bounded in nature LinkedBlockingQueue is optionally
 * bounded
 **/
