
/**
 * Inicia o sistema.
 * 
 * @author Hugo Costa / Denis Biassi 
 * @version 1.0
 */

import Interface.JanelaPosto;
import javax.swing.JFrame;

public class Principal
{
    /**
     * Constructor
     */
    public Principal()
    {
    }

    /**
     * Inicio da execução do sistema
     */
    public static void main(String[] args){
        JanelaPosto app = new JanelaPosto();

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }}
