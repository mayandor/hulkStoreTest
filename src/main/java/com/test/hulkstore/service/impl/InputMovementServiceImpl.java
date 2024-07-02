package com.test.hulkstore.service.impl;

import com.test.hulkstore.model.MovementVM;
import com.test.hulkstore.model.Product;
import com.test.hulkstore.repository.MovementDetailRepository;
import com.test.hulkstore.repository.MovementRepository;
import com.test.hulkstore.repository.ProductRepository;
import com.test.hulkstore.service.TypeMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InputMovementServiceImpl implements TypeMovementService {
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MovementDetailRepository movementDetailRepository;

    @Override
    public String addMovement(MovementVM movementVm) {
        movementVm.getMovement().setMovementDate(new Date());
        Long movementId = movementRepository.addMovement(movementVm.getMovement());
        movementVm.getMovementDetailList().forEach(movementDetail -> {
            movementDetail.setProduct(
                    setValuesProduct(movementDetail.getProduct(), movementDetail.getQuantity()));
            movementDetail.getMovement().setId(movementId);
            movementDetailRepository.addMovementDetail(movementDetail);
        });
        return "Successful";
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
}
