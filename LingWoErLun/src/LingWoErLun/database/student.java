package LingWoErLun.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class student {
    private static TreeMap<String,String> student_datas=new TreeMap<>();
    static Collection<String> student_Id=new ArrayList<>();
    public void clearData(){
        student_datas.clear();
        student_Id.clear();
    }
    public void data(String student_id,String student_password){
        student_datas.put(student_id,student_password);
    }
    public String getDataByAc(String account){
        return student_datas.get(account);
    }
    private Object[] student_Id2;
    private void getStudentInArray(){
        student_Id2 =  student_Id.toArray();
    }
    public long getStArrayLength(){
        getStudentInArray();
        return student_Id2.length;
    }
    public String getStByLength(int i){
        return (String) student_Id2[i];
    }
    public void dataId(String student_id){
        student_Id.add(student_id);
    }
    public static int getDataId(String id) {
        if (student_Id.contains(id)) {
            return 0;
        } else {
            return 1;
        }
    }
    private static String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    private static String rePassword;
    public void setRePassword(String rePassword){
        this.rePassword=rePassword;
    }
    public String getRePassword(){return rePassword;
    }
    private static TreeMap<String,String> student_datasClass=new TreeMap<>();
    public void dataClass(String student_id,String student_class){
        student_datasClass.put(student_id,student_class);
    }
    public String getDataClass(String student_id){
        return student_datasClass.get(student_id);
    }
}
