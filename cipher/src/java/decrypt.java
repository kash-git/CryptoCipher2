
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.RSAUtil;
import static pojo.RSAUtil.privateKey;


/**
 *
 * @author kashif
 */
public class decrypt extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String decryptedString;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(encrypt.encryptedString.equalsIgnoreCase("Please enter a text or upload a file"))
            response.sendRedirect("index.html");
            else{
            decryptedString = RSAUtil.decrypt(encrypt.encryptedString, privateKey);
                    //System.out.println(decryptedString);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Encrypt</title>");  
            out.println("<link rel='stylesheet' href='Styles/style.css'>");
            out.println("<script src='/cipher/download.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<a href=\"index.html\"><h1 class=\"title\">CryptoCipher</h1></a>");
            out.println("<nav>");
            out.println("<ul class='nav_links'>");
            out.println("<li><a href='#'>Home</a></li>");
            out.println("<li><a href='how_to_use.html'>How to use</a></li>");
            out.println("<li><a href='about.html'>About</a></li>");
            out.println("<li><a href='contact.html'>Contact</a></li>");
            out.println("</ul>");
            out.println("</nav>");
            out.println("</header>");
             out.println("<br><br><h1 style='word-wrap: break-word; text-align:center; color:white;'>Your Decrypted text is</h1><br><br>");
            out.println("<h1 style='word-wrap: break-word; width: 60%; text-align:center; margin:auto; color:white;'>" +decryptedString + "</h1><br><br>");
            out.println("<form action='index.html' method='post'>");
                out.println("<center><button style='margin-bottom: 10px; text-align:center;' class='downloadBtn' type='submit'> Encrypt</button></center>");
            out.println("</form>");
                out.println("<center><button class='downloadBtn' type='submit' onclick='downloadDecrypted()'> Download</button></center>");
            out.println("</body>");
            out.println("</html>");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
