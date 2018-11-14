package app.repository;

import app.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    Floor findByBuildingIdAndNumber(Long id, Long number);

    Floor getById(Long id);
}
