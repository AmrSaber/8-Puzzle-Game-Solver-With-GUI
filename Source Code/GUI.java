package pkg8.puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author Amr Saber
 * 
 * The GUI of the game and everything that manipulates it
 * (Except the timer that is in the BoardControl class)
 */
public class GUI extends javax.swing.JFrame {
    
    public final BoardControl boardControl;
    private final JButton tiles[];
    //private final BasicPlayer player;
    
    /**
     * Creates new form GUI
     */
    public GUI() {

        //frame Title
        super("8 Puzzle");
        
        //set GUI settings
        initComponents();
        this.setLocationRelativeTo(null);   //center the frame in the screen on open
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        
        //Global variabels intialization
        this.tiles = new JButton[]{Tile_1, Tile_2, Tile_3, Tile_4, Tile_5, Tile_6, Tile_7, Tile_8, Tile_0};
        this.boardControl = new BoardControl();
        
        //intialize the tiles, set the font, disable focus and add the action listener
        for(int i = 0 ; i < tiles.length ; ++i){
            
            tiles[i].setFocusable(false);
            tiles[i].setFont(tiles[i].getFont().deriveFont(25.0f));
            
            tiles[i].addActionListener(new ActionListener() {
                
                int num;
                
                ActionListener me(int i){
                    num = i;
                    return this;
                }
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(GUI.this.boardControl.isSolving()) return;
                    GUI.this.boardControl.tilePressed(num);
                    GUI.this.drawBoard();
                }
            }.me(i));
        }
        
