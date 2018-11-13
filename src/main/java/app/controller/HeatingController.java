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

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/buildings")
public class HeatingController extends Controller {

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

    @RequestMapping(value = "/{id}/heating", method = GET)
    public ResponseEntity getBuildingHeating(@PathVariable("id") Long id) throws IOException {
        Building building = buildingService.getRepository().getById(id);
        return respond(buildingService.calculateHeatingPerVolume(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns heating per volume on floor by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Floor number", dataType = "long", paramType = "path")
    })

    @RequestMapping(value = "/{id}/floors/{number}/heating", method = GET)
    public ResponseEntity getFloorHeating(@PathVariable("id") Long id,
                                       @PathVariable("number") Long number) throws IOException {
        Floor floor = floorService.getRepository().findByBuildingIdAndNumber(id, number);
        return respond(floorService.calculateHeatingPerVolume(floor), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns heating per volume in room by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Room number", dataType = "long", paramType = "path")
    })

    @RequestMapping(value = "/{id}/rooms/{number}/heating", method = GET)
    public ResponseEntity getRoomHeating(@PathVariable("id") Long id,
                                      @PathVariable("number") Long number) throws IOException {
        Room room = roomService.getRepository().findByBuildingIdAndNumber(id, number);
        return respond(roomService.calculateHeatingPerVolume(room), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns rooms exceeding given level of heating per volume by building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "heating", value = "Heating per volume", dataType = "double", paramType = "path")
    })
    @RequestMapping(value = "/{id}/heating/excess/{heating}", method = GET)
    public ResponseEntity getRoomsExceedingHeating(@PathVariable("id") Long id,
                                                  @PathVariable("heating") BigDecimal heating) throws IOException {
        Building building = buildingService.getRepository().getById(id);
        return respond(buildingService.getRoomsExceedingHeating(building, heating), HttpStatus.OK);
    }
    
}
