/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import speakers.Speaker;

/**
 *  This method represents a horizontal set of measuringpoints
 * @author Gijs
 */
public class HorizontalSoundMapper extends SoundMapper{
    
    private float height;
    
    /**
     * 
     * @param sp    The speaker array
     * @param f     The frequency
     * @param h     The height
     */
    public HorizontalSoundMapper(Speaker[] sp, float f, float h){
        super(sp,f);
        height = h;
    }
    
    @Override
    protected float getZ(float x, float y){
        return height;
    }
}
