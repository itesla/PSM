package org.power_systems_modelica.psm.dymola.integration.proxy.service.utils;

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

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
