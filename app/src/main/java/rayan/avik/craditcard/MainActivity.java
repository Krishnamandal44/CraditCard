package rayan.avik.craditcard;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton next,done;
    Animation fliping;
    AnimatorSet set,setReverse;
    CardView frontSide, backSide;
    EditText cardNumber;
    String showcardnumber;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        setReverse = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip_reverse);
        frontSide = (CardView) findViewById(R.id.card_front_side);
        backSide = (CardView) findViewById(R.id.card_back_side);
        next = (ImageButton) findViewById(R.id.btn_next);
        done = (ImageButton) findViewById(R.id.btn_done);
        cardNumber = (EditText) findViewById(R.id.edittext_card_number);

        ////////////////////////
        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int x;
                Log.e("onTextChanged: ", "CharSequence"+s+ "start: "+start+"before: "+before+"count: "+count);
                for(x=1;x<4;x++){

                    if (start+1 == (4*x+(x-1)) ) {
                        if (count!=0) {
                            cardNumber.append(" ");
                        }
                    }else if (start == (4*x+(x-1))+1){
                        if (count==0){
                            cardNumber.getText().delete(start - 1, start);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable text) {

            }
        });


        /////////////////////////////
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frontSide.setVisibility(View.GONE);
                backSide.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);

                set.setTarget(backSide);
                set.start();

                showcardnumber = cardNumber.getText().toString().replace(" ","").trim();
                Toast.makeText(MainActivity.this, "Your Card Number : "+showcardnumber, Toast.LENGTH_SHORT).show();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frontSide.setVisibility(View.VISIBLE);
                backSide.setVisibility(View.GONE);
                done.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                cardNumber.setText("");

                setReverse.setTarget(frontSide);
                setReverse.start();
            }
        });
    }
}
