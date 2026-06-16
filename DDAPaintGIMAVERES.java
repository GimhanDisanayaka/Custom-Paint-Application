import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DDAPaintGIMAVERES extends JPanel {
    private BufferedImage canvas;
    private int x1, y1, currentX, currentY;
    
   
    private String currentTool = "LINE"; 
    private String statusText = "Mode: Free Hand Line Drawing";

    public DDAPaintGIMAVERES() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();
        setBackground(Color.DARK_GRAY);

        
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x2 = e.getX();
                int y2 = e.getY();

               
                if (currentTool.equals("SQUARE")) {
                    drawDDASquare(x1, y1, x2, y2);
                } else if (currentTool.equals("CIRCLE")) {
                    drawDDACircle(x1, y1, x2, y2);
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                
                if (currentTool.equals("LINE")) {
                    runDDA(x1, y1, currentX, currentY, Color.CYAN);
                    x1 = currentX;
                    y1 = currentY;
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
               
                currentX = e.getX();
                currentY = e.getY();
                repaint();
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        
        setupUI();
    }

    private void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.dispose();
    }

   
    private void runDDA(int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
                canvas.setRGB(Math.round(x), Math.round(y), color.getRGB());
            }
            x += xIncrement;
            y += yIncrement;
        }
    }

    
    private void drawDDASquare(int x1, int y1, int x2, int y2) {
       
        runDDA(x1, y1, x2, y1, Color.GREEN); 
        runDDA(x2, y1, x2, y2, Color.GREEN); 
        runDDA(x2, y2, x1, y2, Color.GREEN); 
        runDDA(x1, y2, x1, y1, Color.GREEN); 
    }

   
    private void drawDDACircle(int x1, int y1, int x2, int y2) {
      
        int radius = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        
     
        int prevX = x1 + radius;
        int prevY = y1;

        for (int angle = 1; angle <= 360; angle++) {
            double rad = Math.toRadians(angle);
            int nextX = (int) (x1 + radius * Math.cos(rad));
            int nextY = (int) (y1 + radius * Math.sin(rad));

            runDDA(prevX, prevY, nextX, nextY, Color.ORANGE);
            
            prevX = nextX;
            prevY = nextY;
        }
    }


    private void setupUI() {
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton lineBtn = new JButton("Free Hand (Line)");
        JButton sqrBtn = new JButton("Draw Square");
        JButton customCircleBtn = new JButton("Draw Circle");
        JButton clearBtn = new JButton("Clear Canvas");

        lineBtn.addActionListener(e -> { currentTool = "LINE"; statusText = "Mode: Free Hand Line Drawing"; });
        sqrBtn.addActionListener(e -> { currentTool = "SQUARE"; statusText = "Mode: Click & Drag to draw Square"; });
        customCircleBtn.addActionListener(e -> { currentTool = "CIRCLE"; statusText = "Mode: Click & Drag to draw Circle"; });
        clearBtn.addActionListener(e -> { clearCanvas(); repaint(); });

        buttonPanel.add(lineBtn);
        buttonPanel.add(sqrBtn);
        buttonPanel.add(customCircleBtn);
        buttonPanel.add(clearBtn);

        this.add(buttonPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(canvas, 0, 0, null);

       
        g.setColor(new Color(0, 0, 0, 180)); // 
        g.fillRect(10, 50, 260, 110);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString("DDA PAINT USER GUIDE", 20, 75);
        
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.YELLOW);
        g.drawString(statusText, 20, 100); 
        
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Mouse X: " + currentX + " | Y: " + currentY, 20, 125); 
        g.drawString("Shortcut: Select tool from top bar", 20, 145);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Advanced Computer Graphics - Custom DDA Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.add(new DDAPaintGIMAVERES());
        frame.setVisible(true);
    }
}
