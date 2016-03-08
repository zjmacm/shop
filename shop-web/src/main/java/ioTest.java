import java.io.*;

/**
 * Created by zjm on 2015/4/6.
 */
public class ioTest {
    /**
     * StringBuffer
     * @param args
     */
    public static void main(String []args) {
        /*String src = "1\n"
                +"2\n"
                +"3\n"
                +"4\n";
        char[] buffer = new char[32];
        int hasRead = 0;
        try(StringReader sr = new StringReader(src)){
           while((hasRead = sr.read(buffer))>0){
               System.out.println(new String(buffer,0,hasRead));
           }
        }catch (IOException e){
            e.printStackTrace();
        }
        try(StringWriter sw = new StringWriter()){
            sw.write("i\n");
            sw.write("love\n");
            sw.write("you\n");
            System.out.println(sw.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
        */
        /**
         * InputStreamReader OutStreamWriter
         * 将字节流转换成字符流（必须要抛出异常）
         */
        /*try(
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(1);
                }
                System.out.println("The Content is:" + line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
