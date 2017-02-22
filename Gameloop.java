package dungeoncrawler;

import Prog1Tools.IOTools;

public class Gameloop {

	public static void main(String[] args) {
		
		//TODO:	finish room class
		//		start work on player class // handle enemy in player class?
		// 		entity class (chests, other events?)
		// 	
		
		Room[] rooms = new Room[3];
		for (int j = 0; j < 3; j++) {
			rooms[j] = new Room();
		}
	
		int i = 0;
		String move_command;
		
		rooms[0].place_item(3, 3, 'X');
		rooms[i].output();
		
		while(true){
			move_command = IOTools.readLine("Move: ");
			rooms[i].player_move('X', move_command);
			
			if(rooms[i].startroom_leave("right") == true){
				if(i < rooms.length){
					i++;
					rooms[i].place_item(3, 1, 'X');
				}else{
					System.out.println("You are in the last room already.");
				}
				
			}else{
				if(rooms[i].startroom_leave("left") == true){
					if(i > 0){
						i--;
						rooms[i].place_item(3, 5, 'X');
					}else{
						System.out.println("You are in the first room already.");
					}
				}
				
			}
			
			System.out.println();
			
			rooms[i].output();
		}
	}

}
