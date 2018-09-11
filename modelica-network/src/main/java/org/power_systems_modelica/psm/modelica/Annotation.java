package org.power_systems_modelica.psm.modelica;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Annotation implements Comparable<Annotation>
{
	public Annotation()
	{
		items = new TreeSet<>();
	}

	public Annotation(AnnotationItem a)
	{
		items = new TreeSet<>();
		items.add(a);
	}

	@Override
	public boolean equals(Object o)
	{
		return items.equals(o);
	}

	@Override
	public int hashCode()
	{
		return items.hashCode();
	}

	@Override
	public int compareTo(Annotation other)
	{
		return asText().compareTo(other.asText());
	}

	public static void annotate(Annotable a, AnnotationItem annotationItem)
	{
		if (a.getAnnotation() == null)
			a.setAnnotation(new Annotation(annotationItem));
		else
		{
			// Remove any previous annotation of the same item type
			// Throw an error if we are annotating with an item that would replace multiple items
			String itemType = annotationItem.getType();
			Set<AnnotationItem> items = a.getAnnotation().getItems().stream()
					.filter(ai -> ai.getType().equals(itemType))
					.collect(Collectors.toSet());
			if (items.size() > 1)
				throw new RuntimeException(
						"Replacing multiple previous annotations with " + annotationItem.asText());
			a.getAnnotation().removeItems(items);
			a.getAnnotation().addItem(annotationItem);
		}
	}

	public boolean isEmpty()
	{
		return items == null || items.isEmpty();
	}

	public Collection<AnnotationItem> getItems()
	{
		return Collections.unmodifiableCollection(items);
	}

	public void addItems(Collection<AnnotationItem> items)
	{
		this.items.addAll(items);
	}

	public void removeItems(Collection<AnnotationItem> items)
	{
		this.items.removeAll(items);
	}

	public void addItem(AnnotationItem a)
	{
		Objects.requireNonNull(a);
		items.add(a);
	}

	public String asText()
	{
		return items.stream().map(AnnotationItem::asText).collect(Collectors.joining(","));
	}

	public Optional<String> getId()
	{
		return items.stream()
				.filter(a -> a.isPsmRefAnnotation())
				.findFirst()
				.flatMap(a -> a.getId());
	}

	public Optional<String> getStaticId()
	{
		return items.stream()
				.filter(a -> a.isPsmRefAnnotation())
				.findFirst()
				.flatMap(a -> a.getStaticId());
	}

	public Optional<String> getStaticId(int side)
	{
		return items.stream()
				.filter(a -> a.isPsmRefAnnotation())
				.findFirst()
				.flatMap(a -> a.getStaticId(side));
	}

	private final Set<AnnotationItem> items;
}