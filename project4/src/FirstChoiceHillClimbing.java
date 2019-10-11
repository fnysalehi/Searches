import java.util.ArrayList;

public class FirstChoiceHillClimbing {

    Problem1 problem;
    State initState;
    State curState;
    int extendedNode=0,nodes=0;

    public FirstChoiceHillClimbing(){
        this.problem = new Problem1();
        this.initState = this.problem.get_random_state();
        this.curState = this.initState;
        System.out.println(initState.name);
        System.out.println(problem.get_cost_of_state(initState));
        while(true){
        	extendedNode++;
            ArrayList<State> neighbors = problem.get_possible_states(this.curState);

            State nextState = null;
            for(State neighbor :neighbors) {
            	nodes++;
                if (problem.get_cost_of_state(neighbor) < problem.get_cost_of_state(curState)) {
                    nextState = neighbor;
                    break;
                }
            }
            if(nextState==null)
                break;
            curState = nextState;
        }
        System.out.println("========= ");
        System.out.println(curState.name);
        System.out.println(problem.get_cost_of_state(curState));
        System.out.println("extended nodes "+extendedNode);
        System.out.println("checked nodes "+nodes);
    }

    public static void main(String[] args) {
        new FirstChoiceHillClimbing();
    }

}
