import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** Grafico da probabilidade de o servico funcionar para cada valor de k. */
public class Graph extends JPanel {

    private static final int ESQUERDA = 80;
    private static final int DIREITA = 40;
    private static final int TOPO = 55;
    private static final int BASE = 120;

    private final int n;
    private final int kInformado;
    private final double p;

    public Graph(int n, int kInformado, double p) {
        this.n = n;
        this.kInformado = kInformado;
        this.p = p;
        setPreferredSize(new Dimension(800, 570));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int direita = getWidth() - DIREITA;
        int base = getHeight() - BASE;
        int largura = direita - ESQUERDA;
        int altura = base - TOPO;

        desenharEixos(g, direita, base, largura, altura);
        desenharCurva(g, base, largura, altura);
        desenharFormula(g, base);
        g.dispose();
    }

    private void desenharEixos(Graphics2D g, int direita, int base, int largura, int altura) {
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2f));
        g.drawLine(ESQUERDA, base, direita, base);
        g.drawLine(ESQUERDA, base, ESQUERDA, TOPO);

        for (int i = 0; i <= 10; i++) {
            int x = ESQUERDA + i * largura / 10;
            int y = base - i * altura / 10;
            int valorK = i * n / 10;
            double probabilidade = i / 10.0;

            g.drawLine(x, base - 4, x, base + 4);
            g.drawLine(ESQUERDA - 4, y, ESQUERDA + 4, y);
            g.drawString(String.valueOf(valorK), x - 8, base + 22);
            g.drawString(String.format("%.1f", probabilidade), ESQUERDA - 38, y + 5);
        }

        g.drawString("k: minimo de servidores funcionando", direita - 205, base + 50);
        g.drawString("P(servico funcionar)", 12, TOPO - 12);
        g.drawString("Probabilidade do servico funcionar (n=" + n + ", p=" + p + ")", ESQUERDA + 190, 28);
    }

    private void desenharCurva(Graphics2D g, int base, int largura, int altura) {
        Path2D curva = new Path2D.Double();
        Path2D area = new Path2D.Double();

        for (int k = 0; k <= n; k++) {
            int x = ESQUERDA + (int) java.lang.Math.round((double) k / n * largura);
            double probabilidade = Formula.probabilidadeServico(n, k, p);
            int y = base - (int) java.lang.Math.round(probabilidade * altura);

            if (k == 0) {
                curva.moveTo(x, y);
                area.moveTo(x, base);
                area.lineTo(x, y);
            } else {
                curva.lineTo(x, y);
                area.lineTo(x, y);
            }
        }

        area.lineTo(ESQUERDA + largura, base);
        area.closePath();
        g.setColor(new Color(219, 237, 255));
        g.fill(area);

        g.setColor(new Color(25, 94, 170));
        g.setStroke(new BasicStroke(3f));
        g.draw(curva);

        int xInformado = ESQUERDA + (int) java.lang.Math.round((double) kInformado / n * largura);
        double resultado = Formula.probabilidadeServico(n, kInformado, p);
        int yInformado = base - (int) java.lang.Math.round(resultado * altura);
        g.setColor(new Color(190, 45, 45));
        g.fillOval(xInformado - 6, yInformado - 6, 12, 12);
        g.drawString(String.format("k=%d: %.6f", kInformado, resultado), xInformado + 10, yInformado - 10);
    }

    private void desenharFormula(Graphics2D g, int base) {
        int y = base + 82;
        g.setColor(Color.DARK_GRAY);
        g.drawString("P(servico funcionar) = P(X >= k) = soma de P(X = i), para i de k ate n", ESQUERDA, y);
        g.drawString("P(X = i) = C(n, i) * p^i * (1 - p)^(n - i)", ESQUERDA, y + 24);
    }

    public static void exibir(int n, int k, double p) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Probabilidade do servico");
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setContentPane(new Graph(n, k, p));
            janela.pack();
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
