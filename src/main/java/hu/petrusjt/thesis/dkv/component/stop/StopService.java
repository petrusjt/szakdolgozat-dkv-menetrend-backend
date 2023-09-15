package hu.petrusjt.thesis.dkv.component.stop;

import hu.petrusjt.thesis.dkv.rest.dto.inbound.StopInputDto;
import hu.petrusjt.thesis.dkv.component.route.model.Route;
import hu.petrusjt.thesis.dkv.component.route.repository.RouteRepository;
import hu.petrusjt.thesis.dkv.component.stop.model.Stop;
import hu.petrusjt.thesis.dkv.component.stop.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public StopService(final StopRepository stopRepository, final RouteRepository routeRepository) {
        this.stopRepository = stopRepository;
        this.routeRepository = routeRepository;
    }

    public void createStops(final List<StopInputDto> stopInputDtos) {
        for (final var stopInputDto : stopInputDtos) {
            var route = routeRepository.findRouteByNameAndDirection(stopInputDto.routeName(),
                    stopInputDto.direction());
            if (route == null) {
                route = createRoute(stopInputDto);
            }

            final var stop = new Stop(null, route.getId(), stopInputDto.stopName(), stopInputDto.minutesFromStart());
            stopRepository.save(stop);
        }
    }

    private Route createRoute(final StopInputDto stopInputDto) {
        final var route = new Route(null, stopInputDto.routeName(), stopInputDto.direction());
        return routeRepository.save(route);
    }
}
