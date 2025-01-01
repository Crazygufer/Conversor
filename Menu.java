package conversor;

import java.util.Scanner;

public class Menu {
    private APIExchangeService apiService;
    private CalculadoraCambio calculadora;

    public Menu() {
        this.apiService = new APIExchangeService();
        this.calculadora = new CalculadoraCambio();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("*************************************");
        System.out.println("       Bienvenido al Conversor de Monedas");
        System.out.println("*************************************");

        while (continuar) {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. USD a CLP");
            System.out.println("2. USD a otra moneda latinoamericana");
            System.out.println("6. Ingresar valores manualmente");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    realizarConversion("USD", "CLP");
                    break;
                case 2:
                    System.out.print("Ingresa el código de la moneda destino (ejemplo: ARS, BRL): ");
                    String monedaDestino = scanner.next().toUpperCase();
                    realizarConversion("USD", monedaDestino);
                    break;
                case 6:
                    System.out.print("Ingresa la moneda origen: ");
                    String monedaOrigen = scanner.next().toUpperCase();
                    System.out.print("Ingresa la moneda destino: ");
                    monedaDestino = scanner.next().toUpperCase();
                    realizarConversion(monedaOrigen, monedaDestino);
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Gracias por usar el conversor. ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }

    private void realizarConversion(String monedaOrigen, String monedaDestino) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa el monto a convertir: ");
            double monto = scanner.nextDouble();
            double tasaCambio = apiService.obtenerTasaCambio(monedaOrigen, monedaDestino);
            double resultado = calculadora.convertir(monto, tasaCambio);
            System.out.println("El monto convertido es: " + resultado + " " + monedaDestino);
        } catch (Exception e) {
            System.err.println("Error durante la conversión: " + e.getMessage());
        }
    }
}
