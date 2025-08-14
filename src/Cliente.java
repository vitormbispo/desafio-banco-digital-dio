import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
    private List<Conta> contas;

    public Cliente(String nome) {
        this.nome = nome;
        contas = new ArrayList<>();
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;
        return nome.equals(cliente.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }
}
