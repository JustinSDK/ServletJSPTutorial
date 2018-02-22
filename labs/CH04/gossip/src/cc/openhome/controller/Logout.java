package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    private final String LOGIN_PATH = "index.html";
	protected void doGet(
	        HttpServletRequest request, HttpServletResponse response) 
	                      throws ServletException, IOException {
        // 實作登出流程
	}
}
