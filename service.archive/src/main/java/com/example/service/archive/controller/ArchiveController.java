package com.example.service.archive.controller;


import com.example.service.archive.entity.Archive;
import com.example.service.archive.service.ArchiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/archive")
public class ArchiveController {
    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping("/{archiveId}")
    public ResponseEntity<Archive> getArchive(@PathVariable long archiveId) {
        return ResponseEntity.status(HttpStatus.OK).body(archiveService.get(archiveId));
    }

    @PostMapping
    public ResponseEntity<Long> postArchive(@RequestBody Archive archive) {
        return ResponseEntity.status(HttpStatus.OK).body(archiveService.post(archive));
    }

    @PutMapping("/{archiveId}")
    public ResponseEntity<Archive> putMarkAndDate(@PathVariable long archiveId,
                                                  @RequestParam("mark") int mark,
                                                  @RequestParam("date") String date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(archiveService.putMarkAndDate(archiveId, mark, Timestamp.valueOf(date)));
    }

    @DeleteMapping("/{archiveId}")
    public ResponseEntity<String> deleteArchive(@PathVariable long archiveId) {
        archiveService.delete(archiveId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
}
