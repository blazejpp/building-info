package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Floor to klasa opisująca strukturę piętra.
 * <p>
 * Zawiera następujące pola:
 * <ul>
 * <li> id - indeks pozwalający na jednoznaczną identyfikację piętra generowany automatycznie
 * <li> number - numer piętra
 * <li> building - budynek, do którego piętro przynależy
 * <li> rooms - lista przechowująca pokoje znajdujące się na danym piętrze
 * </ul>
 * <p>
 * Klasa zawiera zdefiniowane mapowanie z bazą danych.
 * <p>
 * Zastosowanie adnotacji @Data pozwala na generowanie tzw. boilerplate automatycznie.
 */
@Entity
@Table(name = "floor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Floor extends Model {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Long number;

    @JoinColumn(name = "building_id")
    @ManyToOne
    @JsonIgnore
    private Building building;

    @OneToMany(mappedBy = "floor")
    private List<Room> rooms;
}
