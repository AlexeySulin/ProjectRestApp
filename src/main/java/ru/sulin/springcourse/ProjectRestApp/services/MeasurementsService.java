package ru.sulin.springcourse.ProjectRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sulin.springcourse.ProjectRestApp.models.Measurement;
import ru.sulin.springcourse.ProjectRestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public void enrichMeasurement (Measurement measurement) {
        measurement.setSensor(sensorsService.getSensorByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public long findRainyDaysCount() {
        return measurementsRepository.countMeasurementsByRaining(true);
    }
}
