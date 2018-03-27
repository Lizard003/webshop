package cn.yd.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.inject.New;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
		String keyword = request.getParameter("keyword");
		List<Product> proList = productDao.queryByBame(keyword);
		request.setAttribute("proList",proList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		dispatcher.forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		product.setRemark(request.getParameter("remark"));
		productDao.save(product);
		
		response.sendRedirect("/webshop/query.jsp");

	}


	public void init() throws ServletException {
		// Put your code here
	}

}
