@[TOC](读书笔记《Java语言程序设计（11th）》)
# 抽象类和接口
## 抽象类
* 抽象类：不可以创建对象，没有实例，可以包含抽象方法。
* 在UML中，抽象类和抽象方法的名字用斜体表示。
* 抽象类不能使用new操作符创建它的实例。抽象方法只有定义没有实现。它的实现由子类提供。一个包括抽象方法的类必须声明为抽象类。
* 抽象类的构造方法定义为protected，因为它只被子类使用。
* **为何要使用抽象方法？**	
* 如` GeometricObject geoObject1 = new Circle(5);`  ` GeometricObject geoObject1 = new Rectangle(5, 3);`  然后JVM在运行时根据该方法的实际对象的类型来动态地决定调用哪一个方法。
* 抽象方法不能包含在非抽象类中。 如果抽象父类的子类不能实现所有的抽象方法，那么子类也必须定义为抽象。换句话说，在继承自抽象类的非抽象子类中，必须实现所有的抽象方法。
* 抽象方法是非静态的。
* 抽象类不能使用new操作符来初始化。但是，仍然可以定义它的构造方法，这个构造方法在它的子类的构造方法中调用。
* 包含抽象方法的类必须是抽象的。然而，可以定义一个不包括抽象方法的抽象类。这个抽象类用于作为定义新子类的基类。
* 子类可以重写父类的方法并将它定义为抽象的。这很少见，但是它在当父类的方法实现在子类中变得无效时是很有用的。在这种情况下，子类必须定义为抽象的。
* 虽然不能使用new从一个抽象类创建一个实例，但是抽象类可以作为一种数据类型。因此，下面的语句创建一个元素是GeometricObject类型的数组是正确的：`GeometricObject[] objects = new GeometricObject[10];`
* 然后可以创建一个GeometricObject 的实例，并将它的引用赋值给数组。`objects[0] = new Circle();`
* Number类是数值包装类以及BigInteger和BigDecimal类的抽象父类。**byteValue()\shortValue()\intValue\longValue\floatValue()\doubleValue()**
```
import java.util.ArrayList;

import java.math.*;

public class LargestNumber {
	public static void main(String[] args){
		ArrayList<Number> list = new ArrayList<>();
		list.add(45);
		//通过拆箱操作，45自动转换为Integer并加入Number类动态数组列表中
		list.add(3445.53);
		list.add(new BigInteger("34332323234344343101"));
		list.add(new BigDecimal("2.09090909890913434333344343"));
		
		System.out.println("The largest number is " + getLargestNumber(list));
	}
	
	public static Number getLargestNumber(ArrayList<Number> list){
		if(list == null || list.size() == 0)
			return null;
			//Number是抽象类，不可以使用new创建对象，使用，int、double等创建实际对象。
		Number number = list.get(0);
		for(int i = 1; i < list.get(i).doubleValue();i++)
			if (number.doubleValue() < list.get(i).doubleValue())
			//如果doubleValue（）未定义，将不能找到最大值
			number = list.get(i);
		
		return number;
	}
}
```
* java.util.GregorianCalendar是抽象类java.util.Calendar的一个具体的子类。
>抽象类和接口的区别。
>* 抽象类是指都是一种东西。如都是动物。接口呢，是指，都具有共同的一样属性，如可食用。
>*  一个类可以实现多个接口，但是只能继承一个父类。（只允许类的继承做单一继承，但是允许使用接口做多继承）
>* 变量：抽象类无限制，接口的所有变量必须是public static final（可省略）
>* 构造方法  抽象类：子类通过构造方法链调用构造方法，抽象类不能使用new操作符实例化； 接口：没有构造方法，不能使用new操作符实例化。
>* 方法：  抽象类：无限制    接口：可以包含public的抽象实例方法、public的默认方法以及public的静态方法。
* 一个接口可以通过extends继承其他接口，则此接口成为子接口。



