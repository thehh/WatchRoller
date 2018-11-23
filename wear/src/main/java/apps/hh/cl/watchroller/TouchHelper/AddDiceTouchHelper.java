package apps.hh.cl.watchroller.TouchHelper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import apps.hh.cl.watchroller.Adapter.DiceTypeAdapter;
import apps.hh.cl.watchroller.ViewHolder.DiceViewHolder;

public class AddDiceTouchHelper extends ItemTouchHelper.SimpleCallback{
    private DiceTypeAdapter diceAdapter;
    private float previousDx = 0;
    private int swipeCount = 0;
    private float sensitivity = 100f;

    public AddDiceTouchHelper(DiceTypeAdapter diceAdapter){
        super(0,     ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.diceAdapter = diceAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        previousDx = 0;
        if(direction == ItemTouchHelper.LEFT){ //<-
            ((DiceViewHolder) viewHolder).setSwipeCount(swipeCount--);
            swipeCount = swipeCount > 0 ? swipeCount : 0;
            ((DiceViewHolder) viewHolder).setupDices(swipeCount);
        }
        else if(direction == ItemTouchHelper.RIGHT){ //->
            ((DiceViewHolder) viewHolder).setSwipeCount(swipeCount++);
            ((DiceViewHolder) viewHolder).setupDices(swipeCount);
        }
        this.diceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        DiceViewHolder holder = (DiceViewHolder) viewHolder;

        /*if(movingLeft(previousDx, dX)){ //<-
            ((DiceViewHolder) viewHolder).setSwipeCount(swipeCount--);
            swipeCount = swipeCount > 0 ? swipeCount : 0;
            ((DiceViewHolder) viewHolder).setupDices(swipeCount);
        }
        else if(movingRight(previousDx, dX)){ //->
            ((DiceViewHolder) viewHolder).setSwipeCount(swipeCount++);
            ((DiceViewHolder) viewHolder).setupDices(swipeCount);
        }*/
    }

    private boolean movingLeft(float f1, float f2){
        return (f1>= 0 && f2 < sensitivity);
    }

    private boolean movingRight(float f1, float f2){
        return (f1 <= 0 && f2 > sensitivity);
    }
}
