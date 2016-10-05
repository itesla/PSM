package org.power_systems_modelica.psm.modelica;

import java.util.regex.Pattern;

public class ModelicaUtil
{
	public static String idvar2ref(String id, String var)
	{
		String s = String.join(ID_VAR_SEPARATOR, id, var);
		return s;
	}

	public static String[] ref2idvar(String ref)
	{
		String[] parts = ref.split(QUOTED_ID_VAR_SEPARATOR);
		return parts;
	}

	public static String dynamicIdFromStaticId(String id)
	{
		return normalizedDynamicIdentifier("DM".concat(id));
	}

	public static String dynamicDeclarationIdFromModelForType(String type, String name, String id)
	{
		// This is to ease comparison with previous Modelica transformations
		type = ModelicaTricks.legacyType(type);
		return normalizedDynamicIdentifier(type.concat("_").concat(id));
	}

	public static String dynamicDeclarationIdFromModelForEvent(String event, String name, String id)
	{
		return normalizedDynamicIdentifier(event.concat("_").concat(id));
	}

	private static String normalizedDynamicIdentifier(String id)
	{
		return id.replace("-", "_");
	}

	private static final String	ID_VAR_SEPARATOR		= ".";
	private static final String	QUOTED_ID_VAR_SEPARATOR	= Pattern.quote(ID_VAR_SEPARATOR);
}
