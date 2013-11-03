/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import LineairAlgebra.Plane;
import LineairAlgebra.Vect;
import speakers.Speaker;

/**
 *  This class represents a angled measuringfield
 * @author Gijs
 */
public class AngledSoundMapper extends SoundMapper{
    
    // The plane representing the coordinates of the measuringpoints
    private Plane pln;
    
    /**
     * Constructs a new AngledSoundMapper
     * @param sp    The speaker array
     * @param f     The frequency
     * @param p     The plane of measuring point locations
     */
    public AngledSoundMapper(Speaker[] sp,float f,Plane p){
        super(sp,f);
        pln = p;
    }
    
    /**
     * Constructs a new AngledSoundMapper
     * @param sp    The speaker array
     * @param f     The frequency
     * @param sup   The support vector of the plane
     * @param norm  The normal vector of the plane
     */
    public AngledSoundMapper(Speaker[] sp,float f,Vect sup, Vect norm){
        super(sp,f);
        pln = new Plane(sup,norm);
    }
    
    /**
     * Constructs a new AngledSoundMapper
     * @param sp    The speaker array
     * @param f     The frequency
     * @param sup   The support vector of the plane
     * @param p0    The first vector in the plane
     * @param p1    The second vector in the plane
     */
    public AngledSoundMapper(Speaker[] sp,float f,Vect sup, Vect p0, Vect p1){
        super(sp,f);
        pln = new Plane(sup,p0,p1);
    }
    
    @Override
    protected float getZ(float x, float y){
        return pln.getZ(x, y);
    }
}
