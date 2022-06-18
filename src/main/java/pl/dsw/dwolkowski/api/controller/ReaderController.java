package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.dsw.dwolkowski.api.metadata.model.Reader;
import pl.dsw.dwolkowski.api.service.ReaderService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/library/v1/reader")
@Api(tags = "Reader Management")
@Tag(name = "Reader Management", description = "")
public class ReaderController {

    private final ReaderService readerService;

    // GET MAPPINGS
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns all readers from database." )
    public Collection<Reader> getAllReaders(){
        return readerService.getAllReaders();
    }

    @GetMapping(path = "/{reader_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns reader with given id." )
    @ApiResponse(responseCode = "404", description = "Reader Not Found")
    public Reader getReader(@PathVariable long reader_id){
        return readerService.getReader(reader_id);
    }

    // POST MAPPINGS
    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Adds reader given in JSON format to database." )
    public Reader createReader(@RequestBody Reader newReader){
        return readerService.createReader(newReader);
    }

    // DELETE MAPPINGS
    @DeleteMapping(path = "/{reader_id}")
    @Operation( summary = "Deletes reader from database." )
    @ApiResponse(responseCode = "404", description = "Reader Not Found")
    public void deleteReader(@PathVariable long reader_id){
        readerService.deleteReader(reader_id);
    }
}
