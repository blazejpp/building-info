package app.controller;

import app.model.Floor;
import app.service.FloorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/floors")
@Api(value = "Floor controller", description = "Floor controller")
public class FloorController extends Controller {

    @Autowired
    private FloorService floorService;

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns all floors")
    @RequestMapping(method = GET)
    public ResponseEntity getAllFloors() throws IOException {
        return respond(floorService.getRepository().findAll(), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns floor by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Floor ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getFloor(@PathVariable("id") Long id) throws IOException {
        return respond(floorService.getRepository().getById(id), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Deletes floor by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Floor ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteFloor(@PathVariable("id") Long id) throws IOException {
        floorService.getRepository().deleteById(id);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Modifies floor by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Floor ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity editFloor(@PathVariable("id") Long id,
                                       @RequestBody Floor floor) throws IOException {
        floor.setId(id);
        floorService.getRepository().save(floor);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Adds floor")
    @RequestMapping(method = POST)
    public ResponseEntity addFloor(@RequestBody Floor floor) throws IOException {
        floorService.getRepository().save(floor);
        return respond(HttpStatus.OK);
    }

}