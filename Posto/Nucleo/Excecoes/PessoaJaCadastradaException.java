package Nucleo.Excecoes;


/**
 * Emite um alerta quando ocorre uma tentativa de cadastrar um CPF
 * pre-existente.
 * 
 * @author Hugo Costa / Denis Biassi 
 * @version 1.0
 */
public class PessoaJaCadastradaException extends Exception
{
    /**
     * Construtor que permite a customizacao da mensagem
     * de excecao
     * 
     * @param  tipoPessoa   identifica o tipo de pessoa cujo
     *                      cadastro foi duplicado: "cliente"
     *                      ou "funcionario" 
     */
    public PessoaJaCadastradaException(String tipoPessoa)
    {
        super("Este CPF foi encontrado no cadastro de outro " + tipoPessoa +
              ". Verifique o numero informado.");
    }
}
