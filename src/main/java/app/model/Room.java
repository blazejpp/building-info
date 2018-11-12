package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Room to klasa opisująca strukturę pokoju.
 * <p>
 * Zawiera następujące pola:
 * <ul>
 * <li> id - indeks pozwalający na jednoznaczną identyfikację pokój generowany automatycznie
 * <li> number - numer pokoju
 * <li> floor - piętro, do którego pokój przynależy
 * <li> floorArea - powierzchnia pokoju 
 * <li> height - wysokość pokoju
 * <li> lightingPower - łączna moc oświetlenia 
 * <li> heatingPower - poziom zużycia energii ogrzewania 
 * </ul>
 * <p>
 * Klasa zawiera zdefiniowane mapowanie z bazą danych.
 * <p>
 * Zastosowanie adnotacji @Date pozwala na generowanie tzw. boilerplate automatycznie.
 */
@Entity
@Table(name = "room")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Long number;

    @JoinColumn(name = "floor_id")
    @ManyToOne
    @JsonIgnore
    private Floor floor;

    @Column(name = "floor_area")
    private BigDecimal floorArea;

    @Column(name = "height")
    private BigDecimal height;

    @Column(name = "lighting_power")
    private BigDecimal lightingPower;

    @Column(name = "heatingPower")
    private BigDecimal heatingPower;
}
