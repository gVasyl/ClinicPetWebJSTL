package com.servlets;

import com.models.Client;
import com.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.servlets.CreatePet.createPet;

public class EditClientServlet extends HttpServlet {
    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();
    private Integer id;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.CLIENT_CACHE.edit(new Client(this.id, request.getParameter("clientName"), createPet(request)));
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/view"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("client", CLIENT_CACHE.get(id));
        request.getRequestDispatcher("/views/client/EditClient.jsp").forward(request,response);
    }
}
