
import java.awt.Dimension;
import java.awt.Toolkit;


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


        XTSAESDialog xTSAESDialog = new XTSAESDialog(null, true);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - xTSAESDialog.getWidth()) / 2;
        final int y = (screenSize.height - xTSAESDialog.getHeight()) / 2;
        xTSAESDialog.setLocation(x, y);
        xTSAESDialog.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void printMatrix(byte[] a) {
        for (int ii = 0; ii < a.length; ii++) {
<<<<<<< HEAD
            System.out.print(a[ii]);
        }System.out.println();
=======

            System.out.println(a[ii]);
        }
>>>>>>> 2e75a8620b2fd93cf954b0de5c98a0b6e6424bb5
    }
}
