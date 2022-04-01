package Chapter08.package1.Houserental.view;

/*
		1.显示界面
		2.接收用户的输入
		3.调用HoseService完成对房屋信息的各种操作
 */

import Chapter08.package1.Houserental.domain.House;
import Chapter08.package1.Houserental.service.HouseService;
import Chapter08.package1.Houserental.utils.TestUtility;

public class HouseView {

	private boolean loop = true;//控制显示菜单
	private char key = ' ';//接收用户选择
	private HouseService houseService = new HouseService(2);//数组大小

	//添加房屋界面
	public void addHouse() {
		System.out.println("=================添加房屋=================");
		System.out.print("姓名: ");
		String name = TestUtility.Utility.readString(8);
		System.out.println("电话: ");
		String phone = TestUtility.Utility.readString(10);
		System.out.println("地址: ");
		String address = TestUtility.Utility.readString(13);
		System.out.println("月租: ");
		int rent = TestUtility.Utility.readInt();
		System.out.println("状态: ");
		String state = TestUtility.Utility.readString(3);
		//创建一个新的House对象,注意id 是系统分配的,
		House house = new House(1, name, phone, address, rent, state);
		if (houseService.add(house)) {
			System.out.println("=============添加成功=================");
		} else {
			System.out.println("================添加失败==================");
		}
	}

	//根据id修改房屋
	public void update(){
		System.out.println("==================修改房屋信息=================");
		System.out.println("输入修改房屋的编号(-1退出)");
		int updateId = TestUtility.Utility.readInt();
		if (updateId == -1){
			System.out.println("==================放弃修改房屋信息=================");
			return;
		}

		//根据输入得到updateId,查找对象
		//返回类型的是引用类型{即：就是数组的元素}
		//后面对house.setXxx(),就会修改HoseService中houses数组的元素
		House house = houseService.seek(updateId);
		if (house == null){
			System.out.println("===============修改房屋信息编号不存在===============");
			return;
		}

		System.out.print("姓名("+ house.getName()+")：");
		String name = TestUtility.Utility.readString(8,"");//如果用户直接回车就不修改信息,默认为null
		if (!"".equals(name)){//不为" "则修改
			house.setName(name);
		}

		System.out.print("电话("+ house.getPhone()+")：");
		String phone = TestUtility.Utility.readString(8,"");//如果用户直接回车就不修改信息,默认为null
		if (!"".equals(phone)){//不为" "则修改
			house.setName(phone);
		}

		System.out.print("地址("+ house.getAddress()+")：");
		String address = TestUtility.Utility.readString(8,"");//如果用户直接回车就不修改信息,默认为null
		if (!"".equals(address)){//不为" "则修改
			house.setName(address);
		}

		System.out.print("月租("+ house.getRent()+")：");
		int rent = TestUtility.Utility.readInt(-1);
		if (rent != -1){
			house.setRent(rent);
		}

		System.out.print("状态("+ house.getState()+")：");
		String state = TestUtility.Utility.readString(3,"");//如果用户直接回车就不修改信息,默认为null
		if (!"".equals(state)){//不为" "则修改
			house.setState(state);
		}
		System.out.println("===============修改房屋信息编号成功==============");

	}

	//查找id房屋信息
	public void seekHouse() {
		System.out.println("=================查找到的房屋信息=================");
		System.out.println("输入id查询房屋信息(-1退出)");
		int seekId = TestUtility.Utility.readInt();
		House house = houseService.seek(seekId);
		if (house != null) {
			System.out.println(house);
		} else {
			System.out.println(" ");
		}
	}


	//退出确认
	//使用Utility方法,完成
	public void exit() {
		char a = TestUtility.Utility.readConfirmSelection();
		if (a == 'Y') {
			loop = false;
		}
	}

	//编写delHose
	public void delHouse() {
		System.out.println("=================删除房屋信息=================");
		System.out.print("请输入删除房屋的编号(-1退出):");
		int delId = TestUtility.Utility.readInt();
		if (delId == -1) {
			System.out.println("================放弃删除房屋信息===============");
			return;
		}
		//工具类 Utility 封装的功能 该方法本身就有循环判断的逻辑,必须输入Y/N
		char cholie = TestUtility.Utility.readConfirmSelection();
		if (cholie == 'Y') {
			if (houseService.del(delId)) {
				System.out.println("==============删除房屋信息成功================");
			} else {
				System.out.println("=================房屋编号不存在,删除失败===============");
			}
		} else {
			System.out.println("================放弃删除房屋信息===============");
		}
	}

	//编写listHouses()显示房屋列表
	public void listHouses() {
		System.out.println("=================房屋列表=================");
		System.out.println("编号\t\t房屋\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
		//获取数组
		House[] houses = houseService.list();
		for (int i = 0; i < houses.length; i++) {
			if (houses[i] == null) {//如果为null,就不显示
				break;
			}
			System.out.println(houses[i]);
		}
		System.out.println("=================显示房屋列表=================");
	}

	//显示主菜单
	public void mainMenu() {
		do {
			System.out.println("================房屋出租系统菜单=====================");
			System.out.println("\t\t\t1 新 增 房 源");
			System.out.println("\t\t\t2 查 找 房 源");
			System.out.println("\t\t\t3 删 除 房 屋 信 息");
			System.out.println("\t\t\t4 修 改 房 屋 信 息");
			System.out.println("\t\t\t5 显 示 房 屋 列 表");
			System.out.println("\t\t\t6 退     出");
			System.out.println("请输入你的选择(1-6)：");
			key = TestUtility.Utility.readChar();
			switch (key) {
				case '1':
					addHouse();
					break;
				case '2':
					seekHouse();
					break;
				case '3':
					delHouse();
					break;
				case '4':
					update();
					break;
				case '5':
					listHouses();
					break;
				case '6':
					exit();
					break;
			}
		} while (loop);
	}
}

