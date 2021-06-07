package Nucleo.Estoque;

/*
 ****************************************************************
 * PROJETO: CONTROLE SOBRE COMERCIALIZAÇÃO EM POSTO DE COMBUSTIVEL
 * Classe: Item (Classe-mãe dos produtos comercializados)
 * Projetistas: Denis / Hugo
 * versão 01
 * **************************************************************
 */

import java.util.Date;

public class Item
{
    
    /*
    ***********************************************************  
    * Construtor para objetos da classe Item
    * *********************************************************
    */

    private int quantidade;
    private double valorTotalVenda;
    protected double precoUnitarioCompra;
    private double precoUnitarioVenda;
    
    // ----> Classe-mãe criada para classes de produtos comercializados
    
    // ----> quantidade................: Quantidade de produto em estoque 'int';
    // ----> precoUnitarioCompra.......: Valor de custo por unidade 'double'; 
    // ----> precoUnitarioVenda........: Valor de venda por unidade 'double';
    
    public Item(int quantidade, double precoUnitarioCompra, double precoUnitarioVenda)
    {
        //quantidade do item de estoque
        this.quantidade = quantidade;
        //Preco unit�rio de compra
        this.precoUnitarioCompra = precoUnitarioCompra;
        //Preco unit�rio de venda
        this.precoUnitarioVenda = precoUnitarioVenda;   
    
    }
    
    /* 
     * Getters 
     */
    
    public int getQuantidade(){
        return quantidade;
    }
  
    public double getPrecoUnitarioVenda(){
        return precoUnitarioVenda;
    }
    
    public double getPrecoUnitarioCompra(){
        return precoUnitarioCompra;
    }

    
    /*
     * processarVenda()
     * Funcao: processar uma venda dos produtos em estoque, para isso cada vez
     * que o método for executado, a quantidade de �tens diminui e retorna true ou false
     * de acordo com a quantidade em estoque
    */  
   
    public double realizaVenda(int qtde)
   {
        
        
         
     
        if (quantidade > qtde){
            quantidade -= qtde;
        }else{            
            qtde = quantidade;
        }
        valorTotalVenda = this.getPrecoUnitarioVenda()*qtde;
                // throw new EstoqueInsuficiente("Infelizmente n�o temos estoque suficiente");
        return valorTotalVenda;
   
   }   
}
