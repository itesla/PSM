package org.power_systems_modelica.psm.modelica.builder;

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ReflectionUtils
{
	public static Object getPropertyValue(Object object, String property) throws Exception
	{
		String methodName = "get" + property;
		Method method;
		try
		{
			method = object.getClass().getMethod(methodName);
			// To allow access to public members of package protected implementation classes
			// Instead of setAccessible we could try to find the accessible method in one of the parent classes
			method.setAccessible(true);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			LOG.warn("no method to obtain property {} from object of class {}:",
					property,
					object.getClass().getName());
			LOG.warn(e.getMessage());
			throw e;
		}
		Object value;
		try
		{
			value = method.invoke(object);
			return value;
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			LOG.warn("problem obtaining value of property {} from object of class {}:",
					property,
					object.getClass().getName());
			LOG.warn(e.getMessage());
			throw e;
		}
	}

	private static final Logger	LOG	= LoggerFactory.getLogger(ReflectionUtils.class);
}
