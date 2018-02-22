package cc.openhome;

import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shopping.view")
public class Shopping extends HttpServlet {
    String html = 
    "<!DOCTYPE html>" +
    "<html>" +
    "<head>" +
    "<meta charset='UTF-8'>" +
    "<title>資訊圖書</title>" +
    "</head>" +
    "<body>" +
    "    <table style='text-align: left; width: 254px; height: 94px;' border='0'" +
    "        cellpadding='2' cellspacing='2'>" +
    "        <tbody>" +
    "            <tr>" +
    "                <td style='vertical-align: top; width: 115px;'><img" +
    "                    style='width: 99px; height: 82px;' alt=''" +
    "                    src='images/shoppingCart.png' align='left'></td>" +
    "                <td" +
    "                    style='vertical-align: middle; text-align: center; width: 1245px;'><a" +
    "                    href='cart.view'>已採購 %d 本書籍</a><br>" +
    "                </td>" +
    "            </tr>" +
    "        </tbody>" +
    "    </table>" +
    "    <br>" +
    "    <table style='text-align: left; width: 394px; height: 174px;'" +
    "        border='0' cellpadding='2' cellspacing='2'>" +
    "        <tbody>" +
    "            <tr>" +
    "                <td style='vertical-align: top; text-align: center;'><img" +
    "                    style='width: 104px; height: 142px;' alt='' src='images/Java.jpg'><br>" +
    "                    <a href='buy?book=Java'>採購此書</a><br>" +
    "                </td>" +
    "                <td style='vertical-align: top; text-align: center;'><img" +
    "                    style='width: 109px; height: 138px;' alt='' src='images/jQuery.jpg'><br>" +
    "                    <a href='buy?book=jQuery'>採購此書</a><br>" +
    "                </td>" +
    "                <td style='vertical-align: top; text-align: center;'><img" +
    "                    style='width: 106px; height: 142px;' alt=''" +
    "                    src='images/Python.jpg'><br> <a" +
    "                    href='buy?book=Python'>採購此書</a><br>" +
    "                </td>" +
    "            </tr>" +
    "        </tbody>" +
    "    </table>" +
    "    <br>" +
    "    <br> &nbsp;&nbsp;" +
    "    <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "    <br>" +
    "    <br>" +
    "</body>" + 
    "</html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                      throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<String> cart = Optional.ofNullable(
                    (List<String>) request.getSession().getAttribute("cart")
                ).orElse(new ArrayList<>());
        
        request.getSession().setAttribute("cart", cart);
	    
	    response.getWriter().println(String.format(html, cart.size()));
	}
}
