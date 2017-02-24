/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.power_systems_modelica.psm.dymola.integration.service;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SimulatorServer", targetNamespace = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SimulatorServer {

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @param arg6
	 * @param arg7
	 * @param arg8
	 * @param arg9
	 * @param arg10
	 * @param arg11
	 * @return
	 */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/simulateRequest", output = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/simulateResponse")
    public DataHandler simulate(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        double arg3,
        @WebParam(name = "arg4", partName = "arg4")
        double arg4,
        @WebParam(name = "arg5", partName = "arg5")
        int arg5,
        @WebParam(name = "arg6", partName = "arg6")
        double arg6,
        @WebParam(name = "arg7", partName = "arg7")
        double arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String[] arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9,
        @WebParam(name = "arg10", partName = "arg10")
        String arg10,
        @WebParam(name = "arg11", partName = "arg11")
        boolean arg11,
        @WebParam(name = "arg12", partName = "arg12")
        DataHandler arg12)
    	;

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/checkRequest", output = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/checkResponse")
    public DataHandler check(
          @WebParam(name = "arg0", partName = "arg0")
          String arg0,
          @WebParam(name = "arg1", partName = "arg1")
          String arg1,
          @WebParam(name = "arg2", partName = "arg2")
          String arg2,
          @WebParam(name = "arg3", partName = "arg3")
          String arg3,
          @WebParam(name = "arg4", partName = "arg4")
          DataHandler arg4)
    	;
    
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/prepareDynamicEnvironmentRequest", output = "http://service.proxy.integration.dymola.psm.power_systems_modelica.org/SimulatorServer/prepareDynamicEnvironmentResponse")
    public DataHandler prepareDynamicEnvironment(
          @WebParam(name = "arg0", partName = "arg0")
          String arg0,
          @WebParam(name = "arg1", partName = "arg1")
          String arg1,
          @WebParam(name = "arg2", partName = "arg2")
          DataHandler arg2)
    	;
    
}
