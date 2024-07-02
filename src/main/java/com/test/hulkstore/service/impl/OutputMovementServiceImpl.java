package com.test.hulkstore.service.impl;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.MovementDetail;
import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.service.TypeMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OutputMovementServiceImpl implements TypeMovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MovementDetailRepository movementDetailRepository;


    @Override
    public String addMovement(MovementVM movementVm) {
        verifyAvailableProduct(movementVm.getMovementDetailList());
        movementVm.getMovement().setMovementDate(new Date());
        Long movementId = movementRepository.addMovement(movementVm.getMovement());
        movementVm.getMovementDetailList().forEach(movementDetail -> {
            Product product = productRepository.getProductById(movementDetail.getProduct().getId());
            product.setStock(product.getStock() - movementDetail.getQuantity());
            productRepository.updateProduct(product);
            movementDetail.setProduct(product);
            movementDetail.getMovement().setId(movementId);
            movementDetailRepository.addMovementDetail(movementDetail);
        });
        return "Successful";
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
