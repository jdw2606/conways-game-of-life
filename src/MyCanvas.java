import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;



public class MyCanvas extends JPanel{
	private int x = 800;
	private int y = 800;
	
	private int width = 20;
	
	private int cols = x/width;
	private int rows = y/width;
	
	private int[][] grid; 
	
	private JButton nextGen;
	private JButton auto;
	
	public MyCanvas(JButton nextGen, JButton auto) {
		this.setPreferredSize(new Dimension(x, y));
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		
		this.nextGen = nextGen;
		this.auto = auto;
		
		grid = new int[x/width][y/width];
		grid = createGrid();
		
		this.nextGen.addActionListener(e -> {
			grid = nextGen();
			repaint();
		});
		
		this.auto.addActionListener(e -> {
			new Timer(50, ev -> {
		        grid = nextGen();
		        repaint();
		    }).start();
		});

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setBackground(Color.BLACK);
		g2D.setStroke(new BasicStroke(3));
		g2D.setColor(Color.WHITE);
		
		printGrid(g2D);
	}
	
	public int[][] createGrid() {
		int[][] arr = new int[cols][rows];
		for (int i = 0; i < arr.length; i ++) {
			for (int j = 0; j < arr[i].length; j ++) {
				arr[i][j] = (int) (Math.random() * 2);
			}
		}
		return arr;
	}
	
	public void printGrid(Graphics2D g2D) {
		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid[i].length; j ++) {
				if (grid[i][j] == 1) {
					g2D.setColor(Color.WHITE);
					g2D.fillRect(j * width + 1, i * width + 1, width - 1, width - 1);
				}
			}
		}
	}
	
	public int[][] nextGen() {
		int[][] next = new int[cols][rows];
		for (int i = 0; i < grid.length ; i ++) {
			for (int j = 0; j < grid[i].length ; j ++) {
				
				int sum = count(grid, i, j);
				if (grid[i][j] == 0 && sum == 3) {
					next[i][j] = 1;
				} else if (grid[i][j] == 1 && (sum == 2 || sum == 3)) {
					next[i][j] = 1;
				} else {
					next[i][j] = 0;
				}
			}
		}
		return next;
	}
	
	public int count(int[][] grid, int x, int y) {
		int sum = 0;
		for (int i = -1; i < 2; i ++) {
			for (int j = -1; j < 2; j ++) {
				int col = (x + i + cols) % cols;
				int row = (y + j + rows) % rows;
				sum += grid[col][row];
			}
		}
		sum -= grid[x][y];
		return sum;
	}
}
