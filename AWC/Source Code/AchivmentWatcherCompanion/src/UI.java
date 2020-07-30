import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UI extends JFrame{
	static JTextField textField;
	static JTable table=new JTable();
	static DefaultTableModel model=new DefaultTableModel()
    {
      public Class<?> getColumnClass(int column)
      {
        switch(column)
        {
        case 0:
          return Boolean.class; 
        case 1:
          return String.class;
        case 2:
          return String.class;
       

          default:
            return String.class;
        }
      }
    };
	//private JFrame frame;

	/**
	 * Launch the application.
	 */
	static boolean drap=true;

	/**
	 * Create the application.
	 */
	
	public UI() {
		setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBounds(100, 100, 465, 490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Achievement Watcher\\icon.png");
		setIconImage(icon);
		
		
		setTitle("AWC");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 451, 431);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Achievement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 42, 431, 378);
		panel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 16, 415, 351);
		scrollPane.setViewportView(table);
		

	    //ASSIGN THE MODEL TO TABLE
		table.setModel(model);
		model.addColumn("Select");
		model.addColumn("Achivment");
		model.addColumn("Description");
	    TableColumnModel cm = UI.table.getColumnModel();
		cm.getColumn(0).setPreferredWidth(10);
		cm.getColumn(1).setPreferredWidth(50);
		cm.getColumn(2).setPreferredWidth(260);
		panel_1.add(scrollPane);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setText("Game id");
		textField.setColumns(10);
		textField.setBounds(10, 11, 96, 20);
		panel.add(textField);
		
		JButton button = new JButton("Unlock");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Controller.CAFF();
				// TODO Auto-generated method stub

		        //GET SELECTED ROW
		        for(int i=0;i<table.getRowCount();i++)
		        {
		          Boolean checked=Boolean.valueOf(table.getValueAt(i, 0).toString());
		          String Val= AchivManger.ach.get(i);
		        		 
		          

		          //DISPLAY
		          if(checked)
		          {
		            
		            AchivManger.Unlock(Controller.AWCdir+"\\AWC\\"+Controller.appidS+"\\achieve.dat",AchivManger.ach.get(i)+"=0",AchivManger.ach.get(i)+"=1");
		            
		          }
		        }
		        JOptionPane.showMessageDialog(null,"Achievement Unlocked");
				
			}
		});
		button.setBounds(352, 8, 89, 23);
		panel.add(button);
		
		JLabel gt = new JLabel("Game Title");
		
		
		JButton Select = new JButton("Select/Create");
		Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(drap) {
					Controller.SAC();
					gt.setText(AchivManger.appName);
					
					drap =false;
					}
				else{
					 JOptionPane.showMessageDialog(null,"Click clear first");
				}
				
				
				    
			}
		});
		Select.setBounds(117, 10, 108, 23);
		
		panel.add(Select);
		
		JButton clr = new JButton("Clear");
		clr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				Controller.ClearModel();
				AchivManger.CAL(); 
				gt.setText("");
				drap =true;
				
			}
		});
		clr.setBounds(235, 10, 57, 23);
		panel.add(clr);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Initialization");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Create AWC Folder");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.CreateAWCFolder();
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Get DataBase Folder");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.GetDataBaseFolder();
			}
		});
		menu.add(menuItem_1);
		
		
		menuBar.add(gt);
		
		setVisible(true);
	}
}
