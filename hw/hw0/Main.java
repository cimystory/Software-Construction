package edu.cmu.qatar.cs214.hw.hw0;
///////////////  SHAHAN ALI MEMON ///////////////
///////////////       samemon      ///////////////
///////////////		15214 - HW0    //////////////

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		FriendGraph graph = new FriendGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person monica = new Person("Monica");
		Person thierry = new Person("Thierry");
		Person illiano = new Person("Illiano");
		Person saquib = new Person("Saquib");
		
		graph.addPerson(rachel);
		graph.addPerson(ross);
		graph.addPerson(ben);
		graph.addPerson(kramer);
		graph.addPerson(monica);
		graph.addFriendship(rachel, ross);
		graph.addFriendship(ross, ben);
		graph.addFriendship(rachel,monica);
		graph.addFriendship(illiano, saquib);
		graph.addFriendship(illiano, ross);
		graph.addFriendship(illiano,ben);
		graph.addFriendship(monica,thierry);
		
		System.out.println(graph.getDistance(saquib, thierry)); //should print 5
		System.out.println(graph.getDistance(thierry, ben)); //should print 4
		System.out.println(graph.getDistance(ross, rachel)); //should print 1
		System.out.println(graph.getDistance(ben, rachel)); //should print 2
		System.out.println(graph.getDistance(rachel, rachel)); //should print 0
		System.out.println(graph.getDistance(rachel, kramer)); //should print -1
		System.out.println(graph.getDistance(rachel, thierry)); //should print -1
		System.out.println(graph.getDistance(illiano, ben)); //should print 1
		System.out.println(graph.getDistance(thierry, rachel)); //should print 2
		
		
		
	}

}

