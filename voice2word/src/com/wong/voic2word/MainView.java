package com.wong.voic2word;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * @author 黄小天  1853955116@qq.com
 * @date 2018年8月21日 上午10:51:05
 * 程序主入口
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	
	private static JFrame mJframe;
	//右键弹窗
	private JPopupMenu popup = new JPopupMenu();
	//定义系统托盘
	private SystemTray tray;
	//定义系统托盘图标
	private TrayIcon trayIcon;
	//标志是否已经开始录音
	private boolean startFlag = false;
	//按钮 X 轴位置
	private int buttonOldX = 0;
	//按钮 Y 轴位置
	private int buttonOldY = 0;
	//用于记录鼠标左键按下和放开的时间戳，如果时间太长，则不进行操作，只进行窗口拖动操作
	private long clickDuration = 0; 
	//执行录音操作的对象
	private Recoder recoder = new Recoder();
	
	public static void main(String args[]) {
		mJframe = new MainView();
	}
	
	/**
	 * 界面初始化
	 */
	public MainView() {
		setSize(45, 45);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true); // 设置窗口为无标题
		//创建按钮
		ImageIcon imgRecognize = new ImageIcon(getClass().getResource("/com/wong/voic2word/res/btn_recognize.png"));
		JButton jbtnRecognize = this.createImageButton(imgRecognize);
		this.setMouseListener(jbtnRecognize);
		
		//创建放置按钮的面板
		JPanel mMainJpanel = new JPanel();
		mMainJpanel.add(jbtnRecognize);
		mMainJpanel.setBackground(new Color(0,0,0,0));
		
		//创建右键菜单
		createPopMenu();
		setTray(imgRecognize.getImage());
		
		setIconImage(imgRecognize.getImage());
		setContentPane(mMainJpanel);
		setAlwaysOnTop(true);
		setBackground(new Color(0,0,0,0));
		//窗口中间置顶
		int x = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth())/2;
		setLocation(x, 0);
		setVisible(true);
		
	}
	
	/**
	 * 设置按钮鼠标监听事件
	 * @param button
	 * @param iconPath
	 */
	public void setMouseListener(final JButton button) {
		final ImageIcon imagePressed = new ImageIcon(getClass().getResource("/com/wong/voic2word/res/btn_recognize_p.png"));
		final ImageIcon image = new ImageIcon(getClass().getResource("/com/wong/voic2word/res/btn_recognize.png"));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {  
	       	    buttonOldX = e.getX();//记录鼠标按下时的坐标
	       	    buttonOldY = e.getY();
	       	    clickDuration = System.currentTimeMillis();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {   
	       	    if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
	       	    	// 判断是鼠标右键 弹出菜单
	       	    	popup.show(e.getComponent(), e.getX(), e.getY());
	       	    	
	       	    }else if(System.currentTimeMillis() - clickDuration < 150) {//判断是否是双击
	       	    	//判断状态，如果是正在录音状态则设置为非录音状态，反之亦然
	       	    	if (!startFlag) {
	       	    		button.setIcon(imagePressed);
	       	    		recoder.startRecord();
	       	    		startFlag = true;
	       	    	}else {
	       	    		button.setIcon(image);
	       	    		recoder.stopRecord();
	       	    		startFlag = false;
	       	    	}
	       	    }
			}
        });
		
		button.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
			    int xOnScreen = e.getXOnScreen();
			    int yOnScreen = e.getYOnScreen();
			    int xx = xOnScreen - buttonOldX;
			    int yy = yOnScreen - buttonOldY;
			    mJframe.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		});
	}
	
	/**
	 * 创建图片按钮哎
	 * @param img
	 * @return
	 */
	private JButton createImageButton(ImageIcon img) {
		JButton button = new JButton("");
		button.setIcon(img);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBackground(null);

		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		return button;
	}
	
	/**
	 * 创建右键菜单
	 */
	private void createPopMenu() {
		popup.add(new JMenuItem(new AbstractAction("退出程序") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
	}
	
	private void setTray(Image image) {
		//判断当前系统是否支持系统托盘
		if(SystemTray.isSupported()) {
			//如果支持系统托盘的话就把任务栏大图标去掉
			setType(JFrame.Type.UTILITY);
			
			//定义系统托盘图标弹出菜单
			PopupMenu popupMenu = new PopupMenu();
			MenuItem exit = new MenuItem("exit");
			exit.addActionListener(new ActionListener() { // 按下退出键
				public void actionPerformed(ActionEvent e) {
					tray.remove(trayIcon);
					System.exit(0);
				}
			});//addActionListener
			popupMenu.add(exit);
			
			tray = SystemTray.getSystemTray();//通过静态方法得到系统托盘
			trayIcon = new TrayIcon(image, "voice2word", popupMenu);//创建TrayIcon对象得到托盘图标
			trayIcon.setImageAutoSize(true);//设置托盘图标自动设置尺寸
			try {
				//将托盘图标设置到系统托盘中
				tray.add(trayIcon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//为托盘图标注册监听器
			trayIcon.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}