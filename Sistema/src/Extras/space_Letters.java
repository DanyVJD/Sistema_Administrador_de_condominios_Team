package Extras;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class space_Letters implements KeyListener {

    private int limite = 3;

    @Override
    public void keyTyped(KeyEvent ke) {
        char C = ke.getKeyChar();
        if (Character.isDigit(C)) {
            ke.consume();
        } else if ((int) ke.getKeyChar() > 32 && (int) ke.getKeyChar() <= 47
                || (int) ke.getKeyChar() >= 58 && (int) ke.getKeyChar() <= 64
                || (int) ke.getKeyChar() >= 91 && (int) ke.getKeyChar() <= 96
                || (int) ke.getKeyChar() >= 123 && (int) ke.getKeyChar() <= 255) {
            ke.consume();
        } 
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
