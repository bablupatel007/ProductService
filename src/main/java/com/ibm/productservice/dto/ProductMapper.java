package com.ibm.productservice.dto;

import com.ibm.productservice.domain.Product;

public class ProductMapper {

	public ProductDTO convertProductToProductDTO(Product product) {

		ProductDTO productDto = new ProductDTO();

		productDto.setId(product.getId());
		productDto.setProductName(product.getProductName());
		productDto.setPrice(product.getPrice());
		productDto.setQuantity(product.getQuantity());
		productDto.setDiscount(product.getDiscount());

		return productDto;

	}

	public Product convertProductDTOToProduct(ProductDTO productDto) {
		Product product = new Product();

		product.setId(productDto.getId());
		product.setPrice(productDto.getPrice());
		product.setProductName(productDto.getProductName());
		product.setDiscount(productDto.getDiscount());
		product.setQuantity(productDto.getQuantity());

		return product;

	}
}
