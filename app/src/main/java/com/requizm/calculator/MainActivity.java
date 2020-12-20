package com.requizm.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {

    TextView l_result;
    int parantCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l_result = findViewById(R.id.l_result);

    }

    public void b_1Click(View view) {
        pressedNumber(1);
    }

    public void b_2Click(View view) {
        pressedNumber(2);
    }

    public void b_3Click(View view) {
        pressedNumber(3);
    }

    public void b_4Click(View view) {
        pressedNumber(4);
    }

    public void b_5Click(View view) {
        pressedNumber(5);
    }

    public void b_6Click(View view) {
        pressedNumber(6);
    }

    public void b_7Click(View view) {
        pressedNumber(7);
    }

    public void b_8Click(View view) {
        pressedNumber(8);
    }

    public void b_9Click(View view) {
        pressedNumber(9);
    }

    public void b_0Click(View view) {
        if (!l_result.getText().equals("0")) {
            pressedNumber(0);
        }
    }

    public void b_OpenParClick(View view) {
        CharSequence a = l_result.getText();

        if (a.equals("0")) {
            l_result.setText("(");
            parantCount = parantCount + 1;
            return;
        }

        char b = a.charAt(a.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-') {
            l_result.append("(");
            parantCount = parantCount + 1;
        }

    }

    public void b_CloseParClick(View view) {
        if (parantCount > 0) {
            l_result.append(")");
            parantCount = parantCount - 1;
        }
    }

    public void b_CClick(View view) {
        l_result.setText("0");
    }

    public void b_DivClick(View view) {
        CharSequence a = l_result.getText();
        char b = a.charAt(a.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-') {
            return;
        }
        l_result.append("/");
    }

    public void b_MultiplyClick(View view) {
        CharSequence a = l_result.getText();
        char b = a.charAt(a.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-') {
            return;
        }
        l_result.append("*");
    }

    public void b_PlusClick(View view) {
        CharSequence a = l_result.getText();
        char b = a.charAt(a.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-') {
            return;
        }
        l_result.append("+");
    }

    public void b_MinusClick(View view) {
        CharSequence a = l_result.getText();
        char b = a.charAt(a.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-') {
            return;
        }
        l_result.append("-");
    }

    public void b_BackClick(View view) {
        if (!l_result.getText().equals("0")) {
            CharSequence oldText = l_result.getText();
            if (l_result.getText().length() > 1) {
                CharSequence c = oldText.subSequence(0, oldText.length() - 1);
                l_result.setText(c);
            } else if (oldText.length() == 1) {
                l_result.setText("0");
            }


            if (oldText.charAt(oldText.length() - 1) == '(') {
                parantCount = parantCount - 1;
            }
            if (oldText.charAt(oldText.length() - 1) == ')') {
                parantCount = parantCount + 1;
            }
        }
    }

    public void b_EqualsClick(View view) {
        CharSequence c = l_result.getText();
        char b = c.charAt(c.length() - 1);
        if (b == '/' || b == '*' || b == '+' || b == '-' || b == '(' || b == ')') {
            l_result.setText(c.subSequence(0, c.length() - 1));
        }

        for (int i = 0; i < parantCount; i++) {
            l_result.append(")");
        }

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            Double a = (Double) engine.eval(l_result.getText().toString());
            l_result.setText(String.valueOf(a.intValue()));
            parantCount = 0;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public void pressedNumber(int num) {
        if(l_result.getText().length() > 20){
            return;
        }

        if (l_result.getText().toString().equals("0")) {
            l_result.setText((String.valueOf(num)));
            return;
        }
        l_result.append((String.valueOf(num)));
    }
}