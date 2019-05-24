    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package co.edu.utp.isc.pro4.ajedrez.modelo;


    import java.awt.GradientPaint;
    import java.awt.Graphics2D;
    import java.awt.geom.Ellipse2D;
    import java.awt.geom.GeneralPath;
    import java.awt.geom.Line2D;
    import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;


    /**
     *
     * @author utp
     */
    public class Alfil extends Ficha {

        public Alfil(Color color) {
            super(color);
        }

        @Override
        public boolean mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
            boolean ocupada = false, efectivo = false;
            int cI,cF,fI,fF, restaA, restaB;
            cI = casillaI.getColumna() - 'A';//x Inicial
            fI = casillaI.getFila() - 1;//y Inicial
            cF = casillaF.getColumna() - 'A';//x Final 
            fF = casillaF.getFila() - 1 ;//y Final
            restaA = fI - fF;
            restaB = cI - cF;
            Casilla casillaC;
            casillaC = casillaI;
            if(Math.abs(restaA) == Math.abs(restaB)){
                if (casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI + 1;
                }
                else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI - 1;
                    fI = fI - 1;
                }
                else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                    cI = cI + 1;
                    fI = fI - 1;
                }
                
                casillaC = tablero.getCasilla(fI,cI);
                if(cI != cF || fI != fF){
                    ocupada = casillaC.isOcupada();
                }
                while((cI != cF || fI != fF) && ocupada==false){
                    casillaC = tablero.getCasilla(fI,cI);
                    ocupada=casillaC.isOcupada();
                    System.out.println(ocupada);
                    if (casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                        cI = cI + 1;
                        fI = fI + 1;
                    }
                    else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() > casillaI.getFila()){
                        cI = cI - 1;
                        fI = fI + 1;
                    }
                    else if(casillaF.getColumna() < casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                        cI = cI - 1;
                        fI = fI - 1;
                    }
                    else if(casillaF.getColumna() > casillaI.getColumna() && casillaF.getFila() < casillaI.getFila()){
                        cI = cI + 1;
                        fI = fI - 1;
                    }
                }
                if(!casillaF.isOcupada()){//Que en la casilla final no haya nada    TIPO 1 (MOVIMIENTO NORMAL)
                    if(!ocupada){//Si no hay nada en la trayectoria
                        casillaI.setFichaNull();
                        super.asociarFichaTablero(this, casillaF);
                        efectivo = true;
                    }
                    else{
                       // System.out.println("Hay una ficha en la trayectoria");
                        
                    }
                }
                else{//Que en la casilla final haya una ficha                       TIPO 2 (COMER)
                   if(this.getColor() != casillaF.getFicha().getColor()){//Si la fichaI y la fichaF son de diferente color
                        if(!ocupada){
                            if(casillaF.getFicha() instanceof Rey){
                                JOptionPane.showMessageDialog(null,"Se termino el juego");
                            }
                            this.comer(casillaI,casillaF);
                            efectivo = true;
                        }
                        else{
                           // System.out.println("Hay una ficha en trayectoria");
                        }
                   }
                   else{
                      // System.out.println("Ambas fichas son del mismo color");
                   }
                }    
            }
            else{
                //System.out.println("De esa forma no se mueve el alfil");
                JOptionPane.showMessageDialog(null,"De esa forma no se mueve el alfil");
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
            g.fill(new Rectangle2D.Float(x + 17, y + 10, 10, 10));
            g.fill(new Rectangle2D.Float(x + 20, y + 21, 5, 20));
            g.fill(new Rectangle2D.Float(x + 13, y + 40, 20, 5));
            g.setPaint(java.awt.Color.BLACK);
            g.draw(new Rectangle2D.Float(x + 17, y + 10, 10, 10));
            g.draw(new Rectangle2D.Float(x + 20, y + 21, 5, 20));
            g.draw(new Rectangle2D.Float(x + 13, y + 40, 20, 5));
        }
    }
