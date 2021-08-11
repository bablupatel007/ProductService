package com.ibm.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.productservice.dto.ProductDTO;
import com.ibm.productservice.service.ProductServiceImpl;

import io.swagger.annotations.ApiOperation;


@RequestMapping("product")
@RestController
public class ProductController {

	@Autowired
	ProductServiceImpl productService;


	@ApiOperation("This will provide products for given product ID.")
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(productService.getProductById(id));

	}

	@ApiOperation("This will provide products for create product.")
	@PostMapping("/Create")
	public ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO productDto) {

		ProductDTO createDto = productService.insertProduct(productDto);
		return ResponseEntity.ok().body(createDto);
	}


	@GetMapping
	public ResponseEntity<ProductDTO> searchByProductName(@RequestParam(value = "name") String name) {

		return ResponseEntity.ok().body(productService.getProductByName(name));

	}
	
	@GetMapping("productlist")
	public <T> ResponseEntity<T> productDetails() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK) ;
	}
}
