package br.com.printer;


import br.com.jl.document.JLDocumentReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class JLPrinter implements Printable {
    private PrinterJob job = PrinterJob.getPrinterJob();
    private PageFormat pf = job.defaultPage();
    private Paper paper = new Paper();
    private String text;

    private double width = 0;
    private double height = 0;

    public JLPrinter(String text) {
        this.text = text;
        init();
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        JLDocumentReader documentReader = new JLDocumentReader(new Dimension((int) pf.getImageableWidth(), (int) pf.getImageableHeight()), text);

        documentReader.render(g2d);

        return PAGE_EXISTS;
    }

    public void MMToPoints(double width, double height) {
        this.width = width * 72 / 25.4; // Converter mm para pontos (1 polegada = 72 pontos)
        this.height = height * 72 / 25.4; // Converter mm para pontos
    }

    public void init() {
        paper.setSize(width, height);
        paper.setImageableArea(0, 0, width, height); // Área imprimível
        pf.setPaper(paper);
        job.setPrintable(this, pf);
    }

    void show(){
        try {
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            System.err.println("Uso: JLPrinter <largura em MM> <altura em MM> <local do arquivo>");
            System.exit(1);
        }

        int largura = Integer.parseInt(args[0]);
        int altura = Integer.parseInt(args[1]);
        String arquivo = args[2];

        Path path = Paths.get(arquivo);

        if (!Files.isRegularFile(path)) {
            System.err.println("Erro: O caminho especificado não é um arquivo regular.");
            return;
        }

        // Verifica se o arquivo possui a extensão .txt (por exemplo)
        if (!arquivo.toLowerCase().endsWith(".jl")) {
            System.err.println("Erro: O arquivo não possui a extensão .jl");
            return;
        }

        JLPrinter jlPrinter = new JLPrinter(new String(Files.readAllBytes(path)));
        jlPrinter.MMToPoints(largura, altura);
        jlPrinter.init();
        jlPrinter.show();
    }
}
