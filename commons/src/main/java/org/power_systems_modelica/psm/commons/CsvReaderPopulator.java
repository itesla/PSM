package org.power_systems_modelica.psm.commons;

/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvListReader;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public interface CsvReaderPopulator<T>
{
	public void prepare(ICsvListReader listReader, Map<String, List<T>> values);
	public void populate(List<Object> columnValues, Map<String, List<T>> values);
}