## 接口
* 与抽象类一样，不能使用new。
* **abstract和public**
1. 抽象类定义时，将来子类不共同的方法，用abstract定义此方法，（如几何图形抽象类有一个抽象方法getarea（），因为正方形和圆形等求面积方法不同）此抽象类定义也用abstract。
2. 子类如果将接口内和抽象类内的抽象方法重写了，此子类就无需加abstract，用public即可。
* 接口定义时可以省略：
```
public interface T{
	**public static final** int K = 1;
	**public abstract** void p();
}
```
* **等价于**
```
public interface T{
	int K = 1;
	void p();
}
```
* interface必须单独写在一个.java文件中
* Eclipse的默认包其实不存在，.java文件其实都直接放在src大包中，所以无法为default package改名，只能重新创建一个包拷贝过来。
```
package TestEdible;

import java.io.ObjectStreamException;

public class TestEdible {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object [] objects =  {new TIger(),new Chicken(), new Apple()};
		for(int i =0; i < objects.length; i++){
			if(objects[i] instanceof Edible)
				System.out.println(((Edible)objects[i]).howToEat());
			
			if (objects[i] instanceof Animal){
				System.out.println(((Animal)objects[i]).sound());
			}
		}
	}

}
//思考为何用abstract？
**abstract** class Animal{
	private double weight; 
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(double weight){
		this.weight = weight;
	}
	
	public abstract String sound();
}
class Chicken extends Animal implements Edible{
	public String howToEat(){
		return "Chicken: Fry it";
	}
	
	public String sound(){
		return "Chicken: cock-a-doodle-doo";
	}
}

class TIger extends Animal{
	public String sound(){
		return "Tiger: RROOAARR";
	}
}

abstract class Fruit implements Edible{
	
}
class Apple extends Fruit{
	public String howToEat(){
		return "Apple : Make apple cider";
	}
}

class Orange extends Fruit{
	public String howToEat(){
		return "Orange: Make orange juice";
	}
}
```
*****
* Comparable 接口
* 此接口是一个泛型接口。实现该接口时，泛型类型E被替换成一种具体的类型。
```
package java.lang;
public interface Comparable<E>{
	public int compareTo(E o);
}
```
* compareTo方法判断这个对象相较于对象o的顺序，当小于、等于、大于时分别返回负数、零、正数。
****
* 有些接口的方法体是空的，这些接口叫做**标记接口（marker interface）**它用来表示一个类希望拥有某些特征
* 如Cloneable接口指定了一个对象可以被克隆，但它是一个标记接口，它的对象可以使用Object类中定义的clone（）方法克隆。
```
package java.lang;
public interface Cloneable{
}
```
`protectes **native** Object clone( ) throws CloneNotSupportedException`
* **关键字native**表明这个方法不是用Java写的，但它是JVM针对本队平台实现的。
* 关键字protected限定方法只能在一个包内或在其子类中访问。由于这个原因，才用上述一行代码重写clone（）修改为public，该方法就可以在任何一个包中使用。
* Object类中的**clone方法将原始对象的每个数据域复制给目标对象**。
1. 如果一个数据域是基本类型，复制的就是它的值。
2. 如果一个数据域是对象，复制的就是该域的引用。
**深复制和浅复制**
```
//浅复制
public House(int id, double area){
		this.id = id;
		this.area = area;
		whenBuilt = new java.util.Date();
	}
	public Object clone(){
		try{
			return super.clone();
		}
		catch(CloneNotSupportedException ex){
			return null;
		}
	}
```
上述，house1 == house2；为假，但house1.whenBuilt == house2.whenBuilt 为真（说明二者其实是一个东西，称不上复制）  即为浅复制
```
public Object clone() throws CloneNotSupportedException{
	//做一次浅复制
	House houseClone = (House)super.clone();
	//对WhenBuilt单独处理，补完深坑，完成深复制。
	houseClone.whenBuilt = (java.util.Date)(whenBuilt.clone());
}
```
现在`house1.whenBuilt == house2.whenBuilt`为假，即说明单独开辟了一块内存存储新复制的数据域，说明完成了深复制。
******
* 关于clone方法的几点思考：
> 为什么Object类中的clone方法定义为protected，而非public？
>*  因为 并非每个对象都可以被克隆。若子类需要克隆，JAVA强制子类重写此方法。

* 类的设计原则
> 1. 内聚性：不应该将学生和老师放在一个组中。
> 2. 一致性：将数据声明置于构造方法之前，将构造方法置于普通方法之前。遵循java的命名习惯。
> 3.封装性： 应使用private修饰符以免用户直接访问到数据，使类更容易维护。
> 4. 清晰性：尽量使方法定义容易理解，使数据定义尽量精简，如已定义了数据声名-出生日期，就不应该再定义数据声明-年龄。
> 5. 完整性：若要为各种用户服务，应完整的定义类中的各种方法。
> 6.合理的分配静态和实例。如果==一个变量为被类的所有实例所共享== ==（不依赖于任何具体的实例）==，就声明为静态变量 。==对于静态变量，不要从构造方法中传入参数来初始化静态数据域。最好使用修改器（setter）来改变静态数据域。 == ==构造方法永远是实例方法，因为它是用来构造具体的实例的。==
