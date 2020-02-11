package frgp.utn.edu.ar.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frgp.utn.edu.ar.Modelo.Cuenta;
import frgp.utn.edu.ar.Modelo.Movimiento;
import frgp.utn.edu.ar.Service.CuentaService;
import frgp.utn.edu.ar.Service.MovimientoService;
import frgp.utn.edu.ar.Service.ServicioService;


@WebServlet("/MovimientoServlet")
public class MovimientoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MovimientoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pase = "";
        String action = request.getParameter("action");
        
        if (action != null) {
	        if (action.equalsIgnoreCase("transferir"))
	        {
	        	pase = "/TransferiraCuenta.jsp";
	        }
	        else if (action.equalsIgnoreCase("pagarServicio"))
	        {
	        	pase = "/PagarServicio.jsp";
	        }
	        else if (action.equalsIgnoreCase("historial"))
	        {
	        	pase = "/Historial.jsp";
	        }
        }
        else {
        	pase = "/Inicio.jsp";
        }
        
        if (pase == "/Historial.jsp") {
 
        	Cookie[] cookies = request.getCookies();
        	for (Cookie cookie : cookies) {
        		if (cookie.getName().equals("IDCliente")) {
        			int id = Integer.parseInt(cookie.getValue());
        			request.setAttribute("lstHistorial", MovimientoService.Historial(id));
        		}
        	}
        	
        }
        
        RequestDispatcher view = request.getRequestDispatcher(pase);
		request.setAttribute("lstTiposServicio", ServicioService.TraerServicios(2));
        request.setAttribute("lstTiposMovimiento", MovimientoService.traerTipoMovimiento());
        view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("action");
    	String pase = "/Inicio.jsp";
    	
    	// PREGUNTAMOS SI ES AGREGAR Y SI INICIAMOS COMO USUARIO 
    	if (accion.equalsIgnoreCase("TransferirACuenta")) {
    		
    		Movimiento mov = new Movimiento();
    		mov.setCBU_Origen(request.getParameter("CBUOrigen"));
    		mov.setCBU_Destino(request.getParameter("CBUDestino"));
    		mov.setMonto(Double.parseDouble(request.getParameter("Monto")));
    		mov.setTipoMovimiento(Integer.parseInt(request.getParameter("tipoMovimiento")));
    	
    		Cuenta cli = CuentaService.traerCuentaPorCBU(request.getParameter("CBUOrigen"));
    		int idCliente = cli.getIdCliente_Cuenta(); 
    		mov.setIdCliente(idCliente);

   			boolean estado = MovimientoService.CrearMovimientoCuenta(mov);
   			if(estado) {
   				request.setAttribute("estadoMovimientoCuenta", true);
   			}else {
   				request.setAttribute("estadoMovimientoCuenta", false);
   			}
	    	
    	}
    	else if(accion.equalsIgnoreCase("TransferirAServicio")){
    		Movimiento mov = new Movimiento();
    		mov.setCBU_Origen(request.getParameter("CBUOrigen"));
    		mov.setMonto(Double.parseDouble(request.getParameter("Monto")));
    		mov.setTipoMovimiento(Integer.parseInt(request.getParameter("tipoMovimiento")));
    		mov.setIDServicio(Integer.parseInt(request.getParameter("tipoServicio")));
    	
    		/* ----------------- Obtener el ID CUENTA DEL CLIENTE ------------------- */
    		Cuenta cli = CuentaService.traerCuentaPorCBU(request.getParameter("CBUOrigen"));
    		int idCliente = cli.getIdCliente_Cuenta(); 
    		mov.setIdCliente(idCliente);
    		
   			boolean estado = MovimientoService.CrearMovimientoServicio(mov);
   			if(estado) {
   				request.setAttribute("estadoMovimientoServicio", true);
   			}else {
   				request.setAttribute("estadoMovimientoServicio", false);
   			}
    	}
    	else {
    		pase = "/Inicio.jsp";
    	}
    	
    	if (pase != "/Inicio.jsp") {
            request.setAttribute("lstTiposMovimiento", MovimientoService.traerTipoMovimiento());
    	}
    	
    	RequestDispatcher view = request.getRequestDispatcher(pase);
        view.forward(request, response);
	}

}