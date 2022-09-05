package clinic.misc;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtil {
    public static void main(String[] args) {
        String [] passwords= {"admin","doc123","doc456","doc789","rec123"};
        for (String s : passwords) {
            System.out.printf("%s : %s\n",passwords,getSha256Hex(s));
        }

    }

    public static String getSha256Hex(String input){
        return DigestUtils.sha256Hex(input);
    }
}
