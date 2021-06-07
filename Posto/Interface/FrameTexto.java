package Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Painel com a area de texto que contera as informacoes das
 * operacoes do sistema.
 * 
 * @author Hugo Costa, Denis Biassi 
 * @version 1.0
 */
public class FrameTexto extends JPanel
{
    private JTextArea txa_console;

    /**
     * Constructor for objects of class FrameTexto
     */
    public FrameTexto()
    {
        super();
        
        txa_console = new JTextArea(20, 80);
        txa_console.setEditable(false);
        txa_console.setLineWrap(true);
        txa_console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        
        Font fnt_output = new Font(Font.MONOSPACED, Font.BOLD, 16);
        
        GridLayout out_layout = new GridLayout(1, 1);
        this.setLayout(out_layout);
        this.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                "OUTPUT:",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                fnt_output));
        
        JScrollPane scr_console = new JScrollPane(txa_console);
        this.add(scr_console);
    }

    /**
     * Exibe uma mensagem na area de texto
     * 
     * @param  saida   mensagem a ser exibida
     */
    public void print(String saida)
    {
        txa_console.append("[out]: " + saida + "\n");
    }
}
