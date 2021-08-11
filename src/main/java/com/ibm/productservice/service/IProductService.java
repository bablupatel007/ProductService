package com.ibm.productservice.service;

import com.ibm.productservice.dto.ProductDTO;

public interface IProductService {

	public ProductDTO getProductById(Long id);
	public ProductDTO insertProduct(ProductDTO dto) ;
	public ProductDTO getProductByName(String name);
}
