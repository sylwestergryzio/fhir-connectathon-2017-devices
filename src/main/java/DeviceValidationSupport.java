import java.util.HashMap;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;

import org.hl7.fhir.dstu3.hapi.validation.IValidationSupport;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.instance.model.api.IBaseResource;

public class DeviceValidationSupport implements IValidationSupport {

    private static String profileDir = "profiles";

    private final HashMap<String, StructureDefinition> definitionsMap;
    private final List<StructureDefinition> definitions;

    public DeviceValidationSupport() {
        definitions = FhirXmlFileLoader.loadFromDirectory(profileDir);
        definitionsMap = new HashMap<>();
        definitions.forEach(def -> definitionsMap.put(def.getUrl(), def));
    }

    @Override
    public ValueSet.ValueSetExpansionComponent expandValueSet(FhirContext theContext, ValueSet.ConceptSetComponent theInclude) {
        return null;
    }

    @Override
    public List<StructureDefinition> fetchAllStructureDefinitions(FhirContext theContext) {
        return definitions;
    }

    @Override
    public CodeSystem fetchCodeSystem(FhirContext theContext, String theSystem) {
        return null;
    }

    @Override
    public <T extends IBaseResource> T fetchResource(FhirContext theContext, Class<T> theClass, String theUri) {
        return (T) definitionsMap.get(theUri);
    }

    @Override
    public StructureDefinition fetchStructureDefinition(FhirContext theCtx, String theUrl) {
        return null;
    }

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        return false;
    }

    @Override
    public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay) {
        return null;
    }
}
