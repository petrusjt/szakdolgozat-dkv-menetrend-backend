package hu.petrusjt.thesis.dkv.toberenamed.stop.repository;

import hu.petrusjt.thesis.dkv.toberenamed.stop.model.Stop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends CrudRepository<Stop, Long> {

    List<Stop> findAllByRouteId(final Long id);
}
