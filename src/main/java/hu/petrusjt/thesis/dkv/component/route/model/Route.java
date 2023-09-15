package hu.petrusjt.thesis.dkv.component.route.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "ROUTE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME", "DIRECTION"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ROUTE")
    @SequenceGenerator(name = "S_ROUTE", sequenceName = "S_ROUTE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DIRECTION")
    @Enumerated(EnumType.STRING)
    private RouteDirection direction;
}
