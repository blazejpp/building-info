package app.service;

import app.model.Room;
import app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * RoomService jest komponentem aplikacji, który wykonuje operacje wyliczające własności pokoju.
 */

@Service
public class RoomService implements GenericService<Room> {

    @Autowired
    private RoomRepository roomRepository;

    public RoomRepository getRepository() {
        return roomRepository;
    }
    
    /**
     * Metoda zwracająca powierzchnię pokoju.
     * @param room      pokój, którego łączna powierzchnia zostanie zwrócona
     * @return          powierzchnia danego pokoju
    */
    public BigDecimal calculateArea(Room room) {
        return room.getFloorArea();
    }
    
    /**
     * Metoda zwracająca kubaturę pomieszczenia.
     * @param room      pokój, którego łączna kubatura nas interesuje
     * @return          powierzchnia danego pokoju
    */
    public BigDecimal calculateVolume(Room room) {
        return calculateArea(room).multiply(room.getHeight());
    }

    /**
     * Metoda zwracająca łączną moc oświetlenia
     * @param room      pokój, którego moc oświetlenia zostanie zwrócona
     * @return          moc oświetlenia danego pokoju
    */
    public BigDecimal calculateLighting(Room room) {
        return room.getLightingPower();
    }

    /**
     * Metoda zwracająca moc oświetlenia w przeliczeniu na m^2
     * @param room      pokój, którego moc oświetlenia w przeliczeniu na m^2 zostanie policzona
     * @return          moc oświetlenia w przeliczeniu na m^2 danego pokoju
    */
    public BigDecimal calculateLightingPerArea(Room room) {
        return calculateLighting(room).divide(calculateArea(room), MathContext.DECIMAL32);
    }

    /**
     * Metoda zwracająca łączny poziom zużycia energii ogrzewania.
     * @param room      pokój, którego poziom zużycia energii ogrzewania zostanie zwrócony
     * @return          poziom zużycia energii ogrzewania danego pokoju
    */
    public BigDecimal calculateHeating(Room room) {
        return room.getHeatingPower();
    }

    /**
     * Metoda zwracająca poziom zużycia energii ogrzewania w przeliczeniu na m^2
     * @param room      pokój, którego poziom zużycia energii ogrzewania w przeliczeniu na m^2 zostanie policzona
     * @return          poziom zużycia energii ogrzewania w przeliczeniu na m^2 danego pokoju
    */
    public BigDecimal calculateHeatingPerVolume(Room room) {
        return calculateHeating(room).divide(calculateVolume(room), MathContext.DECIMAL32);
    }
}
