package LingWoErLun.main;

import LingWoErLun.administrator.administrator_main;
import LingWoErLun.student.student_main;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String command;
        while(true){
            System.out.println("===课程系统===\n您是\n1.学生\n2.老师\n请输入以选择：");
            command = sc.next();
            switch (command){
                case "1":student_main.student();break;
                case "2":administrator_main.administrator();break;
                default:System.out.println("输入错误");break;
        }
        }
    }
}
