package calculadora;

public class Calculadora {

    public int r = 0;

    public int calc(int a, int b, String op) {

        // Calculadora
        if ("+".equals(op)) {
            r = a + b;
        } else if ("-".equals(op)) {
            r = a - b;
        } else if ("*".equals(op)) {
            r = a * b;
        } else if ("/".equals(op)) {
            if (b != 0) {
                r = a / b;
            } else {
                System.out.println("Erro: divisão por zero, operação irregular");
                r = 0;
            }
        } else {
            System.out.println("Operação inválida");
            r = 0;
        }

        System.out.println("Resultado = " + r);
        return r;
    }
}
