/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LineairAlgebra;

/**
 *  This class represents a simple 3 dimensional vector
 * @author Gijs
 */
public class Vect {
    
    /**
     * The coordinates of the vector
     */
    public float x,y,z;
    
    /**
     * Constructs the vector
     * @param xCor  The x-coordinate
     * @param yCor  The y-coordinate
     * @param zCor  The z-coordinate
     */
    public Vect(float xCor, float yCor, float zCor){
        this.x=xCor;
        this.y=yCor;
        this.z=zCor;
    }
    
    /**
     * 
     * @return The length of this vector
     */
    public float getLength(){
        return (float) Math.sqrt(x*x+y*y+z*z);
    }
       
    /**
     * Scales this vector with the factor f
     * @param f The scaling
     */
    public void scale(float f){
        x*=f;
        y*=f;
        z*=f;
    }
    
    /**
     * 
     * @return A cloned version of this vector
     */
    public Vect clone(){
        return new Vect(x,y,z);
    }
    
    /**
     * 
     * @return True if all the elements are zero
     */
    public boolean isZero(){
        return (x==0 && y== 0 && z==0);
    }
    
    /**
     * Rotates this vector along the x-axis
     * @param ang 
     */
    public static Vect rotateX(Vect v, float ang){
        float c =(float) Math.cos(ang);
        float s =(float) Math.sin(ang);
        
        return new Vect(v.x,c*v.y-s*v.z,s*v.y+c*v.z);
    }
    
    /**
     * Rotates this vector along the y-axis
     * @param ang 
     */
    public static Vect rotateY(Vect v, float ang){
        float c =(float) Math.cos(ang);
        float s =(float) Math.sin(ang);
        
        return new Vect(v.x*c+s*v.y, v.y ,c*v.z-s*v.x);
    }
    
     /**
     * Rotates this vector along the z-axis
     * @param ang 
     */
    public static Vect rotateZ(Vect v, float ang){
        float c =(float) Math.cos(ang);
        float s =(float) Math.sin(ang);
        
        return new Vect(c*v.x-s*v.y, s*v.x+c*v.y ,v.z);
    }
    
    /**
     * 
     * @param v0    The first vector
     * @param v1    The second vector
     * @return      The dot product of the two vectors
     */
    public static float dotProd(Vect v0, Vect v1){
        return (v0.x*v1.x+v0.y*v1.y+v0.z*v1.z);
    }
    
    /**
     * 
     * @param v0    The first vector
     * @param v1    The second vector
     * @return      The cross product of the two vectors
     */
    public static Vect crosProd(Vect v0, Vect v1){
        return new Vect(v0.y*v1.z-v0.z*v1.y , v0.x*v1.z-v0.z*v1.x , v0.y*v1.z-v0.z*v1.y );
    }
    
    /**
     * 
     * @param v0    The first vector
     * @param v1    The second vector
     * @return      The sum of the two vectors
     */
    public static Vect sum(Vect v0,Vect v1){
        return new Vect(v0.x+v1.x,v0.y+v1.y,v0.z+v1.z);
    }
    
    /**
     * 
     * @param v0    The first vector
     * @param v1    The second vector
     * @return      The difference between the two vectors
     */
    public static Vect minus(Vect v0,Vect v1){
        return new Vect(v0.x-v1.x,v0.y-v1.y,v0.z-v1.z);
    }
    
    /**
     * 
     * @param v0    The first vector
     * @param v1    The second vector
     * @return      The angle between the two vectors
     */
    public static float angle(Vect v0,Vect v1){
        return (float) Math.acos(dotProd(v0,v1)/(v0.getLength()*v1.getLength()));
    }
   
    /**
     * Calculates the projection of a onto b
     * @param a
     * @param b
     * @return 
     */
    public static Vect proj(Vect a, Vect b){
        Vect res = b.clone();
        res.scale(dotProd(a,b)/dotProd(b,b));
        return res;
    }
    
    /**
     * Calculates the projection of a onto b
     * @param a
     * @param b
     * @return 
     */
    public static float lenProj(Vect a, Vect b){
        return dotProd(a,b)/b.getLength();
    }

    @Override
    public String toString() {
        return "Vect{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
    
}
