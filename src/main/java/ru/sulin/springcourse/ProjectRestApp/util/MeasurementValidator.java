package ru.sulin.springcourse.ProjectRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sulin.springcourse.ProjectRestApp.models.Measurement;
import ru.sulin.springcourse.ProjectRestApp.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {
    private SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(!sensorsService.getSensorByName(measurement.getSensor().getName()).isPresent()) {
            errors.rejectValue("sensor", "", "Sensor of this measurement doesn't exist.");
        }
    }
}
