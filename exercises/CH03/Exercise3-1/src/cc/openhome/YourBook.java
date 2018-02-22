package cc.openhome;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@WebServlet("/yourbook")
public class YourBook extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");
        ImageIO.write(
                getImage(request.getParameter("name")), 
                "JPG", 
                response.getOutputStream()
            );
    }

    public BufferedImage getImage(String name) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(
                getServletContext().getResourceAsStream("book.jpg"));
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.BLACK);
        g.setFont(new Font("標楷體", Font.BOLD, 25));
        g.drawString(name, 15, 45);
        return bufferedImage;
    }
}
