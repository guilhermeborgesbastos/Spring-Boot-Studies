
package com.tutorials.model;

import org.springframework.stereotype.Component;

/**
 * PYI: Placing both the interface and the implementation is common place, and doesn't seem to be a problem.
 * Take for example the Java API -- most classes have both interfaces and their implementations included in the same package.
 * 
 * Take for example the java.util package:
 * It contains the interfaces such as Set, Map, List, while also having the implementations such as HashSet, HashMap and ArrayList.
 * 
 */

@Component
public interface Computer {
	
	public String retrieveDescription();
	public String toString();
	
}
