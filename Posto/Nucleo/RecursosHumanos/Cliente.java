package Nucleo.RecursosHumanos;

import java.util.List;
import Nucleo.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/**
 * Define o cliente do posto.
 * 
 * @author Hugo Costa, Denis Biassi 
 * @version 1.0
 */
public class Cliente extends Pessoa {
    private List<Compra> compras;  // compras realizadas pelo cliente
    private int prazoMaximoCompras;  // prazo de validade das compras, em meses
	
    /**
     * Construtor
     */
	public Cliente(String nome, String cpf, int prazoMaximoCompras) {
		super(nome, cpf);
		this.prazoMaximoCompras = prazoMaximoCompras;
        compras = new ArrayList<Compra>();
	}
	
	/**
     * Imprime dados do cliente
     * 
     * @param  y   a sample parameter for a method
     * @return     Um texto com os dados do cliente  
     */
	@Override	
	public String imprimeDados() {
	    // antes de informar as compras realizadas, remove as vencidas
	    removeComprasVencidas();
	    
		return "O cliente " + this.getNome() +
               ", portador do CPF no. " + this.getCpf() +
               ", realizou " + String.format("%d", compras.size()) +
               " compras nos últimos " + String.format("%d", prazoMaximoCompras) +
               " meses.";
	}
	
	/**
     * Retorna a quantidade de compras realizadas dentro de um
     * intervalo de meses informado
     * 
     * @param  ultimosMeses   quantidade de meses do prazo
     * @return     quantidade de compras realizadas no prazo
     */
	public int quantidadeCompras(int ultimosMeses) {
	    return processaComprasNoPrazo(ultimosMeses, false);
	}
	
	/**
     * Adiciona uma compra realizada pelo cliente
     * 
     * @param  compra   compra realizada
     */
	public void adicionaCompra(Compra compra) {
	    compras.add(compra);
	}

	/**
     * Remove compras realizadas fora do prazo maximo
     */
	private void removeComprasVencidas() {
	    processaComprasNoPrazo(prazoMaximoCompras, true);
	}
	
	/**
     * Verifica um prazo das compras realizadas pelo cliente e contabiliza
     * as que são válidas. Quando solicitado, remove as vencidas.
     * 
     * @param  przaoMeses   quantidade de meses do prazo
     * @param  removeAntigas   indica se as compras fora do prazo devem ou não ser removidas
     * @return     quantidade de compras realizadas no prazo
     */
	private int processaComprasNoPrazo(int prazoMeses, boolean removeAntigas) {
	    Calendar dataMaisAntiga = Calendar.getInstance();
        dataMaisAntiga.add(Calendar.MONTH, (-1) * prazoMeses);
        
        int qtdeComprasNoPrazo = 0;
        
	    for (Compra c:compras) {
	        Calendar dataCompra = Calendar.getInstance();
	        dataCompra.setTime(c.getData().getTime());
	        
	        if (dataCompra.before(dataMaisAntiga)) {
	            if (removeAntigas)
	                compras.remove(c);
	        } else {
	            qtdeComprasNoPrazo++;
	        }
	    }
	    return qtdeComprasNoPrazo;
	}
}