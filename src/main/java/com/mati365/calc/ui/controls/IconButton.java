/* file name  : src/main/java/com/mati365/calc/ui/controls/IconButton.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 10:50:57 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;

import java.awt.Dimension;
import javax.swing.JButton;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.utils.ClickMouseListener;

/** 
 * Button that holds single icon, default 32 x 32 size  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class IconButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final Dimension ICON_SIZE = new Dimension(32, 32);
    
    /** 
     * @param icon      Link to icon inside resources bundle 
     */
    public IconButton(@NotNull String icon) {
        super();
        setBorderPainted(false);
        setIcon(Resources.Images.getScaledIcon(icon, IconButton.ICON_SIZE));
        setPreferredSize(IconButton.ICON_SIZE);
    }

    public IconButton(@NotNull String icon, @NotNull ClickMouseListener listener) {
        this(icon);
        this.addMouseListener(listener);
    }

    /**
     * @param icon  Icon path from resources/image folder
     */
    public void setIcon(@NotNull String icon) {
        setIcon(Resources.Images.getIcon(icon));
    }
}
