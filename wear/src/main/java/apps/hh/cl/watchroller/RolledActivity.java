package apps.hh.cl.watchroller;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class RolledActivity extends WearableActivity {

    private TextView mTextView;

    @BindView(R.id.tv_dice_result)
    public TextView diceResult;

    @BindView(R.id.bt_dice_reroll)
    public Button diceReroll;

    private int diceRoll;
    private String diceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rolled);



        // Enables Always-on
        setAmbientEnabled();
    }

    @OnClick
    public void rerollDice(){
        diceResult.setText(String.valueOf(diceRoll));
    }
}
