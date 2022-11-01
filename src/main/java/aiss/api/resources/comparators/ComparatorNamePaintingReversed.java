package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Painting;

public class ComparatorNamePaintingReversed implements Comparator<Painting>{

	@Override
	public int compare(Painting p1, Painting p2) {
		return p2.getName().compareTo(p1.getName());
	}

}