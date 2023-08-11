package hu.petrusjt.thesis.dkv.rest.dto.outbound;


import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public record StartTimesDto(Long hour, List<Long> minutes) {

    public static StartTimesDto of(final Long hour, final List<Long> minutes) {
        return new StartTimesDto(hour, CollectionUtils.isNotEmpty(minutes) ? minutes : List.of());
    }
}
