# In init files, replaces equations 
#	sexs_<staticId>.id_VS=0
# with a declaration
#	Modelica.Blocks.Sources.Constant zero_<staticId> (k = 0);
# and an equation
#	connect(sexs_<staticId>.pin_VS, zero_<staticId>.y)

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