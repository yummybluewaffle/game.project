package dungeoncrawler;

public class Room {
	
	private String room[][] = new String [7][7];
	
	public Room(){
		for (int i = 0; i < room.length; i++) { // y-axis
			for (int j = 0; j < room.length; j++) { // x-axis
				room[i][j] = "[ ]";
				if(i >= 0 && i <= 2 && (j == 0 || j == 6)){
					place_item(i, j, '|');
				}
				if(i >= 4 && i <= 6 && (j == 0 || j == 6)){
					place_item(i, j, '|');
				}
				if(j >= 1 && j <= 2 && (i == 0 || i == 6)){
					place_item(i, j, '-');
				}
				if(j >= 4 && j <= 5 && (i == 0 || i == 6)){
					place_item(i, j, '-');
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
	
	public void place_item(int up_down, int left_right, char item){
		String f_bracket = room[up_down][left_right].substring(0,1);
		String b_bracket = room[up_down][left_right].substring(2);
		room[up_down][left_right] = f_bracket + item + b_bracket;
	}
	
	public int[] current_position(char playerZ){
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
	
	public void player_move(char playerZ, String move_command){
		int[] player_position = current_position(playerZ);
		
		if(move_command.equals("right")){
			if(isObstacle(player_position[0], player_position[1]+1) == false){
				place_item(player_position[0], player_position[1]+1, playerZ);
				room[player_position[0]][player_position[1]] = "[ ]";
				if(startroom_leave(move_command) == true){
					place_item(player_position[0], player_position[1]+1, '[ ]');
				}
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
	
	public boolean startroom_leave(String move_command){
		int[] player_position = current_position('X');
		boolean left = false;
		
		
		//TODO: ok why does everything put me on ( 1 | 3 ) LOL
		if((player_position[0] == 3 && player_position[1] == 6) && move_command.equals("right")){
			left = true;
		}
			
		if((player_position[0] == 3 && player_position[1] == 0) && move_command.equals("left")){
			left = true;
		}
		
		return left;
	}
	
	
}
