import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.GotoInstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Repeatable;

public class QA {

    public static void main(String... args) throws IOException {

        String A="CLIENTE";
        String x;
        float IF=0,IE=0,PS=0,Si=0, Mi=0, Ti=0, CLDC=0,sumIF=0, sumSi=0, sumWt=0,sumWm=0,sumWs=0,Ei1=0,cost=0;
        float CDef=0,cost1=0,cost2=0,cost3=0, TDef=0;
        int Pasos=0, Ei=0, Ws=10,Wm=3,Wt=1,x1=0,x2=0;

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\nIngrese cantidad de pasos de verificación: ");
        Pasos = Integer.parseInt(br.readLine());
        //for (int i=0;i<=Pasos;i++){

        do {
/*
            System.out.print("\nIngrese nombre de la empresa: ");
            A = br.readLine();*/

       try {
           System.out.print("Ingrese numero de etapa (1:Diseño y codigo, 2:Justo antes de pruebas, 3:Durante prueba, 4: Pos impl): ");
           Ei = Integer.parseInt(br.readLine());
           switch (Ei) {
               case 1:
                   Ei1 = (float) 0.01;
                   break;
               case 2:
                   Ei1 = (float) 0.605;
                   break;
               case 3:
                   Ei1 = (float) 0.105;
                   break;
               case 4:
                   System.out.print("Ingrese el porcentaje de costo de error (0.47~0.60): ");
                   Ei1 = Float.parseFloat(br.readLine());
                   break;
           }
       }catch (NumberFormatException e){
           System.out.print("Ingrese el numeros");

       }

            System.out.print("Elija el tipo de defecto: (1:Leve, 2:Moderado, 3:Grave): ");
            x1 = Integer.parseInt(br.readLine());
            switch (x1) {
                case 1:
                    TDef = Wt;
                    break;
                case 2:
                    TDef = Wm;
                    break;
                case 3:
                    TDef = Ws;
                    break;
            }

            System.out.print("Ingrese la cantidad de defectos: ");
            Si = Float.parseFloat(br.readLine());

                    if(TDef == Wt){
                        sumWt=sumWt+Si;
                        cost1=sumWt*Ei1;
                    }

                    if(TDef == Wm){
                        sumWm=sumWm+Si;
                        cost2=sumWm*Ei1;
                    }

                    if(TDef == Ws){
                        sumWs=sumWs+Si;
                        cost3=sumWs*Ei1;
                    }



            CDef=Si+CDef;
            //CDef = sumSi;

            sumIF = TDef*(Si/Ei);
            IF = sumIF+IF;
            //IF = (Ws * (Si / Ei) + Wm * (Mi / Ei) + Wt * (Ti / Ei));

            System.out.print("Desea agregar otro error? (si presionar: 'y' / no presionar 'n' ): ");
            x = br.readLine();

            System.out.print("\n");

        }while(!x.equals("n"));

            System.out.print("Ingrese cantidad de Lineas de codigo o tamaño del sistema: ");
            PS = Float.parseFloat(br.readLine());
            System.out.print("Ingrese el costo de linea de codigo: ");
            CLDC = Float.parseFloat(br.readLine());


            System.out.println("\nEl IF (indice acumulativo de error) de " + A + " es: " +IF);

            int num=1;
            double sum=0;
            while (num<=Pasos){
                IE=num*IF;
                sum=sum+IE;
                num++;
            }

            System.out.println("\nEl IE (indice de error) de " + A + " es: " +sum/PS+"%");
           // System.out.println("El costo de los defectos de " + A + " es: " +(CDef*(Ei1)*(CLDC))+"\n");

        System.out.println("El costo de los defectos de " + A + " es: " +((cost1+cost2+cost3)*CLDC));
       // System.out.println("El costo de los defectos de " + A + " es: " +(sumWm*(Wm)*(CLDC)));
        //System.out.println("El costo de los defectos de " + A + " es: " +(sumWt*(Wt)*(CLDC)));

    }

}
