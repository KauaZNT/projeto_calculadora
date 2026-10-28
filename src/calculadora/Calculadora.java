package calculadora;

/**
 * Motor de cálculos da startup FinançApp.
 * <p>
 * Esta classe realiza as quatro operações básicas entre dois números inteiros
 * e foi pensada para ser reaproveitada em várias telas do aplicativo:
 * </p>
 * <ul>
 *   <li>cálculo de orçamento mensal;</li>
 *   <li>divisão de despesas em grupo;</li>
 *   <li>projeção de economias.</li>
 * </ul>
 * <p>
 * Existem duas formas de uso:
 * </p>
 * <ul>
 *   <li>{@link #calc(int, int, String)}: versão segura, que nunca lança exceção.
 *       Em caso de erro mostra uma mensagem no console e devolve {@code 0};</li>
 *   <li>{@link #calcular(int, int, String)}: versão estrita, que lança
 *       {@link IllegalArgumentException} quando a operação não pode ser feita.</li>
 * </ul>
 *
 * @author Kauã Zonta Souza
 * @version 2.0
 */
public class Calculadora {

    /** Mensagem usada quando há tentativa de divisão por zero. */
    public static final String MSG_DIVISAO_POR_ZERO = "Divisão por zero não é permitida.";

    /** Início da mensagem usada quando o operador informado não é reconhecido. */
    public static final String MSG_OPERADOR_INVALIDO = "Operador inválido: ";

    /** Resultado da última operação executada por {@link #calc(int, int, String)}. */
    private int r = 0;

    /**
     * Cria uma calculadora com o resultado inicial igual a zero.
     */
    public Calculadora() {
        // nenhum preparo necessário: r já começa em zero
    }

    /**
     * Executa uma operação de forma segura, sem nunca interromper o programa.
     * <p>
     * Se a operação for válida, guarda o resultado em {@code r}. Se houver erro
     * (divisão por zero ou operador inválido), mostra a mensagem do erro no
     * console e o resultado passa a ser {@code 0}.
     * </p>
     *
     * @param a  primeiro número
     * @param b  segundo número
     * @param op operador: {@code "+"}, {@code "-"}, {@code "*"} ou {@code "/"}
     * @return o resultado da operação, ou {@code 0} se ocorrer algum erro
     */
    public int calc(int a, int b, String op) {
        try {
            r = calcular(a, b, op);
        } catch (IllegalArgumentException e) {
            // O erro vira mensagem para o usuário e o programa continua
            System.out.println("Erro: " + e.getMessage());
            r = 0;
        }

        System.out.println("Resultado = " + r);
        return r;
    }

    /**
     * Executa uma operação de forma estrita: erros viram exceção.
     *
     * @param a        primeiro número
     * @param b        segundo número
     * @param operador operador: {@code "+"}, {@code "-"}, {@code "*"} ou {@code "/"}
     * @return o resultado da operação
     * @throws IllegalArgumentException se o operador for inválido (ou nulo)
     *                                  ou se houver divisão por zero
     */
    public int calcular(int a, int b, String operador) {
        if (operador == null) {
            throw new IllegalArgumentException(MSG_OPERADOR_INVALIDO + "null");
        }

        return switch (operador) {
            case "+" -> somar(a, b);
            case "-" -> subtrair(a, b);
            case "*" -> multiplicar(a, b);
            case "/" -> dividir(a, b);
            default -> throw new IllegalArgumentException(MSG_OPERADOR_INVALIDO + operador);
        };
    }

    /**
     * Soma dois números inteiros.
     *
     * @param a primeira parcela
     * @param b segunda parcela
     * @return a soma de {@code a} e {@code b}
     */
    public int somar(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai dois números inteiros.
     *
     * @param a minuendo
     * @param b subtraendo
     * @return a diferença {@code a - b}
     */
    public int subtrair(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois números inteiros.
     *
     * @param a primeiro fator
     * @param b segundo fator
     * @return o produto de {@code a} por {@code b}
     */
    public int multiplicar(int a, int b) {
        return a * b;
    }

    /**
     * Divide dois números inteiros (divisão inteira: 7 / 2 resulta em 3).
     *
     * @param a dividendo
     * @param b divisor
     * @return o quociente de {@code a} por {@code b}
     * @throws IllegalArgumentException se o divisor {@code b} for zero
     */
    public int dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException(MSG_DIVISAO_POR_ZERO);
        }
        return a / b;
    }

    /**
     * Informa o resultado guardado pela última chamada de
     * {@link #calc(int, int, String)}.
     *
     * @return o valor do atributo {@code r}
     */
    public int getR() {
        return r;
    }
}
