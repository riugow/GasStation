package Nucleo.RecursosHumanos;

public class Funcionario extends Pessoa {
	private double salario;
	
    public Funcionario(String nome, String cpf, Double salario) {
        super(nome, cpf);
        this.salario = salario;
    }

    @Override	
    public String imprimeDados() {
		return "O funcionario " + getNome() +
               ", portador do CPF no. " + getCpf() +
               ", recebe R$" + String.format("%.2f", salario) +
               ".";
	}
}