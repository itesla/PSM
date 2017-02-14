#
# We are interested in obtaining a global mapping between init outputs and sim inputs
#
# This script builds the file refs.csv used by the dyd-files-from-modelica module
# Run it from the command line:
#
#        awk -f prepare_refs_from_map_inits.awk map_init_results_to_sim_input.tsv | grep OUTPUT2 | sed 's/OUTPUT2.//' | sort | sed 's/head //' | sed 's/row  //' > refs.csv
#
# We start from mappings obtained from debug output taken from old modelica-export process
# For every new case, we run it through the old itesla modelica-export process and 
# get the stdout lines starting with "LUMA", 
# then add these lines to the input file of this script, map_init_results_to_sim_input.tsv
#
# We check that refs between different configurations are coherent:
# If we find that a variable of a simulation component is initialized 
# from different initialization components/vars we stop with error
#
# This error means that we can not build a mapping table from init results 
# to sim inputs that is global for all configurations
#
# Filtered outputs OUTPUT0 and OUTPUT2 should be equal
# If one is interested in the mappings identified for every machine, OUTPUT1 could be used

BEGIN {
	getline sinit_vars < "map_init_vars_to_sim_vars.csv";
	getline ssim_vars < "map_init_vars_to_sim_vars.csv";
	gsub("\"", "", sinit_vars);
	gsub("\"", "", ssim_vars);
	print "DEBUG init_vars";
	print "DEBUG " sinit_vars;
	print "DEBUG sim_vars";
	print "DEBUG " ssim_vars;
	n1 = split(sinit_vars, init_vars, ", ");
	n2 = split(ssim_vars, sim_vars, ", ");
	if (n1 != n2) {
		print "ERROR num init vars != num sim vars: " n1 " != " n2;
		exit;
	}
	for (k = 0; k < n1; k++) {
		map_init_vars[init_vars[k]] = sim_vars[k];
	}
	print "DEBUG map_init_vars"
	for (x in map_init_vars) {
		print "DEBUG     [" x "] --> [" map_init_vars[x] "]";
	}
	print "OUTPUT0 sim_component,sim_var,init_component,init_var";
}

!/keysource/ {
	keysource = $1;
	value = $2;
	keytarget = $3;
	print "DEBUG " keysource " : " keytarget " = " value;
	
	p1 = index(keysource, "__");
	p2 = index(keysource, ".");
	init_component = substr(keysource, 1, p1 - 1);
	init_staticId = substr(keysource, p1 + 1, p2 - p1 - 1);
	init_var = substr(keysource, p2 + 1);
	# Save the machine component in generator model
	if (index(init_component, "gen_") == 1) {
		machine_component[init_staticId] = init_component
		print "DEBUG machine " init_staticId " = " machine_component[init_staticId];
	}
	
	p1 = index(keytarget, "__");
	if (p1 > 0) {
		sim_component = substr(keytarget, 1, p1 - 1);
		p2 = index(keytarget, init_staticId);
		if (p2 <= 0) {
			print "ERROR different staticId " $0;
			exit;
		}
		sim_staticId = init_staticId;
		sim_var = substr(keytarget, p2 + length(sim_staticId) + 1);
	} else {
		p1 = index(keytarget, "_");
		sim_component = substr(keytarget, 1, p1 - 1);
		sim_staticId = "";
		sim_var = substr(keytarget, p1 + 1);
	}
	if (sim_component == "gen") {
		print "DEBUG look for machine of " init_staticId ", result = " machine_component[init_staticId];
		sim_component = machine_component[init_staticId];
	}

	sim_var1 = sim_var
	if (sim_var in map_init_vars) {
		sim_var1 = map_init_vars[sim_var]
	}
	
	print "DEBUG    init component = " init_component;
	print "DEBUG    init staticId  = " init_staticId;
	print "DEBUG    init var       = " init_var;
	print "DEBUG    sim component  = " sim_component;
	print "DEBUG    sim staticId   = " sim_staticId;
	print "DEBUG    sim var        = " sim_var;
	print "DEBUG    sim var 1      = " sim_var1;
	
	print "OUTPUT1 " init_staticId "," sim_component "," sim_var1 "," init_component "," init_var;
	
	print "OUTPUT0 " sim_component "," sim_var1 "," init_component "," init_var;
	
	# Check if the mapping is generic or there are ambiguities
	keysim = sim_component "::" sim_var1;
	keyinit = init_component "::" init_var;
	if (keysim in mappings) {
		if (mappings[keysim] != keyinit) {
			print "ERROR The table would contain two different sources for the same simulation var"
			print "ERROR    simulation component = " sim_components
			print "ERROR    simulation variable  = " sim_var1
			print "ERROR    initialization from1 = " mappings[keysim]
			print "ERROR    initialization from2 = " keyinit
			exit 1
		}
	}
	mappings[keysim] = keyinit;
}

END {
	print "OUTPUT2 head sim_component,sim_var,init_component,init_var";
	for (keysim in mappings) {
		split(keysim, parts, "::")
		sim_component = parts[1]
		sim_var1 = parts[2]
		keyinit = mappings[keysim]
		split(keyinit, parts, "::")
		init_component = parts[1]
		init_var = parts[2]
		print "OUTPUT2 row  " sim_component "," sim_var1 "," init_component "," init_var;
	}
}
