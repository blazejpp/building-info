package app.controller;

import app.httpUtils.HttpErrorResponse;
import app.model.Building;
import app.model.Floor;
import app.service.BuildingService;
import app.service.FloorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

import static app.httpUtils.HttpResponseConstants.*;
import static app.httpUtils.HttpResponseConstants.INTERNAL_ERROR_MESSAGE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @ApiOperation(value = "Returns total building area by id")
    @ApiResponses(value = {
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = UNAUTHORIZED_CODE, message = UNAUTHORIZED_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = RESOURCE_NOT_FOUND_CODE, message = RESOURCE_NOT_FOUND_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = INTERNAL_ERROR_CODE, message = INTERNAL_ERROR_MESSAGE, response = HttpErrorResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/building/{id}", method = GET)
    public ResponseEntity<BigDecimal> getBuildingArea(@PathVariable("id") Long id) {
        Building building = buildingService.getRepository().findById(id);
        return new ResponseEntity<>(buildingService.calculateArea(building), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns total floor area by number and building id")
    @ApiResponses(value = {
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = UNAUTHORIZED_CODE, message = UNAUTHORIZED_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = RESOURCE_NOT_FOUND_CODE, message = RESOURCE_NOT_FOUND_MESSAGE, response = HttpErrorResponse.class),
            @ApiResponse(code = INTERNAL_ERROR_CODE, message = INTERNAL_ERROR_MESSAGE, response = HttpErrorResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Building ID", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "Floor number", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/floor/{id}/{number}", method = GET)
    public ResponseEntity<BigDecimal> getFloorArea(@PathVariable("id") Long id,
                                                   @PathVariable("number") Long number) {
        Floor floor = floorService.getRepository().findByBuildingIdAndNumber(id, number);
        return new ResponseEntity<>(floorService.calculateArea(floor), HttpStatus.OK);
    }
}
