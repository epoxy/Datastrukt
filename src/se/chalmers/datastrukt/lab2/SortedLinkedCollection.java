package se.chalmers.datastrukt.lab2;

import java.util.Iterator;

import testSortCol.CollectionWithGet;

import datastructures.LinkedCollection;

public class SortedLinkedCollection<E extends Comparable<E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	public SortedLinkedCollection() {

	}

	private Entry tail;

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

	@Override
	public E get(E e) {
		if (e == null) {
			throw new NullPointerException();
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
		SortedLinkedCollection<Integer> sortedLink = new SortedLinkedCollection<Integer>();
		sortedLink.add(5);
		sortedLink.add(2);
		sortedLink.add(4);
		sortedLink.add(182);
		sortedLink.add(0);
		sortedLink.add(17);
		sortedLink.add(9);
		sortedLink.add(53);

		System.out.println(sortedLink.get(0));
		System.out.println(sortedLink.get(4));
		System.out.println(sortedLink.get(17));
		System.out.println(sortedLink.get(183));
		System.out.println(sortedLink.get(53));
		System.out.println(sortedLink.toString());
	}
}
