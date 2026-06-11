import java.util.Scanner;

class Pessoa {
     String nome;
     int id;

    public Pessoa(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getTipo() {
        return "Pessoa";
    }

    
    public String toString() {
        return "ID: " + id + " | Nome: " + nome;
    }
}

class Paciente extends Pessoa {
    private int idade;
    private String plano;

    public Paciente(String nome, int id, int idade, String plano) {
        super(nome, id);
        this.idade = idade;
        this.plano = plano;
    }

    @Override
    public String getTipo() {
        return "Paciente";
    }

    @Override
    public String toString() {
        return "[Paciente] " + super.toString() + " | Idade: " + idade + " | Plano: " + plano;
    }
}

class Dentista extends Pessoa {
    private String cro;
    private String especialidade;

    public Dentista(String nome, int id, String cro, String especialidade) {
        super(nome, id);
        this.cro = cro;
        this.especialidade = especialidade;
    }

    @Override
    public String getTipo() {
        return "Dentista";
    }

    @Override
    public String toString() {
        return "[Dentista] " + super.toString() + " | CRO: " + cro + " | Especialidade: " + especialidade;
    }
}

public class Admin {
    static Pessoa[] registros = new Pessoa[100];
    static int total = 0;

    public static void cadastrarPaciente(Scanner scanner) {
        if (total >= registros.length) {
            System.out.println("Limite de cadastros atingido.");
            return;
        }
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("Id do paciente (número): ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Plano (particular/convênio): ");
        String plano = scanner.nextLine();

        registros[total] = new Paciente(nome, id, idade, plano);
        total++;
        System.out.println("Paciente cadastrado com sucesso.");
    }

    public static void cadastrarDentista(Scanner scanner) {
        if (total >= registros.length) {
            System.out.println("Limite de cadastros atingido.");
            return;
        }
        System.out.print("Nome do dentista: ");
        String nome = scanner.nextLine();
        System.out.print("Id do dentista (número): ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("CRO: ");
        String cro = scanner.nextLine();
        System.out.print("Especialidade: ");
        String esp = scanner.nextLine();

        registros[total] = new Dentista(nome, id, cro, esp);
        total++;
        System.out.println("Dentista cadastrado com sucesso.");
    }

    public static void listarRegistros() {
        if (total == 0) {
            System.out.println("Nenhum cadastro realizado.");
            return;
        }
        System.out.println("--- Lista de cadastros ---");
        for (int i = 0; i < total; i++) {
            System.out.println((i) + " - " + registros[i].toString());
        }
    }

    public static void excluirRegistro(Scanner scanner) {
        if (total == 0) {
            System.out.println("Nenhum cadastro para excluir.");
            return;
        }
        listarRegistros();
        System.out.print("Digite o índice do registro que deseja excluir: ");
        String line = scanner.nextLine();
        int idx;
        try {
            idx = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Índice inválido.");
            return;
        }
        if (idx < 0 || idx >= total) {
            System.out.println("Índice fora do intervalo.");
            return;
        }
        for (int i = idx; i < total - 1; i++) {
            registros[i] = registros[i + 1];
        }
        registros[total - 1] = null;
        total--;
        System.out.println("Registro excluído com sucesso.");
    }

    public static void pesquisarRegistro(Scanner scanner) {
        if (total == 0) {
            System.out.println("Nenhum cadastro para pesquisar.");
            return;
        }

        System.out.println("Pesquisar por:\n0 - Voltar ao menu principal\n1 - Nome\n2 - ID");
        System.out.print("Escolha: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            System.out.print("Digite o nome a buscar: ");
            String nomeBusca = scanner.nextLine().trim();
            boolean encontrado = false;
            for (int i = 0; i < total; i++) {
                if (registros[i].nome.equalsIgnoreCase(nomeBusca)) {
                    System.out.println(i + " - " + registros[i].toString());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Nenhum registro encontrado para o nome informado.");
            }

        } else if (opt == 2) {
            System.out.print("Digite o ID a buscar: ");
            String idStr = scanner.nextLine();
            int idBusca;
            try {
                idBusca = Integer.parseInt(idStr);
            } catch (Exception e) {
                System.out.println("ID inválido.");
                return;
            }
            boolean encontrado = false;
            for (int i = 0; i < total; i++) {
                if (registros[i].id == idBusca) {
                    System.out.println(i + " - " + registros[i].toString());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Nenhum registro encontrado para o ID informado.");
            }
            else if (opt == 0) {
                return;
            }
            } else {
                System.out.println("Opção de pesquisa inválida.");
        }
    }

    public static void editarRegistro(Scanner scanner) {
        if (total == 0) {
            System.out.println("Nenhum cadastro para editar.");
            return;
        }

        listarRegistros();
        System.out.print("Digite o índice do registro que deseja editar: ");
        String line = scanner.nextLine();
        int idx;
        try {
            idx = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Índice inválido.");
            return;
        }
        if (idx < 0 || idx >= total) {
            System.out.println("Índice fora do intervalo.");
            return;
        }

        Pessoa p = registros[idx];
        System.out.println("Registro selecionado: " + p.toString());

        if (p instanceof Paciente) {
            System.out.print("Novo nome do paciente: ");
            String nome = scanner.nextLine();
            System.out.print("Novo id do paciente (número): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo plano (particular/convênio): ");
            String plano = scanner.nextLine();

            registros[idx] = new Paciente(nome, id, idade, plano);
            System.out.println("Paciente atualizado com sucesso.");

        } else if (p instanceof Dentista) {
            System.out.print("Novo nome do dentista: ");
            String nome = scanner.nextLine();
            System.out.print("Novo id do dentista (número): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo CRO: ");
            String cro = scanner.nextLine();
            System.out.print("Nova especialidade: ");
            String esp = scanner.nextLine();

            registros[idx] = new Dentista(nome, id, cro, esp);
            System.out.println("Dentista atualizado com sucesso.");

        } else {
            System.out.println("Tipo de registro desconhecido. Não é possível editar.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n====== Clínica Odontológica======");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Cadastrar Dentista");
            System.out.println("3 - Listar cadastros");
            System.out.println("4 - Excluir cadastro");
            System.out.println("5 - Pesquisar cadastro");
            System.out.println("6 - Editar cadastro");
            System.out.println("====================================");
            System.out.print("Escolha: ");
            String line = scanner.nextLine();
            int opc = 0;
            try {
                opc = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opc) {
                case 1:
                    cadastrarPaciente(scanner);
                    break;
                case 2:
                    cadastrarDentista(scanner);
                    break;
                case 3:
                    listarRegistros();
                    break;
                case 4:
                    excluirRegistro(scanner);
                    break;
                case 5:
                    pesquisarRegistro(scanner);
                    break;
                case 6:
                    editarRegistro(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
