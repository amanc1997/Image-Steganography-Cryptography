import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Decoding {
    static String STEGOIMAGE = "D:\\stego_image.png";
    static String ORIGINALDATA = "D:\\original_data.txt";

    public static String bin_data="";
    public static int length = 0;

    public static void main(String[] args) throws Exception {

        BufferedImage stego= readCoverImage(STEGOIMAGE);

        decodeData(stego);
        String data="";

        for(int i = 0; i< length *8; i=i+8){

            String bin_s=bin_data.substring(i,i+8);

            int dat=Integer.parseInt(bin_s,2);
            char dat_c=(char) dat;
            System.out.println("m "+dat+" c "+dat_c);
            data+=dat_c;
        }
        PrintWriter out = new PrintWriter(new FileWriter(ORIGINALDATA, true), true);
        out.write(data);
        out.close();
    }
    public static BufferedImage readCoverImage(String COVERIMAGE){
        BufferedImage cover = null;
        File file = new File (COVERIMAGE);
        try{
            cover = ImageIO.read(file);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return cover;
    }


    public static void decodeData(BufferedImage yImage) throws Exception{

        int j=0;
        int curr_bit=0;
        String or_dat="";
        for (int m = 0; m < yImage.getWidth(); m++){
            for ( int n = 0; n < yImage.getHeight(); n++){
                if(m==0&&n<8){
                    
                    int rgbPixel = yImage.getRGB(m, n);
                    int red = rgbPixel>>16;
                    red = red & 255;
                    int green = rgbPixel>>8;
                    green = green & 255;
                    int blue = rgbPixel;
                    blue = blue & 255;
                    String binary_=Integer.toBinaryString(blue);
                    or_dat+=binary_.charAt(binary_.length()-1);
                    length =Integer.parseInt(or_dat,2);

                }
                else if(curr_bit< length *8){

                    int rgbPixel = yImage.getRGB(m, n);
                    int red = rgbPixel>>16;
                    red = red & 255;
                    int green = rgbPixel>>8;
                    green = green & 255;
                    int blue = rgbPixel;
                    blue = blue & 255;
                    String binary_=Integer.toBinaryString(blue);
                    bin_data+=binary_.charAt(binary_.length()-1);


                    curr_bit++;
                    
                }
            }
        }
        System.out.println("hidden data: "+bin_data);
    }
}
