
import java.util.Arrays;
import java.util.Random;
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
    
    public Result XTSAESEnc(byte[] P,byte []keys,int blocksize){
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
        //tweak=XTSUtils.generateTweak();
        tweak=new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        
        AES aes=new AES();
        aes.setKey(key2);
        //main.printMatrix(aes.encrypt(tweak));
        primitiveMultiplication=new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);
        
        byte C[][]=new byte[blocksize][16];
        for(int q=0;q<blocksize-2;q++){
            C[q]=XTSAESBlockEnc(key1, plainText[q], q, primitiveMultiplication);
            
        }
        
        int b=P.length%16;
        System.out.println("lebih"+b);
        int totalLength=(C.length-1)*16;
        if(b==0){
            C[blocksize-2]=XTSAESBlockEnc(key1, plainText[blocksize-2] ,blocksize-2, primitiveMultiplication);
            C[blocksize-1]=new byte[16];
          
        }else{
            byte CC[]=XTSAESBlockEnc(key1, plainText[blocksize-2] ,blocksize-2, primitiveMultiplication);
            byte PP[]=XTSUtils.concat(plainText[blocksize-1], CC, b);
            C[blocksize-1]=XTSUtils.takeFirstBits(CC, b);
            C[blocksize-1]=Arrays.copyOfRange(C[blocksize-1], 0, b);
            C[blocksize-2]=XTSAESBlockEnc(key1, PP ,blocksize-1, primitiveMultiplication);
            //C[blocksize-2]=Arrays.copyOfRange(C[blocksize-2], 0, b);
            totalLength+=b;
        }
        return new Result(C, totalLength);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public byte[] XTSAESBlockDec(byte[] key1, byte[] C, int j,PrimitiveMultiplication primitiveMultiplication){
        byte T[]=primitiveMultiplication.getSequenceMultiplication(j);
        byte CC[]= XTSUtils.XOR(T, C);
        AES aes=new AES();
        aes.setKey(key1);
        byte[] PP=aes.decrypt(CC);
        byte P[]=XTSUtils.XOR(PP, T);
        return P;
    }
    
    public Result XTSAESDec(byte[] C,byte []keys,int blocksize){
        byte[] tweak;
        byte cipherText[][]=new byte[blocksize][16];
        byte key1[];
        byte key2[];
        PrimitiveMultiplication primitiveMultiplication;

        
        for(int ii=0;ii<blocksize;ii++){
            cipherText[ii]=Arrays.copyOfRange(C, ii*16, (ii+1)*16);
        }
        
        key1=Arrays.copyOfRange(keys, 0,16);
        key2=Arrays.copyOfRange(keys, 16, 32);
        tweak=XTSUtils.generateTweak();
        tweak=new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        
        AES aes=new AES();
        aes.setKey(key2);
        primitiveMultiplication=new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);
        
        byte P[][]=new byte[blocksize][16];
        for(int q=0;q<blocksize-2;q++){
            P[q]=XTSAESBlockDec(key1, cipherText[q], q, primitiveMultiplication);
            
        }
        
        int b=C.length%16;
        System.out.println("lebihdec"+b);
        int len=(P.length-1)*16;
        if(b==0){
            P[blocksize-2]=XTSAESBlockDec(key1, cipherText[blocksize-2] ,blocksize-2, primitiveMultiplication);
            P[blocksize-1]=new byte[16];
          
        }else{
            byte CC[]=XTSAESBlockDec(key1, cipherText[blocksize-2] ,blocksize-1, primitiveMultiplication);
            byte PP[]=XTSUtils.concat(cipherText[blocksize-1], CC, b);
            P[blocksize-1]=XTSUtils.takeFirstBits(CC, b);
            P[blocksize-1]=Arrays.copyOfRange(P[blocksize-1], 0, b);
            P[blocksize-2]=XTSAESBlockDec(key1, PP ,blocksize-2, primitiveMultiplication);
            
            len+=b;
        }
        return new Result(P,len);
        
//        int b=C.length%16;
//        System.out.println(b);
//        if(b==0){
//            P[blocksize-2]=XTSAESBlockDec(key1, cipherText[blocksize-2] ,blocksize-2, primitiveMultiplication);
//            P[blocksize-1]=new byte[16];
//            System.out.println("here");
//        }else{
//            byte PP[]=XTSAESBlockDec(key1, cipherText[blocksize-2] ,blocksize-1, primitiveMultiplication);
//            byte CC[]=XTSUtils.concat(cipherText[blocksize-1], PP, b);
//            P[blocksize-1]=XTSUtils.takeFirstBits(CC, b);
//            P[blocksize-2]=XTSAESBlockDec(key1, CC ,blocksize-2, primitiveMultiplication);
//        }
//        return P;
    }
}

