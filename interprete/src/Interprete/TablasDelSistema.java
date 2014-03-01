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
public class TablasDelSistema {
    private String[][] lsToken;
    private String[][] tablaDeSalida;
    
    private String[][] tablaPrincipal;    //Matriz que genera las transicione
    private String[] alfabeto;
    private String[] estados;
    private String[] reserved;
    
    public TablasDelSistema(){
        this.iniTablas();
    }
    
    private void iniTablas(){
       this.reserved = new String[]{
                        "char",
                        "float",
                        "int"
                      };
       
       this.lsToken = new String[][]{    
                            {"R4","cte"},
                            {"R3","unknown"},
                            {"R2", "op"},
                            {"R1", "delim"},
                      };
    //TABLA DE SALIDA DE LOS MEJORES CASOS.
    this.tablaDeSalida = new String[][]{
                             {"q0",   "-"},
                             {"q1",   "R3"}, 
                             {"q2",   "R3"}, 
                             {"q3",   "R3"}, 
                             {"q4",   "R3"}, 
                             {"q5",   "R4"}, 
                             {"q6",   "R1"}, 
                             {"q7",   "R1"}, 
                             {"q8",   "R2"}, 
                             {"q9",   "R2"}  
                         };
       
       this.alfabeto = new String[]{
           "letra","digito",  "_", ",", ";", "=", "+", "otro"," "
       };
       
       this.estados = new String[]{
           "q0","q1","q2","q3","q4","q5","q6","q7","q8","q9"
       };
       
       this.tablaPrincipal = new String[][]{
            {"q1",    "q5",  "E","q6","q7","q8","q9", "E", "O"},
            {"q2",    "q3", "q4", "O", "O", "O", "O", "E", "O"},
            {"q2",    "q1", "q1", "O", "O", "O", "O", "E", "O"},
            {"q1",    "q3", "q1", "O", "O", "O", "O", "E", "O"},
            {"q1",    "q1", "q4", "O", "O", "O", "O", "E", "O"},
            {"O",     "q5",  "O", "O", "O", "O", "O", "E", "O"},
            {"O",      "O",  "O", "O", "O", "O", "O", "E", "O"},
            {"O",      "O",  "O", "O", "O", "O", "O", "E", "O"},
            {"O",      "O",  "O", "O", "O", "O", "O", "E", "O"},
            {"O",      "O",  "O", "O", "O", "O", "O", "E", "O"}
        };    
    }
    
    public String getToken(String R){
        for(int i=0; i < this.lsToken.length; i++){
            if(this.lsToken[i][0].equals(R)){
                return this.lsToken[i][1];
            }
        }
        return "";
    }
    
    public String getSalida(String estado)
    {
        int largoSalida = this.tablaDeSalida.length, i, j;
        for (i = 0; i < largoSalida; i++) {
            if(this.tablaDeSalida[i][0].equals(estado)){
                return this.tablaDeSalida[i][1];
            }
        }
     
        return "";
    }
    
    public String getEstado(String estado, String categoria){ 
        boolean isThere = false;
        int largoAlfabeto = this.alfabeto.length,
            largoEstados = this.estados.length,
            x = 0, 
            y = 0, 
            i;
        
        for (i = 0; i < largoAlfabeto; i++) {
           if(this.alfabeto[i].equals(categoria)){
               x = i;
               isThere = true;
               break;
           }
        }
        
        for (i = 0; i < largoEstados; i++) {
           if(this.estados[i].equals(estado)){
               y = i;
               break;
           }
       }
        
        if((!(isThere)) || this.tablaPrincipal[y][x].equals("E")){
            //return "ERR.LEX.EXIST.TOKEN";
            return "ERR";
        }
        
        return this.tablaPrincipal[y][x];
   }
    
    /*******************************************************
     * Método: evaluarToken()                              *
     *------------------------------------------------------
     *     Busqueda lineal, no dicotomica para diferenciar *
     *     entre identificadores y palabras reservadas.    *
     *------------------------------------------------------
     */
    public String evaluarToken(String lexema){
        for(int i = 0; i < this.reserved.length; i++){
            if(this.reserved[i].equals(lexema)){
                return "reserv";
            }
        }
        return "id";
    }
    
}
