package com.servlets;

import com.models.Client;
import com.models.pets.Cat;
import com.models.pets.Dog;
import com.models.pets.Pet;
import com.store.ClientCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.servlets.CreatePet.createPet;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AVCDESClientServletsTest extends Mockito {
    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    /*
    Start testing CreatePet class
     */
    @Test
    public  void  CreatePetPet(){
        CreatePet createPet = new CreatePet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("kindOfPet")).thenReturn("Pet");
        when(request.getParameter("petName")).thenReturn("TestName0");
        verify(request, atLeast(0)).getParameter("kindOfPet");
        verify(request, atLeast(0)).getParameter("petName");
        assertEquals( new Pet("TestName0"), createPet.createPet(request));

    }

    @Test
    public  void  CreatePetDog(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("kindOfPet")).thenReturn("Dog");
        when(request.getParameter("petName")).thenReturn("TestName1");
        verify(request, atLeast(0)).getParameter("kindOfPet");
        verify(request, atLeast(0)).getParameter("petName");
        assertEquals( new Dog("TestName1"), createPet(request));

    }

    @Test
    public  void  CreatePetCat() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("kindOfPet")).thenReturn("Cat");
        when(request.getParameter("petName")).thenReturn("TestName2");
        verify(request, atLeast(0)).getParameter("kindOfPet");
        verify(request, atLeast(0)).getParameter("petName");
        assertEquals( new Cat("TestName2"), createPet(request));

    }
    /*
    Finished testing  CreatePet class
     */


    /**
     * Testing AddClientServlet...
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Test
    public void createClient() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("clientName")).thenReturn("test0");
        when(request.getParameter("petName")).thenReturn("test0");
        when(request.getParameter("kindOfPet")).thenReturn("Pet");


        assertTrue(this.CLIENT_CACHE.values().isEmpty());

        AddClientServlet addClientServlet = new AddClientServlet();
        addClientServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("kindOfPet");
        assertEquals(new Client(1, "test0", new Pet("test0")), CLIENT_CACHE.get(1));

        assertFalse(this.CLIENT_CACHE.values().isEmpty());
        when(request.getParameter("clientName")).thenReturn("test1");
        when(request.getParameter("petName")).thenReturn("test1");
        when(request.getParameter("kindOfPet")).thenReturn("Cat");

        addClientServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("kindOfPet");
        assertEquals(new Client(2, "test1", new Cat("test1")), CLIENT_CACHE.get(2));

        when(request.getParameter("clientName")).thenReturn("test2");
        when(request.getParameter("petName")).thenReturn("test2");
        when(request.getParameter("kindOfPet")).thenReturn("Dog");

        addClientServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("kindOfPet");
        assertEquals(new Client(3, "test2", new Dog("test2")), CLIENT_CACHE.get(3));

        for (int i = 1, length = this.CLIENT_CACHE.values().size(); i <= length; i++)
            this.CLIENT_CACHE.delete(i);
    }

    /**
     * Testing ClientViewServlet...
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Test
    public void ClientViewServletTest() throws ServletException, IOException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/views/client/ClientView.jsp")).thenReturn(dispatcher);

        verify(dispatcher, atLeast(0)).forward(request, response);

        new ClientViewServlet().doGet(request, response);
    }


    /*
    Start testing EditClientServlet
     */
    /**
     * Testing EditClientServletDoGetDoPost...
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Test
    public void EditClientServletChangeKindOFPetTest() throws ServletException, IOException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        assertTrue(this.CLIENT_CACHE.values().isEmpty());
        CLIENT_CACHE.add(new Client(0, "Test", new Pet("Test")));

        when(request.getRequestDispatcher("/views/client/EditClient.jsp")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("0");

        verify(request, atLeast(0)).getParameter("id");
        verify(dispatcher, atLeast(0)).forward(request, response);

        EditClientServlet editClientServlet = new EditClientServlet();
        editClientServlet.doGet(request, response);

        when(request.getParameter("clientName")).thenReturn("test0");
        when(request.getParameter("petName")).thenReturn("test0");
        when(request.getParameter("kindOfPet")).thenReturn("Dog");

        verify(request, atLeast(0)).getParameter("clientName");
        verify(request, atLeast(0)).getParameter("petName");
        verify(request, atLeast(0)).getParameter("kindOfPet");
        verify(response, atLeast(0)).sendRedirect(String.format("%s%s", request.getContextPath(), "/client/view"));

        editClientServlet.doPost(request, response);

        assertEquals(new Client(0,"test0", new Dog("test0")), CLIENT_CACHE.get(0));

        when(request.getParameter("clientName")).thenReturn("test1");
        when(request.getParameter("petName")).thenReturn("test1");
        when(request.getParameter("kindOfPet")).thenReturn("Cat");

        verify(request, atLeast(0)).getParameter("clientName");
        verify(request, atLeast(0)).getParameter("petName");
        verify(request, atLeast(0)).getParameter("kindOfPet");
        verify(response, atLeast(0)).sendRedirect(String.format("%s%s", request.getContextPath(), "/client/view"));

        editClientServlet.doPost(request, response);

        assertEquals(new Client(0,"test1", new Cat("test1")), CLIENT_CACHE.get(0));

        for (int i = 0, length = this.CLIENT_CACHE.values().size(); i < length; i++)
            this.CLIENT_CACHE.delete(i);
    }

    /**
     * Testing EditClientServletDoGetDoPost...
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Test
    public void DeleteClientServletTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        assertTrue(this.CLIENT_CACHE.values().isEmpty());
        CLIENT_CACHE.add(new Client(0, "Test", new Pet("Test")));
        assertFalse(this.CLIENT_CACHE.values().isEmpty());

        when(request.getRequestDispatcher("/views/client/DeleteClient.jsp")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("0");

        verify(request, atLeast(0)).getParameter("id");
        verify(dispatcher, atLeast(0)).forward(request, response);

        DeleteClientServlet deleteClientServlet = new DeleteClientServlet();

        when(request.getParameter("confirmation")).thenReturn("NO");
        verify(request, atLeast(0)).getParameter("confirmation");

        deleteClientServlet.doGet(request,response);
        deleteClientServlet.doPost(request,response);

        assertFalse(this.CLIENT_CACHE.values().isEmpty());

        when(request.getParameter("confirmation")).thenReturn("YES");
        verify(request, atLeast(0)).getParameter("confirmation");

        deleteClientServlet.doPost(request,response);

        assertTrue(this.CLIENT_CACHE.values().isEmpty());
    }

    @Test
    public void  SearchClientServletTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        assertTrue(this.CLIENT_CACHE.values().isEmpty());
        CLIENT_CACHE.add(new Client(0, "Test", new Pet("Test")));
        CLIENT_CACHE.add(new Client(1, "Test1", new Pet("Test1")));
        CLIENT_CACHE.add(new Client(2, "Test2", new Pet("Test2")));
        CLIENT_CACHE.add(new Client(3, "Test3", new Pet("Test3")));
        assertFalse(this.CLIENT_CACHE.values().isEmpty());

        SearchClientServlet searchClientServlet = new SearchClientServlet();

        when(request.getRequestDispatcher("/views/client/SearchClient.jsp")).thenReturn(dispatcher);

        when(request.getParameter("clientName")).thenReturn("ghf");
        when(request.getParameter("petName")).thenReturn("sdfg");
        when(request.getParameter("kindOfPet")).thenReturn("sdfg");

        verify(request, atLeast(0)).getParameter("clientName");
        verify(request, atLeast(0)).getParameter("petName");
        verify(request, atLeast(0)).getParameter("kindOfPet");

        verify(dispatcher, atLeast(0)).forward(request, response);

        searchClientServlet.doGet(request, response);

        when(request.getParameter("clientName")).thenReturn(null);
        when(request.getParameter("petName")).thenReturn("");
        when(request.getParameter("kindOfPet")).thenReturn("");

        searchClientServlet.doGet(request, response);

        when(request.getParameter("clientName")).thenReturn("");
        when(request.getParameter("petName")).thenReturn(null);
        when(request.getParameter("kindOfPet")).thenReturn("");

        searchClientServlet.doGet(request, response);

        when(request.getParameter("clientName")).thenReturn("");
        when(request.getParameter("petName")).thenReturn("");
        when(request.getParameter("kindOfPet")).thenReturn(null);

        searchClientServlet.doGet(request, response);
        for (int i = 0, length = this.CLIENT_CACHE.values().size(); i < length; i++)
            this.CLIENT_CACHE.delete(i);

    }

}
