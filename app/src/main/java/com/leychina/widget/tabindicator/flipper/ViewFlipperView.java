package com.leychina.widget.tabindicator.flipper;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.leychina.R;

/**
 * 这是一个类似于ViewFlipper的Wiget。
 * @author ydl
 *
 */
public class ViewFlipperView extends FrameLayout implements IAdImages {

	private Context context;                           // 调用方的上下文
	private int currentAdImgIndex;                     // 当前广告图片索引
	private Animation left2RightInAnimation;           // 广告图片从左到右进入屏幕动画
	private Animation left2RightOutAnimation;          // 广告图片从左到右出去屏幕动画
	private Animation right2LeftInAnimation;           // 广告图片从右到左进入屏幕动画
	private Animation right2LeftOutAnimation;          // 广告图片从右到左出去屏幕动画
	private int animationDuration = 500;               // 动画花费时间1000毫秒
	private ViewFlipper mViewFlipper;                  // 滑动页面控件
	private LinearLayout mTipLinearLayout;             // 下方点点控件
	private float startX = 0;                          // touch action down 时的x坐标
	private float endX = 0;                            // touch action up 时的x坐标
	
	
	public ViewFlipperView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		setView();
	}

	public ViewFlipperView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		setView();
	}

	public ViewFlipperView(Context context) {
		super(context);
		this.context = context;
		setView();
	}

	/**
	 * 显示View
	 */
	private void setView(){
		
		// 初始化
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		mViewFlipper = new ViewFlipper(context);
		mTipLinearLayout = new LinearLayout(context);
		// 初始化动画
		left2RightInAnimation = new TranslateAnimation(-screenWidth, 0, 0, 0);
		left2RightInAnimation.setDuration(animationDuration);
		left2RightOutAnimation = new TranslateAnimation(0, screenWidth, 0, 0);
		left2RightOutAnimation.setDuration(animationDuration);
		right2LeftInAnimation = new TranslateAnimation(screenWidth, 0, 0, 0);
		right2LeftInAnimation.setDuration(animationDuration);
		right2LeftOutAnimation = new TranslateAnimation(0, -screenWidth, 0, 0);
		right2LeftOutAnimation.setDuration(animationDuration);

		
		// 将广告图片加入ViewFlipper
		for(int i=0; i<adImages.length; i++){
			ImageView image = new ImageView(context);
			image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			image.setImageResource(adImages[i]);
			mViewFlipper.addView(image);
		}
		addView(mViewFlipper);
		
		// 将图片索引点动态加入
		for(int i=0; i<adImages.length; i++){
			ImageView image = new ImageView(context);
			if(i == 0){
				image.setImageResource(R.drawable.point_selected);
			}else{
				image.setImageResource(R.drawable.point_normal);
			}
			image.setPadding(5, 0, 5, 20);
			mTipLinearLayout.addView(image);
		}
		// 放置在左下角
		mTipLinearLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
		addView(mTipLinearLayout);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			startX = event.getX();
			
			break;
		case MotionEvent.ACTION_UP:
			
			endX = event.getX();
			// 先保存上一个点
			ImageView lastTipImageView = (ImageView) mTipLinearLayout.getChildAt(currentAdImgIndex);
			
			if(currentAdImgIndex > 0 && endX > startX){// 查看前一页的广告

				mViewFlipper.setInAnimation(left2RightInAnimation);
				mViewFlipper.setOutAnimation(left2RightOutAnimation);
				mViewFlipper.showPrevious();
				currentAdImgIndex--;
				if(currentAdImgIndex < 0){
					currentAdImgIndex = 0;
				}
			}
			
			if(currentAdImgIndex < adImages.length-1 && endX < startX){// 查看后一页的广告

				mViewFlipper.setInAnimation(right2LeftInAnimation);
				mViewFlipper.setOutAnimation(right2LeftOutAnimation);
				mViewFlipper.showNext();
				currentAdImgIndex++;
				if(currentAdImgIndex > adImages.length-1){
					currentAdImgIndex = adImages.length-1;
				}
			}
			
			// 根据currentAdImgIndex改变底部点的状态
			ImageView currTipImageView = (ImageView) mTipLinearLayout.getChildAt(currentAdImgIndex);
			lastTipImageView.setImageResource(R.drawable.point_normal);
			currTipImageView.setImageResource(R.drawable.point_selected);
			
			break;		
		}	
		return true;
	}
}
