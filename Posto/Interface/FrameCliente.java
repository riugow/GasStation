package Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import Nucleo.Excecoes.*;

/**
 * Tela para cadastro de dados do cliente.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FrameCliente extends JDialog
{
   private JTextField txf_nome, txf_cpf;

    /**
     * Construtor
     */
    public FrameCliente(JFrame principal)
    {
        super(principal, "Adicionar Cliente", true);
        
        JPanel clientePanel = new JPanel();
        JLabel lab_nome, lab_cpf;
        
        lab_nome = new JLabel("Nome:");
        txf_nome = new JTextField(15);
        txf_nome.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        lab_cpf = new JLabel("CPF:");
        txf_cpf = new JTextField(15);
        txf_cpf.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        
        GridBagLayout cliente_layout = new GridBagLayout();
        GridBagConstraints config = new GridBagConstraints();
        config.fill = GridBagConstraints.BOTH;
        config.weighty = 1;
        config.insets = new Insets(2,5,2,5);
        
        Font fnt_cliente = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        
        clientePanel.setLayout(cliente_layout);
        clientePanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "Dados do Cliente",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                fnt_cliente));
        
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.weightx = 0;
        clientePanel.add(lab_nome, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        clientePanel.add(txf_nome, config);
        
        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.weightx = 0;
        clientePanel.add(lab_cpf, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        clientePanel.add(txf_cpf, config);
        
        config.gridy = 2;
        config.gridx = 0;
        config.gridwidth = 2;
        JButton but_add = new JButton("Adicionar");
        but_add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ((JanelaPosto)principal).getSimulador().adicionarCliente(txf_nome.getText(), txf_cpf.getText());
                    ((JanelaPosto)principal).getFrameTexto().print("Cliente adicionado!");
                    dispose();
                } catch (PessoaJaCadastradaException exp) {
                    ((JanelaPosto)principal).getFrameTexto().print(exp.getMessage());
                }
            }
        });
        clientePanel.add(but_add, config);
        
        config.gridx = 3;
        JButton but_cancel = new JButton("Cancelar");
        but_cancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ((JanelaPosto)principal).getFrameTexto().print("Operação cancelada!");
                dispose();
            }
        });
        clientePanel.add(but_cancel, config);
        
        this.getContentPane().add(clientePanel);
        this.pack();
        Double xCoord = principal.getBounds().getCenterX() - this.getBounds().getCenterX();
        Double yCoord = principal.getBounds().getCenterY() - this.getBounds().getCenterY();
        this.setLocation( xCoord.intValue(), yCoord.intValue() );
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((JanelaPosto)principal).getFrameTexto().print("Operação cancelada!");
            }
        });
            
        this.setVisible(true);
    }
}
