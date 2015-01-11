package com.interview.array;

/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/ 
 * 
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two
 * sets of data.
 * 
 * 1. The amount of petrol that petrol pump will give. 2. Distance from that
 * petrol pump to the next petrol pump.
 * 
 * Calculate the first point from where a truck will be able to complete the
 * circle (The truck will stop at each petrol pump and it has infinite
 * capacity). Expected time complexity is O(n). Assume for 1 litre petrol, the
 * truck can go 1 unit of distance.
 * 
 * For example, let there be 4 petrol pumps with amount of petrol and distance
 * to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The
 * first point from where truck can make a circular tour is 2nd petrol pump.
 * Output should be “start = 1″ (index of 2nd petrol pump).
 * 
 * A Simple Solution is to consider every petrol pumps as starting point and see
 * if there is a possible tour. If we find a starting point with feasible
 * solution, we return that starting point. The worst case time complexity of
 * this solution is O(n^2).
 * 
 * We can use a Queue to store the current tour. We first enqueue first petrol
 * pump to the queue, we keep enqueueing petrol pumps till we either complete
 * the tour, or current amount of petrol becomes negative. If the amount becomes
 * negative, then we keep dequeueing petrol pumps till the current amount
 * becomes positive or queue becomes empty.
 * 
 * Instead of creating a separate queue, we use the given array itself as queue.
 * We maintain two index variables start and end that represent rear and front
 * of queue.
 */

public class GasStationCircle {

	public int startTour(int gasAvailable[], int gasRequired[]) {
		int start = -1;
		int end = 0;
		int currentGas = 0;
		boolean visitedOnce = false;
		while (start != end) {
			currentGas += gasAvailable[end] - gasRequired[end];
			if (start == -1) {
				start = end;
			}
			if (end == gasAvailable.length - 1 && visitedOnce == false) {
				visitedOnce = true;
			} else if (end == gasAvailable.length - 1 && visitedOnce == true) {
				return -1;
			}
			if (currentGas < 0) {
				start = -1;
				currentGas = 0;
			}
			end = (end + 1) % gasAvailable.length;
		}

		return end;
	}

	public static void main(String args[]) {
		GasStationCircle gsc = new GasStationCircle();
		int[] gasAvailable = { 3, 3, 5, 8, 9 };
		int[] gasRequired = { 4, 1, 8, 4, 6 };
		System.out.println(gsc.startTour(gasAvailable, gasRequired));
	}
}
