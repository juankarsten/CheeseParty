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
    int len;
    
    public Result(byte[][] output, int len) {
        this.output=output;
        this.len=len;
    }

    public int getLen() {
        return len;
    }

    public byte[][] getOutput() {
        return output;
    }
    
    
    
}
