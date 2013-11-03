/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soundpredictor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import speakers.Speaker;

/**
 *
 * @author Gijs
 */
public class LocationMap extends JPanel implements MouseListener, MouseMotionListener{
    
    public float width;
    public float height;
    public Speaker[] spk;
    public boolean axes = false, origin=false, fineGrd = false, courseGrd = false;
    public MainFrame uberParent;
    private static Color gridColor = new Color(233,233,233);
    private static Color axsColor = new Color(0,0,0);
    private static Color spkColor1 = new Color(0,0,255);
    private static Color spkColor2 = new Color(255,0,0);
    private boolean objectDragging = false, selectionBoxDragging = false;
    
    public LocationMap(){
        super(); 
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setBackground(Color.WHITE);
    }
    
    public void setUberParent(MainFrame p){
        uberParent = p;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        paintFineGrd(g);
        paintCourseGrd(g);
        paintAxes(g);
        paintOrigin(g);
        paintSpeakers(g);
    }
    
    private void paintAxes(Graphics g){
        if(axes){
            int w = this.getWidth();
            int h = this.getHeight();
            
            g.drawLine(w/2, 0, w/2, h);
            g.drawLine(0, h/2, w, h/2);
            g.setColor(axsColor);
            for(int i=1;i<8;i++){
                int x =(int) (w*(((float) i)/8.0f));
                int y =(int) (h*(((float) i)/8.0f));
                
                g.drawLine(x, h/2, x, h/2-10);
                g.drawString(Float.toString((i-4)*width/8), x+1, h/2+15);
                g.drawLine(w/2, y, w/2+10, y);
                g.drawString(Float.toString((4-i)*height/8), w/2+15, y+1);
                
            }
        }
    }
    
    private void paintOrigin(Graphics g){
        if(origin){
            int x0 = this.getWidth()/2;
            int y0 = this.getHeight()/2;
            g.setColor(axsColor);
            g.drawLine(x0, y0, x0, y0-30);
            g.drawLine(x0, y0-30, x0-5, y0-25);
            g.drawLine(x0, y0-30, x0+5, y0-25);
            
            g.drawLine(x0, y0, x0+30, y0);
            g.drawLine(x0+30, y0, x0+25, y0-5);
            g.drawLine(x0+30, y0, x0+25, y0+5);
        }
    }
    
    private void paintFineGrd(Graphics g){
        if(fineGrd){
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(gridColor);
            for(int i=1;i<16;i++){
                int x =(int) (w*(((float) i)/16.0f));
                int y =(int) (h*(((float) i)/16.0f));
                
                g.drawLine(x,0,x,h);
                g.drawLine(0,y,w,y);
            }
        }
    }
    
    private void paintCourseGrd(Graphics g){
        if(courseGrd && !fineGrd){
            int w = this.getWidth();
            int h = this.getHeight();
            g.setColor(gridColor);
            for(int i=1;i<4;i++){
                
                int x =(int) (w*(((float) i)/4.0f));
                int y =(int) (h*(((float) i)/4.0f));

                g.drawLine(x,0,x,h);
                g.drawLine(0,y,w,y);
                
            }
        }
    }
    
    private void paintSpeakers(Graphics g){
        if(spk != null){
            int w = this.getWidth();
            int h = this.getHeight();
            
            for (int i=0;i<spk.length;i++){
                if(spk[i].selected){
                    g.setColor(spkColor2);
                }else{
                    g.setColor(spkColor1);
                }
                int x =(int) ((spk[i].loc.x/width + 0.5)*((float) w));
                int y =(int) ((-spk[i].loc.y/height + 0.5)*((float) h));
                g.drawOval(x-10, y-10, 20, 20);
                String s0 = "Z=" + spk[i].loc.z + " m";
                String s1 = "D=" + spk[i].delay + " ms";
                String s2 = "A=" + spk[i].ampl + " dB";
                String s3 = "P=" + spk[i].pol;
                g.drawString(s0, x+15, y-20);
                g.drawString(s1, x+15, y-10);
                g.drawString(s2, x+15, y);
                g.drawString(s3, x+15, y+10);
            }
        }
    }
    
    private int isInObject(int scrX, int scrY){
        int w = this.getWidth();
        int h = this.getHeight();
        
        for(int i=0;i<spk.length;i++){
            int spkdScrX =(int) ((0.5+spk[i].loc.x/width)*w-scrX);
            int spkdScrY =(int) ((0.5-spk[i].loc.y/height)*h-scrY);
            if(Math.sqrt(spkdScrX*spkdScrX+spkdScrY*spkdScrY)<10){
               return i;
            }
        }
        
        
        return -1;
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {
        int index = isInObject(me.getX(),me.getY());
        if(index>-1){
            uberParent.spkLst.setSelectedIndex(index);
            objectDragging = true;
        }else{
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        objectDragging = false;
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseDragged(MouseEvent me) {
        int w = this.getWidth();
        int h = this.getHeight();
        float x = (((float)me.getX())/w-0.5f)*width;
        float y = (((float)me.getY())/h-0.5f)*-height;
        if(objectDragging){
            uberParent.xCorSpn.setValue(x);
            uberParent.yCorSpn.setValue(y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {}    

    
}
