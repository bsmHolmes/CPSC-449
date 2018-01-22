

//tree has 2 subclasses branch and leaf
abstract class tree {
	//get a possible tree
	public static byte[] get_initial_tree(int[][] MT_forbidden, int[][] MT_too_near) {
		byte[] MT_pairs = {1, 2, 3, 4, 5, 6, 7, 8};								///array[machine # -1] = task #)
		byte[] MT_sequence = {1, 1, 1, 1, 1, 1, 1, 1};
		while (cost_of_pairs(MT_pairs, MT_forbidden, MT_too_near) == -1) {		//-1 indicates a forbidden pairing
			MT_sequence = increment_sequence(MT_sequence, 0);					//get next set of machine task pairs to try as a solution
			MT_pairs = get_pairs(MT_sequence);									//format pairs into array with array[machine # -1] = task #)
		}
		return MT_pairs;
	}
	
	//finds next sequence array to be attempted as a solution (returns array where array[machine # -1] = task numbered from list 1 through (9-machine #))
	//*********************should throw no_valid_assignments_exception if all possible pairings have been tried (not implemented)*********************
	private static byte[] increment_sequence(byte[] MT_sequence, int start_position) {
		/*if (start_position == 7) {
			throw no_valid_assignments_exception;
		}*/
		if (MT_sequence[start_position] == 8-start_position) {
			MT_sequence[start_position] = 1;
			MT_sequence = increment_sequence(MT_sequence, start_position + 1);
		}
		else {
			MT_sequence[start_position] += 1;
		}
		return MT_sequence;
	}
	
	//takes the sequence array and gives back the paired task for each machine (returns array where array[machine # -1] = task #)
	private static byte[] get_pairs(byte[] MT_sequence) {
		byte[] MT_pairs = new byte[8];
		//byte[][] remaining_tasks = new byte[8][8];
		byte[][] remaining_tasks = {{1, 2, 3, 4, 5, 6, 7, 8}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0} };
		for (int i=1; i<8; i++) {
			for (int j=0; j<8-i; j++) {
				if (MT_sequence[i] != j+1) {
					remaining_tasks[i][j] = remaining_tasks[i-1][j];
				}
				else {
					MT_pairs[i-1] = remaining_tasks[i-1][j];
				}
			}
		}
		return MT_pairs;
	}
	
	//returns total cost of current pairing given cost per machine for task and too near task list (each should hold value -1 to indicate forbidden)
	private static int cost_of_pairs(byte[] MT_pairs, int[][] MT_forbidden, int[][] MT_too_near) {
		int cost = 0;
		for (int i=0; i<8; i++) {
			if (MT_forbidden[i][MT_pairs[i]] == -1) {
				cost = -1;
				break;
			}
			else {
				cost += MT_forbidden[i][MT_pairs[i]];
			}
		}
		for (int i=0; i<MT_too_near.length; i++) {
			if (cost == -1) {
				break;
			}
			if (MT_too_near[i][2] == -1) {
				for (int j=0; j<8; j++) {
					if (j==7) {
						if (MT_pairs[0] == MT_too_near[i][0] && MT_pairs[7] == MT_too_near[i][1] && MT_too_near[i][2] == -1) {
							cost = -1;
							break;
						}
						else {
							cost += MT_too_near[i][2];
						}
					}
					else {
						if (MT_pairs[j] == MT_too_near[i][0] && MT_pairs[j+1] == MT_too_near[i][1] && MT_too_near[i][2] == -1) {
							cost = -1;
							break;
						}
						else {
							cost += MT_too_near[i][2];
						}
					}
				}
			}
		}
		return cost;
	}


	int supertree_cost;
	byte machine_task;			//machine 8's task
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

