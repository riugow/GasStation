package Nucleo.Estoque;

/*
 ****************************************************************
 * PROJETO: CONTROLE SOBRE COMERCIALIZAÇÃO EM POSTO DE COMBUSTIVEL
 * Enumeration: TipoProduto(Produtos comercializados)
 * Projetistas: Denis / Hugo
 * vers�o 01
 * **************************************************************
 */

public enum TipoItem{
    tiAlimento("Alimento"),
    tiBebida("Bebida"),
    tiCombGasolinaComum("Gasolina Comum"),
    tiCombGasolinaAditivada("Gasolina Aditivada"),
    tiCombAlcool("Alcool"),
    tiCombDiesel("Diesel");
    
    private String meaning;
    public static final int size = TipoItem.values().length;
    
    private TipoItem (String meaning) {
        this.meaning = meaning;
    }
    
    public String getMeaning() {
        return meaning;
    }
    
    public static TipoItem getVal(int i) {
        for (TipoItem t : TipoItem.values()) {
            if (i == 0) 
                return t;
            else
                i--;
        }
        return null;
    }
    
    
}