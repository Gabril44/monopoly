package monopoly;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Memepolimein extends JFrame
{
    
    private JPanel contentIncluder;
    static JTextArea infoConsole;
    static JTextArea infoPlayers;
    JPanel Paneldeljugador;
    static int turnCounter = 0;
    JButton btnNextTurno;
    JButton btnRollDadin;
    JButton btnPagarRnt;
    JButton btnComprar;
    JButton btnManual;
    Tablero Tableroenjuego;
    static int juegoON = 0;
    ArrayList<Ficha> jugadorenae = new ArrayList<>();
    Ficha jugador01;
    Ficha jugador02;
    CardLayout cart = new CardLayout();
    Boolean Dadoparajugador01 = false;
    Boolean Dadoparajugador02 = false;
    JPanel testeador;
	protected Component parentComponent;
    
    


    public Memepolimein()
    {
        //Aca creamos el tablero y la ventana en si para que se ejecute dentro de esos parametros
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,720);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);
//Seteamos la ventana principal
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        layeredPane.setBounds(6, 6, 632, 630);
        contentIncluder.add(layeredPane);
//Hacemos el tablerito del juego
        Tableroenjuego = new Tablero(6,6,612,612);
        Tableroenjuego.setBackground(new Color(51, 255, 153));
        layeredPane.add(Tableroenjuego, new Integer(0));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        rightPanel.setBounds(640, 6, 419, 600);
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        JPanel testeador = new JPanel();
        testeador.setBounds(81, 312, 246, 68);
        rightPanel.add(testeador);
        testeador.setLayout(null);

        JPanel testeador2 = new JPanel();
        testeador2.setBounds(81, 200, 246, 68);
        rightPanel.add(testeador2);
        testeador2.setLayout(null);

        jugador01 = new Ficha(1, Color.BLACK);
        jugadorenae.add(jugador01);
        layeredPane.add(jugador01, new Integer(1));

        jugador02 = new Ficha(2, Color.PINK);
        jugadorenae.add(jugador02);
        layeredPane.add(jugador02, new Integer(2));
        
        btnManual = new JButton("Manual");
        btnManual.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Manual manual = new Manual();
        		JOptionPane.showMessageDialog(parentComponent, manual.toString());
        	}
        });

        btnManual.setBounds(147, 100, 117, 29);
        rightPanel.add(btnManual);
        btnManual.setEnabled(true);


        btnComprar = new JButton("Comprar");
        btnComprar.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {

                
                Ficha currentPlayer = jugadorenae.get(juegoON);
                infoConsole.setText("Has comprado "+Tableroenjuego.getAllCuadrantes().get(currentPlayer.getPosicionDeCasilleroActual()).getName());
                currentPlayer.buyTitleDeed(currentPlayer.getPosicionDeCasilleroActual());
                int Retirarmonto = Tableroenjuego.getAllCuadrantes().get(currentPlayer.getPosicionDeCasilleroActual()).getPrecio();
                currentPlayer.Retirardinero(Retirarmonto);
                btnComprar.setEnabled(false);

                infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());
            }
        });
        btnComprar.setBounds(81, 478, 117, 29);
        rightPanel.add(btnComprar);
        btnComprar.setEnabled(false);

        btnPagarRnt = new JButton("Pagar renta");

        btnPagarRnt.setBounds(210, 478, 117, 29);
        rightPanel.add(btnPagarRnt);
        btnPagarRnt.setEnabled(false);

        btnPagarRnt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                Ficha currentPlayer = jugadorenae.get(juegoON);
                Ficha duenioDelCuadrante = jugadorenae.get((Ficha.Filmina.get(currentPlayer.getPosicionDeCasilleroActual()))==1?0:1);
                infoConsole.setText("pagaste al jugador "+duenioDelCuadrante.getNumerojug());

                int withdrawAmount = Tableroenjuego.getAllCuadrantes().get(currentPlayer.getPosicionDeCasilleroActual()).getRentPrecio();
                System.out.println(withdrawAmount);
                currentPlayer.PagarDeuda(withdrawAmount);
                duenioDelCuadrante.PagarDeuda(withdrawAmount);

                btnNextTurno.setEnabled(true);
                btnPagarRnt.setEnabled(false);
                infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());
            }

        });


        Dado dado1 = new Dado(244, 406, 40, 40);
        layeredPane.add(dado1, new Integer(1));

        Dado dado2 = new Dado(333, 406, 40, 40);
        layeredPane.add(dado2, new Integer(1));

        btnRollDadin = new JButton("Tirar Dados");
        btnRollDadin.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (juegoON == 0) {
                    // turno del player 1
                    int dice1OldValue = dado1.getValorcara();
                    int dice2OldValue = dado2.getValorcara();
                    dado1.tirarDados();
                    dado2.tirarDados();
                    int dicesTotal = dado1.getValorcara() + dado2.getValorcara();
                    if (dado1.getValorcara() == dado2.getValorcara()) {
                        Dadoparajugador01 = true;
                    } else {
                        Dadoparajugador01 = false;
                    }
                    jugador01.moverse(dicesTotal);
                    if (Ficha.Filmina.containsKey(jugador01.getPosicionDeCasilleroActual()) // if bought by someone
                            && Ficha.Filmina.get(jugador01.getPosicionDeCasilleroActual()) != jugador01.getNumerojug() // not by itself
                    ) {
                        btnComprar.setEnabled(false);
                        btnRollDadin.setEnabled(false);
                        btnNextTurno.setEnabled(false);
                        btnPagarRnt.setEnabled(true);
                    }
                    else if (Ficha.Filmina.containsKey(jugador01.getPosicionDeCasilleroActual()) // if bought by someone
                            && Ficha.Filmina.get(jugador01.getPosicionDeCasilleroActual()) == jugador01.getNumerojug()) { // and by itself
                        btnComprar.setEnabled(false);
                        btnPagarRnt.setEnabled(false);
                        btnNextTurno.setEnabled(true);
                    }
                    else if (Tableroenjuego.getCuadrantesincomprables().contains(Tableroenjuego.getAllCuadrantes().get(jugador01.getPosicionDeCasilleroActual()))) {
                        if (Tableroenjuego.getAllCuadrantes().get(jugador01.getPosicionDeCasilleroActual()).getName().equals("FORTUNA")){
                            Especiales.especiales.Fortuna(jugador01);
                        }else if (Tableroenjuego.getAllCuadrantes().get(jugador01.getPosicionDeCasilleroActual()).getName().equals("CARCEL") || Tableroenjuego.getAllCuadrantes().get(jugador01.getPosicionDeCasilleroActual()).getName().equals("CARCEL 2.0") ){
                            Especiales.especiales.Carcel(jugador01);

                        }
                        btnComprar.setEnabled(false);
                        btnNextTurno.setEnabled(true);
                    } else if (!Ficha.Filmina.containsKey(jugador01.getPosicionDeCasilleroActual())) { // if not bought by someone
                        btnComprar.setEnabled(true);
                        btnNextTurno.setEnabled(true);
                        btnPagarRnt.setEnabled(false);
                    }else{
                        btnNextTurno.setEnabled(true);
                    }


                } else {
                    // turno del player 2
                    infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());
                    int dice1OldValue = dado1.getValorcara();
                    int dice2OldValue = dado2.getValorcara();
                    dado1.tirarDados();
                    dado2.tirarDados();
                    int dicesTotal = dado1.getValorcara() + dado2.getValorcara();
                    if (dado1.getValorcara() == dado2.getValorcara()) {
                        Dadoparajugador02 = true;
                    } else {
                        Dadoparajugador02 = false;
                    }
                    jugador02.moverse(dicesTotal);
                    if (Ficha.Filmina.containsKey(jugador02.getPosicionDeCasilleroActual()) // si esta comprado por alguien
                            && Ficha.Filmina.get(jugador02.getPosicionDeCasilleroActual()) != jugador02.getNumerojug() // no por el mismo
                    ) {
                        btnComprar.setEnabled(false);
                        btnRollDadin.setEnabled(false);
                        btnNextTurno.setEnabled(false);
                        btnPagarRnt.setEnabled(true);
                    }
                    else if (Ficha.Filmina.containsKey(jugador02.getPosicionDeCasilleroActual()) // si esta comprado por alguien
                            && Ficha.Filmina.get(jugador02.getPosicionDeCasilleroActual()) == jugador02.getNumerojug()) { // no por el mismo
                        btnComprar.setEnabled(false);
                        btnPagarRnt.setEnabled(false);
                        btnNextTurno.setEnabled(true);
                    }
                    else if (Tableroenjuego.getCuadrantesincomprables().contains(Tableroenjuego.getAllCuadrantes().get(jugador02.getPosicionDeCasilleroActual()))) {
                        if (Tableroenjuego.getAllCuadrantes().get(jugador02.getPosicionDeCasilleroActual()).getName().equals("FORTUNA")){
                            Especiales.especiales.Fortuna(jugador02);
                        }else if (Tableroenjuego.getAllCuadrantes().get(jugador02.getPosicionDeCasilleroActual()).getName().equals("CARCEL") || Tableroenjuego.getAllCuadrantes().get(jugador02.getPosicionDeCasilleroActual()).getName().equals("CARCEL 2.0") ){
                            Especiales.especiales.Carcel(jugador02);

                        }
                        btnComprar.setEnabled(false);
                        btnNextTurno.setEnabled(true);
                    } else if (!Ficha.Filmina.containsKey(jugador02.getPosicionDeCasilleroActual())) { // si no esta comprado por alguien
                        btnComprar.setEnabled(true);
                        btnNextTurno.setEnabled(true);
                        btnPagarRnt.setEnabled(false);
                    }else{
                        btnNextTurno.setEnabled(true);
                    }

                }

                btnRollDadin.setEnabled(false);
                if (Dadoparajugador01 || Dadoparajugador02) {
                    infoConsole.setText("Apreta siguiente turno para que el jugador " + (juegoON == 0 ? 1 : 2) + " tire el dado!");
                } else {
                    infoConsole.setText("Apreta siguiente turno para que el jugador " + (juegoON == 0 ? 2 : 1) + " tire el dado!");
                }



                layeredPane.remove(Tableroenjuego);
                layeredPane.add(Tableroenjuego, new Integer(0));

                infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());

            }
        });
        btnRollDadin.setBounds(81, 413, 246, 53);
        rightPanel.add(btnRollDadin);



        btnNextTurno = new JButton("Siguiente Turno");
        btnNextTurno.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jugador02.getDinero()<=0){
                    btnRollDadin.setEnabled(false);
                    btnComprar.setEnabled(false);
                    btnPagarRnt.setEnabled(false);
                    btnNextTurno.setEnabled(false);
                    System.out.println("Gano el jugador 1!");
                }else if (jugador01.getDinero()<=0){
                    btnRollDadin.setEnabled(false);
                    btnComprar.setEnabled(false);
                    btnPagarRnt.setEnabled(false);
                    btnNextTurno.setEnabled(false);
                    System.out.println("Gano el jugador 2!");
                }else {
                    btnRollDadin.setEnabled(true);
                    btnComprar.setEnabled(false);
                    btnPagarRnt.setEnabled(false);
                    btnNextTurno.setEnabled(false);
                }
                if (juegoON == 0 && Dadoparajugador01) {
                    juegoON = 0;
                    Dadoparajugador01 = false;
                } else if (juegoON == 1 && Dadoparajugador02) {
                    juegoON = 1;
                    Dadoparajugador02 = false;
                } else if (!Dadoparajugador01 && !Dadoparajugador02) {
                    juegoON = (juegoON + 1) % 2;
                }



                infoConsole.setText("Jugador "+(juegoON==0 ? 1 : 2)+" tomara su turno");

                infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());
            }


        });
        btnNextTurno.setBounds(81, 519, 246, 53);
        rightPanel.add(btnNextTurno);
        btnNextTurno.setEnabled(false);

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 6, 234, 56);
        testeador.add(infoConsole);
        infoConsole.setLineWrap(true);
        infoConsole.setText("Jugador 1 tire los dados");

        infoPlayers = new JTextArea();
        infoPlayers.setColumns(20);
        infoPlayers.setRows(5);
        infoPlayers.setBounds(6, 6, 234, 56);
        testeador2.add(infoPlayers);
        infoPlayers.setLineWrap(true);
        infoPlayers.setText("Jugador 1: "+jugador01.getDinero()+"\nJugador 2: "+jugador02.getDinero());

    }


    public static void main(String[] args)
    {
        //Ejecutamos todo en el metodo principal ozea que vamos a poner todo el desatre de main hay dentro sino su bardo
        Memepolimein frame = new Memepolimein();
        frame.setVisible(true);
    }
}