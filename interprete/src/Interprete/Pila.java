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

public class Pila {

    private int top = -1;
    private int capacidad = 100;
    private int indice = -1;
    private String pila[] = new String[capacidad];
    private String valores[] = new String[capacidad];

    public Pila() {
    }
    
    public void inicializar(String val){
        indice++;
        valores[indice] = val;
    }
    
    public String buscaDireccion(String elemento){
        int i;
        
        for(i = 0; i < indice; i++){
            if(valores[i].equals(elemento)){
                return "" + i;
            }
        }
        
        return "";
    }
    
    public void push(String elemento) {
        if (top < capacidad - 1) {
            top++;
            pila[top] = elemento;
        } else {
            System.out.println("Stack Overflow !");
        }
    }

    public String pop() {
        if (top >= 0) {
            return pila[top--];
        }
        
        return "";
    }

    public String valorEnTope(){
        return this.pila[top];
    }
    
    public int topPila(){
        return this.top;
    }
    
    public String opndoEnTope(){
        return this.valores[this.top];
    }
    
    public void imprimirElementos() {
        if (top >= 0) {
            System.out.println("Elementos en pila :");
            for (int i = 0; i <= top; i++) {
                System.out.println(pila[i]);
            }
        }
    }
}
