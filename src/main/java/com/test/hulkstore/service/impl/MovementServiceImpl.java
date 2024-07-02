package com.test.hulkstore.service.impl;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.*;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {
    @Autowired
    private MovementDetailRepository movementDetailRepository;

    @Autowired
    private MovementRepository movementRepository;


    @Override
    public MovementVM getMovementById(Long id) {
        Movement movement = movementRepository.getMovementById(id);
        if(movement == null) {
            throw new NotFoundException("No existe el movimiento");
        }
        MovementVM movementVM = new MovementVM();
        movementVM.setMovement(movement);
        movementVM.setMovementDetailList(movementDetailRepository.getMovementDetailByMovementId(id));
        return movementVM;
    }

    @Override
    public MovementVMList getMovements(Paginator paginator) {
        List<Movement> movementList = movementRepository.getMovements(paginator);
        List<MovementVM> movementVms = new ArrayList<>();
        MovementVMList movementVMList =  new MovementVMList();
        movementList.forEach(movement -> {
            MovementVM movementVm= new MovementVM();
            movementVm.setMovement(movement);
            movementVm.setMovementDetailList(movementDetailRepository.getMovementDetailByMovementId(movement.getId()));
            movementVms.add(movementVm);
        });
        int totalElements = movementRepository.countMovements();
        paginator.setTotalRegisters(totalElements);
        paginator.setTotalPages((int) Math.ceil((double) totalElements/paginator.getPerPage()));
        movementVMList.setMovementVmList(movementVms);
        movementVMList.setPagination(paginator);
        return movementVMList;
    }
}
