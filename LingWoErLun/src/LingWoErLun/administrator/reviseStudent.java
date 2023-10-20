package LingWoErLun.administrator;

import LingWoErLun.database.student;
import LingWoErLun.working.mysql_administrator;
import LingWoErLun.working.mysql_student;

import java.util.Scanner;
import java.util.regex.Pattern;

public class reviseStudent {
    public void addStudent(){
        Scanner sc=new Scanner(System.in);
        String name;
        String age;
        String sex;
        String command;
        String pattren="\\D";
        while(true){
            try {
                System.out.println("输入学生名称：");
                name= sc.next();
                while (true){
                    System.out.println("输入学生年龄：");
                    age= sc.next();
                    boolean isMatch = Pattern.matches(pattren,age);
                    if(isMatch){
                        System.out.println("年龄输入错误");
                        System.out.println("是否继续？\ny/s");
                        command=sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                    else {
                        break;
                    }
                }
                while (true){
                    System.out.println("输入学生性别：");
                    sex=sc.next();
                    if(!sex.equals("男")&&!sex.equals("女")){
                        System.out.println("性别输入错误");
                        System.out.println("是否继续？\ny/n");
                        command=sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                    }
                    else {
                        break;
                    }
                    }
                    mysql_administrator.addStudent(name, age, sex);
            }catch (Exception e){
                System.out.println("信息输入错误");
                System.out.println("是否继续？\ny/n");
                command=sc.next();
                if(command.equals("n")){
                    break;
                }
            }
            System.out.println("是否继续？\ny/n");
            command=sc.next();
            if(command.equals("n")){
                break;
            }
        }
    }
    public void deleteStudent(){
        Scanner sc= new Scanner(System.in);
        while(true){
            String id;
            String command;
            while (true){
                System.out.println("请输入学生id");
                try{id= sc.next();break;}
                catch (Exception e){
                    System.out.println("id输入错误");
                    System.out.println("是否继续？\ny/n");
                    command=sc.next();
                    if(!command.equals("y")){
                        return;
                    }
                }
            }
            if(LingWoErLun.database.student.getDataId(id)==1){
                System.out.println("id不存在");
            }
            else {
                mysql_administrator.deleteStudent(id);
            }
            System.out.println("是否继续？\ny/n");
            command= sc.next();
            if(command.equals("n")){
                return;
            }
        }
    }
    public void reviseStudent(){
        student st =new student();
        mysql_student ms=new mysql_student();
        Scanner sc=new Scanner(System.in);
        String id;
        String object = null;
        while(true){
            String command;
            System.out.println("请输入学生id，或输入0退出");
            id=sc.next();
            if(id.equals("0")){
                return;
            }
            else {
                mysql_administrator.getstudentByid(id);
                String name=st.getName();
            if(name==null){
                System.out.println("无此id");
                System.out.println("是否继续？\ny/n");
                command=sc.next();
                if(!command.equals("y")){
                    return;
                }
            }else {
                while (true){
                    String control=null;
                    System.out.println("修改 "+name+" 的档案");
                    System.out.println("1.姓名\n2.年龄\n3.性别\n4.课程\n5.退出\n请输入修改对象：");
                    command= sc.next();
                    if(!command.equals("4")){
                        System.out.println("修改为：");
                        object= sc.next();
                    }
                    switch (command){
                        case "3":if(!object.equals("男")&&!object.equals("女")){System.out.println("性别输入错误");break;}
                        case "1":
                        case "2":
                            mysql_administrator.getstudent(id,command,object);
                            System.out.println("修改成功");break;
                        case "4":String commmand2;
                            System.out.println("1.增添\n2.删除\n3.返回\n请选择：");
                            commmand2 = sc.next();
                            switch (commmand2){
                                case "1":ms.aboutClass(id,"2");
                                case "2":ms.aboutClass(id,"4");
                                case "3":break;
                                default:
                                    System.out.println("输入错误");break;
                            }
                            break;
                        case "5":control="n";break;
                        default:System.out.println("输入错误");break;
                    }
                    if(control==null) {
                        System.out.println("是否继续？\ny/n");
                        control= sc.next();
                        if(!control.equals("y")){
                            break;
                        }
                    }
                    else if(!control.equals("y")){
                        break;
                    }

                }
            }
        }
    }
    }
    public void checkStudent(){
        mysql_administrator.getstudent(2);
    }
}
