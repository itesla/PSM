#
# Copyright (c) 2017 - 2018, RTE (http://www.rte-france.com)
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#
# @author Luma Zamarreño <zamarrenolm at aia.es>
#


BEGIN {
	createFile = 0;
	outputFilename = "";
}

/^model/ {
	if (outputFilename != "") 
	{
		r = close(outputFileName);
	}
	outputFilename = $2 ".mo";
	createFile = 1;
}

{
	if (outputFilename != "")
	{
		if (createFile) 
		{
			print "within ;" > (outputFilename);
			createFile = 0;
		}
		print >> (outputFilename);
	}
}
