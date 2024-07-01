package com.test.hulkstore.controller;

import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.MovementVMList;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movement")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping("/")
    public ResponseEntity<String> addMovement(@RequestBody MovementVM movementVm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movementService.addMovementVm(movementVm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementVM> getMovementById(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @GetMapping("/")
    public ResponseEntity<MovementVMList> getMovements(
            @RequestParam("page") int page,
            @RequestParam("per_page") int perPage) {
        return ResponseEntity.ok(movementService.getMovements(
                new Paginator(page, perPage)));
    }
}
