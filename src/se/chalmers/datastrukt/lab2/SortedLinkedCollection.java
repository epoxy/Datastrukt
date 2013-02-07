package se.chalmers.datastrukt.lab2;

import java.util.Iterator;

import testSortCol.CollectionWithGet;

import datastructures.LinkedCollection;

/**
 * An implementation of sorted singly linked collection of elements with the smallest element 
 * first and the biggest last.
 * @author Tomas, Anton
 *
 * @param <E> an element put in the collection.
 */
public class SortedLinkedCollection<E extends Comparable<E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	private Entry tail;

	/**
	 * Adding an element at the right position. That is sorting it into the order of elements 
	 * where the smallest element is first and the biggest is last.
	 * 
	 * @throws NullPointerException if parameter <tt>element</tt> is null. 
	 */
	@Override
	public boolean add(E element) {

		if (element == null) {
			throw new NullPointerException();
		}
		if (head == null) {
			head = new Entry(element, null);
			tail = head;
			return true;
		}
		if (head.element.compareTo(element) > 0) {
			head = new Entry(element, head);
			return true;
		}
		Entry current = head.next;
		Entry previous = head;
		while (current != null && current.element.compareTo(element) < 0) {
			previous = current;
			current = current.next;
		}
		if (current == null) {
			current = new Entry(element, null);
			tail = current;
		} else {
			current = new Entry(element, current);
		}
		previous.next = current;
		return true;
	}

	/**
	 * Method searching for an inputed argumentvalue. The method searchs for the value
	 * from the beginning of the collection and forward. If the value is found 
	 * it is returned. If the value is never found throughout the whole list, null is 
	 * returned.
	 */
	@Override
	public E get(E e) {
		if (e == null) {
			throw new NullPointerException("You have to type in a non-null value");
		}
		if (tail != null) {
			if (e.compareTo(tail.element) > 0) {
				return null;
			}
		}
		E element;
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			element = it.next();
			int tmpCompare = (e.compareTo(element));
			if (tmpCompare < 0) {
				return null;
			}
			if (tmpCompare == 0) {
				return element;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// Testcases
		// Creating a sorted linked collection of integers
		SortedLinkedCollection<Integer> sortedLink = new SortedLinkedCollection<Integer>();
		
		// adding the integer 5
		sortedLink.add(5);
		System.out.println("*" + sortedLink + " # Bör skriva ut: [5]");
		
		// adding the integers 2, 4 and 1
		sortedLink.add(2);
		sortedLink.add(4);
		sortedLink.add(1);
		System.out.println("*" + sortedLink + " # Bör skriva ut: [1, 2, 4, 5]");
		
		// adding a small value
		sortedLink.add(0);
		System.out.println("*" + sortedLink + " # Bör skriva ut: [0, 1, 2, 4, 5]");

		// adding a big value
		sortedLink.add(1766);
		System.out.println("*" + sortedLink + " # Bör skriva ut: [0, 1, 2, 4, 5, 1766]");
		
		// adding a value in the middle
		sortedLink.add(3);
		System.out.println("*" + sortedLink + " # Bör skriva ut: [0, 1, 2, 3, 4, 5, 1766]");

		// getting a value
		System.out.println("*" + sortedLink.get(0) + " # Bör skriva ut: 0");
		System.out.println("*" + sortedLink.get(5) + " # Bör skriva ut: 5");
		System.out.println("*" + sortedLink.get(1766) + " # Bör skriva ut: 1766");
		
		// getting a value that does not exist
		System.out.println("*" + sortedLink.get(27) + " # Bör skriva ut: null");
		
		/*trying to get a value with the argument null
		Will throw a NullPointerException. Outcommented to make the code compile.*/
		//System.out.println(sortedLink.get(null));
		
	}
}
