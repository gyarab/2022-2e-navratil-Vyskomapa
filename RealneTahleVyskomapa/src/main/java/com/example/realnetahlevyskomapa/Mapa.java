package com.example.realnetahlevyskomapa;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Mapa extends Vyskomapa{


    public Bod[] poleVybranychBodu = new Bod[5];

    public void generujMapu2(Pane root)  {

        //body ze kterych budu brat hodnoty
        int nahoreVlevo1;
        int nahore;
        int nahorevpravo1;
        int vlevo2;
        int vlevo;
        int vpravo1;
        int vpravo2;
        int doleVlevo1;
        int doleVlevo2;
        int dole;
        int doleVpravo1;
        int doleVpravo2;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;
        int pocet = 0;
        int x;
        int prumernaVyska = schodek;

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);
        Bod[] pole = new Bod[mapa.length];
        //Projidzi okno na ose x
        for (int i = 0; i<mapa.length; i++){
            //x jen abych vedel ze je to osa x
            x = i;
            // projizdi pole na ose y
            for (int j =0; j<mapa.length; j++){

                //jestli je to uplne vlevo nahore
                if (i==0 && j==0){

                    //Zakladame bod a u mistujeme ho do pole
                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    pole[j] = mapa[i][j];

                    //jestli to je uplne noahore
                }else if (i==0){

                    //zjisti jakou vysku ma bod nalevo
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= 1){

                        //zjisti jakou vysku ma bod o 2 doleva a udela prumer tech 2 vysek
                        vlevo2 = mapa[i][j-2].getVyska().getvalueOfVyska();
                        prumer = (vlevo + vlevo2 )/2;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        //vyhodi hodnoty ktere nejsou mozne
                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        //vytvori vysku
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

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;

                    mapa[i][j] = bod;
                    root.getChildren().addAll(bod);

                    //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                }else if (j==0){
                    prumernaVyska = schodek;
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    if (j!= mapa.length-1 && j!= mapa.length){
                        nahorevpravo1 = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahorevpravo1 + nahore)/2;
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

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;

                    root.getChildren().addAll(bod);
                    System.out.println("-------------------------------------------------------------"+i);
                    //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                }else {

                    nahoreVlevo1 = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= mapa.length&& j!= mapa.length-1){

                        nahorevpravo1 = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahore + vlevo + nahoreVlevo1 + nahorevpravo1)/4;

                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){

                            x = ThreadLocalRandom.current().nextInt(MIN,  max);

                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }
                        vyskaI = vytvorVysku(max,min);

                    }else{

                        prumer = (nahore + nahoreVlevo1 + vlevo)/3;
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

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;

                    root.getChildren().addAll(bod);

                }
                pole[j] = mapa[i][j];

            }
            System.out.println(prumernaVyska);
        }

        MAPA = mapa;

    }

    public void generujMapu(Pane root){



        Rectangle r = new Rectangle();
        r.setHeight(velikostMapy);
        r.setWidth(200);
        r.setFill(Color.WHITE);
        r.setLayoutX(velikostMapy);
        r.setLayoutY(0);

        int vlevo;
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int prumer;
        int max;
        int min;
        int vyska;

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(MIN + schodek);

        for (int i = 0; i<mapa.length;i++){
            for (int j = 0;j<mapa.length;j++){
                //Jestli je to prvni bod
                if (i==0 && j==0){
                    Bod zakladniBod = new Bod(0, 0, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    prumer = zakladniVyska.getvalueOfVyska();
                }
                //Jestli je to prvni radek
                else if (i==0){
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                    prumer = vlevo;
                }
                //jestli je to prvni bod jakehokoli radku
                else if (j==0){
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVpravo)/2;

                }
                //jestli je to posledni bod jakehokoli radku
                else if (j==mapa.length-1){
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo)/2;

                }
                //Jestli je to cokoli jineho
                else{
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                }

                max = prumer + schodek;
                min = prumer - schodek;


                if (min<MIN){
                    min = MIN;
                }else if(max>MAX){
                    max = MAX;
                }
                vyska = vytvorVysku(max,min);
                Vyska v = new Vyska(vyska);
                Bod bod = new Bod(i*bodSize, j*bodSize, v);
                mapa[i][j]=bod;
                root.getChildren().addAll(bod);
            }

        }
        MAPA = mapa;

        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100);
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10);
        xKoordinace.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266);
        labelVyska.setFont(newFont);

        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20);
        textXKoordinace.setLayoutX(velikostMapy + 20);

        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113);
        textYKoordinace.setLayoutX(velikostMapy + 20);

        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226);
        textVyska.setLayoutX(velikostMapy + 20);

        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        Button buttonGenerujHorizont = new Button("Pohled ze strany");
        buttonGenerujHorizont.setPrefSize(151, 113);
        buttonGenerujHorizont.setLayoutX(velikostMapy + 24.5);
        buttonGenerujHorizont.setLayoutY(velikostMapy - 18 - 113);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(mapa, rootHorizont, sceneHorizont);

            stageHorizont.show();
        });

        Button generujMapuZnovu = new Button("generuj jinou mapu");
        generujMapuZnovu.setPrefSize(151, 113);
        generujMapuZnovu.setLayoutX(velikostMapy + 24.5);
        generujMapuZnovu.setLayoutY(velikostMapy - 18 - 113 - 18 - buttonGenerujHorizont.getPrefWidth());
        generujMapuZnovu.setOnMouseClicked(e->{
             generujMapu(root);
        });


        Rectangle ctverec = new Rectangle();
        ctverec.setHeight(velikostMapy);
        ctverec.setWidth(velikostMapy);
        ctverec.setLayoutY(0);
        ctverec.setLayoutX(0);
        ctverec.setOpacity(0.0);
        ctverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            getKordY(event.getY());
            System.out.println(" x - " + x + "    " + " y - " + y);
            labelVyska.setText(String.valueOf(mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska()));
        });

        root.getChildren().addAll(r, ctverec, yKoordinace, xKoordinace, labelVyska, textXKoordinace, textYKoordinace, textVyska, buttonGenerujHorizont, generujMapuZnovu);

    }

    /**
     *
     * @tohle chatgpt
     */
    public void generujHorizont(Bod [][] mapa, Pane root, Scene scene){

        Rectangle nalepka = new Rectangle();
        nalepka.setHeight(scene.getHeight());
        nalepka.setWidth(scene.getWidth());
        nalepka.setLayoutY(0);
        nalepka.setLayoutX(0);
        nalepka.setFill(Color.WHITE);
        root.getChildren().add(nalepka);


/**
 * tohle okomentuju
 */     int kolikatyBod = 0;
        int[] horizont = new int[mapa.length]; // Create an array to store the max values
        for (int i = 0; i < mapa.length; i++) {
            int max = Integer.MIN_VALUE; // Initialize max to the smallest possible value  int max = Integer.MIN_VALUE;
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == null){
                    //System.out.println("null");
                }else  if (mapa[i][j].getVyska().getvalueOfVyska() > max) { // Compare each element in the row to find the max
                    max = mapa[i][j].getVyska().getvalueOfVyska(); // Update max if a bigger value is found
                }
            }
            horizont[i] = max; // Store the max value in the maxValues array
        }

        Rectangle r = new Rectangle();
        r.setLayoutX(0);
        r.setLayoutY(0);
        r.setFill(Color.WHITE);
        r.setWidth(velikostMapy);
        r.setHeight(velikostHorizontuY);



        int m = 0;
        for (int i = 0;i<horizont.length; i++){
            if (horizont[i]>m){
                m=horizont[i];
            }
        }

        double dilek = (double)(velikostHorizontuY)/(double)(MAX);

        for (int i = 0;i<horizont.length;i++){
            Vyska vyska = new Vyska(horizont[i]);
            HBod hBod = new HBod(i,dilek, vyska);
            root.getChildren().addAll(hBod);
        }

        Line meritko = new Line();
        meritko.setStroke(Color.BLACK);
        meritko.setStrokeWidth(1);
        meritko.setStartX(velikostHorizontuX - meritko.getStrokeWidth() - 5 - 38);
        meritko.setStartY(velikostHorizontuY);
        meritko.setEndX(velikostHorizontuX - meritko.getStrokeWidth() - 5 - 38);
        meritko.setEndY(0);

        Text text1 = new Text("0");
        text1.setLayoutY(velikostHorizontuY - 5 + 4);
        text1.setLayoutX(meritko.getStartX()-20 - 20);

        Text text2 = new Text(String.valueOf(MAX/4));
        text2.setLayoutY(velikostHorizontuY - velikostHorizontuY/4 + 4);
        text2.setLayoutX(meritko.getStartX()-20 - 20);

        Text text3 = new Text(String.valueOf(MAX/2));
        text3.setLayoutY(velikostHorizontuY/2 + 4);
        text3.setLayoutX(meritko.getStartX()-20 - 20);

        Text text4 = new Text(String.valueOf(3*MAX/4));
        text4.setLayoutY(velikostHorizontuY/4 + 4);
        text4.setLayoutX(meritko.getStartX()-20 - 20);

        Text text5 = new Text(String.valueOf(MAX));
        text5.setLayoutY(14);
        text5.setLayoutX(meritko.getStartX()-20 - 20);

        Line odrazka1 = new Line();
        odrazka1.setStroke(Color.BLACK);
        odrazka1.setStrokeWidth(1);
        odrazka1.setStartX(meritko.getStartX()-5);
        odrazka1.setStartY(velikostHorizontuY - 5);
        odrazka1.setEndX(meritko.getStartX()+5);
        odrazka1.setEndY(velikostHorizontuY - 5);

        Line odrazka2 = new Line();
        odrazka2.setStroke(Color.BLACK);
        odrazka2.setStrokeWidth(1);
        odrazka2.setStartX(meritko.getStartX()-5);
        odrazka2.setStartY(velikostHorizontuY - velikostHorizontuY/4);
        odrazka2.setEndX(meritko.getStartX()+5);
        odrazka2.setEndY(velikostHorizontuY - velikostHorizontuY/4);

        Line odrazka3 = new Line();
        odrazka3.setStroke(Color.BLACK);
        odrazka3.setStrokeWidth(1);
        odrazka3.setStartX(meritko.getStartX()-5);
        odrazka3.setStartY(velikostHorizontuY/2);
        odrazka3.setEndX(meritko.getStartX()+5);
        odrazka3.setEndY(velikostHorizontuY/2);

        Line odrazka4 = new Line();
        odrazka4.setStroke(Color.BLACK);
        odrazka4.setStrokeWidth(1);
        odrazka4.setStartX(meritko.getStartX()-5);
        odrazka4.setStartY(velikostHorizontuY/4);
        odrazka4.setEndX(meritko.getStartX()+5);
        odrazka4.setEndY(velikostHorizontuY/4);

        Line odrazka5 = new Line();
        odrazka5.setStroke(Color.BLACK);
        odrazka5.setStrokeWidth(1);
        odrazka5.setStartX(meritko.getStartX()-5);
        odrazka5.setStartY(5);
        odrazka5.setEndX(meritko.getStartX()+5);
        odrazka5.setEndY(5);


        Label rada = new Label("Řada: ");
        rada.setLayoutX(scene.getWidth() -120);
        rada.setLayoutY(velikostHorizontuY/6);
        Font currentFont = rada.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 6);
        rada.setFont(newFont);

        Label textRada = new Label();
        textRada.setLayoutX(scene.getWidth()-70);
        textRada.setLayoutY(velikostHorizontuY/6);
        textRada.setFont(newFont);

        Label labelVyska1 = new Label("Výška: ");
        labelVyska1.setLayoutY(velikostHorizontuY/3);
        labelVyska1.setLayoutX(scene.getWidth() -120);
        labelVyska1.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.setLayoutY(velikostHorizontuY/3);
        labelVyska.setLayoutX(scene.getWidth()-70);
        labelVyska.setFont(newFont);

        Rectangle souradnicovyCtverec = new Rectangle();
        souradnicovyCtverec.setHeight(scene.getHeight());
        souradnicovyCtverec.setWidth(velikostMapy);
        souradnicovyCtverec.setLayoutY(0);
        souradnicovyCtverec.setLayoutX(0);
        souradnicovyCtverec.setOpacity(0.0);
        souradnicovyCtverec.setOnMouseClicked((MouseEvent event)->{
            textRada.setText(String.valueOf(getKordX(event.getX())));
            labelVyska.setText(String.valueOf(horizont[getKordX(event.getX())]));
        });



        root.getChildren().addAll( meritko, odrazka1, odrazka2, odrazka3, odrazka4, odrazka5,text1,text2,text3,text4,text5, rada, textRada, labelVyska, labelVyska1, souradnicovyCtverec  );
    }

    public void scenaVytvorMapu(Pane root){
        //vytvori ctverec ktery prekryje to co tam je od minule
        Rectangle nalepka = new Rectangle(root.getScene().getWidth(), root.getScene().getHeight());
        nalepka.setFill(Color.WHITE);
        nalepka.setLayoutX(0);
        nalepka.setLayoutY(0);


        //vytvori label ktery ukazuje koordinace Y
        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100);
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        //vytvori label ktery ukazuje koordinace X
        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10);
        xKoordinace.setFont(newFont);

        //vytvori label ktery ukazuje vysku zvoleneho bodu
        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266);
        labelVyska.setFont(newFont);

        //Ten text na koordinace x
        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20);
        textXKoordinace.setLayoutX(velikostMapy + 20);

        //text na souradnice Y
        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113);
        textYKoordinace.setLayoutX(velikostMapy + 20);

        //text na vysku
        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226);
        textVyska.setLayoutX(velikostMapy + 20);

        //vytvari root stage a scene na horizont
        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        //tlacitko ktere kdyz zmacknu generuje horizont
        Button buttonGenerujHorizont = new Button("Pohled ze strany");
        buttonGenerujHorizont.setPrefSize(151, 113);
        buttonGenerujHorizont.setLayoutX(velikostMapy + 24.5);
        buttonGenerujHorizont.setLayoutY(velikostMapy - 18 - 113);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(MAPA,rootHorizont,sceneHorizont);
            stageHorizont.show();
        });

        //tlacitko ktere vytvori novou prazdnou slepou mapu
        Button buttonGenerujJinouMapu = new Button("generuj jinou mapu");
        buttonGenerujJinouMapu.setPrefSize(151, 113);
        buttonGenerujJinouMapu.setLayoutX(buttonGenerujHorizont.getLayoutX());
        buttonGenerujJinouMapu.setLayoutY(buttonGenerujHorizont.getLayoutY()-5-buttonGenerujJinouMapu.getPrefHeight());


        //misto kte uzivatel napise vysku
        TextField integerTextField = new TextField();
        integerTextField.setAlignment(Pos.CENTER);
        integerTextField.setPrefSize(151,56);
        integerTextField.setLayoutX(buttonGenerujHorizont.getLayoutX());
        integerTextField.setLayoutY(buttonGenerujJinouMapu.getLayoutY() - 5 - buttonGenerujHorizont.getPrefHeight() - 5 - integerTextField.getPrefHeight());

        // Add key event filter to restrict input to integers within the specified range
        integerTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = integerTextField.getText() + event.getCharacter();
            if (!isInputValid(input)) {
                event.consume();
            }
        });

        //ctverec na ktery kdyz uzivatel klikne tak se mu zobrazi souradnice kde klikl
        Rectangle souradnicovyCtverec = new Rectangle(velikostMapy,velikostMapy);
        souradnicovyCtverec.setLayoutY(0);
        souradnicovyCtverec.setLayoutX(0);
        souradnicovyCtverec.setOpacity(0.0);


        Vyska vyska = new Vyska();
        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];



        //tlacitko ktere prida body do pole (krome jinych)
        Button buttonZvolitVysku = new Button("Zvolit Tento Bod");
        buttonZvolitVysku.setPrefSize(buttonGenerujHorizont.getPrefWidth(),buttonGenerujHorizont.getPrefHeight()/2);
        buttonZvolitVysku.setLayoutY(buttonGenerujJinouMapu.getLayoutY() - 5 - buttonZvolitVysku.getPrefHeight());
        buttonZvolitVysku.setLayoutX(buttonGenerujHorizont.getLayoutX());


        Button buttonGenerujCustomMapu = new Button("Generuj Mapu");
        buttonGenerujCustomMapu.setPrefSize(buttonZvolitVysku.getPrefWidth(),buttonZvolitVysku.getPrefHeight());
        buttonGenerujCustomMapu.setLayoutY(buttonZvolitVysku.getLayoutY() - 5 - buttonGenerujCustomMapu.getPrefHeight());
        buttonGenerujCustomMapu.setLayoutX(buttonZvolitVysku.getLayoutX());

        buttonGenerujCustomMapu.setOnMouseClicked(e->{
            root.getChildren().removeAll(souradnicovyCtverec);
            setMapa(mapa,x,y,vyska,root,4);

            root.getChildren().add(souradnicovyCtverec);

        });

        buttonZvolitVysku.setOnMouseClicked(e->{
            System.out.println("buttonZvolitVysku.setOnMouseClicked");
            if (!Objects.equals(integerTextField.getText(), "")){

                if (pocet< (poleVybranychBodu).length){
                    System.out.println("pocet: " + pocet + " x = " + x + " y =  " + y);
                    poleVybranychBodu[pocet] = new Bod(x,  y, new Vyska(Integer.parseInt(integerTextField.getText())));
                    System.out.println(poleVybranychBodu[pocet]);
                    getPocet(1);
                }
                if (pocet == 1){
                    root.getChildren().add(buttonGenerujCustomMapu);
                }

            }

            else{

                System.out.println("Neni zadana Vyska" );

            }

        });

        buttonGenerujJinouMapu.setOnMouseClicked(e->{
            scenaVytvorMapu(root);
            getPocet(0);
            for (int i = 0; i < poleVybranychBodu.length; i++) {
                poleVybranychBodu[i] = null;
            }
            root.getChildren().removeAll(buttonGenerujCustomMapu);
        });

        souradnicovyCtverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            System.out.println(" x : " + x + "    " + " y : " + y);

            if (MAPA!=null && MAPA[getKordX(event.getX())][getKordY(event.getY())]!=null){
                labelVyska.setText(String.valueOf(MAPA[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska()));
            }else{

                labelVyska.setText("null");
            }


        });

        root.getChildren().addAll(nalepka,souradnicovyCtverec, buttonGenerujJinouMapu, buttonGenerujHorizont, textVyska,textYKoordinace, textXKoordinace ,  labelVyska,
                xKoordinace, yKoordinace, integerTextField, buttonZvolitVysku);

    }

    public Bod[][] vygenerujCustomMapu1(int x, int y, Bod[][] mapa, Vyska vyska) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=9;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 0; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                            }else if(j ==  1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                max = dole + schodek;
                                min = dole - schodek;
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                max = doleVlevo + schodek;
                                min = doleVlevo - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 1){

                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVpravo + dole)/2 + schodek;
                                min = (doleVpravo + dole)/2 - schodek;

                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole)/2 + schodek;
                                min = (doleVlevo + dole)/2 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVlevo + schodek;
                                min = doleVlevo - schodek;
                            //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo)/3 + schodek;
                                min = (doleVlevo + dole + doleVpravo)/3 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = vlevo + schodek;
                                min = vlevo - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = nahoreVlevo + schodek;
                                min = nahoreVlevo - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2-schodek;

                                //Jestli je to predposledni z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + vlevo)/2 + schodek;
                                min = (nahoreVlevo + vlevo)/2 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();

                                max = nahoreVlevo + schodek;
                                min = nahoreVlevo - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + nahoreVlevo + vlevo)/3 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = nahore + schodek;
                                min = nahore - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                max = nahoreVpravo + schodek;
                                min = nahoreVpravo - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo)/2 + schodek;
                                min = (nahore + nahoreVlevo)/2-schodek;
                            //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo)/2 + schodek;
                                min = (nahore + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else if (j == 2*i){

                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = nahoreVpravo + schodek;
                                min = nahoreVpravo - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore)/3 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                //hrana vpravo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j < 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                            max = vpravo + schodek;
                            min = vpravo-schodek;

                        }else{
                            //Jestli je to predposledni bod z dola
                            if (j == 1){
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2-schodek;
                            //Jestli je to predposledni z hora
                            }else if (j == 2*i){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo)/2 + schodek;
                                min = (dole + doleVpravo)/2 - schodek;
                            //Jestli je to nekde uprostred
                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo)/3 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY-1; i++) {
            t++;
            for (int j = zacatekX;j<konecX-1;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                if (vetsiMapa[i][j]!=null){
                    mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
                }


            }
            k =-1;
        }

        return mapa;
    }

    public Bod[][] vygenerujCustomMapu2(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=velikostPole/2;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 0; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                            }else if(j ==  1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + dole)/2 + schodek;
                                min = (vlevo + dole)/2 - schodek;
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX+1][kordY+2].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX+2][kordX+1].getVyska().getvalueOfVyska();
                                max = (doleVpravo + dole + vlevo)/3 + schodek;
                                min = (doleVpravo + dole + vlevo)/3 - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 1){

                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (doleVpravo + dole + vlevo)/3 + schodek;
                                min = (doleVpravo + dole + vlevo)/3 - schodek;

                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + vlevo)/3 + schodek;
                                min = (doleVlevo + dole + vlevo)/3 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo + vlevo)/4 + schodek;
                                min = (doleVlevo + dole + doleVpravo + vlevo)/4 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                    //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + nahore)/2 + schodek;
                                min = (vlevo + nahore)/2 - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + nahore)/2 + schodek;
                                min = (nahoreVlevo + nahore)/2 - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo + nahore)/3 + schodek;
                                min = (vlevo + doleVlevo + nahore)/3 - schodek;

                                //Jestli je to predposledni z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo + vlevo+ nahore)/3 + schodek;
                                min = (nahoreVlevo + vlevo+ nahore)/3 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo+ nahore)/2 + schodek;
                                min = (nahoreVlevo+ nahore)/2 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + vpravo)/2 + schodek;
                                min = (nahore + vpravo)/2 - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (nahoreVpravo + vpravo)/2 + schodek;
                                min = (nahoreVpravo + vpravo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVlevo + vpravo )/3-schodek;
                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVpravo + vpravo )/3 - schodek;
                                //Jestli je posledni Bod z leva
                            }else if (j == 2*i){
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana vlevo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j < 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                            vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                            nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                            max = (vpravo + dole + nahore)/3 + schodek;
                            min = (vpravo + dole + nahore)/3 - schodek;

                        }else{
                            //Jestli je to predposledni bod z dola
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo+ doleVpravo)/3 + schodek;
                                min = (vpravo + nahoreVpravo+ doleVpravo)/3 - schodek;

                                //Jestli je to predposledni z hora
                            }else if (j == 2*i){
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo + nahore + nahoreVpravo)/4 + schodek;
                                min = (dole + doleVpravo + nahore + nahoreVpravo)/4 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo + dole)/4 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo + dole)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY; i++) {
            t++;
            for (int j = zacatekX;j<konecX;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
            }
            k =-1;
        }

        return mapa;
    }

    public Bod[][] vygenerujCustomMapu3(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=velikostPole/2;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                max = dole + schodek;
                                min = dole - schodek;

                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX+1][kordY+2].getVyska().getvalueOfVyska();

                                max = (doleVpravo + dole)/2 + schodek;
                                min = (doleVpravo + dole)/2 - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + vlevo)/3 + schodek;
                                min = (doleVlevo + dole + vlevo)/3 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo + vlevo)/4 + schodek;
                                min = (doleVlevo + dole + doleVpravo + vlevo)/4 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                    //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + nahore+ nahoreVlevo)/3 + schodek;
                                min = (vlevo + nahore+ nahoreVlevo)/3 - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + nahore)/2 + schodek;
                                min = (nahoreVlevo + nahore)/2 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo + nahore+ nahoreVlevo)/4 + schodek;
                                min = (vlevo + doleVlevo + nahore+ nahoreVlevo)/4 - schodek;

                                //Jestli je to druhy z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo + vlevo+ nahore)/3 + schodek;
                                min = (nahoreVlevo + vlevo+ nahore)/3 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo+ nahore)/2 + schodek;
                                min = (nahoreVlevo+ nahore)/2 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + vpravo + nahoreVpravo)/3 + schodek;
                                min = (nahore + vpravo + nahoreVpravo)/3 - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (nahoreVpravo + vpravo)/2 + schodek;
                                min = (nahoreVpravo + vpravo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo + vpravo + nahoreVpravo)/4 + schodek;
                                min = (nahore + nahoreVlevo + vpravo + nahoreVpravo)/4 - schodek;
                                //Jestli je to druhy z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVpravo + vpravo )/3 - schodek;
                                //Jestli je posledni Bod z leva
                            }else if (j == 2*i){
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana vlevo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j <= 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            if (j == 1){

                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = (vpravo + dole + doleVpravo + nahoreVpravo)/4 + schodek;
                                min = (vpravo + dole + doleVpravo + nahoreVpravo)/4 - schodek;

                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = (vpravo + dole + doleVpravo)/3 + schodek;
                                min = (vpravo + dole + doleVpravo)/3 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z dola
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo+ doleVpravo)/3 + schodek;
                                min = (vpravo + nahoreVpravo+ doleVpravo)/3 - schodek;

                                //Jestli je to predposledni z hora
                            }else if (j == 2*i){

                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo + vpravo)/3 + schodek;
                                min = (dole + doleVpravo + vpravo)/3 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo + dole)/4 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo + dole)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY; i++) {
            t++;
            for (int j = zacatekX;j<konecX;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
            }
            k =-1;
        }

        return mapa;
    }

    public Bod[][] vygenerujCustomMapu4(Bod[] bodyNaMape){

        System.out.println("Zacinam prochazet metodu vygenerujCustomMapu 4");
        int vlevo;
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int vpravo;
        int vpravoDole;
        int dole;
        int vlevoDole;
        int prumer;
        int max;
        int min;
        int vyska;

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(MIN + schodek);
        Vyska vyska1 = new Vyska();

        //zjistovani nejvyssi vysky
        int nejvyssiVyska = Integer.MIN_VALUE;
        int nejnizsiVyska = Integer.MAX_VALUE;

        for (int i = 0; i < bodyNaMape.length; i++) {
            if (bodyNaMape[i]!=null){
                if (nejvyssiVyska<bodyNaMape[i].getVyska().getvalueOfVyska()){
                    nejvyssiVyska = bodyNaMape[i].getVyska().getvalueOfVyska();
                }
                if (nejnizsiVyska > bodyNaMape[i].getVyska().getvalueOfVyska()){
                    nejnizsiVyska = bodyNaMape[i].getVyska().getvalueOfVyska();
                }
            }


        }

        //vytvori mapu
        for (int i = 0; i<mapa.length;i++){
            for (int j = 0;j<mapa.length;j++){
                //Jestli je to prvni bod
                if (i==0 && j==0){
                    Bod zakladniBod = new Bod(0, 0, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    prumer = zakladniVyska.getvalueOfVyska();
                }
                //Jestli je to prvni radek
                else if (i==0){
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                    prumer = vlevo;
                }
                //jestli je to prvni bod jakehokoli radku
                else if (j==0){
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVpravo)/2;

                }
                //jestli je to posledni bod jakehokoli radku
                else if (j==mapa.length-1){
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo)/2;

                }
                //Jestli je to cokoli jineho
                else{
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                }

                max = prumer + schodek;
                min = prumer - schodek;

                if (min<MIN){
                    min = MIN;
                }else if(max>nejvyssiVyska + 10*schodek){
                    max = nejvyssiVyska + 10*schodek;
                }
                vyska = vytvorVysku(max,min);
                Vyska v = new Vyska(vyska);
                Bod bod = new Bod(i*bodSize, j*bodSize, v);
                mapa[i][j]=bod;

            }
        }

        //Vytvori kolem vybranych body prazdne misto
        mapa = vytvorMapuSMezerama(mapa,bodyNaMape);


        int prumerNahore = 0;
        int prumerDole = 0;
        int prumerNalevo = 0;
        int prumerNapravo = 0;

        System.out.println("jdu na zjistovani prumeruu");

        //zjistovani prumeru
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = bodyNaMape[p].getCoordY()-11; i <= bodyNaMape[p].getCoordY()+10; i++) {
                    for (int j = bodyNaMape[p].getCoordX()-11; j <= bodyNaMape[p].getCoordX()+10; j++) {
                        if (i == bodyNaMape[p].getCoordY()-11){
                            prumerNahore += bodyNaMape[p].getVyska().getvalueOfVyska();
                        }
                        if (i == bodyNaMape[p].getCoordY()+10){
                            prumerDole += bodyNaMape[p].getVyska().getvalueOfVyska();
                        }
                        if (j == bodyNaMape[p].getCoordX()-11){
                            prumerNalevo += bodyNaMape[p].getVyska().getvalueOfVyska();
                        }
                        if (j == bodyNaMape[p].getCoordX()+10){
                            prumerNapravo += bodyNaMape[p].getVyska().getvalueOfVyska();
                        }
                    }
                }
            }
        }
        prumerDole/=21;
        prumerNahore/=21;
        prumerNalevo /= 21;
        prumerNapravo/=21;



        System.out.println("jdu na vyplnovani mezer");
        //mapa = vyplnMezery3(mapa,bodyNaMape , prumerDole, prumerNahore);

        mapa = vyplnMezery2(mapa,bodyNaMape);

        System.out.println("vracim jiz zmenenou mapu");


        return mapa;
    }

    public Bod[][] vyplnMezery1(Bod[][] mapa, Bod[] bodyNaMape, boolean[] jeVyssiNahore, boolean[] jeVyssiDole){
        int vlevo;
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int vpravo;
        int vpravoDole;
        int dole;
        int vlevoDole;
        int prumer;
        int max;
        int min;


        Vyska vyska = new Vyska();

        for (int i = 0; i < bodyNaMape.length; i++) {

            if (bodyNaMape[i]!=null){
                System.out.println(i + ". bod" + bodyNaMape[i]);
                System.out.println("bodyNaMape[i].getCoordY()-10= " +( bodyNaMape[i].getCoordY()-10));
                System.out.println("bodyNaMape[i].getCoordY() " +( bodyNaMape[i].getCoordY()));
                System.out.println("bodyNaMape[i].getCoordX()-10 " +( bodyNaMape[i].getCoordX()-10));
                System.out.println("bodyNaMape[i].getCoordX()+10 " +( bodyNaMape[i].getCoordX()+10));
                for (int y = bodyNaMape[i].getCoordY()-10; y<bodyNaMape[i].getCoordY();y++){

                    for (int x = bodyNaMape[i].getCoordX()-10; x < bodyNaMape[i].getCoordX()+10; x++) {
                        System.out.println("----------------------------------------");
                        System.out.println(" x = " + x + " y = " + y);
                        nahore = mapa[y-1][x].getVyska().getvalueOfVyska();
                        nahoreVlevo = mapa[y-1][x-1].getVyska().getvalueOfVyska();
                        nahoreVpravo = mapa[y-1][x+1].getVyska().getvalueOfVyska();
                        if (x == bodyNaMape[i].getCoordX()+9){
                            System.out.println("y = " + y + " x-1= " + (x-1) + "ten bod s errorem: " + mapa[y][x-1]);
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            vpravo = mapa[y][x+1].getVyska().getvalueOfVyska();
                            prumer = (vlevo+ vpravo + nahoreVpravo + nahoreVlevo + nahore)/5;
                        }else{
                            //vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            prumer = ( nahoreVpravo + nahoreVlevo + nahore)/3;
                        }

                        if (jeVyssiNahore[i]){
                            vyska.setVyska(vytvorVysku(prumer,prumer-schodek));
                        }else{
                            vyska.setVyska(vytvorVysku(prumer+schodek,prumer));
                        }
                        mapa[y][x] = new Bod(x*bodSize,y*bodSize,vyska);
                        System.out.println("bod je zapsan|" + mapa[x][y]);
                    }
                }

                for (int y =  bodyNaMape[i].getCoordY()+10; y > bodyNaMape[i].getCoordY(); y--) {

                    for (int x = bodyNaMape[i].getCoordX()-10; x < bodyNaMape[i].getCoordX()+10; x++) {

                        if (x == bodyNaMape[i].getCoordX()+9){
                            dole = mapa[y+1][x].getVyska().getvalueOfVyska();
                            vlevoDole = mapa[y+1][x-1].getVyska().getvalueOfVyska();
                            vpravoDole = mapa[y+1][x+1].getVyska().getvalueOfVyska();
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            vpravo = mapa[y][x+1].getVyska().getvalueOfVyska();
                            prumer = (vpravo + vlevo + vlevoDole + vpravoDole + dole)/5;
                        }else{
                            dole = mapa[y+1][x].getVyska().getvalueOfVyska();
                            vlevoDole = mapa[y+1][x-1].getVyska().getvalueOfVyska();
                            vpravoDole = mapa[y+1][x+1].getVyska().getvalueOfVyska();
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            prumer = ( vlevo + vlevoDole + vpravoDole + dole)/4;
                        }

                        if (jeVyssiDole[i]){
                            vyska.setVyska(vytvorVysku(prumer,prumer-schodek));
                        }else{
                            vyska.setVyska(vytvorVysku(prumer+schodek,prumer));
                        }
                        mapa[y][x] = new Bod(x*bodSize,y*bodSize,vyska);
                    }

                }

            }
        }

        return mapa;
    }

    public Bod[][] vyplnMezery2(Bod[][] mapa, Bod[] bodyNaMape){

        int[] prumerNalevo = new int[bodyNaMape.length];
        int[] prumerDole = new int[bodyNaMape.length];
        int[] prumerNahore = new int[bodyNaMape.length];
        int[] prumerNapravo = new int[bodyNaMape.length];

        int[] zmenaNalevo = new int[bodyNaMape.length];
        int[] zmenaDole = new int[bodyNaMape.length];
        int[] zmenaNahore = new int[bodyNaMape.length];
        int[] zmenaNapravo = new int[bodyNaMape.length];

        boolean[] jeVyssiNahore = new boolean[bodyNaMape.length];
        boolean[] jeVyssiNalevo = new boolean[bodyNaMape.length];
        boolean[] jeVyssiNapravo = new boolean[bodyNaMape.length];
        boolean[] jeVyssiDole = new boolean[bodyNaMape.length];

        int[][] bodyNalevo = new  int[bodyNaMape.length][22];
        int[][] bodyDole = new    int[bodyNaMape.length][22];
        int[][] bodyNahore = new  int[bodyNaMape.length][22];
        int[][] bodyNapravo = new int[bodyNaMape.length][22];

        //zaplnovani potrebnych poli
        int h = 0;
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                //zjistovani prumeru kolem prazdne casti mapy
                h=0;
                //zjistovani prumeru nalevo
                for (int i = bodyNaMape[p].getCoordY()-11; i < bodyNaMape[p].getCoordY()+11; i++) {
                    if (mapa[i][bodyNaMape[p].getCoordX()-11]!=null){
                        bodyNalevo[p][h] = mapa[i][bodyNaMape[p].getCoordX()-11].getVyska().getvalueOfVyska();
                        prumerNalevo[p] += mapa[i][bodyNaMape[p].getCoordX()-11].getVyska().getvalueOfVyska();
                    }

                    h++;
                }
                h=0;
                //zjistovani prumeru napravo a zapisovani bodu do pole
                for (int i = bodyNaMape[p].getCoordY()-11; i < bodyNaMape[p].getCoordY()+11; i++) {
                    if (mapa[i][bodyNaMape[p].getCoordX()+11] !=null){
                        bodyNapravo[p][h] = mapa[i][bodyNaMape[p].getCoordX()+11].getVyska().getvalueOfVyska();
                        prumerNapravo[p] += mapa[i][bodyNaMape[p].getCoordX()+11].getVyska().getvalueOfVyska();
                    }

                    h++;
                }
                h=0;
                //zjistovani prumeru nahore
                for (int i = bodyNaMape[p].getCoordX()-11; i < bodyNaMape[p].getCoordX()+11; i++) {
                    if (mapa[bodyNaMape[p].getCoordY()-11][i] != null){
                        bodyNahore[p][h] = mapa[bodyNaMape[p].getCoordY()-11][i].getVyska().getvalueOfVyska();
                        prumerNahore[p] += mapa[bodyNaMape[p].getCoordY()-11][i].getVyska().getvalueOfVyska();
                    }

                    h++;
                }
                h=0;
                //zjistovani prumeru dole
                for (int i = bodyNaMape[p].getCoordX()-11; i < bodyNaMape[p].getCoordX()+11; i++) {
                    if (mapa[bodyNaMape[p].getCoordY()+11][i] !=null){
                        bodyDole[p][h] = mapa[bodyNaMape[p].getCoordY()+11][i].getVyska().getvalueOfVyska();
                        prumerDole[p] += mapa[bodyNaMape[p].getCoordY()+11][i].getVyska().getvalueOfVyska();

                    }
                    h++;
                }
                h=0;
                prumerNalevo[p] /= 21;
                prumerNapravo[p] /= 21;
                prumerNahore[p] /= 21;
                prumerDole[p] /= 21;

                //zjistovani hodnoty o kterou budu pricitat nebo odecitat k vyskam
                zmenaNalevo[p] = (bodyNaMape[p].getVyska().getvalueOfVyska() - prumerNalevo[p])/9;
                zmenaDole[p] = (bodyNaMape[p].getVyska().getvalueOfVyska() - prumerDole[p])/9;
                zmenaNahore[p] = (bodyNaMape[p].getVyska().getvalueOfVyska() - prumerNahore[p])/9;
                zmenaNapravo[p] = (bodyNaMape[p].getVyska().getvalueOfVyska() - prumerNapravo[p])/9;

            }
        }

        int k = 0;
        int l = 0;

        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {
                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {

                        if (k == 10 && l == 10) {

                            mapa[i][j] = new Bod(j*bodSize, i*bodSize, bodyNaMape[p].getVyska());

                        } else if (k < 10 && (l>k-1 && l <= 19-k)) {//body nahore

                            //System.out.println(bodyNahore[p][l]);
                            //System.out.println(" k = " + k + "  zmenaNahore = " + zmenaNahore[p]);

                            mapa[j][i] = new Bod(j*bodSize, i*bodSize, new Vyska(mapa[bodyNaMape[p].getCoordX()-10+l][bodyNaMape[p].getCoordY()-11].getVyska().getvalueOfVyska()+ k*zmenaNahore[p]));
                            //System.out.println("---------------------------------------");

                        } else if (l < 10 && (k>l-1 && k <= 19 - l)) {//Jestli to je nalevo

                            mapa[j][i] = new Bod(j*bodSize, i*bodSize, new Vyska(mapa[bodyNaMape[p].getCoordX()-11][bodyNaMape[p].getCoordY()-10+k].getVyska().getvalueOfVyska()+ l*zmenaNalevo[p]));

                        } else if (l>10 &&((k>(19-l)-1 && k <= 19-(19-l)))) {//jestli je to napravo

                            mapa[j][i] = new Bod(j*bodSize, i*bodSize, new Vyska(mapa[bodyNaMape[p].getCoordX()+11][bodyNaMape[p].getCoordY()-10+k].getVyska().getvalueOfVyska()+ (20-l)*zmenaNapravo[p]));

                        }else{
                            mapa[j][i] = new Bod(j*bodSize, i*bodSize, new Vyska(mapa[bodyNaMape[p].getCoordX()-10+l][bodyNaMape[p].getCoordY()+11].getVyska().getvalueOfVyska()+ (20-k)*zmenaDole[p]));
                        }

                        l++;
                    }
                    k++;
                    l=0;
                }
                k = 0;
            }
        }

        /*for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {
                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {

                        if (i == bodyNaMape[p].getCoordY() && j == bodyNaMape[p].getCoordX()-10){
                            mapa[j][i] = bodyNaMape[p];
                        }else{
                            mapa[j][i] = new Bod(j*bodSize,i*bodSize,new Vyska(vytvorVysku(mapa[j][i].getVyska().getvalueOfVyska()+schodek/2,mapa[j][i].getVyska().getvalueOfVyska()-schodek/2)));
                        }

                    }
                }
            }
        }*/

