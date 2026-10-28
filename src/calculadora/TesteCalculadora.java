package calculadora;

public class TesteCalculadora {

    static int total = 0;
    static int aprovados = 0;

    static void testar(Calculadora calc, String nome, int a, int b, String op, int esperado) {
        System.out.println("[Teste] " + nome);
        System.out.println("   Entradas : a=" + a + ", b=" + b + ", op=\"" + op + "\"");

        int obtido = calc.calc(a, b, op);
        boolean passou = (obtido == esperado);

        total++;
        if (passou) {
            aprovados++;
        }

        System.out.println("   Esperado : " + esperado);
        System.out.println("   Obtido   : " + obtido);
        System.out.println("   Situação : " + (passou ? "PASSOU" : "FALHOU"));
        System.out.println();
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println("===== TESTES DA CALCULADORA =====\n");

        testar(calc, "Soma", 2, 3, "+", 5);
        testar(calc, "Subtração", 10, 4, "-", 6);
        testar(calc, "Subtração com resultado negativo", 3, 8, "-", -5);
        testar(calc, "Multiplicação", 3, 5, "*", 15);
        testar(calc, "Divisão exata", 8, 2, "/", 4);
        testar(calc, "Divisão inteira (7 / 2)", 7, 2, "/", 3);
        testar(calc, "Divisão por zero", 8, 0, "/", 0);
        testar(calc, "Operação inválida", 5, 5, "x", 0);
        testar(calc, "Operação nula", 5, 5, null, 0);

        System.out.println("===== RESULTADO: " + aprovados + "/" + total + " testes aprovados =====");
        if (aprovados != total) {
            System.exit(1);
        }
    }
}
