package vyskomapa;

import com.example.vyskomapa.Bod;
import com.example.vyskomapa.Vyska;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Vyskomapa2 extends Application {
    //zakladni parametry
    public static final int sizeY = 1000;
    public static final int sizeX = 1000;
    public static final int bodSize = 1;
    public static final int schodek = 100;


    @Override
    public void start(Stage stage) throws IOException {

        int nahoreVpravo;
        int v;
        int nahore;
        int nalevo;
        int nahoreVlevo;
        int Vyska;
        int prumer;
        int max;
        int min;

        Pane root = new Pane();
        root.setPrefSize(sizeX,sizeY);

        Bod[][] mapa = new Bod[sizeX/bodSize][sizeY/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);


        System.out.println("zacatek " + mapa.length);

        for (int i = 0; i<mapa.length; i++){

            for (int j =0; j<mapa.length; j++){

                if (i==0 && j==0){

                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;

                }else if (i==0){

                    nalevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= 1){
                        v = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                        prumer = (nalevo + v )/2;
                        max = prumer + schodek;
                        min = prumer - schodek;
                        Vyska = vytvorVysku(max,min);
                    }else{
                        max = nalevo+schodek;
                        min = nalevo-schodek;
                        Vyska = vytvorVysku(max,min);
                    }


                    Vyska vyska = new Vyska(Vyska);

                    Bod bod = new com.example.vyskomapa.Bod(i*bodSize, j*bodSize, vyska);

                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);
                    System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + Vyska);

                }else if (j==0){
/*

*/

                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    if (j!= mapa.length-1 && j!= mapa.length){
                        nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahoreVpravo + nahore)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;
                        Vyska = vytvorVysku(max,min);
                    }else{
                        prumer = nahore;
                        max = prumer + schodek;
                        min = prumer - schodek;
                        Vyska = vytvorVysku(max,min);
                    }

                    Vyska vyska = new Vyska(Vyska);

                    Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);

                    System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + Vyska);

                }else {

                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    nalevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= mapa.length&& j!= mapa.length-1){
                        nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahore + nalevo + nahoreVlevo + nahoreVpravo)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;
                        Vyska = vytvorVysku(max,min);
                    }else{
                        prumer = (nahore + nahoreVlevo + nalevo)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;
                        Vyska = vytvorVysku(max,min);
                    }


                    Vyska vyska = new Vyska(Vyska);
                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                    mapa[i][j]=bod;
                    root.getChildren().addAll(bod);
                    System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + Vyska);
                }
            }
        }
        System.out.println("konec");



        Scene scene = new Scene(root, sizeX, sizeY);
        stage.setScene(scene);
        stage.show();

    }

    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(max - min)+min;
    }

    public static void main(String[] args) {
        System.out.println("mibbg");
        launch();
    }
}