        //action listener for the reset button
        Button_Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.this.boardControl.resetBoard();
                GUI.this.drawBoard();
            }
        });
        
        //action listener for the randomize button
        Button_Rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GUI.this.boardControl.isSolving()) return;
                GUI.this.boardControl.randomizeBoard();
                GUI.this.drawBoard();
            }
        });
        
        //action listener for the solve button
        Button_Solve_A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GUI.this.boardControl.isSolving()) return;
                GUI.this.boardControl.solve(GUI.this, Solvers.SOLVE_METHOD.A_STAR);
                GUI.this.pack();
                //GUI.this.setLocationRelativeTo(null);
            }
        });
        
        Button_Solve_DFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GUI.this.boardControl.isSolving()) return;
                GUI.this.boardControl.solve(GUI.this, Solvers.SOLVE_METHOD.DFS);
                GUI.this.pack();
                //GUI.this.setLocationRelativeTo(null);
            }
        });
        
        Button_Speed.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(GUI.this.boardControl.isSolving()) return;
                String crnt = ((JButton)e.getSource()).getText();
                switch(crnt){
                    case "Slow":
                        GUI.this.boardControl.setTimerSpeed(BoardControl.SPEED.MEDIUM);
                        GUI.this.Button_Speed.setText("Medium");
                        break;
                    case "Medium":
                        GUI.this.boardControl.setTimerSpeed(BoardControl.SPEED.FAST);
                        GUI.this.Button_Speed.setText("Fast");
                        break;
                    case "Fast":
                        GUI.this.boardControl.setTimerSpeed(BoardControl.SPEED.SLOW);
                        GUI.this.Button_Speed.setText("Slow");
                        break;
                }
            }
            
        });
        
        this.drawBoard();
        this.pack();                //resize the frame to fit the new size of the buttons
    }
    
    //draw the current board on the tiles
    public final void drawBoard(){
        final byte[] board = boardControl.getCurrentBoard();
        int empty = -1;
        
        //label the buttons (the tiles)
        for(int i = 0 ; i < board.length ; ++i){
            if(board[i] == 0) empty = i;
            else tiles[i].setText(String.valueOf(board[i]));
        }
        
        //show all the buttons then hide the blank one
        for(JButton tile : tiles) tile.setVisible(true);
        tiles[empty].setVisible(false);

        //notify the panel to update
        Main_Middle.repaint();
        Main_Middle.revalidate();
    }
    
    public void setStatus(String stat){
        this.Label_Status.setText(stat);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        Main_Right = new javax.swing.JPanel();
        ButtonsPanel = new javax.swing.JPanel();
        Button_Rand = new javax.swing.JButton();
        Button_Reset = new javax.swing.JButton();
        Button_Solve_A = new javax.swing.JButton();
        Button_Solve_DFS = new javax.swing.JButton();
        Button_Speed = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Label_Status = new javax.swing.JLabel();
        Main_Middle = new javax.swing.JPanel();
        Tile_1 = new javax.swing.JButton();
        Tile_2 = new javax.swing.JButton();
        Tile_3 = new javax.swing.JButton();
        Tile_4 = new javax.swing.JButton();
        Tile_5 = new javax.swing.JButton();
        Tile_6 = new javax.swing.JButton();
        Tile_7 = new javax.swing.JButton();
        Tile_8 = new javax.swing.JButton();
        Tile_0 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_Right.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        Main_Right.setLayout(new java.awt.BorderLayout());

        ButtonsPanel.setOpaque(false);
        ButtonsPanel.setLayout(new java.awt.GridBagLayout());

        Button_Rand.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        Button_Rand.setText("Randomize");
        Button_Rand.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ButtonsPanel.add(Button_Rand, gridBagConstraints);

        Button_Reset.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        Button_Reset.setText("Reset");
        Button_Reset.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ButtonsPanel.add(Button_Reset, gridBagConstraints);

        Button_Solve_A.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        Button_Solve_A.setText("Solve - A*");
        Button_Solve_A.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ButtonsPanel.add(Button_Solve_A, gridBagConstraints);

        Button_Solve_DFS.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        Button_Solve_DFS.setText("Solve - DFS");
        Button_Solve_DFS.setFocusable(false);
        Button_Solve_DFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Solve_DFSActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ButtonsPanel.add(Button_Solve_DFS, gridBagConstraints);

        Button_Speed.setText("Slow");
        Button_Speed.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ButtonsPanel.add(Button_Speed, gridBagConstraints);

        Main_Right.add(ButtonsPanel, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        jPanel2.setOpaque(false);
        jPanel2.add(Label_Status);

        Main_Right.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(Main_Right, java.awt.BorderLayout.LINE_END);

        Main_Middle.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Main_Middle.setLayout(new java.awt.GridBagLayout());

        Tile_1.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_1, gridBagConstraints);

        Tile_2.setText("2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_2, gridBagConstraints);

        Tile_3.setText("3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_3, gridBagConstraints);

        Tile_4.setText("4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_4, gridBagConstraints);

        Tile_5.setText("5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_5, gridBagConstraints);

        Tile_6.setText("6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_6, gridBagConstraints);

        Tile_7.setText("7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_7, gridBagConstraints);

        Tile_8.setText("8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        Main_Middle.add(Tile_0, gridBagConstraints);

        getContentPane().add(Main_Middle, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_Solve_DFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Solve_DFSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Solve_DFSActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Rand;
    private javax.swing.JButton Button_Reset;
    private javax.swing.JButton Button_Solve_A;
    private javax.swing.JButton Button_Solve_DFS;
    private javax.swing.JButton Button_Speed;
    private javax.swing.JPanel ButtonsPanel;
    private javax.swing.JLabel Label_Status;
    public javax.swing.JPanel Main_Middle;
    private javax.swing.JPanel Main_Right;
    private javax.swing.JButton Tile_0;
    private javax.swing.JButton Tile_1;
    private javax.swing.JButton Tile_2;
    private javax.swing.JButton Tile_3;
    private javax.swing.JButton Tile_4;
    private javax.swing.JButton Tile_5;
    private javax.swing.JButton Tile_6;
    private javax.swing.JButton Tile_7;
    private javax.swing.JButton Tile_8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
