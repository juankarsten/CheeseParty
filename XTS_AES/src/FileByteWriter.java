
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.misc.IOUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class FileByteWriter {
    RandomAccessFile file;
            
    public FileByteWriter(String filename) {
        try {
            file=new RandomAccessFile(filename, "rw");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void write(byte[] b){
        if(b==null)return;
        if(file==null)return;
        try {
            file.write(b);
            file.close();
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (IOException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
