package app.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

/**
 * Building to klasa opisująca strukturę budynku.
 * <p>
 * Zawiera następujące pola:
 * <ul>
 * <li> id - indeks pozwalający na jednoznaczną identyfikację budynku
 * <li> address - adres budynku
 * <li> name - nazwę budynku
 * <li> floors - lista przechowująca piętra należące do danego budynku
 * </ul>
 * <p>
 * Klasa zawiera zdefiniowane mapowanie z bazą danych.
 * <p>
 * Zastosowanie adnotacji @Data pozwala na generowanie tzw. boilerplate automatycznie.
 */
@Entity
@Table(name = "building")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "building")
    private List<Floor> floors;

}
