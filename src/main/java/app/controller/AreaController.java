package app.controller;

import app.model.Building;
import app.model.Floor;
import app.model.Room;
import app.service.BuildingService;
import app.service.FloorService;
import app.service.RoomService;
import app.visitor.AreaVisitor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/area")
public class AreaController extends Controller {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AreaVisitor areaVisitor;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ApiOperation(value = "Returns total building area by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}", method = GET)
    public ResponseEntity getBuildingArea(@PathVariable("id") Long id) throws IOException {
        Building building = buildingService.getRepository().getById(id);
        return respond(areaVisitor.calculate(building), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ApiOperation(value = "Returns total floor area by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Floor number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}/floor/{number}", method = GET)
    public ResponseEntity getFloorArea(@PathVariable("id") Long id,
                                                   @PathVariable("number") Long number) throws IOException {
        Floor floor = floorService.getRepository().findByBuildingIdAndNumber(id, number);
        return respond(areaVisitor.calculate(floor), HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ApiOperation(value = "Returns total room area by number and building id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Room number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}/room/{number}", method = GET)
    public ResponseEntity getRoomArea(@PathVariable("id") Long id,
                                                   @PathVariable("number") Long number) throws IOException {
        Room room = roomService.getRepository().findByBuildingIdAndNumber(id, number);
        return respond(areaVisitor.calculate(room), HttpStatus.OK);
    }
}
