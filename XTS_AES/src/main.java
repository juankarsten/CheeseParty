
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class main {
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws IOException {
        try {
            RandomAccessFile file=new RandomAccessFile("D:\\a.txt","rw");
            byte b[]=new byte[]{1,2,3,4};
            file.write(b);
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte a[]=new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        byte a1[]=new byte[]{21,22,23,24,25,26,27,28,29,110,111,112,113,114,115,116};
        
        //printMatrix(XTSUtils.XOR(a, a1));
        
        //printMatrix(XTSUtils.concat(a, a1, 10));
        //printMatrix(XTSUtils.takeFirstBits(a, 10));
//        try {
//            RandomAccessFile file=new RandomAccessFile("D:\\LAGU\\album\\PARAMORE\\02 - Now.mp3","rw");
//            byte b[]=new byte[(int)file.length()];
//            file.read(b);
//            byte keys[]=new byte[16];
//            Random r=new Random();
//            for(int ii=0;ii<16;ii++){
//                keys[ii]=(byte) (ii+r.nextInt());
//            }
//            file.close();
//            
//            byte[] res=XTSUtils.unify(new XTSAES().XTSAESEnc(b, keys, b.length/16+1));
//            FileByteWriter fbw=new FileByteWriter("D:\\LAGU\\album\\PARAMORE\\02 - Now_enc.mp3");
//            fbw.write(res);
//            
//            FileByteReader fbr=new FileByteReader("D:\\LAGU\\album\\PARAMORE\\02 - Now_enc.mp3");
//            byte[] baca=fbr.read();
//            byte dec[]=XTSUtils.unify(new XTSAES().XTSAESDec(baca, keys, res.length/16+1));
//            fbw=new FileByteWriter("D:\\LAGU\\album\\PARAMORE\\02 - Now_dec.mp3");
//            fbw.write(dec);
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
       XTSAESDialog xTSAESDialog=new XTSAESDialog(null, true);
       
       xTSAESDialog.setVisible(true);
        
    }
    
    public static void printMatrix(byte [] a){
        for(int ii=0;ii<a.length;ii++){
            System.out.println(a[ii]);
        }
    }
}
