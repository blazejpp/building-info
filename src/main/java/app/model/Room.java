package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
