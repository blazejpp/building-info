package app.service;

import app.model.Room;
import app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomRepository getRepository() {
        return roomRepository;
    }

    public BigDecimal calculateArea(Room r) {
        return r.floorArea;
    }

    public BigDecimal calculateVolume(Room r) {
        return r.floorArea.multiply(r.height);
    }
}
