package com.kodilla.library.controller;

import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.dto.BookInstanceDto;
import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.dto.TitleDto;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibraryController {
    private final LibraryService service;
    private final LibraryMapper mapper;

    @PostMapping("/readers")
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto) {
        service.saveReader(mapper.mapToReader(readerDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/readers") // DODANE
    public ResponseEntity<List<ReaderDto>> getReaders() {
        return ResponseEntity.ok(mapper.mapToReaderDtoList(service.getAllReaders()));
    }

    @PostMapping("/titles")
    public ResponseEntity<Void> addTitle(@RequestBody TitleDto titleDto) {
        service.saveTitle(mapper.mapToTitle(titleDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/titles") // DODANE
    public ResponseEntity<List<TitleDto>> getTitles() {
        return ResponseEntity.ok(mapper.mapToTitleDtoList(service.getAllTitles()));
    }

    @PostMapping("/instances")
    public ResponseEntity<Void> addInstance(@RequestBody BookInstanceDto dto) {
        service.saveInstance(mapper.mapToBookInstance(dto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/instances") // DODANE
    public ResponseEntity<List<BookInstanceDto>> getInstances() {
        return ResponseEntity.ok(mapper.mapToBookInstanceDtoList(service.getAllInstances()));
    }

    @PatchMapping("/instances/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam BookStatus status) {
        service.updateInstanceStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Long> getAvailableCount(@RequestParam String title) {
        return ResponseEntity.ok(service.getAvailableCount(title));
    }

    @PostMapping("/rent")
    public ResponseEntity<Void> rentBook(@RequestParam Long readerId, @RequestParam Long titleId) {
        service.rentBook(readerId, titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/return/{instanceId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long instanceId) {
        service.returnBook(instanceId);
        return ResponseEntity.ok().build();
    }
}
