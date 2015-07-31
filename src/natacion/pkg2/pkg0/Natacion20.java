package natacion.pkg2.pkg0;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Natacion20 {
    
    public static void main(String[] args) {
        // TODO code application logic here
    //Declaramos nuestro obj scanner para leer
    Scanner entrada= new Scanner(System.in);
    //Variables nescesarias enviar los datos con el constructor
    long ci=0;
    String nombre="";
    String apellido="";
    double marcapersonal=0.0d;
    String prueba="";
    String categoria="";
    String estilo=" ";
    int cantidad=0;
        //Datos de los nadadores
        System.out.print("Categoria de los nadadores: ");
        categoria=entrada.nextLine();
        System.out.print("Prueba: ");
        prueba=entrada.nextLine();   
        System.out.print("Estilo: ");
        estilo=entrada.nextLine();
        
        //Arreglo de nadadores  
        System.out.print("¿Cuantos nadadores hay en el lote?: ");
        cantidad=entrada.nextInt();
        Nadador nadadores[]= new Nadador[cantidad];
        Carril carriles[] = new Carril[8];
        for (int i=0;i<nadadores.length;i++){
             System.out.print("\n \t Datos del nadador "+(i+1));
             System.out.print("\n numero de cedula: ");
             ci=entrada.nextLong();
             entrada.nextLine();//Limpiar el buffer y lea la siguiente instruccion
             System.out.print("\n nombre: ");
             nombre=entrada.nextLine();
             System.out.print("\n apellido: ");             
             apellido=entrada.nextLine();
             System.out.print("\n marca personal: ");
             marcapersonal=entrada.nextDouble();
             //validar usando isnumeric de ecl);ipse, clase Matriz_metodo y comprueba email
             nadadores[i]= new Nadador(ci,nombre,apellido,marcapersonal);             
        }
        
         //Imprime los nadadores
         for (int k=0;k<nadadores.length;k++){
             System.out.print("\n Nadador numero "+(k+1)+", "+ " cedula:"+nadadores[k].getCi()+" nombre "+ nadadores[k].getNombre()+" apellido "+nadadores[k].getApellido()+" marca personal "+nadadores[k].getMarcapersonal());
             System.out.println();
         }
         
         //Asignando valores al carril
        String posiciones[] = {"Carril 4","Carril 5", "Carril 3", "Carril 6", "Carril 2", "Carril 7", "Carril 1", "Carril 8"} ;
         //Ordenando el carril
         int h = 0;//Contador
         Carril auxc = new Carril(); 
         for( String x : posiciones){//Arreglo for each, para asignar las posiciones con setCarril
            carriles[h] = auxc;//esta linea
            h++; 
        }
        //ordenar nadadores, para asignarlos en los mejores carriles. 
        //Metodo burbuja de ordenamiento
        Nadador aux; 
        for(int p = 0; p < nadadores.length; p++){
            for(int j = p +1 ; j < nadadores.length; j++){ 
                if(nadadores[p].getMarcapersonal() > nadadores[j].getMarcapersonal()){
                    aux = nadadores[p];
                    nadadores[p] = nadadores[j];
                    nadadores[j] = aux;
                }
            }
        }
       /*TOMA DE DECISIONES, ASIGNACION DE NADADORES*/
        h = 0;
        for (Nadador swimmer: nadadores) {
            carriles[h].setNadador(swimmer);
            carriles[h].setCarril(posiciones[h]);//sobreescribiendo las posiciones 
            System.out.println(carriles[h].getNadador() + " asignado al "+ carriles[h].getCarril());
            h++;
        }
        
        /*SIMULACION Y MODELOS, SIMULACION DE COMPETENCIA*/
       
        DecimalFormat df = new DecimalFormat("0.00"); 
        double[] tiempof= new double[cantidad];
        double[] tiemponadadores= new double[cantidad];
        //El for llena el arreglo con los tiempo aleatorios para cada nadador.
        System.out.println("\n \t **** Resultados de la competencia **** ");
        for(int i=0; i<nadadores.length;i++){
            //libre
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Libre")&& categoria.equalsIgnoreCase("Maxima") && nadadores[i].getMarcapersonal()<=29){                            
                nadadores[i].setTiempofinal(Math.random()*(29-nadadores[i].getMarcapersonal())+25);
                //Usar cod ordenar nadadores para simular la victoria            
            }           
            //espalda
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Espalda")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<30){
                tiempof[i]=Math.random()*(30-26)+30;            
            }
            //mariposa
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Mariposa")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<30){
                tiempof[i]=Math.random()*(30-25)+30;            
            }
            //pecho
            if(prueba.equalsIgnoreCase("50 metros") && estilo.equalsIgnoreCase("Pecho")&& categoria.equalsIgnoreCase("Maxima")&& nadadores[i].getMarcapersonal()<31){
                tiempof[i]=Math.random()*(31-27)+31;
            }
            //Resultados
            
        }
        aux = null; 
        for(int p = 0; p < nadadores.length; p++){
            for(int j = p +1 ; j < nadadores.length; j++){ 
                if(nadadores[p].getTiempofinal() > nadadores[j].getTiempofinal()){
                    aux = nadadores[p];
                    nadadores[p] = nadadores[j];
                    nadadores[j] = aux;
                }
            }
        }
          
            System.out.println("***Ganador*** "+nadadores[0].getNombre()+" "+nadadores[0].getApellido()+" es de " +df.format(nadadores[0].getTiempofinal()));          
            for(int i=1; i<nadadores.length;i++)
                System.out.println("El tiempo del nadador "+nadadores[i].getNombre()+" "+nadadores[i].getApellido()+" es de " +df.format(nadadores[i].getTiempofinal()));          
                
    }   
 }
