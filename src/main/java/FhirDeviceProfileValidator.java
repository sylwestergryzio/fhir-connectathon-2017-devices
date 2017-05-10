import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;

import org.hl7.fhir.dstu3.hapi.validation.DefaultProfileValidationSupport;
import org.hl7.fhir.dstu3.hapi.validation.FhirInstanceValidator;
import org.hl7.fhir.dstu3.hapi.validation.IValidationSupport;
import org.hl7.fhir.dstu3.hapi.validation.ValidationSupportChain;

public class FhirDeviceProfileValidator {

    public static FhirValidator initFhirValidator(FhirContext fhirContext) {
        FhirValidator validator = fhirContext.newValidator();

        FhirInstanceValidator instanceValidator = new FhirInstanceValidator();
        IValidationSupport valSupport = new DeviceValidationSupport();
        ValidationSupportChain support = new ValidationSupportChain(new DefaultProfileValidationSupport(), valSupport);
        instanceValidator.setValidationSupport(support);

        validator.registerValidatorModule(instanceValidator);
        return validator;
    }
}
