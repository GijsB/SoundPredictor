/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LineairAlgebra;

/**
 * This class represents a endles 3-dimensional plane. It's only a mathematical
 * thing to make calculations easy.
 * @author Gijs
 */
public class Plane {
    
    /**
     * The suport vector and normal vector
     */
    public Vect sup,norm;
    
    /**
     * Create a new plane with suport vector s and the vector n normal to the
     * plane.
     * @param s Suport vector
     * @param n Normal vector
     */
    public Plane(Vect s,Vect n){
        if (!n.isZero()){
            sup = s;
            norm = n;
        }
    }
    
    /**
     * An alternative constructor using two vectors that are perpundicular to
     * the plane
     * @param s The suport vector
     * @param p0    The first perpendicular vector
     * @param p1    The second perpendicular vector
     */
    public Plane(Vect s, Vect p0, Vect p1){
        this(s,Vect.crosProd(p0, p1));
    }
    
    /**
     * Calculates the z-coordinate of the given xy-point
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return The z-coordinate
     */
    public float getZ(float x, float y){
        return -1*((norm.x*(x-sup.x)+norm.y*(y-sup.y))/norm.z)+sup.z;
    }
    
    /**
     * Mirrors vector v in plane p
     * @param p The plane
     * @param v The vector
     * @return The mirror of v into p
     */
    public static Vect mirror(Plane p,Vect v){
        Vect w = Vect.minus(v,p.sup);
        Vect diff = Vect.proj(w, p.norm);
        diff.scale(2);        
        return Vect.minus(v, diff);
    }

    @Override
    public String toString() {
        return "Plane{" + "sup=" + sup + ", norm=" + norm + '}';
    }
    
    
}
