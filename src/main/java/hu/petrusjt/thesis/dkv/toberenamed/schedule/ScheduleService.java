package hu.petrusjt.thesis.dkv.toberenamed.schedule;

import hu.petrusjt.thesis.dkv.rest.dto.inbound.ScheduleInputDto;
import hu.petrusjt.thesis.dkv.rest.dto.outbound.RouteDto;
import hu.petrusjt.thesis.dkv.rest.dto.outbound.ScheduleResponseDto;
import hu.petrusjt.thesis.dkv.rest.dto.outbound.StartTimesDto;
import hu.petrusjt.thesis.dkv.rest.dto.outbound.StopDto;
import hu.petrusjt.thesis.dkv.toberenamed.route.model.Route;
import hu.petrusjt.thesis.dkv.toberenamed.route.model.RouteDirection;
import hu.petrusjt.thesis.dkv.toberenamed.route.repository.RouteRepository;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.Schedule;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.model.ScheduleClassifier;
import hu.petrusjt.thesis.dkv.toberenamed.schedule.repository.ScheduleRepository;
import hu.petrusjt.thesis.dkv.toberenamed.stop.repository.StopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleService {

    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(final StopRepository stopRepository,
                           final RouteRepository routeRepository,
                           final ScheduleRepository scheduleRepository) {
        this.stopRepository = stopRepository;
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public void createSchedules(final List<ScheduleInputDto> scheduleInputDtos) {
        for (final var scheduleInputDto : scheduleInputDtos) {
            var route = routeRepository.findRouteByNameAndDirection(
                    scheduleInputDto.routeName(), scheduleInputDto.direction());
            if (route == null) {
                route = createRoute(scheduleInputDto);
            }

            final var routeId = route.getId();
            final var startHour = scheduleInputDto.hour();
            final var classifier = scheduleInputDto.scheduleClassifier();
            final List<Schedule> scheduleList = new ArrayList<>();
            for (final var startMinute : scheduleInputDto.minutes().stream().distinct().toList()) {
                scheduleList.add(new Schedule(null, routeId, startHour, startMinute, classifier));
            }
            scheduleRepository.saveAll(scheduleList);
        }
    }

    private Route createRoute(final ScheduleInputDto scheduleInputDto) {
        final var route = new Route(null, scheduleInputDto.routeName(), scheduleInputDto.direction());
        return routeRepository.save(route);
    }

    public ScheduleResponseDto getScheduleForRoute(final String routeId, final RouteDirection direction) {
        final var route = routeRepository.findRouteByNameAndDirection(routeId, direction);
        if (route == null) {
            throw new IllegalArgumentException();
        }

        final var stops = stopRepository.findAllByRouteId(route.getId()).stream()
                .map(StopDto::of)
                .sorted(Comparator.comparing(StopDto::timeFromStart))
                .toList();

        final var startTimes = mapScheduleListToStartTimeList(
                scheduleRepository.findAllByRouteId(route.getId()).stream()
                        // TODO erre ne legyen szükség
                        .filter(schedule -> schedule.getScheduleClassifier() == ScheduleClassifier.TANSZUNET_MUNKANAP)
                        .toList());
        final var startsFrom = stops.get(0);

        return new ScheduleResponseDto(RouteDto.of(route), stops, startTimes, startsFrom);
    }

    private List<StartTimesDto> mapScheduleListToStartTimeList(final List<Schedule> scheduleList) {
        final var hours = scheduleList.stream()
                .map(Schedule::getStartHour)
                .distinct()
                .sorted()
                .toList();
        final List<StartTimesDto> startTimesDtoList = new ArrayList<>();
        for (final var hour : hours) {
            final var schedulesByHour = scheduleList.stream()
                    .filter(schedule -> schedule.getStartHour().equals(hour))
                    .toList();
            final var minutes = schedulesByHour.stream()
                    .map(Schedule::getStartMinute)
                    .filter(Objects::nonNull)
                    .sorted()
                    .toList();
            startTimesDtoList.add(StartTimesDto.of(hour, minutes));
        }
        return startTimesDtoList;
    }
}
