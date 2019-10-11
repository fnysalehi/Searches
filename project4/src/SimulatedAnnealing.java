public class SimulatedAnnealing {

    Problem1 problem;
    State initState;
    State curState;
    State bestState;
    double temp = 2;
    double coolingRate = 0.001;
    int extendedNode=0,nodes=0;

    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    public SimulatedAnnealing(){
        this.problem = new Problem1();
        this.initState = this.problem.get_random_state();
        this.curState = this.initState;
        this.bestState = this.initState;

        System.out.println(initState.name);
        System.out.println(problem.get_cost_of_state(initState));
        //int k = 0;
        while (temp>1){
        	extendedNode++;
            State neighbour = problem.get_random_nears(this.curState);
            nodes++;
            if (acceptanceProbability(problem.get_cost_of_state(curState), problem.get_cost_of_state(neighbour), temp) > Math.random()) {
                curState = neighbour;
            }
            if (problem.get_cost_of_state(curState) < problem.get_cost_of_state(bestState)) {
                bestState = curState;
            }

            // Cool system
            temp *= 1-coolingRate;
            //k++;
        }
        System.out.println("========= ");
        System.out.println(bestState.name);
        System.out.println(problem.get_cost_of_state(bestState));
        System.out.println("extended nodes "+extendedNode);
        System.out.println("checked nodes "+nodes);
    }

    public static void main(String[] args) {
        new SimulatedAnnealing();
    }

}
