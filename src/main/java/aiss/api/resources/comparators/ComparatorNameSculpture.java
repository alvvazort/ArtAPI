package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Sculpture;

public class ComparatorNameSculpture implements Comparator<Sculpture>{

	@Override
	public int compare(Sculpture s1, Sculpture s2) {
		return s1.getName().compareTo(s2.getName());
	}

}