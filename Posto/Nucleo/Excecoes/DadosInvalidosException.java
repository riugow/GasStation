package Nucleo.Excecoes;


/**
 * Emite um alerta para a inclusão de dados incorretos no sistema.
 * 
 * @author Hugo Costa / Denis Biassi 
 * @version 1.0
 */
public class DadosInvalidosException extends Exception
{
    /**
     * Constructor
     */
    public DadosInvalidosException()
    {
        super("Verifique os dados informados para o sistema");
    }

}
