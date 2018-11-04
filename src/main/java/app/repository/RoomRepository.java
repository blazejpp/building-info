package app.repository;

import app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.floor.building.id = :id and r.number = :number")
    Room findByBuildingIdAndNumber(@Param("id") Long id, @Param("number") Long number);
}
