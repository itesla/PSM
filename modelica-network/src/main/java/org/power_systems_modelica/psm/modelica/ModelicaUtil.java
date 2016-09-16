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
		return "DM" + id;
	}
	
	public static String dynamicDeclarationIdFromStaticId(String type, String name, String id)
	{
		//return name.substring(name.lastIndexOf('.') + 1).concat(id);
		// FIXME only to ease the comparison with existing mo's
		type = ModelicaTricks.legacyType(type);
		return type.concat("_").concat(id);
	}

	private static final String	ID_VAR_SEPARATOR		= ".";
	private static final String	QUOTED_ID_VAR_SEPARATOR	= Pattern.quote(ID_VAR_SEPARATOR);
}
