package ru.sulin.springcourse.ProjectRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sulin.springcourse.ProjectRestApp.models.Sensor;
import ru.sulin.springcourse.ProjectRestApp.services.SensorsService;

@Component
public class SensorValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorsService.getSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with this name already exists.");
        }
    }
}
