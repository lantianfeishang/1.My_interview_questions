package LingWoErLun.database;

import java.util.TreeMap;

public class dataClass {
    private static String time;
    public void clearTime(){
        time="";
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getTime(){
        return time;
    }
    public void clearData(){
        classData.clear();
    }
    private static TreeMap<Long,String> classData =new TreeMap<>();
    public void setClassData(Long id,String name){
        classData.put(id,name);
    }
    public String gerClassDataById(long id){
        return classData.get(id);
    }
    private static int idMax;
    public void setIdMax(int idMax){
        this.idMax=idMax;
    }
    public int getIdMax(){
        return idMax;
    }
    private static String classid;
    public void setClassId (String classid){
        this.classid=classid;
    }
    public String getClassId(){
        return classid;
    }
}
