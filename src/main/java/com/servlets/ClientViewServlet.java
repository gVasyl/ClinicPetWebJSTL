package com.servlets;

import com.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientViewServlet extends HttpServlet {

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clients", this.CLIENT_CACHE.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/ClientView.jsp");
        dispatcher.forward(request,response);
    }
}
