package hu.petrusjt.thesis.dkv.rest.dto.outbound;

import hu.petrusjt.thesis.dkv.component.stop.model.Stop;

public record StopDto(String name, Long timeFromStart, Long stopIndex) {

    public static StopDto of(final Stop stop) {
        return new StopDto(stop.getName(), stop.getMinutesFromStart(), stop.getStopIndex());
    }
}
