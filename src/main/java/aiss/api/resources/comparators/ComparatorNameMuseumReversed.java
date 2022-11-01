package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Museum;

public class ComparatorNameMuseumReversed implements Comparator<Museum>{

	@Override
	public int compare(Museum m1, Museum m2) {
		return m2.getName().compareTo(m1.getName());
	}

}