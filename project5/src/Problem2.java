import java.util.Comparator;
import java.util.Scanner;

public class Problem2 {

    private int bitsNumber = -1;
    private int itemSize = -1;
    private int[] itemWeights = null;
    private int[] itemValues = null;
    private int backPackCapacity = -1;
    private int maxItemCount = -1;

    public int getItemSize() {
        return itemSize;
    }
    public int getBitsNumber() {
        return bitsNumber;
    }

    public Problem2() {
        System.out.println("item count ?");
        Scanner in = new Scanner(System.in);
        int input_size = in.nextInt();
        this.itemSize = input_size;
        this.itemWeights = new int[input_size];
        this.itemValues = new int[input_size];
        int minItemWeigh = Integer.MAX_VALUE;
        for (int i = 0; i < this.itemSize; i++) {
            this.itemWeights[i] = in.nextInt();
            minItemWeigh = Math.min(this.itemWeights[i], minItemWeigh);
        }
        for (int i = 0; i < this.itemSize; i++) {
            this.itemValues[i] = in.nextInt();
        }
        input_size = in.nextInt();
        this.backPackCapacity = input_size;

        this.maxItemCount = (backPackCapacity / minItemWeigh);
        this.bitsNumber = Integer.toBinaryString(maxItemCount).length();
//        System.out.println("=========");
//        System.out.println(this.itemSize);
//        System.out.println(this.itemWeights);
//        System.out.println(this.itemValues);
//        System.out.println(this.backPackCapacity);
//        System.out.println(this.maxItemCount);
//        System.out.println(this.bitsNumber);
    }

    public boolean isValidState(State s) {
        int itemIndex = 0;
        int bag_cap = new Integer(this.backPackCapacity);
        for (int index = 0; index < s.name.length(); index += bitsNumber){
            String binary = s.name.substring(index, index+bitsNumber);
            int itemCount = Integer.parseInt(binary, 2);
            bag_cap -= itemCount * this.itemWeights[itemIndex];

            itemIndex +=1;
        }

        return bag_cap >= 0 ;
    }

    public int calc_state_value(State s){
        int itemIndex = 0;
        int value = 0;
        for (int index = 0; index < s.name.length(); index += bitsNumber){
            String binary = s.name.substring(index, index+bitsNumber);
            int itemCount = Integer.parseInt(binary, 2);
            value += itemCount * this.itemValues[itemIndex];

            itemIndex +=1;
        }
        return value;
    }

    public State get_random_state(){
        boolean isValidState = false;
        String res = "";
        while (!isValidState){
            res = "";
            for(int i=0;i<this.itemSize*this.bitsNumber;i++){
                if(Math.random() > 0.5f)
                    res += '1';
                else
                    res += '0';
            }
            isValidState = isValidState(new State(res));
        }
        return new State(res);
    }

    Comparator<State> stateComparator = new Comparator<State>() {
        @Override
        public int compare(State o1, State o2) {
            int val1 = -1;
            int val2 = -1;
            if(isValidState(o1))
                val1 = calc_state_value(o1);
            if(isValidState(o2))
                val2 = calc_state_value(o2);

            return val2 - val1;
        }
    };

}

