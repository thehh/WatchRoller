package apps.hh.cl.watchroller;

import android.os.Bundle;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;

import apps.hh.cl.watchroller.Adapter.DiceTypeAdapter;
import apps.hh.cl.watchroller.Common.Commons;
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

        diceTypeAdapter = new DiceTypeAdapter(Commons.DICE_TYPES);

        diceRecyclerView.setLayoutManager(new WearableLinearLayoutManager(RollerActivity.this));
        diceRecyclerView.setEdgeItemsCenteringEnabled(true);
        diceRecyclerView.setCircularScrollingGestureEnabled(true);
        diceRecyclerView.setBezelFraction(0.5f);
        diceRecyclerView.setScrollDegreesPerScreen(90);
        diceRecyclerView.setAdapter(diceTypeAdapter);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
