package com.onlinetest.upwork;

import java.util.Set;

import com.google.common.collect.Sets;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.Getter;

@Getter
public class Network {

	private Set<Element> elements = Sets.newHashSet();

	/**
	 * builds set of elements
	 * @param size
	 * 	- elements size
	 */
	public Network(int size) {
		for (int i = 1; i <= size; i++) {
			elements.add(Element.builder().value(i).conections(Sets.newHashSet()).build());
		}

	}

	/**
	 * connects two elements
	 * @param left
	 * 	- id left element
	 * @param right
	 *  - id right element
	 */
	public void connect(int left, int right) {
		validateInputs(left, right);
		checkArgument(!query(left, right), "Already Connected.");

		Element elementLeft = selectElement(left);
		Element elementRight = selectElement(right);
		
		elementLeft.getConections().add(elementRight);
		elementRight.getConections().add(elementLeft);
	}

	/**
	 * checks if two elements are connected directly or indirectly
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean query(int left, int right) {
		validateInputs(left, right);
		Element elementLeft = selectElement(left);
		Element elementRight = selectElement(right);

		//Directly connection
		boolean isConected = elementLeft.getConections().stream()
				.anyMatch(element -> element.equals(elementRight));

		if (!isConected) {
			//Indirectly connection	
			isConected = elementLeft.getConections().stream()
					.anyMatch(element -> element.isConnected(elementRight));
		}

		return isConected;

	}

	/**
	 * validates inputs are correct values
	 * @param left
	 * @param right
	 */
	private void validateInputs(int left, int right) {
		// validates input parameters are valid
		checkArgument(elements.size() >= left && left > 0, "Invalid left value.");
		checkArgument(elements.size() >= right && right > 0, "Invalid right value.");
		checkArgument(left != right, "Left and right must be different.");
	}
	
	/**
	 * selects element from set
	 * @param value
	 * @return
	 */
	private Element selectElement (int value) {
		return elements.stream()
				.filter(element -> element.getValue() == value)
				.findFirst()
				.get();
	}

}
