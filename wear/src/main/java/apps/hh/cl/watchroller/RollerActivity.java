package apps.hh.cl.watchroller;

import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;

import apps.hh.cl.watchroller.Adapter.DiceTypeAdapter;
import apps.hh.cl.watchroller.Common.Utils;
import apps.hh.cl.watchroller.TouchHelper.AddDiceTouchHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RollerActivity extends WearableActivity{
    @BindView(R.id.rv_dice_selector)
    public WearableRecyclerView diceRecyclerView;
    private DiceTypeAdapter diceTypeAdapter;

    public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roller);
        unbinder = ButterKnife.bind(this);

        diceTypeAdapter = new DiceTypeAdapter(Utils.DICE_TYPES);
        AddDiceTouchHelper addADice = new AddDiceTouchHelper(diceTypeAdapter);

        diceRecyclerView.setLayoutManager(new WearableLinearLayoutManager(RollerActivity.this));
        diceRecyclerView.setEdgeItemsCenteringEnabled(true);
        diceRecyclerView.setCircularScrollingGestureEnabled(true);
        diceRecyclerView.setBezelFraction(0.5f);
        diceRecyclerView.setScrollDegreesPerScreen(90);
        diceRecyclerView.setAdapter(diceTypeAdapter);

        ItemTouchHelper addADiceHelper = new ItemTouchHelper(addADice);
        addADiceHelper.attachToRecyclerView(diceRecyclerView);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(diceRecyclerView);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
