/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speakers;

import LineairAlgebra.Vect;

/**
 *
 * @author Gijs
 */
public abstract class DirectionalSpeaker extends Speaker{
    
    public Vect dir, up;
    
    public DirectionalSpeaker(Vect l, Vect di, Vect up, float d, float a){
        super(l,d,a);
        this.dir = di;
        // Ensure the vector up is perpendicular to dir
        this.up = Vect.minus(up, Vect.proj(up, dir));
    }
    
    @Override
    public float[] getResponse(Vect mp, float freq){
        return super.getResponse(mp, freq);
    }

    @Override
    public String toString() {
        return "DirectionalSpeaker{" + "dir=" + dir + ", up=" + up + '}';
    }
    
    
}
