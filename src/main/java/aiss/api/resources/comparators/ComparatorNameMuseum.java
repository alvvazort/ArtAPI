package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Museum;

public class ComparatorNameMuseum implements Comparator<Museum>{

	@Override
	public int compare(Museum m1, Museum m2) {
		return m1.getName().compareTo(m2.getName());
	}

}