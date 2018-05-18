/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacao4;

import static java.lang.Math.*;


/**
 *
 * @author Adson Macêdo
 */
public class BST implements BST_IF{
    private int data;
    private BST previous;
    private BST left;
    private BST right;
    
    public BST(){
        this(-1, null);
    }
    
    private BST(int element, BST previous){
        this.data = element;
        this.previous = previous;
    }
    
   
    private int size(){
        if (this.data == -1)
            return 0;
        
        return 1 + left.size() + right.size();
    }
    
    @Override
    public void insert(int element) {
        if (this.data == -1){
            this.data = element;
            this.left = new BST(-1, this);
            this.right = new BST(-1, this);
            
            return;
        }
        
        if (element <= this.data) {
            left.insert(element);
        } else {
            right.insert(element);
        }
    }

    @Override
    public int search(int element) throws Exception {
        if (data == -1)
            throw new Exception("Não encontrado!");
        
        if (data == element) return data;
        
        if (element <= data)
            return left.search(element);
        
        return right.search(element);
        
    }

    private int pre(int [] array, int i){
        if (this.data == -1)
            return i;
        
        array[i++] = data;
        
        i = left.pre(array, i);
        i = right.pre(array, i);
        
        return i;
    }
    
    private int post(int [] array, int i){
        if (this.data == -1)
            return i;
        
        i = left.post(array, i);
        i = right.post(array, i);
        
        array[i++] = data;
        
        return i;
    }
    private int ord(int [] array, int i){
        if (this.data == -1)
            return i;
        
        i = left.ord(array, i);
        array[i++] = data;
        i = right.ord(array, i);
        
        return i;
    }

    @Override
    public int[] preOrder() {
        int [] array = new int[size()];
        pre(array, 0);
        
        return array;
    }

    @Override
    public int[] order() {
        int [] array = new int[size()];
        ord(array, 0);
        
        return array;
    }

    @Override
    public int[] postOrder() {
        int [] array = new int[size()];
        post(array, 0);
        
        return array;
    }

    private boolean checkHeight(int height, int atual){
        if (atual > height) return false;
        
        if (data == -1) return (height == atual);
        
        return left.checkHeight(height, atual + 1) && right.checkHeight(height, atual + 1);
    }
    
    @Override
    public boolean isComplete() {
        int size = size();  
        
        int height = (int) ceil(log(size + 1) / log(2)) - 1;
        
        if (pow(2, height + 1) - 1 != size) 
            return false;
        
        return checkHeight(height, -1);
    }
    
}
