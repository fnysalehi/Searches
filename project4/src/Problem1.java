import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    private String alphabet = "ABCDEFHIJOTYSXWRMLQVUPKNG";
    private int size = 0;
    private int[][] costs = null;

    public State initialState() {
        State s = new State(alphabet.substring(0, size));
        return s;
    }

    public Problem1() {
        System.out.println("city count ?");
        Scanner in = new Scanner(System.in);
        int input_size = in.nextInt();
        this.size = input_size;
        this.costs = new int[input_size][input_size];
        for (int index = 0; index < this.size; index++) {
            for (int i = 0; i < this.size; i++) {
                this.costs[index][i] = in.nextInt();
            }
        }
    }

    @Override
    public String toString() {
        String res = size + "\n";
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                res = res + " " + costs[i][j];
            }
            res = res + "\n";
        }
        return res;
    }


    public ArrayList<State> get_possible_states(State s) {

        ArrayList<State> res = new ArrayList<State>();
        String current = s.name;
        for (int i = 0; i < this.size; i++)
            for (int j = i + 1; j < this.size; j++) {
                StringBuilder next = new StringBuilder(s.name);
                next.setCharAt(i, current.charAt(j));
                next.setCharAt(j, current.charAt(i));
                res.add(new State(next.toString()));
            }
        return res;
    }

    public State get_random_nears(State s) {
        StringBuilder next = new StringBuilder(s.name);
        int i = (int) (Math.random() * this.size);
        int j = (int) (Math.random() * this.size);
        while (i == j) {
            j = (int) (Math.random() * this.size);
        }
        next.setCharAt(i, s.name.charAt(j));
        next.setCharAt(j, s.name.charAt(i));
        return new State(next.toString());
    }

    public State get_random_state() {
        String char_poll = alphabet.substring(0, size);
        String res = "";
        while (char_poll.length() > 0) {
            int rand_index = (int) (char_poll.length() * Math.random());
            char c = char_poll.charAt(rand_index);
            if (rand_index + 1 !=size){
                char_poll = char_poll.substring(0, rand_index) + char_poll.substring(rand_index+1);

            }else {
                char_poll = char_poll.substring(0, rand_index);
            }
            res += c;
        }
        return new State(res);
    }


    public int get_cost_of_state(State s) {
        int cost = 0;
//        System.out.println(s.name);
        for (int cur = 0; cur < size - 1; cur++) {
//            System.out.println(s.name.charAt(cur ));
//            System.out.println(s.name.charAt(cur + 1));
//            System.out.println(this.costs[this.alphabet.indexOf(s.name.charAt(cur))][this.alphabet.indexOf(s.name.charAt(cur + 1))]);
            cost += this.costs[this.alphabet.indexOf(s.name.charAt(cur))][this.alphabet.indexOf(s.name.charAt(cur + 1))];
        }
        return cost;
    }

}


