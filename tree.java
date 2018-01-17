//tree has 2 subclasses branch and leaf
class tree {
	subclass branch {
		double supertree_cost;
		double best_subtree_cost;
		tree best_subtree;
		byte used_tasks;
		byte machine_task;
	}
	
	subclass leaf {
		double supertree_cost;
		byte machine_task;			//machine 8's task
		total_cost;					//supertree cost + cost of machine 8's task
	}
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