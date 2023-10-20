package LingWoErLun.administrator;

import LingWoErLun.database.administrator;

public class administrator_login {
    administrator ad =new administrator();
    public int administrator_login(long account,String password) {
            String truePassword =ad.getAdministrator_password(account);
            if(truePassword == null||!truePassword.equals(password)){
                return 0;
            }
            else {
                return 1;
            }
        }
    }
