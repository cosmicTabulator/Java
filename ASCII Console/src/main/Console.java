package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import graphics.Cell;
import graphics.Display;

public class Console {

	
	boolean squareCells = true;
	Display display;
	
	Vector cursorPos;
	
	int width;
	int height;
	
	int cellWidth;
	int cellHeight;
	
	int trueWidth;
	int trueHeight;
	
	int fontSize;
	int descender;
	int buffer = 2;
	
	Set<Cell> cellFeild = new HashSet<Cell>();
	HashMap<Integer,Cell> cellMap = new HashMap<Integer,Cell>();
	
	Color textColor;
	Color tileColor;
	Color cursorColor;
	Color bgColor;
	Color textbgColor;
	Color fillColor;
	
	Font font;
	
	public Console(int width, int height, int fontSize, Color defaultColor, String fontName){
		
		this.fontSize = fontSize;
		font = new Font(fontName, Font.PLAIN, fontSize);
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(font);
		
		cellHeight = fm.getHeight() + 2 * buffer;
		cellWidth = fm.charWidth('@') + 2 * buffer;
		
		if(squareCells){
			cellWidth = cellHeight;
		}
		
		descender = fm.getMaxDescent();
		System.out.println(descender);
		
		this.width = width;
		this.height = height;
		
		trueWidth = cellWidth * width;
		trueHeight = cellHeight * height;
		
		display = new Display(trueWidth, trueHeight);
		
		bgColor = defaultColor;
		
		float[] hsb = new float[3];
		
		Color.RGBtoHSB(defaultColor.getRed(), defaultColor.getGreen(), defaultColor.getBlue(), hsb);
		
		if(hsb[2] < 0.5){
			textColor = Color.WHITE;
		} else{
			textColor = Color.BLACK;
		}
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				
				Vector pos = new Vector(x,y);
				Cell cell = new Cell(pos, bgColor);
				cellFeild.add(cell);
				cellMap.put(pos.hashCode(), cell);
				
			}
		}
		
	}
	
	public void setCellColor(Color c, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		cell.setColor(c);
	}
	
	public void fillRect(Color c, Vector pos1, Vector pos2){
		Vector fillPos;
		for(int x = pos1.a; x <= pos2.a; x++){
			for(int y = pos1.b; y <= pos2.b; y++){
				fillPos = new Vector(x, y);
				setCellColor(c, fillPos);
			}
		}
		
	}
	
	public void setTextColor(Color c){this.textColor = c;}
	
	public void setTextColor(Color c, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		cell.setTextColor(c);
	}
	
	public void putChar(char c, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		cell.setChar(c);
		cell.blank = false;
		
	}
	
	public void putChar(char c, Color color, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		cell.setChar(c);
		cell.setTextColor(color);
		cell.blank = false;
		
	}
	
	public void putChar(int i, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		char c = getAscii(i);
		cell.setChar(c);
		cell.blank = false;
		
	}
	
	public void putChar(int i, Color color, Vector pos){
		Cell cell = cellMap.get(pos.hashCode());
		char c = getAscii(i);
		cell.setChar(c);
		cell.setTextColor(color);
		cell.blank = false;
		
	}
	
	public boolean putString(String string, Vector pos){
		
		int xMarker = pos.a;
		int yMarker = pos.b;
		
		for(char c : string.toCharArray()){
			putChar(c, new Vector(xMarker, yMarker));
			xMarker++;
			if(xMarker > width){
				xMarker = 0;
				yMarker++;
				if(yMarker > height){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public boolean putString(String string, Color color, Vector pos){
		
		int xMarker = pos.a;
		int yMarker = pos.b;
		
		for(char c : string.toCharArray()){
			putChar(c, color, new Vector(xMarker, yMarker));
			xMarker++;
			if(xMarker > width){
				xMarker = 0;
				yMarker++;
				if(yMarker > height){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public void clearCell(Vector pos){
		pos = new Vector(pos.a, pos.b +1 -descender);
		Cell cell = cellMap.get(pos.hashCode());
		cell.blank = true;
	}
	
	public Set<Integer> getKeys(){
		return display.keys;
	}
	
	public void setStyle(int style){
		
		String name = font.getName();
		font = new Font(name, style, fontSize);
		
	}
	
	public void draw(){
		
		Graphics2D g = display.graphics2D();
		
		for(Cell c : cellMap.values()){
			g.setColor(c.getColor());
			if(c.getPos() == cursorPos){
				g.setColor(cursorColor);
			}
			g.fillRect(c.getX()*cellWidth, c.getY()*cellHeight, cellWidth, cellHeight);
			if(!c.blank){
				if(c.getTextColor() != null){
					g.setColor(c.getTextColor());
				} else {
					g.setColor(textColor);
				}
				g.setFont(font);
				g.drawString(String.valueOf(c.getChar()), c.getX()*cellWidth + buffer, c.getY()*cellHeight - descender - buffer + cellHeight);
			}
		}
		
		g.dispose();
		
		display.reDraw();
		
	}
	
    public static final char[] EXTENDED = { 0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0 };

    public char getAscii(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return EXTENDED[code - 0x7F];
        }
        return (char) code;
    }

    public void printAsciiSet(Color color){
		char[] charList = new char[256];
		
		for(int i = 0; i < 255; i++){
			charList[i] = getAscii(i);
			printChar(i);
		}
		
		int xMark = 0;
		int yMark = 0;
		for(char c : charList){
			putChar(c, color, new Vector(xMark,yMark));
			xMark++;
			if(xMark >= width){
				yMark++;
				xMark = 0;
				if(yMark >= height){
					break;
				}
			}
		}
    }
    
    public void printChar(int code) {
        System.out.printf("%c%n", getAscii(code));
    }
	
	public static void main(String[] args){
		
		Console console = new Console(60, 40, 12, Color.WHITE, Font.SANS_SERIF);
		
		console.printAsciiSet(Color.BLACK);
		
		console.putChar('a', new Vector(59, 39));
		
		console.setCellColor(Color.RED, new Vector(0,0));
		
		console.draw();

	}
	
}
