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
	print "OUTPUT0 h sim_component,sim_var,init_component,init_var";
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
	
	# The real output we are interested in (global mapping between init output and sim input)
	# we obtain the end results filtering output of this script by OUTPUT0 and then applying sort --unique
	print "OUTPUT0 l " sim_component "," sim_var1 "," init_component "," init_var;

	# Only to check that refs between different configurations are coherent
	# Output all refs of every single generator to a different file, then compare files ...
	detailed_output = false;
	if (detailed_output) {
		output_file = init_staticId ".refs.csv";
		print sim_component "," sim_var1 "," init_component "," init_var >> output_file;
		close(output_file);
	}
}

