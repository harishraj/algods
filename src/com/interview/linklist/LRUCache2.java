package com.interview.linklist;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache2<K, V> extends LinkedHashMap<K, V> {

	private int cap;

	// Maximum number of items in the cache.
	public LRUCache2(int cap) { 
  		super(cap+1, 1.0f, true);
  		// Pass 'true' for accessOrder.
  		this.cap = cap;
	}
	
	protected boolean removeEldestEntry(Entry entry) {
  		return (size() > this.cap);
	}	
}

/**
 * Requirements
 * 
 * Fixed size: The cache needs to have some bounds to limit memory usage. Fast
 * access: The cache insert and lookup operations need to be fast preferably
 * O(1) time. Entry replacement algorithm: When the cache is full, the less
 * useful cache entries are purged from cache. The algorithm to replace these
 * entries is Least Recently Used (LRU) - or the cache entries which have not
 * been accessed recently will be replaced. Design discussion
 * 
 * Since the lookup and insert operationed need to fast a HashMap would be a
 * good candidate. The HashMap accepts an initial capacity parameter but it
 * re-sizes itself if more entries are inserted. So we need to override the
 * put() operation and remove (or purge) an entry before inserting.
 * 
 * How do we select the entry to be purged? One approach is to maintain a
 * timestamp at which the entry was inserted and select the entry with the
 * oldest timestamp. But this search would be linear taking O(N) time.
 * 
 * So we need the entries to be maintained in a sorted list based on the order
 * in which the entries were accessed. An alternate way to achieve this would be
 * to maintain the entries in a doubly linked list using which everytime an
 * entry is accessed ( a cache lookup operation), the entry is also moved to the
 * end of the list. When we need to purge the entries it is done from the top of
 * the list. In an ArrayList when an element is removed the rest of the entries
 * need to be moved by one to fill the gap. A doubly linked list does not have
 * this issue.
 * 
 * We have come up with a design that meets our requirements and guarantees O(1)
 * insert and O(1) lookup operations and also has a configurable limit on the
 * number of entries. Let's begin the implementation.
 * 
 * Lucky for us, JDK already provides a class that is very suitable for our
 * purpose - LinkedHashMap. This class maintains the entries in a HashMap for
 * fast lookup at the same time maintains a doubly linked list of the entries
 * either in AccessOrder or InsertionOrder. This is configurable so use use
 * AccessOrder as true. It also has a method removeOldestEntry() which we can
 * override to return true when the cache size exceeds the specified
 * capacity(upper limit).
 **/
