package hu.petrusjt.thesis.dkv.toberenamed.schedule.repository;

import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByRouteId(final Long id);
}
