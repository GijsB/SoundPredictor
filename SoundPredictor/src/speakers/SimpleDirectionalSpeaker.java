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
public class SimpleDirectionalSpeaker extends DirectionalSpeaker{

    public float angl;
    
    public SimpleDirectionalSpeaker(Vect l, Vect di, Vect up, float d, float a, float ang){
        super(l,di,up,d,a);
        angl=ang;
    }
    
    @Override
    public float[] getResponse(Vect mp, float freq) {
        Vect mpDir = Vect.minus(mp, loc);
        float a = Vect.angle(dir, mpDir);
        
        if(a>(2*Math.PI-angl) || a<angl){
            return super.getResponse(mp, freq);
        }
        
        return new float[2];
    }
    
    @Override
    public String toString() {
        return "DoubleDirectionalSpeaker{" + "Loc=" + loc + ", Dir=" + dir + ", Up=" + up + ", Del=" + delay + ", Ampl=" + ampl + ", Pol=" + ", Angl=" + angl + '}';
    }
    
}
