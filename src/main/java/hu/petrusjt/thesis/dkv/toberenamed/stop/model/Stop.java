package hu.petrusjt.thesis.dkv.toberenamed.stop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STOP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "S_STOP", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "S_STOP", sequenceName = "S_STOP")
    private Long id;

    @Column(name = "ID_ROUTE")
    private Long routeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MINUTES_FROM_START")
    private Long minutesFromStart;
}
