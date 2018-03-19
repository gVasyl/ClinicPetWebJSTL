package com.servlets;

import com.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchClientServlet extends HttpServlet {
    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("clientName") != null && request.getParameter("petName") != null && request.getParameter("kindOfPet") != null)
            request.setAttribute("clients", CLIENT_CACHE.searchClient(request.getParameter("clientName"), request.getParameter("petName"), request.getParameter("kindOfPet")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/SearchClient.jsp");
        dispatcher.forward(request,response);
    }
}
