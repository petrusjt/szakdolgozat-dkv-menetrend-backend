package hu.petrusjt.thesis.dkv.component.schedule.model;

import hu.petrusjt.thesis.dkv.component.route.model.Route;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SCHEDULE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "S_SCHEDULE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "S_SCHEDULE", sequenceName = "S_SCHEDULE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ROUTE")
    private Route route;

    @Column(name = "START_HOUR")
    private Long startHour;

    @Column(name = "START_MINUTE")
    private Long startMinute;

    @Column(name = "CLASSIFIER")
    @Enumerated(EnumType.STRING)
    private ScheduleClassifier scheduleClassifier;
}
