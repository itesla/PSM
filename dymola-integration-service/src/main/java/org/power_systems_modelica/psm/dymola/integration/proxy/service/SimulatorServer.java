/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.power_systems_modelica.psm.dymola.integration.proxy.service;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlMimeType;

/**
 *
 * @author Quinary <itesla@quinary.com>
 */
@WebService
@SOAPBinding(style = Style.RPC) // more on this later
public interface SimulatorServer
{
	@WebMethod
	@XmlMimeType("application/octet-stream")
	DataHandler validate(
			 String inputFileName,
			 String problem,
			 double startTime,
			 double stopTime,
			 int numberOfIntervals,
			 double outputInterval,
			 double tolerance,
			 String[] methodList,
			 String resultsFileName,
			 String resultsVars,
			 int depth,
			@XmlMimeType("application/octet-stream") DataHandler data);

	@WebMethod
	@XmlMimeType("application/octet-stream")
	DataHandler simulate(String inputFileName,
			String problem,
			double startTime,
			double stopTime,
			int numberOfIntervals,
			double outputInterval,
			double tolerance,
			String[] methodList,
			String resultsFileName,
			String resultsVars,
			boolean createFilteredMat,
			@XmlMimeType("application/octet-stream") DataHandler data);

	@WebMethod
	void close();
}