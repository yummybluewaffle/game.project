package dungeoncrawler;

public class Room {
	
	private String room[][] = new String [7][7];
	
	public Room(){
		for (int i = 0; i < room.length; i++) { // y-axis
			for (int j = 0; j < room.length; j++) { // x-axis
				room[i][j] = "[ ]";
				if(i >= 0 && i <= 2 && (j == 0 || j == 6)){
					place_item(i, j, "|");
				}
				if(i >= 4 && i <= 6 && (j == 0 || j == 6)){
					place_item(i, j, "|");
				}
				if(j >= 1 && j <= 3 && (i == 0 || i == 6)){
					place_item(i, j, "-");
				}
				if(j >= 4 && j <= 6 && (i == 0 || i == 6)){
					place_item(i, j, "-");
				}
				//thats supposed to be a door........
				if(i == 3 && j == 0 || i == 3 && j == 6){
					place_item(i, j, "\u2610");
				}
			}
			
		}
	}
	
	public String[][] getRoom(){
		return this.room;
	}
	
	public void output(){
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room.length; j++) {
				System.out.print(room[i][j]);
			}
			System.out.println();
		}
	}
	
	public void place_item(int up_down, int left_right, String item){
		String f_bracket = room[up_down][left_right].substring(0,1);
		String b_bracket = room[up_down][left_right].substring(2);
		room[up_down][left_right] = f_bracket + item + b_bracket;
	}
	
	public int[] current_position(String playerZ){
		int [] position = new int[2];
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room.length; j++) {
				if(room[i][j].equals("["+playerZ+"]")){
					position[0] = i;
					position[1] = j;
				}
			}
		}
		return position;
	}
	
	public void player_move(String playerZ, String move_command){
		int[] player_position = current_position(playerZ);
		
		if(move_command.equals("right")){
			if(isObstacle(player_position[0], player_position[1]+1) == false){
				place_item(player_position[0], player_position[1]+1, playerZ);
				//moves the player and removes the X before the movement
				room[player_position[0]][player_position[1]] = "[ ]";
			}else{
				System.out.println("Obstacle in the way, move another way.");
			}
		}
		if(move_command.equals("left")){
			if(isObstacle(player_position[0], player_position[1]-1) == false){
				place_item(player_position[0], player_position[1]-1, playerZ);
				room[player_position[0]][player_position[1]] = "[ ]";
			}else{
				System.out.println("Obstacle in the way, move another way.");
			}
		}
		if(move_command.equals("up")){
			if(isObstacle(player_position[0]-1, player_position[1]) == false){
				place_item(player_position[0]-1, player_position[1], playerZ);
				room[player_position[0]][player_position[1]] = "[ ]";
			}else{
				System.out.println("Obstacle in the way, move another way.");
			}
		}
		if(move_command.equals("down")){
			if(isObstacle(player_position[0]+1, player_position[1]) == false){
				place_item(player_position[0]+1, player_position[1], playerZ);
				room[player_position[0]][player_position[1]] = "[ ]";
			}else{
				System.out.println("Obstacle in the way, move another way.");
			}
		}
		
		
	}
	
	//something in the way?
	public boolean isObstacle(int y, int x){
		boolean obstacle_pos = false;
		if(room[y][x] != "[ ]"){
			obstacle_pos = true;
		}
		return obstacle_pos;
		
	}
	
	//does player want to leave the room?
	public boolean player_IsLeaving(String move_command){
		int[] player_position = current_position("X");
		boolean player_left = false;
		
		
		if((player_position[0] == 3 && player_position[1] == 6) && move_command.equals("right")){
			player_left = true;
		}
			
		if((player_position[0] == 3 && player_position[1] == 0) && move_command.equals("left")){
			player_left = true;
		}
		
		return player_left;
	}
	
	//player leaves room
	public void room_leave(String move_command, Room[] rooms, int i){
		if(rooms[i].player_IsLeaving("right")){
			if(i == rooms.length){
				System.out.println("You are in the last room already.");
			}else{
				i++;
				rooms[i].place_item(3, 1, "X");
				//removes the X in the previous room to avoid double player movement
				rooms[i-1].place_item(3, 6, " ");
			}
			
		}else{
			if(rooms[i].player_IsLeaving("left")){
				if(i == 0){
					System.out.println("You are in the first room already");
				}else{
					i--;
					rooms[i].place_item(3, 5, "X");
					//removes the X in previous room
					rooms[i+1].place_item(3, 1, " ");
				}
			}
			
		}
	}
		
}
