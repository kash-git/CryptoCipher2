import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static pojo.RSAUtil.encrypt;
import static pojo.RSAUtil.publicKey;
@MultipartConfig
public class encrypt extends HttpServlet {
    public static String encryptedString;
//    private String getFileName(final Part part){
//        for(String content:part.getHeader("content-disposition").split(";")){
//            if(content.trim().startsWith("filename")){
//                return content.substring(content.indexOf('=')+1).trim().replace("\"","");
//            }
//        }
//        return null;
//    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ServletContext context=getServletContext();
            String s;
            s=request.getParameter("inp"); 
            if(s.isEmpty()){
            final Part filepart=request.getPart("file");
           // final String filename=getFileName(filepart);
            InputStream filecontent=filepart.getInputStream();
               ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length; (length = filecontent.read(buffer)) != -1; ) {
                    result.write(buffer, 0, length);
                }

                s= result.toString();
            }
                //System.out.print(st);
            if(!s.isEmpty()){
                    encryptedString = Base64.getEncoder().encodeToString(encrypt(s, publicKey));
            }else{
                encryptedString="Please enter a text or upload a file";
            }
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
            out.println("<li><a href='#' >Home</a></li>");
            out.println("<li><a href='how_to_use.html'>How to use</a></li>");
            out.println("<li><a href='about.html'>About</a></li>");
            out.println("<li><a href='contact.html'>Contact</a></li>");
            out.println("</ul>");
            out.println("</nav>");
            out.println("</header>");
            out.println("<br><br><h1 style='word-wrap: break-word; text-align:center; color:white;'>Your Encrypted text is</h1><br><br>");
            out.println("<h2 style='word-wrap: break-word; text-align:center; width: 60%; margin:auto; color:white;'>" +encryptedString + "</h2><br><br>");
            out.println("<form action='decrypt' method='post'>");
            out.println("<center><button style='margin-bottom: 10px; text-align:center;' class='downloadBtn' type='submit'> Decrypt</button></center>");
            out.println("</form>");
            out.println("<center><button class='downloadBtn' type='submit' onclick='downloadEncrypted()'> Download</button></center>");
            out.println("</body>");
            out.println("</html>");
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
