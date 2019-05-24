/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Dibujable;
import co.edu.utp.isc.pro4.ajedrez.exceptions.MovimientoNoValidoException;
import java.util.*;
/**
 *
 * @author utp
 */
public abstract class Ficha extends Dibujable {

    private Casilla casilla;
    private final Color color;

    public Ficha(Color color) {
        this.color = color;
    }

    public abstract boolean mover(Tablero tablero, Casilla casillaI, Casilla casillaF) throws MovimientoNoValidoException;

    public void comer(Casilla casillaI, Casilla casillaF){
        asociarFichaTablero(casillaI.getFicha(),casillaF);
        casillaI.setFichaNull();
    };

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Color getColor() {
        return color;
    }
    public void asociarFichaTablero(Ficha ficha,Casilla casilla){
        ficha.setCasilla(casilla);
        casilla.setFicha(ficha);
    }
    public void eliminarFichaTablero(Ficha ficha,Casilla casilla){
        //Desasignar la ficha de la casilla y la casilla de la ficha
    }
    @Override
    public String toString() {
        String tipo = "";
        if (this instanceof Peon) {
            tipo = "P";
        } else if (this instanceof Torre) {
            tipo = "T";
        } else if (this instanceof Caballo) {
            tipo = "C";
        } else if (this instanceof Alfil) {
            tipo = "A";
        } else if (this instanceof Reina) {
            tipo = "Q";
        } else if (this instanceof Rey) {
            tipo = "R";
        }
        return tipo + (getColor() == Color.BLANCO ? "B" : "N");
    }

}
