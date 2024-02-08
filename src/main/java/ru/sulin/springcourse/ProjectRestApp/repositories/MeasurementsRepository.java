package ru.sulin.springcourse.ProjectRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sulin.springcourse.ProjectRestApp.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    long countMeasurementsByRaining(boolean value);
}
