package hu.petrusjt.thesis.dkv.rest.dto.outbound;

import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.ScheduleClassifier;

import java.util.List;

public record ScheduleResponseDto(RouteDto route, List<StopDto> stops, List<StartTimesDto> startTimes,
                                  StopDto startsFrom, ScheduleClassifier classifier) {
}
