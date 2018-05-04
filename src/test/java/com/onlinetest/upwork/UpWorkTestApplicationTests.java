package com.onlinetest.upwork;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UpWorkTestApplicationTests {

	private static String INVALID_LEFT_VALUE_EXCEPTION = "Invalid left value.";
	private static String INVALID_RIGHT_VALUE_EXCEPTION = "Invalid right value.";
	private static String ALREADY_CONNECTED_EXCEPTION = "Already Connected.";

	@Test
	public void creationTest() {
		Network network = new Network(5);
		assertThat(network.getElements().size() == 5);

		network.getElements().stream().forEach(element -> {
			assertTrue(element != null);
			assertTrue(element.getConections() != null);
			assertTrue(element.getConections().isEmpty());
			assertTrue(element.getValue() > 0);
		});
	}

	@Test
	public void queryNotDirectlyConectedTest() {
		Network network = new Network(6);
		boolean areConected = network.query(1, 3);
		assertFalse(areConected);
	}

	@Test
	public void queryDirectlyConnectedTest() {
		Network network = new Network(6);
		network.connect(1, 3);

		boolean areConected = network.query(1, 3);
		assertTrue(areConected);

		areConected = network.query(3, 1);
		assertTrue(areConected);
	}
	
	@Test
	public void queryIndirectlyConnectedTest() {
		Network network = new Network(6);
		network.connect(1, 3);
		network.connect(3, 6);
		
		boolean areConected = network.query(6, 1);
		assertTrue(areConected);

		areConected = network.query(1, 6);
		assertTrue(areConected);
	}
	
	@Test
	public void queryNotIndirectlyConnectedTest() {
		Network network = new Network(6);
		network.connect(1, 3);
		network.connect(3, 6);
		
		boolean areConected = network.query(6, 2);
		assertFalse(areConected);

		areConected = network.query(2, 6);
		assertFalse(areConected);
	}

	@Test
	public void connectAlreadyConnectedExceptionTest() {
		Network network = new Network(6);
		network.connect(1, 3);

		try {
			network.connect(1, 3);
		} catch (IllegalArgumentException e) {
			assertTrue(ALREADY_CONNECTED_EXCEPTION.equals(e.getMessage()));
		}

	}

	@Test
	public void queryRightElementDoesntExistTest() {
		Network network = new Network(5);
		try {
			network.query(3, 6);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_RIGHT_VALUE_EXCEPTION.equals(e.getMessage()));
		}

	}

	@Test
	public void queryLeftElementDoesntExistTest() {
		Network network = new Network(5);
		try {
			network.query(6, 3);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_LEFT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void queryRightValueLessThanOneTest() {
		Network network = new Network(5);
		try {
			network.query(3, 0);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_RIGHT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void queryLefttValueLessThanOneTest() {
		Network network = new Network(5);
		try {
			network.query(-1, 3);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_LEFT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void connectRightElementDoesntExistTest() {
		Network network = new Network(5);
		try {
			network.connect(3, 6);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_RIGHT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void connectLeftElementDoesntExistTest() {
		Network network = new Network(5);
		try {
			network.connect(6, 3);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_LEFT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void connectRightValueLessThanOneTest() {
		Network network = new Network(5);
		try {
			network.connect(3, 0);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_RIGHT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

	@Test
	public void connectLefttValueLessThanOneTest() {
		Network network = new Network(5);
		try {
			network.connect(-2, 4);
		} catch (IllegalArgumentException e) {
			assertTrue(INVALID_LEFT_VALUE_EXCEPTION.equals(e.getMessage()));
		}
	}

}
