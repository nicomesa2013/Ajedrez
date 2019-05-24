/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JOptionPane;

/**
 *
 * @author utp
 */
public class Torre extends Ficha {

    public Torre(Color color) {
        super(color);
    }

    @Override
        public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
            boolean ocupada = false, efectivo = false;
            int cI,cF,fI,fF;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
            Casilla casillaC;
            casillaC = casillaI;
            if(fI==fF || cI==cF){
                if (casillaF.getColumna() > casillaI.getColumna()){
                    cI = cI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna()){
                    cI = cI - 1;
                }
                else if(casillaF.getFila() < casillaI.getFila()){
                    fI = fI - 1;
                }
                else if(casillaF.getFila() > casillaI.getFila()){
                    fI = fI + 1;
                }
                casillaC = tablero.getCasilla(fI,cI);
                if(cI != cF || fI != fF){
                    ocupada = casillaC.isOcupada();
                }
                while((casillaC.getFila() != casillaF.getFila()) && (casillaC.getColumna() != casillaF.getColumna()) && !ocupada){
                    casillaC = tablero.getCasilla(fI,cI);
                    ocupada = casillaC.isOcupada();
                    if (casillaF.getColumna() > casillaI.getColumna()){
                    cI = cI + 1;
                    }
                    else if(casillaF.getColumna() < casillaI.getColumna()){
                        cI = cI - 1;
                    }
                    else if(casillaF.getFila() < casillaI.getFila()){
                        fI = fI - 1;
                    }
                    else if(casillaF.getFila() > casillaI.getFila()){
                        fI = fI + 1;
                    }
                }
                System.out.println("Ocupada:"+ ocupada);
                if(!ocupada){
                    System.out.println("f Ocupada: "+ocupada);
                    if(!casillaF.isOcupada()){//Movimiento normal
                        casillaI.setFichaNull();
                        super.asociarFichaTablero(this, casillaF);
                        efectivo = true;
                    }
                    else if(casillaI.getFicha().getColor() == casillaF.getFicha().getColor()){//Si la ficha inicial es del mismo color que la final no es valido
                        //System.out.println("Movimiento no valido porque ambas fichas son del mismo color.");
                        System.out.println("entro");
                    }
                    else if(casillaI.getFicha().getColor() != casillaF.getFicha().getColor()){
                        if(casillaF.getFicha() instanceof Rey){
                            JOptionPane.showMessageDialog(null,"Se termino el juego");
                        }
                        this.comer(casillaI, casillaF);
                        efectivo = true;
                    }
                }
                else{//Movimiento no valido por elemento en la trayectoria
                    //System.out.println("Movimiento no valido por ficha en trayectoria");
                }
                }
            else{
            //    System.out.println("Asi no se mueve la torre");
              JOptionPane.showMessageDialog(null,"si no se mueve la torre");
            }
            return efectivo;
        }
        
    @Override
    public void draw(Graphics2D g, float x, float y) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 5, y + 5);
        polyline.lineTo(x + 5, y + 15);
        polyline.lineTo(x + 10, y + 15);
        polyline.lineTo(x + 10, y + 45);
        polyline.lineTo(x + 40, y + 45);
        polyline.lineTo(x + 40, y + 15);
        polyline.lineTo(x + 45, y + 15);
        polyline.lineTo(x + 45, y + 5);
        polyline.lineTo(x + 37, y + 5);
        polyline.lineTo(x + 37, y + 10);
        polyline.lineTo(x + 29, y + 10);
        polyline.lineTo(x + 29, y + 5);
        polyline.lineTo(x + 21, y + 5);
        polyline.lineTo(x + 21, y + 10);
        polyline.lineTo(x + 13, y + 10);
        polyline.lineTo(x + 13, y + 5);
        polyline.lineTo(x + 5, y + 5);

        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 100, y + 50,
                java.awt.Color.WHITE));
        g.fill(polyline);

        g.setColor(java.awt.Color.BLACK);
        g.draw(polyline);
    }

    

}
