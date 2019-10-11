import java.util.ArrayList;

public class RandomRestartHillClimbing {

    Problem1 problem;
    State initState;
    State curState;
    State bestState;
    int restart_count = 20,extendedNode=0,nodes=0;

    public RandomRestartHillClimbing(){
        this.problem = new Problem1();
        this.initState = this.problem.get_random_state();
        this.curState = this.initState;
        this.bestState = this.initState;
        System.out.println(initState.name);
        System.out.println(problem.get_cost_of_state(initState));
        while(restart_count>0){
        	extendedNode++;
            ArrayList<State> neighbors = problem.get_possible_states(this.curState);
            State nextState = null;
            int nextCost = Integer.MAX_VALUE;
            for(State neighbor :neighbors) {
            	nodes++;
                if (problem.get_cost_of_state(neighbor) < nextCost) {
                    nextCost = problem.get_cost_of_state(neighbor);
                    nextState = neighbor;
                }
            }
            if (problem.get_cost_of_state(curState)< nextCost)
                restart_count -= 1 ;
                nextState = problem.get_random_state();

            curState = nextState;
            if (problem.get_cost_of_state(curState) < problem.get_cost_of_state(bestState)) {
                bestState = curState;
            }
        }
        System.out.println("========= ");
        System.out.println(bestState.name);
        System.out.println(problem.get_cost_of_state(bestState));
        System.out.println("extended nodes "+extendedNode);
        System.out.println("checked nodes "+nodes);
    }

    public static void main(String[] args) {
        new RandomRestartHillClimbing();
    }

}
