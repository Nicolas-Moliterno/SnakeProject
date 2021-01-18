/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosnake;

/**
 *
 * @author Nicolas
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Jogo extends JPanel implements ActionListener {

    private final int B_WIDTH1 = 300;
    private final int B_HEIGHT1 = 300;
    private final int B_WIDTH2 = 300;
    private final int B_HEIGHT2 = 350;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    
    
    //As constantes B_WIDTH e B_HEIGHT determinam o tamanho da placa
    //O DOT_SIZE é o tamanho da maçã e o ponto da cobra 
    //A constante RAND_POS é usada para calcular uma posição aleatória para uma maçã
    //A constante DELAY determina a velocidade do jogo.
    
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    //Essas matrizes armazenam as coordenadas x e y de todo o corpo da cobra.
    
    
    private int dots;
    private int maca_x;
    private int maca_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image maca;
    private Image cabeca;
    
    
    public Jogo() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH1, B_HEIGHT1));
        loadImages();
        initGame();
    }
    
  private void Score(){
      JLabel score = new JLabel("Score :");
      JTextField pontos = new JTextField();
        add(score);
        add(pontos);
            //TODO-------------
  };

    private void loadImages() {

        ImageIcon iid = new ImageIcon(getClass().getResource("Corpo.png"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(getClass().getResource("maca.png"));
        maca = iia.getImage();

        ImageIcon iih = new ImageIcon(getClass().getResource("Cabeca.png"));
        cabeca = iih.getImage();
    }
    
    //No método loadImages () obtemos as imagens para o jogo
    //classe ImageIcon é usada para exibir imagens PNG.

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    //No método initGame (), criamos a cobra,
    //localizamos aleatoriamente uma maçã no quadro e iniciamos o temporizador.

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(maca, maca_x, maca_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(cabeca, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
            
        } 
        
    }

    private void gameOver(Graphics g) {
        
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH1 - metr.stringWidth(msg)) / 2, B_HEIGHT1 / 2);
        
        String msg22 = Integer.toString(dots-3);
        String msg11 = "Pontuação : ";
        String msgF = msg11.concat(msg22);
        Font pequena = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrica = getFontMetrics(pequena);
        int pox = 100;
        int poy = 100;
        g.setColor(Color.white);
        g.setFont(pequena);
        g.drawString(msgF, (B_WIDTH2 - metrica.stringWidth(msgF)) / 2, B_HEIGHT2 / 2);
        //PONTUAÇÃO
    }

    private void checkApple() {

        if ((x[0] == maca_x) && (y[0] == maca_y)) {
            int pontos = 0;
               
            dots++;
            locateApple();
        }
        //TODO---------------
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        //Este código move as juntas até a serpente.

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        //Esta linha move a cabeça para a esquerda.



        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
        
    }
    //Se a maçã colide com a cabeça, aumentamos o número de juntas da cobra. Chamamos o método locateApple () que posiciona aleatoriamente um novo objeto Apple.
    //  No método move (), temos o algoritmo-chave do jogo.
     // Nós controlamos a cabeça da cobra. Podemos mudar sua direção com as teclas do cursor. 
    //O resto das articulações movem uma posição até a corrente.
     // A segunda articulação se move onde o primeiro foi, a terceira articulação, onde o segundo foi, etc.
        

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        //Se a cobra atinge uma das articulações com a cabeça,o jogo mostra game over.

        if (y[0] >= B_HEIGHT1) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH1) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }
    //No método checkCollision (), determinamos se a cobra atingiu-se ou uma das paredes.

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        maca_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        maca_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}