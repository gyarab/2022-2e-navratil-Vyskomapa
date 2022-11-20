package com.example.vyskomapa;

import javafx.scene.paint.Color;

import static com.example.vyskomapa.Vyskomapa.sizeY;
import static com.example.vyskomapa.Vyskomapa.sizeX;
import java.util.IllegalFormatCodePointException;

public class Vyska {
    private  int vyska;
    public Color Barva;

    public Vyska(int vyska) {
        this.vyska = vyska;
    }

    public int getvalueOfVyska(){
        return vyska;
    }

    public Color getBarva() {
        if (vyska<0){
            return Color.WHITE;
        }else if (vyska > 0 && vyska < 50) {
            return Color.LIGHTGRAY;
        }else if (vyska < 100) {
            return Color.DIMGRAY;
        }else if(vyska <150){
            return Color.GRAY;
        }else if (vyska<200){
            return Color.DARKGRAY;
        }else if (vyska<250){
            return Color.LIGHTBLUE;
        }else if (vyska < 300){
            return Color.CORNFLOWERBLUE;
        }else if (vyska<350){
            return Color.DARKBLUE;
        }else if (vyska<400){
            return Color.CADETBLUE;
        }else if (vyska<450){
            return Color.LIGHTSKYBLUE;
        }else if (vyska<500){
            return Color.BLUE;
        }else if (vyska<550){
            return  Color.PINK;
        }else if (vyska<600){
            return Color.DEEPPINK;
        }else if (vyska<1000){
            return Color.ORANGERED;
        }else if (vyska<1500){
            return Color.RED;
        }else if (vyska < 2000){
            return Color.INDIANRED;
        }else if (vyska<2500){
            return Color.DARKRED;
        }else if (vyska<3000){
            return Color.SANDYBROWN;
        }else if (vyska<3500){
            return Color.ROSYBROWN;
        }else if (vyska<4000){
            return Color.SADDLEBROWN;
        }else if (vyska<5000){
            return Color.BROWN;
        }else {
            return Color.BLACK;
        }

        /*if (vyska<10){
            return Color.WHITE;
        }else if (vyska<10*10){
            return Color.LIGHTYELLOW;
        }else if (vyska<10*10*10){
            return Color.LIGHTGOLDENRODYELLOW;
        }else if (vyska<10*10*10*10){
            return Color.YELLOWGREEN;
        }else if (vyska<10*10*10*10*10){
            return Color.LIGHTGREEN;
        }else{
            return Color.BLACK;
        }*/

        /*if (vyska%1000 < 50){
            return Color.BLACK;
        }*/
/*
        if (vyska<0)
        {
            return Color.WHITE;
        }
        else if (vyska<200)
        {
            return Color.LIGHTYELLOW;
        }
        else if (vyska<300)
        {
            return Color.LIGHTGOLDENRODYELLOW;
        }
        else if (vyska<400)
        {
            return Color.YELLOW;
        }
        else if (vyska<500)
        {
            return Color.YELLOWGREEN;
        }
        else if (vyska<600)
        {
            return Color.GREENYELLOW;
        }
        else if (vyska<700)
        {
            return Color.LIGHTGREEN;
        }
        else if (vyska<800)
        {
            return Color.LIMEGREEN;
        }
        else if (vyska<900)
        {
            return Color.GREEN;
        }
        else if (vyska<1000)
        {
            return Color.DARKGREEN;
        }
        else if (vyska<1500)
        {
            return Color.DARKOLIVEGREEN;
        }
        else if (vyska<2000)
        {
            return Color.BLUE;
        }
        else if (vyska<2500)
        {
            return Color.ALICEBLUE;
        }
        else if (vyska<3000)
        {
            return Color.CADETBLUE;
        }
        else if (vyska<3500)
        {
            return Color.DEEPSKYBLUE;
        }
        else if (vyska<4000)
        {
            return Color.DARKBLUE;
        }
        else if (vyska<4500)
        {
            return Color.BLUEVIOLET;
        }
        else if (vyska<5000)
        {
            return Color.VIOLET;
        }
        else if (vyska<5500)
        {
            return Color.DARKVIOLET;
        }
        else if (vyska<6000)
        {
            return Color.LIGHTPINK;
        }
        else if (vyska<6500)
        {
            return Color.PINK;
        }
        else if (vyska<7000)
        {
            return Color.DEEPPINK;
        }
        else if (vyska<7500)
        {
            return Color.ORANGERED;
        }
        else if (vyska<8000)
        {
            return Color.INDIANRED;
        }
        else if (vyska<8500)
        {
            return Color.RED;
        }
        else if (vyska<9000)
        {
            return Color.DARKRED;
        }
        else if (vyska<10000)
        {
            return Color.SANDYBROWN;
        }
        else if (vyska<20000)
        {
            return Color.ROSYBROWN;
        }
        else if (vyska<30000)
        {
            return Color.SADDLEBROWN;
        }
        else if (vyska<40000)
        {
            return Color.BROWN;
        }
        else if (vyska<50000)
        {
            return Color.DARKGRAY;
        }
        else
        {
            return Color.BLACK;
        }
*/



    }

}
