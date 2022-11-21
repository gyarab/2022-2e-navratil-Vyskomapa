package com.example.vyskomapa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Vyskomapa extends Application {
    //velikost sceny
    public static final int sizeY = 500;
    public static final int sizeX = 500;
    public static final int bodSize = 2;
    public static final int schodek = 50;
    public static final int MAX = 1500;
    public static final int MIN = 0;
    public static final int OPAK = 20;


    @Override
    public void start(Stage stage) throws IOException {

        Pane root = new Pane();
        root.setPrefSize(sizeX,sizeY);


        generujMapu2(root);
        //generujMapu(root);

        /*Bod[][] mapa = new Bod[sizeX/bodSize][sizeY/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);*/
        /**to co je v generujMapu()
        /*
        int o2DoLeva;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int nahoreVlevo;
        int vpravo;
        int dole;
        int doleVpravo;
        int doleVlevo;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;
        for (int k = 0; k<OPAK; k++){

            if (k==0){
                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                            mapa[i][j] = zakladniBod;

                        }else if (i==0){

                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= 1){

                                o2DoLeva = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                                prumer = (vlevo + o2DoLeva )/2;
                                max = prumer + schodek;
                                min = prumer - schodek;


                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }


                                vyskaI = vytvorVysku(max,min);

                            }else{
                                max = vlevo+schodek;
                                min = vlevo-schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }


                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            if (j!= mapa.length-1 && j!= mapa.length){
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahoreVpravo + nahore)/2;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }else{
                                prumer = nahore;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("-------------------------------------------------------------"+i);
                            //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= mapa.length&& j!= mapa.length-1){

                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahore + vlevo + nahoreVlevo + nahoreVpravo)/4;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }
                                vyskaI = vytvorVysku(max,min);

                            }else{

                                prumer = (nahore + nahoreVlevo + vlevo)/3;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }else{

                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();

                            prumer = (dole + vpravo + doleVpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                        }else if (i==0){


                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            prumer = (dole + doleVlevo + vlevo)/3;

                            if (j!= mapa.length-1 && j!= mapa.length){

                                //vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (dole + doleVlevo + vlevo + vpravo + doleVpravo)/5;

                            }

                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize,j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                            System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVpravo + vpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (i!=mapa.length && i!= mapa.length-1){
                                dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + vpravo + dole + doleVpravo)/5;
                                max = prumer + schodek;
                                min = prumer - schodek;
                            }

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            root.getChildren().addAll(bod);
                            System.out.println("-------------------------------------------------------------"+i);
                            System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (i!= mapa.length&& i!=mapa.length-1)  {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo)/5;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            if (j!= mapa.length-1 &&j!= mapa.length){

                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();

                                prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo + doleVpravo + nahoreVpravo + vpravo)/8;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = 0;
                                    count++;
                                }else if(max>MAX){
                                    max = 1000;
                                    count++;
                                }

                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            root.getChildren().addAll(bod);
                            System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }

        }
        System.out.println("konec " + "count = " + count);
*/




        Scene scene = new Scene(root, sizeX, sizeY);
        stage.setScene(scene);
        stage.show();

    }

    public void generujMapu2(Pane root){

        int o2DoLeva;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int nahoreVlevo;
        int vpravo;
        int dole;
        int doleVpravo;
        int doleVlevo;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;


        Bod[][] mapa = new Bod[sizeX/bodSize][sizeY/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);

        for (int i = 0; i<mapa.length; i++){

            for (int j =0; j<mapa.length; j++){

                if (i==0 && j==0){

                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;

                }else if (i==0){

                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= 1){

                        o2DoLeva = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                        prumer = (vlevo + o2DoLeva )/2;
                        max = prumer + schodek;
                        min = prumer - schodek;


                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }


                        vyskaI = vytvorVysku(max,min);

                    }else{
                        max = vlevo+schodek;
                        min = vlevo-schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }


                    Vyska vyska = new Vyska(vyskaI);

                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);
                    System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                }else if (j==0){

                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    if (j!= mapa.length-1 && j!= mapa.length){
                        nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahoreVpravo + nahore)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }else{
                        prumer = nahore;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
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

                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= mapa.length&& j!= mapa.length-1){

                        nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahore + vlevo + nahoreVlevo + nahoreVpravo)/4;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }
                        vyskaI = vytvorVysku(max,min);

                    }else{

                        prumer = (nahore + nahoreVlevo + vlevo)/3;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
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
    }

    public void generujMapu(Pane root){

        int o2DoLeva;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int nahoreVlevo;
        int vpravo;
        int dole;
        int doleVpravo;
        int doleVlevo;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;


        Bod[][] mapa = new Bod[sizeX/bodSize][sizeY/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);

        System.out.println("zacatek" + mapa.length);

        for (int k = 0; k<OPAK; k++){

            if (k==0){
                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                            mapa[i][j] = zakladniBod;

                        }else if (i==0){

                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= 1){

                                o2DoLeva = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                                prumer = (vlevo + o2DoLeva )/2;
                                max = prumer + schodek;
                                min = prumer - schodek;


                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }


                                vyskaI = vytvorVysku(max,min);

                            }else{
                                max = vlevo+schodek;
                                min = vlevo-schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }


                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            if (j!= mapa.length-1 && j!= mapa.length){
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahoreVpravo + nahore)/2;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }else{
                                prumer = nahore;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("-------------------------------------------------------------"+i);
                            //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= mapa.length&& j!= mapa.length-1){

                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahore + vlevo + nahoreVlevo + nahoreVpravo)/4;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }
                                vyskaI = vytvorVysku(max,min);

                            }else{

                                prumer = (nahore + nahoreVlevo + vlevo)/3;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }else{

                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();

                            prumer = (dole + vpravo + doleVpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                        }else if (i==0){


                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            prumer = (dole + doleVlevo + vlevo)/3;

                            if (j!= mapa.length-1 && j!= mapa.length){

                                //vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (dole + doleVlevo + vlevo /*+ vpravo*/ + doleVpravo)/5;

                            }

                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize,j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                            System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVpravo + vpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (i!=mapa.length && i!= mapa.length-1){
                                dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + vpravo + dole + doleVpravo)/5;
                                max = prumer + schodek;
                                min = prumer - schodek;
                            }

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            root.getChildren().addAll(bod);
                            System.out.println("-------------------------------------------------------------"+i);
                            System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (i!= mapa.length&& i!=mapa.length-1)  {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo)/5;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            if (j!= mapa.length-1 &&j!= mapa.length){

                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();

                                prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo + doleVpravo + nahoreVpravo + vpravo)/8;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = 0;
                                    count++;
                                }else if(max>MAX){
                                    max = 1000;
                                    count++;
                                }

                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            root.getChildren().addAll(bod);
                            System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }

        }
    }


    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(min,max);
    }

    public static void main(String[] args) {
        launch();
    }
}