package com.liushao;
import java.io.*;
import java.util.*;
enum MovieType implements Serializable{ 
	LOVE("爱情片"),COMEDY("喜剧片"),ACTION("动作片"),SCIENCE("科幻片");
	private String n;
	public String getN(){
		return n;
	}
	MovieType(){
		
	}
	MovieType(String n){
		this.n=n;
	}
}

//电影
class Movie implements Serializable{   
	MovieType mt;
	String movieName;
	Movie(){
		
	}
	Movie(MovieType mt,String movieName){
		this.mt=mt;
		this.movieName=movieName;
	}
	
}
//电影放映时间段
class MovieSchedule implements Serializable{  
	String theatre;  //放映厅 
	String time;  //放映时间：年月日 、时分
	MovieSchedule(){
		
	}
	MovieSchedule(String theatre,String time){
		this.theatre=theatre;
		this.time=time;
	}
}

//座位
class Seat implements Serializable{  
	int row;
	int column;
	Seat(){
		
	}
	Seat(int row,int column){
		this.row=row;
		this.column=column;
	}
}
//电影票
class Ticket implements Serializable{
	Movie movie;
	MovieSchedule  ms;
	Seat seat;
	Ticket(){
		
	}
	Ticket(Movie movie,MovieSchedule ms,Seat seat){
		this.movie=movie;
		this.ms=ms;
		this.seat=seat;
	}
	
	public void printTicket(){ //打印票的详细信息 
		System.out.println(movie.movieName+"\n"+"类型："+movie.mt.getN()+"\n"+"放映厅："+ms.theatre+"\n"+"时间:"+ms.time+"\n"+"座位:"+seat.row+"排"+seat.column+"座");
		System.out.println("*************************");
	}
}
public class work1 {

	public static void main(String[] args) {
				//--1 构造电影类型枚举对象
		MovieType mt1=MovieType.LOVE;
		MovieType mt2=MovieType.COMEDY;
		MovieType mt3=MovieType.ACTION;
		MovieType mt4=MovieType.SCIENCE;
				//--2 构造电影对象
		Movie m1=new Movie(mt2,"疯狂的石头");
		Movie m2=new Movie(mt4,"2012");
		Movie m3=new Movie(mt3,"速度与激情");
				//--3 构造放映场次对象
		MovieSchedule MS1=new MovieSchedule("放映厅1","2010年7月12日 20时20分");	
		MovieSchedule MS2=new MovieSchedule("放映厅1","2010年7月14日 19时40分");
		MovieSchedule MS3=new MovieSchedule("放映厅2","2010年7月14日 19时40分");
		MovieSchedule MS4=new MovieSchedule("放映厅2","2018年7月12日 20时20分");
				//--4 构造座位对象
		Seat s1=new Seat(10,12);	
		Seat s2=new Seat(10,14);
		Seat s3=new Seat(7,15);
		Seat s4=new Seat(7,16);
				//--5 根据电影、放映场次、座位构造电影票对象
		Ticket t1=new Ticket(m1,MS1,s1);
		Ticket t2=new Ticket(m2,MS2,s2);
		Ticket t3=new Ticket(m2,MS2,s3);
		Ticket t4=new Ticket(m3,MS4,s4);
				//--6 序列化将电影票写入二进制文件
		ArrayList<Ticket> list1=new ArrayList<Ticket>();
		list1.add(t1);
		list1.add(t2);
		list1.add(t3);
		list1.add(t4);
	try{
		File file=new File("Ticket.dat");
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(list1);//写入
		oos.close();//关闭
		fos.close();
				//--7 反序列化将二进制里的电影票读出来
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(fis);
		ArrayList<Ticket> list2=(ArrayList<Ticket>)ois.readObject();//读取
		for(Ticket t:list2){
			t.printTicket();
		}	
		ois.close();
		fis.close();
		
	}catch(ClassNotFoundException ex){
		ex.printStackTrace();
	}catch(IOException ex){
		ex.printStackTrace();
	}
	}

}
