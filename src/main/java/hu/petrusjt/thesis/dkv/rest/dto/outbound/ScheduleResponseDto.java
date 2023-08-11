package hu.petrusjt.thesis.dkv.rest.dto.outbound;

import java.util.List;

public record ScheduleResponseDto(RouteDto route, List<StopDto> stops, List<StartTimesDto> startTimes,
                                  StopDto startsFrom) {
}
