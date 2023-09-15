package hu.petrusjt.thesis.dkv.rest.dto.inbound;

import hu.petrusjt.thesis.dkv.component.route.model.RouteDirection;

public record StopInputDto(String routeName, RouteDirection direction, String stopName, Long minutesFromStart) {
}
