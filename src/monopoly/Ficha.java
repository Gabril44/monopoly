package monopoly;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Ficha extends JPanel
{

    private ArrayList<Integer> tituloPropiedades = new ArrayList<Integer>();
    private boolean penalizacion;
    private HashMap<Integer, Integer> controlPropiedaesCompradas = new HashMap<>();
    static HashMap<Integer, Integer> Filmina = new HashMap<>();
    private boolean turno;
    JLabel lbNumerojug;
    private int Numerojug;
    static int totalPlayers = 0;


    private int PosicionDeCasilleroActual = 0;
    private ArrayList<Integer> titleDeeds = new ArrayList<Integer>();
    private int dinero = 1500; // Dinero inicial

    public ArrayList<Integer> getTitleDeeds() {
        return titleDeeds;
    }

    public int getdinero() {
        return dinero;
    }

    public void Retirardinero(int Retirarmonto) {
        if(Retirarmonto > dinero) {
            setVisible(false);
            System.out.println("Player "+ Numerojug + "esta en banca rota!");
        } else {
            dinero -= Retirarmonto;
        }
    }

    public void depositardinero(int depositarmonto) {
        dinero += depositarmonto;
        System.out.println("dia de pago del jugador "+ getNumerojug()+". ganaste $200!");
    }

    public int getPosicionDeCasilleroActual() {
        return PosicionDeCasilleroActual;
    }

    public int getNumerojug() {
        return Numerojug;
    }

    public boolean hasTitleDeed(int Numerocuadrante) {
        return titleDeeds.contains(Numerocuadrante) ? true : false;
    }

    public void buyTitleDeed(int squareNumber) {
        if(Filmina.containsKey(squareNumber)) {
            System.out.println("ya esta comprado");
        } else {
            titleDeeds.add(this.getPosicionDeCasilleroActual());
            Filmina.put(squareNumber, this.getNumerojug()); // siempre que un jugador compra un title deed, se escribe en Filmina, por ejemplo cuadrante 1 pertenece a  player 2

        }
    }

    public Ficha(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 20, 20);
        this.setLayout(null);
    }

    public Ficha(int Numerojug, Color color) {
        this.Numerojug = Numerojug;
        this.setBackground(color);
        lbNumerojug = new JLabel(""+ Numerojug);
        lbNumerojug.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lbNumerojug.setForeground(Color.WHITE);
        this.add(lbNumerojug);
        this.setBounds(Numerojug *30, 33, 20, 28); // need to fix here for adjustable player numbers
        totalPlayers++;
    }


    public ArrayList<Integer> getTituloPropiedades() {
        return tituloPropiedades;
    }

    public int getDinero() {
        return dinero;
    }

    public void retirarDinero(int montoARetirar) {
        if(montoARetirar > dinero) {
            System.out.println("Jugador " + getNumerojug() + " está en bancarrota");
        }

            dinero -= montoARetirar;

    }

    public void depositarDinero(int montoADepositar) {
        dinero += montoADepositar;
        System.out.println("El jugador " + getNumerojug() + "recibió $" + montoADepositar);
    }



    public boolean conTituloDePropiedades(int numeroCasilla) {
        return tituloPropiedades.contains(numeroCasilla) ? true : false;
    }

    public void PagarDeuda(int deuda) {
        if(deuda > dinero) {
            setVisible(false);
            System.out.println("El Jugador "+ getNumerojug() + " Quedo en banca rota!");
        } else {
            dinero -= deuda;
        }
    }

    public void comprarTituloDePropiedad(int numeroCasilla) {
        if (controlPropiedaesCompradas.containsKey(numeroCasilla)) {
            System.out.println("Esta propiedad ya esta comprada");
        } else {
            tituloPropiedades.add(this.getPosicionDeCasilleroActual());
            controlPropiedaesCompradas.put(numeroCasilla, this.getNumerojug());
        }
    }

    public boolean getPenalizacion() {
        return penalizacion;
    }
    public void setPenalizacion(boolean penalizacion) {
        this.penalizacion=penalizacion;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    int[] jugadorx01 = {31, 131, 231, 331, 431, 531,
            531, 531, 531, 531, 531,
            431, 331, 231, 131, 31,
            31, 31, 31, 31};

    int[] jugadory01 = {33, 33, 33, 33, 33, 33,
            133, 233, 333, 433, 533,
            533, 533, 533, 533, 533,
            433, 333, 233, 133};

    int[] jugadorx02 = {61, 191, 291, 361, 461, 561,
            561, 561, 561, 561, 561,
            461, 361, 261, 161, 61,
            61, 61, 61, 61};

    int[] jugadory02 = {33, 33, 33, 33, 33, 33,
            133, 233, 333, 433, 533,
            533, 533, 533, 533, 533,
            433, 333, 233, 133};

    public void moverse(int dicesTotal)
    {
        if (PosicionDeCasilleroActual + dicesTotal > 19) {
            depositarDinero(200);
        }
            int objetivo = (PosicionDeCasilleroActual + dicesTotal) % 20;
            PosicionDeCasilleroActual= objetivo;


            if (Memepolimein.juegoON == 0) {

                this.setLocation(jugadorx01[objetivo], jugadory01[objetivo]);


            } else {

                this.setLocation(jugadorx02[objetivo], jugadory02[objetivo]);

            }
        }

    public void pagarCarcel(Ficha ficha)
    {
        retirarDinero(100);
        setPenalizacion(true);
    }

    public void generarFortuna() {
        int azar = (int) ((Math.random() * ((10 - 1) + 1)) + 1);
        if (azar >= 1 && azar < 3) {
            retirarDinero(200);
        } else if(azar >= 3 && azar < 6) {
            depositarDinero(700);
        } else {
            retirarDinero(600);
        }
    }

}
