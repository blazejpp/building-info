package app.controller;

import app.model.Building;
import app.service.BuildingService;
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
@RequestMapping("/buildings")
@Api(value = "Building controller", description = "Building controller")
public class BuildingController extends Controller {

    @Autowired
    private BuildingService buildingService;

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns all buildings")
    @RequestMapping(method = GET)
    public ResponseEntity getAllBuildings() throws IOException {
        return respond(buildingService.getRepository().findAll(), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns building by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getBuilding(@PathVariable("id") Long id) throws IOException {
        return respond(buildingService.getRepository().getById(id), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Deletes building by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteBuilding(@PathVariable("id") Long id) throws IOException {
        buildingService.getRepository().deleteById(id);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Modifies building by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity editBuilding(@PathVariable("id") Long id,
                                       @RequestBody Building building) throws IOException {
        building.setId(id);
        buildingService.getRepository().save(building);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Adds building")
    @RequestMapping(method = POST)
    public ResponseEntity addBuilding(@RequestBody Building building) throws IOException {
        buildingService.getRepository().save(building);
        return respond(HttpStatus.OK);
    }

}