package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ShapeObserver {
    private JLabel l;
	private CustomWidget w;


    public Main() {
        w = new CustomWidget();
        w.addObserver(this);
        l = new JLabel("Hexagon", JLabel.CENTER);
        l.setName("label");
        setLayout(new BorderLayout());
        add(w, BorderLayout.CENTER);
        add(l, BorderLayout.NORTH);
    }
    
    public void shapeChanged(ShapeEvent event) {
        if(event.isHexSelected()) { l.setText("Hexagon"); }
        if(event.isOctSelected()) { l.setText("Octagon"); }
    }


	public static void main(String[] args) {
		JFrame frame = new JFrame();
        frame.setTitle("Main");
        frame.add(new Main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
	}
}