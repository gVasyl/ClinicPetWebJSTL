package com.servlets;

import com.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteClientServlet extends HttpServlet {
    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();
    private Integer id;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("confirmation").equals("YES"))
            this.CLIENT_CACHE.delete(this.id);
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/view"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("client", CLIENT_CACHE.get(id));
        request.getRequestDispatcher("/views/client/DeleteClient.jsp").forward(request,response);
    }
}
