package farms4life2016.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener; 
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

import farms4life2016.dataprocessing.Controller;
import farms4life2016.dataprocessing.Job;
import farms4life2016.gui.buttons.Button;
import farms4life2016.gui.buttons.NPButton;
import farms4life2016.gui.buttons.TextField;
import farms4life2016.gui.tables.Table;
import farms4life2016.gui.tables.TableCell;



public class Display extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener, MouseWheelListener, WindowListener {
    
    private Point mouse;
	private Table jobTable;
	private Timer fps; 
	private static Button extraInfo, createNewJob;
	private TextField textField;
	
	

	/**
	 * Currently, I'm displaying some stuff.
	 */
	public Display() {
		
		//variables
		mouse = new Point(0,0);
		jobTable = new Table(Controller.jobList);
		extraInfo = new NPButton(true, TableCell.OUTLINE_WIDTH);
		textField = new TextField();
		createNewJob = new Button() {
			@Override
			public void onClick(MouseEvent e) {
				System.out.println(text);
				
				
			}

			@Override
			public void drawSelf(Graphics2D g) {
				super.fillBgRect(g);
				
			}
			
		};

		//new job test
		createNewJob.setDimensions(new Rectangle(5, 70, 500, 50));
		createNewJob.setText("Click me to add a job");
		createNewJob.setUnselectedColour(Colours.WHITE);
		createNewJob.setSelected(false);
		


		//format button
		extraInfo.setDimensions(new Rectangle(50, 200+12*30, 950, 50));
		extraInfo.setUnselectedColour(Color.CYAN);
		extraInfo.setSelected(false);
		extraInfo.setFontSize(32);
		extraInfo.setFontStyle(Font.BOLD);
		extraInfo.setText(NPButton.DEFAULT_INFO_STRING);

		//code for testing type-in button fun
		textField.setDimensions(new Rectangle(0, 0, 500, 50));
		
		//String[] headers = {"ID", "Name", "Client", "Type", "File", "Date"};

		//add listeners. WindowListener is added in Controller
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		addMouseWheelListener(this);
		
		//update the screen once every 60 milliseconds
		fps = new Timer(60, this); 
		fps.start();
		
		
		
	}
	
	
	public void addNotify() { //to get KeyListener to work
		super.addNotify();
		requestFocus();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		mouse.setLocation(e.getX(), e.getY());
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//refresh the screen
		if (e.getSource().equals(fps)) {
			repaint();
			textField.onRefresh();
		}
		
		
	}
	
	
	public void paintComponent(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics;
		
		//currently just a white screen
		super.paintComponent(g);
		g.setColor(Colours.GRAY40);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//g.setColor(Color.black);
		//g.fillRect(TABLE.x, TABLE.y, TABLE.width, TABLE.height);

		jobTable.drawSelf(g);
		extraInfo.drawSelf(g);
		textField.drawSelf(g);
		createNewJob.drawSelf(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//detect left-click
		if (e.getButton() == MouseEvent.BUTTON1) {
			jobTable.onClick(e);
			textField.onClick(e);
			createNewJob.onClick(e);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/*
	 * For the search bar later on 
	 */
	
	@Override
	public void keyTyped(KeyEvent e) {
		textField.onType(e);
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Scrollwheel might be easier to implement than buttons to change pages,
	 * plus the former is more user-friendly
	 */

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		jobTable.onScroll(e);
		
	}

	/*
 	 * We have to specify more about how to close the program
 	 */

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("you tried to close the program!"); //more debugging stuff
		Controller.terminate();
		System.exit(0);
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void setInfoText(String newText) {
		extraInfo.setText(newText);
	}

}
