import java.util.Scanner;

public class Cronometro {
    private static volatile boolean rodando = false;
    private static volatile boolean sair = false;
    private static volatile int segundos = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Thread cronometroThread = new Thread(() -> {
            while (!sair) {
                try {
                    if (rodando) {
                        Thread.sleep(1000);
                        segundos++;
                        System.out.print("\rTempo: " + segundos + " segundos ");
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    System.out.println("\nCronômetro interrompido.");
                }
            }
        });

        cronometroThread.start();

        while (!sair) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Iniciar/Continuar");
            System.out.println("2. Pausar");
            System.out.println("3. Reiniciar");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    rodando = true;
                    System.out.println("Cronômetro iniciado/continuado.");
                    break;

                case 2:
                    rodando = false;
                    System.out.println("Cronômetro pausado.");
                    break;

                case 3:
                    rodando = false;
                    segundos = 0;
                    System.out.print("\rTempo: 0 segundos ");
                    System.out.println("\nCronômetro reiniciado.");
                    break;

                case 4:
                    rodando = false;
                    sair = true;
                    System.out.println("Cronômetro encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        sc.close();
    }
}
