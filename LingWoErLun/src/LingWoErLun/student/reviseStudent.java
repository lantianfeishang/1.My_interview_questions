package LingWoErLun.student;

import LingWoErLun.database.dataClass;
import LingWoErLun.working.mysql_opcl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class reviseStudent {
    public void getStudentClassById(String id) {
        dataClass dC = new dataClass();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet;
        try {
            conn = mysql_opcl.getConnection();
            String sql = "select class from lingwoerlun.student where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                dC.setClassId(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysql_opcl.closeResource(conn, ps);
        }
    }
}
