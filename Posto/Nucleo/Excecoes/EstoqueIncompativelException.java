package Nucleo.Excecoes;

import Nucleo.Estoque.TipoItem;
/**
 * Exibe alerta de incompatibilidade da renovação de estoque de determinado item.
 * 
 * @author Hugo Costa 
 * @version 1.0
 */
public class EstoqueIncompativelException extends Exception
{
    /**
     * Constructor
     */
    public EstoqueIncompativelException(TipoItem tipo)
    {
        super("O sistema não prevê a possibilizade de incluir " + tipo.getMeaning() +
              "num estoque pre-existente porém com preços diferentes entre a recarga e" +
              "o estoque atual. Aguarde até que o estoque deste item seja zerado.");
    }

}
