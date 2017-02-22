package dungeoncrawler;

import Prog1Tools.IOTools;

public class Gameloop {

	public static void main(String[] args) {
		
		//TODO:	finish room class //takes fuckin forever, i thought this would be ezpz
		//		start work on player class // handle enemy in player class?
		// 		entity class (chests, other events?)
		// 	
		
		Room[] rooms = new Room[3];
		for (int j = 0; j < rooms.length; j++) {
			rooms[j] = new Room();
		}
		
		int i = 0;
		String move_command;
		
		rooms[0].place_item(3, 3, "X");
		rooms[i].output();
		
		//TODO: you can't leave the room with the doors ('?')
		while(true){
			move_command = IOTools.readLine("Move: ");
			rooms[i].player_move("X", move_command);
			rooms[i].room_leave(move_command, rooms, i);
			System.out.println();
			
			rooms[i].output();
		}
	}

}
