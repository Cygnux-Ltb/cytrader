package io.cygnux.console.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnux.repository.dao.ProductDao;
import io.cygnux.repository.entities.internal.InProduct;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public class ProductService {

	private final Logger log = Log4j2LoggerFactory.getLogger(ProductService.class);

	@Resource
	private ProductDao dao;

	/**
	 * 
	 * @return
	 */
	public List<InProduct> getAll() {
		return dao.findAll();
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public InProduct get(int productId) {
		return dao.getReferenceById(productId);
	}

}
