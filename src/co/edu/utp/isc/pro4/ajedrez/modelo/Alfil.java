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


    /**
     *
     * @author utp
     */
    public class Alfil extends Ficha {

        public Alfil(Color color) {
            super(color);
        }

        @Override
        public void mover(Tablero tablero,Casilla casillaI, Casilla casillaF) {
            boolean ocupada = false;
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
                casillaC = tablero.getCasilla(cI,fI);
                ocupada = casillaC.isOcupada();
                while((casillaC.getFila() != casillaF.getFila()) && (casillaC.getColumna() != casillaF.getColumna()) && !ocupada){
                    casillaC = tablero.getCasilla(cI,fI);
                    ocupada = casillaC.isOcupada();
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
                if(!ocupada){
                if(casillaI.getFicha().getColor() != casillaF.getFicha().getColor()){
                    this.comer( casillaI, casillaF);
                }
                else if(casillaI.getFicha().getColor() == casillaF.getFicha().getColor()){//Si la ficha inicial es del mismo color que la final no es valido
                    System.out.println("Movimiento no valido porque ambas fichas son del mismo color.");
                }
                else if(!casillaF.isOcupada()){//Movimiento normal
                    casillaI.setFichaNull();
                    super.asociarFichaTablero(this, casillaF);
                }
                }
                else{//Movimiento no valido por elemento en la trayectoria
                    System.out.println("Movimiento no valido por ficha en trayectoria");
                }
                }
            else{
                System.out.println("De esa forma no se mueve el alfil");
            }
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
