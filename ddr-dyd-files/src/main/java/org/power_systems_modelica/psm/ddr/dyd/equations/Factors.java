package org.power_systems_modelica.psm.ddr.dyd.equations;

import java.util.List;

public interface Factors
{
	<T> List<Expression> getFrom(Context<T> context);
}