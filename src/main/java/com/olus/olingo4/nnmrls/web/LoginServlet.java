package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.util.Const;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Login servlet<br>
 */
@Slf4j
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final LoginState loginState = LoginStateHolder.INSTANCE.getLoginState(req);
        log.debug("loginState= {}", loginState);

        switch (loginState) {
            case NOT_LOGGED_IN:
                log.debug("Not logged in. Show 'Login with Google' link.");

                resp.setContentType("text/html; charset=UTF-8");
                final PrintWriter out = resp.getWriter();
                out.println("<html><body><a href=\""
                        + "."
                        + "/auth"
                        + "\">[Login with Google]</a>");
                out.close();
                break;

            case LOGGED_IN:

                // If the user intentionally requested "/login" directly
                // even though the user has already been logged-in to "app"

                // Redirect to default path
                final String defaultAppPath = Const.getConfig().getPathAppTop();
                log.debug("Already logged in. Maybe requested '/login' directly, redirect to default path {}", defaultAppPath);
                resp.sendRedirect(defaultAppPath);
                break;
            default:
                break;
        }

    }

}
