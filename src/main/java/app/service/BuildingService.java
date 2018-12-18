package app.service;

import app.dto.RoomHeatingDto;
import app.model.Building;
import app.model.Room;
import app.repository.BuildingRepository;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BuildingService jest komponentem aplikacji, który wykonuje operacje wyliczające własności budynku.
 */

@Service
public class BuildingService implements GenericService<Building> {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    public BuildingRepository getRepository() {
        return buildingRepository;
    }
    
    /**
     * Metoda zwracająca łączną powierzchnię zadanego budynku.
     * @param building  budynek, którego łączna powierzchnia zostanie policzona
     * @return          łączna powierzchnia w danym budynku
    */
    public BigDecimal calculateArea(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateArea(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca łączną kubaturę budynku danego na wejściu.
     * @param building  budynek, którego łączna kubatura zostanie policzona
     * @return          łączna kubatura w danym budynku
    */
    public BigDecimal calculateVolume(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateVolume(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca łączną moc oświetlenia w budynku podanym jako argument.
     * @param building  budynek, którego moc oświetlenia ma zostać policzona
     * @return          wartość mocy oświetlenia w danym budynku
    */
    public BigDecimal calculateLighting(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateLighting(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca moc oświetlenia w przeliczeniu na m^2
     * @param building  budynek, którego moc oświetlenia ma zostać policzona
     * @return          wartość mocy oświetlenia w przeliczeniu na m^2
    */
    public BigDecimal calculateLightingPerArea(Building building) {
        return calculateLighting(building).divide(calculateArea(building), MathContext.DECIMAL32);
    }

    /**
     * Metoda zwracająca łączny poziom zużycia energii ogrzewania
     * @param building  budynek, którego wartość zużycia energii zostanie policzona
     * @return          poziom zużycia energii ogrzewania w danym budynku
    */
    public BigDecimal calculateHeating(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateHeating(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Metoda zwracająca poziom zużycia energii ogrzewania w przeliczeniu na m^2
     * @param building  budynek, którego wartość zużycia energii zostanie policzona
     * @return          poziom zużycia energii ogrzewania w danym budynku w przeliczeniu na m^2 
    */
    public BigDecimal calculateHeatingPerVolume(Building building) {
        return calculateHeating(building).divide(calculateVolume(building), MathContext.DECIMAL32);
    }

    /**
     * Metoda zwracająca pomieszczenia w danym budynku, których zużycie energii przekracza zadany poziom.
     * @param building  budynek, w którym poszukujemy pomieszczeń przekraczających zadany poziom zużycia energii ogrzewania
     * @param heating   maksymalny poziom zużycia energii ogrzewania 
     * @return          lista pomieszczeń, które przekraczają zadany poziom zużycia energii ogrzewania
    */
    
    public List<RoomHeatingDto> getRoomsExceedingHeating(Building building, BigDecimal heating) {
        return building.getFloors().stream()
                .map(f -> f.getRooms()).flatMap(List::stream)
                .map(r -> RoomHeatingDto.builder()
                        .id(r.getId())
                        .number(r.getNumber())
                        .floorNumber(r.getFloor().getNumber())
                        .heatingPerArea(roomService.calculateHeatingPerVolume(r))
                        .build())
                .filter(r -> r.getHeatingPerArea().compareTo(heating) > 0)
                .collect(Collectors.toList());
    }
}
