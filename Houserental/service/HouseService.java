package Chapter08.package1.Houserental.service;

import Chapter08.package1.Houserental.domain.House;

public class HouseService {

	private House[] houses;//保存House对象
	private int houseNums = 1;//记录当前还有多少个房屋信息，默认为1
	private int idCounter = 1;//记录当前id自增的计数器

	public HouseService(int size){
		//当创建HouseService对象,指定数组大小
		houses = new House[size];
		//扩展:houses 数组扩容 size * 2
		//houses = new House[size * 2];

		//测试,初始化一个House对象
		houses[0] = new House(1,"tom","111","广东",2000,"未出租");
	}

	//seek方法,查找房屋信息
	public House seek(int seekId){
		for (int i = 0; i < houses.length; i++) {
			if (seekId == houses[i].getId()){
				return houses[i];
			}
		}
		return null;
	}


	//del方法，删除房屋信息
	public boolean del(int delId){
		//应当先找到删除的房屋信息对应的下标
		//下标和房屋的标号不是一回事
		int index = -1;
		for (int i = 0; i < houseNums; i++) {
			if (delId == houses[i].getId()){//要删除的房屋对应的id,是数组小标的i元素
				index = i;//使用index记录i
			}
		}
		if (index == -1 ){//没有找到delId在数组中不存在
			return false;
		}
		//如果找到了，就进行 置空
		for (int i = 0; i < houseNums - 1; i++) {
			houses[i] = houses[i+1];
		}
		houses[--houseNums] = null;//把当有存在房屋信息的最后一个置空 设置为 null;
		return true;
	}

	//add方法,添加新对象,返回Boolean
	public boolean add(House newHouse){
		//判断是否还可以继续
		if (houseNums == houses.length){
			System.out.println("数组已满");
			return false;
		}
		houses[houseNums] = newHouse;//把newHouse数组放在后面
		houseNums++;//增加了一个房屋信息
		//需要设计一个id自增的机制
		newHouse.setId(++idCounter);//更新id
		return true;
	}

	//list方法，返回houses
	public  House[] list(){
		return houses;
	}
}
