/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkpool;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Nafis Mustakin
 */
public class PowerBar {
    ImageView bar;
    
    Group root;
    Rectangle slider;
    
    
    PowerBar(Group root)
    {
        bar = new ImageView("images/powerbar.png");
        slider = new Rectangle();
        bar.setLayoutX(1115);
        bar.setLayoutY(180);
        
        slider.setWidth(45);
        slider.setHeight(10);
        slider.setLayoutX(1114);
        slider.setLayoutY(183);
        
        slider.setFill(BLUE);
        slider.setCursor(Cursor.CLOSED_HAND);
        
        root.getChildren().addAll(bar, slider);
        
    }
    
    
    public Rectangle getSlider(){return slider;}
    
}
