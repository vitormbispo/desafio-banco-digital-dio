
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected String extrato;
    protected Cliente cliente;


	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
        this.extrato = "";
	}

	@Override
	public void sacar(double valor) {
		if(valor > saldo) {System.out.printf("Saldo indisponível para saque! Disponível R$%.2f\n",saldo); return;}
        saldo -= valor;
        extrato += String.format("|-| Saque realizado no valor de R$%.2f Novo saldo -> R$%.2f\n",valor,saldo);
        System.out.println("-> Saque realizado com sucesso.");
    }

	@Override
	public void depositar(double valor) {
		saldo += valor;
        extrato += String.format("|+| Depósito realizado no valor de R$%.2f. Novo saldo -> R$%.2f\n",valor,saldo);
        System.out.println("-> Depósito realizado com sucesso.");
    }

	@Override
	public void transferir(double valor, Conta contaDestino) {
        if(valor > saldo) {System.out.printf("Saldo indisponível para transferência! Disponível R$%.2f\n",saldo); return;}
        saldo -= valor;
        extrato += String.format("|-| Transferência realizado no valor de R$%.2f para %s|Nª%s. Novo saldo -> R$%.2f\n",valor,contaDestino.getNomeCliente(),contaDestino.getNumero(),saldo);
		contaDestino.saldo += valor;
        contaDestino.extrato += String.format("|+| Transferência recebida no valor de R$%.2f de %s|Nª%s. Novo saldo -> R$%.2f\n",valor,this.getNomeCliente(),this.getNumero(),contaDestino.getSaldo());
        System.out.println("-> Transferência realizada com sucesso.");
    }
    public String getNomeCliente() {return cliente.getNome();}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.printf("Titular: %s\n", this.cliente.getNome());
		System.out.printf("Agencia: %d\n", this.agencia);
		System.out.printf("Numero: %d\n", this.numero);
		System.out.printf("Saldo: R$%.2f\n", this.saldo);
        System.out.println("=== Extrato ===");
        System.out.println(extrato);
	}
}
