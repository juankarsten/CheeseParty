
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    RandomAccessFile file;
    
    
    
    public FileByteReader(String filename) {
        try {
            file=new RandomAccessFile(filename, "r");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public byte[] read(){
        if(file==null)return null;
        byte[] b=null;
        try {
            b = new byte[(int)file.length()];
            file.read(b);
            file.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
}
