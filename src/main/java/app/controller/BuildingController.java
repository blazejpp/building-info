package app.controller;

import app.service.BuildingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/building")
@Api(value = "Building controller", description = "Building controller")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @ApiOperation(value = "Returns building by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getBuilding(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity.ok().body(buildingService.getRepository().getById(id));
    }

}