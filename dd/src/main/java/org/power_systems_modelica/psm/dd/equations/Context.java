package org.power_systems_modelica.psm.dd.equations;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class Context<T>
{
	public abstract String write(T object);

	public abstract Stream<T> getDomainStream();

	public Predicate<T> getPredicate(Selector selector)
	{
		if (selector instanceof LogicalSelector)
		{
			Context<T> ctx = this;
			Predicate<T> p = ((LogicalSelector) selector).getPredicate(ctx);
			return s -> p.test(s);
		}
		return null;
	}
}
