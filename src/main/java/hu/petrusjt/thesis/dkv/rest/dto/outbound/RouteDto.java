package hu.petrusjt.thesis.dkv.rest.dto.outbound;

import hu.petrusjt.thesis.dkv.toberenamed.route.model.Route;

public record RouteDto(String identifier) {

    public static RouteDto of(final Route route) {
        return new RouteDto(route.getName());
    }
}
