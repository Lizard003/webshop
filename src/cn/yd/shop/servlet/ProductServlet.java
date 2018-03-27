package cn.yd.shop.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

@WebServlet(urlPatterns = "/servlet/ProductServlet")
public class ProductServlet extends HttpServlet {

	private ProductDaoImpl productDao = new ProductDaoImpl();

	public ProductServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();

		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		product.setRemark(request.getParameter("remark"));
		productDao.save(product);
		response.sendRedirect("/webshop/query.jsp");
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		product.setRemark(request.getParameter("remark"));
		product.setId(Integer.parseInt(request.getParameter("id")));
		System.out.println(product);
		productDao.update(product);
		response.sendRedirect("/webshop/query.jsp");
	}

	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String keyword = request.getParameter("keyword");
		session.setAttribute("keyword", keyword);
		List<Product> proList = productDao.queryByBame(keyword);
		request.setAttribute("proList", proList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/query.jsp");
		dispatcher.forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		productDao.delete(Integer.parseInt(id));
		String keyword = (String) session.getAttribute("keyword");
		List<Product> proList = productDao.queryByBame(keyword);
		request.setAttribute("proList", proList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/query.jsp");
		dispatcher.forward(request, response);
	}

	public void getById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1: 获取要删除的id
		String id = request.getParameter("id");
		// 2: 先获取要更新的数据
		Product product = productDao.getById(Integer.parseInt(id));

		// 3: 把待更新的数据存储到request中,然后转发到update.jsp
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/update.jsp");
		dispatcher.forward(request, response);
		System.out.println(id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		Class clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(type,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
