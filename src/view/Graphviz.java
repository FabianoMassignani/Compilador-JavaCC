package view;
import java.io.IOException;


public class Graphviz {

    public String s;

    public void runGraphviz(String arvoreString) throws IOException {
        String pathPython = "C:\\Users\\fabia\\eclipse-workspace\\Trabalho\\src\\view\\run.py";

        String[] cmd = new String[3]; 
        cmd[0] = "python";
        cmd[1] = pathPython;
        cmd[2] = arvoreString; 
        Runtime r = Runtime.getRuntime();
        r.exec(cmd);
    }

    public static void main(String[] args) {
    }
}
