package cn.yd.shop.dao;

//ctrl + shift + o: 导入导出包
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.yd.shop.model.Product;

// 此类主要完成数据库的CRUD, 继承BaseDao则可以使用baseDao提供的方法
public class ProductDaoImpl extends BaseDaoImpl<Product> {

//	@Override
//	public Product getRow(ResultSet rs) throws SQLException {
//		Product product = new Product();
//		product.setId(rs.getInt("id"));
//		product.setName(rs.getString("name"));
//		product.setPrice(rs.getDouble("price"));
//		product.setRemark(rs.getString("remark"));
//		return product;
//	}

	// 通过id获取指定的商品数据
	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		// 接口是不能new,除非实现了接口的定义的所有方法
		return super.getById(sql, id, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getBigDecimal("price"));
				product.setRemark(rs.getString("remark"));
				return product;
			}
		});
	}

	// 如果没有给集合指定类型,则默认就是object类型.可以指定泛型<Product>
	public List<Product> queryByBame(String name) {
		String sql = "select * from product where name like ?";
		return super.queryByBame(sql, new Object[]{"%" + name + "%"},Product.class);
	}

	public List<Product> queryByBame(String name, int page, int size) {
		String sql = "select id,name,price from product where name like ? limit ?,?";
		return super.queryByBame(sql, new Object[] { "%" + name + "%",
				(page - 1) * size, size },Product.class);
	}

	// 完成数据的插入操作 ctrl + shift + f
	public void save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		// java是单一继承,因此super指向是唯一的父类
		super.update(sql, new Object[] { product.getName(), product.getPrice(),
				product.getRemark() });
	}

	public void update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id=?   ";
		super.update(sql, new Object[] { product.getName(), product.getPrice(),
				product.getRemark(), product.getId() });
	}

	public void delete(int id) {
		String sql = "delete from product where id = ?";
		super.update(sql, id);
	}

}
