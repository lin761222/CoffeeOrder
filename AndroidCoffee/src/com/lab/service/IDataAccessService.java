package com.lab.service;

import java.util.List;

import com.lab.vo.Product;
import com.lab.vo.Order;

// 資料存取服務介面規格
public interface IDataAccessService {
	// 取得單筆 Product, 根據傳入ID
	public Product getProductById(int id);
	// 取得多筆 Product
	public List<Product> queryProduct();
	// 刪除 Product, 根據傳入ID
	public boolean deleteProductById(int id);
	// 修改 Product 的 ItemPrice, 根據傳入ID
	public boolean updateProductItemPriceById(int id, int price);
	// 新增 Product
	public boolean appendProduct(Product product);
	
	// 取得多筆 Order
	public List<Order> queryOrder();
	// 刪除 Order, 根據傳入ID
	public boolean deleteOrderById(int id);	
	// 新增 Order
	public boolean appendOrder(Order order);
	
	
}
