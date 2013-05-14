/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class Result {
    byte[][] output;
    int length;

    public Result(byte[][] output, int length) {
        this.output = output;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public byte[][] getOutput() {
        return output;
    }
    
}
