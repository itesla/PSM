BEGIN {
	createFile = 0;
	outputFilename = "";
}

/^model/ {
	if (outputFilename != "") 
	{
		r = close(outputFileName);
	}
	outputFilename = $2 ".single_model.mo";
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