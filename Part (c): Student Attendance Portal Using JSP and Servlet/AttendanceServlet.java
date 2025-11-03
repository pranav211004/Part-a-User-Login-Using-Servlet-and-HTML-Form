import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String sid = req.getParameter("sid");
        String status = req.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college", "root", "yourpassword");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO attendance (student_id, status, date) VALUES (?, ?, CURDATE())");
            ps.setString(1, sid);
            ps.setString(2, status);
            int i = ps.executeUpdate();

            if (i > 0) out.println("<h3>Attendance recorded successfully!</h3>");
            else out.println("<h3>Failed to record attendance!</h3>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.close();
    }
}
