package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CustomWidget extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> obs;
    
    
    private boolean hexSelected;
	private boolean octSelected;
    private Point[] hexVertices;
	private Point[] octVertices;
	private final Color hex_Selected_COLOR = Color.green;
    private final Color hex_DEFAULT_COLOR = Color.white;
	private final Color oct_Selected_COLOR = Color.red;
	private final Color oct_DEFAULT_COLOR = Color.white;
	

    
    public CustomWidget() {
        obs = new ArrayList<>();
        
        hexSelected = true;
        hexVertices = new Point[6];
        for(int i = 0; i < hexVertices.length; i++) { hexVertices[i] = new Point(); }
        Dimension dim = getSize();
        calcVert(hexVertices, dim.width, dim.height, 0, dim.width/3, dim.height/2);
		
		octSelected = false;
		octVertices = new Point[8];
		for(int i = 0; i < octVertices.length; i++) { octVertices[i] = new Point(); }
		calcVert(octVertices, dim.width, dim.height, Math.PI * 0.125, dim.width - (dim.width/3), dim.height/2);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

    
    public void addObserver(ShapeObserver observer) {
        if(!obs.contains(observer)) obs.add(observer);
    }
    public void removeObserver(ShapeObserver observer) {
        obs.remove(observer);
    }
    private void notifyObservers() {
        ShapeEvent e = new ShapeEvent(hexSelected, octSelected);
        for(ShapeObserver obs : obs) {
            obs.shapeChanged(e);
        }
    }
    
    
    @Override
    public Dimension getSize() {
        return new Dimension(200, 200);
    }

    private void calcVert(Point[] vert, int width, int height, double offset, int offsetX, int offsetY) {
        // Square size should be half of the smallest dimension (width or height).
        int size = Math.min(width, height) / 2;
		
		for (int i=0; i<vert.length; i++){
			double set = offset + (i * (Math.PI / (vert.length / 2)));
			double x = Math.cos(set);
			double y = Math.sin(set);
			vert[i].setLocation(offsetX + (x* (size/4)), offsetY + (y* (size/4)));
		}
        
    }
    
    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D twoDim = (Graphics2D)graphic;

        
		calcVert(hexVertices, getWidth(), getHeight(), 0, getWidth()/3, getHeight()/2);
        Shape s = getShape(hexVertices);
        twoDim.setColor(Color.black);
        twoDim.draw(s);
		
        if(hexSelected) {
            twoDim.setColor(hex_Selected_COLOR);
        }
        else {
            twoDim.setColor(hex_DEFAULT_COLOR);                     
        }
		twoDim.fill(s);
		calcVert(octVertices, getWidth(), getHeight(), Math.PI * 0.125, getWidth() - (getWidth()/3), getHeight()/2);
		s = getShape(octVertices);
		twoDim.setColor(Color.black);
		twoDim.draw(s);
		
		if(octSelected){
			twoDim.setColor(oct_Selected_COLOR);
		}
		else{
			twoDim.setColor(oct_DEFAULT_COLOR);
		}
		twoDim.fill(s);
    }

    public void mouseClicked(MouseEvent e) {
        Shape s = getShape(hexVertices);
        if(s.contains(e.getX(), e.getY())) {
            hexSelected = !hexSelected;
			octSelected = !hexSelected;
            notifyObservers();
        }
		else{
			s = getShape(octVertices);
			if(s.contains(e.getX(), e.getY())) {
            octSelected = !octSelected;
			hexSelected = !octSelected;
            notifyObservers();
		}
		}
        repaint(s.getBounds());
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public Shape getShape(Point[] vert) {
        int[] x = new int[vert.length];
        int[] y = new int[vert.length];
        for(int i = 0; i < vert.length; i++) {
            x[i] = vert[i].x;
            y[i] = vert[i].y;
        }
        Shape s = new Polygon(x, y, vert.length);
        return s;
    }
	public Shape[] getShapes(){
		return new Shape[]{
			getShape(hexVertices),
			getShape(octVertices)
		};
	}
	
	public boolean isSelected() { return isHexSelected() || isOctSelected(); }
	public boolean isHexSelected() {return hexSelected;}
	public boolean isOctSelected() {return octSelected;}
  
	public static void main(String[] args) {
		JFrame frame = new JFrame("Custom Widget");
        frame.add(new CustomWidget());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
	}
}
