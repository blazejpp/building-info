package app.service;

import app.model.Room;
import app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class RoomService implements GenericService<Room> {

    @Autowired
    private RoomRepository roomRepository;

    public RoomRepository getRepository() {
        return roomRepository;
    }

    public BigDecimal calculateArea(Room room) {
        return room.getFloorArea();
    }

    public BigDecimal calculateVolume(Room room) {
        return calculateArea(room).multiply(room.getHeight());
    }

    public BigDecimal calculateLighting(Room r) {
        return r.getLightingPower();
    }

    public BigDecimal calculateLightingPerArea(Room room) {
        return calculateLighting(room).divide(calculateArea(room), MathContext.DECIMAL32);
    }

    public BigDecimal calculateHeating(Room room) {
        return room.getHeatingPower();
    }

    public BigDecimal calculateHeatingPerVolume(Room room) {
        return calculateHeating(room).divide(calculateVolume(room), MathContext.DECIMAL32);
    }
}
