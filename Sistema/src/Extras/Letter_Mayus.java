package Extras;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Letter_Mayus implements KeyListener{

    public void keyTyped(KeyEvent evt) {
        char c = evt.getKeyChar();
        if(Character.isLowerCase(c)){
            String cad = (""+c).toUpperCase();
            c=cad.charAt(0);
            evt.setKeyChar(c);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
