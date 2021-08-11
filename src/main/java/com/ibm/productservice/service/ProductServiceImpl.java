package com.ibm.productservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.productservice.client.ProductRestClient;
import com.ibm.productservice.domain.Product;
import com.ibm.productservice.dto.ProductDTO;
import com.ibm.productservice.dto.ProductMapper;
import com.ibm.productservice.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class ProductServiceImpl implements IProductService {
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductRepository productRepository;

	final ProductRestClient restClient;

	@Autowired
	public ProductServiceImpl(ProductRestClient restClient) {
		this.restClient = restClient;
	}

	@HystrixCommand(fallbackMethod = "taxServiceFallback")
	public ProductDTO getProductById(Long id) {

		Optional<Product> product = productRepository.findById(id);

		ProductDTO productDto = new ProductDTO();
		if (product.isPresent()) {
			ProductMapper productMapper = new ProductMapper();
			productDto = productMapper.convertProductToProductDTO(product.get());
			logger.info(" Tax service call to get the tax for given product name:"+productDto.getProductName());
			Long tax = restClient.getTax(productDto.getProductName());
			logger.info(" Tax value:"+tax);
			productDto.setTax(tax);
		}
		return productDto;
	}

	
	public ProductDTO insertProduct(ProductDTO dto) {

		ProductMapper mapper = new ProductMapper();
		Product product = mapper.convertProductDTOToProduct(dto);

		Product product1 = productRepository.save(product);
		return mapper.convertProductToProductDTO(product1);

	}
	
	public ProductDTO getProductByName(String name) {
		Product product=productRepository.findByProductName(name);
		ProductMapper mapper=new ProductMapper();
		return mapper.convertProductToProductDTO(product);
		
	}
	
	public ProductDTO taxServiceFallback(Long id) {

		Optional<Product> product = productRepository.findById(id);
		ProductDTO productDTO = null;
		if (product.isPresent()) {

			ProductMapper mapper = new ProductMapper();
			productDTO = mapper.convertProductToProductDTO(product.get());
			productDTO.setTax(3L);
		}

		return productDTO;
	}

	
	@SuppressWarnings("rawtypes")
	public List findAll(){
	  return productRepository.findAll();
	}

}
