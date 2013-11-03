/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soundpredictor;

import speakers.Speaker;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.DefaultListModel;

/**
 *
 * @author Gijs
 */
public class FileIO {
    
    public static void saveSpeakers(DefaultListModel<Speaker> spk,File f){
        try{
            PrintWriter out = new PrintWriter(f);
            
            for(int i=0;i<spk.size();i++){
                Speaker tmpSpk = spk.get(i);
                out.println("Speaker:");
                String s = tmpSpk.loc.x + " " + tmpSpk.loc.y + " " + tmpSpk.loc.z + " " + tmpSpk.delay + " " + tmpSpk.ampl + " " + tmpSpk.pol;
                s = s.replace('.', ',');
                out.println(s);
            }
            
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public static void loadSpeakers(File f,DefaultListModel<Speaker> spk){
        spk.removeAllElements();
        
        // Add all the speakers
        try{
            FileInputStream fileIn = new FileInputStream(f);
            Scanner in = new Scanner(fileIn);            
            
            while(in.hasNext()){
                if (in.nextLine().equals("Speaker:")){
                    Speaker tmpSpk = new Speaker(in.nextFloat(),in.nextFloat(),in.nextFloat(),in.nextFloat(),in.nextFloat());
                    tmpSpk.pol = in.nextBoolean();
                    spk.addElement(tmpSpk);
                    in.nextLine();
                }
            }
            
            in.close();
            fileIn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
