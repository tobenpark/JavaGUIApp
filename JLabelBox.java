import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class JLabelBox extends JLabel{
	private JLabel map;

    public JLabelBox(JLabel map) {
//            map = new JLabel(new ImageIcon(ImageIO.read(new File("c:\\Test\\1.jpg"))));
//            map.setAutoscrolls(true);
//            add(new JScrollPane(map));
//            MouseAdapter ma = new MouseAdapter() {
//
//                private Point origin;
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    origin = new Point(e.getPoint());
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                }
//
//                @Override
//                public void mouseDragged(MouseEvent e) {
//                    if (origin != null) {
//                        JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, map);
//                        if (viewPort != null) {
//                            int deltaX = origin.x - e.getX();
//                            int deltaY = origin.y - e.getY();
//
//                            Rectangle view = viewPort.getViewRect();
//                            view.x += deltaX;
//                            view.y += deltaY;
//
//                            map.scrollRectToVisible(view);
//                        }
//                    }
//                }
//
//            };
//
//            map.addMouseListener(ma);
//            map.addMouseMotionListener(ma);
    }
    
}
