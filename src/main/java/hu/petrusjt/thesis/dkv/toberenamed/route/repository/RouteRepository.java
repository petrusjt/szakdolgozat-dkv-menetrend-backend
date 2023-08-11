package hu.petrusjt.thesis.dkv.toberenamed.route.repository;

import hu.petrusjt.thesis.dkv.toberenamed.route.model.Route;
import hu.petrusjt.thesis.dkv.toberenamed.route.model.RouteDirection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    Route findRouteByNameAndDirection(final String name, final RouteDirection direction);
}
