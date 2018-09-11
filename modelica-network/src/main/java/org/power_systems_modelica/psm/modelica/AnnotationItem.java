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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class AnnotationItem implements Comparable<AnnotationItem>
{
	public AnnotationItem(String text)
	{
		Objects.requireNonNull(text);
		this.text = text;
	}

	public String asText()
	{
		return text;
	}

	public String getType()
	{
		return text.substring(0, text.indexOf("("));
	}

	public boolean isPsmAnnotation()
	{
		return text.startsWith(PSM);
	}

	public boolean isPsmRefAnnotation()
	{
		// An interconnection contains also all the attributes of a PSMRef, is a PSMRef
		// An interconnection only has one PSMRef
		// (it is the definition of one of the points of connection accepted by a PSMRef)
		return text.startsWith(PSM_REF) || isPsmInterconnectionAnnotation();
	}

	public boolean isPsmInterconnectionAnnotation()
	{
		return text.startsWith(PSM_INTERCONNECTION);
	}

	public Optional<String> getId()
	{
		return Optional.ofNullable(getAttributeValue(PSM_REF_ID));
	}

	public Optional<String> getStaticId()
	{
		return Optional.ofNullable(getAttributeValue(PSM_REF_STATIC_ID));
	}

	public Optional<String> getStaticId(int side)
	{
		return Optional.ofNullable(getAttributeValue(PSM_REF_STATIC_ID + side));
	}

	@Override
	public int compareTo(AnnotationItem other)
	{
		return text.compareTo(other.text);
	}

	public static AnnotationItem annotatePsmRef(String id, String staticId)
	{
		return create(PSM_REF, PSM_REF_ID, id, PSM_REF_STATIC_ID, staticId);
	}

	public static AnnotationItem annotatePsmRefStaticId12(String id1, String id2)
	{
		return create(PSM_REF, PSM_REF_STATIC_ID + "1", id1, PSM_REF_STATIC_ID + "2", id2);
	}

	public static AnnotationItem annotatePsmInterconnection(
			ModelicaInterconnection c,
			AnnotationItem ref)
	{
		// An interconnection includes the attributes of a PSMRef
		// This is required to be able to store all interconnections of a system in a single annotation,
		// Each annotation must contain its own reference

		String id = ref.getAttributeValue(PSM_REF_ID);
		String staticId = ref.getAttributeValue(PSM_REF_STATIC_ID);

		return create(PSM_INTERCONNECTION,
				PSM_REF_ID, id,
				PSM_REF_STATIC_ID, staticId,
				"name", c.getName(),
				"componentId", c.getComponentId(),
				"componentVar", c.getComponentVar(),
				"targetModel", c.getTargetModel(),
				"targetName", c.getTargetName(),
				"targetModel2", c.getTargetModel2(),
				"targetName2", c.getTargetName2());
	}

	public ModelicaInterconnection createInterconnection()
	{
		Map<String, String> attrs = getAttributes();
		return new ModelicaInterconnection(
				attrs.get("name"),
				attrs.get("componentId"),
				attrs.get("componentVar"),
				attrs.get("targetModel"),
				attrs.get("targetName"),
				attrs.get("targetModel2"),
				attrs.get("targetName2"));
	}

	private static AnnotationItem create(String annotationType, String... attrs)
	{
		if (attrs == null) return null;
		if (attrs.length == 0) return null;
		// Should receive pairs of (attrName, attrValue)
		if (attrs.length % 2 == 1) return null;
		StringBuilder srefs = new StringBuilder();
		srefs.append(annotationType);
		srefs.append("(");
		for (int k = 0; k <= attrs.length - 2; k += 2)
		{
			String name = attrs[k];
			String value = attrs[k + 1];
			srefs.append(attributeNameValue(name, value));
			if (k < attrs.length - 2) srefs.append(",");
		}
		srefs.append(")");
		return new AnnotationItem(srefs.toString());
	}

	private static String attributeNameValue(String attribute, String value)
	{
		Objects.requireNonNull(attribute);
		String svalue = value == null ? "" : value;
		return attribute.concat("=\"").concat(svalue).concat("\"");
	}

	private String getAttributeValue(String attributeName)
	{
		Matcher m = ANNOTATION_ATTRS.matcher(text);
		if (!m.find()) return null;

		MatchResult r = m.toMatchResult();
		String attrs = r.group(1);

		String[] attributes = attrs.split(",");
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

	private Map<String, String> getAttributes()
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
		return attrs;
	}

	private final String			text;

	private static final String		PSM					= "PSM";
	private static final String		PSM_REF				= "PSMRef";
	private static final String		PSM_INTERCONNECTION	= "PSMInterCnx";

	private static final String		PSM_REF_ID			= "id";
	private static final String		PSM_REF_STATIC_ID	= "staticId";

	private static final Pattern	ANNOTATION_ATTRS	= Pattern
			.compile("\\(([^\\)]*)\\)");
}
