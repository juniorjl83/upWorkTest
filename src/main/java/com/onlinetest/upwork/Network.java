package com.onlinetest.upwork;

import java.util.Set;

import com.google.common.collect.Sets;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.Getter;

@Getter
public class Network {

	private Set<Element> elements = Sets.newHashSet();

	public Network(int size) {
		for (int i = 1; i <= size; i++) {
			elements.add(Element.builder().value(i).conections(Sets.newHashSet()).build());
		}

	}

	public void connect(int left, int right) {
		validateInputs(left, right);
		checkArgument(!query(left, right), "Already Connected.");
		Element elementSelected = elements.stream()
				.filter(element -> element.getValue() == left)
				.findAny()
				.get();

		elementSelected.getConections().add(right);
	}

	public boolean query(int left, int right) {
		validateInputs(left, right);
		return elements.stream()
			.anyMatch(elementLeft -> (elementLeft.getValue() == left
					&& elementLeft.getConections().stream().anyMatch(conexion -> conexion == right)) 
					|| (elementLeft.getValue() == right
							&& elementLeft.getConections().stream().anyMatch(conexion -> conexion == left)));
	}

	private void validateInputs(int left, int right) {
		// validates input parameters are valid
		checkArgument(elements.size() > left && left > 0, "Invalid left value.");
		checkArgument(elements.size() > right && right > 0, "Invalid right value.");
	}

}
