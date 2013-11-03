/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import LineairAlgebra.Vect;
import net.ericaro.surfaceplotter.Mapper;
import speakers.Speaker;

/**
 *  This object contains the deffinition of the measuringfield and how the
 * results are calculated
 * @author Gijs
 */
public class SoundMapper implements Mapper{

    private Speaker[] speakers;
    private float freq; //In rad/s
    private static float vs =333;
    private static float zCor = 0;
    
    /**
     * Constructs a SoundMapper
     * @param sp    The mappable speakers
     * @param f     The frequenty in Hz
     */
    public SoundMapper(Speaker[] sp, float f){
        speakers=sp;
        freq = (float) (f*2*Math.PI);
    }
    
    @Override
    public float f1(float x, float y) {
        int numSpeak = speakers.length;
        Vect mp = new Vect(x,y,getZ(x,y));
        
        float[][] respo = new float[numSpeak][2];
        float result = 0;
        
        for (int i=0;i<numSpeak;i++){            
            respo[i] = speakers[i].getResponse(mp, freq);            
        }
        
        for (int i=0;i<numSpeak;i++){
            for (int j=0;j<numSpeak;j++){
                result += respo[i][0]*respo[j][0]*Math.cos(respo[i][1]-respo[j][1]);
            }
        }
        
        result = (float) (20*Math.log10(Math.sqrt(result)/0.00002));
        
        return result;
    }

    @Override
    public float f2(float x, float y) {
        return f1(x,y);
    }

    /**
     * This method is mainly created to be able to override this methode later on
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return The z-coordinate of the place (x,y)
     */
    protected float getZ(float x, float y){
        return zCor;
    }
    
    @Override
    public String toString() {
        return "SoundMapper{" + "speakers=" + speakers + ", freq=" + freq + '}';
    }
    
    
    
}
