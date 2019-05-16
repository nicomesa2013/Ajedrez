/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author utp
 */
public class Caballo extends Ficha {

    public Caballo(Color color) {
        super(color);
    }

    @Override
    public void mover(Tablero tablero, Casilla casillaI, Casilla casillaF) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void comer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g, float x, float y) { 
        g.setPaint(new GradientPaint(x, y,
                getColor() == Color.BLANCO ? java.awt.Color.CYAN : java.awt.Color.BLACK,
                x + 50, y + 50,
                java.awt.Color.WHITE));
    //Base de ficha
      g.fill(new Rectangle2D.Double(x+10,y+40,30,10));
      //Cuerpo de ficha
      g.fill(new Rectangle2D.Double(x+20,y+30,10,10));
      //Cabeza ficha
      GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 17);
        polyline.moveTo(x + 20, y + 30);
        polyline.lineTo(x + 15, y + 25);
        polyline.lineTo(x + 20, y + 15);
        polyline.lineTo(x + 15, y + 10);
        polyline.lineTo(x + 20, y + 5);
        polyline.lineTo(x + 25, y + 10);
        polyline.lineTo(x + 30, y + 5);
        polyline.lineTo(x + 35, y + 10);
        polyline.lineTo(x + 30, y + 15);
        polyline.lineTo(x + 30, y + 30);
        polyline.moveTo(x + 20, y + 30);
        g.fill(polyline);
    //Cambiar color para margen
    g.setPaint(java.awt.Color.BLACK);
    //Base de ficha
    g.draw(new Rectangle2D.Double(x+10,y+40,30,10));
    //Cuerpo de ficha
    g.draw(new Rectangle2D.Double(x+20,y+30,10,10));
    //Cabeza ficha
    polyline.moveTo(x + 20, y + 30);
        polyline.lineTo(x + 15, y + 25);
        polyline.lineTo(x + 20, y + 15);
        polyline.lineTo(x + 15, y + 10);
        polyline.lineTo(x + 20, y + 5);
        polyline.lineTo(x + 25, y + 10);
        polyline.lineTo(x + 30, y + 5);
        polyline.lineTo(x + 35, y + 10);
        polyline.lineTo(x + 30, y + 15);
        polyline.lineTo(x + 30, y + 30);
        polyline.moveTo(x + 20, y + 30);
        g.draw(polyline);
    }
    
}
