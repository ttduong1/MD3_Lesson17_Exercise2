import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBinary {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("product.txt");
        File targetFile = new File("copyfile.txt");

        if (!targetFile.exists()){
            targetFile.createNewFile();
        }
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream out = new FileOutputStream(targetFile);
        byte c;
        while ((c = (byte) fis.read()) != -1){
            out.write(c);
        }
        fis.close();
        out.close();
    }
}