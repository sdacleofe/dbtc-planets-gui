package com.gui.cleofe;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class Planets extends JFrame implements ItemListener, ActionListener{
	private JLabel planetFiller;
	private JLabel background;
	private JLabel planetName;
	private JTextArea planetDescription;
	private JPanel comboBoxPane;
	private JPanel planetPane;
	private String string;
	private JButton aboutButton;
	private Color whiteff = new Color(255, 255, 255);
	private Color black00 = new Color(0, 0, 0);
	private JComboBox<Object> planetComboBox = new JComboBox<Object>();
	private static final String[] planetString = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};

	
	private Icon[] icons = {
			new ImageIcon("./images/" + planetString[0] + ".png"),
			new ImageIcon("./images/" + planetString[1] + ".png"),
			new ImageIcon("./images/" + planetString[2] + ".png"),
			new ImageIcon("./images/" + planetString[3] + ".png"),
			new ImageIcon("./images/" + planetString[4] + ".png"),
			new ImageIcon("./images/" + planetString[5] + ".png"),
			new ImageIcon("./images/" + planetString[6] + ".png"),
			new ImageIcon("./images/" + planetString[7] + ".png"),
	};
	
	public void CustomCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./cur/Arrow.png");
		Point point = new Point(0, 0);
		Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
		setCursor(cursor);
	}
	
	public Planets(String title) {
		super(title);
		CustomCursor();
				
		ImageIcon bg = new ImageIcon("./images/Starry.jpg");
		background  = new JLabel("", bg, JLabel.CENTER);
		planetFiller = new JLabel (icons[0]);	 
		planetName = new JLabel("Solar Planets");
		aboutButton = new JButton("About");
		planetComboBox = new JComboBox<Object>(planetString);
		planetDescription = new JTextArea();
		planetPane = new JPanel();
		comboBoxPane = new JPanel();
		
		planetComboBox.setMaximumRowCount(8);

		setPreferredSize(new Dimension(480, 380));
		add(background, BorderLayout.NORTH);
		add(comboBoxPane, BorderLayout.CENTER);

		background.setLayout(null);
	    planetPane.setBounds(0,100,480,480);
	    planetName.setBounds(0,30,480,50);
	    planetName.setHorizontalAlignment(JLabel.CENTER);
	    planetName.setFont(new Font("SF Fourche", 0, 36));	
	
		planetPane.setBackground(new Color(0.1f, 0.1f, 0.1f, 0.0f));
		planetComboBox.setFont(new Font("SF Fourche", 0, 12));		
		planetName.setForeground(whiteff);
		
		setLayout(null);
		background.setBounds(0,0,480,345);		
		planetComboBox.setBounds(10,300,120,30);
		comboBoxPane.setBounds(0,300,480,500);
		aboutButton.setBounds(320,300,130,30);
		
		comboBoxPane.setLayout(null);
		comboBoxPane.setBackground(black00);
		planetDescription.setBounds(50,80,480,180);
		planetDescription.setBackground(black00);
		planetDescription.setForeground(whiteff);
		planetDescription.setEditable(false);
		planetDescription.setFont(new Font("SF Fourche", 0, 16));

		// ComboBox Line Border
		planetComboBox.setUI(new BasicComboBoxUI() {
		    @Override
		    protected ComboPopup createPopup() {
		        BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
		        basicComboPopup.setBorder(new LineBorder(black00));
		        return basicComboPopup;
		    }
		});
		
		planetComboBox.setRenderer(new IndentedRenderer());
		aboutButton.setVisible(false);
		aboutButton.setBackground(whiteff);
		
		add(comboBoxPane);
		comboBoxPane.add(planetDescription);
		background.add(planetName);
		background.add(planetComboBox);
		background.add(aboutButton);
		background.add(planetPane);
		planetPane.add(planetFiller);
		
		planetComboBox.addItemListener(this);
		aboutButton.addActionListener(this);
	}

	// ComboBox Text Padding
	public class IndentedRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
			JLabel padding = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
			padding.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			return padding;
		}
	}

	public static void main(String[] args) {
		Planets solarSystemFrame = new Planets("Planets");
		solarSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		solarSystemFrame.pack();
		solarSystemFrame.setResizable(false);
		solarSystemFrame.setLocationRelativeTo(null);
		solarSystemFrame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED) {
			planetFiller.setIcon(icons[planetComboBox.getSelectedIndex()]);
			string = planetString[planetComboBox.getSelectedIndex()];
			planetName.setText(string);
			aboutButton.setText("About " +string);
			aboutButton.setVisible(true);
			setSize(new Dimension(480, 380));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton) e.getSource();
		if (sourceButton.getText().contains("Mercury")) {
			setSize(new Dimension(480, 500));
			planetDescription.setText("The smallest planet in our solar system\n"
					+ "and nearest to the Sun, Mercury is only\n"
					+ "slightly larger than Earth's Moon.");
		}
		
		if (sourceButton.getText().contains("Venus")) {
			setSize(new Dimension(480, 550));
			planetDescription.setText("Venus is the second planet from the Sun\n"
					+ "and is Earth’s closest planetary neighbor.\n"
					+ "It’s one of the four inner, terrestrial\n"
					+ "(or rocky) planets, and it’s often called\n"
					+ "Earth’s twin because it’s similar in size\n"
					+ "and density.");
		}
		
		if (sourceButton.getText().contains("Earth")) {
			setSize(new Dimension(480, 500));
			planetDescription.setText("Our home planet is the third planet from\n"
					+ "the Sun, and the only place we know of\n"
					+ "so far that’s inhabited by living things.");
		}
		
		if (sourceButton.getText().contains("Mars")) {
			setSize(new Dimension(480, 550));
			planetDescription.setText("Mars is the fourth planet from the Sun\n"
					+ "– a dusty, cold, desert world with a very\n"
					+ "thin atmosphere. Mars is also a dynamic \n"
					+ "planet with seasons, polar ice caps,\n"
					+ "canyons, extinct volcanoes, and evidence\n"
					+ "that it was even more active in the past.");
		}
		
		if (sourceButton.getText().contains("Jupiter")) {
			setSize(new Dimension(480, 530));
			planetDescription.setText("Jupiter has a long history of surprising\n"
					+ "scientists – all the way back to 1610\n"
					+ "when Galileo Galilei found the first moons\n"
					+ "beyond Earth. That discovery changed the\n"
					+ "way we see the universe.");
		}
		
		if (sourceButton.getText().contains("Saturn")) {
			setSize(new Dimension(480, 500));
			planetDescription.setText("Saturn is the sixth planet from the Sun\n"
					+ "and the second-largest planet in our\n"
					+ "solar system.");
		}
		
		if (sourceButton.getText().contains("Uranus")) {
			setSize(new Dimension(480, 520));
			planetDescription.setText("Uranus is the seventh planet from the\n"
					+ "Sun, and has the third-largest diameter\n"
					+ "in our solar system. It was the first\n"
					+ "planet found with the aid of a telescope.");
		}
		
		if (sourceButton.getText().contains("Neptune")) {
			setSize(new Dimension(480, 500));
			planetDescription.setText("Dark, cold, and whipped by supersonic\n"
					+ "winds, ice giant Neptune is the eighth and\n"
					+ "most distant planet in our solar system.");
		}
	}

}
