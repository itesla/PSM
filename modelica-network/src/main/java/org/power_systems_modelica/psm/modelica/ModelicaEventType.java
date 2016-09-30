package org.power_systems_modelica.psm.modelica;

// TODO This is no a Modelica object, or Modelica-related data,
// but it related to the "world" of dynamic modelling a power system network
// but putting it here allows us to reference it from DDR without having to create another library
public enum ModelicaEventType
{
	LINE_FAULT, BUS_FAULT, LINE_OPEN_RECEIVER_SIDE, LINE_OPEN_BOTH_SIDES, BANK_MODIFICATION, LOAD_VARIATION, BREAKER
}
