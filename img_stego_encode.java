import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Encoding {
    static String SECRETDATA = "D:\\secret_data.txt";
    static String COVERIMAGE = "D:\\cover_image.jpg";
    static String STEGOIMAGE = "D:\\stego_image.png";
    public static void main(String[] args) throws Exception {

        String secretText = (readSecretData());
        int[] bits= dataBit(secretText);
        System.out.println("secret data: "+secretText);
        for(int i=0;i<bits.length;i++)
            System.out.print(bits[i]);
        System.out.println();
        BufferedImage cover= readCoverImage(COVERIMAGE);
        encodeData(bits, cover);

    }

    public static String readSecretData() throws FileNotFoundException {
        String secretData = "";
        File temp = new File (SECRETDATA);
        Scanner scan = new Scanner (temp);
        while (scan.hasNextLine()){
            String x = scan.nextLine();
            secretData += x;
            if (scan.hasNextLine()){
                secretData += "\n";
            }
        }
        scan.close();
        return secretData;
    }
    public static int[] dataBit(String data){
        int temp=0;
        int[] dat_b=new int[data.length()*8];
        for(int i=0;i<data.length();i++){
            int y=data.charAt(i);
            String binary=Integer.toBinaryString(y);
            while(binary.length()!=8){
                binary='0'+binary;
            }
            System.out.println("decrypted value of "+y +": "+binary);

            for(int j=0;j<8;j++) {
                dat_b[temp] = Integer.parseInt(String.valueOf(binary.charAt(j)));
                temp++;
            };
        }

        return dat_b;
    }
    public static BufferedImage readCoverImage(String COVERIMAGEFILE){
        BufferedImage cover = null;
        File temp = new File (COVERIMAGEFILE);
        try{
            cover = ImageIO.read(temp);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return cover;
    }


    public static void encodeData(int[] bits, BufferedImage cover) throws Exception{
        File file = new File (STEGOIMAGE);
        int b_len=bits.length/8;
        int[] data_b=new int[8];
        System.out.println("Secret data length: "+b_len);
        String binary=Integer.toBinaryString(b_len);
        while(binary.length()!=8){
            binary='0'+binary;
        }
        for(int i=0;i<8;i++) {
            data_b[i] = Integer.parseInt(String.valueOf(binary.charAt(i)));
        };
        int j=0;
        int b=0;
        int curr_Bit=8;

        for (int m = 0; m < cover.getWidth(); m++){
            for ( int n = 0; n < cover.getHeight(); n++){
                if(m==0&&n<8){
                    int rgbPixel = cover.getRGB(m, n);

                    int red = rgbPixel>>16;
                    red = red & 255;
                    int green = rgbPixel>>8;
                    green = green & 255;
                    int blue = rgbPixel;
                    blue = blue & 255;
                    String binary_=Integer.toBinaryString(blue);
                    String stego_s=binary_.substring(0, binary_.length()-1);
                    stego_s=stego_s+data_b[b];


                    int temp_pixel=Integer.parseInt(stego_s, 2);
                    int z=255;
                    int rgb = (z<<24) | (red<<16) | (green<<8) | temp_pixel;
                    cover.setRGB(m, n, rgb);
                  
                    ImageIO.write(cover, "png", file);
                    b++;

                }
                else if (curr_Bit < bits.length+8 ){

                    int rgbPixel = cover.getRGB(m, n);

                    int red = rgbPixel>>16;
                    red = red & 255;
                    int green = rgbPixel>>8;
                    green = green & 255;
                    int blue = rgbPixel;
                    blue = blue & 255;
                    String binary_=Integer.toBinaryString(blue);
                    String stego_s=binary_.substring(0, binary_.length()-1);
                    stego_s=stego_s+bits[j];
                    j++;

                    int temp_pixel=Integer.parseInt(stego_s, 2);

                    int z=255;
                    int rgb = (z<<24) | (red<<16) | (green<<8) | temp_pixel;
                    cover.setRGB(m, n, rgb);
                    
                    ImageIO.write(cover, "png", file);

                    curr_Bit++;
                    
                }
            }
        }
    }
}
