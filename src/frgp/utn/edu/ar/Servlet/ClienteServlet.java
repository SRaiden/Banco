package frgp.utn.edu.ar.Servlet;

import java.io.*;
import java.text.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import frgp.utn.edu.ar.Modelo.Cliente;
import frgp.utn.edu.ar.Service.EstadoService;
import frgp.utn.edu.ar.Service.UsuariosService;
import frgp.utn.edu.ar.Service.ClienteService;


@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	 
    public ClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action");
        HttpSession session = request.getSession();
        boolean Admin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
        String pase = "";
        
        if (accion != null) {
        	
        	int idCliente = (request.getParameter("idcliente") != null && !request.getParameter("idcliente").isEmpty()) ? Integer.parseInt(request.getParameter("idcliente")) : UsuariosService.TraerUsuarioPorLogin(session.getAttribute("login").toString()).getIdCliente();
        	
        	// Preguntamos si se da de baja el cliente y si iniciamos como Admin
        	if (accion.equalsIgnoreCase("baja") && Admin){
	            ClienteService.EliminarCliente(idCliente);
	            pase = "/ListarClientes.jsp";
	        }
        	// Preguntamos si la accion es listarClientes y si iniciamos como Admin
        	else if (accion.equalsIgnoreCase("listarClientes") && Admin){
        		pase = "/ListarClientes.jsp";
	        }
        	// Preguntamos si la accion es editar y si es Admin o si esta logueado como Usuario
        	else if (accion.equalsIgnoreCase("editar") && (Admin || session.getAttribute("login").equals(UsuariosService.TraerUsuarioPorIdCliente(idCliente).getLogin()))){
	            Cliente cliente = ClienteService.TraerClientePorId(idCliente);
	            pase = "/Cliente.jsp";
	            request.setAttribute("cliente", cliente);
	        }
        	// Preguntamos si se da de alta y si iniciamos como Admin
        	else if (accion.equalsIgnoreCase("alta") && Admin){
	            ClienteService.RehabilitarCliente(idCliente);
	            pase = "/ListarClientes.jsp";
	        }
        	// Preguntamos si la accion es ver el Perfil
        	else if (accion.equalsIgnoreCase("perfil")){
	        	Cliente cliente = ClienteService.TraerClientePorId(idCliente);
	        	pase = "/Perfil.jsp";
	        	request.setAttribute("cliente", cliente);
	        }
        	// Preguntamos si iniciamos sesion como Admin
        	else if (Admin){
        		pase = "/Cliente.jsp";
	        }
        	// Caso contrario nos redirigiremos a Inicio.jsp
        	else{
        		pase = "/Inicio.jsp";
	        }
        	
        }else{
        	pase = "/Cliente.jsp";
        }
        
        
        if (pase == "/ListarClientes.jsp"){
        	int tipoEstado = (session.getAttribute("estadoClientes") != null) ? (Integer) session.getAttribute("estadoClientes") : 0;
        	request.setAttribute("lstClientes", ClienteService.TraerClientes(tipoEstado));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(pase);
        request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
        view.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("action");
    	HttpSession session = request.getSession();
    	boolean Admin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
    	int Estado = (session.getAttribute("estadoClientes") != null) ? (Integer) session.getAttribute("estadoClientes") : 0;
    	String pase = "";
    	
    	// La accion es Editar o Agregar y si iniciamos como Admin
    	if (accion.equalsIgnoreCase("Editar") || (accion.equalsIgnoreCase("Agregar") && Admin)){
    		
    		// Guardamos los datos del cliente en setters
    		Cliente cliente = new Cliente();
	        cliente.setNombre_Cliente(request.getParameter("nombre"));
	        cliente.setApellido_Cliente(request.getParameter("apellido"));
	        cliente.setDNI_Cliente(request.getParameter("dni"));
	        cliente.setDireccion_Cliente(request.getParameter("direccion"));
	        cliente.setEmail_Cliente(request.getParameter("email"));
	        cliente.setTelefono_Cliente(request.getParameter("telefono"));
	        
	        // Intentamos guardar la fecha de Nacimiento del cliente
	        try{
	            Date FechaNac = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechanac")).getTime());
	            cliente.setFechaNac_Cliente(FechaNac);
	        }
	        catch (ParseException e){
	            e.printStackTrace();
	        }
	        
	        // El estado del cliente es distinto de nulo? Guaradara el estado actual caso contrario se guardara un 2
	        cliente.setTipoEstado_Cliente((request.getParameter("tipoestado") != null) ? Integer.parseInt(request.getParameter("tipoestado")) : 2);
	        
	        //recibimos del IdCliente un valor distinto de nulo y de vacio? entonces guardaremos en la variable el idCliente caso contrario un 0
	        int idCliente = (request.getParameter("idcliente") != null && !request.getParameter("idcliente").isEmpty()) ? Integer.parseInt(request.getParameter("idcliente")) : 0;

	        // El id del cliente es 0 y es Admin?
	        if ((idCliente == 0) && Admin){
	            boolean estado = ClienteService.AgregarCliente(cliente);
	            pase = "/ListarClientes.jsp";
	            if(estado) {
	   				request.setAttribute("estadoCliente", true);
	   			}else {
	   				request.setAttribute("estadoCliente", false);
	   			}
	        }
	        // Iniciamos como Admin o 
	        else if (Admin || session.getAttribute("login").equals(UsuariosService.TraerUsuarioPorIdCliente(idCliente).getLogin()))
	        {
	        	cliente.setIdCliente(idCliente);
	        	//cliente.setCuenta1(request.getParameter("cuenta1"));
	        	//cliente.setCuenta2(request.getParameter("cuenta2"));
	        	//cliente.setCuenta3(request.getParameter("cuenta3"));
	        	//cliente.setCuenta4(request.getParameter("cuenta4"));
	        	
	        	boolean estado = ClienteService.ActualizarCliente(cliente);
	        	if (Admin)
	        	{
	        		pase = "/ListarClientes.jsp";
	        	}
	        	else
	        	{
	        		request.setAttribute("cliente", cliente);
	        		pase = "/Perfil.jsp";
	        	}
	        	if(estado) {
	   				request.setAttribute("estadoCliente", true);
	   			}else {
	   				request.setAttribute("estadoCliente", false);
	   			}
	        }
    	}
    	//  Preguntamos si la accion es filtrar y si iniciamos como Admin
    	else if (accion.equalsIgnoreCase("Filtrar") && Admin)
    	{
    		Estado = Integer.parseInt(request.getParameter("estado"));
    		session.setAttribute("estadoClientes", Estado);
    		pase = "/ListarClientes.jsp";
    	}
    	// Preguntamos si iniciamos como Admin
    	else if (Admin)
    	{
    		pase = "/ListarClientes.jsp";
    	}
    	// Caso contrario nos mostrar el Perfil
    	else
    	{
    		pase = "/Perfil.jsp";
    	}
    	// En el string hay algo distinto escrito que "/Perfil.jsp"
    	if (pase != "/Perfil.jsp") {
    		request.setAttribute("lstClientes", ClienteService.TraerClientes(Estado));
    		request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
    	}
    	
        RequestDispatcher view = request.getRequestDispatcher(pase);
        view.forward(request, response);
    	
	}

}
