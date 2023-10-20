package LingWoErLun.administrator;

import LingWoErLun.working.mysql_administrator;

import java.util.Scanner;

public class administrator_main {
    public static void administrator() {
        LingWoErLun.database.administrator at =new LingWoErLun.database.administrator();
        Scanner sc =new Scanner(System.in);
        mysql_administrator.getadministrator();
        String command;
        String password;
        while (true){
        while(true) {
            System.out.println("===管理员系统===\n请输入账号：\n请输入密码：");
            String account;
            long account1;
            try {
                account = sc.next();
                password = sc.next();
                account1=Long.parseLong(account);
                at.setAccount(account1);
                break;
            } catch (Exception e) {
                System.out.println("输入错误");
                System.out.println("是否继续？y/n");
                command = sc.next();
                if (!command.equals("y")) {
                    return;
                }
            }
        }
        administrator_login ad =new administrator_login();
        if(ad.administrator_login(at.getAccount(),password)==1){
            while (true){
             if(administrator_truemain()==1){
                 return;
             }
            }
        }
        else {
            System.out.println("账号或密码错误");
            System.out.println("是否继续？y/n");
            command=sc.next();
            if(!command.equals("y")){
                return;
            }
        }}
    }
    private static int administrator_truemain(){
        mysql_administrator.getstudent(1);
        Scanner sc=new Scanner(System.in);
        System.out.println("===管理员系统===\n1.管理学生表\n2.管理课程表\n3.退出\n请选择：");
        String command=sc.next();
        switch (command){
            case "1":
                while(true){
                if(administrator_truemain_student()==1){
                return 0;
                }}
            case "2":
                while(true){
                if(administrator_truemain_class()==1){
                return 0;
                }}
            case "3":return 1;
            default:System.out.println("输入错误");return 0;
        }
    }
    private static int administrator_truemain_student(){
        mysql_administrator.getstudent(1);
        reviseStudent rs =new reviseStudent();
        Scanner sc=new Scanner(System.in);
            System.out.println("===管理员系统 学生表管理===\n1.增加\n2.删除\n3.修改\n4.查找\n5.重置\n6.退出");
            String command= sc.next();
            switch (command){
                case "1":rs.addStudent();return 0;
                case "2":rs.deleteStudent();return 0;
                case "3":rs.reviseStudent();return 0;
                case "4":rs.checkStudent();return 0;
                case "5":mysql_administrator.reset(1);return 0;
                case "6":return 1;
                default:System.out.println("输入错误");return 0;
        }
    }
    private static int administrator_truemain_class(){
        mysql_administrator.getClass(1);
        reviseClass rc=new reviseClass();
        Scanner sc=new Scanner(System.in);
        System.out.println("===管理员系统 课程表管理===\n1.增加\n2.删除\n3.修改\n4.查找\n5.重置\n6.退出");
        String command= sc.next();
        switch (command){
            case "1":rc.addClass();return 0;
            case "2":rc.deleteClass();return 0;
            case "3":rc.reviseClass();return 0;
            case "4":rc.checkClass();return 0;
            case "5":mysql_administrator.reset(1);return 0;
            case "6":return 1;
            default:System.out.println("输入错误");return 0;
        }
    }
}
