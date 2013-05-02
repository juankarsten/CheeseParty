

import java.util.Arrays;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author juan.karsten
 *
 */
public class XTSAES {

    /*
     Method XTESAESBlockEnc untuk meng-enkripsi satu buah blok
     @param key1, yaitu 128 bit key
     @param P, yaitu 128 bit PlainText
     @param j, yaitu eksponen dari bilangan primitif
     @param primitiveMultiplication, yaitu objek untuk perkalian dengan bilangan primitif
     */
    public byte[] XTSAESBlockEnc(byte[] key1, byte[] P, int j, PrimitiveMultiplication primitiveMultiplication) {
        byte T[] = primitiveMultiplication.getSequenceMultiplication(j);
        byte PP[] = XTSUtils.XOR(T, P);
        AES aes = new AES();
        aes.setKey(key1);
        byte[] CC = aes.encrypt(PP);
        byte C[] = XTSUtils.XOR(CC, T);
        return C;
    }

    /*
     Method XTESAESEnc untuk meng-enkripsi dengan algoritma XTSAES
     @param P, yaitu Plaintext
     @param keys, 256 bit key yang dimasukkan sebagai input
     @param blocksize, yaitu jumlah blok yang tersedia
     */
    public byte[][] XTSAESEnc(byte[] P, byte[] keys, int blocksize) {
        byte[] tweak;
        byte plainText[][] = new byte[blocksize][16];
        byte key1[];
        byte key2[];
        PrimitiveMultiplication primitiveMultiplication;

        for (int ii = 0; ii < blocksize; ii++) {
            plainText[ii] = Arrays.copyOfRange(P, ii * 16, (ii + 1) * 16);
        }

        key1 = Arrays.copyOfRange(keys, 0, 16);
        key2 = Arrays.copyOfRange(keys, 16, 32);
        tweak = XTSUtils.generateTweak();
        tweak = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        AES aes = new AES();
        aes.setKey(key2);
        main.printMatrix(aes.encrypt(tweak));
        primitiveMultiplication = new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);

        byte C[][] = new byte[blocksize][16];
        for (int q = 0; q < blocksize - 2; q++) {
            C[q] = XTSAESBlockEnc(key1, plainText[q], q, primitiveMultiplication);

        }

        int b = P.length % 16;
        if (b == 0) {
            C[blocksize - 2] = XTSAESBlockEnc(key1, plainText[blocksize - 2], blocksize - 2, primitiveMultiplication);
            C[blocksize - 1] = new byte[16];
        } else {
            byte CC[] = XTSAESBlockEnc(key1, plainText[blocksize - 2], blocksize - 2, primitiveMultiplication);
            byte PP[] = XTSUtils.concat(plainText[blocksize - 1], CC, b);
            C[blocksize - 1] = XTSUtils.takeFirstBits(CC, b);
            C[blocksize - 2] = XTSAESBlockEnc(key1, PP, blocksize - 1, primitiveMultiplication);
        }
        return C;
    }

    /*
     Method XTESAESBlockDec untuk mendekripsi satu buah blok
     @param key1, yaitu 128 bit key
     @param P, yaitu 128 bit PlainText
     @param j, yaitu eksponen dari bilangan primitif
     @param primitiveMultiplication, yaitu objek untuk perkalian dengan bilangan primitif
     */
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public byte[] XTSAESBlockDec(byte[] key1, byte[] C, int j, PrimitiveMultiplication primitiveMultiplication) {
        byte T[] = primitiveMultiplication.getSequenceMultiplication(j);
        byte CC[] = XTSUtils.XOR(T, C);
        AES aes = new AES();
        aes.setKey(key1);
        byte[] PP = aes.decrypt(CC);
        byte P[] = XTSUtils.XOR(PP, T);
        return P;
    }

    /*
     Method XTESAESDec untuk mendekripsi dengan algoritma XTSAES
     @param C, yaitu CipherText
     @param keys, 256 bit key yang dimasukkan sebagai input
     @param blocksize, yaitu jumlah blok yang tersedia
     */
    public byte[][] XTSAESDec(byte[] C, byte[] keys, int blocksize) {
        byte[] tweak;
        byte cipherText[][] = new byte[blocksize][16];
        byte key1[];
        byte key2[];
        PrimitiveMultiplication primitiveMultiplication;


        for (int ii = 0; ii < blocksize; ii++) {
            cipherText[ii] = Arrays.copyOfRange(C, ii * 16, (ii + 1) * 16);
        }

        key1 = Arrays.copyOfRange(keys, 0, 16);
        key2 = Arrays.copyOfRange(keys, 16, 32);
        tweak = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        AES aes = new AES();
        aes.setKey(key2);
        primitiveMultiplication = new PrimitiveMultiplication(aes.encrypt(tweak), blocksize);

        byte P[][] = new byte[blocksize][16];
        for (int q = 0; q < blocksize - 2; q++) {
            P[q] = XTSAESBlockDec(key1, cipherText[q], q, primitiveMultiplication);

        }

        int b = P.length % 16;
        if (b == 0) {
            P[blocksize - 2] = XTSAESBlockDec(key1, cipherText[blocksize - 2], blocksize - 2, primitiveMultiplication);
            P[blocksize - 1] = new byte[16];
        } else {
            byte PP[] = XTSAESBlockDec(key1, cipherText[blocksize - 2], blocksize - 1, primitiveMultiplication);
            byte CC[] = XTSUtils.concat(cipherText[blocksize - 1], PP, b);
            P[blocksize - 1] = XTSUtils.takeFirstBits(CC, b);
            P[blocksize - 2] = XTSAESBlockEnc(key1, CC, blocksize - 2, primitiveMultiplication);
        }
        return P;
    }
}

