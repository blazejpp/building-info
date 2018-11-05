package app.controller;

import app.model.Building;
import app.model.Floor;
import app.model.Room;
import app.service.BuildingService;
import app.service.FloorService;
import app.service.RoomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/heating")
public class HeatingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "Returns heating per volume in building by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}", method = GET)
    public ResponseEntity getBuildingHeating(@PathVariable("id") Long id) {
        Building building = buildingService.getRepository().getById(id);
        return new ResponseEntity<>(buildingService.calculateHeatingPerVolume(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns heating per volume on floor by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Floor number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/floor/{id}/{number}", method = GET)
    public ResponseEntity getFloorHeating(@PathVariable("id") Long id,
                                       @PathVariable("number") Long number) {
        Floor floor = floorService.getRepository().findByBuildingIdAndNumber(id, number);
        return new ResponseEntity<>(floorService.calculateHeatingPerVolume(floor), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns heating per volume in room by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Room number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/room/{id}/{number}", method = GET)
    public ResponseEntity getRoomHeating(@PathVariable("id") Long id,
                                      @PathVariable("number") Long number) {
        Room room = roomService.getRepository().findByBuildingIdAndNumber(id, number);
        return new ResponseEntity<>(roomService.calculateHeatingPerVolume(room), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns rooms exceeding given level of heating per volume by building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buildingId", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "heating", value = "Heating per volume", dataType = "double", paramType = "path")
    })
    @RequestMapping(value = "/excess/{buildingId}/{heating}", method = GET)
    public ResponseEntity getRoomsExceedingHeating(@PathVariable("buildingId") Long buildingId,
                                                  @PathVariable("heating") BigDecimal heating) {
        Building building = buildingService.getRepository().getById(buildingId);
        return new ResponseEntity<>(buildingService.getRoomsExceedingHeating(building, heating), HttpStatus.OK);
    }

}
