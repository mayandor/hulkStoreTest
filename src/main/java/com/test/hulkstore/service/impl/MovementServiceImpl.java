package com.test.hulkstore.service.impl;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.*;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.repository.enums.MovementType;
import com.test.hulkstore.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {
    @Autowired
    private MovementDetailRepository movementDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public String addMovementVm(MovementVM movementVm) {
        Long movementId;
        if(movementVm.getMovement().getType().equals(MovementType.OUTPUT)) {
            verifyAvailableProduct(movementVm.getMovementDetailList());
            movementVm.getMovement().setMovementDate(new Date());
            movementId = movementRepository.addMovement(movementVm.getMovement());
            movementVm.getMovementDetailList().forEach(movementDetail -> {
                Product product = productRepository.getProductById(movementDetail.getProduct().getId());
                product.setStock(product.getStock() - movementDetail.getQuantity());
                productRepository.updateProduct(product);
                movementDetail.setProduct(product);
                movementDetail.getMovement().setId(movementId);
                movementDetailRepository.addMovementDetail(movementDetail);
            });
        } else {
            movementVm.getMovement().setMovementDate(new Date());
            movementId = movementRepository.addMovement(movementVm.getMovement());
            movementVm.getMovementDetailList().forEach(movementDetail -> {
                movementDetail.setProduct(
                        setValuesProduct(movementDetail.getProduct(), movementDetail.getQuantity()));
                movementDetail.getMovement().setId(movementId);
                movementDetailRepository.addMovementDetail(movementDetail);
            });
        }
        return "Successful";
    }

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


    private Product setValuesProduct(Product product, Integer quantity) {
        if(product.getId() != null) {
            product = productRepository.getProductById(product.getId());
            product.setStock(product.getStock() + quantity);
            productRepository.updateProduct(product);
        } else {
            product.setStock(quantity);
            product.setId(productRepository.addProduct(product));
        }
        return product;
    }

    private void verifyAvailableProduct(List<MovementDetail> movementDetailList){
        movementDetailList.forEach(movementDetail-> {
            Product product = productRepository.getProductById(movementDetail.getProduct().getId());
            if(product== null || product.getStock() - movementDetail.getQuantity()  < 0) {
                throw new NotFoundException("Producto " + product.getName() + " no disponible!" );
            }
        });
    }
}
