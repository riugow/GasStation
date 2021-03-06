package Simulador;

import Nucleo.*;
import Nucleo.Excecoes.*;
import Nucleo.Estoque.*;
import Interface.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Gerencia as funcoes de simulacao do sistema do posto.
 * 
 * @author Hugo Costa / Denis Biassi
 * @version 1.0
 */
public class SimuladorPosto
{
    private Posto posto;
    private int periodoSimulacao;
    private List<String> cpfsClientes;
    private List<String> cpfsFuncionarios;

    /**
     * Construtor 
     */
    public SimuladorPosto()
    {
        posto = new Posto();
        periodoSimulacao = 12;
        posto.setPrazoMaximoCompras(periodoSimulacao);
        cpfsClientes = new ArrayList<String>();
        cpfsFuncionarios = new ArrayList<String>();
    }
    
    /**
     * Adiciona um cliente
     * 
     * @param  nome   nome do cliente
     * @param  cpf    CPF do cliente
     */
    public void adicionarCliente(String nome, String cpf) throws PessoaJaCadastradaException
    {
        posto.adicionaCliente(nome, cpf);
        cpfsClientes.add(cpf);
    }
    
    /**
     * Adiciona um funcionario
     * 
     * @param  nome   nome do funcionario
     * @param  cpf    CPF do funcionario
     * @param  salario   salario do funcionario
     */
    public void adicionarFuncionario(String nome, String cpf, String salario)
                throws PessoaJaCadastradaException
    {
        posto.adicionaFuncionario(nome, cpf, Double.parseDouble(salario));
        cpfsFuncionarios.add(cpf);
    }
    
    /**
     * Adiciona um item em estoque
     * 
     * @param  tipo   tipo do item
     * @param  cpf    quantidade de itens a adicionar
     * @param  precoCompra   preco de compra do item
     * @param  precoVenda   preco de venda do item
     */
    public void adicionarItem(int tipo, String qtde, String precoCompra, String precoVenda)
                throws EstoqueIncompativelException
    {
        posto.adicionarItem(tipo, qtde, precoCompra, precoVenda);        
    }
    
    /**
     * Tenta adicionar uma determinada quantidade de clientes, criando um nome e
     * um numero de CPF para eles.
     * 
     * @param  nome   nome do cliente
     * @param  cpf    CPF do cliente
     */
    public void adicionarClientesEmLote(int qtde)
                throws LimiteMaximoDeCadastrosAlcancadoException {
        int numero = posto.getQuantidadeClientes();
        String nome = "";
        String cpf = "";
        
        for (int i = 1; i <= qtde; i++) {
            // cria um nome com o formato "ClienteNN"
            nome = String.format("Cliente%d", numero + i);
            // cria um n??mero de CPF com a m??scara padr??o brasileiro (NNN.NNN.NNN-NN)
            // Esta forma de criar permite no m??ximo 1000 numeros diferentes
            cpf = String.format("%03d.%03d.%03d-%02d",
                                (numero + i)%1000,
                                (numero + i)%1000,
                                (numero + i)%1000,
                                (numero + i)%100);
            try {
                posto.adicionaCliente(nome, cpf);
                cpfsClientes.add(cpf);
            } catch (PessoaJaCadastradaException exp) {
                // Como estamos criando um CPF nesta pr??pria funcao, ignoramos 
                // o alerta de duplicidade e aumentamos o limite em uma unidade.
                qtde++;
            }
            if (qtde >= 1000) {
                throw new LimiteMaximoDeCadastrosAlcancadoException("clientes");
            }
        }
    }
    
    public String iniciarSimulacao() throws EstoqueIncompativelException
    {
        if (cpfsClientes.size() == 0)
            return "Simula????o cancelada! Cadastre alguns clientes antes de come????-la.";
//        if (cpfsFuncionarios.size() == 0)
//            return "Simula????o cancelada! Cadastre pelo menos um funcion??rio antes de come????-la.";

        String mensagem = "Inicio da simulacao";
        Random rand = new Random();

        // inicio da simula????o de compras
        // repete por um per??odo em meses. Padr??o ?? 12
        for (int i = 0; i < periodoSimulacao; i++) {
            // um cliente qualquer
            int cli = rand.nextInt(cpfsClientes.size());
            
            // repete para dois produtos
            for (int j = 0; j < 2; j++) {
                // escolhe o produto
                int ti = rand.nextInt(TipoItem.size);
                
                // e a quantidade
                int qtde = (rand.nextInt(10) + 4) * 5;
                
                posto.adicionarItemCompra(ti, qtde);
            }
            
            posto.finalizaCompra(posto.getCliente(cpfsClientes.get(cli)));
            
            mensagem += String.format("\nCompra do %do. cliente finalizada.", cli);
        }
               
        mensagem += "\nSimula????o finalizada!";
        
        return mensagem;
    }
    
    public String imprimeDadosClientes() {
        return posto.imprimeDadosClientes();
    }
    
    public String imprimeDadosPosto() {
        return posto.imprimeDadosPosto();
    }
}
