/*
    Laboratorio No. 3 - Recursive Descent Parsing
    CC4 - Compiladores

    Clase que representa el parser

    Actualizado: agosto de 2021, Luis Cu
*/

import java.util.LinkedList;
import java.util.Stack;

public class Parser {

    // Puntero next que apunta al siguiente Token
    private int next;
    // Stacks para evaluar en el momento
    private Stack<Double> operandos;
    private Stack<Token> operadores;
    // LinkedList de Tokens
    private LinkedList<Token> Tokens;

    // Funcion que manda a llamar main para parsear la expresion
    public boolean parse(LinkedList<Token> Tokens) {
        this.Tokens = Tokens;
        this.next = 0;
        this.operandos = new Stack<Double>();
        this.operadores = new Stack<Token>();

        // Recursive Descent Parser
        // Imprime si el input fue aceptado
        System.out.println("Aceptada? " + S());

        // Shunting Yard Algorithm
        // Imprime el resultado de operar el input
        // System.out.println("Resultado: " + this.operandos.peek());

        // Verifica si terminamos de consumir el input
        if(this.next != this.Tokens.size()) {
            return false;
        }
        return true;
    }

    // Verifica que el id sea igual que el id del Token al que apunta next
    // Si si avanza el puntero es decir lo consume.
    private boolean term(int id) {
        if(this.next < this.Tokens.size() && this.Tokens.get(this.next).equals(id)) {
            
            // Codigo para el Shunting Yard Algorithm
            /*
            if (id == Token.NUMBER) {
				// Encontramos un numero
				// Debemos guardarlo en el stack de operandos
				operandos.push( this.Tokens.get(this.next).getVal() );

			} else if (id == Token.SEMI) {
				// Encontramos un punto y coma
				// Debemos operar todo lo que quedo pendiente
				while (!this.operadores.empty()) {
					popOp();
				}
				
			} else {
				// Encontramos algun otro Token, es decir un operador
				// Lo guardamos en el stack de operadores
				// Que pushOp haga el trabajo, no quiero hacerlo yo aqui
				pushOp( this.Tokens.get(this.next) );
			}
			*/

            this.next++;
            return true;
        }
        return false;
    }

    // Funcion que verifica la precedencia de un operador
    private int pre(Token op) {
        /* TODO: Su codigo aqui */

        /* El codigo de esta seccion se explicara en clase */

        switch(op.getId()) {
        	case Token.PLUS:
        		return 1;
        	case Token.MULT:
        		return 2;
        	default:
        		return -1;
        }
    }

    private void popOp() {
        Token op = this.operadores.pop();

        /* TODO: Su codigo aqui */

        /* El codigo de esta seccion se explicara en clase */

        if (op.equals(Token.PLUS)) {
        	double a = this.operandos.pop();
        	double b = this.operandos.pop();
        	// print para debug, quitarlo al terminar
        	System.out.println("suma " + a + " + " + b);
        	this.operandos.push(a + b);
        } else if (op.equals(Token.MULT)) {
        	double a = this.operandos.pop();
        	double b = this.operandos.pop();
        	// print para debug, quitarlo al terminar
        	System.out.println("mult " + a + " * " + b);
        	this.operandos.push(a * b);
        }
    }

    private void pushOp(Token op) {
        /* TODO: Su codigo aqui */

        /* Casi todo el codigo para esta seccion se vera en clase */
    	
    	// Si no hay operandos automaticamente ingresamos op al stack

    	// Si si hay operandos:
    		// Obtenemos la precedencia de op
        	// Obtenemos la precedencia de quien ya estaba en el stack
        	// Comparamos las precedencias y decidimos si hay que operar
        	// Es posible que necesitemos un ciclo aqui, una vez tengamos varios niveles de precedencia
        	// Al terminar operaciones pendientes, guardamos op en stack

    }

    private boolean S() {
        return E() && term(Token.SEMI);
    }

    /* TODO: sus otras funciones aqui */

    private boolean E1() {
        return G();
    }

    private boolean E2() {
        return term(Token.NUMBER) && F();
    }

    private boolean G1() {
        return term(Token.UNARY) && E();
    }

    private boolean G2() {
        return term(Token.LPAREN) && E() && term(Token.RPAREN);
    }

    private boolean F1() {
        return term(Token.PLUS) && E();
    }

    private boolean F2() {
        return term(Token.MINUS) && E();
    }
    
    private boolean F3() {
        return term(Token.MULT) && E();
    }
    
    private boolean F4() {
        return term(Token.DIV) && E();
    }
    
    private boolean F5() {
        return term(Token.MOD) && E();
    }
    
    private boolean F6() {
        return term(Token.EXP) && E();
    }
    
    private boolean F7() {
        return true;
    }
    
    private boolean E() { 
        int save = this.next;

        this.next = save;
        if ( E1() ) { return true;}

        this.next = save;
        if ( E2() ) { return true;}

        return false;
    }

    private boolean G() { 
        int save = this.next;

        this.next = save;
        if ( G1() ) { return true;}

        this.next = save;
        if ( G2() ) { return true;}

        return false;
    }

    private boolean F() { 
        int save = this.next;

        this.next = save;
        if ( F1() ) { return true;}

        this.next = save;
        if ( F2() ) { return true;}

        this.next = save;
        if ( F3() ) { return true;}

        this.next = save;
        if ( F4() ) { return true;}

        this.next = save;
        if ( F5() ) { return true;}

        this.next = save;
        if ( F6() ) { return true;}

        this.next = save;
        if ( F7() ) { return true;}

        return false;
    }
}