package com.emergentes.controller;
import com.emergentes.modelo.gestor;
import com.emergentes.modelo.productos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos objP = new productos();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            gestor agenda = (gestor) ses.getAttribute("agenda");
            objP.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("mipractica", objP);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            gestor agenda = (gestor) ses.getAttribute("agenda");
            pos = agenda.ubicarP(id);
            objP = agenda.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("mipractica", objP);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            gestor agenda = (gestor) ses.getAttribute("agenda");
            pos = agenda.ubicarP(id);
            agenda.eliminarP(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos objP = new productos();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            objP.setId(Integer.parseInt(request.getParameter("id")));
            objP.setProducto(request.getParameter("producto"));
            objP.setPrecio(Integer.parseInt(request.getParameter("precio")));
            objP.setCantidad(Integer.parseInt(request.getParameter("cantidad")));            
            HttpSession ses = request.getSession();
            gestor agenda = (gestor) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarP(objP);
            }
            else{
                pos = agenda.ubicarP(objP.getId());
                agenda.modificarP(pos, objP);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
}