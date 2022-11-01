package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Sculpture;

public class ComparatorNameSculptureReversed implements Comparator<Sculpture>{

	@Override
	public int compare(Sculpture s1, Sculpture s2) {
		return s2.getName().compareTo(s1.getName());
	}

}