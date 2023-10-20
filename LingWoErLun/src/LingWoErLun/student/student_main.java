package LingWoErLun.student;

import LingWoErLun.database.student;
import LingWoErLun.working.mysql_administrator;
import LingWoErLun.working.mysql_student;

import java.util.Scanner;

public class student_main {
    public static void student() {
        Scanner sc = new Scanner(System.in);
        mysql_administrator.getstudent(1);
        mysql_administrator.getClass(1);
        String command;
        String id = null;
        String password = null;
        while (true) {
            System.out.println("===学生端系统===\n请输入学号：\n请输入密码：");
            while (true) {
                try {
                    id = sc.next();
                    password = sc.next();
                } catch (Exception e) {
                    System.out.println("输入错误");
                    System.out.println("是否继续？y/n");
                    command = sc.next();
                    if (!command.equals("y")) {
                        return;
                    } else {
                        break;
                    }
                }
                break;
            }
            student_login sl = new student_login();
            if (sl.student_login(id, password) == 1) {
                while (true) {
                    if (student_truemain(id, password) == 1) {
                        return;
                    }
                }
            } else {
                System.out.println("学号或密码错误");
                System.out.println("是否继续？y/n");
                command = sc.next();
                if (!command.equals("y")) {
                    return;
                }
            }
        }
    }

    private static int student_truemain(String id, String password) {
        Scanner sc = new Scanner(System.in);
        mysql_administrator ms = new mysql_administrator();
        mysql_student mt = new mysql_student();
        student st = new student();
        ms.getstudentByid(id);
        String name = st.getName();
        String command;
        System.out.println("===学生端系统===\n欢迎 " + name + "\n1.修改密码\n2.选课\n3.查课\n4.退课\n5.退出\n请选择：");
        command = sc.next();
        switch (command) {
            case "1":
                mt.reviseObject(id, password, command);
                return 0;
            case "3":
                String command2;
                System.out.println("1.已选课程\n2.全部课程\n3.返回\n请选择：");
                command2 = sc.next();
                switch (command2) {
                    case "1":
                    case "2":
                        mt.checkClass(command2, id);
                        break;
                    case "3":
                        break;
                    default:
                        System.out.println("输入错误");
                        break;
                }
                return 0;
            case "2":
            case "4":
                mt.aboutClass(id, command);
                return 0;
            case "5":
                return 1;
            default:
                System.out.println("输入错误");
                return 0;
        }
    }
}