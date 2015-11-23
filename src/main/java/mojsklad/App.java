package mojsklad;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {

            class Edge {
                int from;
                int to;
                Edge(int from, int to) {
                    this.from = from;
                    this.to = to;
                }
            }
            LinkedList<Edge> edges = new LinkedList<>();
            String currentLine;
            int numberOfVertexes=0;
            while ((currentLine = br.readLine()) != null) {
                String[] temp = currentLine.split(" ");
                int from=Integer.parseInt(temp[0]);
                int to= Integer.parseInt(temp[1]);
                if(numberOfVertexes<Math.max(from,to)+1){
                    numberOfVertexes=Math.max(from,to)+1;
                }
                edges.add(new Edge(from,to));

            }
            Digraph g = new Digraph(numberOfVertexes);
            for (Edge it : edges) {
                g.addEdge(it.from, it.to);
            }
            Cycles finder = new Cycles(g);
            while (finder.hasCycle()) {
                Iterator<Integer> it = finder.cycle().iterator();
                int bufV = it.next();
                int bufW = it.next();
                for (int v : finder.cycle()) {
                    bw.write(v + " ");
                }
                bw.newLine();
                g.deleteEdge(bufW, bufV);
                finder.refreshGraph(g);

            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
