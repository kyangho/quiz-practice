
import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Duc Tien
 */
public class MainApp {

    public static void main(String[] args) {
//        String pass1 = "admin123";
        String pass2="admin123";
//        String pass3="14052001";
//        String pass4="t042001";
//        String pass5="anbanlientuc";
//        String bcryptHashString1 = BCrypt.withDefaults().hashToString(12, pass1.toCharArray());
        String bcryptHashString2 = BCrypt.withDefaults().hashToString(12, pass2.toCharArray());
//        String bcryptHashString3 = BCrypt.withDefaults().hashToString(12, pass3.toCharArray());
//        String bcryptHashString4 = BCrypt.withDefaults().hashToString(12, pass4.toCharArray());
//        String bcryptHashString5 = BCrypt.withDefaults().hashToString(12, pass5.toCharArray());
//        System.out.println(bcryptHashString1);
        System.out.println(bcryptHashString2);
//        System.out.println(bcryptHashString3);
//        System.out.println(bcryptHashString4);
//        System.out.println(bcryptHashString5);
        // $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString1);
        //result.verified == true
//        System.out.println(result.verified);
    }
}
