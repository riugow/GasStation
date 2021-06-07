package Nucleo.Estoque;

/*
 ****************************************************************
 * PROJETO: CONTROLE SOBRE COMERCIALIZAÇÃO EM POSTO DE COMBUSTÍVEL
 * Classe: Bebida (Classe-filha da classe �tem)
 * Projetistas: Denis / Hugo
 * vers�o 01
 * **************************************************************
 */


public class Bebida extends Item implements Tributavel
{
    
    private double tributo;
    
    /*
    ***********************************************************  
    * Construtor para objetos da classe Combust�vel
    * *********************************************************
    */
   
   
    // Estoque de bebida
    
    // ----> Quantidade............: Quantidade de produto em estoque 'int';
    // ----> PrecoUnitCompra.......: Valor de custo por unidade 'double'; 
    // ----> PrecoUnitVenda........: Valor de venda por unidade 'double';
  
      
    public Bebida(int quantidade, double precoUnitarioCompra, double precoUnitarioVenda)
    {
        // Construtor inicializa vari�veis utilizando aquelas j� declaradas na classe m�e Item
        super(quantidade, precoUnitarioCompra, precoUnitarioVenda);
    }

    // Rotina de c�lculo de venda herdada da Classe m�e Item
      
//    public void realizaVenda(double qtde){   
   
//    }

    // Rotina de c�lculo de imposto devido
    
    @Override
    public double calculaImposto(){   
        // C�lculo do imposto sobre o produto.
        // Considera sempre uma fun��o fixa para c�lculo do imposto. Altera��es s�o manipuladas diretamente no programa.
        // Vamos considerar um c�lculo fict�cio para este caso
        tributo = this.getPrecoUnitarioVenda() + 0.04*this.getPrecoUnitarioCompra();
        return tributo;
    }  
    
}