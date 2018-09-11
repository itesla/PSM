#
# Copyright (c) 2017 - 2018, RTE (http://www.rte-france.com)
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#
# @author Luma Zamarreño <zamarrenolm at aia.es>
#


# In init files, replaces equations 
#	sexs_<staticId>.id_VS=0
# with a declaration
#	Modelica.Blocks.Sources.Constant zero_<staticId> (k = 0);
# and an equation
#	connect(sexs_<staticId>.pin_VS, zero_<staticId>.y)
#
# Run it in a single line on an init folder using:
# find . -name "*mo" | while read mo; do awk -f ../../../../../scripts/fixinitvs.awk $mo > ${mo}.kk && mv ${mo}.kk $mo; done
#

/^model/ {
	modelId = $2;
	staticId = modelId;
	gsub("_Initialization", "", staticId);
}

/^[ \t]*equation[ \t]*$/ {
	# Insert a last declaration, just before the beginning of equations
	printf "  Modelica.Blocks.Sources.Constant zero_%s (k = 0) annotation (Placement(transformation()));\n", staticId;
}
	
/sexs_.*pin_VS=0;/ {
	$0 = sprintf("    connect(sexs_%s.pin_VS, zero_%s.y) annotation (Line());", staticId, staticId);
}

{
	print;
}
