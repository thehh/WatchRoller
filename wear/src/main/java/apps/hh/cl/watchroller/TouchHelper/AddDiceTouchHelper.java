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
    }

    @Override
    public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        DiceViewHolder holder = (DiceViewHolder) viewHolder;
        boolean shouldShowSecond = false;
        boolean shouldShowThird = false;
        boolean shouldHideThird = false;
        boolean shouldHideSecond = false;
        if(previousDx >= 0 && dX < 0){
            // <--
        }
        else if(previousDx <= 0 && dX > 0){
            // -->
        }
    }
}
