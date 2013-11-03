/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speakers;

import LineairAlgebra.Vect;
import java.io.Serializable;

/**
 *
 * @author Gijs
 */
public class Speaker implements Serializable{
    
    public Vect loc;
    public float delay;//[msec]
    public float ampl; //[dB]   
    private static float vs =333;
    public boolean pol=true;// False if inversed
    public boolean selected = false;
    
    public Speaker(float lX,float lY,float lZ,float d,float a){
        loc = new Vect(lX,lY,lZ);
        delay=d;
        ampl=a;
    }
    
    public Speaker(Vect l,float d,float a){
        loc = l;
        delay=d;
        ampl=a;
    }
    
    /**
     * 
     * @param loc
     * @param freq
     * @return      An array containing the response [Pa] and the phase[rad]
     */
    public float[] getResponse(Vect mp, float freq){
        float[] res = new float[2];
        float dist =Vect.minus(mp, loc).getLength();
        
        // Calculate the amplitude [Pa]
        res[0] = (float) (0.00002*Math.pow(10,ampl/20)/dist);
        
        // Calculate the phase:
        res[1] = freq*dist/vs + freq*delay/1000;
        if (!pol) res[1] += Math.PI;        
        
        return res;
    }
    
    @Override
    public Speaker clone(){
        Speaker res = new Speaker(loc.x,loc.y,loc.z,delay,ampl);
        res.pol=pol;
        return res;
    }

    @Override
    public String toString() {
        return "Sp{" + "X=" + loc.x + ", Y=" + loc.y + ", Z=" + loc.z + ", d=" + delay + ", a=" + ampl + ", p=" + pol + '}';
    }
    
    
}
