package Interface; 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Nucleo.*;
import Simulador.*;
import Nucleo.Excecoes.*;

public class JanelaPosto extends JFrame
{
    private FrameTexto pan_output;
    
    private SimuladorPosto simulador;
    
    public JanelaPosto()
    {
        super("Gerenciador de Vendas de Posto de Combustiveis");
        
        this.simulador = new SimuladorPosto();

        this.setContentPane(carregarPainel());
        this.setJMenuBar(carregarMenu());
        
        this.pack();
        
        this.setMaximumSize(this.getSize());  
        this.setMinimumSize(this.getSize());
        
        this.setVisible(true);
        
        pan_output.print("Bom dia, chefe! Sistema pronto");
    }

    private JPanel carregarPainel()
    {
        JPanel pan_main = new JPanel();
        
        GridBagLayout posto_layout = new GridBagLayout();
        GridBagConstraints config = new GridBagConstraints();
        pan_main.setLayout(posto_layout);

        //Adicionando subpaineis ao painel principal
        config.fill = GridBagConstraints.BOTH;
        config.gridx = 0;
        config.gridy = 0;
        
        pan_output = new FrameTexto();
        
        config.fill = GridBagConstraints.BOTH;
        config.gridx = 0;
        config.gridy = 1;
        config.weighty = 1;
        pan_main.add(pan_output, config);

        return pan_main;
    }

    private JMenuBar carregarMenu()
    {
        //Barras de menus são compostas por menus
        JMenuBar mbr_barra = new JMenuBar();

        //Menus podem ser compostos por menuitens e submenus
        JMenu mnu_gerenciar = new JMenu("Gerenciar");
        JMenu mnu_arquivo = new JMenu("Arquivo");
        JMenu mnu_relatorio = new JMenu("Relatório");

        JMenuItem mit_simular = new JMenuItem("Simular Vendas.");
        JMenuItem mit_sair = new JMenuItem("Sair");
        JMenuItem mit_relatClientes = new JMenuItem("Exibir Clientes");
        JMenuItem mit_relatPosto = new JMenuItem("Exibr dados do posto");

        JMenuItem mit_addCliente = new JMenuItem("Adicionar Cliente...");
        JMenuItem mit_addItem = new JMenuItem("Adicionar Item...");
        JMenuItem mit_addClienteLote = new JMenuItem("Adicionar Cliente em lote");
        JMenuItem mit_addFuncionario = new JMenuItem("Adicionar Funcionario...");

        JFrame principal = this;
        
        mit_addCliente.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FrameCliente clienteDialog = new FrameCliente(principal);
            }
        });
        
        mit_addItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FrameItem itemDialog = new FrameItem(principal);
            }
        });
        
        mit_addClienteLote.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object[] opt_cliente = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                                        12, 13, 14, 15, 16, 17, 18, 19, 20};
                
                int opcao = (Integer)JOptionPane.showInputDialog(
                        new JFrame(), "Quantos clientes deseja adicionar?",
                        "Quantidade de clientes", JOptionPane.PLAIN_MESSAGE,
                        null, opt_cliente, 1);
                        
                if(opcao == JOptionPane.CLOSED_OPTION)
                {
                    pan_output.print("Operação cancelada!");
                } else {
                    try {
                        simulador.adicionarClientesEmLote(opcao);
                        pan_output.print("Clientes cadastrados!");
                    } catch (LimiteMaximoDeCadastrosAlcancadoException exp) {
                        pan_output.print(exp.getMessage());
                    }
                }
            }
        });

        
        mit_addFuncionario.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FrameFuncionario funcionarioDialog = new FrameFuncionario(principal);
            }
        });
        
        mit_simular.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    pan_output.print(simulador.iniciarSimulacao());
                } catch (Exception exp) {
                    pan_output.print(exp.getMessage());
                }
            }
        });
        
        mit_sair.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        
        mit_relatClientes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pan_output.print(simulador.imprimeDadosClientes());
            }
        });
        
        mit_relatPosto.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pan_output.print(simulador.imprimeDadosPosto());
            }
        });

        mnu_gerenciar.add(mit_addCliente);
        mnu_gerenciar.add(mit_addClienteLote);
        mnu_gerenciar.add(mit_addFuncionario);
        mnu_gerenciar.add(mit_addItem);

        mnu_arquivo.add(mit_simular);
        mnu_arquivo.add(mit_sair);
        
        mnu_relatorio.add(mit_relatClientes);
        mnu_relatorio.add(mit_relatPosto);

        //Adicionando menu à barra
        mbr_barra.add(mnu_arquivo);
        mbr_barra.add(mnu_gerenciar);
        mbr_barra.add(mnu_relatorio);

        return mbr_barra;
    }
    
    public FrameTexto getFrameTexto() {
        return pan_output;
    }
    
    public SimuladorPosto getSimulador() {
        return simulador;
    }
}