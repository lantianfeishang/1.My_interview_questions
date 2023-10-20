package LingWoErLun.database;

import java.util.HashMap;
import java.util.Map;

public class administrator {
    private static Map<Long,String> administrator_datas = new HashMap<>();
    public void clearData(){
        administrator_datas.clear();
    }
    public void data(long administrator_account,String administrator_password) {
        administrator_datas.put(administrator_account,administrator_password);
    }
    public String getAdministrator_password(long administrator_account) {
        return administrator_datas.get(administrator_account);
    }
    private static long account;
    public void setAccount(long account){
        this.account=account;
    }
    public long getAccount(){
        return account;
    }
}
