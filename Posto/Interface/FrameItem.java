package Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import Nucleo.Excecoes.*;
import Nucleo.Estoque.*;

/**
 * Tela para cadastro de item em estoque.
 * 
 * @author Hugo Costa / Denis Biassi
 * @version 1.0
 */
public class FrameItem extends JDialog
{
   private JTextField txf_qtde, txf_pCompra, txf_pVenda;

    /**
     * Construtor
     */
    public FrameItem(JFrame principal)
    {
        super(principal, "Adicionar novo item", true);
        
        JPanel itemPanel = new JPanel();
        JLabel lab_tipo, lab_qtde, lab_pCompra, lab_pVenda;
        JComboBox<String> cmb_item;
        
        lab_tipo = new JLabel("Tipo:");
        String[] opt_item = new String[TipoItem.size];
        
        for ( TipoItem t : TipoItem.values())
        {
            opt_item[t.ordinal()] = t.getMeaning();
        }
        cmb_item = new JComboBox(opt_item);
        
        lab_qtde = new JLabel("Quantidade:");
        txf_qtde = new JTextField(10);
        txf_qtde.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        
        lab_pCompra = new JLabel("Preço de compra (R$):");
        txf_pCompra = new JTextField(10);
        txf_pCompra.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        lab_pVenda = new JLabel("Preço de venda (R$):");
        txf_pVenda = new JTextField(10);
        txf_pVenda.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
        
        GridBagLayout item_layout = new GridBagLayout();
        GridBagConstraints config = new GridBagConstraints();
        config.fill = GridBagConstraints.BOTH;
        config.weighty = 1;
        config.insets = new Insets(2,5,2,5);
        
        Font fnt_item = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        
        itemPanel.setLayout(item_layout);
        itemPanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "Item a adicionar",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                fnt_item));
        
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.weightx = 0;
        itemPanel.add(lab_tipo, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        itemPanel.add(cmb_item, config);
        
        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.weightx = 0;
        itemPanel.add(lab_qtde, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        itemPanel.add(txf_qtde, config);
        
        config.gridx = 0; 
        config.gridy = 2;
        config.gridwidth = 1;
        config.weightx = 0;
        itemPanel.add(lab_pCompra, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        itemPanel.add(txf_pCompra, config);
        
        config.gridx = 0; 
        config.gridy = 3;
        config.gridwidth = 1;
        config.weightx = 0;
        itemPanel.add(lab_pVenda, config);
        config.gridx = 1;
        config.gridwidth = 4;
        config.weightx = 1;
        itemPanel.add(txf_pVenda, config);
        
        config.gridy = 4;
        config.gridx = 0;
        config.gridwidth = 2;
        JButton but_add = new JButton("Adicionar");
        but_add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ((JanelaPosto)principal).getSimulador().adicionarItem(
                            cmb_item.getSelectedIndex(), txf_qtde.getText(),
                            txf_pCompra.getText(), txf_pVenda.getText());
                    ((JanelaPosto)principal).getFrameTexto().print("Item adicionado!");
                    dispose();
                } catch (Exception exp) {
                    ((JanelaPosto)principal).getFrameTexto().print(exp.getMessage());
                }
            }
        });
        itemPanel.add(but_add, config);
        
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
        itemPanel.add(but_cancel, config);
        
        this.getContentPane().add(itemPanel);
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
