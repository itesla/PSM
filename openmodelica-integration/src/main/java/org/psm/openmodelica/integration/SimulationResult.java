package org.psm.openmodelica.integration;

public class SimulationResult {

	public SimulationResult(String simulationResult, String simError) {
		if(simulationResult != null) {
			resultFile = simulationResult.split(",\n")[0].trim();
			resultFile = resultFile.substring(resultFile.indexOf("\"")+1, resultFile.length()-1);
			
			simulationOptions = simulationResult.split(",\n")[1].trim();
			simulationOptions = simulationOptions.substring(simulationOptions.indexOf("\"")+1, simulationOptions.length()-1);
			
			messages = simulationResult.split(",\n")[2].trim();
			messages = messages.substring(messages.indexOf("\"")+1, messages.length()-2);
		}
		
		error = simError;
		error = error.substring(error.indexOf("\""), error.length()-1);
	}
	
	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getSimulationOptions() {
		return simulationOptions;
	}
	public void setSimulationOptions(String simulationOptions) {
		this.simulationOptions = simulationOptions;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}

	private String resultFile;
	private String simulationOptions;
	private String messages;
	private String error;
	
}
