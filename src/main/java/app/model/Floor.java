package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floor")
public class Floor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "number")
    public Long number;

    @JoinColumn(name = "building_id")
    @ManyToOne
    @JsonIgnore
    public Building building;

    @OneToMany(mappedBy = "floor")
    public List<Room> rooms;
}
