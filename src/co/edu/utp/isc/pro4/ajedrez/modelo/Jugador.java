/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.pro4.ajedrez.modelo;

import co.edu.utp.isc.pro4.ajedrez.controlador.Ajedrez;
import javax.swing.JOptionPane;

/**
 *
 * @author utp
 */
public class Jugador {

    private Ajedrez ajedrez;
    private final String nombre;
    //private Ficha[] fichas;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    /*public void setFichas(int jugador){
        fichas = new Ficha[16];
        Tablero tablero;
        tablero = ajedrez.getTablero();
        int k = 0;
        jugador = ajedrez.getTurno();
        for(int i = 0;i < 8;i++)
            for(int j = 0;j < 8; j++){
                if(((tablero.getCasilla(i,j).getFicha().getColor() == Color.BLANCO) && (jugador == 0) 
                || (tablero.getCasilla(i,j).getFicha().getColor() == Color.NEGRO) && (jugador == 1))
                && tablero.getCasilla(i,j).isOcupada()){
                    fichas[k] = tablero.getCasilla(i,j).getFicha();
                    k++;
                }
            }
    }
    public Ficha[] getFichas(){
        return fichas;
    }*/

    public void setAjedrez(Ajedrez ajedrez) {
        this.ajedrez = ajedrez;
    }

    public String getNombre() {
        return this.nombre;
    }

    
    private void rendirse() {
        // No me gusta pero los estudiantes lo pidieron
        ajedrez.rendirse();
    }
    
    public void jugar(Casilla casillaI, Casilla casillaF){
        boolean efectivo;
        efectivo = false;
        //System.out.println("Jaque: "+jaque);
        if(casillaI.isOcupada()){
            Ficha f;
            f = casillaI.getFicha();
            if((casillaI.getFicha().getColor() == Color.BLANCO) && (ajedrez.getTurno() == 0)//Valida si el turno concuerda on el color de ficha que selecciono 
                || (casillaI.getFicha().getColor() == Color.NEGRO) && (ajedrez.getTurno() == 1)){
                //System.out.println("JAQUEEE: " + hayJaque(ajedrez.getTablero()));
                    if(hayJaque(ajedrez.getTablero())){
                    JOptionPane.showMessageDialog(null,"Esta en jaque!!");
                }
                efectivo = f.mover(ajedrez.getTablero(), casillaI, casillaF);   
            }
                
            else{
                System.out.println("No es su turno");
            }
        }
        else{
            System.out.println("No ha seleccionado una ficha");
            efectivo = false;
        }
        if(!efectivo){
            ajedrez.cambioTurno();
            JOptionPane.showMessageDialog(null,"Vuelva a internarlo");
        }
        else if(efectivo){
            validarJaqueFichasVsRey(ajedrez.getTablero());
        }
    }
    private void validarJaqueFichasVsRey(Tablero tablero){//Valida si despues de mover una ficha mia, hace jaque
        int turno;
        turno = ajedrez.getTurno();
        Casilla casillaC;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                casillaC = tablero.getCasilla(i,j);
                if(casillaC.isOcupada()){
                    if((turno == 0 && casillaC.getFicha().getColor() == Color.BLANCO)
                    || (turno == 1 && casillaC.getFicha().getColor() == Color.NEGRO)){
                        casillaC.getFicha().haceJaque(tablero);
                    }     
                }
            }
        }
    }
    private boolean hayJaque(Tablero tablero){//Consulta si hay jaque por parte de las fichas enemigas
        int turno; 
        turno = ajedrez.getTurno();
        boolean jaque = false;
        Casilla casillaC;
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                casillaC = tablero.getCasilla(i,j);
                if(casillaC.isOcupada()){
                    if((turno == 0 && casillaC.getFicha().getColor() == Color.NEGRO)
                    || (turno == 1 && casillaC.getFicha().getColor() == Color.BLANCO)){
                        jaque = casillaC.getFicha().getJaque();
                        System.out.println("-JAQUE: "+ jaque + " TURNO: " + turno);
                        if(jaque)
                            return jaque;    
                    }   
                }
            }
        }
        return jaque;
    }
}
