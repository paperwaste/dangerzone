package beng.jumperB;

public class GameTree {
	 private GameState[][] currentState;
	 // Opponent si not necessarily predictable
	 // Search is time limited and space limited
	 
	 //Plan of attack
	 //If cannot see to end state
	 /*
	 if(perfectPlay()!=true){
		  horizonApprox();
		  pruneTree();
	  }
	  */
	  /*
	   * Initial state
	   * Operators
	   * Terminal test (outcome)
	   * Utility function
	   * 
	   * EX: Minimax game (zero-sum application)
	   * Make best move ?independent of opponent actions?
	   * Perfect play for deterministic, perfect information games
	   * 
	   * Utility function applied to tree LEAVES
	   * score propagates up the tree based on expectation of the opponents best move to the 1st degree children, culminating in a choice
	   * 
	   * MINIMAX ALGORITHM:
	   * utility MAX-VALUE(state){
	   * 	if(TERMINAL-TEST(state)){
	   * 		return UTILITY(state);
	   * 	}
	   * 	v = -INFINTY;
	   * 	
	   * 
	   * 
	   * }
	   * 
	   * try to profile their strategy to modify the cost function
	   * 
	   * Data mining strategy
	   * 1. Determine branching and depth characteristics of the game
	   * 2. Caculate amount of data required
	   * 3. Determine feasability of implementing a machine learned utility function
	   * 
	   * DATA-MINING techniques relevant
	   * Association of position characteristics to outcomes
	   * 
	   * 
	   * 
	   */
	 
	 
}
