package ru.sulin.springcourse.ProjectRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sulin.springcourse.ProjectRestApp.models.Sensor;
import ru.sulin.springcourse.ProjectRestApp.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorsRepository.findSensorByName(name);
    }
}
