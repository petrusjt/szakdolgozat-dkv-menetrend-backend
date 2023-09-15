package hu.petrusjt.thesis.dkv.component.route.repository;

import hu.petrusjt.thesis.dkv.component.route.model.Route;
import hu.petrusjt.thesis.dkv.component.route.model.RouteDirection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    Route findRouteByNameAndDirection(final String name, final RouteDirection direction);
}
