package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Annotation
{
	// TODO Use the list of items instead of building and parsing whole text in first item

	public Annotation(String a)
	{
		items = new ArrayList<>();
		items.add(new AnnotationItem(a));
	}

	public boolean isEmpty()
	{
		return items == null || items.isEmpty();
	}

	public Collection<AnnotationItem> getItems()
	{
		return Collections.unmodifiableList(items);
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

	public String getStaticId()
	{
		return getAttributeValue(REF_STATIC_ID);
	}

	public String getStaticId(int side)
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

	public static List<ModelicaConnector> readConnectors(List<Annotation> annotations)
	{
		return annotations.stream()
				.filter(a -> !a.isEmpty())
				.map(a -> a.asText())
				.filter(Objects::nonNull)
				.map(t -> readConnectors(t))
				.filter(Objects::nonNull)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public static String writeConnectors(List<ModelicaConnector> connectors)
	{
		if (connectors == null || connectors.isEmpty()) return "";
		return connectors.stream()
				.map(c -> writeConnector(c))
				.collect(Collectors.joining(","));
	}

	private String getAttributeValue(String attributeName)
	{
		// Expect only one item that is a REF annotation
		return items.stream()
				.map(AnnotationItem::asText)
				.filter(t -> t.startsWith(REF_KEYWORD))
				.findFirst()
				.map(t1 -> getAttributeValue(attributeName, t1))
				.orElse(null);
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

	private static String writeConnector(ModelicaConnector c)
	{
		Optional<String> sid, spin, starget;

		sid = c.getId().map(id -> writeAttributeValue(CONNECTOR_ATTR_ID, id));
		spin = Optional.of(writeAttributeValue(CONNECTOR_ATTR_PIN, c.getPin()));
		starget = c.getTarget().map(t -> writeAttributeValue(CONNECTOR_ATTR_TARGET, t));

		String attrs = Arrays.asList(sid, spin, starget).stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining(","));

		return CONNECTOR_KEYWORD
				.concat("(")
				.concat(attrs)
				.concat(")");
	}

	private static String writeAttributeValue(String attribute, String value)
	{
		return attribute.concat("=\"").concat(value).concat("\"");
	}

	private static List<ModelicaConnector> readConnectors(String text)
	{
		List<ModelicaConnector> connectors = new ArrayList<>();
		Matcher m = CONNECTOR_ANNOTATION.matcher(text);
		while (m.find())
		{
			MatchResult r = m.toMatchResult();
			String sc = r.group(1);
			connectors.add(readConnector(sc));
		}
		return connectors;
	}

	private static ModelicaConnector readConnector(String text)
	{
		// Format expected is: id=<identifier>,pin=<pin name>,target=<dataSource>:<item>:<pin>
		// Values may be quoted
		// Target is not parsed
		// Attributes may be given in any order
		// id and target are optional

		String id = null, pin = null, target = null;
		String[] attributes = text.split(",");
		for (String a : attributes)
		{
			String[] keyvalue = a.split("=");
			if (keyvalue.length < 2) continue;
			String key = keyvalue[0];
			String value = keyvalue[1].replace("\"", "");
			switch (key)
			{
			case CONNECTOR_ATTR_ID:
				id = value;
				break;
			case CONNECTOR_ATTR_PIN:
				pin = value;
				break;
			case CONNECTOR_ATTR_TARGET:
				target = value;
				break;
			}
		}
		if (id == null && target == null) return new ModelicaConnector(pin);
		else return new ModelicaConnector(id, pin, target);
	}

	private final List<AnnotationItem>	items;

	private static final String			REF_KEYWORD				= "PSMRef";
	private static final String			REF_ID					= "id";
	private static final String			REF_STATIC_ID			= "staticId";
	private static final Pattern		REF_ANNOTATION			= Pattern
			.compile(REF_KEYWORD + "\\(([^\\)]*)\\)");

	private static final String			CONNECTOR_KEYWORD		= "PSMConnector";
	private static final String			CONNECTOR_ATTR_ID		= "id";
	private static final String			CONNECTOR_ATTR_PIN		= "pin";
	private static final String			CONNECTOR_ATTR_TARGET	= "target";
	private static final Pattern		CONNECTOR_ANNOTATION	= Pattern
			.compile(CONNECTOR_KEYWORD + "\\(([^\\)]*)\\)");
}