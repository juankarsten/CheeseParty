
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class PrimitiveMultiplication {
    private byte[][] a;
    
    public PrimitiveMultiplication(byte[] data,int length) {
       
        a=new byte[length][16];
        a[0]=Arrays.copyOf(data, 16);
        for(int ii=0;ii<length-1;ii++){
            a[ii+1][0]= (byte) ( ( (a[ii][0]<<2) ^ (135*(a[ii][15]>>>7)) ) & 0x0f );
            for(int kk=1;kk<15;kk++){
                a[ii+1][kk]=(byte) ( ( (a[ii][kk]<<2) ^ (a[ii][kk-1]>>>7) ) & 0x0f );
            }
        }
    }
    
    public byte[] getSequenceMultiplication(int j){
        return a[j];
    }
}
