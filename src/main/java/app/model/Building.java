package app.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
@Data
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
