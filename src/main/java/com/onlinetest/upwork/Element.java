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
	private Set<Integer> conections; 
}
