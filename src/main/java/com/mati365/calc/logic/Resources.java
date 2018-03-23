/* file name  : src/main/java/com/mati365/calc/logic/Resources.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 18:32:03 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import javax.validation.constraints.NotNull;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Dimension;

import java.io.IOException;
import java.net.URL;

public class Resources { 
    /** 
     * Class that handles text based resources(i18n) 
     *  
     * @author Mateusz Bagiński (cziken58@gmail.com)
     */
    public static class Translations {
        private static ResourceBundle messages = ResourceBundle.getBundle("locale", Locale.getDefault());

        /**
         * @param key   translated keyword path
         * @return      single translation from bundle
         */
        public static String getString(@NotNull String key) {
            return messages.getString(key);
        }

        /**
         * @param key   translated keyword path
         * @param args  formatter args
         * @return      single formatted translation from bundle
         */
        public static String getString(@NotNull String key, Object... args) {
            return MessageFormat.format(Translations.getString(key), args);
        }
    }

    /** 
     * Class that handles image based resources 
     *
     * @author Mateusz Bagiński (cziken58@gmail.com)
     */
    public static class Images {
        /**
         * @param resource  Resource path to be appended to /images/ path
         * @return  Loaded image, null if not present
         */
        public static BufferedImage getImage(@NotNull String resource) {
            final URL imageResource = Resources.class.getResource("/images/" + resource);

            try {
                return ImageIO.read(imageResource);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * @param icon  PNG icons name inside images/icons/ folder
         * @return  Loaded ImageIcon object
         */
        public static ImageIcon getIcon(@NotNull String icon) {
            return new ImageIcon(
                    Images.getImage("icons/" + icon + ".png")
            );
        }
        
        /** 
         * Loads icon and smooth scale it to provided dimension 
         * 
         * @param icon 
         * @param size 
         * @return 
         */
        public static ImageIcon getScaledIcon(@NotNull String icon, @NotNull Dimension size) {
            ImageIcon imageIcon = Images.getIcon(icon);
            return new ImageIcon(
                        imageIcon
                            .getImage()
                            .getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH)
                    );
        }
    } 
}
