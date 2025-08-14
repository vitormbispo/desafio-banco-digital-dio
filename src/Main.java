import java.util.List;
import java.util.Scanner;

public class Main {

    static Banco banco = new Banco("Utaí");
    static Scanner scan = new Scanner(System.in);
    static Conta conta = null;
    static Cliente cliente = null;

    public static void main(String[] args) {
        System.out.printf("=== Banco %s===\n",banco.getNome());
        cliente = login();
        System.out.println("Login realizado!");
        escolherConta();

        int opcao;
        boolean programaRodando = true;
        do {
            System.out.println("\n== Operações ==");
            System.out.println("""
                    
                    1) Sacar
                    2) Depositar
                    3) Transferir
                    4) Imprimir extrato
                    5) Trocar de conta
                    6) Logoff
                    7) Encerrar.
                    
                    """);
            System.out.print("Sua opção ->   ");
            opcao = scan.nextInt(); scan.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o valor do saque ->   ");
                    conta.sacar(scan.nextDouble());
                    scan.nextLine();
                }
                case 2 -> {
                    System.out.print("Digite o valor do depósito ->   ");
                    conta.depositar(scan.nextDouble());
                    scan.nextLine();
                }

                case 3 -> {
                    System.out.print("Digite o número da conta do recebedor ->   ");
                    int alvo = scan.nextInt();scan.nextLine();
                    Conta destino = banco.buscaCliente(alvo).orElse(null);
                    if(destino == null) System.out.println("Conta não encontrada!");
                    else {
                        System.out.print("Digite o valor da transferência ->   ");
                        conta.transferir(scan.nextDouble(),destino);
                        scan.nextLine();
                    }
                }
                case 4 -> conta.imprimirExtrato();
                case 5 -> escolherConta();
                case 6 -> {
                    cliente = login();
                    System.out.println("Login realizado!");
                    escolherConta();
                }
                case 7 -> {
                    System.out.println("Encerrando programa...");
                    programaRodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (programaRodando);
	}

    static Cliente login() {
        System.out.println("\n== Login ==");
        System.out.print("Nome de usuário->   ");
        return banco.buscaCliente(scan.nextLine());
    }

    static void escolherConta() {
        do {
            // Escolha de contas
            System.out.printf("\n== Bem-vindo, %s! ==\n",cliente.getNome());
            System.out.println("Acesse uma de suas contas:");

            List<Conta> contas = cliente.getContas();
            int ultima = 0;
            for(int i = 0; i < contas.size();i++) {
                Conta c = contas.get(i);
                System.out.printf("%s) Conta %s | Agência: %s | Nª%s\n",i+1,c instanceof ContaCorrente ? "Corrente":"Poupança",c.agencia,c.numero);
                ultima = i+1;
            }
            System.out.printf("%s) Criar nova conta\n",++ultima);
            System.out.print("\nSua escolha ->   ");
            int opcao = scan.nextInt(); scan.nextLine();

            // Criação de conta
            if(opcao == ultima) {
                criarConta();
            }
            else if (opcao > 0 && opcao < ultima) conta = contas.get(opcao-1);
            else System.out.println("Opção inválida");

        } while (conta == null);
    }

    static void criarConta() {
        System.out.println("\n== Abrindo conta ==");
        do {
            System.out.print("Conta poupança ou conta corrente? (p/c) ->   ");
            String tipoConta = scan.nextLine();
            if(tipoConta.equalsIgnoreCase("p")) {
                conta = banco.novaContaPoupanca(cliente);
                cliente.adicionarConta(conta);
                break;
            }
            else if(tipoConta.equalsIgnoreCase("c")) {
                conta = banco.novaContaCorrente(cliente);
                cliente.adicionarConta(conta);
                break;
            }
            else System.out.println("Opção inválida!");
        } while (true);
    }
}
