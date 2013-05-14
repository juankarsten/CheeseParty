
import java.util.Arrays;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class XTSUtils {
    public static byte[] generateTweak(){
        byte[] tweak=new byte[16];
        Random random=new Random(System.currentTimeMillis());
        random.nextBytes(tweak);
        return tweak;
    }
   
    public static byte[] XOR(byte[] a, byte[] b){
        byte[] c=new byte[a.length];
        for(int ii=0;ii<a.length;ii++){
            c[ii]=(byte) (a[ii]^b[ii]);
        }
        return c;
    }

    
    public static byte[] concat(byte a[], byte b[],int mid){

        byte c[]=takeFirstBits(a, mid);
        
        for(int ii=mid;ii<16;ii++){
                c[ii]=b[ii];
        }
        
        return c;
        
    }
    
    public static byte[] takeFirstBits(byte a[],int mid){

        byte c[]=new byte[16];
        for(int ii=0; ii<mid;ii++){
            c[ii]=a[ii];
        }
        return c;
    }
    
    public static byte[] unify(byte[][] a,int length){
        byte hasil[]=new byte[length];
        for(int ii=0,kk=0;ii<a.length;ii++){
            for(int jj=0;jj<a[ii].length;jj++){
                if(kk>=length)break;
                hasil[kk++]=a[ii][jj];
            }
        }
        return hasil;
    }
}