/*
        //pole ktere vytvori pole map mensiMapa
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){

                for (int i = bodyNaMape[p].getCoordY()-11; i < bodyNaMape[p].getCoordY()+11; i++) {

                    for (int j = bodyNaMape[p].getCoordX()-11; j < bodyNaMape[p].getCoordX()+11; j++) {

                        if (i == bodyNaMape[p].getCoordY() && j == bodyNaMape[p].getCoordX()){//jestli je to zakladni bod
                            mensiMapa[p][k][l] = mapa[i][j];
                            System.out.println(" l = " + l + " k = " + k );
                        }else{
                            mensiMapa[p][k][l] = mapa[i][j];
                        }

                        l++;
                    }
                    k++;
                    l=0;
                }
                k = 0;
            }
        }

        //pole,ktere nastavy vysky v mensich mapach
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p] != null){

                for (int i = 0; i < mensiMapa[p].length; i++) {
                    for (int j = 0; j < mensiMapa[p].length; j++) {

                        if ((i>0 && i< mensiMapa.length-1 && j>0 && j<mensiMapa[p].length-1) && i != mensiMapa.length/2 && j != mensiMapa.length/2){//jestli to nejsou okrajove body ani zakladni bod


                            if (i < mensiMapa[p].length/2 && (j>i-1 && j <= mensiMapa[p].length-i)){//body nad bodem

                                mensiMapa[p][i][j] = new Bod((bodyNaMape[p].getCoordX()-10 + j -1)*bodSize, (bodyNaMape[p].getCoordY()-10 +i-1)*bodSize,
                                        new Vyska( mensiMapa[p][i-1][j].getVyska().getvalueOfVyska() + zmenaNahore[p]));
                            }else if (j < mensiMapa[p].length/2 && (i>j-1 && i <= mensiMapa[p].length - j)){//Jestli to je nalevo

                                mensiMapa[p][i][j] = new Bod((bodyNaMape[p].getCoordX()-10 + j -1)*bodSize, (bodyNaMape[p].getCoordY()-10 +i-1)*bodSize,
                                        new Vyska(mensiMapa[p][i][j-1].getVyska().getvalueOfVyska() + zmenaNalevo[p]));

                            } else if (i>mensiMapa[p].length/2 && (j>i-1 && j <= mensiMapa[p].length-i)) {//jestli trojuhelnik dole

                                mensiMapa[p][i][j] = new Bod((bodyNaMape[p].getCoordX()-10 + j -1)*bodSize, (bodyNaMape[p].getCoordY()-10 +i-1)*bodSize,
                                        new Vyska( prumerDole[p] + i * zmenaDole[p]));

                            }else{

                                mensiMapa[p][i][j] = new Bod((bodyNaMape[p].getCoordX()-10 + j -1)*bodSize, (bodyNaMape[p].getCoordY()-10 +i-1)*bodSize,
                                        new Vyska( prumerNapravo[p] + (mensiMapa[p].length - 2 - j) * zmenaNapravo[p]));
                            }


                        }

                    }
                }
            }
        }


        k = 0;
        l = 0;
        //cyklus ktery vyplnuje prazdne pole v mape
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){

                for (int i = bodyNaMape[p].getCoordY()-11; i < bodyNaMape[p].getCoordY()+11; i++) {

                    for (int j = bodyNaMape[p].getCoordX()-11; j < bodyNaMape[p].getCoordX()+11; j++) {

                            mapa[j][i] = mensiMapa[p][k][l];

                        l++;
                    }
                    k++;
                    l=0;
                }
                k = 0;
            }
        }
        */
        for (int i = 0; i < bodyNaMape.length; i++) {
            System.out.println("i. bod  = " + bodyNaMape[i]);
        }
        return mapa;
    }
    
    public Bod[][] vyplnMezery3(Bod[][] mapa, Bod[] bodyNaMape, int prumerDole, int prumerNahore, int prumerNalevo, int prumerNapravo){
        //vytvoreni nutnych poli

        int[] zmenaNalevo = new int[bodyNaMape.length];
        int[] zmenaDole = new int[bodyNaMape.length];
        int[] zmenaNahore = new int[bodyNaMape.length];
        int[] zmenaNapravo = new int[bodyNaMape.length];

        boolean[] jeVyssiNahore = new boolean[bodyNaMape.length];
        boolean[] jeVyssiNalevo = new boolean[bodyNaMape.length];
        boolean[] jeVyssiNapravo = new boolean[bodyNaMape.length];
        boolean[] jeVyssiDole = new boolean[bodyNaMape.length];

        //urcovani o kolik se musi menit vyska nove pridanych bodu aby kdyz dorazi k zakladnimu bodu se jejich vysky o moc nelisily
        // take urceni jestli am stoupat nebo klesat pomoci pole booleanu
        for (int i = 0; i < bodyNaMape.length; i++) {
            if (bodyNaMape[i]!=null){
                zmenaNalevo[i] = Math.abs((bodyNaMape[i].getVyska().getvalueOfVyska() - prumerNalevo)/10);
                zmenaDole[i] = Math.abs((bodyNaMape[i].getVyska().getvalueOfVyska() - prumerDole)/10);
                zmenaNahore[i] = Math.abs((bodyNaMape[i].getVyska().getvalueOfVyska() - prumerNahore)/10);
                zmenaNapravo[i] = Math.abs((bodyNaMape[i].getVyska().getvalueOfVyska() - prumerNapravo)/10);

                if (prumerDole>bodyNaMape[i].getVyska().getvalueOfVyska()){
                    jeVyssiDole[i] = true;
                }else{
                    jeVyssiDole[i] = false;
                }

                if (prumerNahore>bodyNaMape[i].getVyska().getvalueOfVyska()){
                    jeVyssiNahore[i] = true;
                }else{
                    jeVyssiNahore[i] = false;
                }

                if (prumerNalevo>bodyNaMape[i].getVyska().getvalueOfVyska()){
                    jeVyssiNalevo[i] = true;
                }else{
                    jeVyssiNalevo[i] = false;
                }

                if (prumerNapravo>bodyNaMape[i].getVyska().getvalueOfVyska()){
                    jeVyssiNapravo[i] = true;
                }else{
                    jeVyssiNapravo[i] = false;
                }
            }
        }


        //inicializace nutnych promennych
        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        int pocatekX;
        int pocatekY;
        Bod[][] mensiMapa = new Bod[20][20];
        int x;
        int y;
        int prumer;
        Bod[][][] poleMap = new Bod[bodyNaMape.length][mensiMapa.length][mensiMapa.length];


        //vytvareni minimapy ke kazdemu bodu
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p] != null){
                mensiMapa[10][10] = bodyNaMape[p];

                //pruchazim pole diagonalne do leva nahoru od pocatecniho bodu
                for (int i = 1; i < 10; i++) {

                    for (int poc =1;poc<=4;poc++){

                        //hrana nahore         smer doprava
                        if (poc == 1){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("poc: " + poc);

                            pocatekX = 10-i;
                            pocatekY = 10-i;

                            for (int j = 1; j <= 2*i; j++) {
                                x = pocatekX + j;
                                y = pocatekY;

                                //jestli jsem vzdalen jen jeden bod od pocatecniho bodu
                                if (i==1){
                                    if (j == 1){

                                        dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                        prumer = dole;

                                    }else{
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        prumer = (vlevo + doleVlevo)/2;
                                    }

                                }else{
                                    //Jestli je to posledni bod z leva
                                    if (j == 1){
                                        doleVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                        prumer = (doleVpravo + dole)/2;

                                        //Jestli je to predposledni z prava
                                    }else if (j == 2*i-1){

                                        dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        prumer = (doleVlevo + dole + vlevo)/3 ;

                                        //Jestli je to posledni z prava
                                    }else if (j == 2*i){

                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        prumer = (vlevo + doleVlevo)/2;
                                        //Jestli je to nekde uprostred
                                    } else{
                                        dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        doleVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        prumer = (doleVlevo + dole + doleVpravo + vlevo)/4;
                                    }
                                }
                                String x1 = "10-i+j " + (10 - i + j) + "      10-i " + (10 - i);
                                mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(8000));
                                poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(8000));
                                /*

                                if (jeVyssiNahore[p]){
                                    System.out.println(" Je: "  + j + " I: " + i);
                                    System.out.println(x1);
                                    mensiMapa[y][x] = new Bod(10-i+j,10-i,new Vyska(prumer+zmenaNahore[p]));
                                    poleMap[p][y][x] = new Bod(10-i+j,10-i,new Vyska(prumer+zmenaNahore[p]));
                                }else{
                                    System.out.println(x1);
                                    mensiMapa[y][x] = new Bod(10-i+j,10-i,new Vyska(prumer-zmenaNahore[p]));
                                    poleMap[p][y][x] = new Bod(10-i+j,10-i,new Vyska(prumer-zmenaNahore[p]));
                                }

                                 */
                            }

                        //hrana napravo        smer dolu
                        }else if (poc == 2){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("poc: " + poc);
                            pocatekX = 10+i;
                            pocatekY = 10-i;

                            for (int j = 1; j <= 2*i; j++) {
                                x = pocatekX;
                                y = pocatekY + j;

                                //jestli jsem vzdalen pouze jeden bod d pocatecniho bodu
                                if (i ==1){
                                    if (j == 1){
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();

                                        prumer = (vlevo + nahoreVlevo + nahore)/3;
                                    }else{
                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        prumer = ( nahoreVlevo + nahore)/2;
                                    }
                                }else{
                                    if (j == 1){
                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        prumer = (vlevo + doleVlevo + nahore + nahoreVlevo)/4;

                                        //Jestli je to predposledni z dola
                                    }else if (j == 2*i-1){
                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        prumer = (nahoreVlevo + vlevo+ nahore)/3;

                                        //Jestli je to posledni z dola
                                    }else if (j == 2*i){

                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        prumer = (nahoreVlevo+ nahore)/2;

                                        //Jestli je to nekde uprostred
                                    }else{
                                        doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
                                        nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
                                        vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        prumer = (doleVlevo + nahoreVlevo + vlevo + nahore)/4;

                                    }
                                }
                                mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(8000));
                                poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(8000));
                                /*


                                if (jeVyssiNapravo[p]){
                                    mensiMapa[y][x] = new Bod(10+i,10-i+j,new Vyska(prumer+zmenaNapravo[p]));
                                    poleMap[p][y][x] = new Bod(10+i,10-i+j,new Vyska(prumer+zmenaNapravo[p]));
                                }else{
                                    mensiMapa[y][x] = new Bod(10+i,10-i+j,new Vyska(prumer-zmenaNapravo[p]));
                                    poleMap[p][y][x] = new Bod(10+i,10-i+j,new Vyska(prumer-zmenaNapravo[p]));
                                }

                                 */

                            }

                        //hrana dole          smer doleva
                        }else if (poc == 3){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("poc: " + poc);

                            pocatekX = 10+i;
                            pocatekY = 10+i;

                            for (int j = 1; j <= 2*i; j++) {
                                x = pocatekX - j;
                                y = pocatekY;

                                //jestli jsem od pccatecniho bodu vzdalen pouze jeden bod
                                if (i == 1) {

                                    if (j == 1) {
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                        prumer = (nahore + vpravo) / 2;
                                    } else {
                                        nahoreVpravo = mensiMapa[y - 1][x + 1].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                        prumer = (nahoreVpravo + vpravo) / 2;
                                    }

                                } else {
                                    if (j == 1) {
                                        nahoreVlevo = mensiMapa[y - 1][x - 1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y - 1][x].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y ][x+ 1].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y - 1][x + 1].getVyska().getvalueOfVyska();
                                        prumer = (nahore + nahoreVlevo + vpravo + nahoreVpravo) / 4;

                                        //Jestli je to predposledni z prava
                                    } else if (j == 2 * i - 1) {
                                        nahore = mensiMapa[y - 1][x].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y - 1][x + 1].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y ][x+ 1].getVyska().getvalueOfVyska();
                                        prumer = (nahore + nahoreVpravo + vpravo) / 3;

                                        //Jestli je posledni Bod z leva
                                    } else if (j == 2 * i) {
                                        vpravo = mensiMapa[y][x + 1].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y - 1][x + 1].getVyska().getvalueOfVyska();
                                        prumer = (vpravo + nahoreVpravo) / 2;

                                        //Jestli je to nekde uprostred
                                    } else {
                                        vpravo = mensiMapa[y ][x+ 1].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y - 1][x + 1].getVyska().getvalueOfVyska();
                                        nahoreVlevo = mensiMapa[y - 1][x - 1].getVyska().getvalueOfVyska();
                                        nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
                                        prumer = (nahoreVpravo + nahoreVlevo + nahore + vpravo) / 4;
                                    }
                                }
                                mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(8000));
                                poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(8000));
                                /*

                                //vytvareni bodu
                                if (jeVyssiDole[p]) {
                                    mensiMapa[y][x] = new Bod( 10+i-j,  10+i, new Vyska(prumer + zmenaDole[p]));
                                    poleMap[p][y][x] = new Bod(10+i-j,  10+i, new Vyska(prumer + zmenaDole[p]));
                                } else {
                                    mensiMapa[y][x] = new Bod( 10+i-j,  10+i, new Vyska(prumer - zmenaDole[p]));
                                    poleMap[p][y][x] = new Bod(10+i-j,  10+i, new Vyska(prumer - zmenaDole[p]));
                                }

                                 */
                            }

                        //hrana nalevo        smer nahoru
                        }else{
                            System.out.println("------------------------------------------------------------");
                            System.out.println("poc: " + poc);
                            pocatekX = 10-i;
                            pocatekY = 10+i;

                            for (int j = 1; j <= 2*i; j++) {
                                x = pocatekX;
                                y = pocatekY-j;
                                //jestli Je to ten prvni cyklus
                                if (i == 1){
                                    dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + dole)/2;

                                }else{
                                    //Jestli je to predposledni bod z dola
                                    if (j == 1){
                                        doleVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                        prumer = (vpravo + nahoreVpravo+ doleVpravo)/3;


                                        //Jestli je to predposledni z hora
                                    }else if (j == 2*i){
                                        nahoreVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        dole = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                        doleVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        prumer = (dole + doleVpravo  + nahoreVpravo)/3;


                                        //Jestli je to nekde uprostred
                                    }else{
                                        dole = mensiMapa[y][x+1].getVyska().getvalueOfVyska();
                                        nahoreVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        vpravo = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
                                        doleVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
                                        prumer = (nahoreVpravo + vpravo + doleVpravo + dole)/4;

                                    }
                                }
                                mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(8000));
                                poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(8000));
                                /*
                                if (jeVyssiNalevo[p]) {

                                    mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(prumer + zmenaNalevo[p]));
                                    poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(prumer + zmenaNalevo[p]));
                                } else {
                                    mensiMapa[y][x] = new Bod( 10-i, 10+i-j, new Vyska(prumer - zmenaNalevo[p]));
                                    poleMap[p][y][x] = new Bod(10-i, 10+i-j, new Vyska(prumer - zmenaNalevo[p]));
                                }

                                 */

                            }
                        }
                    }
                }
            }
        }

        int[] zacatekX = new int[bodyNaMape.length];
        int[] konecX = new int[bodyNaMape.length];
        int[] zacatekY = new int[bodyNaMape.length];
        int[] konecY = new int[bodyNaMape.length];

        //zde vytvor zacatky a konce
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                zacatekX[p] = bodyNaMape[p].getCoordX()-10;
                konecX[p] = bodyNaMape[p].getCoordX()+9;
                zacatekY[p] = bodyNaMape[p].getCoordY()-10;
                konecY[p] = bodyNaMape[p].getCoordY()+9;
            }
        }

        //zde dej body z mensich map do normalni mapy
        for (int p = 0; p < bodyNaMape.length; p++) {
            for (int i = 0; i < poleMap[p].length; i++) {
                for (int j = 0; j < poleMap[p].length; j++) {
                    if (poleMap[p][i][j] == null){
                        System.out.println(" x = " + j+" y = " + i );
                        System.out.println("null");
                        System.out.println("--------------------------------");
                    }
                }
            }
        }

        int i1 = 0;
        int j1 = 0;
        for (int p = 0; p < bodyNaMape.length; p++) {
            for (int i = zacatekY[p]; i < konecY[p]; i++) {
                for (int j = zacatekX[p]; j < konecX[p]; j++) {
                    System.out.println(" i  = " + i + " j " + j + " " + mapa[i][j]);
                    System.out.println();
                    System.out.println(" i1 = "+i1+" j1 =   "+j1 + " " + poleMap[p][i1][j1]);
                    System.out.println(" poleMap[p][i1][j1] = " + poleMap[p][i1][j1]);
                    System.out.println("Pred PRED PRIDANIM");

                    if (poleMap[p][i1][j1]== null){

                        if (j1-1 == -1){
                            poleMap[p][i1][j1] = poleMap[p][i1][j1+1];
                            System.out.println("zmenil jsem tenhle bod: " +poleMap[p][i1][j1] );
                            System.out.println("tento za tento bod: " + poleMap[p][i1][j1+1]);
                        }else{
                            poleMap[p][i1][j1] = poleMap[p][i1][j1-1];

                            System.out.println("zmenil jsem tenhle bod: " +poleMap[p][i1][j1] );
                            System.out.println("tento za tento bod: " + poleMap[p][i1][j1-1]);
                        }

                    }else{
                        mapa[i][j] = poleMap[p][i1][j1];
                        mapa[i][j] = new Bod(i+bodSize,j*bodSize, poleMap[p][i1][j1].getVyska());
                    }

                    System.out.println("PO PRIDANI");
                    System.out.println("mapa i j " + mapa[i][j]);
                    System.out.println("-----------------------------------------------");

                    j1++;
                }
                j1=0;
                i1++;
            }
            i1=0;
        }
        return mapa;
    }



    public Bod[][] vytvorMapuSMezerama(Bod[][] mapa, Bod[] bodyNaMape){

        System.out.println("tvorim mezery");


        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {
                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {
                        mapa[j][i] = null;
                    }
                }
            }
        }
        System.out.println("hotovo mezery");



        return mapa;
    }

    private int x;
    private int y;
    public int getKordX(double dX){
        this.x = (int)dX/bodSize;
        return x;
    }
    public int getKordY(double dY){
        this.y = (int)dY/bodSize;
        return y;
    }

    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(min,max);
    }

    public int pocet;
    public void getPocet(int i){
        if (pocet< poleVybranychBodu.length){
            pocet++;
        }
        if (i ==0){
            pocet = 0;
        }
    }

    Bod[][] MAPA;
    public void setMapa(Bod [][] mapa,int x, int y, Vyska vyska, Pane root, int verze){
        if (verze == 1){
            MAPA = vygenerujCustomMapu1(x,y,mapa,vyska);
        }else if(verze == 2){
            MAPA = vygenerujCustomMapu2(x,y,mapa,vyska, root);
        } else if (verze == 3) {
            MAPA = vygenerujCustomMapu3(x,y,mapa,vyska, root);
        }else if (verze == 4){
            MAPA = vygenerujCustomMapu4(poleVybranychBodu);
        }
        System.out.println("pridavanibodu na scenu");

                for (int i = 0; i < MAPA.length; i++) {
                    for (int j =0; j < MAPA.length; j++) {
                        if (MAPA[i][j]!=null){
                            root.getChildren().add(MAPA[i][j]);
                        }

                    }
                }



    }

    public Bod[][] getMAPA(){
        if (MAPA == null){
            System.out.println("neni zadany zakladni bod");
        }
        return MAPA;
    }

    private boolean isInputValid(String input) {
        // Check if input is a valid integer within the specified range
        try {
            int value = Integer.parseInt(input);
            return value >= MIN && value <= MAX;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
/*
dole = mensiMapa[y+1][x].getVyska().getvalueOfVyska();
vlevo = mensiMapa[y][x-1].getVyska().getvalueOfVyska();
doleVlevo = mensiMapa[y+1][x-1].getVyska().getvalueOfVyska();
doleVpravo = mensiMapa[x+1][y+1].getVyska().getvalueOfVyska();
nahoreVlevo = mensiMapa[y-1][x-1].getVyska().getvalueOfVyska();
nahore = mensiMapa[y-1][x].getVyska().getvalueOfVyska();
nahoreVpravo = mensiMapa[y+1][x+1].getVyska().getvalueOfVyska();
vpravo =
 */




