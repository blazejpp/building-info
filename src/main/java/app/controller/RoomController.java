package app.controller;

import app.model.Room;
import app.service.RoomService;
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
@RequestMapping("/rooms")
@Api(value = "Room controller", description = "Room controller")
public class RoomController extends Controller {

    @Autowired
    private RoomService roomService;

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns all rooms")
    @RequestMapping(method = GET)
    public ResponseEntity getAllRooms() throws IOException {
        return respond(roomService.getRepository().findAll(), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Returns room by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Room ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getRoom(@PathVariable("id") Long id) throws IOException {
        return respond(roomService.getRepository().getById(id), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Deletes room by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Room ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteRoom(@PathVariable("id") Long id) throws IOException {
        roomService.getRepository().deleteById(id);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Modifies room by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Room ID", dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity editRoom(@PathVariable("id") Long id,
                                       @RequestBody Room room) throws IOException {
        room.setId(id);
        roomService.getRepository().save(room);
        return respond(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "Adds room")
    @RequestMapping(method = POST)
    public ResponseEntity addRoom(@RequestBody Room room) throws IOException {
        roomService.getRepository().save(room);
        return respond(HttpStatus.OK);
    }

}