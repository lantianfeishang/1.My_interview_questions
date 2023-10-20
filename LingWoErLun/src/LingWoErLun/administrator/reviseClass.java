package LingWoErLun.administrator;

import LingWoErLun.database.dataClass;
import LingWoErLun.working.mysql_administrator;

import java.util.Scanner;

public class reviseClass {
    public void addClass(){
        dataClass dC=new dataClass();
        Scanner sc=new Scanner(System.in);
        String name;
        String teacher;
        String command;
        int a;
        while(true){
            try {
                dC.clearTime();
                System.out.println("输入课程名称：");
                name= sc.next();
                    for(int i=1;i<=2;i++){
                        while (true){a=hour(i);if(a==1){return;}else if(a==2){break;}}
                        while (true){a=minute(i);if(a==1){return;}else if(a==2){break;}}
                    }
                    if(Integer.parseInt(dC.getTime().substring(0,3))>=Integer.parseInt(dC.getTime().substring(4,8))){
                        System.out.println("终止时间小于或等于起始时间");
                        System.out.println("是否继续？y/n");
                        command= sc.next();
                        if(!command.equals("y")){
                            return;
                        }
                        else {
                            dC.clearTime();
                        }
                    }
                System.out.println("输入老师名称：");
                teacher= sc.next();
                mysql_administrator.addClass(name,dC.getTime(),teacher);
            }catch (Exception e){
                System.out.println("信息输入错误");
            }
            System.out.println("是否继续？\ny/n");
            command=sc.next();
            if(command.equals("n")){
                break;
            }
        }
    }
    private int hour(int i){
        dataClass dC =new dataClass();
        Scanner sc=new Scanner(System.in);
        String command1;
        String command2;
        try{
            if(i==1){
                System.out.println("输入课程起始小时：");
            }else {
                System.out.println("输入课程终止小时：");
            }
            int temp=sc.nextInt();
                if(temp<0||temp>24){
                    System.out.println("输入错误\n是否继续 y/s");
                    command1= sc.next();
                    if(!command1.equals("y")){
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    if(temp<10){
                        if(dC.getTime()==null){
                            dC.setTime("0"+temp);
                        }else {
                            dC.setTime(dC.getTime()+"0"+temp);
                        }
                    }
                    else {
                        if(dC.getTime()==null){
                            dC.setTime(String.valueOf(temp));
                        }else {
                            dC.setTime(dC.getTime()+temp);
                        }
                    }
                    return 2;
                }
        }catch (Exception e){
            System.out.println("输入格式有误");
            System.out.println("是否继续？\ny/n");
            command2= sc.next();
            if(!command2.equals("y")){
                return 1;
            }else {
                return 0;
            }
        }
    }
    private int minute(int i){
        dataClass dC =new dataClass();
        Scanner sc=new Scanner(System.in);
        String command;
        try{
            if(i==1){
                System.out.println("输入课程起始分钟：");
            }else {
                System.out.println("输入课程终止分钟：");
            }
            int temp=sc.nextInt();
            if(temp<0||temp>60){
                System.out.println("输入错误\n是否继续 y/s");
                command= sc.next();
                if(!command.equals("y")){
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                if(temp<10){
                    if(dC.getTime()==null){
                        dC.setTime("0"+temp);
                    }else {
                        dC.setTime(dC.getTime()+"0"+temp);
                    }
                }
                else {
                    if(dC.getTime()==null){
                        dC.setTime(String.valueOf(temp));
                    }else {
                        dC.setTime(dC.getTime()+temp);
                    }
                }
                return 2;
            }
        }catch (Exception e){
            System.out.println("输入格式有误");
            System.out.println("是否继续？\ny/n");
            command= sc.next();
            if(!command.equals("y")){
                return 1;
            }else {
                return 0;
            }
        }
    }
    public void deleteClass(){
        dataClass dC = new dataClass();
        Scanner sc= new Scanner(System.in);
        while(true){
            int id = 0;
            String command;
            System.out.println("请输入课程id");
            try{id= sc.nextInt();}
            catch (Exception e){
                System.out.println("id输入错误");
            }
            int id1=LingWoErLun.database.student.getDataId(String.valueOf(id));
            if(id1<0 && id1>dC.getIdMax()+1){
                System.out.println("id不存在");
            }
            else {
                mysql_administrator.deleteClass_deleteStudent(id,"1");
                mysql_administrator.deleteClass(id);
                System.out.println("删除成功");
            }
            System.out.println("是否继续？\ny/n");
            command= sc.next();
            if(command.equals("n")){
                return;
            }
        }
    }
    public void reviseClass(){
        dataClass dC =new dataClass();
        Scanner sc=new Scanner(System.in);
        String command;
        long id;
        String object;
        while(true){
            try {
                String control=null;
                System.out.println("请输入课程id，或输入0退出");
                id=sc.nextInt();
                if(id<=0){
                    return;
                }
                else {
                    if(id>dC.getIdMax()){
                        System.out.println("无此id");
                    }else {
                        String name= dC.gerClassDataById(id);
                        while (true){
                            System.out.println("修改 "+name+" 的档案");
                            name= dC.gerClassDataById(id);
                            System.out.println("1.名称\n2.老师\n3.时间\n请输入修改对象：");
                            command= sc.next();
                            switch (command){
                                case "1":
                                case "2":
                                    System.out.println("修改为：");
                                    object= sc.next();
                                    mysql_administrator.reviseClass(id,command,object);
                                case "3":
                                    dC.clearTime();
                                    for(int i=1;i<=2;i++){
                                        while (true){if(hour(i)==1){return;}else{break;}}
                                        while (true){if(minute(i)==1){return;}else{break;}}
                                    }
                                    mysql_administrator.reviseClass(id,command,dC.getTime());
                                case "4":control="n";break;
                                default:System.out.println("输入错误");break;
                            }
                            if(!control.equals("y")){
                                break;
                            }
                            System.out.println("是否继续？\ny/n");
                            control= sc.next();
                            if(!control.equals("y")){
                                break;
                            }
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("输入错误");
            }
        }
    }
    public void checkClass(){
        mysql_administrator.getClass(2);
    }
}