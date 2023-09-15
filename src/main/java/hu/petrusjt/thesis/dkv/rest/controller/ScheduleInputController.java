package hu.petrusjt.thesis.dkv.rest.controller;

import hu.petrusjt.thesis.dkv.rest.dto.inbound.ScheduleInputDto;
import hu.petrusjt.thesis.dkv.rest.dto.inbound.StopInputDto;
import hu.petrusjt.thesis.dkv.rest.dto.outbound.ScheduleResponseDto;
import hu.petrusjt.thesis.dkv.toberenamed.route.model.RouteDirection;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.ScheduleService;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.ScheduleClassifier;
import hu.petrusjt.thesis.dkv.toberenamed.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/schedule-input")
public class ScheduleInputController {

    final StopService stopService;
    final ScheduleService scheduleService;

    @Autowired
    public ScheduleInputController(final StopService stopService, final ScheduleService scheduleService) {
        this.stopService = stopService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleInput(@RequestBody final List<ScheduleInputDto> scheduleInputDto) {
        scheduleService.createSchedules(scheduleInputDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Created");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopInput(@RequestBody final List<StopInputDto> stopInputDto) {
        stopService.createStops(stopInputDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Created");
    }

    @CrossOrigin
    @GetMapping("/{route}/{direction}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable("route") final String routeId,
            @PathVariable("direction") final RouteDirection direction,
            @RequestParam("classifier") final ScheduleClassifier classifier) {
        try {
            final var scheduleClassifier = classifier == null
                    ? ScheduleClassifier.getFromDate(LocalDate.now(ZoneId.of("Europe/Budapest")))
                    : classifier;
            return ResponseEntity.ok(scheduleService.getScheduleForRoute(routeId, direction, scheduleClassifier));
        } catch (final IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        } catch (final Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
