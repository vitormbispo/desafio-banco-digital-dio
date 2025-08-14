import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {

	private String nome;
	private List<Conta> contas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

    public Banco(String nome) {
        this.nome = nome;
        contas = new ArrayList<>();
    }
    public ContaCorrente novaContaCorrente(Cliente cliente) {
        ContaCorrente nova = new ContaCorrente(cliente);
        contas.add(nova);
        return nova;
    }

    public ContaPoupanca novaContaPoupanca(Cliente cliente) {
        ContaPoupanca nova = new ContaPoupanca(cliente);
        contas.add(nova);
        return nova;
    }

    public Cliente buscaCliente(String nome) {
        Optional<Conta> encontrado = contas.stream()
                .filter(c -> c.getNomeCliente().equals(nome))
                .findFirst();
        return encontrado.isPresent() ? encontrado.get().cliente : new Cliente(nome);
    }

    public Optional<Conta> buscaCliente(int numero) {
        return contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst();
    }
}
