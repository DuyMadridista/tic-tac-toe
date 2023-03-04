import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;;

public class caro extends JFrame implements MouseListener {
    public static void main(String[] args) {
        new caro();
    }

    int n = 10, s = 45, off = 50;
    List<Point> danhsach = new ArrayList<Point>();
    int[][] board = new int[n][n];
    int currentPlayer = 1;
    Stack<Point> history = new Stack<>();

    public caro() {
        this.setTitle("Caro");
        this.setSize(s * n + 2 * off, s * n + 2 * off);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.setVisible(true);
        
        
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //ve ban co
        g.setColor(Color.BLACK);
        for (int i = 0; i <= n; i++) {
            g.drawLine(off, off + s * i, off + s * n, off + s * i);
            g.drawLine(off + s * i, off, off + s * i, off + s * n);
        }
        //ve
        g.setFont(new Font("Bradley Hand ITC", Font.BOLD, s));
        for (int i = 0; i < danhsach.size(); i++) {
            String str = "o";
            Color c = Color.RED;
            if (i % 2 != 0) {
                str = "x";
                c = Color.BLUE;
            }

            int x = off + danhsach.get(i).x * s + s - s / 2 - s / 4;
            int y = off + danhsach.get(i).y * s + s - s / 2 + s / 4;
            g.setColor(c);
            g.drawString(str, x, y);
        }
    }

    public boolean check(int x, int y) {
        int count = 0;
        // kiểm tra hàng ngang
        for (int i = 0; i < n; i++) {
            if (board[x][i] == board[x][y])
                count++;
            else
                count = 0;
            if (count == 5)
                return true;
        }
        // kiểm tra hàng dọc
        count = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][y] == board[x][y])
                count++;
            else
                count = 0;
            if (count == 5)
                return true;
        }
        // kiểm tra đường chéo chính
        count = 0;
        for (int i = 0; i < n; i++) {
            if (x - i < 0 || y - i < 0)
                break;
            if (board[x - i][y - i] == board[x][y])
                count++;
            else
                break;
        }
        for (int i = 1; i < n; i++) {
            if (x + i >= n || y + i >= n)
                break;
            if (board[x + i][y + i] == board[x][y])
                count++;
            else
                break;
            if (count == 5)
                return true;
        }
        // kiểm tra đường chéo phụ
        count = 0;
        for (int i = 0; i < n; i++) {
            if (x - i < 0 || y + i >= n)
                break;
            if (board[x - i][y + i] == board[x][y])
                count++;
            else
                break;
        }
        for (int i = 1; i < n; i++) {
            if (x + i >= n || y - i < 0)
                break;
            if (board[x + i][y - i] == board[x][y])
                count++;
            else
                break;
            if (count == 5)
                return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            if (danhsach.size() != 0) {
                danhsach.remove(danhsach.size() - 1);
                this.repaint();
                return;
            }
        }
        int x = e.getX();
        int y = e.getY();
        if (x < off || x >= off + s * n)
            return;
        if (y < off || y >= off + s * n)
            return;

        int ix = (x - off) / s;
        int iy = (y - off) / s;
        for (Point p : danhsach) {
            if (p.x == ix && p.y == iy)
                return;
        }
        danhsach.add(new Point(ix, iy));
        board[ix][iy] = currentPlayer;
        history.push(new Point(ix, iy));
        this.repaint();
        if (check(ix, iy)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " win!");
            System.exit(0);
        }
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}