package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Annotation implements Comparable<Annotation>
{
	public Annotation()
	{
		items = new TreeSet<>();
	}

	public Annotation(String a)
	{
		items = new TreeSet<>();
		items.add(new AnnotationItem(a));
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

	public static void annotate(Annotable a, String annotationItem)
	{
		if (a.getAnnotation() == null)
			a.setAnnotation(new Annotation(annotationItem));
		else
		{
			// Only one annotation of each type is allowed,
			// remove any previous annotation of this item type
			String itemType = getItemType(annotationItem);
			Set<AnnotationItem> items = a.getAnnotation().getItems().stream()
					.filter(ai -> getItemType(ai.asText()).equals(itemType))
					.collect(Collectors.toSet());
			a.getAnnotation().removeItems(items);
			a.getAnnotation().addItem(annotationItem);
		}
	}

	public static String getItemType(String annotationItem)
	{
		return annotationItem.substring(0, annotationItem.indexOf("("));
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

	public void addItem(String a)
	{
		Objects.requireNonNull(a);
		items.add(new AnnotationItem(a));
	}
	
	public String asText()
	{
		return items.stream().map(AnnotationItem::asText).collect(Collectors.joining(","));
	}

	public Optional<String> getId()
	{
		return getAttributeValue(REF_ID);
	}

	public Optional<String> getStaticId()
	{
		return getAttributeValue(REF_STATIC_ID);
	}

	public Optional<String> getStaticId(int side)
	{
		return getAttributeValue(REF_STATIC_ID + side);
	}

	public static String writeIdStaticId(String id, String staticId)
	{
		return writeRefs(REF_ID, id, REF_STATIC_ID, staticId);
	}

	public static String writeStaticId12(String id1, String id2)
	{
		return writeRefs(REF_STATIC_ID + "1", id1, REF_STATIC_ID + "2", id2);
	}

	public static List<ModelicaInterconnection> readConnectors(List<Annotation> annotations)
	{
		return annotations.stream()
				.filter(a -> !a.isEmpty())
				.map(a -> a.asText())
				.filter(Objects::nonNull)
				.map(t -> readInterconnections(t))
				.filter(Objects::nonNull)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public static String writeConnectors(List<ModelicaInterconnection> connectors)
	{
		if (connectors == null || connectors.isEmpty()) return "";
		return connectors.stream()
				.map(c -> writeInterconnection(c))
				.collect(Collectors.joining(","));
	}

	private Optional<String> getAttributeValue(String attributeName)
	{
		// Expect only one item that is a REF annotation
		return items.stream()
				.map(AnnotationItem::asText)
				.filter(t -> t.startsWith(REF_KEYWORD))
				.findFirst()
				.map(t1 -> getAttributeValue(attributeName, t1));
	}

	private String getAttributeValue(String attributeName, String text)
	{
		Matcher m = REF_ANNOTATION.matcher(text);
		if (!m.find()) return null;

		MatchResult r = m.toMatchResult();
		text = r.group(1);

		String[] attributes = text.split(",");
		for (String a : attributes)
		{
			String[] nameValue = a.split("=");
			if (nameValue.length < 2) continue;
			String name = nameValue[0];
			String value = nameValue[1].replace("\"", "");
			if (name.equals(attributeName)) return value;
		}
		return null;
	}

	private static String writeRefs(String... refs)
	{
		if (refs == null) return null;
		if (refs.length == 0) return null;
		// Should receive pairs of (refName, refValue)
		if (refs.length % 2 == 1) return null;
		StringBuilder srefs = new StringBuilder();
		srefs.append(REF_KEYWORD);
		srefs.append("(");
		for (int k = 0; k <= refs.length - 2; k += 2)
		{
			srefs.append(writeAttributeValue(refs[k], refs[k + 1]));
			if (k < refs.length - 2) srefs.append(",");
		}
		srefs.append(")");
		return srefs.toString();
	}

	private static String writeInterconnection(ModelicaInterconnection c)
	{
		// TODO Code for writing and reading interconnections should be symmetric 
		String[] attrNames = {
				"name",
				"componentId",
				"componentVar",
				"targetModel",
				"targetName" };
		String[] attrValues = {
				c.getName(),
				c.getComponentId(),
				c.getComponentVar(),
				c.getTargetModel(),
				c.getTargetName()
		};
		List<String> as = new ArrayList<>();
		for (int k = 0; k < attrNames.length; k++)
			if (attrValues[k] != null)
				as.add(writeAttributeValue(attrNames[k], attrValues[k]));
		return INTERCONNECTION_KEYWORD
				.concat("(")
				.concat(String.join(",", as))
				.concat(")");
	}

	private static String writeAttributeValue(String attribute, String value)
	{
		return attribute.concat("=\"").concat(value).concat("\"");
	}

	private static List<ModelicaInterconnection> readInterconnections(String text)
	{
		List<ModelicaInterconnection> connectors = new ArrayList<>();
		Matcher m = INTERCONNECTION_ANNOTATION.matcher(text);
		while (m.find())
		{
			MatchResult r = m.toMatchResult();
			String sc = r.group(1);
			connectors.add(readInterconnection(sc));
		}
		return connectors;
	}

	private static ModelicaInterconnection readInterconnection(String text)
	{
		Map<String, String> attrs = new HashMap<>();
		String[] attributes = text.split(",");
		for (String a : attributes)
		{
			String[] keyvalue = a.split("=");
			if (keyvalue.length < 2) continue;
			String key = keyvalue[0];
			String value = keyvalue[1].replace("\"", "");
			attrs.put(key, value);
		}
		return ModelicaInterconnection.create(
				attrs.get("name"),
				attrs.get("componentId"),
				attrs.get("componentVar"),
				attrs.get("targetModel"),
				attrs.get("targetName"));
	}

	private final Set<AnnotationItem>	items;

	private static final String			REF_KEYWORD					= "PSMRef";
	private static final String			REF_ID						= "id";
	private static final String			REF_STATIC_ID				= "staticId";
	private static final Pattern		REF_ANNOTATION				= Pattern
			.compile(REF_KEYWORD + "\\(([^\\)]*)\\)");

	private static final String			INTERCONNECTION_KEYWORD		= "PSMInterCnx";
	private static final Pattern		INTERCONNECTION_ANNOTATION	= Pattern
			.compile(INTERCONNECTION_KEYWORD + "\\(([^\\)]*)\\)");
}