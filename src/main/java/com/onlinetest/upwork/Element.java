package com.onlinetest.upwork;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Element {
	private int value;
	private Set<Element> conections;

	/**
	 * checks if an element is connected with this element
	 * @param toSearch
	 * 	- element to search
	 * @return
	 */
	public boolean isConnected(Element toSearch) {
		return conections.stream()
				.anyMatch(element -> element.equals(toSearch));
	}
}
