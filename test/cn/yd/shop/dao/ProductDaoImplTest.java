package cn.yd.shop.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.yd.shop.model.Product;

public class ProductDaoImplTest {

	private static ProductDaoImpl daoImpl = null;

	@BeforeClass
	// 注解(说明)
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试方法之前执行,一般用来初始化测试对象.....");
		daoImpl = new ProductDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("所有测试方法之后执行,用来销毁资源.....");
		daoImpl = null; // java对象会自动被垃圾回收
	}

	@Test
	public void testGetById() {
		Product product = daoImpl.getById(3);
		System.out.println(product);
	}
	
	@Test
	public void testQueryName() {
		List<Product> proList = daoImpl.queryByBame("电脑");
		for(Product temp:proList){
			System.out.println(temp);
		}
	}
	
	@Test
	public void testQueryPage() {
		List<Product> proList = daoImpl.queryByBame("电脑",2,3);
		for(Product temp:proList){
			System.out.println(temp);
		}
	}

	@Test
	public void testSave() {
		// java web中此处通过表单来进行数据输入
		Product product = new Product();
		product.setName("华为笔记本电脑");
		product.setPrice(new BigDecimal(1.99));
		product.setRemark("商务本!!!!");
		product.setId(new Integer(6));
		daoImpl.save(product);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setName("华为笔记本电脑");
		product.setPrice(new BigDecimal(1.99));
		product.setRemark("商务本!");
		product.setId(6);
		daoImpl.update(product);
	}

	@Test
	public void testDelete() {
		daoImpl.delete(6);
	}

}
