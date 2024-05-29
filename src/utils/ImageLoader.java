
package utils;

import java.awt.Image;
import javax.swing.ImageIcon;


public class ImageLoader {
    
    public static Image LoadImage(String path){
    
        return new ImageIcon(path).getImage();
    
    }
    
    
}
