package Nucleo;

/**
 * Gerencia os dados e funções do posto.
 * 
 * @author Hugo Costa, Denis Biassi 
 * @version 1.0
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import Nucleo.RecursosHumanos.*;
import Nucleo.Excecoes.*;
import Nucleo.Estoque.*;

public class Posto
{
    private List<Cliente> clientes;    // clientes cadastrados no sistema do posto
    private List<Funcionario> funcionarios;  // funcionarios do posto
    private int prazoMaximoCompras;  // prazo das compras, para permitir o calculo do desconto pela fidelidade
    private List<Item> estoque;  // estoque de produtos do posto
    double capital;  // dinheiro 
    private Compra compra;
    
    public Posto()
    {
        clientes = new ArrayList<Cliente>();
        funcionarios = new ArrayList<Funcionario>();
        capital = 50000.0;
        estoque = new ArrayList<Item>();
        prazoMaximoCompras = 12;
        estoque.add(new Alimento(0, 0.0, 0.0));
        estoque.add(new Bebida(0, 0.0, 0.0));
        estoque.add(new Combustivel(TipoItem.tiCombGasolinaComum, 0, 0.0, 0.0));
        estoque.add(new Combustivel(TipoItem.tiCombGasolinaAditivada, 0, 0.0, 0.0));
        estoque.add(new Combustivel(TipoItem.tiCombAlcool, 0, 0.0, 0.0));
        estoque.add(new Combustivel(TipoItem.tiCombDiesel, 0, 0.0, 0.0));
        compra = null;
    }

    /**
     * Adiciona um cliente, ou alerta a existência de outro cliente com
     * o mesmo CPF
     * 
     * @param  nome   nome do cliente
     * @param  cpf    CPF do cliente
     */
    public void adicionaCliente(String nome, String cpf)
                throws PessoaJaCadastradaException {
        Cliente cliente = new Cliente(nome, cpf, prazoMaximoCompras);
        for (Cliente c : clientes) {
            if ( c.getCpf().equals(cpf) ) {
                throw new PessoaJaCadastradaException("cliente");
            }
        }
        clientes.add(cliente);
    }
    
    /**
     * Adiciona um funcionario, ou alerta a existência de outro funcionario
     * com o mesmo CPF
     * 
     * @param  nome   nome do funcionario
     * @param  cpf    CPF do funcionario
     * @param salario    salario do funcionario
     */
    public void adicionaFuncionario(String nome, String cpf, Double salario)
                throws PessoaJaCadastradaException {
        
        for (Funcionario f : funcionarios) {
            if ( f.getCpf().equals(cpf) ) {
                throw new PessoaJaCadastradaException("funcionario");
            }
        }
        Funcionario funcionario = new Funcionario(nome, cpf, salario);
        funcionarios.add(funcionario);
    }
    
    /**
     * Adiciona um item em estoque
     * 
     * @param  tipo   tipo do item a ter o estoque renovado
     * @param  qtde    quantidade de itens adicionados
     * @param  precoCompra    preco de compra
     * @param  precoVenda    preco de venda
     */
    public void adicionarItem(int tipo, String qtde, String precoCompra, String precoVenda) throws EstoqueIncompativelException
    {
        TipoItem tipoItem = TipoItem.getVal(tipo);
        Item i = estoque.get(tipo);
        int quantidade = Integer.parseInt(qtde);
        Double compra = Double.parseDouble(precoCompra);
        Double venda = Double.parseDouble(precoVenda);
        if ( (i.getQuantidade() == 0) ||
             ( compra.equals(i.getPrecoUnitarioCompra()) && venda.equals(i.getPrecoUnitarioVenda()) )
           ) {
          
            if (tipo == TipoItem.tiAlimento.ordinal())
                estoque.set(tipo, new Alimento(quantidade + i.getQuantidade(), compra, venda));
            else if (tipo == TipoItem.tiBebida.ordinal())
                estoque.set(tipo, new Bebida(quantidade + i.getQuantidade(), compra, venda));
            else 
                estoque.set(tipo, new Combustivel(tipoItem, quantidade + i.getQuantidade(), compra, venda));
            capital -= (quantidade + i.getQuantidade()) * compra;
        } else {
            throw new EstoqueIncompativelException(tipoItem);
        }
    }
    
    /**
     * Adiciona um item a ser comprado
     * 
     * @param  tipo   tipo do item a ser comprado
     * @param  qtde    quantidade de itens comprados
     */
    public void adicionarItemCompra(int tipo, int qtde) throws EstoqueIncompativelException
    {
        if (compra == null) {
            compra = new Compra();
        }        
        TipoItem tipoItem = TipoItem.getVal(tipo);
        Item i = estoque.get(tipo);
        int quantidade = qtde;
        
        if (quantidade > i.getQuantidade() ) {
            quantidade = i.getQuantidade();
        }
        if (tipo == TipoItem.tiAlimento.ordinal())
            compra.adicionaItem(new Alimento(quantidade, i.getPrecoUnitarioCompra(), i.getPrecoUnitarioVenda()));
        else if (tipo == TipoItem.tiBebida.ordinal())
            compra.adicionaItem(new Bebida(quantidade, i.getPrecoUnitarioCompra(), i.getPrecoUnitarioVenda()));
        else 
            compra.adicionaItem(new Combustivel(tipoItem, quantidade, i.getPrecoUnitarioCompra(), i.getPrecoUnitarioVenda()));
        
        capital += i.realizaVenda(quantidade);
    }
    
    
    public void finalizaCompra(Cliente c) {
        c.adicionaCompra(compra);
        
        compra = null;
    }
     /**
     * Retorna um cliente cadastrado, dado o seu CPF 
     * 
     * @param  cpf   CPF do cliente
     * @return  Cliente     o cliente
     */
    public Cliente getCliente(String cpf) {
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
            }
        }        
        return cliente;
    }
    
    /**
     * Informa a quantidade de clientes cadastrada
     * 
     * @return  quantidade de clientes cadastrada
     */
    public int getQuantidadeClientes() {
        return clientes.size();
    }
    
    /**
     * Imprime os dados dos clientes
     * 
     * @return    dados dos clientes
     */
    public String imprimeDadosClientes() {
        String dados = "";
        
        if (clientes.size() == 0) {
            dados = "Nenhum cliente cadastrado!";
        } else {
            for (Cliente c : clientes) {
                dados += "\n" + c.imprimeDados();
            }
        }
        return dados;
    }
    
    /**
     * Define um novo prazo de validade para as compras dos clientes
     * 
     * @param    novo prazo
     */
    public void setPrazoMaximoCompras(int prazo) {
        prazoMaximoCompras = prazo;
    }
    
    /**
     * Imprime os dados do posto
     * 
     * @return    dados do posto
     */
    public String imprimeDadosPosto() {
        String dados = String.format("Capital atual: R$%.2f", capital);
        
        dados += "\nEm estoque, temos: ";
        
        for (Item i : estoque) {
            if (i instanceof Alimento)
                dados += String.format("\n - %d unidades de alimento.", i.getQuantidade());
            if (i instanceof Bebida)
                dados += String.format("\n - %d unidades de bebida.", i.getQuantidade());
            if (i instanceof Combustivel) 
                dados += String.format("\n - %d unidades de combustivel do tipo %s.",
                                       i.getQuantidade(),
                                       ((Combustivel)i).getTipoCombustivel() );
        }
        return dados;
    }
}
