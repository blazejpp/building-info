package app.controller;

import app.httpUtils.HttpErrorResponse;
import app.model.Building;
import app.service.BuildingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static app.httpUtils.HttpResponseConstants.*;
import static app.httpUtils.HttpResponseConstants.INTERNAL_ERROR_MESSAGE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/building")
@Api(value = "Building controller", description = "Building controller")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @ApiOperation(value = "Returns building by id")
    @ApiResponses(value = {
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = UNAUTHORIZED_CODE, message = UNAUTHORIZED_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = RESOURCE_NOT_FOUND_CODE, message = RESOURCE_NOT_FOUND_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = INTERNAL_ERROR_CODE, message = INTERNAL_ERROR_MESSAGE, response = HttpErrorResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Building> getBuilding(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(buildingService.getRepository().findById(id), HttpStatus.OK);
    }

}