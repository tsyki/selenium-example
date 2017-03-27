package jp.gr.java_conf.tsyki.selenium.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 何もせずただレスポンスOKを返すサーブレット
 * @author TOSHIYUKI.IMAIZUMI
 *
 */
@WebServlet("/NopService")
public class NopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Thread.sleep(1 * 1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
