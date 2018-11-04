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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/volume")
public class VolumeController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "Returns total building volume by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}", method = GET)
    public ResponseEntity getBuildingArea(@PathVariable("id") Long id) {
        Building building = buildingService.getRepository().getById(id);
        return new ResponseEntity<>(buildingService.calculateVolume(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns total floor volume by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Floor number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/floor/{id}/{number}", method = GET)
    public ResponseEntity getFloorArea(@PathVariable("id") Long id,
                                       @PathVariable("number") Long number) {
        Floor floor = floorService.getRepository().findByBuildingIdAndNumber(id, number);
        return new ResponseEntity<>(floorService.calculateVolume(floor), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns total room volume by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Room number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/room/{id}/{number}", method = GET)
    public ResponseEntity getRoomArea(@PathVariable("id") Long id,
                                      @PathVariable("number") Long number) {
        Room room = roomService.getRepository().findByBuildingIdAndNumber(id, number);
        return new ResponseEntity<>(roomService.calculateVolume(room), HttpStatus.OK);
    }
}
