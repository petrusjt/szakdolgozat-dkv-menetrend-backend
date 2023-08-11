package hu.petrusjt.thesis.dkv.rest.dto.inbound;

import hu.petrusjt.thesis.dkv.toberenamed.route.model.RouteDirection;

public record StopInputDto(String routeName, RouteDirection direction, String stopName, Long minutesFromStart) {
}
