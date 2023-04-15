package ...;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import java.text.DecimalFormat;
import android.util.AttributeSet;
import android.graphics.RectF;

public class RatingView extends View {
	
	public int one=0,two=0,three=0,four=0,five=0; 
	public int padding = 50;
	public int line = 10;
	public int round = 10;
	public int backgroundColor = 0x00000000;
	public int textColor = 0xffffffff;
	
	public DecimalFormat f;
	public Rect background = new Rect(0,0,getWidth(),getHeight());
	public Rect zona1 = new Rect(padding,padding,getWidth()/3,getHeight()-padding);
	public Rect zona2 = new Rect(getWidth()/3,padding,getWidth()-padding,getHeight()-padding);
	
	public RatingView(Context context){
		super(context);
		load();
	}
	
	public RatingView(Context context,AttributeSet attr){
		super(context,attr);
		load();
	}
	
	private void load(){
		f = new DecimalFormat("0.0");
		background = new Rect(0,0,getWidth(),getHeight());
		zona1 = new Rect(padding,padding,getWidth()/3,getHeight()-padding);
		zona2 = new Rect(getWidth()/3,padding,getWidth()-padding,getHeight()-padding);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		load();
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		Rect zona1_1 = new Rect(padding,padding,getWidth()/3,zona1.centerY());
		Rect zona1_2 = new Rect(padding,zona1.centerY(),getWidth()/3,getHeight()-padding);
		Rect zona1_2_1 = new Rect(padding,zona1.centerY(),getWidth()/3,zona1.centerY()+(zona1.centerY()/2)-(padding/2));
		
		paint.setColor(backgroundColor);
		canvas.drawRect(background,paint);
		
		//paint.setColor(Color.RED);
		//canvas.drawRect(zona1,paint);
		
		//paint.setColor(Color.YELLOW);
		//canvas.drawRect(zona1_1,paint);
		//paint.setColor(Color.RED);
		//canvas.drawRect(zona1_2,paint);
		//paint.setColor(Color.RED);
		//canvas.drawRect(zona1_2_1,paint);
		
		//paint.setColor(Color.YELLOW);
		//canvas.drawRect(zona2,paint);
		
		paint.setColor(Color.GRAY);
		for(int i=1;i<=5;i++){
			canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*i-line,getRight()-padding,padding+getHeightLine()*i+line),round,round,paint);
		}
		
		paint.setColor(Color.WHITE);
		canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*1-line,getWidth()/3+(int)getLine(five),padding+getHeightLine()*1+line),round,round,paint);
		canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*2-line,getWidth()/3+(int)getLine(four),padding+getHeightLine()*2+line),round,round,paint);
		canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*3-line,getWidth()/3+(int)getLine(three),padding+getHeightLine()*3+line),round,round,paint);
		canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*4-line,getWidth()/3+(int)getLine(two),padding+getHeightLine()*4+line),round,round,paint);
		canvas.drawRoundRect(new RectF(getWidth()/3,padding+getHeightLine()*5-line,getWidth()/3+(int)getLine(one),padding+getHeightLine()*5+line),round,round,paint);
		
		paint.setColor(textColor);
		paint.setTextSize(16);
		canvas.drawText("5",getWidth()/3-15,padding+getHeightLine()*1+5,paint);
		canvas.drawText("4",getWidth()/3-15,padding+getHeightLine()*2+5,paint);
		canvas.drawText("3",getWidth()/3-15,padding+getHeightLine()*3+5,paint);
		canvas.drawText("2",getWidth()/3-15,padding+getHeightLine()*4+5,paint);
		canvas.drawText("1",getWidth()/3-15,padding+getHeightLine()*5+5,paint);
		
		paint.setTextSize(30);
		drawFixedText(f.format(getRating()),zona1,paint,canvas);
		paint.setTextSize(16);
		drawFixedText("Оценок:"+getNumbers(),zona1_2_1,paint,canvas);
	}
	
	public float getRating(){
		return (float)((1*one)+(2*two)+(3*three)+(4*four)+(5*five))/(float)getNumbers();
	}
	
	public int getNumbers(){
		return one+two+three+four+five;
	}
	
	public float getLine(int i){
		return ((float)((getRight()-padding)-(getWidth()/3))/(float)getNumbers())*(float)i;
	}
	
	public int getHeightLine(){
		Rect background = new Rect(getLeft()+padding,getTop()+padding,getRight()-padding,getBottom()-padding);
		return (background.height()/6);
	}
	
	public void drawFixedText(String text, Rect rect, Paint paint,Canvas canvas) {
		float textSize = paint.getTextSize();
		float textWidth = paint.measureText(text);

		while (textWidth > rect.width())  {
			textSize--;
			paint.setTextSize(textSize);
			textWidth = paint.measureText(text);
		}

		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		canvas.drawText(text,rect.centerX() - textWidth / 2,rect.centerY() - (bounds.top + bounds.bottom) / 2,paint);
	}
	
	public void setPadding(int size){
		padding = size;
		invalidate();
	}
	
	public void setLine(int size){
		line = size;
		invalidate();
	}
	
	public void setRound(int size){
		round = size;
		invalidate();
	}

	@Override
	public void setBackgroundColor(int color){
		backgroundColor = color;
		invalidate();
	}
	
	public void setTextColor(int color){
		textColor = color;
		invalidate();
	}
	
	public void setNumber(int one,int two,int three,int four,int five){
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		invalidate();
	}
}
