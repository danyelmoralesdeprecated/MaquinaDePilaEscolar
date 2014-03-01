/*******************************************
 *            INTERPRETE ESCOLAR
 * -----------------------------------------
 * INSTITUTO TECNOLOGICO DE MERIDA, YUCATAN.
 * -----------------------------------------
 *          IN HOC SIGNO VINCES
 * -----------------------------------------
 *            DANIEL MORALES
 *          medennysoft@outlook.com
 * -----------------------------------------
 */

package Interprete;

public class Instrucciones {

    private int top = -1;
    private int indice = -1;
    private int capacidad = 100;
    private int opCode[] = new int[capacidad];
    private String operacion[] = new String[capacidad];
    private String oPndo[] = new String[capacidad];
    private String valor[] = new String[capacidad];

    private Pila stack;

    public Instrucciones(Pila pila) {
        this.stack = pila;
    }

    public boolean leerInstruccion() {
        if (indice <= top) {
            indice++;
        } else {
            return false;
        }
        return true;
    }

    public void push(int opcode, String op, String opndo, String val) {
        top++;
        opCode[top] = opcode;
        operacion[top] = op;
        oPndo[top] = opndo;
        valor[top] = val;

        this.stack.inicializar(opndo);
    }

    public int opCode() {
        return opCode[indice];
    }

    public String op() {
        return operacion[indice];

    }

    public String opndo() {
        return oPndo[indice];
    }

    public String valor() {
        return valor[indice];
    }
}
