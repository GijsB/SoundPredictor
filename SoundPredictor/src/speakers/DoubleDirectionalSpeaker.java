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
public class DoubleDirectionalSpeaker extends DirectionalSpeaker {

    public float xAngl, yAngl;
    
    public DoubleDirectionalSpeaker(Vect l, Vect di, Vect up, float d, float a, float xAng, float yAng){
        super(l,di,up,d,a);
        xAngl = xAng;
        yAngl = yAng;
    }
    
    @Override
    public float[] getResponse(Vect mp, float freq) {
        Vect mpDir = Vect.minus(mp, loc);
        Vect side = Vect.crosProd(up, dir);
        
        Vect upProj = Vect.proj(mpDir, up);
        Vect frProj = Vect.proj(mpDir, dir);
        Vect siProj = Vect.proj(mpDir, side);
        
        float xA = Vect.angle(dir,Vect.sum(siProj, frProj));
        float yA = Vect.angle(dir,Vect.sum(upProj, frProj));
        
        if((xA>(2*Math.PI-xAngl) || xA<xAngl)&&(yA>(2*Math.PI-yAngl) || yA<yAngl)){
            return super.getResponse(mp, freq);
        }
        
        return new float[2];
    }

    @Override
    public String toString() {
        return "DoubleDirectionalSpeaker{" + "Loc=" + loc + ", Dir=" + dir + ", Up=" + up + ", Del=" + delay + ", Ampl=" + ampl + ", Pol=" + ", xAngl=" + xAngl + ", yAngl=" + yAngl + '}';
    }
    
    
}
