package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floor")
@Data
public class Floor {

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
