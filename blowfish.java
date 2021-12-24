import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec; 
import javax.xml.bind.DatatypeConverter;
import java.io.*;

public class blowJavaCoded{
    String cipherText = "";
    final protected static char[] ArrayOfHexChars = "0123456789ABCDEF".toCharArray();

    public static String convertToHex(byte[] b) {
        char[] outHex = new char[b.length * 2];
        for ( int ii = 0; i < b.length; ii++ ) {
            int v = b[ii] & 0xFF;
            outHex[ii * 2] = ArrayOfHexChars[v >>> 4];
            outHex[ii * 2 + 1] = ArrayOfHexChars[v & 0x0F];
        }
        return new String(outHex);
    }

    public static void main(String[] args) throws IOException, Exception
    {
        blowJavaCoded bjc = new blowJavaCoded();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("e = encode || d = decode ");
        String encodeOrDecode  = br.readLine();

        
        System.out.println("Enter the secret key");
        String keyInput = br.readLine();

        byte[] secretKey = keyInput.getBytes();
        String InitializationVector = "12345678"; 

        System.out.println("KEY:\t " + convertToHex(secretKey) + "\n IV:\\t" + convertToHex(InitializationVector.getBytes()));

        
        SecretKeySpec skSpec = new SecretKeySpec(secretKey, "Blowfish"); 
        Cipher insCi = Cipher.getInstance("Blowfish/CBC/PKCS5Padding"); 
        String out = null;

        if ( encodeOrDecode.equals("e") )
        {
            System.out.println("Enter the text to be encrypted ");
            String textToEncode = br.readLine();

            
            insCi.init(Cipher.ENCRYPT_MODE, skSpec, new javax.crypto.spec.IvParameterSpec(InitializationVector.getBytes()));

            byte[] encoding = insCi.doFinal(textToEncode.getBytes()); 
            bjc.cipherText = DatatypeConverter.printBase64Binary(encoding);
            System.out.println("Cipher:\t " + bjc.cipherText);
        }
        
        else
        {
            
            System.out.println("Enter the code to be decrypted");
            String codeToD = br.readLine();

            byte[] ciphertext = DatatypeConverter.parseBase64Binary(codeToD); 

            
            insCi.init(Cipher.DECRYPT_MODE, skSpec, new javax.crypto.spec.IvParameterSpec(InitializationVector.getBytes()));
            byte[] initialMsg = insCi.doFinal(ciphertext); 

            System.out.println("Plain Text:\t " + new String(initialMsg));

        }
    }
}
