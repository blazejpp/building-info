package app.service;

import app.model.Floor;
import app.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * FloorService jest komponentem aplikacji, który wykonuje operacje wyliczające własności piętra.
 */

@Service
public class FloorService implements GenericService<Floor> {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomService roomService;

    public FloorRepository getRepository() {
        return floorRepository;
    }

    /**
     * Metoda zwracająca łączną powierzchnię danego piętra.
     * @param floor     piętro, którego łączna powierzchnia zostanie policzona
     * @return          łączna powierzchnia na danym piętrze
    */
    public BigDecimal calculateArea(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateArea(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca łączną kubaturę piętra podanego na wejściu.
     * @param floor     piętro, którego łączna kubatura zostanie policzona
     * @return          łączna kubatura na danym piętrze
    */
    public BigDecimal calculateVolume(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateVolume(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca łączną moc oświetlenia.
     * @param floor     piętro, którego moc oświetlenia ma zostać policzona
     * @return          wartość mocy oświetlenia na danym piętrze
    */
    public BigDecimal calculateLighting(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateLighting(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca moc oświetlenia na m^2.
     * @param floor     piętro, którego moc oświetlenia ma zostać policzona
     * @return          wartość mocy oświetlenia w przeliczeniu na m^2 na danym piętrze
    */
    public BigDecimal calculateLightingPerArea(Floor floor) {
        return calculateLighting(floor).divide(calculateArea(floor), MathContext.DECIMAL32);
    }

    /**
     * Metoda zwracająca łączny poziom zużycia energii ogrzewania.
     * @param floor     piętro, którego wartość zużycia energii zostanie policzona
     * @return          poziom zużycia energii ogrzewania na danym piętrze
    */
    public BigDecimal calculateHeating(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateHeating(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca poziom zużycia energii ogrzewania na m^2.
     * @param floor     piętro, którego wartość zużycia energii zostanie policzona
     * @return          poziom zużycia energii ogrzewania na danym piętrze w przeliczeniu na m^2 
    */
    public BigDecimal calculateHeatingPerVolume(Floor floor) {
        return calculateHeating(floor).divide(calculateVolume(floor), MathContext.DECIMAL32);
    }
}
