package cc.openhome;

import java.io.*;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/user")
public class User extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {

        Optional<Cookie> userCookie = 
                Optional.ofNullable(request.getCookies())
                        .flatMap(this::userCookie);

        if(userCookie.isPresent()) {
            Cookie cookie = userCookie.get();
            request.setAttribute(cookie.getName(), cookie.getValue());
            request.getRequestDispatcher("user.view").forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }

    }

    private Optional<Cookie> userCookie(Cookie[] cookies) {
        return Stream.of(cookies)
                     .filter(cookie -> check(cookie))
                     .findFirst();
    }

    private boolean check(Cookie cookie) {
        return "user".equals(cookie.getName()) && 
               "caterpillar".equals(cookie.getValue());
    }
}