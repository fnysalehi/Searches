import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {

    private int populationSize = 20;
    private ArrayList<State> population = null;
    private Problem2 problem;
    private float mutationP = 0f;
    private int itrate = 10000;
    private int crossoverPointNumber = 1;
    int nasl=0;

    public GeneticAlgorithm(){
        this.problem = new Problem2();
        this.population = createFirstPopulation();
        this.mutationP = 1f/problem.getItemSize();
        while(itrate>0){
        	nasl++;
            ArrayList<State> newPopulation = new ArrayList<State>();
            while(population.size()>0){
                State parentA = population.remove((int)(Math.random()*population.size()));
                State parentB = population.remove((int)(Math.random()*population.size()));

                ArrayList<Integer> crossOverIndexes = new ArrayList<Integer>();
                while(crossOverIndexes.size()<this.crossoverPointNumber){
                    int randIndex = (int)(Math.random() * (problem.getItemSize() * problem.getBitsNumber()));
                    while(crossOverIndexes.contains(randIndex))
                        randIndex = (int)(Math.random() * (problem.getItemSize() * problem.getBitsNumber()));
                    crossOverIndexes.add(randIndex);
                }
                Collections.sort(crossOverIndexes);

                boolean crossToggle = false ;
                String chileAString = "";
                String chileBString = "";
                for(int i=0;i<this.problem.getBitsNumber()*this.problem.getItemSize();i++){
                    if(crossOverIndexes.size()>0)
                        if(i==crossOverIndexes.get(0)){
                            crossOverIndexes.remove(0);
                            crossToggle = !crossToggle;
                        }
                    if(crossToggle){
                        chileAString += parentA.name.charAt(i);
                        chileBString += parentB.name.charAt(i);
                    }else{
                        chileAString += parentB.name.charAt(i);
                        chileBString += parentA.name.charAt(i);
                    }
                }
                StringBuilder childABuilder = new StringBuilder(chileAString);
                StringBuilder childBBuilder = new StringBuilder(chileBString);
                for(int i=0;i<this.problem.getBitsNumber()*this.problem.getItemSize();i++){

                    if(Math.random()<mutationP){
                        if(chileAString.charAt(i)=='1'){
                            childABuilder.setCharAt(i,'0');
                        }else{
                            childABuilder.setCharAt(i,'1');
                        }
                    }

                    if(Math.random()<mutationP){
                        if(chileBString.charAt(i)=='1'){
                            childBBuilder.setCharAt(i,'0');
                        }else{
                            childBBuilder.setCharAt(i,'1');
                        }
                    }

                }
                newPopulation.add(parentA);
                newPopulation.add(parentB);
                newPopulation.add(new State(childABuilder.toString()));
                newPopulation.add(new State(childBBuilder.toString()));

            }

            Collections.sort(newPopulation, problem.stateComparator);
            //System.out.println("---------------");
            //System.out.println(this.problem.calc_state_value(newPopulation.get(0)));
            //System.out.println(this.problem.calc_state_value(newPopulation.get((newPopulation.size()-1)/2)));
            //System.out.println(this.problem.calc_state_value(newPopulation.get(newPopulation.size()-1)));
            //System.out.println("---------------");

            this.problem.calc_state_value(newPopulation.get(0));				
            population = new ArrayList<State>(newPopulation.subList(0,populationSize));

            itrate -=1;
        }
        //System.out.println("---------------");
        System.out.println(problem.calc_state_value(population.get(0)));
        System.out.println("Generation number "+nasl);
        //System.out.println("---------------");

    }

    public ArrayList<State> createFirstPopulation(){
        ArrayList<State> res = new ArrayList<State>();
        for(int i = 0;i<this.populationSize;i++)
            res.add(problem.get_random_state());
        return res;
    }


    public static void main(String[] args) {
        new GeneticAlgorithm();
    }
}
