
package configuracion;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Seguridad {
     // Definicin del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definicin del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS5Padding";
    
    private final static String key ="123ISWACADEMY123"; //llave
    private final static String iv = "0123456789ABCDEF"; // vector de inicializacin
    
    
    /**
     * Funcin de tipo String que recibe una llave (key), un vector de inicializacin (iv)
     * y el texto que se desea cifrar
     * @param texto el texto sin cifrar a encriptar
     * @return el texto cifrado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException
     */
    public static String encrypt(String texto) throws Exception {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(texto.getBytes());
            return DatatypeConverter.printBase64Binary(encrypted);
    }
 
    /**
     * Funcin de tipo String que recibe una llave (key), un vector de inicializacin (iv)
     * y el texto que se desea descifrar
     * @param encrypted el texto cifrado en modo String
     * @return el texto desencriptado en modo String
     * @throws Exception puede devolver excepciones de los siguientes tipos: NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException
     */
    public static String decrypt(String encrypted) throws Exception {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            byte[] enc = DatatypeConverter.parseBase64Binary(encrypted);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(enc);
            return new String(decrypted);
    }
}
