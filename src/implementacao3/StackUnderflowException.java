/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao3;

/**
 *
 * @author Adson Macêdo
 */
public class StackUnderflowException extends Exception{

    public StackUnderflowException() {
        super("Pilha vazia");
    }
    
}
