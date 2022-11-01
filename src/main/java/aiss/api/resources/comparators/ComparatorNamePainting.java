package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Painting;

public class ComparatorNamePainting implements Comparator<Painting>{

	@Override
	public int compare(Painting p1, Painting p2) {
		return p1.getName().compareTo(p2.getName());
	}

}