import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SudokuGUI extends JFrame {

    private static final int ROWS = 9;
    private static final int COLS = 9;
    private JTextField[][] cells;
    private JButton solveButton;
    public SudokuGUI() {
        super("Sudoku");

        JPanel panel = new JPanel(new GridLayout(ROWS, COLS));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Font font = new Font("SansSerif", Font.BOLD, 20);
        cells = new JTextField[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JTextField cell = new JTextField();
                cell.setPreferredSize(new Dimension(50, 50));
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[i][j] = cell;
                panel.add(cells[i][j]);
            }
        }
        solveButton = new JButton("Solve");

        resolveSudokoProblem resolveSudokoProblem=new resolveSudokoProblem("sudokuInitialTable.txt");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveSudokoProblem.resolveSudokuproblem();
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++){
                        if(resolveSudokoProblem.matrix[i][j]!=-1)
                            cells[i][j].setText(String.valueOf(resolveSudokoProblem.matrix[i][j]));
                    }
                }
            }
        });
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(resolveSudokoProblem.matrix[i][j]!=-1)
                cells[i][j].setText(String.valueOf(resolveSudokoProblem.matrix[i][j]));
            }
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(solveButton, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SudokuGUI();
    }
}
