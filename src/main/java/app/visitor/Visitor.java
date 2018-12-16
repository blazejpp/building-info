package app.visitor;

import app.model.Building;
import app.model.Floor;
import app.model.Room;

import java.math.BigDecimal;

public interface Visitor {
    BigDecimal calculate(Building building);
    BigDecimal calculate(Floor floor);
    BigDecimal calculate(Room room);
}
