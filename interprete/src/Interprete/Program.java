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

public class Program {

    String T1;
    String post;
    String postf;
    String ope;
    String ids;
    int c = 0;
    final int TAMANO = 5;
    int ciclo = 0, contador = 0;
    
    String vecto1[] = new String[TAMANO];
    String T2[] = new String[50];
    String T3;
    String T4[] = new String[50];
    
    SourceCode codigoFuente1 = new SourceCode();
    AnalizadorLexico analizador1 = new AnalizadorLexico();
    
    Pila pila = new Pila();
    Instrucciones instruccion = new Instrucciones(pila);
    
    public static void main(String[] args) {

        Program objMain = new Program();

        objMain.setSourceCode();
        //objMain.ejecutarAnalizadorLexico();
        objMain.Analizador_Sintactico();
        objMain.Ejecutor();
    }

    public void setSourceCode() {
        this.analizador1.setSourceCode(codigoFuente1);
        this.codigoFuente1.switchInputMode(1);
    }

    public void ejecutarAnalizadorLexico() {
        //Simula las peticiones del analizador lexico.
        boolean isEndOfSource = false;
        String token = "w=3";
        String valLex = "";

        while (!(isEndOfSource)) {
            token = this.analizador1.getToken(); //analizador1.getToken()
            valLex = this.analizador1.getValorLexico(); //analizador1.getValorLexico()

            if (token.equals("") && valLex.equals("")) {
                isEndOfSource = true;
            } else {
                //System.out.printf("Token:%S  Lexema: %S \n", token, valLex);
            }
        }
    }

    public void Analizador_Sintactico() {
        D();
    }

    public void D() {
        d();
    }

    public void d() {
        T();
    }

    public void T() {
        T1 = this.analizador1.getToken();
        if ("reserv".equals(T1)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            T4[contador] = T3;
            LI();
            //System.out.printf(post);

            //System.out.printf(ope);
        } else {
            System.out.println("Error Sintactico: en el Metodo T, se acepta Palabra reservada");
            FINERROR();
        }
    }

    public void LI() {
        T1 = this.analizador1.getToken();
        if ("id".equals(T1)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            //System.out.println("PUSHD " + T3);
            this.instruccion.push(1, "PUSHD", T3, "0");
            
            T4[contador] = T3;
            post = T3;
            LIP();

        } else {
            System.out.println("Error Sintactico: en el Metodo LI, se acepta ID");
            FINERROR();
        }
    }

    public void LIP() {
        T1 = this.analizador1.getToken();
        T3 = this.analizador1.getValorLexico();
        if ("delim".equals(T1) && ",".equals(T3)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            T4[contador] = T3;
            LIP();
        } else {
            if (("delim".equals(T1)) && ";".equals(T3)) {
                contador = contador + 1;
                //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
                T4[contador] = T3;
                DP();
            } else {
                if (("op".equals(T1)) && ("=".equals(T3))) {
                    contador = contador + 1;
                    //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
                    T4[contador] = T3;
                    I();
                } else {
                    System.out.println("Error Sintactico: en el Metodo LIP, se acepta delimitador|fin de cadena|operador asignacion");
                    FINERROR();
                }
            }
        }
    }

    public void DP() {
        T1 = this.analizador1.getToken();
        if ("reserv".equals(T1)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            T4[contador] = T3;
            LI();

        } else {
            if ("".equals(T1)) {
                //FIN();
            } else {
                System.out.println("Error Sintactico: en el Metodo DP, se acepta Palabra reservada");
                FINERROR();
            }
        }
    }

    public void I() {
        E();
        FIN();
    }

    public void E() {
        T1 = this.analizador1.getToken();
        if ("id".equals(T1)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            //System.out.println("PUSHD " + T3);
            this.instruccion.push(1, "PUSHD", T3, "");
            T4[contador] = T3;
            EP();

        } else if ("cte".equals(T1)) {
            contador = contador + 1;
            T3 = this.analizador1.getValorLexico();
            this.instruccion.push(2, "PUSHC", T3, "");
            //System.out.println("PUSHC " + T3);
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            T4[contador] = T3;
            EP();
        } else {
            System.out.println("Error Sintactico: en el Metodo E, se acepta id o cte");
            FINERROR();
        }
    }

    public void EP() {
        T1 = this.analizador1.getToken();
        T3 = this.analizador1.getValorLexico();
        if ("op".equals(T1) && "+".equals(T3)) {
            contador = contador + 1;
            //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
            ope = T3;
            T4[contador] = T3;
            E();
            //System.out.println("ADD +");
            this.instruccion.push(5, "ADD", "", "");
        } else {
            if ("delim".equals(T1) && ";".equals(T3)) {
                contador = contador + 1;
                //System.out.printf("TOKEN:%S Lexema: %S \n", T1, T3);
                T4[contador] = T3;
                //FIN();
            } else {
                System.out.println("Error Sintactico:en el Metodo EP, se acepta op|delim ");
                FINERROR();
            }
        }
    }

    public void FIN() {
        //System.out.println("STORE =");
        this.instruccion.push(6, "STORE", "", "");
        
        //Instrucciones adicionales
        this.instruccion.push(7, "OUTPUT", "","");
        this.instruccion.push(1001, "EXIT", "","");
        
        System.out.println("FRASE VALIDA PARA LA GRAMATICA:");
        
    }

    public void FINERROR() {
        System.out.println("FRASE ERRONEA!!!!");
        System.exit(c);
    }

    //EJECUTOR DE CÓDIGO
    public void Ejecutor() {
        boolean run = true;
        String dir, value, OutPut;
        int val;
        
        while (this.instruccion.leerInstruccion() && run) {
            switch (this.instruccion.opCode()) {
                case 1:
                       dir = pila.buscaDireccion(instruccion.opndo());
                       this.pila.push(dir);
                       
                    break;
                case 2:
                        this.pila.push(instruccion.opndo());
                        
                    break;
                case 3:
                    
                    break;
                case 4:
                    break;
                case 5:
                    
                        val = Integer.parseInt(this.pila.pop()) +
                                  Integer.parseInt(this.pila.pop());
                        this.pila.push(val + "");
                        
                    break;
                case 6:
                       
                       value = this.pila.pop();
                       this.pila.pop();
                       this.pila.push(value);
                       
                    break;
                case 7:
                        value = this.pila.opndoEnTope();
                        OutPut = this.pila.valorEnTope();
                        System.out.printf("%S  %S %S\n",value, "=" ,OutPut);
                        
                    break;
                case 1001:
                        run = false;
                    break;
                default:
                    break;
            }
            
        }
    }
}
