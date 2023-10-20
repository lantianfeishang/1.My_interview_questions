package LingWoErLun.student;

import LingWoErLun.database.student;

public class student_login {
    student st=new student();
    public int student_login(String account,String password){
        String truePassword=st.getDataByAc(account);
        if(truePassword==null||!truePassword.equals(password)){
            return 0;
        }
        else {
            return 1;
        }
    }
}
