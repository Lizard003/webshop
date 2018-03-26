package cn.yd.shop.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.yd.shop.util.JdbcUtil;

// 所有dao的父类,主要用来抽取共性代码,拥有抽象方法的类是抽象类
public class BaseDaoImpl<T> {
	
//	private JdbcTemplate jdbcTemplate;

	// 定义一个抽象方法,此方法让子类去实现
//	public abstract T getRow(ResultSet rs) throws SQLException;

	public T getById(String sql, Object id,RowMapper<T> mapper) {
		T t = null;
		// 1: 获取数据库连接对象
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		// 2: 创建执行SQL语句prepareStatement对象
		try {
			conn = JdbcUtil.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setObject(1, id);
			rs = pre.executeQuery();// 用来存储查询返回的结果集
			if (rs.next()) {
				System.out.println(this);
				t = mapper.mapRow(rs);
			}
			return t;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pre, rs);
		}
	}

	protected List<T> queryByBame(String sql,Object[] param,Class<T> clazz) {
		List<T> tList = new ArrayList<T>();
		// 1: 获取数据库连接对象
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		// 2: 创建执行SQL语句prepareStatement对象
		try {
			conn = JdbcUtil.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			rs = pre.executeQuery();// 用来存储查询返回的结果集
			// 将光标从当前位置向前移一行。ResultSet 光标最初位于第一行之前；第一次调用 next
			// 方法使第一行成为当前行；第二次调用使第二行成为当前行，依此类推
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				// 只有具体的子类才能知道结果集如何操作,此处父类实现不了
				T model = clazz.newInstance();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String colName = metaData.getColumnName(i);
					Field field = clazz.getDeclaredField(colName);
					field.setAccessible(true);
					field.set(model, rs.getObject(colName));		
				}
				tList.add(model);
			}
			return tList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pre, rs);
		}
	}

	// 抽取update insert delete , protected:只有子类可以访问
	protected void update(String sql, Object... param) {
		// 1: 获取数据库连接对象
		Connection conn = null;
		// 2: 创建执行SQL语句prepareStatement对象
		PreparedStatement pre = null;
		try {
			conn = JdbcUtil.getConnection();
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			// 3: 对每个?进行赋值操作
			// pre.setString(1, product.getName());
			// pre.setDouble(2, product.getPrice().doubleValue());
			// pre.setString(3, product.getRemark());

			// 4: 执行SQL语句(在java中 insert update delete 都称为update)
			pre.executeUpdate();
			// 5: 释放connection连接对象
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pre);
		}

	}
}
