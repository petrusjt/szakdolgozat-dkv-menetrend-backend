package hu.petrusjt.thesis.dkv.rest.dto.inbound;

import hu.petrusjt.thesis.dkv.toberenamed.route.model.RouteDirection;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.ScheduleClassifier;

import java.util.List;

public record ScheduleInputDto(String routeName, RouteDirection direction, Long hour, List<Long> minutes,
                               ScheduleClassifier scheduleClassifier) {
}
