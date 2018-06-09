/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao4;

import java.util.ArrayList;

/**
 *
 * @author Adson Macêdo
 */
public class Grafo {
    int n;
    ArrayList<Integer> [] adj;
    
    public Grafo(int n){
        this.n = n;
        this.adj = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }
    
    public void criaAdj(int a, int b){
        adj[a].add(b);
        adj[b].add(a);
    }
    
    public static void main(String[] args) {
        Grafo g = new Grafo(10);
        
        g.criaAdj(1, 1);
    }
}
