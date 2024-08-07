package com.test.hulkstore.controller;

import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.MovementVMList;
import com.test.hulkstore.model.Paginator;
import com.test.hulkstore.service.MovementService;
import com.test.hulkstore.service.TypeMovementService;
import com.test.hulkstore.service.component.MovementServiceFactory;
import com.test.hulkstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Utils.PATH_MOVEMENT)
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private MovementServiceFactory movementServiceFactory;

    @PostMapping("/")
    public ResponseEntity<String> addMovement(@RequestBody MovementVM movementVm) {
        TypeMovementService typeMovementService = movementServiceFactory.getService(movementVm.getMovement().getType());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(typeMovementService.addMovement(movementVm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementVM> getMovementById(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @GetMapping("/")
    public ResponseEntity<MovementVMList> getMovements(
            @RequestParam(Utils.PAGE) int page,
            @RequestParam(Utils.PER_PAGE) int perPage) {
        return ResponseEntity.ok(movementService.getMovements(
                new Paginator(page, perPage)));
    }
}
