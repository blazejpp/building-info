package app.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "address")
    public String address;

    @Column(name = "name")
    public String name;

    @OneToMany(mappedBy = "building")
    public List<Floor> floors;

}
