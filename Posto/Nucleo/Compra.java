package Nucleo;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import Nucleo.Estoque.*;

/**
 * Define uma compra de produtos do posto pelo cliente.
 * 
 * @author Hugo Costa / Denis Biassi
 * @version 1.0
 */

public class Compra
{
    private Calendar data;
    private List<Item> itens;

    public Compra()
    {
        this.data = Calendar.getInstance();
        itens = new ArrayList<Item>();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Calendar getData()
    {
        return data;
    }
    
    public void adicionaItem(Item item)
    {
        itens.add(item);
    }
    
    public String imprimeCupomFiscal()
    {
        String mensagem = "";
        int n = 1;
        double total = 0.0;
        
        for (Item i : itens) {
            mensagem += String.format("Item #%0d", n);
            if (i instanceof Alimento)
                mensagem += String.format("Alimento - %d unid", i.getQuantidade());
            if (i instanceof Bebida)
                mensagem += String.format("Bebida - %d unid", i.getQuantidade());
            if (i instanceof Combustivel)
                mensagem += String.format("Combustivel - %d litros", i.getQuantidade());
            mensagem += String.format(" - R$ %.2f\n", i.getPrecoUnitarioVenda() * i.getQuantidade());
            total += i.getPrecoUnitarioVenda() * i.getQuantidade();
            n++;
        }
        mensagem += String.format("\nTOTAL: R$ %.2f", total);
        
        return mensagem;
    }
}
