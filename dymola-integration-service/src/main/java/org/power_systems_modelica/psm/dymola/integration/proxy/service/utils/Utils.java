package org.power_systems_modelica.psm.dymola.integration.proxy.service.utils;

/*
 * #%L
 * Dymola proxy service
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class Utils {
 
    /**
     * Transpose a 2D array
     * @return
     */
    public static UnaryOperator<double[][]> transpose() {
        return m -> {
            return IntStream.range(0, m[0].length).mapToObj(r ->
            IntStream.range(0, m.length).mapToDouble(c -> m[c][r]).toArray()
            ).toArray(double[][]::new);
        };
    }
}
