package com.lab.service;

import java.util.List;

import com.lab.vo.Product;
import com.lab.vo.Order;

// ��Ʀs���A�Ȥ����W��
public interface IDataAccessService {
	// ���o�浧 Product, �ھڶǤJID
	public Product getProductById(int id);
	// ���o�h�� Product
	public List<Product> queryProduct();
	// �R�� Product, �ھڶǤJID
	public boolean deleteProductById(int id);
	// �ק� Product �� ItemPrice, �ھڶǤJID
	public boolean updateProductItemPriceById(int id, int price);
	// �s�W Product
	public boolean appendProduct(Product product);
	
	// ���o�h�� Order
	public List<Order> queryOrder();
	// �R�� Order, �ھڶǤJID
	public boolean deleteOrderById(int id);	
	// �s�W Order
	public boolean appendOrder(Order order);
	
	
}
