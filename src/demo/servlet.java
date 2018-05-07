package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date 2018年5月7日 上午11:08:59
 * Description: servlet
 */
public class servlet extends HttpServlet {

	public servlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	/**
	 * 
	 * @author xiaoD
	 * @date 2018年5月7日 上午11:11:42
	 * Description: 重写doGet
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action != null && action.equals(action)) {
			String base64 = request.getParameter("base64");
			// 采用replace的原因是因为在ajax传参过程中base64中的+会替换为空格
			boolean isSuccess = Picture.getPicture(base64.replace(" ", "+"));
			if(isSuccess) {
				String result = Picture.getText();
				System.out.println("success:"+result);
				out.print(result);
			}
			else out.print(0);
		}
	}

	/**
	 * 
	 * @author xiaoD
	 * @date 2018年5月7日 上午11:11:13
	 * Description: 重写doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
