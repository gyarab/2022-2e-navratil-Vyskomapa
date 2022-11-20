package com.example.vyskomapa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Vyskomapa extends Application {
    //velikost sceny
    public static final int sizeY = 1000;
    public static final int sizeX = 1000;
    public static final int bodSize = 5;
    public static final int schodek = 50;


    @Override
    public void start(Stage stage) throws IOException {
        int v;
        int u;
        int x;
        int y;
        int z;
        int vyskaI;
        int prumer;
        int max;
        int min;



        Pane root = new Pane();
        root.setPrefSize(sizeX,sizeY);

        Bod[][] mapa = new Bod[sizeX/bodSize][sizeY/bodSize];
        Vyska zakladniVyska = new Vyska(50);


        System.out.println("zacatek" + mapa.length);

        for (int i = 0; i<mapa.length; i++){

            for (int j =0; j<mapa.length; j++){

                if (i==0 && j==0){

                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;

                }else if (i==0){

                    y = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= 1){

                        v = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                        prumer = (y + v )/2;
                        max = prumer + schodek;
                        min = prumer - schodek;


                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }


                        vyskaI = vytvorVysku(max,min);

                    }else{
                        max = y+schodek;
                        min = y-schodek;

                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }


                    Vyska vyska = new Vyska(vyskaI);

                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);
                    System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                }else if (j==0){
/*
*/

                    x = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    if (j!= mapa.length-1 && j!= mapa.length){
                        u = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (u + x)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }else{
                        prumer = x;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }

                    Vyska vyska = new Vyska(vyskaI);

                    Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);
                    System.out.println("-------------------------------------------------------------"+i);
                    System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                }else {

                    z = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                    x = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    y = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= mapa.length&& j!= mapa.length-1){

                        u = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (x + y + z + u)/4;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }
                        vyskaI = vytvorVysku(max,min);

                    }else{

                        prumer = (x + z + y)/3;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<0){
                            min = 0;
                        }else if(max>1000){
                            max = 1000;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }

                    Vyska vyska = new Vyska(vyskaI);
                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                    mapa[i][j]=bod;
                    root.getChildren().addAll(bod);
                    System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
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
        return random.nextInt(min,max);
    }

    public static void main(String[] args) {
        launch();
    }
}