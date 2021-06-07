package Nucleo.Excecoes;


/**
 * Alerta quando o sistema chegar ao máximo de usuarios
 * permitidos para seguir com a inclusao de pessoas em lote
 * 
 * @author Hugo Costa / Denis Biassi
 * @version 1.0
 */
public class LimiteMaximoDeCadastrosAlcancadoException extends Exception
{
    /**
     * Construtor
     */
    public LimiteMaximoDeCadastrosAlcancadoException(String tipo)
    {
        super("O sistema nao permite a inclusão automatica de " + tipo +
              "quando o total cadastrado alcança 1000 " + tipo + ".");
    }
}
