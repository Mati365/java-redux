/* file name  : src/main/java/com/mati365/calc/utils/ClickListener.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 22:38:47 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public interface ClickMouseListener extends MouseListener {
    @Override
    public default void mouseEntered(MouseEvent e) {}

    @Override
    public default void mouseExited(MouseEvent e) {}

    @Override
    public default void mousePressed(MouseEvent e) {}

    @Override
    public default void mouseReleased(MouseEvent e) {}
}
