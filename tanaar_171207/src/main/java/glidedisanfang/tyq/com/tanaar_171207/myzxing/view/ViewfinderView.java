/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package glidedisanfang.tyq.com.tanaar_171207.myzxing.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import glidedisanfang.tyq.com.tanaar_171207.R;
import glidedisanfang.tyq.com.tanaar_171207.myzxing.camera.CameraManager;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 */
@SuppressLint("DrawAllocation")
public final class ViewfinderView extends View {

  private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
  private static final long ANIMATION_DELAY = 100L;
  private static final int OPAQUE = 0xFF;

  private final Paint paint;
  private Bitmap resultBitmap;
  private final int maskColor;
  private final int resultColor;
  private final int frameColor;
  private final int laserColor;
  private final int resultPointColor;
  private int scannerAlpha;
  private Collection<ResultPoint> possibleResultPoints;
  private Collection<ResultPoint> lastPossibleResultPoints;

  // This constructor is used when the class is built from an XML resource.
  public ViewfinderView(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Initialize these once for performance rather than calling them every time in onDraw().
    paint = new Paint();
    Resources resources = getResources();
    maskColor = resources.getColor(R.color.viewfinder_mask);
    resultColor = resources.getColor(R.color.result_view);
   /* frameColor = resources.getColor(R.color.viewfinder_laser);  //周边框的颜色
    laserColor = resources.getColor(R.color.viewfinder_laser);  //中间线的颜色
    resultPointColor = resources.getColor(R.color.viewfinder_laser);   //点的颜色*/
    frameColor = resources.getColor(R.color.textColor1);  //周边框的颜色
    laserColor = resources.getColor(R.color.textColor1);  //中间线的颜色
    resultPointColor = resources.getColor(R.color.textColor1);   //点的颜色
    scannerAlpha = 0;
    possibleResultPoints = new HashSet<ResultPoint>(5);
  }


  @Override
  public void onDraw(Canvas canvas) {
    Rect frame = CameraManager.get().getFramingRect();
    if (frame == null) {
      return;
    }
    int width = canvas.getWidth();
    int height = canvas.getHeight();

    // Draw the exterior (i.e. outside the framing rect) darkened
    paint.setColor(resultBitmap != null ? resultColor : maskColor);
	// 头部
	canvas.drawRect(0, 0, width, frame.top, paint);
	// 左边
	canvas.drawRect(0, frame.top, frame.left, frame.bottom, paint);
	// 右边
	canvas.drawRect(frame.right, frame.top, width, frame.bottom, paint);
	// 底部
	canvas.drawRect(0, frame.bottom, width, height, paint);

    if (resultBitmap != null) {
      // Draw the opaque result bitmap over the scanning rectangle
      paint.setAlpha(OPAQUE);
      canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
    } else {
//边框的大小
      // Draw a two pixel solid black border inside the framing rect
    	paint.setColor(frameColor);
		// 左上角
		canvas.drawRect(frame.left, frame.top, frame.left + 80,
				frame.top + 5, paint);
		canvas.drawRect(frame.left, frame.top, frame.left + 5,
				frame.top + 80, paint);
		// 右上角
		canvas.drawRect(frame.right - 80, frame.top, frame.right,
				frame.top + 5, paint);
		canvas.drawRect(frame.right - 5, frame.top, frame.right,
				frame.top + 80, paint);
		// 左下角
		canvas.drawRect(frame.left, frame.bottom - 5, frame.left + 80,
				frame.bottom, paint);
		canvas.drawRect(frame.left, frame.bottom - 80, frame.left + 5,
				frame.bottom, paint);
		// 右下角
		canvas.drawRect(frame.right - 80, frame.bottom - 5, frame.right,
				frame.bottom, paint);
		canvas.drawRect(frame.right - 5, frame.bottom - 80, frame.right,
				frame.bottom, paint);

      // Draw a red "laser scanner" line through the middle to show decoding is active
		paint.setColor(laserColor);
      paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
      scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
      int middle = frame.height() / 2 + frame.top;
      canvas.drawRect(frame.left + 2, middle - 1, frame.right - 1, middle + 2, paint);

      Collection<ResultPoint> currentPossible = possibleResultPoints;
      Collection<ResultPoint> currentLast = lastPossibleResultPoints;
      if (currentPossible.isEmpty()) {
        lastPossibleResultPoints = null;
      } else {
        possibleResultPoints = new HashSet<ResultPoint>(5);
        lastPossibleResultPoints = currentPossible;
        paint.setAlpha(OPAQUE);
        paint.setColor(resultPointColor);
        for (ResultPoint point : currentPossible) {
          canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, paint);
        }
      }
      if (currentLast != null) {
        paint.setAlpha(OPAQUE / 2);
        paint.setColor(resultPointColor);
        for (ResultPoint point : currentLast) {
          canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, paint);
        }
      }

      // Request another update at the animation interval, but only repaint the laser line,
      // not the entire viewfinder mask.
      postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
    }
  }

  public void drawViewfinder() {
    resultBitmap = null;
    invalidate();
  }

  /**
   * Draw a bitmap with the result points highlighted instead of the live scanning display.
   *
   * @param barcode An image of the decoded barcode.
   */
  public void drawResultBitmap(Bitmap barcode) {
    resultBitmap = barcode;
    invalidate();
  }

  public void addPossibleResultPoint(ResultPoint point) {
    possibleResultPoints.add(point);
  }

}
