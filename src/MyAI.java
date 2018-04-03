import java.util.Stack;

public class MyAI extends Agent {

	Stack<Cell> st = new Stack<Cell>();
	Cell matrix[][] = new Cell[7][7];
	int currDirection = 0;  //right=0 up=1 left=2 down=3
	int i = 0, j = 0;

	public MyAI() {
		for(int p = 0; p < 7; p++) {
			for(int q = 0; q < 7; q++) {
				matrix[p][q]=new Cell();
				matrix[p][q].x=p;
				matrix[p][q].y=q;

			}
		}

		matrix[0][0].isSafe = true;
	}

	public class Cell {
		int x;
		int y;
		boolean isSafe;
		boolean isVisited;
		boolean isExplored;
		boolean deadEnd;
		public Cell() {
			x = 0;
			y = 0;
			isSafe = false;
			isVisited = false;
			isExplored = false;
			deadEnd = false;
		}
	}


	boolean goldFound = false;
	boolean notSet=false;
	public Action getAction
			(
					boolean stench,
					boolean breeze,
					boolean glitter,
					boolean bump,
					boolean scream
			) {

		//System.out.println("Current I :  "+ i);
		//System.out.println("Current J :  "+ j);

		matrix[i][j].isVisited = true;

		//System.out.println("X - co-ordinate of current cell: "+ matrix[i][j].x);
		//System.out.println("Y - co-ordinate of current cell: "+ matrix[i][j].y);

	//	System.out.println("Present Value of my Current Direction is " + currDirection);
		for(int o=0;o<7;o++)
		{
			for(int h=0;h<7;h++)
			{
			//	System.out.print("For I value = " + o + " And j value = " + h + "Is Safe value  "+matrix[o][h].isSafe);
			//	System.out.println("For I value = " + o + " And j value = " + h + "Is Explored value  "+matrix[o][h].isExplored);
			}
		}

		if (goldFound == true) {
			if(st.isEmpty()==true)
			{
				return Action.CLIMB;
			}
			//System.out.println("Since gold was found ....Backtracking to be done...");

		//	Stack clo;
			//System.out.println("Stack size is ...." + st.size());
		//	clo = (Stack<Cell>)(st.clone());



			//for(int h=0;h<clo.size();h++)
		//	{
			//	Cell extras = (Cell)clo.peek();
//                            Cell extras = clo.peek();


			//	System.out.println(" X co-ordinate of a parent" + extras.x);
			//	System.out.println(" Y co-ordinate of a parent" + extras.y);
			//	clo.pop();
			//}





			Cell temp = st.peek();

			//System.out.println("Parents X- co-ordinate: " + temp.x);
			//System.out.println("Parents Y- co-ordinate: " + temp.y);

			//parent is in the upper cell
			if (matrix[i][j].x > temp.x) {
				//crr dir is right
				if (currDirection == 0) {
					currDirection = 3;

					return Action.TURN_RIGHT;
				} else if (currDirection == 1)
				{
					currDirection=2;

					return Action.TURN_LEFT;

				}else if (currDirection == 2) {
					currDirection=3;
					return Action.TURN_LEFT;
					// st.pop();
					//  i=i-1;
					// return Action.FORWARD;
				} else {
					i=i-1;
					st.pop();
					return Action.FORWARD;
				}
			}
			//parent in right cell
			//parent is in the bottom cell
			else if (matrix[i][j].x < temp.x) {
				//crr dir is right
				if (currDirection == 0) {
					currDirection=1;
					return Action.TURN_LEFT;
					//  st.pop();
					//  i=i+1;
					// return Action.FORWARD;
					//currDirection = 3;
					//return Action.TURN_RIGHT;
				} else if (currDirection == 1) {
					i=i+1;
					st.pop();
					//currDirection = 0;
					return Action.FORWARD;
				} else if (currDirection == 2) {
					currDirection = 1;
					return Action.TURN_RIGHT;
				} else {
					currDirection=2;
					return Action.TURN_RIGHT;
				}

			} else if (matrix[i][j].y < temp.y) {
				//crr dir is right
				if (currDirection == 0) {
					st.pop();
					j=j+1;
					return Action.FORWARD;
				} else if (currDirection == 1) {

					currDirection = 0;
					return Action.TURN_RIGHT;
				} else if (currDirection == 2) {
					currDirection = 3;
					return Action.TURN_LEFT;
				} else {
					currDirection = 0;
					return Action.TURN_LEFT;
				}

			} else if (matrix[i][j].y > temp.y) {
				//I have to move upwards now
				if (currDirection == 0) {
					currDirection = 1;
					return Action.TURN_LEFT;
				} else if (currDirection == 1) {
					//   st.pop();
					currDirection = 2;
					//j=j-1;
					return Action.TURN_LEFT;
				} else if (currDirection == 2) {
					//currDirection=1;
					st.pop();
					j = j-1;
					return Action.FORWARD;
				} else {
					currDirection = 2;
					return Action.TURN_RIGHT;
				}

			}



			//System.out.println("Although gold was found but going nowhere :O ");
		}

		if (glitter == true) {
			//System.out.println("Gold was found and to be grabbed...");
			goldFound = true;
			return Action.GRAB;
		}

		//matrix[i][j].isVisited = true;
		if (matrix[i][j].isExplored == false) {

			if (stench == false && breeze == false && bump == false) {

				//System.out.println("1..........");
				// marking all 4 adjacent cells as safe
				int rowNbr[] = new int[]{-1, 0, 0, 1};
				int colNbr[] = new int[]{0, -1, 1, 0};

				for (int k = 0; k < 4; k++) {
					if ((matrix[i][j].x + rowNbr[k] >= 0) && (matrix[i][j].x + rowNbr[k] < 7) &&
							(matrix[i][j].y + colNbr[k] >= 0) && (matrix[i][j].y + colNbr[k] < 7)) {
						//System.out.println("Hello");
						matrix[i + rowNbr[k]][j + colNbr[k]].isSafe = true;
					}


				}
			} else if (stench == false && breeze == false && bump == true) {
				//bump was found, overwriting the values of matrix
			//	System.out.println("2x.....");
				int rowNbr[] = new int[]{-1, 0, 0, 1};
				int colNbr[] = new int[]{0, -1, 1, 0};

				for (int k = 0; k < 4; k++) {
					if ((matrix[i][j].x + rowNbr[k] >= 0) && (matrix[i][j].x + rowNbr[k] < 7) &&
							(matrix[i][j].y + colNbr[k] >= 0) && (matrix[i][j].y + colNbr[k] < 7)) {
						matrix[i + rowNbr[k]][j + colNbr[k]].isSafe = true;
					}


				}
				matrix[i][j].isExplored=true;

//            if(currDirection == 0 )
//            {
//                j=j-1;
//            }
//            if(currDirection == 1)
//            {
//                i=i-1;
//            }
//            if(currDirection ==2)
//            {
//                j=j+1;
//            }
//            if(currDirection == 3)
//            {
//                i=i+1;
//            }

				if(i!=j || (i!=0 && j!=0))
				{
					//System.out.println("Bump was found and now have to send other cells as unsafe...");
					//int temp = (int)Math.max(i, j);
					//System.out.println("Temp value" + temp);
					//    temp=temp;

					if(currDirection==0)
					{
						for(int x=0;x<7;x++)
						{
							for(int z=0;z<7;z++)
							{
								if(z>=(j))
								{

									matrix[x][z].isSafe = false;
									matrix[x][z].deadEnd = true;
								}
							}
						}
					}else if(currDirection==1) {
						for (int x = 0; x < 7; x++) {
							for (int z = 0; z < 7; z++) {
								if (x >= (i)) {

									matrix[x][z].isSafe = false;
									matrix[x][z].deadEnd = true;
								}
							}
						}

					}else if(currDirection==2)
					{
						for(int x=0;x<7;x++)
						{
							for(int z=0;z<7;z++)
							{
								if(x<(i))
								{

									matrix[x][z].isSafe = false;
									matrix[x][z].deadEnd = true;
								}
							}
						}
					}
					else if(currDirection==3)
					{
						for(int x=0;x<7;x++)
						{
							for(int z=0;z<7;z++)
							{
								if(z<(j))
								{

									matrix[x][z].isSafe = false;
									matrix[x][z].deadEnd = true;
								}
							}
						}
					}

//					for(int x=0;x<7;x++)
//					{
//						for(int z=0;z<7;z++)
//						{
//							if(x>=(i) || z>=(j))
//							{
//
//								matrix[x][z].isSafe = false;
//								matrix[x][z].deadEnd = true;
//							}
//						}
//					}

				}

				if(currDirection == 0 )
				{
					j=j-1;
				}
				if(currDirection == 1)
				{
					i=i-1;
				}
				if(currDirection ==2)
				{
					j=j+1;
				}
				if(currDirection == 3)
				{
					i=i+1;
				}

				if(currDirection == 0 )
				{
					currDirection =1;
				}
				else if(currDirection == 1)
				{
					currDirection =2;
				}
				else if(currDirection ==2)
				{
					currDirection = 3;
				}
				else if(currDirection == 3)
				{
					currDirection = 0;
				}

				st.pop();
				return Action.TURN_LEFT;


			}

			if (i >= 0 && j + 1 <= 6) {

				//System.out.println("Wanna go rightside");
				if (matrix[i][j + 1].isExplored == false && matrix[i][j + 1].isSafe == true && matrix[i][j+1].isVisited==false && matrix[i][j+1].deadEnd==false) {
					//if ( matrix[i][j + 1].isSafe == true) {
					// j=j+1;
					//System.out.println("I am going rightside");
				//	System.out.println("3");
					if (currDirection == 0) {
						st.push(matrix[i][j]);
						j = j + 1;
						//adding into stack as a root

						return Action.FORWARD;

					} else if (currDirection == 1) {
						currDirection = 0;
						return Action.TURN_RIGHT;

					} else if (currDirection == 2) {
						currDirection = 1;
						return Action.TURN_RIGHT;
					} else {
						//facing downwards
						currDirection = 0;
						return Action.TURN_LEFT;
					}
				}
			}
			if (i + 1 < 7 && j < 7) {
				//GOING UPWARDS
				//System.out.println("Wanna go upwards");
				if (matrix[i+1][j].deadEnd==false && matrix[i + 1][j].isExplored == false && matrix[i + 1][j].isSafe == true && matrix[i+1][j].isVisited==false) {

					//System.out.println(" going upwards");
				//	System.out.println("4.....");
					//if ( matrix[i + 1][j].isSafe == true) {
					// j=j+1;
					if (currDirection == 0) {
						// st.push(matrix[i][j]);
						//   j = j + 1;
						//adding into stack as a root
						currDirection = 1;
						return Action.TURN_LEFT;

					} else if (currDirection == 1) {
						st.push(matrix[i][j]);
						i = i + 1;
						return Action.FORWARD;

					} else if (currDirection == 2) {
						currDirection = 1;
						return Action.TURN_RIGHT;
					} else {
						//downwards
						currDirection = 2;
						return Action.TURN_RIGHT;
					}
				}

			}
			if (i < 7 && j - 1 >= 0) {

				//System.out.println("Wanna go leftside");
				if (matrix[i][j-1].deadEnd==false && matrix[i][j - 1].isExplored == false && matrix[i][j - 1].isSafe == true && matrix[i][j-1].isVisited==false) {

				//	System.out.println("Going leftside");
					//System.out.println("5");
					//if ( matrix[i][j - 1].isSafe == true) {
					// j=j+1;
					if (currDirection == 0) {
						currDirection = 1;
						return Action.TURN_LEFT;

					} else if (currDirection == 1) {
						currDirection = 2;
						return Action.TURN_LEFT;

					} else if (currDirection == 2) {
						st.push(matrix[i][j]);
						j = j - 1;
						return Action.FORWARD;
					} else {
						//downwards
						currDirection = 2;
						return Action.TURN_RIGHT;
					}
				}

			}
			if (i - 1 >= 0 && j < 7) {

				//System.out.println("Wanna go downside");
				if (matrix[i-1][j].deadEnd==false && matrix[i - 1][j].isExplored == false && matrix[i - 1][j].isSafe == true && matrix[i-1][j].isVisited==false) {

				//	System.out.println("Going downwards");
				//	System.out.println("6");
					//if (matrix[i - 1][j].isSafe == true) {
					// j=j+1;
					if (currDirection == 0) {
						currDirection = 3;
						return Action.TURN_RIGHT;

					} else if (currDirection == 1) {
						currDirection = 2;
						return Action.TURN_LEFT;

					} else if (currDirection == 2) {
						currDirection = 3;
						return Action.TURN_LEFT;
					} else {
						//downwards
						st.push(matrix[i][j]);
						i = i - 1;
						return Action.FORWARD;
					}
				}
			}
			matrix[i][j].isExplored = true;
			//this one is when it wont go to any of the 4 adj cells and cell is marked as explored
			if(st.isEmpty()==true)
			{
				return Action.CLIMB;
			}
			//System.out.println("Backtracking to be done...");

			Cell temp = st.peek();

//			System.out.println("Parents X- co-ordinate: " + temp.x);
//			System.out.println("Parents Y- co-ordinate: " + temp.y);



			//parent is in the upper cell
			if (matrix[i][j].x > temp.x) {

				//System.out.println("12");
				//crr dir is right
				if (currDirection == 0) {
					currDirection = 3;

					return Action.TURN_RIGHT;
				} else if (currDirection == 1)
				{
					currDirection=2;

					return Action.TURN_LEFT;

				}else if (currDirection == 2) {
					currDirection=3;
					return Action.TURN_LEFT;
					// st.pop();
					//  i=i-1;
					// return Action.FORWARD;
				} else {
					i=i-1;
					st.pop();
					return Action.FORWARD;
				}
			}
			//parent in right cell
			//parent is in the bottom cell
			else if (matrix[i][j].x < temp.x) {

			//	System.out.println("13 ............");
				//crr dir is right
				if (currDirection == 0) {
					currDirection=1;
					return Action.TURN_LEFT;
					//  st.pop();
					//  i=i+1;
					// return Action.FORWARD;
					//currDirection = 3;
					//return Action.TURN_RIGHT;
				} else if (currDirection == 1) {
					i=i+1;
					st.pop();
					//currDirection = 0;
					return Action.FORWARD;
				} else if (currDirection == 2) {
					currDirection = 1;
					return Action.TURN_RIGHT;
				} else {
					currDirection=2;
					return Action.TURN_RIGHT;
				}

			} else if (matrix[i][j].y < temp.y) {
				//crr dir is right
			//	System.out.println("14 ............");
				if (currDirection == 0) {
					st.pop();
					j=j+1;
					return Action.FORWARD;
				} else if (currDirection == 1) {

					currDirection = 0;
					return Action.TURN_RIGHT;
				} else if (currDirection == 2) {
					currDirection = 3;
					return Action.TURN_LEFT;
				} else {
					currDirection = 0;
					return Action.TURN_LEFT;
				}

			} else if (matrix[i][j].y > temp.y) {

			//	System.out.println("15 ............");
				//I have to move upwards now
				if (currDirection == 0) {

					currDirection = 1;
					return Action.TURN_LEFT;
				} else if (currDirection == 1) {
					//   st.pop();
					currDirection = 2;
					//j=j-1;
					return Action.TURN_LEFT;
				} else if (currDirection == 2) {
					//currDirection=1;
					st.pop();
					j = j-1;
					return Action.FORWARD;
				} else {
					currDirection = 2;
					return Action.TURN_RIGHT;
				}

			}


			if(currDirection == 2)
			{
				return Action.TURN_RIGHT;
			}
			if(currDirection == 0)
			{
				return Action.TURN_RIGHT;
			}
			if(currDirection == 1)
			{
				return Action.TURN_RIGHT;
			}
			else return Action.TURN_RIGHT;


		}else {
			//backtracking
			//goto parent

			//Stack<Cell> clo;


			if(st.isEmpty()==true)
			{
				return Action.CLIMB;
			}

		//	System.out.println("Stack size is ...." + st.size());
		//	clo = (Stack<Cell>)(st.clone());



		//	for(int h=0;h<clo.size();h++)
		//	{
		//		Cell extras = clo.peek();
//
//				System.out.println(" X co-ordinate of a parent" + extras.x);
//				System.out.println(" Y co-ordinate of a parent" + extras.y);
			//	clo.pop();
		//	}
		//	System.out.println("Since cell was explored ....Backtracking to be done...");

			Cell temp = st.peek();
//
//			System.out.println("Parents X- co-ordinate: " + temp.x);
//			System.out.println("Parents Y- co-ordinate: " + temp.y);





			//parent is in the upper cell
			if (matrix[i][j].x > temp.x) {

				//System.out.println("12");
				//crr dir is right
				if (currDirection == 0) {
					currDirection = 3;

					return Action.TURN_RIGHT;
				} else if (currDirection == 1)
				{
					currDirection=2;

					return Action.TURN_LEFT;

				}else if (currDirection == 2) {
					currDirection=3;
					return Action.TURN_LEFT;
					// st.pop();
					//  i=i-1;
					// return Action.FORWARD;
				} else {
					i=i-1;
					st.pop();
					return Action.FORWARD;
				}
			}
			//parent in right cell
			//parent is in the bottom cell
			else if (matrix[i][j].x < temp.x) {

			//	System.out.println("13 ............");
				//crr dir is right
				if (currDirection == 0) {
					currDirection=1;
					return Action.TURN_LEFT;
					//  st.pop();
					//  i=i+1;
					// return Action.FORWARD;
					//currDirection = 3;
					//return Action.TURN_RIGHT;
				} else if (currDirection == 1) {
					i=i+1;
					st.pop();
					//currDirection = 0;
					return Action.FORWARD;
				} else if (currDirection == 2) {
					currDirection = 1;
					return Action.TURN_RIGHT;
				} else {
					currDirection=2;
					return Action.TURN_RIGHT;
				}

			} else if (matrix[i][j].y < temp.y) {
				//crr dir is right
			//	System.out.println("14 ............");
				if (currDirection == 0) {
					st.pop();
					j=j+1;
					return Action.FORWARD;
				} else if (currDirection == 1) {

					currDirection = 0;
					return Action.TURN_RIGHT;
				} else if (currDirection == 2) {
					currDirection = 3;
					return Action.TURN_LEFT;
				} else {
					currDirection = 0;
					return Action.TURN_LEFT;
				}

			} else if (matrix[i][j].y > temp.y) {

				//System.out.println("15 ............");
				//I have to move upwards now
				if (currDirection == 0) {

					currDirection = 1;
					return Action.TURN_LEFT;
				} else if (currDirection == 1) {
					//   st.pop();
					currDirection = 2;
					//j=j-1;
					return Action.TURN_LEFT;
				} else if (currDirection == 2) {
					//currDirection=1;
					st.pop();
					j = j-1;
					return Action.FORWARD;
				} else {
					currDirection = 2;
					return Action.TURN_RIGHT;
				}

			}

		}
		return Action.CLIMB;
	}
}