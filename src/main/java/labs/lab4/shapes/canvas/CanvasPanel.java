package labs.lab4.shapes.canvas;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;

@RequiredArgsConstructor
public class CanvasPanel extends JPanel {
    private static final int STROKE_WIDTH = 5;
    private final ICanvasPanelDrawable canvas;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        initGraphics2D(g2d);
        canvas.draw(g2d);
    }

    private void initGraphics2D(final Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
    }
}
