/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.exceptions.MovimientoNoValidoException;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;

/**
 *
 * @author utp
 */
public class Peon extends Ficha {

    public Peon(Color color) {
        super(color);
    }

    @Override
    public boolean mover(Tablero tablero, Casilla casillaI, Casilla casillaF) throws MovimientoNoValidoException {
        boolean ocupada = false, efectivo = false;
            int cI,cF,fI,fIAUX,fF, restaA, restaB;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
            fIAUX = fI;//Para validar la posicion inicial del peon sin que se pierda
            restaA = fI - fF;
            restaB = cF - cI;
            Casilla casillaC;

            if((Math.abs(restaA) == 2 || Math.abs(restaA) == 1)){// Condicion general de movimiento del peon
                if(restaA == 2 && casillaI.getFicha().getColor() == Color.BLANCO){//Caso para mover 2 casillas BLANCO
                    fI = fI + 1;
                    casillaC = tablero.getCasilla(fI,cI);
                    if(fI != fF || cI != cF){
                        ocupada = casillaC.isOcupada();
                    }
                }
                else if(restaA == 2 && casillaI.getFicha().getColor() == Color.NEGRO){//Caso para mover 2 casillas NEGRO
                    fI = fI - 1;
                    casillaC = tablero.getCasilla(fI,cI);
                    if(fI != fF || cI != cF){
                        ocupada = casillaC.isOcupada();
                    }
                }
                if(!ocupada){
                    if(!casillaF.isOcupada()){//Movimiento normal
                        if(this.getColor() == Color.NEGRO){
                            if((restaA == 1 && cF == cI ) || (restaA == 2 && fIAUX == 6)){
                                casillaI.setFichaNull();
                                super.asociarFichaTablero(this, casillaF);
                                efectivo = true;
                            }
                            else if(fIAUX != 6){
                               // System.out.println("Este movimiento no es valido en esta fila");
                            }
                            else{
                               // System.out.println("Asi no se mueve el peon");
                                JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");
                            }
                        }
                        else if(this.getColor() == Color.BLANCO){
                            System.out.println("cI : "+ cI + "cI: " + cF + "restaA: " + restaA);
                            if((restaA == -1 && cF == cI )|| (restaA == -2 && fIAUX == 1)){
                                System.out.println("Entro");
                                casillaI.setFichaNull();
                                super.asociarFichaTablero(this, casillaF);
                                efectivo = true;
                            }
                            else if(fIAUX != 1){
                              //  System.out.println("Este movimiento no es valido en esta fila");
                            }
                            else{
                               // System.out.println("Asi no se mueve el peon");
                              JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");
                            }
                        }
                    }
                    else if(casillaF.isOcupada()){
                        if(casillaI.getFicha().getColor() != casillaF.getFicha().getColor()){
                            if(Math.abs(restaB) == 1){
                                if(casillaI.getFicha().getColor() == Color.BLANCO && restaA == -1){
                                    this.comer(casillaI, casillaF); 
                                    efectivo = true;
                                }
                                else if(casillaI.getFicha().getColor() == Color.NEGRO && restaA == 1){
                                    this.comer(casillaI, casillaF);
                                    efectivo = true;
                                }    
                            }
                            else{
                                //throw new MovimientoNoValidoException("Asi no come el peon");
                                JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");
                                
                            }
                        }
                    }   
                   
                }
                else if(ocupada){//Movimiento no valido por elemento en la trayectoria
                   // throw new MovimientoNoValidoException("Movimiento no valido por ficha en trayectoria");
                }
            }
            else{
               // throw new MovimientoNoValidoException("De esa forma no se mueve el peon");
               JOptionPane.showMessageDialog(null,"De esa forma no se mueve el peon");
            }
            return efectivo;
    }


    @Override
    public void draw(Graphics2D g, float x, float y) {
        // 50x50 dibujar la ficha
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
        g.fill(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.fill(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
        g.setPaint(java.awt.Color.BLACK);
        g.draw(new Ellipse2D.Float(x + 17, y + 15, 16, 16));
        g.draw(new Rectangle2D.Float(x + 15, y + 30, 20, 15));
    }

}
