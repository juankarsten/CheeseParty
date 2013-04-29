
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
public class XTSAES {
//    private byte[] tweak;
//    private byte plainText[][];
//    private byte key1[];
//    private byte key2[];
//    private PrimitiveMultiplication primitiveMultiplication;
//    
//    public XTSAES(byte[] plainText,byte []keys,int blocksize) {
//        for(int ii=0;ii<blocksize;ii++){
//            this.plainText[ii]=Arrays.copyOfRange(plainText, ii*8, (ii+1)*8);
//        }
//        key1=Arrays.copyOfRange(keys, 0, 16);
//        key2=Arrays.copyOfRange(keys, 16, 32);
//        tweak=XTSUtils.generateTweak();
//        
//        AES aes=new AES();
//        aes.setKey(key2);
//        primitiveMultiplication=new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);
//    }
    
    public byte[] XTSAESBlockEnc(byte[] key1, byte[] P, int j,PrimitiveMultiplication primitiveMultiplication){
        byte T[]=primitiveMultiplication.getSequenceMultiplication(j);
        byte PP[]= XTSUtils.XOR(T, P);
        AES aes=new AES();
        aes.setKey(key1);
        byte[] CC=aes.encrypt(PP);
        byte C[]=XTSUtils.XOR(CC, T);
        return C;
    }
    
    public byte[][] XTSAESEnc(byte[] P,byte []keys,int blocksize){
        byte[] tweak;
        byte plainText[][]=new byte[blocksize][16];
        byte key1[];
        byte key2[];
        PrimitiveMultiplication primitiveMultiplication;

        
        for(int ii=0;ii<blocksize;ii++){
            plainText[ii]=Arrays.copyOfRange(P, ii*16, (ii+1)*16);
        }
        
        key1=Arrays.copyOfRange(keys, 0, 16);
        key2=Arrays.copyOfRange(keys, 16, 32);
        tweak=XTSUtils.generateTweak();
        
        AES aes=new AES();
        aes.setKey(key2);
        primitiveMultiplication=new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);
        
        byte C[][]=new byte[blocksize][16];
        for(int q=0;q<blocksize-2;q++){
            C[q]=XTSAESBlockEnc(key1, plainText[q], q, primitiveMultiplication);
            
        }
        
        int b=XTSUtils.getByteLength(plainText[blocksize-1]);
        if(b==0){
            C[blocksize-2]=XTSAESBlockEnc(key1, plainText[blocksize-2] ,blocksize-2, primitiveMultiplication);
            
        }else{
            byte CC[]=XTSAESBlockEnc(key1, plainText[blocksize-2] ,blocksize-2, primitiveMultiplication);
            byte PP[]=XTSUtils.concat(plainText[blocksize-1], CC, b);
            C[blocksize-1]=XTSUtils.takeFirstBits(CC, b);
            C[blocksize-2]=XTSAESBlockEnc(key1, PP ,blocksize-1, primitiveMultiplication);
        }
        return C;
    }
}
