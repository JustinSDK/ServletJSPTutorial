package cc.openhome;

import java.io.*;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@WebServlet("/captcha")
public class Captcha extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("image/jpeg");

        String passwd = new Random().ints(0, 10)
                                    .limit(4)
                                    .mapToObj(String::valueOf)
                                    .collect(Collectors.joining());
        
        ImageIO.write(
                getPasswdImage(passwd), 
                "JPG", 
                response.getOutputStream()
            );
    }

    public BufferedImage getPasswdImage(String password) throws IOException {
        BufferedImage bufferedImage =
                 new BufferedImage(60, 25,
                         BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("標楷體", Font.BOLD, 16));
        g.drawString(password, 10, 15);
        return bufferedImage;
    }}
