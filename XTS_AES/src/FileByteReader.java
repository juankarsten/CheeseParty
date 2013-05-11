
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class FileByteReader {
//    RandomAccessFile file;
//    FileInputStream file2;    
    BufferedInputStream file;
    
    public FileByteReader(String filename) {
        try {
            file=new BufferedInputStream(new FileInputStream(filename));
//            file2=new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public byte[] read(){
        if(file==null)return null;
        byte[] b=null;
        try {
//            b = new byte[(int)file.length()];
//            System.out.println("zzzzz"+(int)file.length());
//            file.read(b);
//            file.close();
            ArrayList<Byte> array=new ArrayList<Byte>();
            int bb=file.read();
            if(bb!=-1)array.add((byte)bb);
            int length=0;
            while(bb!=-1){
                
                length++;
                bb=file.read();
                if(bb!=-1)array.add((byte)bb);
            }
            b=new byte[length];
            for(int ii=0;ii<array.size();ii++){
                b[ii]=array.get(ii);
            }
            System.out.println("addsfsdfsfdsfsdfsdfsdfsdfsfdddf read"+length);
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
}
