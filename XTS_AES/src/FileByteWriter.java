
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
//    RandomAccessFile file;
//    FileOutputStream output;        
    BufferedOutputStream file;
    
    public FileByteWriter(String filename) {
            try {
                //            file=new RandomAccessFile(filename, "rw");
                //            System.out.println("write");
                //            output = new FileOutputStream(new File(filename));
                            file=new BufferedOutputStream(new FileOutputStream(filename));
            } catch (IOException ex) {
                Logger.getLogger(FileByteWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
       
    }
    
    public void write(byte[] b){
        if(b==null)return;
        if(file==null)return;
        try {
//            file.write(b,0,b.length);
//            System.out.println("before write"+b.length);
//            file.close();
            System.out.println("tajdkdshfhksdf"+b.length);
            file.write(b);
            file.flush();
            file.close();
//            System.out.println("before write"+b.length);
//            output.write(b);
//            output.close();
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (IOException ex) {
            Logger.getLogger(FileByteReader.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
