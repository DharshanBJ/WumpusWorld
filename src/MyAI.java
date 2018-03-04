import java.util.*;
// ======================================================================
// FILE:        MyAI.java
//
// AUTHOR:      Abdullah Younis
//
// DESCRIPTION: This file contains your agent class, which you will
//              implement. You are responsible for implementing the
//              'getAction' function and any helper methods you feel you
//              need.
//
// NOTES:       - If you are having trouble understanding how the shell
//                works, look at the other parts of the code, as well as
//                the documentation.
//
//              - You are only allowed to make changes to this portion of
//                the code. Any changes to other portions of the code will
//                be lost when the tournament runs your code.
// ======================================================================

//git push test
public class MyAI extends Agent
{
	Stack<Action> s=new Stack<>();
	Stack<Action> sv=new Stack<>();
	Stack<Action> toCheck = new Stack<>();
	Stack<Action> toCheckVertical = new Stack<>();
	Stack<Action> toCheckTop = new Stack<>();
	Stack<Action> toCheckBottom = new Stack<>();
	//Stack<Action> st=new Stack<Action>();
	boolean horizontal=false;
	boolean vertical=false;
	int toReturn=0;
	boolean goldFound=false;
	boolean top=false;
	boolean bottom=false;

	public Action callBottom(boolean stench, boolean breeze, boolean glitter, boolean bump, boolean scream)
	{
//		if(bottom==true){
//			return callBottom(stench, breeze, glitter, bump, scream);
//		}

		//System.out.println("Reached bottom");
		if(toCheckBottom.isEmpty()==false)
		{
			//System.out.println("Vertical - returning..."+toCheckVertical.peek());
			return toCheckBottom.pop();
		}

		else if(glitter == true)
		{
			//System.out.println("In vertical/..gold found");
			sv.push(Action.TURN_LEFT);
			sv.push(Action.TURN_LEFT);
			toCheckBottom =(Stack<Action>)sv.clone();
			return Action.GRAB;
		}
		else if(stench == true || breeze==true ||bump==true )
		{
			//System.out.println("In vertical...stench or breeze or bump found");
			sv.push(Action.TURN_LEFT);
			toCheckBottom =(Stack<Action>)sv.clone();

			return (Action.TURN_LEFT);
		}

		//System.out.println("In vertical...moving forward");
		sv.push(Action.FORWARD);
		return Action.FORWARD;

	}

	public Action callTop(boolean stench, boolean breeze, boolean glitter, boolean bump, boolean scream)
	{
		//System.out.println("Reached top");
		if(bottom==true){
			return callBottom(stench, breeze, glitter, bump, scream);
		}
		if(toCheckTop.isEmpty()==false)
		{
			//System.out.println("Vertical - returning..."+toCheckVertical.peek());
			return toCheckTop.pop();
		}
		else if(glitter == true)
		{
			//System.out.println("In vertical/..gold found");
			sv.push(Action.TURN_LEFT);
			sv.push(Action.TURN_LEFT);
			toCheckTop =(Stack<Action>)sv.clone();
			return Action.GRAB;
		}
		else if(stench == true || breeze==true )
		{
			//System.out.println("In vertical...stench or breeze or bump found");
			sv.push(Action.TURN_LEFT);
			toCheckTop =(Stack<Action>)sv.clone();

			return (Action.TURN_LEFT);
		}
 		else if(bump==true)
		{
			bottom=true;
			sv.push(Action.TURN_LEFT);
			return Action.TURN_RIGHT;
		}
		//System.out.println("In vertical...moving forward");
		sv.push(Action.FORWARD);
		return Action.FORWARD;

	}

	public Action callVertical(boolean stench, boolean breeze, boolean glitter, boolean bump, boolean scream)
	{
		if(top==true){
			return callTop(stench, breeze, glitter, bump, scream);
		}
		if(toCheckVertical.isEmpty()==false)
		{
			//System.out.println("Vertical - returning..."+toCheckVertical.peek());
			return toCheckVertical.pop();
		}
		else if(glitter == true)
		{
			//System.out.println("In vertical/..gold found");
			sv.push(Action.TURN_LEFT);
			sv.push(Action.TURN_LEFT);
			toCheckVertical =(Stack<Action>)sv.clone();
			return Action.GRAB;
		}
		else if(stench == true || breeze==true )
		{
			//System.out.println("In vertical...stench or breeze or bump found");
			sv.push(Action.TURN_LEFT);
			toCheckVertical =(Stack<Action>)sv.clone();

			return (Action.TURN_LEFT);
		}else if(bump==true)
		{
			top=true;
			sv.push(Action.TURN_LEFT);
			return Action.TURN_RIGHT;
		}
		//System.out.println("In vertical...moving forward");
		sv.push(Action.FORWARD);
		return Action.FORWARD;

	}

	public MyAI ( )
	{
		sv.push(Action.CLIMB);
	}

	public Action getAction	(
			boolean stench,
			boolean breeze,
			boolean glitter,
			boolean bump,
			boolean scream )
	{

		//  System.out.println("Inside horizontal");
		//System.out.println("Stench = "+stench+" Breeze = "+breeze + " Glitter = " + glitter + " Bump = "+bump );

		if(vertical==true)
		{
			//System.out.println("Calling vertical");
			return callVertical(stench, breeze, glitter, bump, scream);
		}

		if(toCheck.isEmpty()==false)
		{
			//System.out.println("Returning... "+ toCheck.peek());
			return toCheck.pop();

		}
		else  if(toReturn ==1 && goldFound==true)
		{
			return Action.CLIMB;
		}
		else if(toReturn ==1 && goldFound==false)
		{
			vertical = true;
			return Action.TURN_RIGHT;
			// go vertical
		}
		else if(glitter == true)
		{
			//System.out.println("Gold found");
			toReturn =1;
			s.push(Action.TURN_LEFT);
			s.push(Action.TURN_LEFT);
			goldFound = true;
			toCheck =(Stack<Action>)s.clone();
			return Action.GRAB;
		}
		else if(stench == true || breeze==true || bump==true)
		{
			//System.out.println("Stench or breeze found");
			toReturn =1;
			s.push(Action.TURN_LEFT);
			toCheck =(Stack<Action>)s.clone();
			//System.out.println("Turning left cz of stench");
			return Action.TURN_LEFT;
		}
		//System.out.println("Moving forward");
		s.push(Action.FORWARD);
		return Action.FORWARD;
	}
}