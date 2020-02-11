package frgp.utn.edu.ar.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frgp.utn.edu.ar.Modelo.Cuenta;
import frgp.utn.edu.ar.Service.CuentaService;
import frgp.utn.edu.ar.Service.EstadoService;


@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CuentaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pase = "";
        String action = request.getParameter("action");
        
        // Estamos ejecutando alguna accion?
        if (action != null) {
        	// La accion es Editar?
	        if (action.equalsIgnoreCase("editar"))
	        {
	            String CBU = request.getParameter("cbu");
	            Cuenta cuenta = CuentaService.traerCuentaPorCBU(CBU);
	            pase = "/Cuenta.jsp";
	            request.setAttribute("Cuenta", cuenta);
	        }
	        // La accion es dar de alta?
	        else if (action.equalsIgnoreCase("alta"))
	        {
	        	String CBU = request.getParameter("cbu");
	            CuentaService.rehabilitarCuenta(CBU);
	            pase = "/ListarCuentas.jsp";
	        }
        	// La accion es dar de baja?
	        else if (action.equalsIgnoreCase("baja"))
	        {
	        	String CBU = request.getParameter("cbu");
	            CuentaService.EliminarCuenta(CBU);
	            pase = "/ListarCuentas.jsp";
	        }
        	// La accion es ver las listas de cuentas al pulsar en el menu?
	        else if (action.equalsIgnoreCase("listarCuentas"))
	        {
	        	pase = "/ListarCuentas.jsp";
	        }
        	// Caso contrario
	        else
	        {
	        	pase = "/Cuenta.jsp";
	        }
        	
        }
        // Si la accion es nula ver la pagina cuenta.jsp
        else {
        	pase = "/Cuenta.jsp";
        }
        
        // En el string esta escrito la "/ListarCuentas.jsp"
        if (pase == "/ListarCuentas.jsp") {
        	HttpSession session = request.getSession();
        	int tipoEstado = (session.getAttribute("estadoCuentas") != null) ? (Integer) session.getAttribute("estadoCuentas") : 0;
        	request.setAttribute("lstCuentas", CuentaService.traerCuentas(tipoEstado));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(pase);
        request.setAttribute("lstTiposCuenta", CuentaService.traerTiposCuenta());
        request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
        view.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("action");
    	HttpSession session = request.getSession();
    	boolean Admin = (session.getAttribute("login") != null) ? session.getAttribute("login").equals("admin") : false;
    	int Estado = (session.getAttribute("estadoCuentas") != null) ? (Integer) session.getAttribute("estadoCuentas") : 0;
    	String pase = "/ListarCuentas.jsp";
    	
    	/* PREGUNTAMOS SI ES EDICION O AGREGAR Y SE INICIO COMO ADMIN*/
    	if ((accion.equalsIgnoreCase("Editar") || accion.equalsIgnoreCase("Agregar") && Admin)) {
    		Cuenta cuenta = new Cuenta();
	        cuenta.setIdCliente_Cuenta(Integer.parseInt(request.getParameter("idcliente")));	// GUARDAMOS EN EL OBJETO EL VALOR DEL FORM
	        cuenta.setTipoCuenta(Integer.parseInt(request.getParameter("tipocuenta")));			// GUARDAMOS EN EL OBJETO EL VALOR DEL FORM
	        cuenta.setTipoEstado(Integer.parseInt(request.getParameter("tipoestado")));			// GUARDAMOS EN EL OBJETO EL VALOR DEL FORM
	        
	        String ID = request.getParameter("idCuenta");	// GUARDAMOS EN UNA VARIABLE EL IDCUENTA
	        
	        // SI EL ID ES NULO O ESTA VACIO ES PORQUE ES UN AGREGAR
	        if (ID == null || ID.isEmpty()) 
	        {
	            boolean estado = CuentaService.AgregarCuenta(cuenta);
	            if(estado) {
	   				request.setAttribute("estadoCuenta", true);
	   			}else {
	   				request.setAttribute("estadoCuenta", false);
	   			}
	        }
	        // CASO CONTRARIO ES ACTUALIZAR CUENTA
	        else 
	        {
	        	cuenta.setID_Cuenta(ID);
	        	cuenta.setSaldo_Cuenta(Double.parseDouble(request.getParameter("saldoCuenta")));
	        	boolean estado = CuentaService.ActualizarCuenta(cuenta);
	        	if(estado) {
	   				request.setAttribute("estadoCuenta", true);
	   			}else {
	   				request.setAttribute("estadoCuenta", false);
	   			}
	        }
    	}
    	// La accion es Filtrar e iniciamos como Admin?
    	else if (accion.equalsIgnoreCase("Filtrar") && Admin) {
    		Estado = Integer.parseInt(request.getParameter("estado"));
    		session.setAttribute("estadoCuentas", Estado);
    	}
    	// Caso contrario
    	else {
    		pase = "/Inicio.jsp";
    	}
    	
    	// En el string pase hay algo distinto escrito que "/Inicio.jsp"
    	if (pase != "/Inicio.jsp") {
    		request.setAttribute("lstCuentas", CuentaService.traerCuentas(Estado));
            request.setAttribute("lstTiposCuenta", CuentaService.traerTiposCuenta());
            request.setAttribute("lstTiposEstado", EstadoService.TraerTiposEstado());
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(pase);
        view.forward(request, response);
	}

}
