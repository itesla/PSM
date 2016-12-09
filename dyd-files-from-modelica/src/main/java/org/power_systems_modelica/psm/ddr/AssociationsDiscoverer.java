package org.power_systems_modelica.psm.ddr;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.dyd.Association;
import org.power_systems_modelica.psm.ddr.dyd.AssociationProvider;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public class AssociationsDiscoverer
{
	AssociationsDiscoverer(DynamicDataRepositoryDydFiles ddr, String containerName)
	{
		this.ddr = ddr;
		this.containerName = containerName;
	}

	Association findCreateAssociation(ModelicaModel m, Stage stage)
	{
		String staticId = m.getStaticId();
		Optional<Association> oa = associations.findAssociation(staticId);
		Association a;

		// If it has already been classified in an association,
		if (oa.isPresent())
		{
			a = oa.get();
			// ensure that the model signature is coherent with the association signature for the stage
			checkSameSignatures(
					staticId, getModelSignature(m),
					getAssociationSignature(a, stage),
					stage);
		}
		else
		{
			// Either add this staticId to one of the current associations
			// or create a new one for it
			a = getCreateAssociation(stage, getModelSignature(m));
			includeInAssociation(a, staticId);
		}
		return a;
	}

	void checkSameSignatures(
			String staticId, String modelSignature,
			String associationSignature,
			Stage stage)
	{
		// OK if the association does not yet contain a signature for this stage
		if (associationSignature == null) return;

		// FIXME we are reversing the condition to ensure it happens and test it
		if (modelSignature.equals(associationSignature))
		{
			String msg = String.format("Bad association classification\n" +
					"    staticId       = %s\n" +
					"    modelSignature = %s\n",
					"    assocSignature = %s\n",
					"    stage          = %s\n",
					staticId,
					modelSignature,
					associationSignature,
					stage.name());
			throw new RuntimeException(msg);
		}
	}

	String getAssociationSignature(Association a, Stage stage)
	{
		Map<Association, String> byAssoc = signaturesByStageByAssociation.get(stage);
		if (byAssoc == null) return null;
		return byAssoc.get(a);
	}

	Association getCreateAssociation(Stage stage, String modelSignature)
	{
		Map<String, Association> bySignature = associationsByStageBySignature.get(stage);
		if (bySignature == null) return createAssociation(stage, modelSignature);
		Association a = bySignature.get(modelSignature);
		if (a == null) return createAssociation(stage, modelSignature);
		return a;
	}

	Association createAssociation(Stage stage, String modelSignature)
	{
		Association a = new Association("association" + (associationCount++));

		Map<String, Association> bySignature = associationsByStageBySignature.get(stage);
		if (bySignature == null)
		{
			bySignature = new HashMap<>();
			associationsByStageBySignature.put(stage, bySignature);
		}
		bySignature.put(modelSignature, a);
		Map<Association, String> byAssoc = signaturesByStageByAssociation.get(stage);
		if (byAssoc == null)
		{
			byAssoc = new HashMap<>();
			signaturesByStageByAssociation.put(stage, byAssoc);
		}
		byAssoc.put(a, modelSignature);
		associations.add(a);

		ddr.addAssociation(containerName, a);

		return a;
	}

	void includeInAssociation(Association a, String staticId)
	{
		if (a.getPattern() == null || a.getPattern().isEmpty())
			a.setPattern(staticId);
		else
			a.setPattern(a.getPattern().concat("|").concat(staticId));
	}

	private static String getModelSignature(ModelicaModel m)
	{
		return m.getDeclarations().stream()
				.map(ModelicaDeclaration::getType)
				.sorted()
				.collect(Collectors.joining(","));
	}

	private final DynamicDataRepositoryDydFiles		ddr;
	private final String							containerName;
	private int										associationCount				= 0;
	private Map<Stage, Map<String, Association>>	associationsByStageBySignature	= new HashMap<>();
	private Map<Stage, Map<Association, String>>	signaturesByStageByAssociation	= new HashMap<>();
	private AssociationProvider						associations					= new AssociationProvider();
}
