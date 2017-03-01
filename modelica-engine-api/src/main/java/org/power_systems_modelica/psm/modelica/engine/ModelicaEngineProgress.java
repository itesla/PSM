package org.power_systems_modelica.psm.modelica.engine;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelicaEngineProgress extends Observable
{

	public void updateProgress(String info)
	{
		setChanged();
		notifyObservers(info);
	}

	private static final Logger	LOG	= LoggerFactory.getLogger(ModelicaEngineProgress.class);
}
