import static org.junit.Assert.assertTrue;

import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.DeviceComponent;
import org.hl7.fhir.dstu3.model.DeviceMetric;
import org.junit.BeforeClass;
import org.junit.Test;

public class FhirValidatorTests {

    private static FhirValidator validator;

    @BeforeClass
    public static void setUp() {
        validator = FhirDeviceProfileValidator.initFhirValidator(FhirContext.forDstu3());
    }

    @Test
    public void shouldValidateDeviceComponents() {
        List<DeviceComponent> filesToValidate = FhirXmlFileLoader.loadFromDirectory("device-components");
        System.out.println("Validating device-components");
        filesToValidate.forEach(f -> {
            ValidationResult vr = validator.validateWithResult(f);
            System.out.println("Validating file " + f.getIdentifier().getValue());
            System.out.println(vr.isSuccessful());
            assertTrue(vr.isSuccessful());
            vr.getMessages().forEach(m -> System.out.println(m));
        });
    }

    @Test
    public void shouldValidateDeviceMetrics() {
        List<DeviceMetric> metricsToValidate = FhirXmlFileLoader.loadFromDirectory("device-metrics");
        System.out.println("Validating device-metrics");
        metricsToValidate.forEach(f -> {
            ValidationResult vr = validator.validateWithResult(f);
            System.out.println("Validating file " + f.getIdentifier().getValue());
            System.out.println(vr.isSuccessful());
            assertTrue(vr.isSuccessful());
            vr.getMessages().forEach(m -> System.out.println(m));
        });
    }

    @Test
    public void shouldValidateDeviceBundle() {
        List<Bundle> bundlesToValidate = FhirXmlFileLoader.loadFromDirectory("bundles");
        System.out.println("Validating bundles");
        bundlesToValidate.forEach(f -> {
            ValidationResult vr = validator.validateWithResult(f);
            System.out.println(vr.isSuccessful());
            assertTrue(vr.isSuccessful());
            vr.getMessages().forEach(m -> System.out.println(m));
        });
    }
}
