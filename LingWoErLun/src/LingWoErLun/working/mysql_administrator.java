package LingWoErLun.working;

import LingWoErLun.database.administrator;
import LingWoErLun.database.dataClass;
import LingWoErLun.database.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class mysql_administrator {
    //学生表的修改
    static administrator ad =new administrator();
    public static void getadministrator(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet;
        try {
            conn = mysql_opcl.getConnection();
                String sql="select account,password from lingwoerlun.administrator";
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
                ad.clearData();
                if(resultSet.next()){
                    ad.data(resultSet.getLong(1),resultSet.getString(2));
                }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void getstudent(int command){
        student st =new student();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet;
        String sql;
        String sql1 = null;
        String sql2 = null;
        try {
            st.clearData();
            conn = mysql_opcl.getConnection();
            if(command==1){
                sql="select id,password from lingwoerlun.student";
                sql1="select id from lingwoerlun.student";
                sql2="select id,class from lingwoerlun.student";
            } else{
                sql="select name,id,age,sex,password from lingwoerlun.student";
            }
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            if(command==1){
                while(resultSet.next()){
                    st.data(resultSet.getString(1), resultSet.getString(2));
                }
                ps = conn.prepareStatement(sql1);
                resultSet = ps.executeQuery();
                while (resultSet.next()){
                    st.dataId(resultSet.getString(1));
                }
                ps = conn.prepareStatement(sql2);
                resultSet = ps.executeQuery();
                while (resultSet.next()){
                    st.dataClass(resultSet.getString(1), resultSet.getString(2));
                }
            } else{
                System.out.println("名称 学号 年龄 性别 密码");
                while(resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
            }}
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void getstudent(String id,String command,String object){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql = null;
        try {
            conn = mysql_opcl.getConnection();
            switch (command){
                case "1":sql="update lingwoerlun.student set name=? where id = ?";break;
                case "2":sql="update lingwoerlun.student set age=? where id = ?";break;
                case "3":sql="update lingwoerlun.student set sex=? where id = ?";break;
                case "4":sql="update lingwoerlun.student set class=? where id = ?";break;
            }
            ps = conn.prepareStatement(sql);
            ps.setObject(1,object);
            ps.setObject(2,id);
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void getstudentByid(String id){
        student st =new student();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet;
        try {
            conn = mysql_opcl.getConnection();
            String sql="select name from lingwoerlun.student where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                st.setName(resultSet.getString(1));
            }
    }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void addStudent(String name,String age,String sex){
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = mysql_opcl.getConnection();
            String  sql = "insert into lingwoerlun.student(name,id,age,sex,password,class)values(?,?,?,?,?,?)";
            addInformation(sql,name,returnId(),age,sex,"123456","null");
            ps = conn.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
        System.out.println("输入成功");
    }
    private static void addInformation(String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = mysql_opcl.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysql_opcl.closeResource(conn, ps);
        }
    }
    private static String generateId(){
        String Id=String.valueOf(LocalDateTime.now().getYear());
        for(int i=1;i<=8;i++){
            Id+=String.valueOf((int) Math.floor(Math.random() * 9+1));
        }
        return Id;
    }
    private static String returnId(){
        student st=new student();
        String id;
        while(true){
            id =generateId();
            if(st.getDataId(id)==1){
                return id;
            }
        }
    }
    public static void deleteStudent(String id){
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = mysql_opcl.getConnection();
            String sql = "delete from lingwoerlun.student where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }System.out.println("删除成功");
    }
    public static void reset(int command){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        try {
            conn = mysql_opcl.getConnection();
            if(command==1){
                sql = "truncate table lingwoerlun.student";
            }else {
                sql = "truncate table lingwoerlun.class";
            }
            ps = conn.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    //课程表的修改
    public static void getClass(int command){
        dataClass dC=new dataClass();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet resultSet;
        String sql;
        String time;
        int i=0;
        try {
            conn = mysql_opcl.getConnection();
            if(command==1){
                sql="select id,name from lingwoerlun.class";
            }
            else {
                sql="select id,name,Classtime,teacher from lingwoerlun.class";
            }
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
                if(command==1){
                    dC.clearData();
                    while (resultSet.next()){
                        i++;
                        dC.setClassData(resultSet.getLong(1), resultSet.getString(2));
                    }
                    dC.setIdMax(i);
                }else {
                    while (resultSet.next()){
                    time= resultSet.getString(3);
                    System.out.println(resultSet.getInt(1)+"："+resultSet.getString(2)+" "+time.substring(0,2)+":"+time.substring(2,4)+"-"+time.substring(4,6)+":"+time.substring(6,8)+" "+resultSet.getString(4)+"老师");}
                }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void addClass(String name,String Classtime,String teacher){
        dataClass dC=new dataClass();
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = mysql_opcl.getConnection();
            String  sql = "insert into lingwoerlun.class(id,name,Classtime,teacher)values (?,?,?,?)";
            addInformation(sql,dC.getIdMax()+1,name,Classtime,teacher);
            ps = conn.prepareStatement(sql);
            dC.setIdMax(dC.getIdMax()+1);
            System.out.println("添加成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void deleteClass(int id){
        dataClass dC =new dataClass();
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn = mysql_opcl.getConnection();
            String sql = "delete from lingwoerlun.class where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            ps.execute();
            if(id<dC.getIdMax()){
                int i=id+1;
                while(i<=dC.getIdMax()){
                    reviseClass(i);
                    mysql_administrator.deleteClass_deleteStudent(i,"2");
                    i++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void deleteClass_deleteStudent(int id,String control){
        mysql_administrator.getstudent(1);
        student st=new student();
        String classId;
        String newClassId;
        int id1=id-1;
        String newClassId2=String.valueOf(id1);
        String inputId;
        inputId=String.valueOf(id);
        String studentId;
        if(inputId.length()==1){
            inputId="0"+inputId;
        }
        long length=st.getStArrayLength();
            for(int i=0;i<length;i++){
                mysql_administrator.getstudent(1);
                studentId=st.getStByLength(i);
                classId=st.getDataClass(st.getStByLength(i));
                if(classId.contains(inputId)){
                    if(control.equals("1")){
                        if(classId.length()==2){
                        newClassId="null";
                        }
                        else {
                        newClassId=classId.replace(inputId,"");
                        }
                        getstudent(studentId,"4",newClassId);
                    }
                    else{
                        if(newClassId2.length()==1){
                            newClassId2="0"+newClassId2;
                        }
                            newClassId=classId.replace(inputId,newClassId2);
                        getstudent(studentId,"4",newClassId);
                    }
                }
            }
    }
    public static void reviseClass(long id,String command,String object){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql = null;
        try {
            conn = mysql_opcl.getConnection();
            switch (command){
                case "1":sql="update lingwoerlun.class set name=? where id = ?";break;
                case "2":sql="update lingwoerlun.class set teacher=? where id = ?";break;
                case "3":sql="update lingwoerlun.class set time=? where id = ?";break;
            }
            ps = conn.prepareStatement(sql);
            ps.setObject(1,object);
            ps.setObject(2,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    public static void reviseClass(int id){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql;
        int id1=id-1;
        try {
            conn = mysql_opcl.getConnection();
            sql="update lingwoerlun.class set id=? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id1);
            ps.setObject(2,id);
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
}
