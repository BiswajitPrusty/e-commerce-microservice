package com.biswa.ecommerce.product;

import com.biswa.ecommerce.category.Category;
import com.biswa.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public Integer createProduct(ProductRequest request) {
        var product = repository.save(toProduct(request));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        List<Integer> productIds = request.stream().map(ProductPurchaseRequest::getProductId).toList();

        var storedProducts = repository.findAllByIdInOrderById(productIds);

        if (request.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exits");
        }
        var storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getQuantity() - productRequest.getQuantity();
            product.setQuantity(productRequest.getQuantity());
            purchasedProducts.add(toProductPurchaseResponse(product));
            product.setQuantity(newAvailableQuantity);
            repository.save(product);
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId).map(this::toProductResponse).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product not found with ID: %s", productId.toString()))
        );
    }

    public List<ProductResponse> findAllProducts() {
        return repository.findAll().stream().map(this::toProductResponse).collect(Collectors.toList());
    }

    private ProductResponse toProductResponse(Product product) {
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        productResponse.setCategoryId(product.getCategory().getId());
        productResponse.setCategoryName(product.getCategory().getName());
        productResponse.setCategoryDescription(product.getCategory().getDescription());
        return productResponse;
    }

    private Product toProduct(ProductRequest request) {
        var product = modelMapper.map(request, Product.class);
        product.setCategory(Category.builder().id(request.getCategoryId()).build());
        return product;
    }

    private ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        return modelMapper.map(product, ProductPurchaseResponse.class);
    }
}
