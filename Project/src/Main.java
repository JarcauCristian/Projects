import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

enum LOAD_TYPE {
    HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
    CONSOLA, FISIER, GUI
}

class Main {
    public static void main(String[] args) {
        Settings.initApplication();
        Thread ConsoleThread = new Thread(new ConsoleThread());
        Thread GUIThread = new Thread(new GUIThread());
        ConsoleThread.start();
        GUIThread.start();
    }
}