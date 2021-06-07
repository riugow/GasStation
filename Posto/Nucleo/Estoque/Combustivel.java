package Nucleo.Estoque;

/*
 ****************************************************************
 * PROJETO: CONTROLE SOBRE COMERCIALIZA��O EM POSTO DE COMBUST�VEL
 * Classe: Combust�vel(Classe-filha da classe �tem)
 * Projetistas: Denis / Hugo
 * vers�o 01
 * **************************************************************
 */

public class Combustivel extends Item implements Tributavel
{
    
    private double tributo;
    private TipoItem tipoCombustivel;
    
    /*
     ***********************************************************  
     * Construtor para objetos da classe Combust�vel
     * *********************************************************
     */
  
    // Estoque de combust�vel
    
    // ----> Quantidade............: Quantidade de produto em estoque 'int';
    // ----> PrecoUnitCompra.......: Valor de custo por unidade 'double'; 
    // ----> PrecoUnitVenda........: Valor de venda por unidade 'double';
     
    
    public String getTipoCombustivel()
    {
        return tipoCombustivel.getMeaning();
    }
    
    public Combustivel(TipoItem tipoCombustivel, int quantidade, double precoUnitarioCompra, double precoUnitarioVenda)
    {
        // Construtor inicializa vari�veis utilizando aquelas j� declaradas na classe m�e Item
        super(quantidade, precoUnitarioCompra, precoUnitarioVenda);
        
        this.tipoCombustivel = tipoCombustivel;
    }
    
    
    
    @Override
    public double calculaImposto(){   
        // C�lculo do imposto sobre o produto.
        // Considera sempre uma fun��o fixa para c�lculo do imposto. Altera��es s�o manipuladas diretamente no programa.
        // Vamos considerar um c�lculo fict�cio para este caso
        tributo = this.getPrecoUnitarioVenda() + 0.04*this.getPrecoUnitarioCompra();
        return tributo;
    }  
    
}
