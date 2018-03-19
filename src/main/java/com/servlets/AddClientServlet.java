package com.servlets;

import com.models.Client;
import com.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.servlets.CreatePet.createPet;

public class AddClientServlet extends HttpServlet {
    private final AtomicInteger ids = new AtomicInteger();
    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.CLIENT_CACHE.add(new Client(this.ids.incrementAndGet(), request.getParameter("clientName"), createPet(request)));
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/client/view"));
    }

}
