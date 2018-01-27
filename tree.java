

//tree has 2 subclasses branch and leaf
abstract class tree {
	//get a possible tree
	public static byte[] get_initial_tree(int[][] MT_penalties, int[][] MT_too_near) {
		byte[] MT_pairs = {1, 2, 3, 4, 5, 6, 7, 8};								///array[machine # -1] = task #)
		byte[] MT_sequence = {1, 1, 1, 1, 1, 1, 1, 1};
 		while (get_cost(MT_pairs, MT_penalties, MT_too_near) == -1) {		//-1 indicates a forbidden pairing
			MT_sequence = increment_sequence(MT_sequence, 0);					//get next set of machine task pairs to try as a solution
			MT_pairs = get_pairs(MT_sequence);									//format pairs into array with array[machine # -1] = task #)
		}
		return MT_pairs;
	}
	
	//finds next sequence array to be attempted as a solution (returns array where array[machine # -1] = task numbered from list 1 through (9-machine #))
	//*********************should throw no_valid_assignments_exception if all possible pairings have been tried (not implemented, currently throws arrayIndexOutOfBoudns error)*********************
	private static byte[] increment_sequence(byte[] MT_sequence, int array_position) {		//WORKING as expected all cases tested
		/*if (array_position == 7) {
			throw no_valid_assignments_exception;
		}*/
		if (MT_sequence[array_position] == 8-array_position) {
			MT_sequence[array_position] = 1;
			MT_sequence = increment_sequence(MT_sequence, array_position + 1);
		}
		else {
			MT_sequence[array_position] += 1;
		}
		return MT_sequence;
	}
	
	//takes the sequence array and gives back the paired task for each machine (returns array where array[machine # -1] = task #)
	private static byte[] get_pairs(byte[] MT_sequence) {
		byte[] MT_pairs = new byte[8];
		byte[][] remaining_tasks = new byte[8][8];
		remaining_tasks[0] = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
		MT_pairs[0] = MT_sequence[0];
		byte next_task_index = -1;
		for (int i=1; i<8; i++) {					//represents index of remaining tasks to work with
			for (byte k=0; k<8; k++) {				//next_task_index = remaining_tasks[i].indexOf(MT_pairs[i-1]);
				if (remaining_tasks[i-1][k] == MT_pairs[i-1]) {
					next_task_index = k;			//index of task already paired
					break;
				}
			}
// System.out.print(next_task_index);
			for (int j=0; j<9-i; j++) {				//index of remaining_tasks[i]
				if (j < next_task_index) {			//add the corresponding entry in the previous array
					remaining_tasks[i][j] = remaining_tasks[i-1][j];		//place j term
				}
				if (j > next_task_index) {			//add the entry previous to the corresponding one in the previous array
					remaining_tasks[i][j-1] = remaining_tasks[i-1][j];	//place j-1 term
				}
			}
// System.out.print("\n");
// for (int j=0; j<8; j++) {System.out.print(remaining_tasks[i][j]);}
			MT_pairs[i] = remaining_tasks[i][MT_sequence[i]-1];
		}
// System.out.print("\nPairs:");
// for (int i=0; i<8; i++) {System.out.print(MT_pairs[i]);}
// System.out.print("\n");
		return MT_pairs;
	}

	//returns total cost of current pairing given cost per machine for task and too near task list (each should hold value -1 to indicate forbidden)
	private static int get_cost(byte[] MT_pairs, int[][] MT_penalties, int[][] MT_too_near) {
		int cost = 0;
		for (int i=0; i<8; i++) {
			cost += MT_penalties[i][MT_pairs[i]-1];
			if (MT_penalties[i][MT_pairs[i]-1] == -1) {
				cost = -1;
				break;
			}
		}
		if (MT_too_near.length > 0) {
			byte task1 = -1;
			byte task2 = -1;
			for (int i=0; i<MT_too_near.length; i++) {		//iterate through the too_near array
				if (cost == -1) {
					break;
				}
				for (byte j=0; j<8; j++) {					//find the machine corresponding to the first entry of the too_close array
					if (MT_pairs[j] == MT_too_near[i][0]) {
						task1 = j;
						break;
					}
				}
				for (byte j=0; j<8; j++) {					//find the machine corresponding to the second entry of the too_close array
					if (MT_pairs[j] == MT_too_near[i][1]) {
						task2 = j;
						break;
					}
				}
				if (task1-task2 == 1 || task1-task2 == -1 || (task1 == 1 && task2 == 8)) {
					cost += MT_too_near[i][2];
					if (MT_too_near[i][2] == -1) {
						cost = -1;
					}
				}
			}
		}
		return cost;
	}


	int supertree_cost;
	byte machine_task;
	Boolean closed = false;	
}
	
class branch extends tree {
	int best_subtree_cost;
	tree[] subtrees = new tree[8];
	byte used_tasks;				//tasks used in supertree or in this branch
}
class leaf extends tree {
	int total_cost;					//supertree cost + cost of machine 8's task
}

/*
used_tasks
	should have 1 at positions #s where machines not in the subtree have that task #
	should have 0 at positions #s where machines in the subtree have that task #
	the total number of 1's gives the machine #
	
machine_task
	should have a 1 at the task number -1 th position
	number_ones(used_tasks), index_of_1(machine_task) + 1 = this node's Machine, Task pair

use trial and error to find an initial tree
	use 7 branches and 1 leaf to form the tree
*/

