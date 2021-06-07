package Nucleo.Estoque;

/*
 ****************************************************************
 * PROJETO: CONTROLE SOBRE COMERCIALIZAÇÂO EM POSTO DE COMBUSTÍVEL
 * Classe: Alimento (Classe-filha da classe Item)
 * Projetistas: Denis / Hugo
 * vers�o 01
 * **************************************************************
 */


public class Alimento extends Item implements Tributavel
{
    /*
    ***********************************************************  
    * Construtor para objetos da classe Alimento
    * *********************************************************
    */
    
    private double tributo;

    
    // Estoque de alimento
    
    // ----> Quantidade............: Quantidade de produto em estoque 'int';
    // ----> PrecoUnitCompra.......: Valor de custo por unidade 'double'; 
    // ----> PrecoUnitVenda........: Valor de venda por unidade 'double';
    // ----> tributo...............: Valor do imposto cobrado 'double';


      
    public Alimento(int quantidade, double precoUnitarioCompra, double precoUnitarioVenda)
    {
        // Construtor inicializa vari�veis utilizando aquelas j� declaradas na classe m�e Item
        super(quantidade, precoUnitarioCompra, precoUnitarioVenda);
    }
    
    
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