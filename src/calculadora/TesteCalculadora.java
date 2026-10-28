package calculadora;

/**
 * Testes da classe {@link Calculadora}.
 * <p>
 * Para cada caso são mostradas as entradas utilizadas, o resultado esperado,
 * o resultado obtido e a situação (PASSOU ou FALHOU). No final aparece o
 * resumo e, se algum teste falhar, o programa termina com código 1.
 * </p>
 *
 * @author Kauã Zonta Souza
 * @version 2.0
 */
public class TesteCalculadora {

    /** Quantidade de testes executados. */
    static int total = 0;

    /** Quantidade de testes que passaram. */
    static int aprovados = 0;

    /** Classe utilitária: não deve ser instanciada. */
    private TesteCalculadora() {
    }

    /**
     * Testa o método seguro {@code calc}, que devolve um inteiro.
     *
     * @param calc     calculadora usada no teste
     * @param nome     descrição do cenário
     * @param a        primeiro número
     * @param b        segundo número
     * @param op       operador
     * @param esperado resultado esperado
     */
    static void testar(Calculadora calc, String nome, int a, int b, String op, int esperado) {
        System.out.println("[Teste] " + nome);
        System.out.println("   Entradas : a=" + a + ", b=" + b + ", op=\"" + op + "\"");

        int obtido = calc.calc(a, b, op);

        mostrarResultado(String.valueOf(esperado), String.valueOf(obtido), obtido == esperado);
    }

    /**
     * Testa o método estrito {@code calcular}, que deve lançar exceção.
     *
     * @param calc             calculadora usada no teste
     * @param nome             descrição do cenário
     * @param a                primeiro número
     * @param b                segundo número
     * @param op               operador
     * @param mensagemEsperada mensagem que a exceção deve trazer
     */
    static void testarExcecao(Calculadora calc, String nome, int a, int b, String op,
                              String mensagemEsperada) {
        System.out.println("[Teste] " + nome);
        System.out.println("   Entradas : a=" + a + ", b=" + b + ", op=\"" + op + "\"");

        String obtido;
        try {
            obtido = "sem exceção (resultado " + calc.calcular(a, b, op) + ")";
        } catch (IllegalArgumentException e) {
            obtido = e.getMessage();
        }

        mostrarResultado(mensagemEsperada, obtido, mensagemEsperada.equals(obtido));
    }

    /**
     * Imprime esperado, obtido e a situação do teste, e atualiza os contadores.
     *
     * @param esperado texto do resultado esperado
     * @param obtido   texto do resultado obtido
     * @param passou   {@code true} se o teste passou
     */
    private static void mostrarResultado(String esperado, String obtido, boolean passou) {
        total++;
        if (passou) {
            aprovados++;
        }

        System.out.println("   Esperado : " + esperado);
        System.out.println("   Obtido   : " + obtido);
        System.out.println("   Situação : " + (passou ? "PASSOU" : "FALHOU"));
        System.out.println();
    }

    /**
     * Executa todos os testes e mostra o resumo.
     *
     * @param args não utilizado
     */
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println("===== TESTES DA CALCULADORA =====\n");

        // Os mesmos 9 testes da versão original
        testar(calc, "Soma", 2, 3, "+", 5);
        testar(calc, "Subtração", 10, 4, "-", 6);
        testar(calc, "Subtração com resultado negativo", 3, 8, "-", -5);
        testar(calc, "Multiplicação", 3, 5, "*", 15);
        testar(calc, "Divisão exata", 8, 2, "/", 4);
        testar(calc, "Divisão inteira (7 / 2)", 7, 2, "/", 3);
        testar(calc, "Divisão por zero", 8, 0, "/", 0);
        testar(calc, "Operação inválida", 5, 5, "x", 0);
        testar(calc, "Operação nula", 5, 5, null, 0);

        // Novos: o método estrito deve lançar exceção com a mensagem correta
        testarExcecao(calc, "Exceção na divisão por zero", 8, 0, "/",
                Calculadora.MSG_DIVISAO_POR_ZERO);
        testarExcecao(calc, "Exceção em operador inválido", 5, 5, "x",
                Calculadora.MSG_OPERADOR_INVALIDO + "x");

        System.out.println("===== RESULTADO: " + aprovados + "/" + total + " testes aprovados =====");
        if (aprovados != total) {
            System.exit(1);
        }
    }
}
