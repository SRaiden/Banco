package frgp.utn.edu.ar.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frgp.utn.edu.ar.Service.ServicioService;
import frgp.utn.edu.ar.Service.EstadoService;
import frgp.utn.edu.ar.Modelo.Servicio;


@WebServlet("/ServicioServlet")
public class ServicioServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
       
    public ServicioServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pase = "";
	     String accion = request.getParameter("action");
	     HttpSession session = request.getSession();
	     
	     // Hay alguna accion ejecutandose?
	     if (accion != null) {
	    	 
	    	 // La accion es editar?
	    	if (accion.equalsIgnoreCase("editar"))
	        {
	        	int idServicio = Integer.parseInt(request.getParameter("idservicio")); 
	            Servicio servicio = ServicioService.TraerServicioPorId(idServicio);
	            pase = "/Servicio.jsp";
	            request.setAttribute("servicio", servicio);
	        } 
	    	// La accion es dar de alta?
	        else if (accion.equalsIgnoreCase("alta"))
	        {
	        	int idServicio = Integer.parseInt(request.getParameter("idservicio"));
	        	ServicioService.rehabilitarServicio(idServicio);
	            pase = "/ListarServicios.jsp";
	        }
	    	// La accion es dar de baja?
	    	else if (accion.equalsIgnoreCase("baja"))
		    {
	        	int idServicio = Integer.parseInt(request.getParameter("idservicio"));  
	            ServicioService.EliminarServicio(idServicio);
	            pase = "/ListarServicios.jsp";
	        }
	    	// La accion es ver los Servicios desde el menu?
	    	else if (accion.equalsIgnoreCase("listarServicios"))
	        {
	        	pase = "/ListarServicios.jsp";
	        }
	    	// Caso contrario
	        else
	        {
	        	pase = "/Servicio.jsp";
	        } 
	     }
	     // Caso contrario
	     else {
	    	 pase = "/Servicio.jsp";
        
	     }
	     
        // La escrito en el string de pase es "/ListarServicios.jsp"?
        if (pase == "/ListarServicios.jsp")
        {
        	int Estado = (session.getAttribute("estadoServicios") != null) ? (Integer) session.getAttribute("estadoServicios") : 0;
        	request.setAttribute("lstServicios", ServicioService.TraerServicios(Estado));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(pase);
        request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
        view.forward(request, response);
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("action");
    	HttpSession session = request.getSession();
    	int Estado = (session.getAttribute("estadoServicios") != null) ? (Integer) session.getAttribute("estadoServicios") : 0;
    	
    	// La accion es editar o crear?
    	if (accion.equalsIgnoreCase("Editar") || accion.equalsIgnoreCase("Agregar"))
    	{
	        Servicio servicio = new Servicio();	// Guardamos los valores de Servicio mediante el Setters
	        servicio.setRazonSocial_Servicio(request.getParameter("razonsocial"));
	        servicio.setDescripcion_Servicio(request.getParameter("descripcion"));
	        servicio.setTipoEstado	(Integer.parseInt(request.getParameter("tipoestado")));
	
	        String idServicio = request.getParameter("idservicio");
	        
	        // El id Servicio es nulo o esta vacio?
	        if (idServicio == null || idServicio.isEmpty())
	        {
	            boolean estado = ServicioService.CrearServicio(servicio); // Crear Servicio
	            if(estado) {
	   				request.setAttribute("estadoServicio", true);
	   			}else {
	   				request.setAttribute("estadoServicio", false);
	   			}
	        }
	        else
	        {
	        	servicio.setId_Servicio(Integer.parseInt(idServicio));
	        	
	        	boolean estado = ServicioService.ActualizarServicio(servicio);	// Modificar Servicio
	        	if(estado) {
	   				request.setAttribute("estadoServicio", true);
	   			}else {
	   				request.setAttribute("estadoServicio", false);
	   			}
	        }
    	}
    	// La accion es filtrar?
    	else if (accion.equalsIgnoreCase("Filtrar"))
    	{
    		Estado = Integer.parseInt(request.getParameter("estado"));
    		session.setAttribute("estadoServicios", Estado);
    	}
        
        RequestDispatcher view = request.getRequestDispatcher("/ListarServicios.jsp");
        request.setAttribute("lstServicios", ServicioService.TraerServicios(Estado));
        request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
        view.forward(request, response);
		
	}
	
}
