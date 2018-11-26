package apps.hh.cl.watchroller.touchhelper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import apps.hh.cl.watchroller.adapter.DiceTypeAdapter;
import apps.hh.cl.watchroller.viewholder.DiceViewHolder;

public class AddDiceTouchHelper extends ItemTouchHelper.SimpleCallback{
    private DiceTypeAdapter diceAdapter;
    private float previousDx = 0;
    private int thisDiceSwipeCount = 0;
    private float sensitivity = 100f;

    public AddDiceTouchHelper(DiceTypeAdapter diceAdapter){
        super(0,     ItemTouchHelper.START | ItemTouchHelper.END);
        this.diceAdapter = diceAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        previousDx = 0;
        //thisDiceSwipeCount = ((DiceViewHolder) viewHolder).getSwipeCount();
        if(direction == ItemTouchHelper.START){ //<-
            ((DiceViewHolder) viewHolder).setSwipeCount(thisDiceSwipeCount--);
            thisDiceSwipeCount = thisDiceSwipeCount > 0 ? thisDiceSwipeCount : 0;
            ((DiceViewHolder) viewHolder).setupDices(thisDiceSwipeCount);
        }
        else if(direction == ItemTouchHelper.END){ //->
            ((DiceViewHolder) viewHolder).setSwipeCount(thisDiceSwipeCount++);
            ((DiceViewHolder) viewHolder).setupDices(thisDiceSwipeCount);
        }
        this.diceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private boolean movingLeft(float f1, float f2){
        return (f1>= 0 && f2 < sensitivity);
    }

    private boolean movingRight(float f1, float f2){
        return (f1 <= 0 && f2 > sensitivity);
    }
}
