package hu.petrusjt.thesis.dkv.rest.dto.inbound;

import hu.petrusjt.thesis.dkv.component.route.model.RouteDirection;
import hu.petrusjt.thesis.dkv.component.schedule.model.ScheduleClassifier;

import java.util.List;

public record ScheduleInputDto(String routeName, RouteDirection direction, Long hour, List<Long> minutes,
                               ScheduleClassifier scheduleClassifier) {
}
