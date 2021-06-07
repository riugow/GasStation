package Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import Nucleo.Excecoes.*;

/**
 * class FrameFuncionario.
 * 
 * @author Hugo Costa / Denis Biassi
 * @version 1.0
 */
public class FrameFuncionario extends JDialog
{
   private JTextField txf_nome, txf_cpf, txf_sal;

    /**
     * Constructor
     */
    public FrameFuncionario(JFrame principal)
    {
        super(principal, "Adicionar Funcionario", true);
        
        JPanel funcionarioPanel = new JPanel();
        JLabel lab_nome, lab_cpf, lab_sal;
        
        lab_nome = new JLabel("Nome:");
        txf_nome = new JTextField(15);
        txf_nome.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        lab_cpf = new JLabel("CPF:");
        txf_cpf = new JTextField(15);
        txf_cpf.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        lab_sal = new JLabel("Salario:");
        txf_sal = new JTextField(15);
        txf_sal.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));

        
        GridBagLayout funcionario_layout = new GridBagLayout();
        GridBagConstraints config = new GridBagConstraints();
        config.fill = GridBagConstraints.BOTH;
        config.weighty = 1;
        config.insets = new Insets(2,5,2,5);
        
        Font fnt_funcionario = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        
        funcionarioPanel.setLayout(funcionario_layout);
        funcionarioPanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "Dados do Funcionario",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                fnt_funcionario));
        
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.weightx = 0;
        funcionarioPanel.add(lab_nome, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        funcionarioPanel.add(txf_nome, config);
        
        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.weightx = 0;
        funcionarioPanel.add(lab_cpf, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        funcionarioPanel.add(txf_cpf, config);
        
        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 1;
        config.weightx = 0;
        funcionarioPanel.add(lab_sal, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        funcionarioPanel.add(txf_sal, config);
        
        config.gridy = 3;
        config.gridx = 0;
        config.gridwidth = 2;
        JButton but_add = new JButton("Adicionar");
        but_add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ((JanelaPosto)principal).getSimulador().adicionarFuncionario(
                            txf_nome.getText(), txf_cpf.getText(), txf_sal.getText());
                    ((JanelaPosto)principal).getFrameTexto().print("Funcionario adicionado!");
                    dispose();
                } catch (PessoaJaCadastradaException exp) {
                    ((JanelaPosto)principal).getFrameTexto().print(exp.getMessage());
                }
            }
        });
        funcionarioPanel.add(but_add, config);
        
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
        funcionarioPanel.add(but_cancel, config);
        
        this.getContentPane().add(funcionarioPanel);
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
