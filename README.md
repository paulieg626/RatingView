# RatingView 1.0

Image:
![Image](https://raw.githubusercontent.com/paulieg626/RatingView/main/image.jpg)

XML:
```xml
<package.RatingView
 android:id="@+id/ratingview"
 android:layout_width="286dp"
 android:layout_height="103dp"/>
```

Java code:
```java
RatingView ratingview = ...;

//Indentation from the edge
ratingview.setPadding(10);

//Line thickness
ratingview.setLine(10);

//Round lines
ratingview.setRound(10);

//Background color
ratingview.setBackgroundColor(0x00000000);

//Text color
ratingview.setTextColor(0xffffffff);

//Rating (one,two,three,four,five)
ratingview.setNumber(505000,10000,7656,300060,4000000);
```
