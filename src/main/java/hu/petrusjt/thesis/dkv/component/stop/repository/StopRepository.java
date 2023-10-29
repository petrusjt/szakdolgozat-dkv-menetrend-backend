package hu.petrusjt.thesis.dkv.component.stop.repository;

import hu.petrusjt.thesis.dkv.component.route.model.Route;
import hu.petrusjt.thesis.dkv.component.stop.model.Stop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends CrudRepository<Stop, Long> {

    List<Stop> findAllByRoute(final Route route);
}
