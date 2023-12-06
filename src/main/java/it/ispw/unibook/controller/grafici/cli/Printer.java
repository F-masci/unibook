package it.ispw.unibook.controller.grafici.cli;

public class Printer {

    private Printer() {}

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void println(String msg) {
        System.out.println(msg);
    }
    public static void error(String msg) {
        System.err.println(msg);
    }

}
