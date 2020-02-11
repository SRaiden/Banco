package frgp.utn.edu.ar.Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import frgp.utn.edu.ar.Service.UsuariosService;
import frgp.utn.edu.ar.Modelo.Usuarios;


@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
       
    public UsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pase = "";
        HttpSession session = request.getSession();
        String accion = request.getParameter("action");
        boolean Admin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
        
        /* HAY ALGUNA ACCION? */
        if (accion != null) {
        	
        	/* CERRAR SESION */
        	if (accion.equalsIgnoreCase("logout")) 
        	{
        		session.setAttribute("login", null);
	    		pase = "/Inicio.jsp";
        	}
        	// La accion es editar e iniciamos como Administrador?
        	else if (accion.equalsIgnoreCase("editar") && Admin)
	        {
	        	String login = request.getParameter("login");
	            Usuarios usuario = UsuariosService.TraerUsuarioPorLogin(login);
	            pase = "/Usuario.jsp";
	            request.setAttribute("usuario", usuario);
	        }
        	// La accion es listar Usuarios desde el menu?
	        else if (accion.equalsIgnoreCase("listarUsuarios") && Admin)
	        {
	        	pase = "/ListarUsuarios.jsp";
	        }
        	// Iniciamos SESION como admin?
	        else if (Admin)
	        {
	        	pase = "/Usuario.jsp";
	        }
        	// Caso contrario
	        else
	        {
	        	pase = "/Inicio.jsp";
	        }
        	
        }
        // Caso contrario
        else {
        	pase = "/Inicio.jsp";
        }
        
        /* ES LISTAR USUARIOS? */
        if (pase == "/ListarUsuarios.jsp")
        {
        	request.setAttribute("lstUsuarios", UsuariosService.TraerUsuarios());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(pase);
        view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
    	HttpSession session = request.getSession();
    	String pase = "";
    	boolean Admin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
    	
    	Usuarios usuario = new Usuarios();
    	usuario.setLogin(request.getParameter("login"));
    	usuario.setPassword(request.getParameter("password"));
    	
    	/* INICIAR SESION */
    	if (action.equalsIgnoreCase("Iniciar sesión")) {
    		Usuarios usu = UsuariosService.TraerUsuarioPorLogin(usuario.getLogin());
    		/* EL LOGIN Y PASSWORD ESTAN EN LA BBDD?*/
    		if (usu != null && usuario.getLogin().equals(usu.getLogin()) && usuario.getPassword().equals(usu.getPassword()))
    		{
    			session.setAttribute("login", usuario.getLogin());
    			pase = "/Inicio.jsp";
 
    			
    			/* CREAMOS UNA COOKIE PARA EL HISTORIAL */
    			String idCliente = String.valueOf(usuario.getIdCliente());
    			Cookie CIDCliente = new Cookie("IDCliente", idCliente);
    			CIDCliente.setMaxAge(60 * 60);
    			response.addCookie(CIDCliente);
    		}
    		else
    		{
    			pase = "/IniciarSesion.jsp?msg=error";
    		}
    	}
    	// La accion es Agregar o Editar e iniciamos como Admin?
    	else if ((action.equalsIgnoreCase("Agregar") || action.equalsIgnoreCase("Editar")) && Admin)
    	{
    		// El parametro recibido del form idcliente es distinto de nulo y de vacio?
	        if (request.getParameter("idcliente") != null && !request.getParameter("idcliente").isEmpty()) {
	        	usuario.setIdCliente(Integer.parseInt(request.getParameter("idcliente")));
	        }
	        // La accion es agregar?
	        if (action.equalsIgnoreCase("Agregar"))
	        {
	        	boolean estado = UsuariosService.CrearUsuario(usuario);
	        	 if(estado) {
		   			request.setAttribute("estadoUsuario", true);
		   		}else {
		   			request.setAttribute("estadoUsuario", false);
		   		}
	        }
	        // La accion es editar?
	        else if (action.equalsIgnoreCase("Editar"))
	        {
	        	boolean estado = UsuariosService.ActualizarUsuario(usuario);
	        	if(estado) {
		   			request.setAttribute("estadoUsuario", true);
		   		}else {
		   			request.setAttribute("estadoUsuario", false);
		   		}
	        }
	        
	        pase = "/ListarUsuarios.jsp";
    	}
    	
    	/* LISTAR USUARIOS */
    	if (pase == "/ListarUsuarios.jsp") {
    		request.setAttribute("lstUsuarios", UsuariosService.TraerUsuarios());
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(pase);
        view.forward(request, response);
	}

}
