package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "number")
    public Long number;

    @JoinColumn(name = "floor_id")
    @ManyToOne
    @JsonIgnore
    public Floor floor;

    @Column(name = "width")
    public BigDecimal width;

    @Column(name = "height")
    public BigDecimal height;

    @Column(name = "depth")
    public BigDecimal depth;
}
