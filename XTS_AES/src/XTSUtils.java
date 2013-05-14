<<<<<<< HEAD

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
//    
//    public static int getByteLength(byte[] a){
//        int len=0;
//        for(int ii=0,temp=0;ii<16;ii++){
//            for(int jj=7;jj>=0;jj--){
//                temp++;
//                if(((a[ii]<<jj)&0x0f)==1){
//                    len+=temp;
//                    temp=0;
//                }
//            }
//        }
//        return len;
//    }
    
    public static byte[] concat(byte a[], byte b[],int mid){
//        int block=mid/8;
//        int mod=mid%8;
        
//        byte c[]=new byte[16];
//        for(int ii=0;ii<=block;ii++){
//            c[ii]=a[ii];
//        }
        byte c[]=takeFirstBits(a, mid);
//        c[block]=(byte) ((c[block]^(b[0]>>>(mod)))& 0x0f);
        
        for(int ii=mid;ii<16;ii++){
                c[ii]=b[ii];
        }
        
        return c;
        
    }
    
    public static byte[] takeFirstBits(byte a[],int mid){
//        int block=mid/8;
//        int mod=mid%8;
//        byte c[]=new byte[16];
//        for(int ii=0;ii<block;ii++){
//            c[ii]=a[ii];
//        }
//        c[block]=(byte) (((a[block]>>>(8-mod))<<(8-mod))&0x0f);
//        
        byte c[]=new byte[16];
        for(int ii=0; ii<mid;ii++){
            c[ii]=a[ii];
        }
        return c;
    }
    
    public static byte[] unify(byte[][] a,int len){
        byte hasil[]=new byte[len];
        for(int ii=0,kk=0;ii<a.length;ii++){
            for(int jj=0;jj<a[ii].length;jj++){
                if(kk>=len) break;
                hasil[kk++]=a[ii][jj];
            }
        }
        return hasil;
    }
}
=======

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
    
    public static byte[] unify(byte[][] a){
        byte hasil[]=new byte[a.length*16];
        for(int ii=0,kk=0;ii<a.length;ii++){
            for(int jj=0;jj<16;jj++){
                hasil[kk++]=a[ii][jj];
            }
        }
        return hasil;
    }
}
>>>>>>> 2e75a8620b2fd93cf954b0de5c98a0b6e6424bb5
