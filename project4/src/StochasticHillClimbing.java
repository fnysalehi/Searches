import java.util.ArrayList;

public class StochasticHillClimbing {

    Problem1 problem;
    State initState;
    State curState;
    int extendedNode=0,nodes=0;

    public StochasticHillClimbing(){
        this.problem = new Problem1();
        this.initState = this.problem.get_random_state();
        this.curState = this.initState;
        System.out.println(initState.name);
        System.out.println(problem.get_cost_of_state(initState));
        while(true){
        	extendedNode++;
            ArrayList<State> neighbors = problem.get_possible_states(this.curState);
            ArrayList<State> betters = new ArrayList<State>();

            for(State neighbor :neighbors) {
            	nodes++;
                if (problem.get_cost_of_state(neighbor) < problem.get_cost_of_state(curState)) {
                    betters.add(neighbor);
                }
            }
            if (betters.size()==0)
                break;

            curState = betters.get((int)(Math.random()* betters.size()));
        }
        System.out.println("========= ");
        System.out.println(curState.name);
        System.out.println(problem.get_cost_of_state(curState));
        System.out.println("extended nodes "+extendedNode);
        System.out.println("checked nodes "+nodes);
    }

    public static void main(String[] args) {
        new StochasticHillClimbing();
    }

}
