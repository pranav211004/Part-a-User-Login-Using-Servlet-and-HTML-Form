import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company", "root", "yourpassword");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            out.println("<h2>Employee Records</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Designation</th><th>Salary</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt(1) + "</td><td>"
                        + rs.getString(2) + "</td><td>"
                        + rs.getString(3) + "</td><td>"
                        + rs.getDouble(4) + "</td></tr>");
            }
            out.println("</table>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.close();
    }
}
