# BottomMenu
[ ![Download](https://api.bintray.com/packages/lilincpp/android/BottomMenu/images/download.svg) ](https://bintray.com/lilincpp/android/BottomMenu/_latestVersion)

快速集成市面上主流的底部菜单栏

Gradle
------

```
compile 'com.github.lilincpp:bottommenu:0.0.1'
```

Maven
--------

```
<dependency>
  <groupId>com.github.lilincpp</groupId>
  <artifactId>bottommenu</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

效果
----

![](https://github.com/lilincpp/BottomMenu/blob/master/picture/effect1.png)
![](https://github.com/lilincpp/BottomMenu/blob/master/picture/effect3.png)

如何使用
--------

```java
	BottomMenu.Builder builder = new BottomMenu.Builder(v);
	builder
		.gridLayout()
		.addItem("你好","Your Drawable")
		.addItem("你好","Your Drawable")
		.addItem("你好","Your Drawable")
		.addItem("你好","Your Drawable");
                
	BottomMenu menu = builder.create();
	
	menu.addOnDismissListener(new IMenu.OnDismissListener() {
		@Override
		public void onDismiss(IMenu menu) {
		   //TODO SOMETHING
		}
	});

	menu.addOnShowListener(new IMenu.OnShowListener() {
		@Override
		public void onShow(IMenu menu) {
			//TODO SOMETHING
		}
	});

	menu.addOnItemClickListener(new IMenu.OnItemClickListener() {
		@Override
		public void onItemClick(IMenu menu, int position) {
			//TODO SOMETHING
		}
	});
	
	menu.show();
```

License
-------

BottomMenu binaries and source code can be used according to the [Apache License, Version 2.0](https://github.com/lilincpp/BottomMenu/blob/master/LICENSE).
