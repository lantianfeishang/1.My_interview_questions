package LingWoErLun.working;

import LingWoErLun.database.dataClass;
import LingWoErLun.database.student;
import LingWoErLun.student.reviseStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class mysql_student {
    public static void reviseObject(String id,String password,String command){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql = null;
        student st=new student();
        String object = null;
        try {
            conn = mysql_opcl.getConnection();
            switch (command){
                case "1":sql="update lingwoerlun.student set password=? where id = ?";
                addRevisePassword(password);
                object=st.getRePassword();
                break;
                case "2":sql="update lingwoerlun.class set teacher=? where id = ?";break;
                }
            ps = conn.prepareStatement(sql);
            ps.setObject(1,object);
            ps.setObject(2,id);
            ps.execute();
            System.out.println("修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mysql_opcl.closeResource(conn,ps);
        }
    }
    private static void addRevisePassword(String password){
        Scanner sc=new Scanner(System.in);
        student st=new student();
        String oldPassword;
        String newPassword1;
        String newPassword2;
        String command;
        while(true){
            System.out.println("输入原密码");
            oldPassword= sc.next();
            if(oldPassword.equals(password)){
                while (true){
                    System.out.println("请输入新密码");
                    newPassword1= sc.next();
                    System.out.println("请再次输入新密码");
                    newPassword2=sc.next();
                    if(newPassword1.equals(newPassword2)){
                        st.setRePassword(newPassword1);
                        return;
                    }else {
                        System.out.println("两次密码不一致\n是否继续？y/n");
                        command= sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                }
            }else {
                System.out.println("与原密码不一致\n是否继续？y/n");
                command= sc.next();
                if(!command.equals("y")){
                    return;
                }
            }
        }
    }
    public void aboutClass(String id,String control){
        reviseStudent rS=new reviseStudent();
        dataClass dC=new dataClass();
        mysql_administrator ms=new mysql_administrator();
        Scanner sc=new Scanner(System.in);
        String command;
        long classId;
        String oldClassId;
        String newClassId;
        while (true){
            System.out.println("请输入课程id：");
            try {
                classId= sc.nextLong();
                String classId1=String.valueOf(classId);
                if(classId1.length()<2){
                    classId1="0"+classId1;
                }
                if(checkAddClass(classId)==0){
                    return;
                }
                else {
                    rS.getStudentClassById(id);
                    oldClassId=dC.getClassId();
                }
                if(control.equals("2")){
                    if(oldClassId.contains(classId1)){
                        System.out.println("已有此课程，是否继续？\ny/n");
                        command=sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                    else {
                        if(oldClassId.equals("null")){
                            newClassId=classId1;
                        }
                        else {
                            newClassId=oldClassId+classId1;
                        }
                        if(newClassId.length()>=4){
                            for(int i=2;i<=newClassId.length()-2;i+=2){
                                for(int j=i+2;j<=newClassId.length();j+=2){
                                    if(Integer.parseInt(newClassId.substring(j-4,j-2))>Integer.parseInt(newClassId.substring(j-2,j))){
                                        String temp1=newClassId.substring(j-4,j-2);
                                        String temp2=newClassId.substring(j-2,j);
                                        newClassId= newClassId.replace(temp1,"aa");
                                        newClassId=newClassId.replace(temp2,temp1);
                                        newClassId=newClassId.replace("aa",temp2);
                                    }
                                }
                            }
                        }
                        ms.getstudent(id,"4",newClassId);
                        System.out.println("修改成功，是否继续？\ny/n");
                        command= sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                }
                else {
                    if(oldClassId.equals("null")){
                        System.out.println("还未选任何课");
                        return;
                    }
                    else if(!oldClassId.contains(String.valueOf(classId))){
                        System.out.println("未选此课程，是否继续？\ny/n");
                        command=sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                    else {
                        if(oldClassId.length()==classId1.length()){
                            newClassId="null";
                        }
                        else {
                            newClassId=oldClassId.replace(classId1,"");
                            ms.getstudent(id,"4",newClassId);
                            System.out.println("删除成功，是否继续？\ny/n");
                            command= sc.next();
                            if(!command.equals("y")){
                                return;
                            }
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("输入错误");
                System.out.println("是否继续？y/n");
                command= sc.next();
                if(!command.equals("y")){
                    return;
                }
            }
        }
    }
    private int checkAddClass(Long classId){
        dataClass dC=new dataClass();
        Scanner sc=new Scanner(System.in);
        String command;
        String name=dC.gerClassDataById(classId);
        if(name == null){
            System.out.println("课程id不存在");
            System.out.println("是否继续？\ny/n");
            command=sc.next();
            if(!command.equals("y")){
                return 0;
            }
        }return 1;
    }
    public void checkClass(String control,String id){
        reviseStudent rS=new reviseStudent();
        dataClass dC=new dataClass();
        if(control.equals("1")){
            rS.getStudentClassById(id);
            String classId =dC.getClassId();
            if(classId.equals("null")){
                System.out.println("未选课程");
            }
            else {
                for(int i=2;i<=classId.length();i+=2){
                    long a=Long.parseLong(classId.substring(i-2,i));
                    System.out.println(dC.gerClassDataById(a));
                }
                System.out.println("以上为已选课程");
            }
        }else {
            long idMax=dC.getIdMax();
            for(int i=1;i<=idMax;i++){
                System.out.println(i+"："+dC.gerClassDataById(i));
            }
            System.out.println("以上为全部课程");
        }
    }
}
