import java.util.Scanner;

public class CoversorApp {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner input = new Scanner(System.in);


        while (true) {
            System.out.println("\n📋 MENÚ PRINCIPAL:");
            System.out.println("1. Dólar a Peso Argentino (USD → ARS)");
            System.out.println("2. Peso Argentino a Dólar (ARS → USD)");
            System.out.println("3. Euro a Peso Argentino (EUR → ARS)");
            System.out.println("4. Peso Argentino a Euro (ARS → EUR)");
            System.out.println("5. Dólar a Real Brasileño (USD → BRL)");
            System.out.println("6. Euro a Dólar (EUR → USD)");
            System.out.println("7. Conversión personalizada");
            System.out.println("8. Salir");
            System.out.print("👉 Seleccione una opción (1-8): ");

            try {
                int opcion = Integer.parseInt(input.nextLine());

                if (opcion == 8) {
                    System.out.println("\n ¡Gracias por usar el conversor! ");
                    break;
                }

                if (opcion == 7) {
                    continue;
                }

                double cantidad = input.nextDouble();
                switch (opcion) {
                    case 1:
                        controller.deDolarAPesoArgentino(cantidad);
                        break;
                    case 2:
                        controller.dePesoArgentinoADolar(cantidad);
                        break;
                    case 3:
                        controller.deEuroAPesoArgentino(cantidad);
                        break;
                    case 4:
                        controller.dePesoArgentinoAEuro(cantidad);
                        break;
                    case 5:
                        controller.deDolarAReal(cantidad);
                        break;
                    case 6:
                        controller.deEuroADolar(cantidad);
                        break;
                    case 7: {
                        System.out.print(" Ingrese moneda base (ejemplo: USD): ");
                        String base = input.nextLine().trim();
                        System.out.print(" Ingrese moneda a covertir (ejempl: EUR): ");
                        String objetivo = input.nextLine().trim();
                        controller.convertirMoneda(base, objetivo, cantidad);
                    }
                }



            }catch (NumberFormatException e) {
            }
        }
        }
    }
