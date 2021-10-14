package com.core.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**   
 * 项目名称：AndroidSoldier
 * 类名称：MyGridView</br>   
 * 类描述：自定义GridView</br>   
 * 类详细描述: 在ScrollView中嵌套GridView</br>
 * 创建人：ywm</br>
 * 创建时间：2013-02-24 上午8:54:04</br>   
 * 修改人：Administrator</br>
 * 修改时间：2013-02-24 上午8:54:04</br>   
 * 修改备注：</br>   
 * @version</br>    
 *    
 */ 
public class MyNoGridView extends GridView {

    public MyNoGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNoGridView(Context context) {
        super(context);
    }

    public MyNoGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle); 
    } 

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec); 
    }


    /*********************** 不要拦截父控件ListView的下拉刷新事件 start *************************/
    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    */

    /**
     * 为了让ChildListView的adapter中的控件可以触发点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }


    /**
     * 为了让外层的AutoListView可以下拉刷新
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
    /*********************** 不要拦截父控件ListView的下拉刷新事件  end *************************/

} 