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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class ProjetoSnake extends JFrame {

    public ProjetoSnake() {

        add(new Jogo());
       
        setResizable(false);
        pack();
        //O método setResizable () afeta as inserções do contêiner JFrame em algumas plataforma 
        //é importante chamá-lo antes do método pack (). 
        //Caso contrário, a colisão da cabeça da serpente com as bordas direita e inferior pode não funcionar corretamente.
        
        setTitle("Snake");
        JMenuBar bar = new JMenuBar();
        JMenu mArq = new JMenu( "Arquivo" );
        JMenuItem mitSair = new JMenuItem("Sair");
        bar.add(mArq);
        setJMenuBar(bar);
        mArq.add(mitSair);
        
        mitSair.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		});
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new ProjetoSnake();
                ex.setVisible(true);                
            }
        });
    }
}
